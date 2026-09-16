<template>
    <view class="dw-user">
        <!-- 渐变背景 -->
        <view class="dw-user-bg-gradient"></view>
        <view class="dw-user-bg-pattern"></view>
        
        <!-- 用户信息卡片 -->
        <view class="dw-user-header-card">
            <view class="dw-user-header-content" v-if="tableName=='yonghu'">
                <!-- 用户头像区域 -->
                <view class="dw-user-avatar-container">
                    <view class="dw-user-avatar-wrapper">
                        <image class="dw-user-avatar" src="../../static/center/avatar.svg"></image>
                        <view class="dw-user-avatar-badge">
                            <text class="icon-user">👤</text>
                        </view>
                    </view>
                    <view class="dw-user-status">
                        <view class="status-dot active"></view>
                        <text class="status-text">在线</text>
                    </view>
                </view>
                
                <!-- 用户信息 -->
                <view class="dw-user-info">
                    <view class="dw-user-welcome">
                        <text class="welcome-text">欢迎回来，</text>
                        <text class="username">{{user.zhanghao || '用户'}}</text>
                    </view>
                    
                    <!-- 用户标签 -->
                    <view class="dw-user-tags">
                        <view class="user-tag balance-tag">
                            <view class="tag-icon">
                                <text class="icon-money">💰</text>
                            </view>
                            <view class="tag-content">
                                <text class="tag-label">账户余额</text>
                                <text class="tag-value">¥{{user.money || '0.00'}}</text>
                            </view>
                        </view>
                        
                        <view class="user-tag level-tag">
                            <view class="tag-icon">
                                <text class="icon-vip">⭐</text>
                            </view>
                            <view class="tag-content">
                                <text class="tag-label">会员等级</text>
                                <text class="tag-value">VIP1</text>
                            </view>
                        </view>
                    </view>
                </view>
                
                <!-- 设置按钮 -->
                <view class="dw-user-settings" @tap="onPageTap('../user-info/user-info')">
                    <view class="settings-icon-wrapper">
                        <text class="icon-settings">⚙️</text>
                    </view>
                    <text class="settings-text">设置</text>
                </view>
            </view>
        </view>

        <!-- 功能菜单区域 -->
        <view class="dw-user-menu-container">
            <!-- 菜单卡片 -->
            <view class="menu-card">
                <view class="menu-header">
                    <text class="menu-title">快捷功能</text>
                    <view class="menu-decor">
                        <view class="decor-line"></view>
                        <view class="decor-dot"></view>
                    </view>
                </view>
                
                <view class="menu-grid">
                    <!-- 我要发贴 -->
                    <view class="menu-item" @tap="onPageTap('../forum-add-or-update/forum-add-or-update')">
                        <view class="menu-item-icon forum-icon">
                            <text class="icon-forum">📝</text>
                            <view class="icon-badge new">NEW</view>
                        </view>
                        <text class="menu-item-text">我要发贴</text>
                    </view>
                    
                    <!-- 我的发贴 -->
                    <view class="menu-item" @tap="onPageTap('../forum-my/forum-my')">
                        <view class="menu-item-icon my-forum-icon">
                            <text class="icon-my-forum">📄</text>
                        </view>
                        <text class="menu-item-text">我的发贴</text>
                    </view>
                    
                    <!-- 用户充值 -->
                    <view class="menu-item" @tap="onPageTap('../shop-recharge/pay-confirm')">
                        <view class="menu-item-icon recharge-icon">
                            <text class="icon-recharge">💳</text>
                        </view>
                        <text class="menu-item-text">用户充值</text>
                    </view>
                    
                    <!-- 购物车 -->
                    <view class="menu-item" @tap="onPageTap('../shop-cart/shop-cart')">
                        <view class="menu-item-icon cart-icon">
                            <text class="icon-cart">🛒</text>
                            <view class="cart-badge">3</view>
                        </view>
                        <text class="menu-item-text">购物车</text>
                    </view>
                </view>
            </view>
            
            <!-- 订单管理卡片 -->
            <view class="menu-card">
                <view class="menu-header">
                    <text class="menu-title">订单管理</text>
                    <view class="menu-decor">
                        <view class="decor-line"></view>
                        <view class="decor-dot"></view>
                    </view>
                </view>
                
                <view class="menu-list">
                    <!-- 我的订单 -->
                    <view class="menu-list-item" @tap="onPageTap('../shop-order/shop-order')">
                        <view class="list-item-icon">
                            <text class="icon-order">📦</text>
                        </view>
                        <view class="list-item-content">
                            <text class="list-item-title">我的订单</text>
                            <text class="list-item-desc">查看所有订单记录</text>
                        </view>
                        <view class="list-item-arrow">
                            <text class="icon-arrow">›</text>
                        </view>
                    </view>
                    
                    <!-- 我的地址 -->
                    <view class="menu-list-item" @tap="onPageTap('../shop-address/shop-address')">
                        <view class="list-item-icon">
                            <text class="icon-address">📍</text>
                        </view>
                        <view class="list-item-content">
                            <text class="list-item-title">我的地址</text>
                            <text class="list-item-desc">管理收货地址</text>
                        </view>
                        <view class="list-item-arrow">
                            <text class="icon-arrow">›</text>
                        </view>
                    </view>
                </view>
            </view>
            
            <!-- 其他功能卡片 -->
            <view class="menu-card">
                <view class="menu-header">
                    <text class="menu-title">我的服务</text>
                    <view class="menu-decor">
                        <view class="decor-line"></view>
                        <view class="decor-dot"></view>
                    </view>
                </view>
                
                <view class="menu-list">
                    <!-- 在线留言 -->
                    <view @tap="onPageTap('../message/message')" class="menu-list-item">
                        <view class="list-item-icon">
                            <text class="icon-message">💬</text>
                        </view>
                        <view class="list-item-content">
                            <text class="list-item-title">在线留言</text>
                            <text class="list-item-desc">联系客服与反馈</text>
                        </view>
                        <view class="list-item-arrow">
                            <text class="icon-arrow">›</text>
                        </view>
                    </view>
                    
                    <!-- 我的收藏 -->
                    <view @tap="onPageTap('../storeup/list')" class="menu-list-item">
                        <view class="list-item-icon">
                            <text class="icon-collect">❤️</text>
                        </view>
                        <view class="list-item-content">
                            <text class="list-item-title">我的收藏</text>
                            <text class="list-item-desc">查看收藏内容</text>
                        </view>
                        <view class="list-item-arrow">
                            <text class="icon-arrow">›</text>
                        </view>
                    </view>
                    
                    <!-- 动态菜单 -->
                    <view v-for="(item,index) in grmenu" @tap="onPageTap(`../${item.url}/index`)" class="menu-list-item" :key="index">
                        <view class="list-item-icon">
                            <text class="icon-default">📋</text>
                        </view>
                        <view class="list-item-content">
                            <text class="list-item-title">{{item.name}}</text>
                            <text class="list-item-desc">点击查看详情</text>
                        </view>
                        <view class="list-item-arrow">
                            <text class="icon-arrow">›</text>
                        </view>
                    </view>
                </view>
            </view>
            
            <!-- 底部装饰 -->
            <view class="footer-decor">
                <text class="footer-text">—— 为您提供优质服务 ——</text>
            </view>
        </view>
    </view>
</template>

<script>
   import {menu,geren} from '@/utils/menu'

    export default {
        data() {
            return {
                isH5Plus: true,
                user: {},
                tableName: '',
                role: '',
                menuList: [],
                grmenu:[],
            };
        },
        async onLoad() {
            this.role = uni.getStorageSync("role");
            let table = uni.getStorageSync("nowTable");
            let res = await this.$api.session(table);
            this.user = res.data;
            this.tableName = table;
             let menus = geren.list();
            this.menuList = menus;
            this.menuList.forEach((item)=>{
                item.ykMenu.forEach((i)=>{
                    i.child.forEach((a)=>{
                        this.grmenu.push({name:a.menu,url:a.tableName})
                    })
                });
            })
        },
        async onShow() {
            this.role = uni.getStorageSync("role");
            let table = uni.getStorageSync("nowTable");
            let res = await this.$api.session(table);
            this.user = res.data;
            this.tableName = table;
            let menus = menu.list();
            this.menuList = menus;
        },
        methods: {
            onPageTap(url) {
                uni.navigateTo({
                    url: url,
                    fail: function () {
                        uni.switchTab({
                            url: url
                        });
                    }
                });
            }
        }
    }
</script>

<style lang="scss">
    .dw-user {
        position: relative;
        width: 100vw;
        min-height: 100vh;
        background: linear-gradient(135deg, #f0f5ff 0%, #f9fbfd 100%);
        overflow-x: hidden;
        
        &-bg-gradient {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 420rpx;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 0 0 50rpx 50rpx;
            z-index: 1;
        }
        
        &-bg-pattern {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-image: 
                radial-gradient(circle at 20% 30%, rgba(255,255,255,0.1) 0%, transparent 50%),
                radial-gradient(circle at 80% 80%, rgba(255,255,255,0.05) 0%, transparent 50%);
            z-index: 2;
        }
        
        &-header-card {
            position: relative;
            z-index: 10;
            margin: 40rpx 30rpx 0;
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(20px);
            border-radius: 30rpx;
            padding: 40rpx;
            box-shadow: 0 20rpx 60rpx rgba(102, 126, 234, 0.15);
            border: 1rpx solid rgba(255, 255, 255, 0.3);
        }
        
        &-header-content {
            display: flex;
            align-items: center;
            gap: 30rpx;
        }
        
        &-avatar-container {
            position: relative;
            flex-shrink: 0;
        }
        
        &-avatar-wrapper {
            position: relative;
            width: 140rpx;
            height: 140rpx;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 50%;
            padding: 6rpx;
            box-shadow: 0 10rpx 30rpx rgba(102, 126, 234, 0.3);
        }
        
        &-avatar {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background: white;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        
        &-avatar-badge {
            position: absolute;
            bottom: 0;
            right: 0;
            background: #ff6b6b;
            width: 36rpx;
            height: 36rpx;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 3rpx solid white;
            
            .icon-user {
                font-size: 20rpx;
                color: white;
            }
        }
        
        &-status {
            display: flex;
            align-items: center;
            gap: 8rpx;
            position: absolute;
            bottom: -30rpx;
            left: 50%;
            transform: translateX(-50%);
            background: white;
            padding: 6rpx 16rpx;
            border-radius: 20rpx;
            box-shadow: 0 4rpx 12rpx rgba(0,0,0,0.1);
            
            .status-dot {
                width: 12rpx;
                height: 12rpx;
                border-radius: 50%;
                
                &.active {
                    background: #2ecc71;
                    animation: pulse 2s infinite;
                }
            }
            
            .status-text {
                font-size: 20rpx;
                color: #666;
            }
        }
        
        &-info {
            flex: 1;
        }
        
        &-welcome {
            margin-bottom: 20rpx;
            
            .welcome-text {
                font-size: 28rpx;
                color: #666;
            }
            
            .username {
                font-size: 36rpx;
                font-weight: bold;
                color: #2c3e50;
                margin-left: 8rpx;
            }
        }
        
        &-tags {
            display: flex;
            gap: 20rpx;
            
            .user-tag {
                flex: 1;
                background: linear-gradient(135deg, rgba(255,255,255,0.9) 0%, rgba(240,245,255,0.9) 100%);
                border-radius: 16rpx;
                padding: 16rpx;
                display: flex;
                align-items: center;
                gap: 12rpx;
                border: 1rpx solid rgba(102, 126, 234, 0.1);
                
                .tag-icon {
                    width: 50rpx;
                    height: 50rpx;
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    border-radius: 12rpx;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    
                    [class^="icon-"] {
                        font-size: 28rpx;
                        color: white;
                    }
                }
                
                .tag-content {
                    display: flex;
                    flex-direction: column;
                    
                    .tag-label {
                        font-size: 22rpx;
                        color: #666;
                    }
                    
                    .tag-value {
                        font-size: 26rpx;
                        font-weight: bold;
                        color: #2c3e50;
                    }
                }
            }
        }
        
        &-settings {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 8rpx;
            
            .settings-icon-wrapper {
                width: 60rpx;
                height: 60rpx;
                background: rgba(102, 126, 234, 0.1);
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                transition: all 0.3s ease;
                
                .icon-settings {
                    font-size: 30rpx;
                    color: #667eea;
                }
            }
            
            .settings-text {
                font-size: 22rpx;
                color: #666;
            }
            
            &:active .settings-icon-wrapper {
                background: rgba(102, 126, 234, 0.2);
                transform: scale(0.95);
            }
        }
        
        &-menu-container {
            position: relative;
            z-index: 5;
            margin: 30rpx 30rpx 60rpx;
        }
        
        .menu-card {
            background: white;
            border-radius: 24rpx;
            margin-bottom: 30rpx;
            padding: 30rpx;
            box-shadow: 0 10rpx 40rpx rgba(0, 0, 0, 0.06);
            border: 1rpx solid rgba(0, 0, 0, 0.03);
            
            .menu-header {
                margin-bottom: 30rpx;
                position: relative;
                
                .menu-title {
                    font-size: 32rpx;
                    font-weight: bold;
                    color: #2c3e50;
                    position: relative;
                    padding-left: 20rpx;
                    
                    &::before {
                        content: '';
                        position: absolute;
                        left: 0;
                        top: 50%;
                        transform: translateY(-50%);
                        width: 6rpx;
                        height: 28rpx;
                        background: linear-gradient(180deg, #667eea 0%, #764ba2 100%);
                        border-radius: 3rpx;
                    }
                }
                
                .menu-decor {
                    position: absolute;
                    right: 0;
                    top: 50%;
                    transform: translateY(-50%);
                    display: flex;
                    align-items: center;
                    gap: 8rpx;
                    
                    .decor-line {
                        width: 40rpx;
                        height: 3rpx;
                        background: linear-gradient(90deg, #667eea 0%, transparent 100%);
                        border-radius: 2rpx;
                    }
                    
                    .decor-dot {
                        width: 8rpx;
                        height: 8rpx;
                        background: #667eea;
                        border-radius: 50%;
                    }
                }
            }
            
            .menu-grid {
                display: grid;
                grid-template-columns: repeat(4, 1fr);
                gap: 20rpx;
                
                .menu-item {
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    padding: 20rpx 10rpx;
                    border-radius: 16rpx;
                    transition: all 0.3s ease;
                    
                    &:active {
                        background: rgba(102, 126, 234, 0.05);
                        transform: scale(0.95);
                    }
                    
                    &-icon {
                        width: 80rpx;
                        height: 80rpx;
                        border-radius: 20rpx;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        margin-bottom: 16rpx;
                        position: relative;
                        
                        [class^="icon-"] {
                            font-size: 40rpx;
                        }
                        
                        &.forum-icon {
                            background: linear-gradient(135deg, #ff9a9e 0%, #fad0c4 100%);
                            
                            .icon-badge {
                                position: absolute;
                                top: -8rpx;
                                right: -8rpx;
                                background: #ff6b6b;
                                color: white;
                                font-size: 18rpx;
                                padding: 4rpx 8rpx;
                                border-radius: 12rpx;
                                font-weight: bold;
                            }
                        }
                        
                        &.my-forum-icon {
                            background: linear-gradient(135deg, #a1c4fd 0%, #c2e9fb 100%);
                        }
                        
                        &.recharge-icon {
                            background: linear-gradient(135deg, #84fab0 0%, #8fd3f4 100%);
                        }
                        
                        &.cart-icon {
                            background: linear-gradient(135deg, #ffecd2 0%, #fcb69f 100%);
                            position: relative;
                            
                            .cart-badge {
                                position: absolute;
                                top: -8rpx;
                                right: -8rpx;
                                background: #ff6b6b;
                                color: white;
                                font-size: 20rpx;
                                width: 32rpx;
                                height: 32rpx;
                                border-radius: 50%;
                                display: flex;
                                align-items: center;
                                justify-content: center;
                                font-weight: bold;
                            }
                        }
                    }
                    
                    &-text {
                        font-size: 24rpx;
                        color: #2c3e50;
                        font-weight: 500;
                        text-align: center;
                    }
                }
            }
            
            .menu-list {
                .menu-list-item {
                    display: flex;
                    align-items: center;
                    padding: 24rpx 0;
                    border-bottom: 1rpx solid rgba(0, 0, 0, 0.05);
                    
                    &:last-child {
                        border-bottom: none;
                    }
                    
                    &:active {
                        opacity: 0.7;
                    }
                    
                    .list-item-icon {
                        width: 60rpx;
                        height: 60rpx;
                        border-radius: 16rpx;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        margin-right: 20rpx;
                        flex-shrink: 0;
                        
                        [class^="icon-"] {
                            font-size: 32rpx;
                        }
                    }
                    
                    .list-item-content {
                        flex: 1;
                        
                        .list-item-title {
                            font-size: 28rpx;
                            color: #2c3e50;
                            font-weight: 500;
                            display: block;
                            margin-bottom: 4rpx;
                        }
                        
                        .list-item-desc {
                            font-size: 22rpx;
                            color: #95a5a6;
                        }
                    }
                    
                    .list-item-arrow {
                        .icon-arrow {
                            font-size: 36rpx;
                            color: #bdc3c7;
                            font-weight: bold;
                        }
                    }
                }
            }
        }
        
        .footer-decor {
            text-align: center;
            padding: 40rpx 0;
            
            .footer-text {
                font-size: 24rpx;
                color: #95a5a6;
                letter-spacing: 4rpx;
            }
        }
        
        @keyframes pulse {
            0% {
                box-shadow: 0 0 0 0 rgba(46, 204, 113, 0.7);
            }
            70% {
                box-shadow: 0 0 0 10rpx rgba(46, 204, 113, 0);
            }
            100% {
                box-shadow: 0 0 0 0 rgba(46, 204, 113, 0);
            }
        }
    }
</style>