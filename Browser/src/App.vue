<template>
    <router-view :activity="activity" :host="host"/>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      host: 'http://xiyoulinux.org:8080/join',
      activity: {
        act_name: '第十五届软件自由日',
        grades: [2015,2016,2017,2018,2019],
        readme: `
            <span style="color: red">致歉声明： 抱歉，由于今年18级新生在教务系统(新)还没有信息记录，
            而我们之前的报名系统使用了教务系统的接口进行实名校验，导致18级同学无法进行报名，
            我们对没有在测试阶段考虑到这个问题进行深刻的反思！
            <span style="color: green"> 现在，系统已经使用新的接口重新开发，感谢您的支持！</span>
            再次表示非常抱歉！</span>
            <br/><br/>
            1.您在报名过程中如果遇到问题，请联系管理员：root@xiyoulinux.org。<br/><br/>
            2.报名成功即可参与15号现场抽奖活动。<br/><br/>
            3.您的所有记录将在活动结束后被彻底清除，除非征得您的同意，您的所有信息不会以任何形式对外透露。<br/>
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
