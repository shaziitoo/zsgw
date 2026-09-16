<template>
	<view class="content" v-if="seen">


		<view>


			<view class="inputView">
				<text class="leftTitle">留言内容</text>
			</view>

			<textarea class="rightTextarea" v-model="ruleForm.content" name="direct" placeholder=" 留言内容" />


			<view class="inputView">
				<text class="leftTitle">添加照片（仅可添加1张）</text>
			</view>

			<!-- photoList：选择的图片数组  @click：图片选择事件-->
		<view 
		      class="cu-form-group" @tap="tupianTap">
		   
		    <view class="right-input" style='text-align: center;'>
		        <image 
		                class="avator" v-if="ruleForm.cpicture" :src="ruleForm.cpicture" mode="aspectFill"></image>
		        <image 
		               class="avator" v-else src="../../static/gen/upload.png" style="width: 200px;height: 200px;" mode="aspectFill"></image>
		    </view>
		</view>


			<view class="uni-btn-v">
				<view class="flex">
				    <view class="flex-sub padding-sm margin-xs radius">
						<button class="botBtn" @click="tj" type="primary" >提交</button>
					</view>
				    <view class="flex-sub  padding-sm margin-xs radius">
						<button class="botBtn"  @tap="onPageTap('./messagelist')"  type="primary" >查看留言</button>
					</view>
				</view>
				<!-- <view class="basis-df bg-grey basis-sub padding-sm radius">
					
					</view>
				<view class="basis-df bg-grey basis-sub padding-sm radius">
					<button class="botBtn" @click="tj" type="primary" >查看留言</button>
				</view> -->
				
                
			</view>
		</view>
	</view>
</template>

<script>
	import Vue from 'vue';


	export default {

		data() {

			return {
				seen: true,
				ruleForm: {
					content:'',
					cpicture:''
				},
				isClick: false,
			};
		},

		onLoad: function(e) {



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
            },
			tupianTap() {
			    let _this = this;
			    this.$api.upload(function (res) {
			    _this.ruleForm.cpicture = _this.$base.url + 'upload/' + res.file;
			    });
			},
			
   async tj(){
	   let _this=this;
		let table = uni.getStorageSync("nowTable");
		let res1 = await _this.$api.session(table);
		_this.ruleForm.username=uni.getStorageSync("nickname");
		_this.ruleForm.userid=res1.data.id;
		
		await _this.$api.add('messagess', _this.ruleForm);
		// await _this.$utils.msg('留言成功')
		
		uni.showToast({
			title:'留言成功',
			duration: 2000,
			success() {
				setTimeout(function(){
								uni.navigateBack({
								delta:1,//返回层数，2则上上页
								})
							},2000)
				
			}
		});
		
		 
	}

		}
	};
</script>

<style>
	.uni-form-item .title {
		padding: 20rpx 0;
	}

	.content {
		display: flex;
		flex-direction: column;
		width: 100%;
		height: auto;
	}

	.inputView {
		flex-direction: row;
		display: flex;
		height: 40px;
		align-items: center;
		width: 100%;
	}

	.line {
		width: 90%;
		height: 2rpx;
		margin-left: -2rpx;
		background-color: #f8f8f8;
		margin-left: 5%;
	}

	.leftTitle {
		margin-left: 40rpx;
		width: 284px;
		height: 32px;
		line-height: 32px;
		font-size: 28rpx;
		color: #333333;
	}



	.rightTextarea {

		margin-left: 5%;
		width: 90%;
		height: 106px;
		line-height: 40rpx;
		border-radius: 12rpx;
		border: solid 1px #F5F5F5;
		font-size: 15px;
	}




	.uni-btn-v {
		width: 100%;
		height: auto;
	}

	.botBtn {
		width: 90%;
		margin-top: 36px;
		height: 48px;
	}

	.tipText {
		width: 100%;
		margin-left: 0px;
		text-align: center;
		color: #666666;
		margin-top: 36px;
		margin-bottom: 36px;
		font-size: 28rpx;
	}
	.container {
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  height: 100%;
	}
	 
	.uploaded-image {
	  width: 100px;
	  height: 100px;
	  object-fit: cover; /* 确保图片覆盖整个容器 */
	  border: 1px solid #ddd; /* 添加边框 */
	  box-shadow: 0 0 5px #ccc; /* 添加阴影 */
	  border-radius: 5px; /* 圆角 */
	}
</style>