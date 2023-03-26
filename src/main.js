import Vue from 'vue'
import App from './App.vue'
import './index.css'
import ThemeChange from 'theme-change'

Vue.config.productionTip = false
Vue.use(ThemeChange)

new Vue({
  render: h => h(App),
}).$mount('#app')
