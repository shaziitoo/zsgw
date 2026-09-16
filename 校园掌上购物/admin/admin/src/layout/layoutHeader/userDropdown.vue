<!-- layoutHeader/userDropdown.vue -->
<script setup lang="ts">
import { Key } from '@/stores/auth';
import { Session } from '@/utils/storage';
import { useFullscreen, useDark } from '@vueuse/core'
import { useLayoutConfigStore } from '@/stores/layoutConfig'
import { ref } from 'vue';
import { useRouter } from 'vue-router'
import { storeToRefs } from 'pinia';

const layoutConfig = useLayoutConfigStore();
const { globalTitle } = storeToRefs(layoutConfig)
const router = useRouter();

const { isFullscreen, toggle: toggleFullscreen } = useFullscreen();
const isDark = useDark({
  valueDark: 'auto',
  valueLight: '',
  initialValue: 'auto'
});

async function handleToggleFullscreen() {
  await toggleFullscreen();
}

function changeDark(val: boolean) {
  LayoutConfigStore.isDrak = val;
}

const props = defineProps<{
  id: number,
  img: string,
  uname: string
}>();

function tc() {
  Session.remove(Key.accessTokenKey);
  Session.remove(Key.userInfoKey);
  router.push("/login")
}

const showUserMenu = ref(false);
const toggleUserMenu = () => {
  showUserMenu.value = !showUserMenu.value;
};
</script>

<template>
  <div class="user-dropdown-container">
    <button 
      @click="handleToggleFullscreen"
      class="action-btn"
      :title="isFullscreen ? '退出全屏' : '全屏'"
    >
      <SvgIcon 
        :name="isFullscreen ? 'ele-CloseBold' : 'ele-FullScreen'" 
        class="action-icon"
      />
    </button>
    
    <div class="theme-toggle">
      <el-switch
        v-model="isDark"
        @change="changeDark"
        inline-prompt
        active-color="#7e22ce"
        inactive-color="#e9d5ff"
        active-icon="ele-Moon"
        inactive-icon="ele-Sunny"
      />
    </div>
    
    <div class="user-menu relative">
      <div 
        @click="toggleUserMenu"
        class="user-avatar"
      >
        <el-avatar 
          :size="32" 
          :src="props.img || 'https://ai-public.mastergo.com/ai/img_res/8a1e2c786bb03b4538d64309baaaa932.jpg'"
          class="cursor-pointer border border-gray-200"
        />
        <span class="username">{{ props.uname }}</span>
        <SvgIcon 
          name="ele-ArrowDown" 
          class="dropdown-icon"
          :class="{ 'rotate-180': showUserMenu }"
        />
      </div>
      
      <transition name="fade">
        <div 
          v-show="showUserMenu"
          class="dropdown-menu"
        >
          <a @click="router.push('/center')" class="menu-item">
            <SvgIcon name="ele-User" class="mr-2" />
            个人信息
          </a>
          <a @click="router.push('/updatePassword')" class="menu-item">
            <SvgIcon name="ele-Lock" class="mr-2" />
            修改密码
          </a>
          <a @click="tc" class="menu-item logout">
            <SvgIcon name="ele-SwitchButton" class="mr-2" />
            退出登录
          </a>
        </div>
      </transition>
    </div>
  </div>
</template>

<style scoped lang="scss">
.user-dropdown-container {
  @apply flex items-center space-x-4;
  
  .action-btn {
    @apply p-2 rounded-md hover:bg-gray-100 transition-colors;
    
    .action-icon {
      @apply text-gray-600 text-lg;
    }
  }
  
  .theme-toggle {
    :deep(.el-switch) {
      --el-switch-on-color: #7e22ce;
      --el-switch-off-color: #e9d5ff;
      
      .el-switch__core {
        @apply h-5;
      }
    }
  }
  
  .user-menu {
    .user-avatar {
      @apply flex items-center cursor-pointer space-x-2;
      
      .username {
        @apply text-gray-700 text-sm;
      }
      
      .dropdown-icon {
        @apply text-gray-500 text-xs transition-transform duration-200;
      }
      
      .rotate-180 {
        transform: rotate(180deg);
      }
    }
    
    .dropdown-menu {
      @apply absolute right-0 mt-2 w-40 bg-white rounded-md shadow-lg py-1 z-50 border border-gray-100;
      
      .menu-item {
        @apply flex items-center px-3 py-2 text-sm text-gray-600 hover:bg-purple-50 transition-colors;
        
        &.logout {
          @apply text-red-500 hover:bg-red-50;
        }
      }
    }
    
    .fade-enter-active,
    .fade-leave-active {
      transition: opacity 0.15s, transform 0.15s;
    }
    
    .fade-enter-from,
    .fade-leave-to {
      opacity: 0;
      transform: translateY(-5px);
    }
  }
}
</style>