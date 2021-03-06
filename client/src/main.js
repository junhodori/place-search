import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)
Vue.axios.defaults.baseURL = 'http://localhost:8080'
// Vue.axios.defaults.baseURL = 'http://49.50.160.25:8080'

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
