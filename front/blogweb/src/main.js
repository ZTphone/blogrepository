// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'

//引入markdown
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import axios from 'axios';
// import VueAxios from "vue-axios";

import md5 from 'js-md5'
//axios.defaults.withCredentials=true;

// 配置请求根路径
//axios.defaults.withCredentials=true //让ajax携带cookie

//axios.defaults.withCredentials=true;//让ajax携带cookie
axios.defaults.baseURL="http://localhost:9191/";


// 通过axios拦截器添加token
// axios.interceptors.request.use(config=>{
//   console.log(config);
//   config.headers.Authorization=window.sessionStorage.getItem('btoken')
//   return config
// })
Vue.prototype.$http=axios;
Vue.prototype.$md5=md5;
// Vue.use(VueAxios,axios);
//

Vue.use(mavonEditor)

Vue.use(ElementUI);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
