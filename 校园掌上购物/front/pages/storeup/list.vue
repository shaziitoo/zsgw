<template>
	 <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
	<view class="cu-card case" v-for="(item,index) in storeuplist">
		<view class="cu-item shadow">
			<view class="image">
				<image :src="item.picture"
				 mode="widthFix"></image>
				<view class="cu-tag bg-blue">{{item.name}}</view>
				<view class="cu-bar bg-shadeBottom"> <text class="text-cut">{{item.addtime}}</text></view>
			</view>
			<view class="cu-item shadow">
				<view class="flex">
				    <view class="flex-sub padding-sm margin-xs radius">
						<button @tap="onDetailTap(item)"  class="cu-btn block bg-blue margin-tb-sm lg" type="">详情</button>
					</view>
				    <view class="flex-sub padding-sm margin-xs radius">
						<button  @click="Transfer(item)" class="cu-btn block bg-red margin-tb-sm lg" type="">删除</button>
					</view>
				</view>
			</view>
		</view>
	</view>
	</mescroll-uni>
</template>

<script>
	export default {
		data() {
			return {
				user:{},
				storeuplist:[],
				mescroll: null, //mescroll实例对象
				downOption: {
				    auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
				    noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
				    textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
			};
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
			    let res = await this.$api.list(`storeup`, params);
			    // 如果是第一页数据置空
			    if (mescroll.num == 1) this.storeuplist = [];
			    this.storeuplist = this.storeuplist.concat(res.data.list);
			    if (res.data.list.length == 0) this.hasNext = false;
			    mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			
			Transfer(item){
			var _this = this;
				
				uni.showModal({
				    title: '提示',
				    content: '是否删除',
				    success: async function (res) {
				        if (res.confirm) {
				            await _this.$api.del(`storeup`, JSON.stringify([item.id]));
				            _this.hasNext = true
				            // 重置分页参数页数为1
				            _this.mescroll.resetUpScroll()
				        }
				    }
				});
				
				
				},
				// 详情
				onDetailTap(item) {
				        this.$utils.jump(`../${item.tablename}/detail?id=${item.refid}`)
				},
			}
	};
</script>

<style>
	
</style>
