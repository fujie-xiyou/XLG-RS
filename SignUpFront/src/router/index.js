import Vue from 'vue'
import Router from 'vue-router'
import SignUpNew from '@/components/SignUpNew'
import Info from '@/components/Info'
import SignUpNext from '@/components/SignUpNext'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'SignUpNew',
      component: SignUpNew
    },

    {
      path: '/info',
      name: 'Info',
      component: Info
    },
    {
      path: '/sign-up-next',
      name: 'SignUpNext',
      component: SignUpNext
    }
  ]
})
