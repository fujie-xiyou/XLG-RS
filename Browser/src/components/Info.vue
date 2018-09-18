<template>
  <div id="info" class="form-sign">
    <form class="form-sign">
      <img class="logo" src="../assets/xiyoulinux.png" alt="" width="45%">
      <h1 class="h4 mb-4 font-weight-normal">西邮Linux兴趣小组<br/>{{view.title}}</h1>
      <div class="row">
        <div class="col-5">学号：</div>
        <div class="col">{{info.student_no}}</div>
      </div>
      <div class="row">
        <div class="col-5">姓名：</div>
        <div class="col">{{info.cn_name}}</div>
      </div>
      <div class="row">
        <div class="col-5">班级：</div>
        <div class="col">{{info.admin_class}}</div>
      </div>
      <div class="row">
        <div class="col-5">手机号：</div>
        <div class="col">{{info.mobile}}<a href="">修改</a></div>
      </div>
      <div class="row">
        <div class="col-5">面试状态：</div>
        <div class="col status">{{info.status}}</div>
      </div>
    </form>

  </div>
</template>

<script>
  export default {
    name: "Info",
    data() {
      return {
        view: {
          title: this.activity.act_name + "状态查询",
        },
        info: null,
      }
    },
    methods: {
      submit() {
        var fd = 'mobile=' + this.info.mobile;
        let config = {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        };
        this.$axios.post(this.host + '/info', fd, config)
          .then((response) => {
            console.log(response);
            if (response.data.status.statusCode === 1) {
              toastr.success('修改成功 ^-^');
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
    beforeMount(){
      /*  this.$axios.get(this.host+'/info')
    .then((response) =>{
      if(response.data.status.statusCode === 1){
        this.info = response.data.result;
      }else {
        response.data.status.reasons.forEach((reason) => {
          toastr.error(reason);
        });
      }
    })
    .catch((error) =>{
      toastr.error('服务器异常 >_<')
      console.log(error);
    })
*/
      this.info = {
        student_no: '04163028',
        cn_name: '刘付杰',
        admin_class: '软件1601',
        mobile: '15596207601',
        status: '三面通过'
      }
    }
  }
</script>

<style scoped>
  .logo {
    margin-bottom: 1.5rem;
  }

  .row {
    text-align: left;
  }

  .col.status {
    color: red;
  }
</style>
