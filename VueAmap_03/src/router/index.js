import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import ShowMarks from "../components/ShowMarks";

Vue.use(Router)

export default new Router({
  routes: [

    {
      path: '/',
      component:HelloWorld
    },
    {
      path: '/ShowMarks/:DriverId',
      component:ShowMarks
    }
  ]
})
