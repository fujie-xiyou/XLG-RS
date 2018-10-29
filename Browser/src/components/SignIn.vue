<template>
  <div id="sign-in" class="form-sign">
    <form class="form-sign">
      <img class="logo" src="../assets/xiyoulinux.png" alt="" width="45%">
      <h1 class="h4 mb-4 font-weight-normal">西邮Linux兴趣小组<br/>{{view.title}}</h1>
      <input type="number" id="student_no" v-model="form.student_no" @blur="checkNo" class="form-control"
             placeholder="请输入学号" required
             autofocus>
      <input type="password" id="password" v-model="form.password" @input="checkPassword" class="form-control"
             placeholder="请输入教务系统密码"
             required>
      <button class="btn btn-lg btn-primary btn-block" type="submit" @click="submit"
              v-bind:disabled="view.isDisableButton">登录
      </button>
    </form>

  </div>
</template>

<script>
  export default {
    name: "SignIn",
    data() {
      return {
        view: {
          title: this.activity.act_name + "状态查询",
          isDisableButton: true, //是否禁用button
          is_check: 0,
          flagMap: {
            student_no: 0b1,
            password: 0b10,
          },
        },
        form: {
          student_no: '',
          password: '',
        },
      }
    },
    methods: {
      setInvalid(id) {
        //与运算 将某一标志位置空
        this.view.is_check &= ~this.view.flagMap[id];
        this.view.isDisableButton = (this.view.is_check < 3);
        document.getElementById(id).classList.add('is-invalid');
      },
      setNormal(id) {
        //或运算 设置某一标志位
        this.view.is_check |= this.view.flagMap[id];
        this.view.isDisableButton = (this.view.is_check < 3);
        document.getElementById(id).classList.remove('is-invalid');
      },
      checkNo() {
        let re = /^[01]\d(\d{2})\d{4}$/;
        let arr = re.exec(this.form.student_no);
        let maxGrade = Math.max(...this.activity.grades) - 2000;
        let minGrade = Math.min(...this.activity.grades) - 2000;
        if (arr) {
          if (arr[1] > maxGrade) {
            // toastr.warning('你还小，不能报名哦。');
            this.setInvalid('student_no');
          } else if (arr[1] < minGrade) {
            // toastr.warning('你已经过了报名的年纪。');
            this.setInvalid('student_no');
          } else {
            this.setNormal('student_no');
          }
        } else {
          this.setInvalid('student_no');
        }
      },
      checkPassword() {
        let len = this.form.password.length;
        if(len < 6 || len > 26){
          this.setInvalid('password')
        }else {
          this.setNormal('password');

        }
      },

      submit() {
        var fd = 'student_no=' + this.form.student_no + '&password=' + this.form.password;
        let config = {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        };
        this.$axios.post(this.host + '/signIn', fd, config)
          .then((response) => {
            console.log(response);
            if (response.data.status.statusCode === 1) {
              toastr.success('登陆成功 ^-^');
            } else {
              response.data.status.reasons.forEach((reason) => {
                toastr.error(reason);
              })
            }
          })
          .catch((error) => {
            toastr.error('服务器异常 >_<');
            console.log(error);
          });
      },

    },
    props: ['host', 'activity'],
    mounted() {
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
    margin-bottom: -1px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
    margin-bottom: 1.5rem;
  }


</style>
