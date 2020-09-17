import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import Calendar from '../views/Calendar.vue'
import Agenda from '../views/Agenda.vue'
import Home2 from '../views/Home2.vue'
import Kanban from '../views/Kanban.vue'

const requireAuth = () => (to,from,next) => {
  if(sessionStorage.getItem("access-token")==null) {
    alert("로그인이 필요한 접근입니다.");
    next('/');
  } else {
    next();
  }
}

Vue.use(VueRouter)

  const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/home2',
    name: 'Home',
    component: Home
  },
  {
    path: '/',
    name: 'Home2',
    component: Home2
  },
  {
    path: '/agenda',
    name: 'Calendar',
    component: Calendar
  },
  {
    path: '/calendar',
    component: Agenda,
    beforeEnter:requireAuth(),
  },
  {
    path: '/kanban',
    component: Kanban,
    beforeEnter:requireAuth(),
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router