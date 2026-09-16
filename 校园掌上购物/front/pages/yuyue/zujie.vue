<template>
	 <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
	<view>
		<view class="order" v-for="(item,index) in orderList" :key="index">
			
			<view class="order-item">
				<image
					src="../../static/title.png"
					mode="widthFix"></image>
				<text style="font-size: 30rpx; color: #000000;" class="row1">{{ item.id }}</text>
			</view>
			<view class="order-item">
				<image
					src="../../static/time.png"
					mode="widthFix"></image>
				<text>开始时间：</text>
				<text>{{ item.funddate }}</text>
			</view>
			<view class="order-item">
               <image
					src="../../static/time.png"
					mode="widthFix"></image>
				<text>结束时间：</text>
				<text>{{ item.enddate }}</text>
			</view>
			<view class="order-item">
				<image
					src="../../static/maddres.png"
					mode="widthFix"></image>
				<text>租借状态：</text>
				<text>{{ item.type }}</text>
			</view>
			<view class="order-item">
				<image
					src="../../static/maddres.png"
					mode="widthFix"></image>
				<text>价格：</text>
				<text>{{ item.price }}</text>
			</view>
			<view class="order-item">
				<image
					src="../../static/time.png"
					mode="widthFix"></image>
				<text>预约内容：</text>
				<text>{{ item.title }}</text>
			</view>
			<view class="order-bom">
				<view class="order-bom-left"></view>
				<view class="order-bom-right">
                    <view class="box1" v-if="item.type=='未支付'" @click="zf(item)">支付</view>
					<view class="box1" v-if="item.type=='租借中'" @click="Transfer(item)">归还</view>
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
                orderList: [],
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
			
			async zf(item){
				let _this=this;
				console.log(item.price)
				let table = uni.getStorageSync("nowTable");
				let res1 = await _this.$api.session(table);
				 if(Number(res1.data.money)<Number(item.price)){
				   _this.$utils.msg('余额不足')
				      return
				}
				
				res1.data.money=res1.data.money-item.price;
				await _this.$api.update(table, JSON.stringify(res1.data));
				
				item.type='租借中';
				await _this.$api.update(`lease`, JSON.stringify(item));
				_this.hasNext = true
				// 重置分页参数页数为1
				_this.mescroll.resetUpScroll()
				
				
				
				
			},
			
			
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
			    let res = await this.$api.list(`lease`, params);
			    // 如果是第一页数据置空
			    if (mescroll.num == 1) this.orderList = [];
			    this.orderList = this.orderList.concat(res.data.list);
			    if (res.data.list.length == 0) this.hasNext = false;
			    mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			
			 Transfer(item){
		var _this = this;
			
			uni.showModal({
			    title: '提示',
			    content: '是否归还',
			    success: async function (res) {
					
			        if (res.confirm) {
						item.type='已归还';
			            await _this.$api.update(`lease`, JSON.stringify(item));
			            _this.hasNext = true
			            // 重置分页参数页数为1
			            _this.mescroll.resetUpScroll()
			        }
			    }
			});
			
			
			},
			
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
					background-color: #526afc;
					padding: 0 30rpx;
					box-sizing: border-box;
					border-radius: 10rpx;
					// margin-right: 10rpx;
				}
			}
		}
	}
</style>