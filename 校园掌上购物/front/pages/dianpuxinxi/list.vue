<template>
    <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
        <!-- 搜索栏 -->
        <view class="cu-bar search-wrapper" :style="[{top:CustomBar + 'px'}]">
            <view class="search-container">
                <!-- 搜索类型选择 -->
                <view class="search-header">
                    <picker v-if="queryList.length>1" mode="selector" :range="queryList" range-key="queryName"
                            :value="queryIndex" @change="queryChange" class="search-picker">
                        <view class="picker-content">
                            <text class="cuIcon-sort picker-icon"></text>
                            <text class="picker-label">{{queryList[queryIndex].queryName}}</text>
                            <text class="cuIcon-unfold picker-arrow"></text>
                        </view>
                    </picker>
                </view>
                
                <!-- 搜索输入区域 -->
                <view class="search-body">
                    <view class="search-input-group">
                        <text class="cuIcon-search search-icon"></text>
                        <input 
                            v-if="queryIndex==0" 
                            v-model="searchForm.dianpumingcheng" 
                            type="text" 
                            placeholder="请输入店铺名称" 
                            class="search-input"
                            @confirm="search"
                        ></input>
                        <input 
                            v-if="queryIndex==1" 
                            v-model="searchForm.nicheng" 
                            type="text" 
                            placeholder="请输入昵称" 
                            class="search-input"
                            @confirm="search"
                        ></input>
                        <!-- 清空按钮 -->
                        <text 
                            v-if="(queryIndex==0 && searchForm.dianpumingcheng) || (queryIndex==1 && searchForm.nicheng)" 
                            class="cuIcon-roundclosefill clear-icon" 
                            @tap="clearSearch"
                        ></text>
                    </view>
                    
                    <!-- 搜索按钮 -->
                    <view class="search-actions">
                        <button @tap="search" class="search-btn">
                            <text class="btn-text">搜索</text>
                            <text class="cuIcon-search btn-icon"></text>
                        </button>
                    </view>
                </view>
            </view>
        </view>
        
        <!-- 商品列表 -->
        <view class="product-wrapper">
            <view class="product-grid">
                <view @tap="onDetailTap(product)" class="product-card" v-for="(product,index) in list" :key="index">
                    <!-- 图片区域 -->
                    <view class="card-image-container">
                        <image class="card-image" mode="aspectFill" 
                               :src="product.tupian?product.tupian.split(',')[0]:''"
                               :style="{'background-color': getRandomColor(index)}"></image>
                        <view class="image-overlay"></view>
                        <!-- 角标 -->
                        <view class="card-badge">
                            <text class="badge-text">{{index + 1}}</text>
                        </view>
                    </view>
                    
                    <!-- 内容区域 -->
                    <view class="card-content">
                        <!-- 店铺名称 -->
                        <view class="product-title" v-if="product.dianpumingcheng">
                            <text class="cuIcon-shop title-icon"></text>
                            <text class="title-text">{{product.dianpumingcheng}}</text>
                        </view>
                        
                        <!-- 昵称 -->
                        <view class="product-subtitle" v-if="product.nicheng">
                            <text class="cuIcon-people subtitle-icon"></text>
                            <text class="subtitle-text">{{product.nicheng}}</text>
                        </view>
                        
                        <!-- 操作区域 -->
                        <view class="card-actions">
                            <view class="action-btn" @tap.stop="onDetailTap(product)">
                                <text class="cuIcon-right action-icon"></text>
                                <text class="action-text">查看详情</text>
                            </view>
                        </view>
                    </view>
                </view>
            </view>
            
            <!-- 空状态 -->
            <view v-if="list.length === 0 && !hasNext" class="empty-state">
                <view class="empty-icon">
                    <text class="cuIcon-shop"></text>
                </view>
                <text class="empty-text">暂无店铺信息</text>
                <text class="empty-subtext">快去添加一些店铺吧</text>
            </view>
            
            <!-- 加载状态 -->
            <view v-if="list.length > 0" class="loading-wrapper">
                <text class="loading-text" v-if="hasNext">
                    <text class="cuIcon-loading1 loading-icon"></text>
                    加载中...
                </text>
                <text class="no-more-text" v-else>
                    <text class="cuIcon-roundcheckfill check-icon"></text>
                    已经到底啦~
                </text>
            </view>
        </view>
    </mescroll-uni>
</template>

<script>
    export default {
        data() {
            return {
                btnColor: ['#409eff', '#67c23a', '#909399', '#e6a23c', '#f56c6c', '#356c6c', '#351c6c', '#f093a9', '#a7c23a', '#104eff', '#10441f', '#a21233', '#503319'],
                queryList: [
                    {
                        queryName: "店铺名称",
                    },
                    {
                        queryName: "昵称",
                    },
                ],
                sactiveItem: {
                    "padding": "0 28rpx",
                    "boxShadow": "0 0 12rpx rgba(0,0,0,.3)",
                    "margin": "0 12rpx",
                    "borderColor": "rgba(0,0,0,1)",
                    "backgroundColor": "rgba(34, 115, 191, 1)",
                    "color": "#fff",
                    "borderRadius": "8rpx",
                    "borderWidth": "0",
                    "width": "auto",
                    "lineHeight": "68rpx",
                    "fontSize": "28rpx",
                    "borderStyle": "solid"
                },
                sitem: {
                    "padding": "0 20rpx",
                    "boxShadow": "0 0 12rpx rgba(0,0,0,.3)",
                    "margin": "0 12rpx",
                    "borderColor": "rgba(0,0,0,1)",
                    "backgroundColor": "#fff",
                    "color": "#333",
                    "borderRadius": "8rpx",
                    "borderWidth": "0",
                    "width": "auto",
                    "lineHeight": "68rpx",
                    "fontSize": "28rpx",
                    "borderStyle": "solid"
                },
                queryIndex: 0,
                list: [],
                mescroll: null,
                downOption: {
                    auto: false
                },
                upOption: {
                    noMoreSize: 5,
                    textNoMore: '~ 没有更多了 ~',
                },
                hasNext: true,
                searchForm: {},
                categoryList: [],
                CustomBar: '0'
            };
        },
        async onShow() {
            this.hasNext = true
            if (this.mescroll) this.mescroll.resetUpScroll()
        },
        onLoad() {
            this.hasNext = true
            if (this.mescroll) this.mescroll.resetUpScroll()
        },
        methods: {
            //查询条件切换
            queryChange(e) {
                this.queryIndex = e.detail.value;
                this.searchForm.dianpumingcheng = "";
                this.searchForm.nicheng = "";
            },
            // 清空搜索
            clearSearch() {
                if (this.queryIndex == 0) {
                    this.searchForm.dianpumingcheng = "";
                } else if (this.queryIndex == 1) {
                    this.searchForm.nicheng = "";
                }
            },
            // 随机背景色
            getRandomColor(index) {
                const colors = [
                    'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
                    'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)',
                    'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)',
                    'linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)',
                    'linear-gradient(135deg, #fa709a 0%, #fee140 100%)',
                    'linear-gradient(135deg, #30cfd0 0%, #330867 100%)',
                    'linear-gradient(135deg, #a8edea 0%, #fed6e3 100%)',
                    'linear-gradient(135deg, #5ee7df 0%, #b490ca 100%)'
                ];
                return colors[index % colors.length];
            },
            // mescroll组件初始化的回调,可获取到mescroll对象
            mescrollInit(mescroll) {
                this.mescroll = mescroll;
            },
            /*下拉刷新的回调 */
            downCallback(mescroll) {
                this.hasNext = true
                mescroll.resetUpScroll()
            },
            /*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
            async upCallback(mescroll) {
                let params = {
                    page: mescroll.num,
                    limit: mescroll.size
                }
                let res = await this.$api.list(`dianpuxinxi`, params);
                if (mescroll.num == 1) this.list = [];
                this.list = this.list.concat(res.data.list);
                if (res.data.list.length == 0) this.hasNext = false;
                mescroll.endSuccess(mescroll.size, this.hasNext);
            },
            // 详情
            onDetailTap(item) {
                this.$utils.jump(`./detail?id=${item.id}`)
            },
            // 修改
            onUpdateTap(id) {
                this.$utils.jump(`./add-or-update?id=${id}`)
            },
            // 添加
            onAddTap() {
                this.$utils.jump(`./add-or-update`)
            },
            onDeleteTap(id) {
                var _this = this;
                uni.showModal({
                    title: '提示',
                    content: '是否确认删除',
                    success: async function (res) {
                        if (res.confirm) {
                            await _this.$api.del('dianpuxinxi', JSON.stringify([id]));
                            _this.hasNext = true
                            _this.mescroll.resetUpScroll()
                        }
                    }
                });
            },
            // 搜索
            async search() {
                this.mescroll.num = 1
                let searchForm = {
                    page: this.mescroll.num,
                    limit: this.mescroll.size
                }
                if (this.searchForm.dianpumingcheng) {
                    searchForm['dianpumingcheng'] = '%' + this.searchForm.dianpumingcheng + '%'
                }
                if (this.searchForm.nicheng) {
                    searchForm['nicheng'] = '%' + this.searchForm.nicheng + '%'
                }
                let res = await this.$api.list(`dianpuxinxi`, searchForm);
                if (this.mescroll.num == 1) this.list = [];
                this.list = this.list.concat(res.data.list);
                if (res.data.list.length == 0) this.hasNext = false;
                this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
            }
        }
    };
</script>

<style lang="scss">
    page {
        background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
        min-height: 100vh;
        
        &::before {
            content: '';
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: radial-gradient(circle at 20% 50%, rgba(102, 126, 234, 0.05) 0%, transparent 50%),
                        radial-gradient(circle at 80% 80%, rgba(74, 108, 247, 0.03) 0%, transparent 50%);
            pointer-events: none;
            z-index: 0;
        }
    }
    
    .search-wrapper {
        background: rgba(255, 255, 255, 0.95);
        backdrop-filter: blur(20px);
        border-bottom: 1px solid rgba(255, 255, 255, 0.3);
        box-shadow: 0 4rpx 20rpx rgba(0, 0, 0, 0.06);
        padding: 24rpx 30rpx;
        position: sticky;
        top: 0;
        z-index: 100;
        transition: all 0.3s ease;
        
        &:hover {
            box-shadow: 0 8rpx 32rpx rgba(0, 0, 0, 0.08);
        }
    }
    
    .search-container {
        width: 100%;
    }
    
    .search-header {
        margin-bottom: 24rpx;
    }
    
    .search-picker {
        .picker-content {
            display: flex;
            align-items: center;
            gap: 12rpx;
            padding: 12rpx 24rpx;
            background: linear-gradient(135deg, rgba(255, 255, 255, 0.9) 0%, rgba(248, 249, 252, 0.9) 100%);
            border-radius: 16rpx;
            border: 1.5rpx solid rgba(74, 108, 247, 0.1);
            backdrop-filter: blur(10px);
            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
            
            &:active {
                transform: scale(0.98);
                border-color: rgba(74, 108, 247, 0.3);
                background: linear-gradient(135deg, rgba(255, 255, 255, 1) 0%, rgba(248, 249, 252, 1) 100%);
            }
        }
        
        .picker-icon {
            font-size: 28rpx;
            color: #4a6cf7;
        }
        
        .picker-label {
            font-size: 28rpx;
            font-weight: 600;
            color: #4a6cf7;
            text-shadow: 0 1rpx 2rpx rgba(0, 0, 0, 0.05);
        }
        
        .picker-arrow {
            font-size: 24rpx;
            color: #a0aec0;
            margin-left: auto;
        }
    }
    
    .search-body {
        display: flex;
        align-items: center;
        gap: 20rpx;
    }
    
    .search-input-group {
        flex: 1;
        display: flex;
        align-items: center;
        background: linear-gradient(135deg, rgba(255, 255, 255, 0.95) 0%, rgba(248, 249, 252, 0.95) 100%);
        border-radius: 20rpx;
        padding: 0 24rpx;
        height: 80rpx;
        border: 1.5rpx solid transparent;
        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
        box-shadow: 0 4rpx 16rpx rgba(0, 0, 0, 0.05);
        position: relative;
        
        &:focus-within {
            border-color: rgba(74, 108, 247, 0.3);
            box-shadow: 0 8rpx 32rpx rgba(74, 108, 247, 0.15);
            transform: translateY(-1rpx);
        }
    }
    
    .search-icon {
        font-size: 32rpx;
        color: #4a6cf7;
        margin-right: 16rpx;
        filter: drop-shadow(0 2rpx 4rpx rgba(74, 108, 247, 0.2));
    }
    
    .search-input {
        flex: 1;
        height: 100%;
        font-size: 28rpx;
        color: #2c3e50;
        background: transparent;
        
        &::placeholder {
            color: #a0aec0;
            font-weight: 400;
        }
    }
    
    .clear-icon {
        position: absolute;
        right: 24rpx;
        font-size: 28rpx;
        color: #a0aec0;
        padding: 8rpx;
        z-index: 2;
        
        &:active {
            color: #4a6cf7;
            transform: scale(0.9);
        }
    }
    
    .search-btn {
        background: linear-gradient(135deg, #4a6cf7 0%, #6c8cff 100%) !important;
        color: #fff !important;
        border: none;
        border-radius: 20rpx;
        height: 80rpx;
        padding: 0 36rpx;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 12rpx;
        font-size: 28rpx;
        font-weight: 600;
        box-shadow: 0 6rpx 24rpx rgba(74, 108, 247, 0.3);
        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
        position: relative;
        overflow: hidden;
        
        &::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
            transition: 0.5s;
        }
        
        &:active {
            transform: scale(0.96);
            box-shadow: 0 12rpx 32rpx rgba(74, 108, 247, 0.4);
            
            &::before {
                left: 100%;
            }
        }
        
        .btn-text {
            font-weight: 600;
            letter-spacing: 0.5rpx;
        }
        
        .btn-icon {
            font-size: 24rpx;
            opacity: 0.9;
        }
    }
    
    .product-wrapper {
        padding: 30rpx;
        min-height: 70vh;
    }
    
    .product-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 24rpx;
        animation: fadeIn 0.5s ease-out;
    }
    
    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translateY(20rpx);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
    
    .product-card {
        background: rgba(255, 255, 255, 0.95);
        border-radius: 24rpx;
        overflow: hidden;
        box-shadow: 0 8rpx 32rpx rgba(0, 0, 0, 0.08);
        transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
        border: 1rpx solid rgba(255, 255, 255, 0.2);
        backdrop-filter: blur(10px);
        
        &:active {
            transform: translateY(-8rpx) scale(0.98);
            box-shadow: 0 16rpx 48rpx rgba(74, 108, 247, 0.15);
            border-color: rgba(74, 108, 247, 0.1);
        }
    }
    
    .card-image-container {
        position: relative;
        height: 280rpx;
        overflow: hidden;
    }
    
    .card-image {
        width: 100%;
        height: 100%;
        object-fit: cover;
        transition: transform 0.6s cubic-bezier(0.4, 0, 0.2, 1);
    }
    
    .product-card:active .card-image {
        transform: scale(1.1);
    }
    
    .image-overlay {
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        height: 60rpx;
        background: linear-gradient(to top, rgba(0, 0, 0, 0.4), transparent);
    }
    
    .card-badge {
        position: absolute;
        top: 20rpx;
        left: 20rpx;
        background: linear-gradient(135deg, #ff6b6b 0%, #ff8e53 100%);
        color: white;
        width: 40rpx;
        height: 40rpx;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 22rpx;
        font-weight: 600;
        box-shadow: 0 4rpx 12rpx rgba(255, 107, 107, 0.3);
        z-index: 2;
    }
    
    .card-content {
        padding: 24rpx;
    }
    
    .product-title {
        display: flex;
        align-items: center;
        gap: 12rpx;
        margin-bottom: 16rpx;
        
        .title-icon {
            font-size: 28rpx;
            color: #4a6cf7;
            flex-shrink: 0;
        }
        
        .title-text {
            font-size: 30rpx;
            font-weight: 700;
            color: #2c3e50;
            line-height: 1.4;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    }
    
    .product-subtitle {
        display: flex;
        align-items: center;
        gap: 12rpx;
        margin-bottom: 20rpx;
        
        .subtitle-icon {
            font-size: 24rpx;
            color: #a0aec0;
            flex-shrink: 0;
        }
        
        .subtitle-text {
            font-size: 26rpx;
            color: #718096;
            line-height: 1.4;
            display: -webkit-box;
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    }
    
    .card-actions {
        margin-top: 20rpx;
        padding-top: 20rpx;
        border-top: 1rpx solid rgba(226, 232, 240, 0.8);
        
        .action-btn {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8rpx;
            padding: 12rpx;
            background: linear-gradient(135deg, rgba(74, 108, 247, 0.1) 0%, rgba(74, 108, 247, 0.05) 100%);
            border-radius: 12rpx;
            border: 1rpx solid rgba(74, 108, 247, 0.1);
            transition: all 0.3s ease;
            
            &:active {
                background: linear-gradient(135deg, rgba(74, 108, 247, 0.2) 0%, rgba(74, 108, 247, 0.1) 100%);
                transform: scale(0.98);
            }
            
            .action-icon {
                font-size: 24rpx;
                color: #4a6cf7;
            }
            
            .action-text {
                font-size: 24rpx;
                color: #4a6cf7;
                font-weight: 500;
            }
        }
    }
    
    .empty-state {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        padding: 120rpx 0;
        text-align: center;
    }
    
    .empty-icon {
        width: 120rpx;
        height: 120rpx;
        background: linear-gradient(135deg, rgba(74, 108, 247, 0.1) 0%, rgba(74, 108, 247, 0.05) 100%);
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 32rpx;
        
        .cuIcon-shop {
            font-size: 56rpx;
            color: #4a6cf7;
            opacity: 0.7;
        }
    }
    
    .empty-text {
        font-size: 32rpx;
        font-weight: 600;
        color: #2c3e50;
        margin-bottom: 12rpx;
    }
    
    .empty-subtext {
        font-size: 26rpx;
        color: #95a5a6;
    }
    
    .loading-wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 40rpx 0;
        position: relative;
        
        &::before {
            content: '';
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 120rpx;
            height: 1px;
            background: linear-gradient(90deg, transparent, #4a6cf7, transparent);
        }
    }
    
    .loading-text {
        font-size: 26rpx;
        color: #4a6cf7;
        display: flex;
        align-items: center;
        gap: 8rpx;
        
        .loading-icon {
            font-size: 24rpx;
            animation: rotate 1s linear infinite;
        }
    }
    
    .no-more-text {
        font-size: 26rpx;
        color: #bdc3c7;
        letter-spacing: 1rpx;
        display: flex;
        align-items: center;
        gap: 8rpx;
        
        .check-icon {
            font-size: 24rpx;
            color: #4caf50;
        }
    }
    
    @keyframes rotate {
        from {
            transform: rotate(0deg);
        }
        to {
            transform: rotate(360deg);
        }
    }
    
    @keyframes pulse {
        0%, 100% {
            opacity: 0.5;
        }
        50% {
            opacity: 1;
        }
    }
    
    // 响应式调整
    @media (max-width: 750rpx) {
        .product-grid {
            grid-template-columns: 1fr;
            gap: 20rpx;
        }
        
        .search-body {
            flex-direction: column;
            gap: 20rpx;
        }
        
        .search-input-group,
        .search-btn {
            width: 100%;
        }
        
        .search-btn {
            padding: 0 24rpx;
        }
        
        .card-image-container {
            height: 240rpx;
        }
    }
</style>