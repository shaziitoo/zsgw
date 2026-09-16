<!-- layoutHeader/index.vue -->
<script setup lang="ts" name="LayoutHeader">
import { defineAsyncComponent } from 'vue';
const Breadcrumb = defineAsyncComponent(() => import('@/layout/layoutHeader/breadcrumb.vue'))
const UserDropdown = defineAsyncComponent(() => import('@/layout/layoutHeader/userDropdown.vue'))

const props = defineProps<{
  id: number,
  img: string,
  uname: string
}>();
</script>

<template>
  <header class="layout-header">
    <div class="header-container">
      <!-- 面包屑导航 -->
      <Breadcrumb />
      
      <!-- 右侧用户功能区 -->
      <UserDropdown 
        :img="props.img" 
        :uname="props.uname" 
        :id="props.id"
      />
    </div>
  </header>
</template>

<style scoped lang="scss">
.layout-header {
  @apply fixed top-0 left-0 right-0 h-16 bg-gradient-to-r from-purple-700 to-indigo-700 shadow-lg z-50;
  
  .header-container {
    @apply h-full flex items-center justify-between px-6;
  }
}
</style>