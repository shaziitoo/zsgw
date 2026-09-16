<script setup lang="ts" name="Home">
    // 这里可以保留空的setup脚本，以便未来添加功能
</script>

<template>
  <div class="h-screen bg-gradient-to-br from-purple-50 to-indigo-50 flex flex-col overflow-hidden">
    <!-- 主内容区（调整高度并添加滚动） -->
    <main class="flex-1 p-6 overflow-auto">
      <!-- 顶部欢迎区域 -->
      <div class="max-w-7xl mx-auto bg-white rounded-2xl shadow-lg overflow-hidden mb-8">
        <div class="p-8 md:p-12 flex flex-col md:flex-row items-center">
          <div class="md:w-1/2 mb-6 md:mb-0">
            <h1 class="text-4xl font-bold text-gray-800 mb-4">
              欢迎来到<span class="bg-gradient-to-r from-purple-600 to-indigo-600 bg-clip-text text-transparent">校园掌上购物</span>管理平台
            </h1>
            <p class="text-lg text-gray-600 mb-6">
              专为校园商城打造的智能管理系统，帮助您轻松管理商品、订单和用户，为校园师生提供优质的购物体验。
            </p>
            <div class="flex space-x-4">
              <button class="px-6 py-3 bg-gradient-to-r from-purple-600 to-indigo-600 text-white rounded-lg shadow-md hover:shadow-lg transition-all">
                开始探索
              </button>
              <button class="px-6 py-3 border border-purple-600 text-purple-600 rounded-lg hover:bg-purple-50 transition-all">
                了解更多
              </button>
            </div>
          </div>
          <div class="md:w-1/2 flex justify-center">
            <img 
              src="https://ai-public.mastergo.com/ai/img_res/a1cce3231e0913cd302f6ae49d3584ee.jpg" 
              alt="系统展示" 
              class="rounded-xl shadow-md w-full max-w-md object-cover"
            >
          </div>
        </div>
      </div>

      <!-- 功能卡片区域 -->
      <div class="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <div class="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-all border-l-4 border-purple-500">
          <div class="text-purple-600 mb-4">
            <i class="fas fa-shopping-cart text-3xl"></i>
          </div>
          <h3 class="text-xl font-semibold text-gray-800 mb-2">订单管理</h3>
          <p class="text-gray-600">实时跟踪和管理校园购物订单</p>
        </div>

        <div class="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-all border-l-4 border-indigo-500">
          <div class="text-indigo-600 mb-4">
            <i class="fas fa-store text-3xl"></i>
          </div>
          <h3 class="text-xl font-semibold text-gray-800 mb-2">商家管理</h3>
          <p class="text-gray-600">管理校园商家和店铺信息</p>
        </div>

        <div class="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-all border-l-4 border-purple-500">
          <div class="text-purple-600 mb-4">
            <i class="fas fa-boxes text-3xl"></i>
          </div>
          <h3 class="text-xl font-semibold text-gray-800 mb-2">商品管理</h3>
          <p class="text-gray-600">维护校园商城商品库存和分类</p>
        </div>

        <div class="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-all border-l-4 border-indigo-500">
          <div class="text-indigo-600 mb-4">
            <i class="fas fa-users text-3xl"></i>
          </div>
          <h3 class="text-xl font-semibold text-gray-800 mb-2">用户管理</h3>
          <p class="text-gray-600">管理校园师生用户账户和权限</p>
        </div>
      </div>
    </main>

    <!-- 底部信息区域（固定高度） -->
    <footer class="bg-white py-6 border-t border-gray-100">
      <div class="max-w-7xl mx-auto px-6">
        <h2 class="text-2xl font-bold text-gray-800 mb-6 text-center">平台优势</h2>
        <div class="grid md:grid-cols-3 gap-6">
          <div class="text-center">
            <div class="w-14 h-14 bg-purple-100 rounded-full flex items-center justify-center mx-auto mb-3">
              <i class="fas fa-rocket text-purple-600 text-xl"></i>
            </div>
            <h3 class="text-lg font-semibold text-gray-800 mb-1">快速便捷</h3>
            <p class="text-gray-600 text-sm">为校园师生提供快速便捷的购物体验</p>
          </div>

          <div class="text-center">
            <div class="w-14 h-14 bg-indigo-100 rounded-full flex items-center justify-center mx-auto mb-3">
              <i class="fas fa-lock text-indigo-600 text-xl"></i>
            </div>
            <h3 class="text-lg font-semibold text-gray-800 mb-1">安全保障</h3>
            <p class="text-gray-600 text-sm">完善的权限管理和数据保护机制</p>
          </div>

          <div class="text-center">
            <div class="w-14 h-14 bg-purple-100 rounded-full flex items-center justify-center mx-auto mb-3">
              <i class="fas fa-chart-bar text-purple-600 text-xl"></i>
            </div>
            <h3 class="text-lg font-semibold text-gray-800 mb-1">智能管理</h3>
            <p class="text-gray-600 text-sm">全面的数据分析和智能决策支持</p>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>

<style scoped>
/* 精确控制视口高度 */
.h-screen {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

/* 主内容区自动填充剩余空间并允许滚动 */
.flex-1 {
  flex: 1 1 auto;
  overflow-y: auto;
}

/* 卡片悬停效果 */
.card-hover {
  transition: all 0.3s ease;
}
.card-hover:hover {
  transform: translateY(-3px);
}

/* 动画效果 */
@keyframes fadeIn {
  from { opacity: 0; transform: translateY(10px); }
  to { opacity: 1; transform: translateY(0); }
}

.fade-in {
  animation: fadeIn 0.4s ease-out forwards;
}

/* 优化图片显示 */
img {
  max-height: 280px;
  object-fit: contain;
}
</style>