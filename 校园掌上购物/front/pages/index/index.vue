<template>
    <view class="container">
        <!-- 渐变装饰背景 -->
        <view class="decorative-bg">
            <view class="gradient-circle circle-1"></view>
            <view class="gradient-circle circle-2"></view>
            <view class="gradient-circle circle-3"></view>
            <view class="gradient-circle circle-4"></view>
        </view>

        <!-- 轮播图区域 -->
        <view class="banner-section">
            <swiper class="banner-swiper" 
                    circular 
                    autoplay 
                    :interval="3000" 
                    :duration="1000" 
                    indicator-dots
                    indicator-active-color="#ff6b6b"
                    indicator-color="rgba(255,255,255,0.4)">
                <swiper-item v-for="(item, index) in swiperList" :key="index">
                    <view class="swiper-item">
                        <image :src="item.img" mode="aspectFill" class="swiper-image"></image>
                        <view class="swiper-overlay"></view>
                    </view>
                </swiper-item>
            </swiper>
            <view class="banner-decoration">
                <view class="decoration-line"></view>
            </view>
        </view>

        <!-- 主要内容区域 -->
        <scroll-view class="main-content" scroll-y>
            <!-- 商品管理模块 -->
            <view class="module-card">
                <view class="module-header">
                    <view class="header-left">
                        <view class="title-icon">
                            <view class="icon-dot"></view>
                            <view class="icon-line"></view>
                        </view>
                        <text class="module-title">商品管理</text>
                        <view class="title-underline"></view>
                    </view>
                    <view class="more-btn" @tap="onPageTap('shangpinguanli')">
                        <text class="more-text">更多</text>
                        <view class="more-arrow">
                            <view class="arrow-line"></view>
                            <view class="arrow-head"></view>
                        </view>
                    </view>
                </view>
                
                <view class="product-grid">
                    <view class="product-item" v-for="(product, index) in shangpinguanlilist" :key="index">
                        <view class="product-image-container">
                            <image v-for="(value, key, ind) in product" 
                                   :key="ind"  
                                   v-if="ind == 3" 
                                   @tap="onDetailTap('shangpinguanli',product.id)" 
                                   :src="value" 
                                   mode="aspectFill"
                                   class="product-image">
                            </image>
                            <view class="image-overlay"></view>
                            <view class="item-index">0{{ index + 1 }}</view>
                        </view>
                        <view class="product-info">
                            <text class="product-name" 
                                  v-for="(value, key, ind) in product" 
                                  :key="ind"  
                                  v-if="ind == 2">
                                {{ value }}
                            </text>
                            <view class="product-line"></view>
                        </view>
                    </view>
                </view>
            </view>

            <!-- 店铺信息模块 -->
            <view class="module-card">
                <view class="module-header">
                    <view class="header-left">
                        <view class="title-icon">
                            <view class="icon-dot"></view>
                            <view class="icon-line"></view>
                        </view>
                        <text class="module-title">店铺信息</text>
                        <view class="title-underline"></view>
                    </view>
                    <view class="more-btn" @tap="onPageTap('dianpuxinxi')">
                        <text class="more-text">更多</text>
                        <view class="more-arrow">
                            <view class="arrow-line"></view>
                            <view class="arrow-head"></view>
                        </view>
                    </view>
                </view>
                
                <view class="product-grid">
                    <view class="product-item" v-for="(product, index) in dianpuxinxilist" :key="index">
                        <view class="product-image-container">
                            <image v-for="(value, key, ind) in product" 
                                   :key="ind"  
                                   v-if="ind == 3" 
                                   @tap="onDetailTap('dianpuxinxi',product.id)" 
                                   :src="value" 
                                   mode="aspectFill"
                                   class="product-image">
                            </image>
                            <view class="image-overlay"></view>
                            <view class="item-index">0{{ index + 1 }}</view>
                        </view>
                        <view class="product-info">
                            <text class="product-name" 
                                  v-for="(value, key, ind) in product" 
                                  :key="ind"  
                                  v-if="ind == 2">
                                {{ value }}
                            </text>
                            <view class="product-line"></view>
                        </view>
                    </view>
                </view>
            </view>

            <!-- 推荐区域 -->
            <view class="recommend-section">
                <!-- 商品管理推荐 -->
                <view class="recommend-card">
                    <view class="recommend-header">
                        <view class="recommend-title-container">
                            <view class="recommend-icon">
                                <view class="recommend-star"></view>
                                <view class="recommend-star"></view>
                                <view class="recommend-star"></view>
                            </view>
                            <text class="recommend-title">商品管理推荐</text>
                        </view>
                        <view class="recommend-subtitle">为你精心挑选</view>
                    </view>
                    
                    <view class="recommend-grid">
                        <view class="recommend-item" v-for="(product,index) in shangpinguanlirecommendList" :key="index">
                            <view class="recommend-image-wrapper">
                                <image @tap="onDetailTap('shangpinguanli',product.id)"  
                                       v-for="(value, key, ind) in product" 
                                       :key="ind"  
                                       v-if="ind == 3" 
                                       :src="value" 
                                       mode="aspectFill"
                                       class="recommend-image">
                                </image>
                                <view class="recommend-badge">
                                    <text class="badge-text">推荐</text>
                                </view>
                            </view>
                            <view class="recommend-info">
                                <text class="recommend-name" 
                                      v-for="(value, key, ind) in product" 
                                      :key="ind"  
                                      v-if="ind == 2">
                                    {{ value }}
                                </text>
                                <view class="recommend-tag">
                                    <text class="tag-text">精选</text>
                                </view>
                            </view>
                        </view>
                    </view>
                </view>

                <!-- 店铺信息推荐 -->
                <view class="recommend-card">
                    <view class="recommend-header">
                        <view class="recommend-title-container">
                            <view class="recommend-icon">
                                <view class="recommend-star"></view>
                                <view class="recommend-star"></view>
                                <view class="recommend-star"></view>
                            </view>
                            <text class="recommend-title">店铺信息推荐</text>
                        </view>
                        <view class="recommend-subtitle">优质店铺发现</view>
                    </view>
                    
                    <view class="recommend-grid">
                        <view class="recommend-item" v-for="(product,index) in dianpuxinxirecommendList" :key="index">
                            <view class="recommend-image-wrapper">
                                <image @tap="onDetailTap('dianpuxinxi',product.id)"  
                                       v-for="(value, key, ind) in product" 
                                       :key="ind"  
                                       v-if="ind == 3" 
                                       :src="value" 
                                       mode="aspectFill"
                                       class="recommend-image">
                                </image>
                                <view class="recommend-badge">
                                    <text class="badge-text">热门</text>
                                </view>
                            </view>
                            <view class="recommend-info">
                                <text class="recommend-name" 
                                      v-for="(value, key, ind) in product" 
                                      :key="ind"  
                                      v-if="ind == 2">
                                    {{ value }}
                                </text>
                                <view class="recommend-tag">
                                    <text class="tag-text">优选</text>
                                </view>
                            </view>
                        </view>
                    </view>
                </view>
            </view>

            <!-- 系统通知 -->
            <view class="notice-card">
                <view class="notice-header">
                    <view class="notice-title-container">
                        <view class="notice-icon">
                            <view class="notice-bell">
                                <view class="bell-body"></view>
                                <view class="bell-clapper"></view>
                            </view>
                        </view>
                        <text class="notice-title">系统通知</text>
                    </view>
                    <view class="notice-more" @tap="onPageTap('news')">
                        <text class="more-text">更多</text>
                        <view class="more-icon">›</view>
                    </view>
                </view>
                
                <view class="notice-list">
                    <view class="notice-item" v-for="(notice, index) in news" :key="index">
                        <view class="notice-item-content">
                            <view class="notice-left">
                                <view class="notice-indicator">
                                    <view class="indicator-dot"></view>
                                    <view class="indicator-pulse"></view>
                                </view>
                                <text class="notice-text" @tap="onNewsDetailTap(notice.id)">{{ notice.title }}</text>
                            </view>
                            <view class="notice-right">
                                <text class="notice-time">{{ notice.addtime }}</text>
                                <view class="time-line"></view>
                            </view>
                        </view>
                        <view class="notice-divider" v-if="index < news.length - 1"></view>
                    </view>
                </view>
            </view>
        </scroll-view>
    </view>
</template>

<script>
    import '@/assets/css/global-restaurant.css'
    import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
    import KristenGoodsList from "@/components/kristen-goodsList/kristen-goodsList.vue"
    
    export default {
        components: {
            uniIcons,
            KristenGoodsList
        },
        data() {
            return {
                autoplaySwiper: {"delay":5000,"disableOnInteraction":false} ? true : false,
                intervalSwiper: {"delay":5000,"disableOnInteraction":false} ? 5000 : 5000,
                swiperList: [],
                mpGoodsShow: 'imgFalls',
                shangpinguanlilist: [],
                dianpuxinxilist: [],
                news: [],
                shangpinguanlirecommendList: [],
                dianpuxinxirecommendList: [],
                user: {},
                ws: null,
            }
        },
        async onShow() {
            // 轮播图
            let swiperList = []
            let res = await this.$api.page('config', {
                page: 1,
                limit: 5
            });
            for (let item of res.data.list) {
                if (item.value && item.value!="" && item.value!=null ) {
                    swiperList.push({
                        img: item.value
                    });
                }
            }
            if (swiperList) {
                this.swiperList = swiperList;
            }
            
            // 网站资讯
            res = await this.$api.list('news', {
                page: 1,
                limit: 3
            })
            this.news = res.data.list
            
            res = await this.$api.list('shangpinguanli', {
                page: 1,
                limit: 6
            });
            this.shangpinguanlilist = res.data.list
            
            res = await this.$api.list('dianpuxinxi', {
                page: 1,
                limit: 6
            });
            this.dianpuxinxilist = res.data.list
            
            let table = uni.getStorageSync("nowTable");
            let users = await this.$api.session(table);
            this.user = users.data;
            
            if(this.ws != null) {
                this.ws.close();
            }
            this.connectWebSocket();
            
            res = await this.$api.recommend('shangpinguanli',this.user.id,10);
            this.shangpinguanlirecommendList = res.data
            
            res = await this.$api.recommend('dianpuxinxi',this.user.id,10);
            this.dianpuxinxirecommendList = res.data
        },
        methods: {
            setWs(ws) {
                this.$store.commit('setWs', ws);
            },
            connectWebSocket() {
                const wsUrl = "ws://localhost:8080/springbootzkgw/consultation?role=" + this.user.role + "&userid=" + this.user.id + "&nicheng=" + this.user.nicheng;
                this.ws = uni.connectSocket({
                    url: wsUrl,
                    method: 'GET',
                    success: function(res) {
                        console.log('WebSocket success', res)
                    },
                    fail: function(err) {
                        console.log('WebSocket fail', err)
                    }
                })
                this.setWs(this.ws);
            },
            onSwiperTap(e) {
            },
            onNewsDetailTap(id) {
                this.$utils.jump(`../news/detail?id=${id}`)
            },
            toGoodsDetail(id,tableName){
                this.$utils.jump(`../${tableName}/detail?id=${id}`)
            },
            onDetailTap(tableName, id) {
                this.$utils.jump(`../${tableName}/detail?id=${id}`)
            },
            onPageTap(tableName){
                uni.navigateTo({
                    url: `../${tableName}/list`,
                    fail: function(){
                        uni.switchTab({
                            url: `../${tableName}/list`
                        });
                    }
                });
            }
        },
        onUnload() {
            if (this.ws) {
                this.ws.close();
            }
        }
    }
</script>

<style lang="scss">
    .container {
        min-height: 100vh;
        background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
        position: relative;
        overflow: hidden;
        
        .decorative-bg {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            pointer-events: none;
            z-index: 0;
            
            .gradient-circle {
                position: absolute;
                border-radius: 50%;
                filter: blur(60rpx);
                opacity: 0.15;
                animation: float 20s ease-in-out infinite;
                
                &.circle-1 {
                    width: 400rpx;
                    height: 400rpx;
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    top: 10%;
                    left: -10%;
                    animation-delay: 0s;
                }
                
                &.circle-2 {
                    width: 300rpx;
                    height: 300rpx;
                    background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
                    top: 30%;
                    right: -5%;
                    animation-delay: 5s;
                }
                
                &.circle-3 {
                    width: 250rpx;
                    height: 250rpx;
                    background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
                    bottom: 20%;
                    left: 5%;
                    animation-delay: 10s;
                }
                
                &.circle-4 {
                    width: 350rpx;
                    height: 350rpx;
                    background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
                    bottom: 10%;
                    right: 10%;
                    animation-delay: 15s;
                }
            }
        }
        
        .banner-section {
            position: relative;
            height: 420rpx;
            overflow: hidden;
            
            .banner-swiper {
                height: 100%;
                
                .swiper-item {
                    position: relative;
                    height: 100%;
                    
                    .swiper-image {
                        width: 100%;
                        height: 100%;
                        display: block;
                    }
                    
                    .swiper-overlay {
                        position: absolute;
                        top: 0;
                        left: 0;
                        right: 0;
                        bottom: 0;
                        background: linear-gradient(180deg, 
                            rgba(0,0,0,0) 0%, 
                            rgba(0,0,0,0.1) 70%,
                            rgba(0,0,0,0.2) 100%);
                        pointer-events: none;
                    }
                }
            }
            
            .banner-decoration {
                position: absolute;
                bottom: 0;
                left: 0;
                right: 0;
                height: 60rpx;
                background: linear-gradient(180deg, rgba(255,255,255,0) 0%, #ffffff 100%);
                
                .decoration-line {
                    position: absolute;
                    top: 30rpx;
                    left: 50%;
                    transform: translateX(-50%);
                    width: 120rpx;
                    height: 4rpx;
                    background: linear-gradient(90deg, 
                        rgba(255,107,107,0) 0%,
                        rgba(255,107,107,0.8) 50%,
                        rgba(255,107,107,0) 100%);
                    border-radius: 2rpx;
                }
            }
        }
        
        .main-content {
            position: relative;
            z-index: 1;
            height: calc(100vh - 420rpx);
            padding-bottom: env(safe-area-inset-bottom);
            
            .module-card {
                background: rgba(255, 255, 255, 0.9);
                backdrop-filter: blur(20px);
                border-radius: 24rpx;
                margin: 30rpx;
                padding: 32rpx;
                box-shadow: 0 10rpx 40rpx rgba(0, 0, 0, 0.08),
                            0 1rpx 3rpx rgba(0, 0, 0, 0.04),
                            inset 0 1rpx 0 rgba(255, 255, 255, 0.4);
                border: 1rpx solid rgba(255, 255, 255, 0.3);
                position: relative;
                overflow: hidden;
                
                &::before {
                    content: '';
                    position: absolute;
                    top: 0;
                    left: 0;
                    right: 0;
                    height: 4rpx;
                    background: linear-gradient(90deg, 
                        #ff6b6b 0%, 
                        #4ecdc4 50%, 
                        #667eea 100%);
                    border-radius: 24rpx 24rpx 0 0;
                }
                
                .module-header {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    margin-bottom: 32rpx;
                    
                    .header-left {
                        display: flex;
                        align-items: center;
                        position: relative;
                        
                        .title-icon {
                            position: relative;
                            width: 40rpx;
                            height: 40rpx;
                            margin-right: 16rpx;
                            
                            .icon-dot {
                                position: absolute;
                                width: 12rpx;
                                height: 12rpx;
                                background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
                                border-radius: 50%;
                                top: 50%;
                                left: 50%;
                                transform: translate(-50%, -50%);
                                box-shadow: 0 4rpx 12rpx rgba(255, 107, 107, 0.3);
                            }
                            
                            .icon-line {
                                position: absolute;
                                width: 20rpx;
                                height: 3rpx;
                                background: linear-gradient(90deg, #4ecdc4 0%, #667eea 100%);
                                border-radius: 1.5rpx;
                                bottom: 8rpx;
                                left: 50%;
                                transform: translateX(-50%);
                                animation: lineSlide 2s ease-in-out infinite;
                            }
                        }
                        
                        .module-title {
                            font-size: 34rpx;
                            font-weight: 700;
                            color: #2d3748;
                            letter-spacing: 1rpx;
                            background: linear-gradient(135deg, #2d3748 0%, #4a5568 100%);
                            -webkit-background-clip: text;
                            -webkit-text-fill-color: transparent;
                            background-clip: text;
                        }
                        
                        .title-underline {
                            position: absolute;
                            bottom: -8rpx;
                            left: 56rpx;
                            width: 60rpx;
                            height: 3rpx;
                            background: linear-gradient(90deg, #ff6b6b 0%, #4ecdc4 100%);
                            border-radius: 1.5rpx;
                        }
                    }
                    
                    .more-btn {
                        display: flex;
                        align-items: center;
                        gap: 8rpx;
                        padding: 12rpx 20rpx;
                        border-radius: 20rpx;
                        background: linear-gradient(135deg, rgba(102, 126, 234, 0.1) 0%, rgba(255, 107, 107, 0.1) 100%);
                        border: 1rpx solid rgba(102, 126, 234, 0.2);
                        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
                        
                        &:active {
                            transform: translateX(4rpx);
                            background: linear-gradient(135deg, rgba(102, 126, 234, 0.2) 0%, rgba(255, 107, 107, 0.2) 100%);
                        }
                        
                        .more-text {
                            font-size: 24rpx;
                            font-weight: 500;
                            color: #667eea;
                        }
                        
                        .more-arrow {
                            position: relative;
                            width: 20rpx;
                            height: 20rpx;
                            
                            .arrow-line {
                                position: absolute;
                                width: 12rpx;
                                height: 2rpx;
                                background: #667eea;
                                top: 50%;
                                right: 0;
                                transform: translateY(-50%);
                            }
                            
                            .arrow-head {
                                position: absolute;
                                width: 6rpx;
                                height: 6rpx;
                                border-top: 2rpx solid #667eea;
                                border-right: 2rpx solid #667eea;
                                transform: rotate(45deg);
                                top: 50%;
                                right: 0;
                                transform: translateY(-50%) rotate(45deg);
                            }
                        }
                    }
                }
                
                .product-grid {
                    display: grid;
                    grid-template-columns: repeat(2, 1fr);
                    gap: 24rpx;
                    
                    .product-item {
                        background: white;
                        border-radius: 20rpx;
                        overflow: hidden;
                        box-shadow: 0 4rpx 20rpx rgba(0, 0, 0, 0.06);
                        transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
                        position: relative;
                        
                        &:active {
                            transform: translateY(-8rpx) scale(0.98);
                            box-shadow: 0 16rpx 40rpx rgba(0, 0, 0, 0.15);
                        }
                        
                        .product-image-container {
                            position: relative;
                            height: 240rpx;
                            overflow: hidden;
                            
                            .product-image {
                                width: 100%;
                                height: 100%;
                                display: block;
                                transition: transform 0.6s cubic-bezier(0.4, 0, 0.2, 1);
                            }
                            
                            .image-overlay {
                                position: absolute;
                                top: 0;
                                left: 0;
                                right: 0;
                                bottom: 0;
                                background: linear-gradient(180deg, 
                                    rgba(0,0,0,0) 0%, 
                                    rgba(0,0,0,0.05) 100%);
                                pointer-events: none;
                            }
                            
                            .item-index {
                                position: absolute;
                                top: 16rpx;
                                left: 16rpx;
                                font-size: 20rpx;
                                font-weight: 700;
                                color: rgba(255, 255, 255, 0.9);
                                text-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.2);
                                z-index: 1;
                            }
                        }
                        
                        .product-info {
                            padding: 20rpx;
                            
                            .product-name {
                                font-size: 28rpx;
                                font-weight: 600;
                                color: #2d3748;
                                line-height: 1.4;
                                display: block;
                                margin-bottom: 12rpx;
                                word-break: break-word;
                            }
                            
                            .product-line {
                                width: 40rpx;
                                height: 2rpx;
                                background: linear-gradient(90deg, #ff6b6b 0%, #4ecdc4 100%);
                                border-radius: 1rpx;
                                opacity: 0.6;
                            }
                        }
                        
                        &:hover .product-image {
                            transform: scale(1.05);
                        }
                    }
                }
            }
            
            .recommend-section {
                padding: 0 30rpx;
                
                .recommend-card {
                    background: linear-gradient(135deg, rgba(255, 255, 255, 0.95) 0%, rgba(248, 250, 252, 0.95) 100%);
                    backdrop-filter: blur(20px);
                    border-radius: 28rpx;
                    margin-bottom: 32rpx;
                    padding: 36rpx;
                    box-shadow: 0 12rpx 48rpx rgba(102, 126, 234, 0.12),
                                0 4rpx 16rpx rgba(0, 0, 0, 0.04),
                                inset 0 1rpx 0 rgba(255, 255, 255, 0.4);
                    border: 1rpx solid rgba(255, 255, 255, 0.4);
                    position: relative;
                    overflow: hidden;
                    
                    &::before {
                        content: '';
                        position: absolute;
                        top: 0;
                        left: 0;
                        right: 0;
                        bottom: 0;
                        background: radial-gradient(circle at 30% 20%, rgba(102, 126, 234, 0.05) 0%, transparent 50%),
                                    radial-gradient(circle at 70% 80%, rgba(255, 107, 107, 0.05) 0%, transparent 50%);
                        pointer-events: none;
                    }
                    
                    .recommend-header {
                        margin-bottom: 32rpx;
                        
                        .recommend-title-container {
                            display: flex;
                            align-items: center;
                            gap: 16rpx;
                            margin-bottom: 8rpx;
                            
                            .recommend-icon {
                                display: flex;
                                gap: 4rpx;
                                
                                .recommend-star {
                                    width: 8rpx;
                                    height: 8rpx;
                                    background: linear-gradient(135deg, #ffd700 0%, #ffa500 100%);
                                    border-radius: 50%;
                                    animation: starTwinkle 2s ease-in-out infinite;
                                    
                                    &:nth-child(2) {
                                        animation-delay: 0.2s;
                                    }
                                    
                                    &:nth-child(3) {
                                        animation-delay: 0.4s;
                                    }
                                }
                            }
                            
                            .recommend-title {
                                font-size: 32rpx;
                                font-weight: 700;
                                color: transparent;
                                background: linear-gradient(135deg, #667eea 0%, #764ba2 50%, #f093fb 100%);
                                -webkit-background-clip: text;
                                -webkit-text-fill-color: transparent;
                                background-clip: text;
                                letter-spacing: 0.5rpx;
                            }
                        }
                        
                        .recommend-subtitle {
                            font-size: 24rpx;
                            color: #94a3b8;
                            font-weight: 400;
                            margin-left: 24rpx;
                        }
                    }
                    
                    .recommend-grid {
                        display: grid;
                        grid-template-columns: repeat(2, 1fr);
                        gap: 28rpx;
                        
                        .recommend-item {
                            background: white;
                            border-radius: 20rpx;
                            overflow: hidden;
                            box-shadow: 0 8rpx 32rpx rgba(0, 0, 0, 0.08);
                            transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
                            position: relative;
                            
                            &:active {
                                transform: translateY(-6rpx);
                                box-shadow: 0 20rpx 60rpx rgba(102, 126, 234, 0.2);
                            }
                            
                            .recommend-image-wrapper {
                                position: relative;
                                height: 220rpx;
                                overflow: hidden;
                                
                                .recommend-image {
                                    width: 100%;
                                    height: 100%;
                                    display: block;
                                    transition: transform 0.6s cubic-bezier(0.4, 0, 0.2, 1);
                                }
                                
                                .recommend-badge {
                                    position: absolute;
                                    top: 16rpx;
                                    right: 16rpx;
                                    background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
                                    padding: 4rpx 12rpx;
                                    border-radius: 12rpx;
                                    box-shadow: 0 4rpx 12rpx rgba(255, 107, 107, 0.3);
                                    
                                    .badge-text {
                                        font-size: 20rpx;
                                        font-weight: 600;
                                        color: white;
                                    }
                                }
                            }
                            
                            .recommend-info {
                                padding: 20rpx;
                                
                                .recommend-name {
                                    font-size: 26rpx;
                                    font-weight: 600;
                                    color: #2d3748;
                                    line-height: 1.4;
                                    display: block;
                                    margin-bottom: 12rpx;
                                    word-break: break-word;
                                }
                                
                                .recommend-tag {
                                    display: inline-block;
                                    background: linear-gradient(135deg, rgba(102, 126, 234, 0.1) 0%, rgba(255, 107, 107, 0.1) 100%);
                                    padding: 4rpx 12rpx;
                                    border-radius: 8rpx;
                                    border: 1rpx solid rgba(102, 126, 234, 0.2);
                                    
                                    .tag-text {
                                        font-size: 20rpx;
                                        font-weight: 500;
                                        color: #667eea;
                                    }
                                }
                            }
                            
                            &:hover .recommend-image {
                                transform: scale(1.08);
                            }
                        }
                    }
                }
            }
            
            .notice-card {
                background: linear-gradient(135deg, rgba(255, 255, 255, 0.98) 0%, rgba(248, 250, 252, 0.98) 100%);
                backdrop-filter: blur(20px);
                border-radius: 28rpx;
                margin: 30rpx;
                padding: 36rpx;
                box-shadow: 0 10rpx 40rpx rgba(0, 0, 0, 0.08),
                            0 4rpx 16rpx rgba(0, 0, 0, 0.04),
                            inset 0 1rpx 0 rgba(255, 255, 255, 0.4);
                border: 1rpx solid rgba(255, 255, 255, 0.3);
                position: relative;
                overflow: hidden;
                
                &::before {
                    content: '';
                    position: absolute;
                    top: 0;
                    left: 0;
                    right: 0;
                    height: 4rpx;
                    background: linear-gradient(90deg, 
                        #4ecdc4 0%, 
                        #667eea 50%, 
                        #ff6b6b 100%);
                    border-radius: 28rpx 28rpx 0 0;
                }
                
                .notice-header {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    margin-bottom: 28rpx;
                    
                    .notice-title-container {
                        display: flex;
                        align-items: center;
                        gap: 16rpx;
                        
                        .notice-icon {
                            width: 40rpx;
                            height: 40rpx;
                            position: relative;
                            
                            .notice-bell {
                                position: absolute;
                                top: 50%;
                                left: 50%;
                                transform: translate(-50%, -50%);
                                width: 24rpx;
                                height: 24rpx;
                                
                                .bell-body {
                                    width: 100%;
                                    height: 100%;
                                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                                    border-radius: 50% 50% 0 0;
                                    position: relative;
                                    animation: bellRing 2s ease-in-out infinite;
                                    
                                    &::before {
                                        content: '';
                                        position: absolute;
                                        top: -4rpx;
                                        left: 50%;
                                        transform: translateX(-50%);
                                        width: 6rpx;
                                        height: 6rpx;
                                        background: #667eea;
                                        border-radius: 50%;
                                    }
                                }
                                
                                .bell-clapper {
                                    position: absolute;
                                    bottom: -2rpx;
                                    left: 50%;
                                    transform: translateX(-50%);
                                    width: 2rpx;
                                    height: 6rpx;
                                    background: #667eea;
                                    border-radius: 1rpx;
                                    animation: clapperSwing 2s ease-in-out infinite;
                                }
                            }
                        }
                        
                        .notice-title {
                            font-size: 32rpx;
                            font-weight: 700;
                            color: #2d3748;
                            letter-spacing: 0.5rpx;
                        }
                    }
                    
                    .notice-more {
                        display: flex;
                        align-items: center;
                        gap: 8rpx;
                        padding: 10rpx 20rpx;
                        border-radius: 20rpx;
                        background: linear-gradient(135deg, rgba(78, 205, 196, 0.1) 0%, rgba(102, 126, 234, 0.1) 100%);
                        border: 1rpx solid rgba(78, 205, 196, 0.2);
                        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
                        
                        &:active {
                            transform: translateX(4rpx);
                            background: linear-gradient(135deg, rgba(78, 205, 196, 0.2) 0%, rgba(102, 126, 234, 0.2) 100%);
                        }
                        
                        .more-text {
                            font-size: 24rpx;
                            font-weight: 500;
                            color: #4ecdc4;
                        }
                        
                        .more-icon {
                            font-size: 28rpx;
                            color: #667eea;
                            font-weight: bold;
                        }
                    }
                }
                
                .notice-list {
                    .notice-item {
                        .notice-item-content {
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            padding: 20rpx 0;
                            
                            .notice-left {
                                display: flex;
                                align-items: center;
                                gap: 20rpx;
                                flex: 1;
                                
                                .notice-indicator {
                                    position: relative;
                                    width: 24rpx;
                                    height: 24rpx;
                                    
                                    .indicator-dot {
                                        position: absolute;
                                        top: 50%;
                                        left: 50%;
                                        transform: translate(-50%, -50%);
                                        width: 8rpx;
                                        height: 8rpx;
                                        background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
                                        border-radius: 50%;
                                        z-index: 2;
                                    }
                                    
                                    .indicator-pulse {
                                        position: absolute;
                                        top: 50%;
                                        left: 50%;
                                        transform: translate(-50%, -50%);
                                        width: 24rpx;
                                        height: 24rpx;
                                        background: rgba(255, 107, 107, 0.2);
                                        border-radius: 50%;
                                        animation: pulse 2s ease-out infinite;
                                    }
                                }
                                
                                .notice-text {
                                    font-size: 28rpx;
                                    font-weight: 500;
                                    color: #4a5568;
                                    line-height: 1.4;
                                    flex: 1;
                                    word-break: break-word;
                                    transition: color 0.3s ease;
                                    
                                    &:active {
                                        color: #667eea;
                                    }
                                }
                            }
                            
                            .notice-right {
                                display: flex;
                                flex-direction: column;
                                align-items: flex-end;
                                gap: 4rpx;
                                
                                .notice-time {
                                    font-size: 22rpx;
                                    color: #94a3b8;
                                    font-weight: 400;
                                }
                                
                                .time-line {
                                    width: 40rpx;
                                    height: 2rpx;
                                    background: linear-gradient(90deg, #94a3b8 0%, transparent 100%);
                                    border-radius: 1rpx;
                                }
                            }
                        }
                        
                        .notice-divider {
                            height: 1rpx;
                            background: linear-gradient(90deg, 
                                transparent 0%,
                                rgba(148, 163, 184, 0.2) 50%,
                                transparent 100%);
                            margin: 0 -36rpx;
                        }
                    }
                }
            }
        }
    }
    
    // 动画定义
    @keyframes float {
        0%, 100% {
            transform: translate(0, 0) rotate(0deg);
        }
        25% {
            transform: translate(20rpx, -20rpx) rotate(5deg);
        }
        50% {
            transform: translate(-15rpx, 15rpx) rotate(-5deg);
        }
        75% {
            transform: translate(10rpx, -10rpx) rotate(3deg);
        }
    }
    
    @keyframes lineSlide {
        0%, 100% {
            width: 20rpx;
            opacity: 1;
        }
        50% {
            width: 24rpx;
            opacity: 0.8;
        }
    }
    
    @keyframes starTwinkle {
        0%, 100% {
            opacity: 1;
            transform: scale(1);
        }
        50% {
            opacity: 0.6;
            transform: scale(0.9);
        }
    }
    
    @keyframes bellRing {
        0%, 100% {
            transform: translate(-50%, -50%) rotate(0deg);
        }
        25% {
            transform: translate(-50%, -50%) rotate(5deg);
        }
        75% {
            transform: translate(-50%, -50%) rotate(-5deg);
        }
    }
    
    @keyframes clapperSwing {
        0%, 100% {
            transform: translateX(-50%) rotate(0deg);
        }
        25% {
            transform: translateX(-50%) rotate(15deg);
        }
        75% {
            transform: translateX(-50%) rotate(-15deg);
        }
    }
    
    @keyframes pulse {
        0% {
            transform: translate(-50%, -50%) scale(0.8);
            opacity: 0.8;
        }
        100% {
            transform: translate(-50%, -50%) scale(1.5);
            opacity: 0;
        }
    }
</style>