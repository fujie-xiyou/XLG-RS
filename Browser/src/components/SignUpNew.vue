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
      <h4>
        <img class="logo" src="../assets/xiyoulinux.png" alt="" width="30%">
        &nbsp;&nbsp;+&nbsp;&nbsp;
        <img class="logo" src="../assets/sfd.png" alt="" width="30%">
      </h4>
      <h1 class="h4 mb-4 font-weight-normal">西邮Linux兴趣小组<br/>{{view.title}}</h1>
      <input type="number" id="student_no" v-model="form.student_no" @blur="checkNo" class="form-control"
             placeholder="请输入学号" required>
      <input type="text" id="real_name" v-model="form.real_name" @input="checkName" class="form-control"
             placeholder="请输入姓名"
             required>
      <input type="text" id="admin_class" v-model="form.admin_class" @input="checkClass" class="form-control"
             placeholder="请输入班级"
             required>

      <button id="submit" class="btn btn-lg btn-primary btn-block" type="submit" @click="submit"
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
            max: 7,
            student_no: 0b1,
            real_name: 0b10,
            admin_class: 0b100,
            checkCode: 0b1000
          },
        },
        form: {
          student_no: '',
          real_name: '',
          admin_class: '',
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
        this.view.isDisableButton = (this.view.is_check < this.view.flagMap.max);
        document.getElementById(id).classList.add('is-invalid');
      },
      setNormal(id) {
        //或运算 设置某一标志位
        this.view.is_check |= this.view.flagMap[id];
        this.view.isDisableButton = (this.view.is_check < this.view.flagMap.max);
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
      checkName() {
        if (this.form.real_name === '') {
          this.setInvalid('real_name');
        } else {
          this.setNormal('real_name');
        }
      },
      checkClass() {
        if (this.form.admin_class === '') {
          this.setInvalid('admin_class');
        } else {
          this.setNormal('admin_class');
        }
      },
      submit() {

        //copy from 教务系统源代码
        //获取公钥 对密码进行加密
        var modulus, exponent;
        this.view.isDisableButton = true;
        this.view.is_check = -1; // 防止在响应过程中用户通过修改输入框的事件将按钮又设置为可用
        $('#submit').text('请稍候...');

        var fd = 'student_no=' + this.form.student_no
          + '&real_name=' + encodeURIComponent(this.form.real_name)
          + '&admin_class=' + encodeURIComponent(this.form.admin_class);
        console.log(fd)
        let config = {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        };
        this.$axios.post(this.host + '/signUpNew', fd, config)
          .then((response) => {
            //console.log(response);
            $('#submit').text('提交');
            this.view.isDisableButton = false;
            this.view.is_check = this.view.flagMap.max;//将按钮设置为可用
            if (response.data.status.statusCode === 1) {
              toastr.success('报名成功 ^-^');
            } else {
              response.data.status.reasons.forEach((reason) => {
                toastr.warning(reason);
              })
            }
          })
          .catch((error) => {
            $('#submit').text('提交');
            this.view.isDisableButton = false;
            this.view.is_check = this.view.flagMap.max;//将按钮设置为可用
            toastr.error('服务器异常 >_<');
            console.log(error);
          })
          .finally(() => {
            $('#submit').text('提交');
            this.view.isDisableButton = false;
            this.view.is_check = this.view.flagMap.max;//将按钮设置为可用
          });


      },

    },
    props: ['host', 'activity'],
    mounted() {
      $('#exampleModal').modal({show: true});
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

  #password {
    margin-bottom: 1.5rem;
    border-radius: 0;
  }
  #real_name {
    margin-bottom: -1px;
    border-radius: 0;
  }
  #admin_class {
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


</style>
