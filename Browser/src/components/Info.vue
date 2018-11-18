<template>
  <div id="info" class="form-sign">
    <!-- Modal -->
    <div class="modal fade" id="confirmLogout" tabindex="-1" role="dialog" aria-labelledby="confirmLogoutTitle"
         aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered " role="document">
        <div class="modal-content">

          <div class="modal-body">
            <h5>确认注销吗？</h5>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal" @click="logout">确认</button>
          </div>
        </div>
      </div>
    </div>
    <div class="form-sign">
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
          <li class="list-group-item">
            <span class=" input-group">
              <input id="mobile" type="number" class="form-control" aria-describedby="basic-addon2"
                     v-model="info.mobile" @input="checkMobile" placeholder="修改手机号"/>
              <div class="input-group-append">
                <button class="btn btn-outline-primary" v-bind:disabled="view.isDisableButton" @click="submit">修改</button>
              </div>
            </span>
          </li>


        </ul>
      </div>
      <br/>
      <button type="button" class="btn btn-danger btn-lg btn-block" data-toggle="modal" data-target="#confirmLogout">
        注销
      </button>
    </div>
  </div>
</template>

<script>
  import {axios, host} from '../../static/js/config'

  export default {
    name: "Info",
    data() {
      return {
        view: {
          title: this.activity.act_name,
          isDisableButton: true, //是否禁用button
          is_check: 0,
          flagMap: {
            max: 2 ** 1 - 1, //中间的1代表下方共有几个有效参数 如需求变更可以修改之
            mobile: 0b1,
          },
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
        if (re.test(this.info.mobile)) {
          this.setNormal('mobile');
        } else {
          if(this.info.mobile.length === 11) toastr.warning('手机号都记错@_@');
          this.setInvalid('mobile');
        }
      },
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
              this.view.isDisableButton = true;
            } else {
                toastr.error(response.data.result);
            }
          })
          .catch((error) => {
            toastr.error('服务器异常 >_<');
            console.log(error);
          });
      },
      logout() {
        axios.get(host + '/logout')
          .then((response) => {
            if (response.data.status === 1) {
              toastr.success('注销成功');
              setTimeout(() => {
                this.$router.push('/');
              }, 1000);
            }
          })
          .catch((error) => {
            toastr.error('服务器异常');
          })
      },
      parseStatus(status) {
        switch (status) {
          case 0:
            return "<span style='color: orange'>面试结果待定</span>";
          case 1:
            return "<span style='color: orange'>报名成功，等待A面</span>";
          case -1:
            return "<span style='color: red'>A面未通过</span>";
          case 2:
            return "<span style='color: darkgreen'>A面通过，等待C面</span>";
          case -2:
            return "<span style='color: red'>B面未通过</span>";
          case 3:
            return "<span style='color: darkgreen'>C面通过，等待D面</span>";
          case -3:
            return "<span style='color: red'>D面未通过</span>";
          case 4:
            return "<span style='color: darkgreen'>D面通过，面试完成</span>";
          default :
            return "加载中.."
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
            setTimeout(() => {
              this.$router.push('/');

            }, 1000)
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
