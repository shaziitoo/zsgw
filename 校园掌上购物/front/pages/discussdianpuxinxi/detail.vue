<template>
                

   <view>
    <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback" :bottom="150">


        <view class="top">
                                                                                                                                                                                                            </view>
       
                                                                                                                                                                                                                                                                                                        
  

        <unisection title="操作" type="line">

                                                                                                                                                                                                                  
                                    
             					
			            
            
        </unisection>
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
                
                
                autoplaySwiper: false ? true : false,
                intervalSwiper: false ? $template2.front.base.swiper.autoplay.delay : 5000,
                btnColor: ['#409eff', '#67c23a', '#909399', '#e6a23c', '#f56c6c', '#356c6c', '#351c6c', '#f093a9', '#a7c23a', '#104eff', '#10441f', '#a21233', '#503319'],
                id: '',
                detail: {},
                swiperList: [],
                commentList: [],
                mescroll: null, //mescroll实例对象
                downOption: {
                    auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
                },
                upOption: {
                    noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
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

        components:{

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
        // 渲染数据
        this.init();
        let table = uni.getStorageSync("nowTable");
        // 获取用户信息
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




        //跳转到问诊界面
        consultation(id, name) {
            uni.navigateTo({
                url: `../consultation/message?name=` + name + `&id=` + id + `&srcUserId=` + this.user.id + `&srcUserNicheng=` + this.user.nicheng
            })
        },


                // 支付
        onPayTap() {
            uni.setStorageSync('paytable', 'discussdianpuxinxi');
            uni.setStorageSync('payObject', this.detail);
                this.$utils.jump('../pay-confirm/pay-confirm?type=1')
        },
                // 跨表
        onAcrossTap(acrossTable, statusColumnName, tips, statusColumnValue) {
            let boolean=true
            uni.setStorageSync('crossTable', `discussdianpuxinxi`);
            uni.setStorageSync('statusColumnName', statusColumnName);
            uni.setStorageSync('statusColumnValue', statusColumnValue);
            uni.setStorageSync('crossObj', JSON.stringify(this.detail));
            if (statusColumnName != '') {
                // this.detail.statusColumnName=statusColumnValue
                var obj = JSON.parse(uni.getStorageSync('crossObj'));
                for (var o in obj) {
                    if (o == statusColumnName && obj[o] == statusColumnValue) {
                            this.$utils.msg(tips);
                        boolean=false
                    }
                }
            }if(boolean){
                    this.$utils.jump(`../${acrossTable}/add-or-update?cross=true`);
            }
        },
        // 获取详情
        async init() {
            let res = await this.$api.info('discussdianpuxinxi', this.id);
            this.detail = res.data;
            // 轮播图片
                                                                                                                                                                                                                            //修改富文本的图片样式
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
                        mescroll.endSuccess(mescroll.size, this.hasNext);
        },


                        

        
        

                    

        //旧代码
        async onCartTap() {
            if (uni.getStorageSync(`cart${this.detail.id}${this.user.id}`)) {
                this.$utils.msg('该商品已添加到购物车')
                        return
            }
            await this.$api.add('cart', {
                tablename: 'discussdianpuxinxi',
                goodid: this.detail.id,
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
                tablename: 'discussdianpuxinxi',
                goodid: this.detail.id,
                        

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            buynumber: 1,
                price: this.detail.price,
                discountprice: this.detail.vipprice
            }])
                this.$utils.jump('../shop-order-confirm/shop-order-confirm?type=1');
        },
        onChatTap() {
                this.$utils.jump('../chat/chat')
        },
        // 下载
        download(url) {
            let _this = this;
            uni.downloadFile({
                url: url,//下载地址接口返回
                success: (data) => {
                if (data.statusCode === 200) {
                //文件保存到本地
                uni.getFileSystemManager().saveFile({
                    tempFilePath: data.tempFilePath, //临时路径
                    success: function(res) {
                        uni.showToast({
                            icon: 'none',
                            mask: true,
                            title: '文件已保存：' + res.savedFilePath, //保存路径
                            duration: 3000,
                        });
                        setTimeout(() => {
                            //打开文档查看
                            uni.openDocument({
                            filePath: res.savedFilePath,
                            success: function(res) {
                                // console.log('打开文档成功');
                            }
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
        //
        onCartTabTap() {
            this.$utils.tab('../shop-cart/shop-cart')
        },
        // 添加评论
        onCommentTap() {
                this.$utils.jump(`../discussdiscussdianpuxinxi/add-or-update?refid=${this.id}`)
        },
        // 点赞
        async zan() {
            let _this = this;
            uni.showModal({
                title: '提示',
                content: '是否点赞',
                success: async function (res) {
                    if (res.confirm) {
                        _this.detail.thumbsupnum = parseInt(_this.detail.thumbsupnum) + 1;
                        await _this.$api.update('discussdianpuxinxi', _this.detail);
                        _this.$utils.msg('点赞成功');
                    }
                }
            });
        },
        // 踩
        async cai() {
            let _this = this;
            uni.showModal({
                title: '提示',
                content: '是否点踩',
                success: async function (res) {
                    if (res.confirm) {
                        _this.detail.crazilynum = parseInt(_this.detail.crazilynum) + 1;
                        await _this.$api.update('discussdianpuxinxi', _this.detail);
                        _this.$utils.msg('点踩成功');
                    }
                }
            });
        },
        onSHTap() {
            this.$refs.popup.open()
        },
        // 完成审核
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
            await this.$api.update('discussdianpuxinxi', this.detail);
                this.$utils.msg('审核成功');
                this.$refs.popup.close();
        },
        // 关闭窗口
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

                @import '../../components/hm-news-detail/index.response.css';
                        .container {
        // padding-bottom: 80upx;
        }

        .container:after {
            position: fixed;
            top: 0;
            right: 0;
            left: 0;
            bottom: 0;
            content: '';
            background-attachment: fixed;
            background-size: cover;
            background-position: center;
        }

        .swiper {
            width: 100%;
            height: 450upx;

        image {
            width: 100%;
        }
        }

        .detail-content {
            font-size: 28upx;
            color: #888888;
        // padding: 10upx 20upx;
            background: #FFFFFF;
            line-height: 45upx;

        .name {
            font-size: 38rpx;
            font-weight: blod;
        // margin-bottom: 20upx;
            padding-top: 20upx;
        }

        .price {
            font-size: 33rpx;
            color: red;
        // margin-top: 20upx;
        }
        }

        .time-content {
            background: #FFFFFF;
            padding: 30upx;
            font-size: 30upx;
            border-radius: 20upx;
            margin-top: 20upx;

        .header {
            font-size: 33upx;
            margin-bottom: 20upx;
        }
        }

        .bottom-content {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
        }

        .comoment-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-bottom: 12upx;

        .btn-comment-content {
            color: #DD514C;
            font-weight: bold;
            padding: 0 12upx;
        }
        }

        .comment-item {
            margin-bottom: 10upx;
            border-bottom: 1px solid #EEEEEE;
            padding-bottom: 20upx;

        .content {
            margin-left: 20upx;
            margin-top: 20upx;
        }
        }

        .icon {
            width: 50upx;
            height: 50upx;
        }

        .popup-content {
        .popup-form {
            background: #FFFFFF;
            margin-top: 20upx;
        }

        .btn-content {
            text-align: right;
            background: #FFFFFF;

        button {
            margin-left: 20upx;
            margin-right: 20upx;
            margin-bottom: 20upx;
        }
        }
        }

        .detail-list-item {
            padding: 0 24upx;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            height: 68upx;
            border-bottom: 2upx solid #efefef;
            background-color: #fff;

        .lable {
            font-size: 28upx;
            color: #000;
        }

        .text {
            flex: 1;
            font-size: 24upx;
            color: #FF00FF;
            text-align: left;
        }

        &.favor {
        .el-icon-star-on {
            color: inherit;
            font-size: inherit;
        }
        }
        }

        .detail-content .shop-title {
            padding: 20upx 24upx;
            box-sizing: border-box;
            line-height: 1.4;
        }

        .priceFavor {
            height: 96upx !important;
        }

        .priceFavor .text {
            font-size: 64upx !important;
            color: red !important;
        }

        .priceFavor .icon {
            font-size: 56upx !important;
            text-align: right !important;
            color: red !important;
        }

            .page {
        padding: 20px;
        background-color: #ebf1fc;
    }



    .b_img {
        width: 100%;
        height: auto;
    }
</style>
