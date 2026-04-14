import { createRouter, createWebHashHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import HobbiesView from '../views/HobbiesView.vue'

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/hobbies',
      name: 'hobbies',
      component: HobbiesView
    }
  ]
})

export default router;
