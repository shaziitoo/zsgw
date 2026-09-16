<template>
	<!-- <view class="allGoods">
		
		
		<view class="pubuBox bg-white" v-if="mpGoodsShow=='imgFalls'">
			<view class="pubuItem">
				<view class="item-masonry padding-xl radius shadow bg-white"
					v-for="(item, index) in goodsList" :key="index">
					<image @tap="toGoodsDetail(item.id)" :src="item.tupian" mode="widthFix"></image>
					<view class="listtitle">
						<view class="listtitle1">{{item.biaoti}}</view>
						<view class="listtitle3" v-if="item.alllimittimes">
							仅剩{{item.alllimittimes}}份
						</view>
						<view class="listtitle4" v-if="item.price">
							<view class="price1">￥{{item.price}}</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		




	</view> -->
	
	
		<view class="content">
			<view class="topic-list">
				<view class="topic-item" v-for="(item,index) in goodsList" :key="index">
					<!-- <text v-for="(value, key, ind) in item" :key="ind"> -->
					<image v-for="(value, key, ind) in item" :key="ind" v-show="ind == 3" :src="value" @tap="toGoodsDetail(item.id)" class="topic-img"></image>
					<!-- </text> -->
					<view class="topic-title" v-for="(value, key, ind) in item" :key="ind">
						<text  v-show="ind == 2">{{value}}</text>
					</view>
				</view>
			</view>
		</view>

	
</template>

<script>
	const app = getApp()
	export default {
		props: {
			mpGoodsShow: {
				type: String,
				default: 'xingsheng'
			},
			goodsList: {
				type: Array,
				default () {
					return []
				}
			},
			classname:{
				type: String,
				default:''
			}
		},
		data() {
			return {
				
			}
		},
		methods: {
			// 跳转商品详情
			toGoodsDetail(e) {
				let seqId = e
				console.log(this.classname);
				this.$emit('toGoodsDetail',e,this.classname)
			},
			//弹出购物车
			addShopCart(e) {
				this.$emit('addShopCart', )
			},
		}
	}
</script>

<style lang="scss" scoped>
	/* 默认样式 */
		.content {
			/* 左右间距 */
			margin: 0 20rpx; 
		}
		
		/* 话题列表样式 */
		.topic-list{
			display:flex;
			/* 自动换行 */
			flex-wrap:wrap;
			/* 均匀排列每个元素,首个元素放置于起点，末尾元素放置于终点 */
			justify-content: space-between;
		}
		.topic-item{
			/* 单个列表宽度 */
			width:335rpx;
		
		}
		.topic-img{
			/* 继承父元素宽度 */
			width:100%;
			/* 图片高度 */
			height:180rpx;
			border-radius:10rpx;
			
		}
		.topic-title{
			font-size:24rpx;
		}
		.topic-tag{
			font-size:22rpx;
			padding: 14rpx 0rpx;
			color:gray;
		}
</style>
