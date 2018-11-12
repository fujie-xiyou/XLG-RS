<template>
  <div id="sign-up" class="form-sign">
    <form class="form-sign">
      <h4>
        <img class="logo" src="../assets/xiyoulinux.png" alt="" width="40%">
      </h4>
      <h1 class="h4 mb-4 font-weight-normal">西邮Linux兴趣小组<br/>{{view.title}}</h1>

      <input type="number" id="mobile" v-model="form.mobile" class="form-control" @blur="checkMobile"
             placeholder="请输入手机号" required>
      <div class="form-check" id="authorization" >
        <input type="checkbox" class="form-check-input" id="check_box" @input="checkAuthorization"/>
        <label class="form-check-label" for="check_box">授权我们获取您的姓名和班级信息</label>
      </div>
      <button id="submit" class="btn btn-lg btn-primary btn-block" type="submit" @click="submit"
              v-bind:disabled="view.isDisableButton">{{form.button_text}}
      </button>
    </form>

  </div>
</template>

<script>
  export default {
    name: "SignUpNext",
    data() {
      return {
        view: {
          title: this.activity.act_name + "报名",
          isShowCheckCode: false,//用来决定验证码是是否显示
          isDisableButton: true, //是否禁用button
          is_check: 0,
          flagMap: {
            max: 2 ** 2 - 1, //4代表下方共有几个有效参数 如需求变更可以修改之
            mobile: 0b1,
            authorization: 0b10,
          },
        },
        form: {
          mobile :'',
          authorization: false,
          button_text: '报名'
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
        delete join.authorization;
        let config = {
          headers: {
            'Content-Type' : 'application/json'
          }

        };
        this.$axios.post(this.host + '/signUpNew', join, config)
          .then((response) => {
            //console.log(response);
            $('#submit').text(this.form.button_text);
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

  #mobile{
    border-top-left-radius: 0;
    border-top-right-radius: 0;
    margin-bottom: 1rem;
  }
  #authorization{
    margin-bottom: 1.5rem;
  }


</style>
