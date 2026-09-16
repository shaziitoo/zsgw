<script setup lang="ts" name="LayoutVerticalMenu">
import { useRoute, useRouter } from 'vue-router'
import { useLayoutConfigStore } from '@/stores/layoutConfig'
import { toRaw } from "@vue/reactivity";
import { storeToRefs } from 'pinia';
import menu from '@/utils/menu'
import { Session } from '@/utils/storage';
import { reactive, toRefs, ref } from 'vue';

const route = useRoute();
const router = useRouter();
const layoutConfig = useLayoutConfigStore();
const { isCollapse } = storeToRefs(layoutConfig);

const props = defineProps<{
  type: number
}>();

const state = reactive({
  menuList: [],
  dynamicMenuRoutes: [],
  role: '',
  icon: [
    'ele-Calendar',
    'ele-Box',
    'ele-Money',
    'ele-Refrigerator',
    'ele-Cpu',
    'ele-Football',
    'ele-Brush',
    'ele-Suitcase',
    'ele-Monitor',
    'ele-BrushFilled',
    'ele-DataBoard',
    'ele-DataLine',
    'ele-Reading',
    'ele-FirstAidKit',
    'ele-ScaleToOriginal',
    'ele-ShoppingTrolley',
    'ele-Timer',
    'ele-Sunset',
    'ele-SwitchFilled',
    'ele-Dish',
    'ele-Dessert',
    'ele-Burger',
    'ele-GobletSquare',
    'ele-Cherry',
  ],
  activeMenu: route.path
})

const { menuList, dynamicMenuRoutes, role, icon, activeMenu } = { ...toRefs(state) };
const menus = menu.list()
state.menuList = toRaw(menus);
state.role = Session.get('role')

function menuHandler(name: any) {
  name = '/' + name
  state.activeMenu = name
  router.push(name)
}

const showUserMenu = ref(false);
const submenuOpen = ref({
  pets: true,
  appointments: false
});

const toggleSubmenu = (menu: string) => {
  submenuOpen.value[menu as keyof typeof submenuOpen.value] = !submenuOpen.value[menu as keyof typeof submenuOpen.value];
};
</script>

<template>
  <!-- 左侧菜单 -->
  <aside
    class="fixed left-0 top-0 bottom-0 w-56 bg-gradient-to-b from-purple-900 to-indigo-900 text-white shadow-xl z-50 overflow-y-auto transition-all duration-300">
    <!-- 菜单顶部logo区域 -->
    <div class="p-4 border-b border-purple-700/50 flex items-center justify-center">
      <div class="text-xl font-bold bg-gradient-to-r from-purple-400 to-indigo-400 bg-clip-text text-transparent">
        管理系统
      </div>
    </div>

    <!-- 菜单内容 -->
    <nav class="py-4" v-for="(item, index) in menuList" :key="item.roleName">
      <span v-if="role == item.roleName">
        <!-- 首页菜单项 -->
        <a @click="menuHandler('home')" :class="{ 'bg-purple-700/30': activeMenu === '/home' }"
          class="flex items-center px-6 py-3 hover:bg-purple-700/20 transition-all duration-200 group">
          <i class="fas fa-home mr-3 text-purple-300 group-hover:text-white"></i>
          <span class="group-hover:text-white">首页</span>
          <div class="ml-auto w-1 h-6 bg-purple-400 rounded-l-full transition-all duration-300"
            :class="{ 'opacity-100': activeMenu === '/home', 'opacity-0': activeMenu !== '/home' }"></div>
        </a>

        <!-- 个人信息菜单项 -->
        <a @click="menuHandler('center')" :class="{ 'bg-purple-700/30': activeMenu === '/center' }"
          class="flex items-center px-6 py-3 hover:bg-purple-700/20 transition-all duration-200 group">
          <i class="fas fa-user mr-3 text-purple-300 group-hover:text-white"></i>
          <span class="group-hover:text-white">个人信息</span>
          <div class="ml-auto w-1 h-6 bg-purple-400 rounded-l-full transition-all duration-300"
            :class="{ 'opacity-100': activeMenu === '/center', 'opacity-0': activeMenu !== '/center' }"></div>
        </a>

        <!-- 修改密码菜单项 -->
        <a @click="menuHandler('updatePassword')" :class="{ 'bg-purple-700/30': activeMenu === '/updatePassword' }"
          class="flex items-center px-6 py-3 hover:bg-purple-700/20 transition-all duration-200 group">
          <i class="fas fa-cog mr-3 text-purple-300 group-hover:text-white"></i>
          <span class="group-hover:text-white">修改密码</span>
          <div class="ml-auto w-1 h-6 bg-purple-400 rounded-l-full transition-all duration-300"
            :class="{ 'opacity-100': activeMenu === '/updatePassword', 'opacity-0': activeMenu !== '/updatePassword' }">
          </div>
        </a>

        <!-- 动态菜单项 -->
        <template v-for="(menu, index) in item.backMenu" :key="menu.menu">
          <div class="px-6 py-2 text-xs uppercase text-purple-400 tracking-wider mt-4">
            {{ menu.menu }}
          </div>
          <a v-for="(child, sort) in menu.child" :key="sort" @click="menuHandler(child.tableName)"
            :class="{ 'bg-purple-700/30': activeMenu === `/${child.tableName}` }"
            class="flex items-center px-6 py-3 hover:bg-purple-700/20 transition-all duration-200 group">
            <i class="mr-3 text-purple-300 group-hover:text-white">
              <SvgIcon :name="icon[index]" class="text-current" />
            </i>
            <span class="group-hover:text-white">{{ child.menu }}</span>
            <div class="ml-auto w-1 h-6 bg-purple-400 rounded-l-full transition-all duration-300"
              :class="{ 'opacity-100': activeMenu === `/${child.tableName}`, 'opacity-0': activeMenu !== `/${child.tableName}` }">
            </div>
          </a>
        </template>
      </span>
    </nav>

    <!-- 菜单底部 -->
    <div class="absolute bottom-0 left-0 right-0 p-4 border-t border-purple-700/50 text-center text-xs text-purple-400">
      v1.0.0
    </div>
  </aside>
</template>

<style scoped>
/* 滚动条样式 */
aside::-webkit-scrollbar {
  width: 6px;
}

aside::-webkit-scrollbar-track {
  background: rgba(255, 255, 255, 0.1);
}

aside::-webkit-scrollbar-thumb {
  background: rgba(255, 255, 255, 0.2);
  border-radius: 3px;
}

aside::-webkit-scrollbar-thumb:hover {
  background: rgba(255, 255, 255, 0.3);
}

/* 菜单项动画 */
.menu-item {
  transition: all 0.3s;
}

/* 数字输入框样式 */
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>