<template>
    <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
        <!-- 搜索栏 -->
        <view class="search-container" :style="[{top:CustomBar + 'px'}]">
            <view class="search-content">
                <view class="search-header">
                    <picker v-if="queryList.length>1" mode="selector" :range="queryList" range-key="queryName"
                            :value="queryIndex" @change="queryChange" class="search-picker">
                        <view class="picker-trigger">
                            <image class="picker-icon" src="../../static/center/to.png"></image>
                            <text class="picker-text">{{queryList[queryIndex].queryName}}</text>
                        </view>
                    </picker>
                </view>
                
                <view class="search-main">
                    <view v-if="queryIndex==0" class="search-input-wrapper">
                        <text class="search-icon cuIcon-search"></text>
                        <input v-model="searchForm.mingcheng" type="text" placeholder="输入商品名称搜索" class="search-input"></input>
                    </view>
                    
                    <button @tap="search" class="search-button">
                        <text class="button-text">搜索</text>
                        <view class="button-wave"></view>
                    </button>
                </view>
            </view>
        </view>
        
        <!-- 商品列表 -->
        <view class="product-container">
            <view class="product-grid">
                <view @tap="onDetailTap(product)" class="product-card" v-for="(product,index) in list" :key="index">
                    <view class="card-image-wrapper">
                        <image class="card-image" mode="aspectFill" 
                               :src="product.tupian?product.tupian.split(',')[0]:''"></image>
                        <view class="image-gradient"></view>
                        <view class="card-index">
                            <text class="index-text">#{{index + 1}}</text>
                        </view>
                    </view>
                    
                    <view class="card-content">
                        <view class="card-header">
                            <text class="product-name">{{product.mingcheng}}</text>
                        </view>
                        
                        <view class="card-meta">
                            <view class="meta-item" v-if="product.fenleimingcheng">
                                <text class="meta-icon cuIcon-tag"></text>
                                <text class="meta-text">{{product.fenleimingcheng}}</text>
                            </view>
                            <view class="meta-item" v-if="product.dianpumingcheng">
                                <text class="meta-icon cuIcon-shop"></text>
                                <text class="meta-text">{{product.dianpumingcheng}}</text>
                            </view>
                        </view>
                        
                        <view class="card-action">
                            <text class="action-text">查看详情</text>
                            <view class="action-arrow">
                                <text class="cuIcon-right"></text>
                            </view>
                        </view>
                    </view>
                </view>
            </view>
            
            <!-- 空状态 -->
            <view v-if="list.length === 0 && !hasNext" class="empty-container">
                <view class="empty-illustration">
                    <text class="cuIcon-pic"></text>
                </view>
                <text class="empty-title">暂无商品</text>
                <text class="empty-desc">尝试搜索其他关键词</text>
            </view>
            
            <!-- 加载状态 -->
            <view v-if="list.length > 0" class="load-status">
                <text class="load-text" v-if="hasNext">加载中...</text>
                <text class="load-text-end" v-else>已显示所有商品</text>
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
                    { queryName: "名称" },
                    { queryName: "分类名称" },
                    { queryName: "店铺名称" }
                ],
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
                CustomBar: '0'
            };
        },
        methods: {
            queryChange(e) {
                this.queryIndex = e.detail.value;
                this.searchForm.mingcheng = "";
                this.searchForm.fenleimingcheng = "";
                this.searchForm.dianpumingcheng = "";
            },
            mescrollInit(mescroll) {
                this.mescroll = mescroll;
            },
            async downCallback(mescroll) {
                this.hasNext = true
                mescroll.resetUpScroll()
            },
            async upCallback(mescroll) {
                let params = {
                    page: mescroll.num,
                    limit: mescroll.size
                }
                let res = await this.$api.list(`shangpinguanli`, params);
                if (mescroll.num == 1) this.list = [];
                this.list = this.list.concat(res.data.list);
                if (res.data.list.length == 0) this.hasNext = false;
                mescroll.endSuccess(mescroll.size, this.hasNext);
            },
            onDetailTap(item) {
                this.$utils.jump(`./detail?id=${item.id}`)
            },
            async search() {
                this.mescroll.num = 1
                let searchForm = {
                    page: this.mescroll.num,
                    limit: this.mescroll.size
                }
                if (this.searchForm.mingcheng) {
                    searchForm['mingcheng'] = '%' + this.searchForm.mingcheng + '%'
                }
                if (this.searchForm.fenleimingcheng) {
                    searchForm['fenleimingcheng'] = '%' + this.searchForm.fenleimingcheng + '%'
                }
                if (this.searchForm.dianpumingcheng) {
                    searchForm['dianpumingcheng'] = '%' + this.searchForm.dianpumingcheng + '%'
                }
                let res = await this.$api.list(`shangpinguanli`, searchForm);
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
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        min-height: 100vh;
        position: relative;
        
        &::before {
            content: '';
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: 
                radial-gradient(circle at 10% 20%, rgba(255, 255, 255, 0.1) 0%, transparent 30%),
                radial-gradient(circle at 90% 80%, rgba(255, 255, 255, 0.1) 0%, transparent 30%);
            pointer-events: none;
            z-index: 0;
        }
    }
    
    .search-container {
        background: rgba(255, 255, 255, 0.1);
        backdrop-filter: blur(20px);
        border-bottom: 1px solid rgba(255, 255, 255, 0.2);
        padding: 30rpx;
        position: sticky;
        top: 0;
        z-index: 100;
        box-shadow: 0 10rpx 30rpx rgba(0, 0, 0, 0.1);
    }
    
    .search-content {
        background: rgba(255, 255, 255, 0.95);
        border-radius: 20rpx;
        padding: 30rpx;
        box-shadow: 
            0 8rpx 32rpx rgba(0, 0, 0, 0.08),
            inset 0 1px 0 rgba(255, 255, 255, 0.6);
        animation: slideDown 0.5s ease;
    }
    
    @keyframes slideDown {
        from {
            opacity: 0;
            transform: translateY(-20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
    
    .search-header {
        margin-bottom: 25rpx;
    }
    
    .search-picker {
        .picker-trigger {
            display: inline-flex;
            align-items: center;
            padding: 15rpx 25rpx;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 50rpx;
            box-shadow: 0 6rpx 20rpx rgba(102, 126, 234, 0.4);
            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
            
            &:active {
                transform: scale(0.95);
                box-shadow: 0 4rpx 15rpx rgba(102, 126, 234, 0.3);
            }
        }
        
        .picker-icon {
            width: 24rpx;
            height: 24rpx;
            margin-right: 12rpx;
            filter: brightness(0) invert(1);
        }
        
        .picker-text {
            font-size: 28rpx;
            color: white;
            font-weight: 600;
            letter-spacing: 0.5rpx;
        }
    }
    
    .search-main {
        display: flex;
        align-items: center;
        gap: 20rpx;
    }
    
    .search-input-wrapper {
        flex: 1;
        display: flex;
        align-items: center;
        background: white;
        border-radius: 50rpx;
        padding: 0 25rpx;
        height: 80rpx;
        border: 2rpx solid transparent;
        transition: all 0.3s ease;
        box-shadow: 0 4rpx 20rpx rgba(0, 0, 0, 0.05);
        
        &:focus-within {
            border-color: #667eea;
            box-shadow: 0 6rpx 30rpx rgba(102, 126, 234, 0.15);
        }
    }
    
    .search-icon {
        font-size: 32rpx;
        color: #667eea;
        margin-right: 15rpx;
    }
    
    .search-input {
        flex: 1;
        height: 100%;
        font-size: 28rpx;
        color: #333;
        
        &::placeholder {
            color: #999;
        }
    }
    
    .search-button {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        border: none;
        border-radius: 50rpx;
        height: 80rpx;
        padding: 0 40rpx;
        font-size: 28rpx;
        font-weight: 600;
        box-shadow: 0 6rpx 25rpx rgba(102, 126, 234, 0.4);
        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
        position: relative;
        overflow: hidden;
        
        &:active {
            transform: scale(0.95);
            box-shadow: 0 4rpx 15rpx rgba(102, 126, 234, 0.3);
        }
    }
    
    .button-text {
        position: relative;
        z-index: 1;
    }
    
    .button-wave {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 0;
        height: 0;
        background: rgba(255, 255, 255, 0.3);
        border-radius: 50%;
        transition: all 0.6s ease;
    }
    
    .search-button:active .button-wave {
        width: 200rpx;
        height: 200rpx;
    }
    
    .product-container {
        padding: 30rpx;
        min-height: 70vh;
    }
    
    .product-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 30rpx;
    }
    
    .product-card {
        background: white;
        border-radius: 25rpx;
        overflow: hidden;
        box-shadow: 0 10rpx 40rpx rgba(0, 0, 0, 0.1);
        transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
        
        &:active {
            transform: translateY(-10rpx) scale(0.98);
            box-shadow: 0 20rpx 60rpx rgba(0, 0, 0, 0.15);
        }
    }
    
    .card-image-wrapper {
        position: relative;
        height: 300rpx;
        overflow: hidden;
    }
    
    .card-image {
        width: 100%;
        height: 100%;
        object-fit: cover;
        transition: transform 0.6s ease;
    }
    
    .product-card:active .card-image {
        transform: scale(1.1);
    }
    
    .image-gradient {
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        height: 100rpx;
        background: linear-gradient(to top, rgba(0, 0, 0, 0.4), transparent);
    }
    
    .card-index {
        position: absolute;
        top: 20rpx;
        left: 20rpx;
        background: rgba(255, 255, 255, 0.9);
        padding: 6rpx 15rpx;
        border-radius: 15rpx;
        backdrop-filter: blur(5px);
        
        .index-text {
            font-size: 24rpx;
            color: #667eea;
            font-weight: 600;
        }
    }
    
    .card-content {
        padding: 25rpx;
    }
    
    .card-header {
        margin-bottom: 20rpx;
    }
    
    .product-name {
        font-size: 30rpx;
        font-weight: 700;
        color: #333;
        line-height: 1.4;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    
    .card-meta {
        display: flex;
        flex-direction: column;
        gap: 15rpx;
        margin-bottom: 25rpx;
    }
    
    .meta-item {
        display: flex;
        align-items: center;
        gap: 10rpx;
    }
    
    .meta-icon {
        font-size: 26rpx;
        color: #667eea;
    }
    
    .meta-text {
        font-size: 24rpx;
        color: #666;
        flex: 1;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    
    .card-action {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-top: 20rpx;
        border-top: 1rpx solid #eee;
    }
    
    .action-text {
        font-size: 26rpx;
        color: #667eea;
        font-weight: 500;
    }
    
    .action-arrow {
        .cuIcon-right {
            font-size: 24rpx;
            color: #667eea;
            transition: transform 0.3s ease;
        }
    }
    
    .product-card:active .action-arrow .cuIcon-right {
        transform: translateX(5rpx);
    }
    
    .empty-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        padding: 120rpx 0;
        text-align: center;
    }
    
    .empty-illustration {
        width: 120rpx;
        height: 120rpx;
        background: rgba(255, 255, 255, 0.9);
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 30rpx;
        box-shadow: 0 10rpx 30rpx rgba(0, 0, 0, 0.1);
        
        .cuIcon-pic {
            font-size: 50rpx;
            color: #667eea;
        }
    }
    
    .empty-title {
        font-size: 32rpx;
        font-weight: 600;
        color: white;
        margin-bottom: 15rpx;
        text-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.2);
    }
    
    .empty-desc {
        font-size: 26rpx;
        color: rgba(255, 255, 255, 0.8);
    }
    
    .load-status {
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
            width: 100rpx;
            height: 2rpx;
            background: linear-gradient(90deg, 
                transparent, 
                rgba(255, 255, 255, 0.5), 
                transparent);
        }
    }
    
    .load-text {
        font-size: 26rpx;
        color: rgba(255, 255, 255, 0.8);
        animation: pulse 1.5s infinite;
    }
    
    .load-text-end {
        font-size: 26rpx;
        color: rgba(255, 255, 255, 0.6);
    }
    
    @keyframes pulse {
        0%, 100% { opacity: 0.5; }
        50% { opacity: 1; }
    }
    
    // 响应式调整
    @media (max-width: 750rpx) {
        .product-grid {
            grid-template-columns: 1fr;
            gap: 25rpx;
        }
        
        .search-main {
            flex-direction: column;
            gap: 25rpx;
        }
        
        .search-input-wrapper,
        .search-button {
            width: 100%;
        }
        
        .search-container {
            padding: 25rpx;
        }
        
        .search-content {
            padding: 25rpx;
        }
    }
</style>