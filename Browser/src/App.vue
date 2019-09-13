<template>
  <router-view :activity="activity" :host="host"/>
</template>

<script>
    export default {
        name: 'App',
        data() {
            return {
                host: 'http://xiyoulinux.org:8080/sfd2019',
                // host: 'http://localhost:8088',
                activity: {
                    act_name: '第十六届软件自由日',
                    grades: [2016, 2017, 2018, 2019],
                    readme: `
            0.本系统使用<a href="http://222.24.3.10/wap/index.html"> 学校图书馆系统 </a>账号密码进行验证，默认密码一般可能为：身份证号后六位、学号后六位、一卡通密码、6个0或者6个8。<br/><br/>
            1.您在报名过程中如果遇到问题，请在QQ群：<a target="_blank"
            href="//shang.qq.com/wpa/qunwpa?idkey=001f28868ecac9641c50f175ebddd2d626102164ac815f557ac15c384d9bb877">
            754164417 </a>进行反馈。<br/><br/>
            2.报名成功即可参与21号现场抽奖活动。<br/><br/>
            3.您的所有记录将在活动结束后被彻底清除，除非征得您的同意，您的所有信息不会以任何形式对外透露。<br/>
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
