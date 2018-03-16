// 根路由配置
import { topMenu_route } from './topMenu_route.js'
import store from '../store/store'
import * as types from '../store/types'
const login_view = resolve => require(['../view/login.vue'], resolve);

if (window.localStorage.getItem('token')) {
  store.commit(types.LOGIN, window.localStorage.getItem('token'))
}
if (window.localStorage.getItem('title')) {
  store.commit(types.TITLE, window.localStorage.getItem('title'))
}
const routes=[
  topMenu_route,
  {
    path: '/login',
    component: login_view,
    meta: {
      requireAuth:false,
    }
  },
  { path: '*', redirect: '/login' }
];
export const router_config = {
  routes: routes
};
