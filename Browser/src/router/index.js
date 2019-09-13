import Vue from 'vue'
import Router from 'vue-router'
import SignUp from '@/components/SignUp'
import SignUpNew from '@/components/SignUpNew'
import SignIn from '@/components/SignIn'
import Info from '@/components/Info'

Vue.use(Router);

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    {
      path: '/',
      name: 'SignUpNew',
      component: SignUpNew
    },
    {
      path: '/sign-up',
      name: 'SignUp',
      component: SignUp
    },
    {
      path: '/sign-in',
      name: 'SignIn',
      component: SignIn
    },
    {
      path: '/info',
      name: 'Info',
      component: Info
    }
  ]
})
