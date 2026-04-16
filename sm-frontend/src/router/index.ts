import { createRouter, createWebHashHistory } from 'vue-router'

// Lazy loading (better for performance)
const HomeView = () => import('../views/HomeView.vue')
const AboutView = () => import('../views/AboutView.vue')
const HobbiesView = () => import('../views/hobbies/HobbiesView.vue')
const PhotographyView = () => import('../views/hobbies/PhotographyView.vue')
const BlogView = () => import('../views/hobbies/BlogView.vue')
const BadmintonView = () => import('../views/hobbies/BadmintonView.vue')

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
      component: HobbiesView,
      children: [
        {
          path: '',
          component: PhotographyView   // default page
        },
        {
          path: 'writeup',
          component: BlogView
        },
        {
          path: 'playa',
          component: BadmintonView
        }
      ]
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    }
  ]
})

export default router
