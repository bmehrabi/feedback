import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import {QueryClient, VueQueryPlugin} from "@tanstack/vue-query";
import { vuetify } from "./plugins/vuetify";

import "@mdi/font/css/materialdesignicons.css";

const queryClient = new QueryClient();

createApp(App)
  .use(vuetify)
  .use(VueQueryPlugin, { queryClient })
  .mount('#app')
