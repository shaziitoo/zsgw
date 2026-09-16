<template>
    <view>
        <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback" :bottom="150">
            <!-- 商品主图 -->
            <view class="product-header">
                <swiper class="product-swiper" :autoplay="autoplaySwiper" :interval="intervalSwiper" circular>
                    <swiper-item v-for="(item, index) in swiperList" :key="index">
                        <image class="swiper-image" :src="item" mode="aspectFill"></image>
                    </swiper-item>
                </swiper>
                <view class="swiper-indicator">
                    <text class="indicator-text">{{ swiperList.length }}张图片</text>
                </view>
            </view>

            <!-- 商品信息 -->
            <view class="product-info">
                <view class="product-price-section">
                    <text class="price-symbol">¥</text>
                    <text class="price-number">{{ detail.price }}</text>
                    <view class="price-tag">
                        <text class="tag-text">商品价格</text>
                    </view>
                </view>
                
                <view class="product-title-section">
                    <text class="product-name">{{ detail.mingcheng }}</text>
                    <view class="product-subtitle">
                        <text class="subtitle-text">{{ detail.fenleimingcheng }}</text>
                        <text class="divider">·</text>
                        <text class="subtitle-text">{{ detail.dianpumingcheng }}</text>
                    </view>
                </view>
            </view>

            <!-- 商品详情卡片 -->
            <view class="detail-card">
                <view class="card-header">
                    <text class="card-title">商品详情</text>
                    <view class="card-line"></view>
                </view>
                
                <view class="detail-grid">
                    <view class="detail-item" v-for="(item, key) in detailItems" :key="key">
                        <text class="detail-label">{{ item.label }}</text>
                        <text class="detail-value">{{ item.value }}</text>
                    </view>
                </view>
            </view>

            <!-- 商品介绍 -->
            <view class="description-card" v-if="detail.jieshao">
                <view class="card-header">
                    <text class="card-title">商品介绍</text>
                    <view class="card-line"></view>
                </view>
                <view class="description-content">
                    <rich-text :nodes="detail.jieshao"></rich-text>
                </view>
            </view>

            <!-- 商品详情 -->
            <view class="content-card" v-if="detail.xiangqing">
                <view class="card-header">
                    <text class="card-title">详细内容</text>
                    <view class="card-line"></view>
                </view>
                <view class="content-wrapper">
                    <rich-text :nodes="detail.xiangqing"></rich-text>
                </view>
            </view>

            <!-- 底部操作栏 -->
            <view class="product-actions">
                <uniGoodsNav class="goods-nav" :fill="true" :options="options" :buttonGroup="buttonGroup" 
                             @click="onClick" @buttonClick="buttonClick" />
            </view>

            <!-- 评论区域 -->
            <view class="comment-section">
                <view class="section-header">
                    <text class="section-icon cuIcon-message"></text>
                    <text class="section-title">商品评价</text>
                    <text class="comment-count">({{ commentList.length }})</text>
                </view>

                <!-- 发布评论 -->
                <view class="comment-input-card">
                    <view class="input-wrapper">
                        <text class="input-icon cuIcon-edit"></text>
                        <input class="comment-input" v-model="ruleForm.content" placeholder="分享你的使用体验..." 
                               placeholder-class="placeholder-text" maxlength="200"></input>
                        <text class="input-count">{{ ruleForm.content ? ruleForm.content.length : 0 }}/200</text>
                    </view>
                    <button class="publish-btn" @tap="sendReply" :disabled="!ruleForm.content">
                        <text class="btn-text">发布</text>
                    </button>
                </view>

                <!-- 评论列表 -->
                <view class="comment-list" v-if="commentList.length > 0">
                    <view class="comment-item" v-for="(item, index) in commentList" :key="index">
                        <view class="comment-avatar">
                            <image class="avatar-image" src="https://ossweb-img.qq.com/images/lol/img/champion/Morgana.png"></image>
                            <view class="avatar-badge" v-if="index < 3">
                                <text class="badge-text">热</text>
                            </view>
                        </view>
                        
                        <view class="comment-content">
                            <view class="comment-header">
                                <text class="comment-name">{{ item.nickname }}</text>
                                <text class="comment-time">{{ item.addtime }}</text>
                            </view>
                            
                            <view class="comment-text">
                                <text class="text-content">{{ item.content }}</text>
                            </view>
                            
                            <view class="comment-reply" v-if="item.reply">
                                <view class="reply-wrapper">
                                    <text class="reply-label">商家回复：</text>
                                    <text class="reply-content">{{ item.reply }}</text>
                                </view>
                            </view>
                            
                            <view class="comment-footer">
                                <text class="footer-text">{{ index + 1 }}楼</text>
                                <view class="footer-actions">
                                    <text class="action-item">
                                        <text class="cuIcon-appreciate"></text>
                                        <text class="action-text">赞</text>
                                    </text>
                                    <text class="action-item">
                                        <text class="cuIcon-message"></text>
                                        <text class="action-text">回复</text>
                                    </text>
                                </view>
                            </view>
                        </view>
                    </view>
                </view>

                <!-- 空评论状态 -->
                <view class="empty-comment" v-else>
                    <view class="empty-icon">
                        <text class="cuIcon-message"></text>
                    </view>
                    <text class="empty-text">暂无评价</text>
                    <text class="empty-subtext">快来发表第一条评论吧</text>
                </view>

                <!-- 加载状态 -->
                <view class="load-status" v-if="commentList.length > 0">
                    <text class="load-text" v-if="hasNext">加载更多评价...</text>
                    <text class="load-end" v-else>已经看到底了</text>
                </view>
            </view>
        </mescroll-uni>
    </view>
</template>

<script>
    import unisection from '../../components/goo-section/goo-section.vue'
    import uniNumberBox from "../../components/uni-number-box/uni-number-box.vue"
    import uniGoodsNav from '../../components/uni-goods-nav/uni-goods-nav.vue'
    import { uniList, uniListItem, uniListChat } from '@dcloudio/uni-ui'

    export default {
        data() {
            return {
                ruleForm: {},
                detail: {},
                swiperList: [],
                commentList: [],
                mescroll: null,
                downOption: {
                    auto: false
                },
                upOption: {
                    noMoreSize: 3,
                    textNoMore: '~ 没有更多了 ~',
                },
                hasNext: true,
                user: {},
                id: '',
                autoplaySwiper: true,
                intervalSwiper: 5000,
                btnColor: ['#409eff', '#67c23a', '#909399', '#e6a23c', '#f56c6c', '#356c6c', '#351c6c', '#f093a9', '#a7c23a', '#104eff', '#10441f', '#a21233', '#503319'],
                options: [
                    {
                        icon: 'star-filled',
                        text: '收藏'
                    },
                    {
                        icon: 'cart',
                        text: '购物车'
                    }
                ],
                buttonGroup: [
                    {
                        text: '加入购物车',
                        backgroundColor: '#ff6b6b',
                        color: '#fff'
                    },
                    {
                        text: '立即购买',
                        backgroundColor: '#ffa200',
                        color: '#fff'
                    }
                ]
            };
        },

        components: {
            unisection,
            uniGoodsNav,
            uniNumberBox,
        },

        computed: {
            detailItems() {
                return [
                    { label: '商品分类', value: this.detail.fenleimingcheng },
                    { label: '所属店铺', value: this.detail.dianpumingcheng },
                    { label: '商品库存', value: this.detail.alllimittimes + '件' },
                    { label: '商品编号', value: this.detail.id }
                ].filter(item => item.value);
            }
        },

        async onLoad(options) {
            this.id = options.id;
        },

        async onShow(options) {
            this.init();
            let table = uni.getStorageSync("nowTable");
            let res = await this.$api.session(table);
            this.user = res.data;
        },

        methods: {
            async init() {
                let res = await this.$api.info('shangpinguanli', this.id);
                this.detail = res.data;
                this.swiperList = this.detail.tupian ? this.detail.tupian.split(",") : [];
            },

            async sendReply() {
                let _this = this;
                let table = uni.getStorageSync("nowTable");
                let res = await _this.$api.session(table);
                _this.user = res.data;
                
                _this.ruleForm.refid = _this.id;
                _this.ruleForm.userid = _this.user.id;
                _this.ruleForm.nickname = uni.getStorageSync("nickname");

                if (_this.ruleForm.id) {
                    await _this.$api.update(`discussshangpinguanli`, _this.ruleForm);
                } else {
                    await _this.$api.add(`discussshangpinguanli`, _this.ruleForm);
                }
                
                _this.$utils.msg('评论成功');
                _this.ruleForm.content = "";
                _this.mescroll.resetUpScroll();
            },

            mescrollInit(mescroll) {
                this.mescroll = mescroll;
            },

            downCallback(mescroll) {
                this.hasNext = true;
                mescroll.resetUpScroll();
            },

            async upCallback(mescroll) {
                let res = await this.$api.list('discussshangpinguanli', {
                    page: mescroll.num,
                    limit: mescroll.size,
                    refid: this.id
                });
                
                if (mescroll.num == 1) this.commentList = [];
                this.commentList = this.commentList.concat(res.data.list);
                if (res.data.list.length == 0) this.hasNext = false;
                mescroll.endSuccess(mescroll.size, this.hasNext);
            },

            async onClick(e) {
                // 处理顶部选项点击（收藏、购物车）
                if (e.index === 0) {
                    // 收藏按钮
                    await this.addToCollection();
                } else if (e.index === 1) {
                    // 购物车按钮 - 跳转到购物车页面
                    uni.navigateTo({
                        url: '/pages/shop-cart/shop-cart'
                    });
                }
            },

            async buttonClick(e) {
                // 处理底部按钮点击（加入购物车、立即购买）
                if (e.index === 0) {
                    // 加入购物车按钮
                    await this.addToCart();
                } else if (e.index === 1) {
                    // 立即购买按钮
                    await this.buyNow();
                }
            },

            async addToCollection() {
                try {
                    let table = uni.getStorageSync("nowTable");
                    let res = await this.$api.session(table);
                    this.user = res.data;

                    // 检查是否已收藏
                    let checkRes = await this.$api.list('storeup', {
                        userid: this.user.id,
                        refid: this.id,
                        tablename: 'shangpinguanli'
                    });

                    if (checkRes.data.list && checkRes.data.list.length > 0) {
                        this.$utils.msg('已经收藏过了');
                        return;
                    }

                    // 添加收藏 - 包含所有必需字段
                    let collectionData = {
                        userid: this.user.id,
                        refid: this.id,
                        tablename: 'shangpinguanli',
                        name: this.detail.mingcheng,
                        picture: this.swiperList[0] || '',
                        type: 1
                    };

                    await this.$api.add('storeup', collectionData);
                    this.$utils.msg('收藏成功');
                } catch (error) {
                    this.$utils.msg('收藏失败');
                    console.error('收藏错误:', error);
                }
            },

            async addToCart() {
                try {
                    let table = uni.getStorageSync("nowTable");
                    let res = await this.$api.session(table);
                    this.user = res.data;

                    // 检查购物车中是否已存在该商品
                    let checkRes = await this.$api.list('cart', {
                        userid: this.user.id,
                        goodid: this.id,
                        tablename: 'shangpinguanli'
                    });

                    if (checkRes.data.list && checkRes.data.list.length > 0) {
                        // 商品已在购物车，更新数量
                        let cartItem = checkRes.data.list[0];
                        cartItem.buynumber = parseInt(cartItem.buynumber) + 1;
                        await this.$api.update('cart', cartItem);
                    } else {
                        // 添加新商品到购物车
                        let cartData = {
                            userid: this.user.id,
                            goodid: this.id,
                            tablename: 'shangpinguanli',
                            goodname: this.detail.mingcheng,
                            picture: this.swiperList[0] || '',
                            price: this.detail.price,
                            buynumber: 1
                        };
                        await this.$api.add('cart', cartData);
                    }

                    this.$utils.msg('已加入购物车');
                } catch (error) {
                    this.$utils.msg('加入购物车失败');
                    console.error('购物车错误:', error);
                }
            },

            async buyNow() {
                try {
                    let table = uni.getStorageSync("nowTable");
                    let res = await this.$api.session(table);
                    this.user = res.data;

                    // 准备订单商品信息
                    let orderGoods = [{
                        tablename: 'shangpinguanli',
                        goodid: this.id,
                        goodname: this.detail.mingcheng,
                        picture: this.swiperList[0] || '',
                        price: this.detail.price,
                        buynumber: 1,
                        total: this.detail.price
                    }];

                    // 保存订单信息到本地存储
                    uni.setStorageSync('orderGoods', orderGoods);

                    // 跳转到订单确认页面
                    uni.navigateTo({
                        url: '/pages/shop-order-confirm/shop-order-confirm?type=1&seat=0'
                    });
                } catch (error) {
                    this.$utils.msg('跳转失败');
                    console.error('立即购买错误:', error);
                }
            }
        }
    }
</script>

<style lang="scss">
    page {
        background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
        min-height: 100vh;
    }

    /* 商品头部轮播 */
    .product-header {
        position: relative;
        height: 500rpx;
        overflow: hidden;
        
        .product-swiper {
            width: 100%;
            height: 100%;
        }
        
        .swiper-image {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        
        .swiper-indicator {
            position: absolute;
            bottom: 30rpx;
            right: 30rpx;
            background: rgba(0, 0, 0, 0.5);
            backdrop-filter: blur(10px);
            padding: 8rpx 20rpx;
            border-radius: 20rpx;
            
            .indicator-text {
                color: #fff;
                font-size: 24rpx;
                font-weight: 500;
            }
        }
    }

    /* 商品信息 */
    .product-info {
        background: #fff;
        margin: 20rpx;
        border-radius: 20rpx;
        padding: 30rpx;
        box-shadow: 0 6rpx 30rpx rgba(0, 0, 0, 0.06);
        animation: slideUp 0.5s ease;
    }

    @keyframes slideUp {
        from {
            opacity: 0;
            transform: translateY(30px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }

    .product-price-section {
        display: flex;
        align-items: baseline;
        margin-bottom: 20rpx;
        
        .price-symbol {
            color: #ff6b6b;
            font-size: 36rpx;
            font-weight: 600;
        }
        
        .price-number {
            color: #ff6b6b;
            font-size: 56rpx;
            font-weight: 700;
            margin: 0 15rpx;
            text-shadow: 0 2rpx 4rpx rgba(255, 107, 107, 0.1);
        }
        
        .price-tag {
            background: linear-gradient(135deg, #ff6b6b 0%, #ff8e53 100%);
            padding: 6rpx 16rpx;
            border-radius: 12rpx;
            
            .tag-text {
                color: #fff;
                font-size: 22rpx;
                font-weight: 600;
                letter-spacing: 1rpx;
            }
        }
    }

    .product-title-section {
        .product-name {
            display: block;
            font-size: 36rpx;
            font-weight: 700;
            color: #2c3e50;
            line-height: 1.4;
            margin-bottom: 15rpx;
            letter-spacing: -0.5rpx;
        }
        
        .product-subtitle {
            display: flex;
            align-items: center;
            gap: 10rpx;
            
            .subtitle-text {
                font-size: 26rpx;
                color: #7f8c8d;
                font-weight: 500;
            }
            
            .divider {
                color: #bdc3c7;
                font-size: 28rpx;
            }
        }
    }

    /* 详情卡片 */
    .detail-card,
    .description-card,
    .content-card {
        background: #fff;
        margin: 20rpx;
        border-radius: 20rpx;
        padding: 30rpx;
        box-shadow: 0 6rpx 30rpx rgba(0, 0, 0, 0.06);
        animation: slideUp 0.5s ease 0.1s both;
    }

    .card-header {
        margin-bottom: 30rpx;
        position: relative;
        
        .card-title {
            font-size: 32rpx;
            font-weight: 700;
            color: #2c3e50;
            position: relative;
            padding-left: 15rpx;
            
            &::before {
                content: '';
                position: absolute;
                left: 0;
                top: 50%;
                transform: translateY(-50%);
                width: 6rpx;
                height: 28rpx;
                background: linear-gradient(135deg, #ff6b6b 0%, #ff8e53 100%);
                border-radius: 3rpx;
            }
        }
        
        .card-line {
            position: absolute;
            bottom: -10rpx;
            left: 0;
            width: 80rpx;
            height: 4rpx;
            background: linear-gradient(90deg, #ff6b6b, transparent);
            border-radius: 2rpx;
        }
    }

    .detail-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 20rpx;
        
        .detail-item {
            background: #f8f9fa;
            padding: 20rpx;
            border-radius: 12rpx;
            border-left: 4rpx solid #ff6b6b;
            transition: all 0.3s ease;
            
            &:active {
                transform: translateY(-2px);
                box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.1);
            }
        }
        
        .detail-label {
            display: block;
            font-size: 24rpx;
            color: #95a5a6;
            margin-bottom: 8rpx;
            font-weight: 500;
        }
        
        .detail-value {
            display: block;
            font-size: 28rpx;
            color: #2c3e50;
            font-weight: 600;
        }
    }

    .description-content,
    .content-wrapper {
        font-size: 28rpx;
        color: #2c3e50;
        line-height: 1.6;
        
        >>> img {
            max-width: 100% !important;
            height: auto !important;
            border-radius: 12rpx;
            margin: 10rpx 0;
            box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.1);
        }
        
        >>> p {
            margin: 15rpx 0;
        }
    }

    /* 评论区域 */
    .comment-section {
        background: #fff;
        margin: 20rpx;
        border-radius: 20rpx;
        padding: 30rpx;
        box-shadow: 0 6rpx 30rpx rgba(0, 0, 0, 0.06);
        animation: slideUp 0.5s ease 0.2s both;
    }

    .section-header {
        display: flex;
        align-items: center;
        margin-bottom: 30rpx;
        
        .section-icon {
            font-size: 36rpx;
            color: #ff6b6b;
            margin-right: 12rpx;
        }
        
        .section-title {
            font-size: 32rpx;
            font-weight: 700;
            color: #2c3e50;
            margin-right: 10rpx;
        }
        
        .comment-count {
            font-size: 26rpx;
            color: #95a5a6;
        }
    }

    .comment-input-card {
        background: #f8f9fa;
        border-radius: 16rpx;
        padding: 25rpx;
        margin-bottom: 30rpx;
        
        .input-wrapper {
            display: flex;
            align-items: center;
            background: #fff;
            border-radius: 12rpx;
            padding: 0 20rpx;
            margin-bottom: 20rpx;
            border: 2rpx solid transparent;
            transition: all 0.3s ease;
            
            &:focus-within {
                border-color: #ff6b6b;
                box-shadow: 0 4rpx 12rpx rgba(255, 107, 107, 0.1);
            }
        }
        
        .input-icon {
            font-size: 28rpx;
            color: #ff6b6b;
            margin-right: 15rpx;
        }
        
        .comment-input {
            flex: 1;
            height: 80rpx;
            font-size: 28rpx;
            color: #2c3e50;
        }
        
        .placeholder-text {
            color: #bdc3c7;
            font-size: 28rpx;
        }
        
        .input-count {
            font-size: 22rpx;
            color: #95a5a6;
        }
        
        .publish-btn {
            background: linear-gradient(135deg, #ff6b6b 0%, #ff8e53 100%);
            color: #fff;
            border: none;
            border-radius: 12rpx;
            height: 80rpx;
            font-size: 28rpx;
            font-weight: 600;
            box-shadow: 0 4rpx 15rpx rgba(255, 107, 107, 0.3);
            transition: all 0.3s ease;
            
            &[disabled] {
                opacity: 0.6;
                filter: grayscale(0.5);
            }
            
            &:active {
                transform: scale(0.98);
                box-shadow: 0 2rpx 10rpx rgba(255, 107, 107, 0.2);
            }
            
            .btn-text {
                font-weight: 600;
                letter-spacing: 1rpx;
            }
        }
    }

    .comment-list {
        .comment-item {
            display: flex;
            padding: 25rpx 0;
            border-bottom: 1rpx solid #f0f0f0;
            
            &:last-child {
                border-bottom: none;
            }
        }
        
        .comment-avatar {
            position: relative;
            flex-shrink: 0;
            margin-right: 20rpx;
            
            .avatar-image {
                width: 80rpx;
                height: 80rpx;
                border-radius: 50%;
                box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.1);
            }
            
            .avatar-badge {
                position: absolute;
                top: -5rpx;
                right: -5rpx;
                width: 30rpx;
                height: 30rpx;
                background: linear-gradient(135deg, #ff6b6b 0%, #ff8e53 100%);
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                border: 2rpx solid #fff;
                
                .badge-text {
                    color: #fff;
                    font-size: 18rpx;
                    font-weight: 600;
                }
            }
        }
        
        .comment-content {
            flex: 1;
        }
        
        .comment-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15rpx;
            
            .comment-name {
                font-size: 28rpx;
                font-weight: 600;
                color: #2c3e50;
            }
            
            .comment-time {
                font-size: 22rpx;
                color: #95a5a6;
            }
        }
        
        .comment-text {
            margin-bottom: 15rpx;
            
            .text-content {
                font-size: 26rpx;
                color: #34495e;
                line-height: 1.6;
            }
        }
        
        .comment-reply {
            background: #f8f9fa;
            border-radius: 12rpx;
            padding: 15rpx;
            margin-bottom: 15rpx;
            
            .reply-wrapper {
                display: flex;
                flex-direction: column;
            }
            
            .reply-label {
                font-size: 24rpx;
                color: #ff6b6b;
                font-weight: 600;
                margin-bottom: 5rpx;
            }
            
            .reply-content {
                font-size: 26rpx;
                color: #555;
            }
        }
        
        .comment-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            
            .footer-text {
                font-size: 22rpx;
                color: #95a5a6;
            }
            
            .footer-actions {
                display: flex;
                gap: 25rpx;
                
                .action-item {
                    display: flex;
                    align-items: center;
                    gap: 8rpx;
                    font-size: 24rpx;
                    color: #95a5a6;
                    
                    .cuIcon {
                        font-size: 26rpx;
                    }
                    
                    .action-text {
                        font-size: 24rpx;
                    }
                }
            }
        }
    }

    .empty-comment {
        text-align: center;
        padding: 60rpx 0;
        
        .empty-icon {
            width: 100rpx;
            height: 100rpx;
            background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 25rpx;
            
            .cuIcon-message {
                font-size: 48rpx;
                color: #bdc3c7;
            }
        }
        
        .empty-text {
            display: block;
            font-size: 30rpx;
            color: #95a5a6;
            margin-bottom: 10rpx;
            font-weight: 500;
        }
        
        .empty-subtext {
            display: block;
            font-size: 26rpx;
            color: #bdc3c7;
        }
    }

    .load-status {
        text-align: center;
        padding: 40rpx 0;
        position: relative;
        
        &::before {
            content: '';
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 100rpx;
            height: 1px;
            background: linear-gradient(90deg, transparent, #e9ecef, transparent);
        }
        
        .load-text {
            font-size: 26rpx;
            color: #95a5a6;
            animation: pulse 1.5s infinite;
        }
        
        .load-end {
            font-size: 26rpx;
            color: #bdc3c7;
        }
    }

    @keyframes pulse {
        0%, 100% { opacity: 0.6; }
        50% { opacity: 1; }
    }

    /* 底部操作栏 */
    .product-actions {
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        background: #fff;
        box-shadow: 0 -4rpx 20rpx rgba(0, 0, 0, 0.1);
        padding: 20rpx;
        z-index: 100;
        
        .goods-nav {
            >>> .uni-goods-nav {
                border-radius: 25rpx;
                overflow: hidden;
                box-shadow: 0 4rpx 20rpx rgba(0, 0, 0, 0.08);
            }
        }
    }

    /* 响应式调整 */
    @media (max-width: 750rpx) {
        .product-header {
            height: 450rpx;
        }
        
        .detail-grid {
            grid-template-columns: 1fr;
        }
    }
</style>