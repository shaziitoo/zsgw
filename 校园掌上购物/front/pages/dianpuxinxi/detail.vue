<template>
    <view>
        <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback" :bottom="150">
            <!-- 顶部图片 -->
            <view class="top-image-container">
                <image class="top-image" mode="widthFix" :src="detail.tupian"></image>
                <view class="image-overlay"></view>
            </view>

            <!-- 店铺信息卡片 -->
            <view class="info-card">
                <view class="info-section">
                    <view class="section-header">
                        <text class="cuIcon-shop section-icon"></text>
                        <text class="section-title">店铺信息</text>
                    </view>
                    <view class="info-grid">
                        <view class="info-item">
                            <text class="info-label">店铺名称</text>
                            <text class="info-value">{{detail.dianpumingcheng || '暂无'}}</text>
                        </view>
                        
                        <view class="info-item">
                            <text class="info-label">商家电话</text>
                            <text class="info-value">{{detail.shangjiadianhua || '暂无'}}</text>
                        </view>
                    </view>
                </view>

                <view class="info-section">
                    <view class="section-header">
                        <text class="cuIcon-info section-icon"></text>
                        <text class="section-title">店铺简介</text>
                    </view>
                    <view class="info-content">
                        <text class="content-text">{{detail.dianpujianjie || '暂无简介'}}</text>
                    </view>
                </view>

                <view class="info-section">
                    <view class="section-header">
                        <text class="cuIcon-location section-icon"></text>
                        <text class="section-title">店铺地址</text>
                    </view>
                    <view class="info-item address-item">
                        <text class="info-label">详细地址</text>
                        <text class="info-value address-value">{{detail.dianpudizhi || '暂无地址'}}</text>
                    </view>
                    <view class="coordinate-row">
                        <view class="coordinate-item">
                            <text class="coordinate-label">纬度</text>
                            <text class="coordinate-value">{{detail.lat || '未知'}}</text>
                        </view>
                        <view class="coordinate-item">
                            <text class="coordinate-label">经度</text>
                            <text class="coordinate-value">{{detail.lnt || '未知'}}</text>
                        </view>
                    </view>
                </view>
            </view>

            <!-- 操作区域 -->
            <view class="action-section">
                <view class="section-header">
                    <text class="cuIcon-menu section-icon"></text>
                    <text class="section-title">操作</text>
                </view>
                <view class="action-buttons">
                    <button class="action-btn collect-btn" @click="sc()">
                        <text class="cuIcon-favorfill btn-icon"></text>
                        <text class="btn-text">收藏</text>
                    </button>
                    
                </view>
            </view>

            <!-- 评论区域 -->
            <view class="comment-section">
                <view class="section-header comment-header">
                    <text class="cuIcon-messagefill section-icon"></text>
                    <text class="section-title">评论内容</text>
                    <text class="comment-count">({{commentList.length || 0}})</text>
                </view>

                <!-- 发表评论 -->
                <view class="comment-input-card">
                    <view class="input-container">
                        <text class="cuIcon-edit input-icon"></text>
                        <input 
                            class="comment-input" 
                            placeholder="写下你的评论..." 
                            v-model="ruleForm.content" 
                            placeholder-class="input-placeholder"
                            maxlength="200"
                        ></input>
                        <text class="input-count" v-if="ruleForm.content">{{ruleForm.content.length}}/200</text>
                    </view>
                    <button class="publish-btn" @tap="sendReply" :disabled="!ruleForm.content">
                        <text class="btn-text">发布</text>
                    </button>
                </view>

                <!-- 评论列表 -->
                <view class="comment-list" v-if="commentList.length > 0">
                    <view class="comment-item" v-for="(item,index) in commentList" :key="index">
                        <view class="comment-avatar">
                            <view class="avatar-placeholder">
                                <text class="cuIcon-people"></text>
                            </view>
                        </view>
                        <view class="comment-content">
                            <view class="comment-header">
                                <text class="comment-author">{{item.nickname || '匿名用户'}}</text>
                                <text class="comment-time">{{index + 1}}楼</text>
                            </view>
                            <view class="comment-text">
                                {{item.content}}
                            </view>
                            <view class="reply-section" v-if="item.reply">
                                <view class="reply-label">
                                    <text class="cuIcon-forward"></text>
                                    <text>回复：</text>
                                </view>
                                <view class="reply-content">{{item.reply}}</view>
                            </view>
                        </view>
                    </view>
                </view>

                <!-- 空评论状态 -->
                <view class="empty-comment" v-else>
                    <view class="empty-icon">
                        <text class="cuIcon-message"></text>
                    </view>
                    <text class="empty-text">暂无评论</text>
                    <text class="empty-subtext">快来发表第一条评论吧</text>
                </view>

                <!-- 加载状态 -->
                <view class="loading-wrapper" v-if="commentList.length > 0">
                    <text class="loading-text" v-if="hasNext">加载更多...</text>
                    <text class="no-more-text" v-else>没有更多评论了</text>
                </view>
            </view>
        </mescroll-uni>
    </view>
</template>

<script>
    import unisection from '../../components/goo-section/goo-section.vue'
    import HmNewsDetail from "../../components/hm-news-detail/index.vue"
    import KaiComment from "../../components/kai-comment/kai-comment.vue"

    export default {
        data() {
            return {
                ruleForm: {},
                autoplaySwiper: false ? true : false,
                intervalSwiper: false ? $template2.front.base.swiper.autoplay.delay : 5000,
                btnColor: ['#409eff', '#67c23a', '#909399', '#e6a23c', '#f56c6c', '#356c6c', '#351c6c', '#f093a9', '#a7c23a', '#104eff', '#10441f', '#a21233', '#503319'],
                id: '',
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
                sfshIndex: -1,
                sfshOptions: ['通过', '不通过'],
                count: 0,
                storeupFlag: 0,
            }
        },
        components: {
            unisection,
            HmNewsDetail,
            KaiComment,
        },
        computed: {
            SecondToDate: function () {
                var time = this.count;
                if (null != time && "" != time) {
                    if (time > 60 && time < 60 * 60) {
                        time =
                                parseInt(time / 60.0) +
                                "分钟" +
                                parseInt((parseFloat(time / 60.0) - parseInt(time / 60.0)) * 60) +
                                "秒";
                    } else if (time >= 60 * 60 && time < 60 * 60 * 24) {
                        time =
                                parseInt(time / 3600.0) +
                                "小时" +
                                parseInt(
                                        (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                ) +
                                "分钟" +
                                parseInt(
                                        (parseFloat(
                                                (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                                ) -
                                                parseInt(
                                                        (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                                )) *
                                        60
                                ) +
                                "秒";
                    } else if (time >= 60 * 60 * 24) {
                        time =
                                parseInt(time / 3600.0 / 24) +
                                "天" +
                                parseInt(
                                        (parseFloat(time / 3600.0 / 24) - parseInt(time / 3600.0 / 24)) *
                                        24
                                ) +
                                "小时" +
                                parseInt(
                                        (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                ) +
                                "分钟" +
                                parseInt(
                                        (parseFloat(
                                                (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                                ) -
                                                parseInt(
                                                        (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                                )) *
                                        60
                                ) +
                                "秒";
                    } else {
                        time = parseInt(time) + "秒";
                    }
                }
                return time;
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
            this.btnColor = this.btnColor.sort(() => {
                return (0.5 - Math.random());
            });
        },
        destroyed: function () {
            window.clearInterval(this.inter);
        },
        methods: {
            async sendReply() {
                let _this = this;
                let table = uni.getStorageSync("nowTable");
                let res = await _this.$api.session(table);
                _this.user = res.data;
                _this.ruleForm.refid = _this.id;
                _this.ruleForm.userid = _this.user.id;
                _this.ruleForm.nickname = uni.getStorageSync("nickname");

                if (_this.ruleForm.id) {
                    await _this.$api.update(`discussdianpuxinxi`, _this.ruleForm);
                } else {
                    await _this.$api.add(`discussdianpuxinxi`, _this.ruleForm);
                }
                _this.$utils.msg('提交成功');
                _this.ruleForm.content = "";
                _this.mescroll.resetUpScroll();
            },
            async getStoreup() {
                let params = {
                    page: 1,
                    limit: 1,
                    refid: this.id,
                    tablename: 'dianpuxinxi',
                    userid: this.user.id,
                    type: 1,
                }
                let res = await this.$api.list(`storeup`, params);
                this.storeupFlag = res.data.list.length;
            },
            async shoucang() {
                let _this = this;
                let params = {
                    page: 1,
                    limit: 1,
                    refid: _this.detail.id,
                    tablename: 'dianpuxinxi',
                    userid: _this.user.id,
                    type: 1,
                }
                let res = await _this.$api.list(`storeup`, params);
                if (res.data.list.length == 1) {
                    let storeupId = res.data.list[0].id;
                    uni.showModal({
                        title: '提示',
                        content: '是否取消',
                        success: async function (res) {
                            if (res.confirm) {
                                await _this.$api.del('storeup', JSON.stringify([storeupId]));
                                _this.$utils.msg('取消成功');
                                _this.getStoreup();
                            }
                        }
                    });
                    return;
                }
                uni.showModal({
                    title: '提示',
                    content: '是否收藏',
                    success: async function (res) {
                        if (res.confirm) {
                            await _this.$api.add('storeup', {
                                userid: _this.user.id,
                                name: _this.detail.dianpumingcheng,
                                name: _this.detail.nicheng,
                                picture: _this.swiperList[0],
                                refid: _this.detail.id,
                                tablename: 'dianpuxinxi'
                            });
                            _this.$utils.msg('收藏成功');
                            _this.getStoreup();
                        }
                    }
                });
            },
            async onAcrossTap(acrossTable, statusColumnName, tips, statusColumnValue) {
                let boolean = true
                uni.setStorageSync('crossTable', `dianpuxinxi`);
                uni.setStorageSync('statusColumnName', statusColumnName);
                uni.setStorageSync('statusColumnValue', statusColumnValue);
                uni.setStorageSync('crossObj', JSON.stringify(this.detail));
                if (statusColumnName != '') {
                    var obj = JSON.parse(uni.getStorageSync('crossObj'));
                    for (var o in obj) {
                        if (o == statusColumnName && obj[o] == statusColumnValue) {
                            this.$utils.msg(tips);
                            boolean = false
                        }
                    }
                }
                if (boolean) {
                    this.$utils.jump(`../${acrossTable}/add-or-update?cross=true`);
                }
            },
            async init() {
                let res = await this.$api.info('dianpuxinxi', this.id);
                this.detail = res.data;
                this.swiperList = this.detail.tupian ? this.detail.tupian.split(",") : [];
                this.detail.dianpujianjie = this.detail.dianpujianjie.replace(/img src/gi, "img style=\"width:100%;\" src");
            },
            mescrollInit(mescroll) {
                this.mescroll = mescroll;
            },
            downCallback(mescroll) {
                this.hasNext = true
                mescroll.resetUpScroll()
            },
            async upCallback(mescroll) {
                let res = await this.$api.list('discussdianpuxinxi', {
                    page: mescroll.num,
                    limit: mescroll.size,
                    refid: this.id
                });
                if (mescroll.num == 1) this.commentList = [];
                this.commentList = this.commentList.concat(res.data.list);
                if (res.data.list.length == 0) this.hasNext = false;
                mescroll.endSuccess(mescroll.size, this.hasNext);
            },
            async sc() {
                let _this = this;
                let params = {
                    page: 1,
                    limit: 1,
                    refid: _this.detail.id,
                    tablename: 'dianpuxinxi',
                    userid: _this.user.id,
                    type: 1,
                }
                let res = await _this.$api.list(`storeup`, params);
                if (res.data.list.length == 1) {
                    let storeupId = res.data.list[0].id;
                    uni.showModal({
                        title: '提示',
                        content: '是否取消',
                        success: async function (res) {
                            if (res.confirm) {
                                await _this.$api.del('storeup', JSON.stringify([storeupId]));
                                _this.$utils.msg('取消成功');
                            }
                        }
                    });
                    return;
                }
                uni.showModal({
                    title: '提示',
                    content: '是否收藏',
                    success: async function (res) {
                        if (res.confirm) {
                            await _this.$api.add('storeup', {
                                userid: _this.user.id,
                                name: _this.detail.dianpumingcheng,
                                name: _this.detail.nicheng,
                                picture: _this.detail.tupian,
                                refid: _this.detail.id,
                                tablename: 'dianpuxinxi'
                            });
                            _this.$utils.msg('收藏成功');
                        }
                    }
                });
            },
            async onCartTap() {
                if (uni.getStorageSync(`cart${this.detail.id}${this.user.id}`)) {
                    this.$utils.msg('该商品已添加到购物车')
                    return
                }
                await this.$api.add('cart', {
                    tablename: 'dianpuxinxi',
                    goodid: this.detail.id,
                    goodname: this.detail.dianpumingcheng,
                    goodname: this.detail.nicheng,
                    picture: this.swiperList[0],
                    buynumber: 1,
                    userid: this.user.id,
                    price: this.detail.price,
                    discountprice: this.detail.vipprice
                });
                uni.setStorageSync(`cart${this.detail.id}${this.user.id}`, true);
                this.$utils.msg('添加到购物车成功')
            },
            onBuyTap() {
                uni.setStorageSync('orderGoods', [{
                    tablename: 'dianpuxinxi',
                    goodid: this.detail.id,
                    name: this.detail.dianpumingcheng,
                    name: this.detail.nicheng,
                    picture: this.detail.tupian,
                    goodname: this.detail.dianpumingcheng,
                    goodname: this.detail.nicheng,
                    buynumber: 1,
                    price: this.detail.price,
                    discountprice: this.detail.vipprice
                }])
                this.$utils.jump('../shop-order-confirm/shop-order-confirm?type=1');
            },
            onChatTap() {
                this.$utils.jump('../chat/chat')
            },
            download(url) {
                let _this = this;
                uni.downloadFile({
                    url: url,
                    success: (data) => {
                        if (data.statusCode === 200) {
                            uni.getFileSystemManager().saveFile({
                                tempFilePath: data.tempFilePath,
                                success: function (res) {
                                    uni.showToast({
                                        icon: 'none',
                                        mask: true,
                                        title: '文件已保存：' + res.savedFilePath,
                                        duration: 3000,
                                    });
                                    setTimeout(() => {
                                        uni.openDocument({
                                            filePath: res.savedFilePath,
                                            success: function (res) {}
                                        });
                                    }, 3000)
                                }
                            });
                        }
                    },
                    fail: (err) => {
                        console.log(err);
                        uni.showToast({
                            icon: 'none',
                            mask: true,
                            title: '失败请重新下载',
                        });
                    },
                });
            },
            onCartTabTap() {
                this.$utils.tab('../shop-cart/shop-cart')
            },
            onCommentTap() {
                this.$utils.jump(`../discussdianpuxinxi/add-or-update?refid=${this.id}`)
            },
            async zan() {
                let _this = this;
                uni.showModal({
                    title: '提示',
                    content: '是否点赞',
                    success: async function (res) {
                        if (res.confirm) {
                            _this.detail.thumbsupnum = parseInt(_this.detail.thumbsupnum) + 1;
                            await _this.$api.update('dianpuxinxi', _this.detail);
                            _this.$utils.msg('点赞成功');
                        }
                    }
                });
            },
            async cai() {
                let _this = this;
                uni.showModal({
                    title: '提示',
                    content: '是否点踩',
                    success: async function (res) {
                        if (res.confirm) {
                            _this.detail.crazilynum = parseInt(_this.detail.crazilynum) + 1;
                            await _this.$api.update('dianpuxinxi', _this.detail);
                            _this.$utils.msg('点踩成功');
                        }
                    }
                });
            },
            onSHTap() {
                this.$refs.popup.open()
            },
            async onFinishTap() {
                if (!this.detail.sfsh) {
                    this.$utils.msg('请选择审核状态');
                    return
                }
                if (!this.detail.shhf) {
                    this.$utils.msg('请填写审核回复');
                    return
                }
                if (this.detail.sfsh == "通过") {
                    this.detail.sfsh = '是'
                }
                if (this.detail.sfsh == "不通过") {
                    this.detail.sfsh = '否'
                }
                await this.$api.update('dianpuxinxi', this.detail);
                this.$utils.msg('审核成功');
                this.$refs.popup.close();
            },
            onCloseWinTap() {
                this.$refs.popup.close()
            },
            sfshChange(e) {
                console.log(this.detail)
                this.sfshIndex = e.target.value
                this.detail.sfsh = this.sfshOptions[this.sfshIndex]
            }
        }
    }
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
            background: radial-gradient(circle at 10% 20%, rgba(74, 108, 247, 0.05) 0%, transparent 40%),
                        radial-gradient(circle at 90% 80%, rgba(255, 107, 107, 0.03) 0%, transparent 40%);
            pointer-events: none;
            z-index: 0;
        }
    }

    .top-image-container {
        position: relative;
        width: 100%;
        height: 420rpx;
        overflow: hidden;
        
        .top-image {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.6s ease;
        }
        
        .image-overlay {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            height: 100rpx;
            background: linear-gradient(to top, rgba(0, 0, 0, 0.3), transparent);
            pointer-events: none;
        }
        
        &:active .top-image {
            transform: scale(1.05);
        }
    }

    .info-card {
        background: rgba(255, 255, 255, 0.95);
        backdrop-filter: blur(20px);
        border-radius: 24rpx;
        margin: -40rpx 30rpx 30rpx;
        padding: 30rpx;
        box-shadow: 0 8rpx 32rpx rgba(0, 0, 0, 0.08);
        border: 1rpx solid rgba(255, 255, 255, 0.2);
        position: relative;
        z-index: 1;
        animation: slideUp 0.5s ease-out;
        
        @keyframes slideUp {
            from {
                opacity: 0;
                transform: translateY(40rpx);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    }

    .info-section {
        margin-bottom: 40rpx;
        
        &:last-child {
            margin-bottom: 0;
        }
    }

    .section-header {
        display: flex;
        align-items: center;
        gap: 16rpx;
        margin-bottom: 24rpx;
        padding-bottom: 16rpx;
        border-bottom: 2rpx solid rgba(74, 108, 247, 0.1);
        
        .section-icon {
            font-size: 36rpx;
            color: #4a6cf7;
        }
        
        .section-title {
            font-size: 32rpx;
            font-weight: 700;
            color: #2c3e50;
            letter-spacing: 0.5rpx;
        }
    }

    .info-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 20rpx;
    }

    .info-item {
        background: rgba(74, 108, 247, 0.05);
        border-radius: 16rpx;
        padding: 20rpx;
        border: 1rpx solid rgba(74, 108, 247, 0.1);
        transition: all 0.3s ease;
        
        &:hover {
            background: rgba(74, 108, 247, 0.08);
            transform: translateY(-2rpx);
        }
        
        .info-label {
            display: block;
            font-size: 24rpx;
            color: #7e8c9a;
            margin-bottom: 8rpx;
            font-weight: 500;
        }
        
        .info-value {
            display: block;
            font-size: 28rpx;
            color: #2c3e50;
            font-weight: 600;
            line-height: 1.4;
        }
    }

    .info-content {
        background: rgba(74, 108, 247, 0.05);
        border-radius: 16rpx;
        padding: 24rpx;
        border: 1rpx solid rgba(74, 108, 247, 0.1);
        
        .content-text {
            font-size: 28rpx;
            color: #4a5568;
            line-height: 1.6;
        }
    }

    .address-item {
        grid-column: 1 / -1;
        margin-bottom: 20rpx;
        
        .address-value {
            color: #4a6cf7;
            font-weight: 600;
        }
    }

    .coordinate-row {
        display: flex;
        gap: 20rpx;
        
        .coordinate-item {
            flex: 1;
            background: rgba(156, 163, 175, 0.1);
            border-radius: 16rpx;
            padding: 20rpx;
            text-align: center;
            
            .coordinate-label {
                display: block;
                font-size: 24rpx;
                color: #7e8c9a;
                margin-bottom: 8rpx;
            }
            
            .coordinate-value {
                display: block;
                font-size: 28rpx;
                color: #4a5568;
                font-weight: 600;
                font-family: 'Courier New', monospace;
            }
        }
    }

    .action-section {
        background: rgba(255, 255, 255, 0.95);
        backdrop-filter: blur(20px);
        border-radius: 24rpx;
        margin: 30rpx;
        padding: 30rpx;
        box-shadow: 0 8rpx 32rpx rgba(0, 0, 0, 0.08);
        border: 1rpx solid rgba(255, 255, 255, 0.2);
    }

    .action-buttons {
        display: flex;
        gap: 24rpx;
        margin-top: 20rpx;
        
        .action-btn {
            flex: 1;
            height: 88rpx;
            border: none;
            border-radius: 20rpx;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 12rpx;
            font-size: 28rpx;
            font-weight: 600;
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
                
                &::before {
                    left: 100%;
                }
            }
            
            .btn-icon {
                font-size: 32rpx;
            }
            
            .btn-text {
                font-weight: 600;
                letter-spacing: 0.5rpx;
            }
        }
        
        .collect-btn {
            background: linear-gradient(135deg, #ff6b6b 0%, #ff8e53 100%) !important;
            color: #fff !important;
            box-shadow: 0 6rpx 24rpx rgba(255, 107, 107, 0.3);
            
            &:active {
                box-shadow: 0 12rpx 32rpx rgba(255, 107, 107, 0.4);
            }
        }
        
        .consult-btn {
            background: linear-gradient(135deg, #4a6cf7 0%, #6c8cff 100%) !important;
            color: #fff !important;
            box-shadow: 0 6rpx 24rpx rgba(74, 108, 247, 0.3);
            
            &:active {
                box-shadow: 0 12rpx 32rpx rgba(74, 108, 247, 0.4);
            }
        }
    }

    .comment-section {
        background: rgba(255, 255, 255, 0.95);
        backdrop-filter: blur(20px);
        border-radius: 24rpx;
        margin: 30rpx;
        padding: 30rpx;
        box-shadow: 0 8rpx 32rpx rgba(0, 0, 0, 0.08);
        border: 1rpx solid rgba(255, 255, 255, 0.2);
    }

    .comment-header {
        .comment-count {
            font-size: 24rpx;
            color: #7e8c9a;
            margin-left: 8rpx;
        }
    }

    .comment-input-card {
        background: rgba(255, 255, 255, 0.9);
        border-radius: 20rpx;
        padding: 24rpx;
        margin-bottom: 30rpx;
        border: 1rpx solid rgba(74, 108, 247, 0.1);
        transition: all 0.3s ease;
        
        &:focus-within {
            border-color: rgba(74, 108, 247, 0.3);
            box-shadow: 0 8rpx 32rpx rgba(74, 108, 247, 0.1);
        }
    }

    .input-container {
        display: flex;
        align-items: center;
        gap: 16rpx;
        margin-bottom: 20rpx;
        position: relative;
        
        .input-icon {
            font-size: 32rpx;
            color: #4a6cf7;
            flex-shrink: 0;
        }
        
        .comment-input {
            flex: 1;
            height: 60rpx;
            font-size: 28rpx;
            color: #2c3e50;
            background: transparent;
            
            .input-placeholder {
                color: #a0aec0;
                font-weight: 400;
            }
        }
        
        .input-count {
            position: absolute;
            right: 0;
            bottom: -30rpx;
            font-size: 22rpx;
            color: #a0aec0;
        }
    }

    .publish-btn {
        background: linear-gradient(135deg, #4a6cf7 0%, #6c8cff 100%) !important;
        color: #fff !important;
        border: none;
        border-radius: 16rpx;
        height: 64rpx;
        width: 100%;
        font-size: 28rpx;
        font-weight: 600;
        transition: all 0.3s ease;
        
        &:disabled {
            opacity: 0.6;
            transform: none !important;
        }
        
        &:active:not(:disabled) {
            transform: scale(0.98);
            opacity: 0.9;
        }
        
        .btn-text {
            font-weight: 600;
            letter-spacing: 0.5rpx;
        }
    }

    .comment-list {
        .comment-item {
            display: flex;
            gap: 20rpx;
            padding: 30rpx 0;
            border-bottom: 1rpx solid rgba(226, 232, 240, 0.8);
            
            &:last-child {
                border-bottom: none;
            }
        }
        
        .comment-avatar {
            .avatar-placeholder {
                width: 80rpx;
                height: 80rpx;
                background: linear-gradient(135deg, #4a6cf7 0%, #6c8cff 100%);
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                
                .cuIcon-people {
                    font-size: 40rpx;
                    color: #fff;
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
            margin-bottom: 16rpx;
            padding: 0;
            border: none;
            
            .comment-author {
                font-size: 28rpx;
                font-weight: 600;
                color: #2c3e50;
            }
            
            .comment-time {
                font-size: 24rpx;
                color: #a0aec0;
            }
        }
        
        .comment-text {
            font-size: 28rpx;
            color: #4a5568;
            line-height: 1.6;
            margin-bottom: 16rpx;
        }
        
        .reply-section {
            background: rgba(226, 232, 240, 0.3);
            border-radius: 12rpx;
            padding: 20rpx;
            border-left: 4rpx solid #4a6cf7;
            
            .reply-label {
                display: flex;
                align-items: center;
                gap: 8rpx;
                margin-bottom: 8rpx;
                
                .cuIcon-forward {
                    font-size: 24rpx;
                    color: #4a6cf7;
                }
                
                text {
                    font-size: 24rpx;
                    color: #7e8c9a;
                    font-weight: 500;
                }
            }
            
            .reply-content {
                font-size: 26rpx;
                color: #4a5568;
                line-height: 1.5;
            }
        }
    }

    .empty-comment {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        padding: 80rpx 0;
        text-align: center;
        
        .empty-icon {
            width: 120rpx;
            height: 120rpx;
            background: linear-gradient(135deg, rgba(74, 108, 247, 0.1) 0%, rgba(74, 108, 247, 0.05) 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 32rpx;
            
            .cuIcon-message {
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
        
        .loading-text {
            font-size: 26rpx;
            color: #4a6cf7;
            animation: pulse 1.5s ease-in-out infinite;
        }
        
        .no-more-text {
            font-size: 26rpx;
            color: #bdc3c7;
            letter-spacing: 1rpx;
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
        .info-card,
        .action-section,
        .comment-section {
            margin: 20rpx;
            padding: 24rpx;
        }
        
        .info-grid {
            grid-template-columns: 1fr;
        }
        
        .action-buttons {
            flex-direction: column;
        }
        
        .top-image-container {
            height: 350rpx;
        }
        
        .coordinate-row {
            flex-direction: column;
        }
    }
</style>