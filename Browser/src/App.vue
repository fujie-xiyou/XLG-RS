<template>
  <router-view :activity="activity" :host="host"/>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {
        // host: 'http://xiyoulinux.org:8080/join',
        host: 'http://localhost:8080',
        activity: {
          act_name: '2019纳新',
          grades: [2015, 2016, 2017, 2018],
          readme: `
            0. 您填写的手机号将作为通知您面试时间地点的唯一方式,我们保证不会用于其他用途,请您务必正确填写。<br/><br/>
            1. 一旦您勾选界面上的<span style="color: red"> "授权我们记录您的姓名和班级信息"</span>,就意味着我们从教务系统平台上获取并且记录您的姓名以及班级信息是合法的,同时我们承诺,绝对不会获取除此之外的任何信息,请您放心报名。<br/><br/>
            2. 您在报名过程中如果遇到问题,请联系：fujie@xiyoulinux.org,或者在纳新交流群,微信公众号等平台留言。<br/><br/>
            3. 报名成功还可以参与宣讲会现场学号抽奖活动哦。<br/><br/>
            4. 您的所有记录将在活动结束后被彻底清除,除非征得您的同意,您的所有信息不会以任何形式对外透露。<br/>
        `,
          status: true,//报名界面只需要知道活动是否结束即可
        }
      }
    },
    mounted() {
      //获取activity

    }
  }
</script>

<style>


</style>
