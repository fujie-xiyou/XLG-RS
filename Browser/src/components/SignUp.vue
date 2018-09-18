<template>
  <div id="sign-up" class="form-sign">
    <button type="button" id="readme-button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
      README
    </button>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">报名须知</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p v-html="activity.readme"></p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">我知道了</button>
          </div>
        </div>
      </div>
    </div>
    <form class="form-sign">
      <img class="logo" src="../assets/xiyoulinux.png" alt="" width="45%">
      <h1 class="h4 mb-4 font-weight-normal">西邮Linux兴趣小组<br/>{{view.title}}</h1>
      <input type="number" id="student_no" v-model="form.student_no" @blur="checkNo" class="form-control"
             placeholder="请输入学号" required
             autofocus>
      <input type="password" id="password" v-model="form.password" @blur="checkPassword" class="form-control"
             placeholder="请输入教务系统密码"
             required>
      <input type="number" id="mobile" v-model="form.mobile" class="form-control" @blur="checkMobile"
             placeholder="请输入手机号" required>
      <input type="text" id="checkCode" v-model="form.checkCode" class="form-control" @input="checkCheckCode"
             placeholder="请输入验证码"
             required>
      <img v-bind:src="view.checkCodeImageUrl" id="checkImage" @click="reloadImage" v-if="view.isShowCheckCode"/>
      <button class="btn btn-lg btn-primary btn-block" type="submit" @click="submit"
              v-bind:disabled="view.isDisableButton">提交
      </button>
    </form>

  </div>
</template>

<script>
  export default {
    name: "SignUp",
    data() {
      return {
        view: {
          title: this.activity.act_name + "报名",
          isShowCheckCode: false,//用来决定验证码是是否显示
          isDisableButton: true, //是否禁用button
          is_check: 0,
          flagMap: {
            student_no: 0b1,
            password: 0b10,
            mobile: 0b100,
            checkCode: 0b1000
          },
          checkCodeImageUrl: this.host + '/checkCode.gif',
        },
        form: {
          student_no: '',
          password: '',
          mobile: '',
          checkCode: '',
        },

      }
    },
    methods: {
      setInvalid(id) {
        //与运算 将某一标志位置空
        this.view.is_check &= ~this.view.flagMap[id];
        this.view.isShowCheckCode = (this.view.is_check === 7 || this.view.is_check === 15);
        this.view.isDisableButton = (this.view.is_check < 16);
        document.getElementById(id).classList.add('is-invalid');
      },
      setNormal(id) {
        //或运算 设置某一标志位
        this.view.is_check |= this.view.flagMap[id];
        this.view.isShowCheckCode = (this.view.is_check === 7 || this.view.is_check === 15);
        this.view.isDisableButton = (this.view.is_check < 15);
        document.getElementById(id).classList.remove('is-invalid');
      },
      checkNo() {
        let re = /^[01]\d(\d{2})\d{4}$/;
        let arr = re.exec(this.form.student_no);
        let maxGrade = Math.max(...this.activity.grades) - 2000;
        let minGrade = Math.min(...this.activity.grades) - 2000;
        if (arr) {
          if (arr[1] > maxGrade) {
            toastr.warning('你还小，不能报名哦。');
            this.setInvalid('student_no');
          } else if (arr[1] < minGrade) {
            toastr.warning('你已经过了报名的年纪。');
            this.setInvalid('student_no');
          } else {
            this.setNormal('student_no');
          }
        } else {
          this.setInvalid('student_no');
        }
      },
      checkPassword() {
        let re = /.{6,26}/;
        if (re.test(this.form.password)) {
          this.setNormal('password');
        } else {
          this.setInvalid('password')
        }
      },
      checkMobile() {
        let re = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
        if (re.test(this.form.mobile)) {
          this.setNormal('mobile');
        } else {
          this.setInvalid('mobile');
        }
      },
      checkCheckCode() {
        let len = this.form.checkCode.length;
        if (len === 4) {
          let re = /[a-zA-Z0-9]{4}/;
          if (re.test(this.form.checkCode)) {
            this.setNormal('checkCode');
          } else {
            this.setInvalid('checkCode');
          }
        } else {
          this.setInvalid('checkCode')
        }
      },
      reloadImage() {
        this.view.checkCodeImageUrl += '?';
      },
      submit() {

        var fd = 'student_no=' + this.form.student_no + '&password=' + this.form.password +
          '&mobile=' + this.form.mobile + '&checkCode=' + this.form.checkCode;
        let config = {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        };
        this.$axios.post(this.host + '/signUp', fd, config)
          .then((response) => {
            console.log(response);
            if (response.data.status.statusCode === 1) {
              toastr.success('报名成功 ^-^');
            } else {
              response.data.status.reasons.forEach((reason) => {
                toastr.error(reason);
                this.reloadImage();
              })
            }
          })
          .catch((error) => {
            toastr.error('服务器异常 >_<');
            this.reloadImage();
            console.log(error);
          });
      },

    },
    props: ['host', 'activity'],
    mounted() {
      $('#exampleModal').modal({show : true});
      toastr.options.positionClass = 'toast-top-center';
      document.title = '西邮Linux兴趣小组' + this.view.title;
    },
  }
</script>

<style scoped>
  .logo {
    margin-bottom: 1.5rem;
  }

  #student_no {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  #password, #mobile {
    margin-bottom: -1px;
    border-radius: 0;
  }

  #checkCode {
    margin-bottom: 1.5rem;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }

  #readme-button {
    position: absolute;
    right: 10px;
    top: 10px;
  }

  #exampleModal {
    text-align: left;
  }

  #checkImage {
    z-index: 3;
    position: absolute;
    top: 500px;
    right: 80px;
    float: right;
  }

</style>
