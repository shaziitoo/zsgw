<template>
  <div class="min-h-screen w-screen flex relative overflow-hidden">
    <!-- 左侧品牌展示区 -->
    <div class="absolute inset-0 w-full h-full overflow-hidden">
      <div class="relative w-full h-full">
        <img class="absolute inset-0 w-full h-full object-cover transform scale-105"
             src="https://images.unsplash.com/photo-1519681393784-d120267933ba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"
             alt="时尚抽象背景"/>
        <div class="absolute inset-0 bg-gradient-to-br from-purple-900/70 to-blue-900/70 backdrop-blur-sm"></div>
      </div>
      <div class="relative z-10 flex flex-col h-full p-12 text-white">
        <div class="text-4xl font-bold mb-4 tracking-wide">校园掌上购物</div>
        <div class="flex-grow flex items-center justify-center">
          <img class="w-96 h-96 object-contain animate-float"
               src="https://cdn-icons-png.flaticon.com/512/3144/3144456.png" alt="时尚插画"/>
        </div>
        <div class="text-2xl font-light tracking-wider">智能 · 高效 · 创新</div>
        <div class="mt-4 text-sm text-white/70">© 2023 校园掌上购物 版权所有</div>
      </div>
    </div>

    <!-- 右侧登录区 -->
    <div class="relative z-20 w-full min-h-screen flex items-center justify-center p-4">
      <div
          class="w-full max-w-md bg-white/95 backdrop-blur-xl p-10 rounded-3xl shadow-2xl my-8 border border-white/20 transition-all duration-500 hover:shadow-xl">
        <div class="flex items-center justify-center mb-8">
          <div
              class="w-16 h-16 bg-gradient-to-r from-purple-600 to-blue-600 rounded-2xl flex items-center justify-center shadow-lg mr-4">
            <i class="fas fa-lock-open text-white text-2xl"></i>
          </div>
          <h1 class="text-3xl font-bold text-gray-800">欢迎登录</h1>
        </div>

        <form @submit.prevent="handleSubmit" class="space-y-6">
          <!-- 账号输入框 -->
          <div class="relative group">
            <div
                class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none text-gray-400 group-focus-within:text-purple-600 transition-colors">
              <i class="fas fa-user text-lg"></i>
            </div>
            <input v-model="loginData.username" type="text"
                   class="w-full pl-12 pr-4 py-3 border border-gray-200 rounded-xl focus:border-purple-500 focus:ring-2 focus:ring-purple-500/30 transition-all duration-300 bg-white/90 hover:bg-white"
                   placeholder="请输入账号"/>
          </div>

          <!-- 密码输入框 -->
          <div class="relative group">
            <div
                class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none text-gray-400 group-focus-within:text-purple-600 transition-colors">
              <i class="fas fa-lock text-lg"></i>
            </div>
            <input v-model="loginData.password" :type="showPassword ? 'text' : 'password'"
                   class="w-full pl-12 pr-12 py-3 border border-gray-200 rounded-xl focus:border-purple-500 focus:ring-2 focus:ring-purple-500/30 transition-all duration-300 bg-white/90 hover:bg-white"
                   placeholder="请输入密码"/>
            <button type="button" @click="togglePassword"
                    class="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-purple-600 transition-colors">
              <i :class="showPassword ? 'fas fa-eye-slash' : 'fas fa-eye'"></i>
            </button>
          </div>

          <!-- 验证码 -->
          <div class="flex space-x-4">
            <div class="relative group flex-grow">
              <div
                  class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none text-gray-400 group-focus-within:text-purple-600 transition-colors">
                <i class="fas fa-shield-alt text-lg"></i>
              </div>
              <input v-model="formData.captcha" type="text"
                     class="w-full pl-12 pr-4 py-3 border border-gray-200 rounded-xl focus:border-purple-500 focus:ring-2 focus:ring-purple-500/30 transition-all duration-300 bg-white/90 hover:bg-white"
                     placeholder="请输入验证码"/>
            </div>
            <div @click="refreshCaptcha"
                 class="w-32 h-12 bg-gradient-to-r from-purple-100 to-blue-100 rounded-xl overflow-hidden cursor-pointer flex items-center justify-center font-mono text-xl font-bold text-purple-700 select-none shadow-inner hover:shadow transition-all"
                 style="letter-spacing: 4px;">
              {{ captchaCode }}
            </div>
          </div>

          <!-- 角色选择 -->
          <div class="space-y-3">
            <div class="text-gray-600 mb-2 font-medium">请选择登录角色</div>
            <div class="grid grid-cols-2 gap-3">
              <span v-for="item in menus" v-bind:key="item.roleName">
                <label class="flex items-center space-x-2 cursor-pointer" v-if="item.roleName != '游客' && item.roleName != '用户'">
                  <div class="relative">
                    <input type="radio" :value="item.roleName" v-model="loginData.role" class="sr-only peer"/>
                    <div
                        class="w-5 h-5 rounded-full border-2 border-gray-300 peer-checked:border-purple-500 flex items-center justify-center transition-colors">
                      <div
                          class="w-2.5 h-2.5 rounded-full bg-purple-500 scale-0 peer-checked:scale-100 transition-transform">
                      </div>
                    </div>
                  </div>
                  <span class="text-gray-700">{{ item.roleName }}</span>
                </label>
              </span>
            </div>
          </div>

          <!-- 登录按钮 -->
          <button type="submit"
                  class="!rounded-button w-full py-4 bg-gradient-to-r from-purple-600 to-blue-600 text-white font-medium hover:from-purple-700 hover:to-blue-700 focus:outline-none focus:ring-4 focus:ring-purple-500/20 shadow-lg hover:shadow-purple-500/30 transition-all duration-300 flex items-center justify-center"
                  :disabled="loading">
            <span v-if="!loading" class="flex items-center">
              <i class="fas fa-sign-in-alt mr-2"></i> 登录系统
            </span>
            <span v-else class="flex items-center">
              <i class="fas fa-circle-notch fa-spin mr-2"></i> 登录中...
            </span>
          </button>
        </form>

        <!-- 底部导航 -->
        <div class="mt-8 pt-6 border-t border-gray-100">
          <div class="flex items-center justify-between text-sm">
            <a href="#" class="text-gray-500 hover:text-purple-600 transition-colors">
              <i class="fas fa-question-circle mr-1"></i> 忘记密码?
            </a>
                             
                              <a @click="register('shangjia')"
                   class="text-purple-600 hover:text-purple-700 whitespace-nowrap font-medium transition-colors">
                  <i class="fas fa-user-plus mr-1"></i>注册商家
                </a>
              

          </div>
        </div>
      </div>
    </div>
  </div>
  <Register ref="RegisterRef"></Register>
</template>

<script lang="ts" setup>
  import {ref, reactive, toRefs, defineAsyncComponent, onMounted} from 'vue';
  import {isUsername, isPassword} from '@/utils/validate';
  import {useAuthStore} from '@/stores/auth';
  import {useRouter, useRoute} from 'vue-router';
  import {Session} from '@/utils/storage';
  import type {FormRules} from 'element-plus/es/components/form/src/types';
  import menu from "@/utils/menu";

  const Register = defineAsyncComponent(() => import('@/views/login/register.vue'));
  const authStore = useAuthStore();

  const router = useRouter();
  const route = useRoute();

  const formRef = ref();

  const state = reactive({
    loading: false,
    registerloading: false,
    loginData: {
      username: '',
      password: '',
      role: ''
    } as LoginData,
    tableName: ''
  });

  const menus = menu.list();

  const {loading, loginData, tableName} = {...toRefs(state)};
  const RegisterRef = ref();

  function register(tableName: any) {
    RegisterRef.value.open(tableName);
  }

  import {notify, confirm} from '@/utils/element';

  const showPassword = ref(false);

  const formData = reactive({
    captcha: ''
  });

  const captchaCode = ref('');

  const generateCaptcha = () => {
    const chars = 'ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789';
    let captcha = '';
    for (let i = 0; i < 4; i++) {
      captcha += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return captcha;
  };

  const refreshCaptcha = () => {
    captchaCode.value = generateCaptcha();
  };

  const togglePassword = () => {
    showPassword.value = !showPassword.value;
  };

  onMounted(() => {
    refreshCaptcha();
  });

  const handleSubmit = async () => {
    state.loading = true;
    try {
      if (!state.loginData.username || !state.loginData.password) {
        notify("账号密码不能为空", {type: "error"})
        return;
      }

      if (captchaCode.value.toUpperCase() !== formData.captcha.toUpperCase()) {
        notify("验证码错误", {type: "error"})
        refreshCaptcha();
        return;
      }

      for (let i = 0; i < menus.length; i++) {
        if (menus[i].roleName == state.loginData.role) {
          state.tableName = menus[i].tableName;
        }
      }

      const res = await authStore.userLogin(state.loginData, `${state.tableName}/login`);
      Session.set("tableName", state.tableName);
      Session.set("role", state.loginData.role);
      Session.set("adminName", state.loginData.username);
      router.replace({path: <string>route.query?.redirect || '/'});

    } finally {
      state.loading = false;
    }
  };
</script>

<style scoped>
  @keyframes float {
    0%, 100% {
      transform: translateY(0);
    }
    50% {
      transform: translateY(-15px);
    }
  }

  .animate-float {
    animation: float 6s ease-in-out infinite;
  }

  .login-form-enter-active {
    transition: all 0.3s ease-out;
  }

  .login-form-leave-active {
    transition: all 0.3s ease-in;
  }

  .login-form-enter-from,
  .login-form-leave-to {
    opacity: 0;
    transform: translateY(-20px);
  }

  input[type="number"]::-webkit-inner-spin-button,
  input[type="number"]::-webkit-outer-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }

  /* 自定义radio样式 */
  input[type="radio"]:checked + div > div {
    transform: scale(1);
  }
</style>