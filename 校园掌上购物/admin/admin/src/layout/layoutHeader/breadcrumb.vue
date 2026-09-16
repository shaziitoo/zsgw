<!-- layoutHeader/breadcrumb.vue -->
<script setup lang="ts">
import { useLayoutConfigStore } from '@/stores/layoutConfig';
import { useRoute, onBeforeRouteUpdate, useRouter } from 'vue-router';
import { onMounted, ref } from 'vue';
import type { RouteLocationNormalized, RouteRecordNormalized } from 'vue-router'

const route = useRoute();
const router = useRouter();
const layoutConfig = useLayoutConfigStore();
const breadcrumdList = ref<RouteRecordNormalized[]>([]);

onMounted(() => {
  getBreadcrumb(route);
});

onBeforeRouteUpdate((to) => {
  getBreadcrumb(to)
})

function getBreadcrumb(to: RouteLocationNormalized) {
  const matched = to.matched.filter(item => item.meta && item.meta.title && item.meta.isBreadcrumd !== false);
  breadcrumdList.value = matched;
}

function handleLink(_route: RouteRecordNormalized) {
  const { redirect, path } = _route;
  if (redirect) router.push(<string>redirect);
  else router.push(path);
}

function handleChangeCollapse() {
  layoutConfig.isCollapse = !layoutConfig.isCollapse
}
</script>

<template>
  <div class="breadcrumb-container">
    <!-- 菜单展开/收起按钮 -->
    <button 
      @click="handleChangeCollapse"
      class="collapse-btn"
    >
      <SvgIcon 
        :name="layoutConfig.isCollapse ? 'ele-Expand' : 'ele-Fold'" 
        class="collapse-icon"
      />
    </button>
    
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" class="breadcrumb-nav">
      <el-breadcrumb-item
        v-for="(item, index) in breadcrumdList" 
        :key="item.path"
      >
        <span 
          v-if="index === breadcrumdList.length-1" 
          class="breadcrumb-item current"
        >
          <SvgIcon v-if="item.meta.icon" :name="item.meta.icon" :size="14" />
          {{ item.meta.title }}
        </span>
        <a 
          v-else 
          @click.prevent="handleLink(item)" 
          class="breadcrumb-item link"
        >
          <SvgIcon v-if="item.meta.icon" :name="item.meta.icon" :size="14" />
          {{ item.meta.title }}
        </a>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<style scoped lang="scss">
.breadcrumb-container {
  @apply flex items-center;
  
  .collapse-btn {
    @apply mr-4 p-2 rounded-full hover:bg-purple-600/30 transition-colors;
    
    .collapse-icon {
      @apply text-white text-lg;
    }
  }
  
  .breadcrumb-nav {
    @apply text-sm;
    
    :deep(.el-breadcrumb__separator) {
      @apply text-purple-300;
    }
    
    .breadcrumb-item {
      @apply flex items-center text-white/90 transition-colors;
      
      &.current {
        @apply text-white font-medium;
      }
      
      &.link {
        @apply hover:text-white;
      }
    }
  }
}
</style>