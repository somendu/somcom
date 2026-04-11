import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import HobbiesView from "../views/HobbiesView.vue";

const routes = [
  { path: "/", component: HomeView },
  { path: "/hobbies", component: HobbiesView }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
