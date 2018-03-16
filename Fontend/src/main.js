// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
//import MintUi from 'mint-ui' // 移动样式组件库
//import 'mint-ui/lib/style.css' // 移动样式组件库样式表
import elementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueRouter from 'vue-router' // vue路由
import VueResoure from 'vue-resource' //vue ajax请求库
import { router_config } from './routers' //路由配置
import axios from 'axios'
//import axios from './service/http'
import store from './store/store' // vue 模块管理
import * as types from './store/types'
import util from './util'
import { HOST } from './service/HOST'
import ipfsAPI from 'ipfs-api';
Vue.use(ipfsAPI)
console.log(ipfsAPI)
Vue.use(VueRouter)
Vue.use(VueResoure)
Vue.use(elementUI)
//Vue.use(MintUi)
Vue.use(util);
const router = new VueRouter(router_config);
router.beforeEach((to, from, next) => {
  if (to.matched.some(r => r.meta.requireAuth!=false)) {
    if (store.state.token) {
      axios.defaults.headers={'Access-Control-Allow-Origin': '*'},
      axios.defaults.auth = {
        username: store.state.token,
      };
      next();
    }
    else {
      next({path: '/login'})
    }
  }
  else {
    next();
  }
});
// axios 配置
//axios.defaults.timeout = 5000;
axios.defaults.baseURL =HOST;
// http request 拦截器
axios.interceptors.request.use(
  config => {
    //axios.defaults.headers={'Access-Control-Allow-Origin': '*'}
   /* console.log(config.headers)
    config.headers = `token ${store.state.token}`;*/
    return config;
  },
  err => {
    return Promise.reject(err);
  });
// http response 拦截器
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          store.commit(types.LOGOUT);
          router.replace({path: 'login'})
      }
    }
    return Promise.reject(error.response.data)
  });
Vue.prototype.axios = axios;
new Vue({
  router,
  store,
  axios,
  http: {
    root: './',
    headers: {
      Authorization: 'RDJ'
    }
  },
  render: (h) => h(App)
}).$mount('#app');

Vue.http.options.emulateJSON = true;
