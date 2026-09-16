<template>
    <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
		        <view>
            <button v-if="isgrAuth('address','新增')" @tap="add()" class="round lg bg-green">新增</button>
            <view class="order"  v-for="(product,index) in list" :key="index">

																				                        <view class="order-item">
                            <text>地址：</text>
                            <text>{{ product.address }}</text>
                        </view>
										                        <view class="order-item">
                            <text>收货人：</text>
                            <text>{{ product.name }}</text>
                        </view>
										                        <view class="order-item">
                            <text>电话：</text>
                            <text>{{ product.phone }}</text>
                        </view>
										                        <view class="order-item">
                            <text>是否默认地址[是/否]：</text>
                            <text>{{ product.isdefault }}</text>
                        </view>
									
																																																																			
																																																



                <view class="order-bom">
                    <view class="order-bom-left"></view>
                    <view class="order-bom-right">
                        <view v-if="isgrAuth('address','修改')" class="box2" @tap="update(product)">修改</view>
                        <view v-if="isgrAuth('address','删除')" class="box3" @tap="del(product)">删除</view>
                    </view>

                </view>
            </view>
        </view>
    </mescroll-uni>
</template>

<script>
    export default {
        name: "yt-order-list",

        data() {
            return {
                mescroll: null, //mescroll实例对象
                downOption: {
                    auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
                },
                upOption: {
                    noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
                    textNoMore: '~ 没有更多了 ~',
                },
                hasNext: true,
                list: [],
            }
        },
        async onShow() {
        this.hasNext = true
        // 重新加载数据
        if (this.mescroll) this.mescroll.resetUpScroll()
    },
    onLoad() {
        this.hasNext = true
        // 重新加载数据
        if (this.mescroll) this.mescroll.resetUpScroll()
    },
    methods: {




        mescrollInit(mescroll) {
            this.mescroll = mescroll;
        },
        /*下拉刷新的回调 */
        downCallback(mescroll) {
            this.hasNext = true
            // 重置分页参数页数为1
            mescroll.resetUpScroll()
        },
        /*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
        async upCallback(mescroll) {

            let table = uni.getStorageSync("nowTable");
            let res1 = await this.$api.session(table);


            let params = {
                page: mescroll.num,
                limit: mescroll.size,
                userid:res1.data.id
            }
            let res = await this.$api.list(`address`, params);
            // 如果是第一页数据置空
            if (mescroll.num == 1) this.list = [];
            this.list = this.list.concat(res.data.list);
            if (res.data.list.length == 0) this.hasNext = false;
            mescroll.endSuccess(mescroll.size, this.hasNext);
        },
        add(){
				this.$utils.jump(`./add-or-update`)
        },
        update(row){

				this.$utils.jump(`./add-or-update?id=${row.id}`)
        },
        del(row){
            let _this=this;
            uni.showModal({
                title: '提示',
                content: '是否删除',
                success: async function (res) {
                    if (res.confirm) {
                        await _this.$api.del(`address`, JSON.stringify([row.id]));
                        _this.hasNext = true
                        // 重置分页参数页数为1
                        _this.mescroll.resetUpScroll()
                    }
                }
            });
        }

    }
    }
</script>

<style lang="scss">
    .green {
        color: green;
    }

    .red {
        color: red;
    }

    .blue {
        color: #4b5eeb;
    }

    .order {
        width: 100%;
    // height: 200rpx;
        border-radius: 30rpx;
        background-color: #ffffff;
        padding: 15rpx;
        box-sizing: border-box;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        margin-bottom: 25rpx;

    .order-number {
        width: 100%;
        color: #c73614;
        display: flex;
        align-items: center;
        justify-content: space-between;
        border-bottom: 2rpx solid #f3f3f3;
        padding-bottom: 20rpx;

    .number-left {
        display: flex;
        align-items: center;
        font-weight: bold;

    image {
        width: 30rpx;
        height: 30rpx;
        margin-right: 15rpx;
    }

    text {
        font-size: 30rpx;
        letter-spacing: 3rpx;
    }
    }

    .number-right {
        font-size: 26rpx;
        font-weight: bold;
        letter-spacing: 3rpx;
        padding-right: 5rpx;
        box-sizing: border-box;
    }

    }

    .order-item {
        width: 100%;
        display: flex;
        align-items: center;
        height: 50rpx;

    image {
        width: 30rpx;
        height: 30rpx;
        margin-right: 15rpx;
    }

    text {
        font-size: 24rpx;
        color: rgba(0, 0, 0, 0.8);
    }
    }

    .order-bom {
        width: 100%;
        height: 60rpx;
        background-color: #ffffff;
        display: flex;
        align-items: center;
        justify-content: space-between;

    .order-bom-left {
        color: rgba(0, 0, 0, 0.5);
        font-size: 24rpx;
        padding-left: 40rpx;
        box-sizing: border-box;
    }

    .order-bom-right {
    // width: 200rpx;
        height: 100%;
        display: flex;
        color: #ffffff;
        font-size: 26rpx;

    .box1 {
        height: 100%;
        line-height: 60rpx;
        background-color: #e5a84a;
        padding: 0 30rpx;
        box-sizing: border-box;
        border-radius: 10rpx;
        margin-right: 10rpx;
    }

    .box2 {
        height: 100%;
        line-height: 60rpx;
        background-color: #56caa3;
        padding: 0 30rpx;
        box-sizing: border-box;
        border-radius: 10rpx;
        margin-right: 10rpx;
    }

    .box3 {
        height: 100%;
        line-height: 60rpx;
        background-color: red;
        padding: 0 30rpx;
        box-sizing: border-box;
        border-radius: 10rpx;
    // margin-right: 10rpx;
    }
    }
    }
    }
</style>
