<template>
		 <mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
	<!-- <view>
		<view class="padding-xl radius shadow bg-white">
			
			
			<view class="padding-xl radius shadow bg-white">默认阴影</view>
		
		</view>
		
	</view> -->
	<view class="padding-xl radius shadow bg-white body" v-for="(item,index) in messagelist">
	<view class="cu-card dynamic no-card">
		<view class="cu-item shadow">
			<view class="cu-list menu-avatar">
				<view class="cu-item">
					<view class="content flex-sub">
						<view>{{item.username}}</view>
						<view class="text-gray text-sm flex justify-between">
							{{item.addtime}}
						</view>
					</view>
				</view>
			</view>
			<view class="text-content">
				{{item.content}}
			</view>
	        <!-- 官网给出两种样式，第一就是等高 grid ，第二就是一张图片，自行复制查看 -->
			<view v-if="item.cpicture" class="grid flex-sub padding-lr" :class="isCard?'col-3 grid-square':'col-1'">
				<!-- <view class="bg-img " :class="isCard?'':'only-img'" :style="{background-image:`url(${item.cpicture})`}">
				</view> -->
				<image :src="item.cpicture"></image>
			</view>
	 
			<view v-if="item.reply" class="cu-list menu-avatar comment solids-top">
				<view class="cu-item">
					<view class="content">
						<view class="text-grey">管理员</view>
						<view class="text-gray text-content text-df">
							{{item.reply}}
						</view>
					</view>
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
				messagelist:[],
				mescroll: null, //mescroll实例对象
				downOption: {
				    auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
				    noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
				    textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
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
				    let res = await this.$api.list(`messagess`, params);
				    // 如果是第一页数据置空
				    if (mescroll.num == 1) this.messagelist = [];
				    this.messagelist = this.messagelist.concat(res.data.list);
				    if (res.data.list.length == 0) this.hasNext = false;
				    mescroll.endSuccess(mescroll.size, this.hasNext);
				}
				}
		
	}
</script>

<style>
    .body{
		background-color:#f5f7ff;
	}
</style>
