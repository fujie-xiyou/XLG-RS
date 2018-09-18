<template>
    <router-view :activity="activity" :host="host"/>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      host: 'http://localhost:8080',
      activity: {
        act_name: '2018年纳新',
        grades: [2016,2017,2018],
        readme: `
            1.您在报名过程中如果遇到问题，请联系管理员：root@xiyoulinux.org<br/>
            2.我们承诺，仅使用您的教务管理系统帐号与密码进行实名验证，绝不在纳新系统服务器明文保存您的教务系统密码等任何隐私信息。<br/>
            3.请正确填写手机号以便于我们及时向您发送面试安排。<br/>
            4.您的所有面试记录将在纳新结束后被彻底清除。除非征得您的同意，您的所有信息不会以任何形式对外透露。<br/>
        `,
        status: true,//报名界面只需要知道活动是否结束即可
      }
    }
  },
  mounted(){
    //获取activity

  }
}
</script>

<style>

</style>
