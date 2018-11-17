<template>
  <div id="info" class="form-sign">
    <form class="form-sign">
      <img class="logo" src="../assets/xiyoulinux.png" alt="" width="40%">
      <h1 class="h4 mb-4 font-weight-normal">西邮Linux兴趣小组<br/>{{view.title}}</h1>
      <div class="card" style="width: 100%;">
        <div class="card-body">
          <h5 class="card-title">面试状态</h5>
          <p class="card-text" v-html="parseStatus(info.process_id)"></p>
        </div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">{{info.admin_class}}</li>
          <li class="list-group-item">{{info.cn_name}}</li>
          <li class="list-group-item">{{info.mobile}}</li>
        </ul>
      </div>
      <br/>
      <button type="button" class="btn btn-danger btn-lg btn-block">注销</button>

    </form>


  </div>
</template>

<script>
  import {axios,host} from '../../static/js/config'

  export default {
    name: "Info",
    data() {
      return {
        view: {
          title: this.activity.act_name,
        },
        info: {
          admin_class: "加载中..",
          cn_name: "加载中..",
          mobile: "加载中..",
          process_id: '加载中..',
          student_no: "加载中.."
        },
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
        axios.post(host + '/info', fd, config)
          .then((response) => {
            console.log(response);
            if (response.data.status === 1) {
              toastr.success('修改成功 ^-^');
            } else {
              response.data.result.forEach((reason) => {
                toastr.error(reason);
              })
            }
          })
          .catch((error) => {
            toastr.error('服务器异常 >_<');
            console.log(error);
          });
      },
      parseStatus(status) {
        switch (status) {
          case 0: return "<span style='color: orange'>面试结果待定</span>";
          case 1: return "<span style='color: orange'>报名成功，等待A面</span>";
          case -1: return "<span style='color: red'>A面未通过</span>";
          case 2: return "<span style='color: darkgreen'>A面通过，等待C面</span>";
          case -2: return "<span style='color: red'>B面未通过</span>";
          case 3: return "<span style='color: darkgreen'>C面通过，等待D面</span>";
          case -3: return "<span style='color: red'>D面未通过</span>";
          case 4: return "<span style='color: darkgreen'>D面通过，面试完成</span>";
          default : return "加载中.."
        }
      }

    },
    props: ['activity'],
    mounted() {
      toastr.options.positionClass = 'toast-top-center';
      document.title = '西邮Linux兴趣小组' + this.view.title;
    },
    beforeMount() {
      axios.get(host + '/info')
        .then((response) => {
          if (response.data.status === 1) {
            this.info = response.data.result;
          } else {
            toastr.error(response.data.result);
            setTimeout(()=>{
              this.$router.push('/');

            },1000)
          }
        })
        .catch((error) => {
          toastr.error('服务器异常 >_<');
          console.log(error);
        });
    }
  }
</script>

<style scoped>
  .logo {
    margin-bottom: 1.5rem;
  }

</style>
