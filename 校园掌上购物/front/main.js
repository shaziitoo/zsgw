import Vue from 'vue'
import App from './App'
import store from './store';
import utils from './utils/utils.js'
import api from './api/index.js'
import base from './api/base.js'
import * as validate from 'utils/validate.js'
// 工具类
import { isAuth,isgrAuth } from '@/utils/system'
// 弹出层
import uniPopup from "./components/uni-popup/uni-popup.vue"
// 上拉,下拉组件
import MescrollUni from "./components/mescroll-uni/mescroll-uni.vue";
import cuCustom from 'colorui/components/cu-custom.vue'
// 上拉,下拉组件
import uniLoadMore from "./components/uni-load-more/uni-load-more.vue"
import HeyCarousel from "./components/hey-ui/components/hey-carousel/hey-carousel.vue"
var amapFile = require('@/utils/amap-wx.130.js');
Vue.prototype.myAmapFun = new amapFile.AMapWX({
	key: '3d53b903a420219fbe0ae6fc30f569ff'
});
Vue.prototype.$utils = utils
Vue.prototype.$base = base
Vue.prototype.$api = api
Vue.prototype.$validate = validate
// 判断权限方法
Vue.prototype.isAuth = isAuth
Vue.prototype.isgrAuth = isgrAuth

Vue.component('uniLoadMore', MescrollUni)
Vue.component('cu-custom',cuCustom)
Vue.component('mescroll-uni', MescrollUni)
Vue.component('uni-popup',uniPopup)
Vue.component("hey-carousel",HeyCarousel)

Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
	store,
	...App
})
app.$mount()
