<template>
  <div id="sign-up" class="form-sign">

    <form class="form-sign">
      <h4>
        <img class="logo" src="../assets/xiyoulinux.png" alt="" width="40%">
      </h4>
      <h1 class="h4 mb-4 font-weight-normal">西邮Linux兴趣小组<br/>{{view.title}}</h1>
      <input type="number" id="student_no" v-model="form.student_no" @input="checkNo" class="form-control"
             placeholder="请输入学号" required>
      <input type="password" id="passwd" v-model="form.passwd" class="form-control" @input="checkPassword"
             placeholder="请输入教务系统密码" required/>

      <button id="submit" class="btn btn-lg btn-primary btn-block" type="submit" @click.prevent="submit"
              v-bind:disabled="view.isDisableButton">{{form.button_text}}
      </button>
    </form>

  </div>
</template>

<script>
  import {axios,host} from '../../static/js/config'

  export default {
    name: "SignUp",
    data() {
      return {
        view: {
          title: this.activity.act_name,
          isShowCheckCode: false,//用来决定验证码是是否显示
          isDisableButton: true, //是否禁用button
          is_check: 0,
          flagMap: {
            max: 2 ** 2 - 1, //4代表下方共有几个有效参数 如需求变更可以修改之
            student_no: 0b1,
            passwd: 0b10,
          },
        },
        form: {
          student_no: '',
          passwd: '',
          button_text: '下一步'
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
        if(this.form.student_no.length !== 8){
          this.setInvalid('student_no');
          return;
        }
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
        if (re.test(this.form.passwd)) {
          this.setNormal('passwd');
        } else {
          this.setInvalid('passwd')
        }
      },
      checkMobile() {
        let re = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
        if (re.test(this.form.mobile)) {
          this.setNormal('mobile');
        } else {
          toastr.warning('手机号都记错@_@');
          this.setInvalid('mobile');
        }
      },
      checkAuthorization(){
        this.form.authorization = !this.form.authorization;
        if(this.form.authorization) this.setNormal('authorization');
        else this.setInvalid('authorization');
      },
      submit() {

        var modulus, exponent;
        this.view.isDisableButton = true;
        this.view.is_check = -1; // 防止在响应过程中用户通过修改输入框的事件将按钮又设置为可用
        $('#submit').text('请稍候...');

        let join = Object.assign(this.form);
        delete join.authorization          ;
        let config = {
          headers: {
            'Content-Type' : 'application/json'
          }

        };
        axios.post(host + '/checkUser', join, config)
          .then((response) => {
            $('#submit').text(this.form.button_text);
            this.view.isDisableButton = false;
            this.view.is_check = this.view.flagMap.max;//将按钮设置为可用
            if (response.data.status === 1) {
              //学号密码校验通过 并且尚未注册 跳转至手机号校验页面
              this.$router.push('/sign-up-next');
            }else if(response.data.status ===2){
              //状态码为2  表示已经报过名了  那么就跳转到个人中心
              this.$router.push('/info');
            }
            else {
              toastr.warning(response.data.result)
            }
          })
          .catch((error) => {
            $('#submit').text(this.form.button_text);
            this.view.isDisableButton = false;
            this.view.is_check = this.view.flagMap.max;//将按钮设置为可用
            toastr.error('服务器异常 >_<');
            console.log(error);
          })
          .finally(() => {
            $('#submit').text(this.form.button_text);
            this.view.isDisableButton = false;
            this.view.is_check = this.view.flagMap.max;//将按钮设置为可用
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

  #passwd {
    margin-bottom: 1.5rem;
    border-top-left-radius: 0;
    border-top-right-radius: 0;

  }



</style>
