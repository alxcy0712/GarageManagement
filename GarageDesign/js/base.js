import { createApp } from 'vue'
import App from './App.vue';

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

const app = createApp(App)
app.use(ElementUI)
app.mount('#app')



import { defineComponent, ref } from 'vue'

export default defineComponent ({
  setup() {
    return {
      carId: ref('')
    }
  }
})
