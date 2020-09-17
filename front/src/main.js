import Vue from "vue";
import App from "./App.vue";
import store from "./store/store";
import router from './router'
import GAuth from 'vue-google-oauth2'

import modal from "vue-js-modal";
import vuetify from './plugins/vuetify';
import VueMoment from 'vue-moment'
import BootstrapVue from 'bootstrap-vue'
import Gravatar from 'vue-gravatar';

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(GAuth, {clientId: 'google api client id', scope: 'profile email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/calendar'})
Vue.component('v-gravatar', Gravatar);
Vue.use(BootstrapVue)
Vue.use(VueMoment);

Vue.config.productionTip = false;
Vue.use(modal, { dialog: true, dynamic: true });

new Vue({
  render: h => h(App),
  vuetify,
  store,
  router,
}).$mount("#app");
