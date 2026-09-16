<template>
    <view class="content">
                              
        <view :style='{"boxShadow":"","backgroundColor":"rgba(15, 93, 159, 0.56)","borderColor":"#ccc","margin":"","borderWidth":"0","borderStyle":"solid","height":"108rpx"}'
              v-if="tableName=='yonghu'" class="cu-form-group">
            <view class="title"
                  :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(4, 239, 243, 1)","textAlign":"left"}'>账号
            </view>
            <input style="padding: 0 30upx"
                   :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,.6) inset","backgroundColor":"rgba(15, 93, 159, 0)","borderColor":"rgba(0,0,0,.6)","borderRadius":"20rpx","color":"rgba(16, 15, 15, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
                   v-model="ruleForm.zhanghao" placeholder="账号"></input>
        </view>

          
        <view :style='{"boxShadow":"","backgroundColor":"rgba(15, 93, 159, 0.56)","borderColor":"#ccc","margin":"","borderWidth":"0","borderStyle":"solid","height":"108rpx"}'
              v-if="tableName=='yonghu'" class="cu-form-group">
            <view class="title"
                  :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(4, 239, 243, 1)","textAlign":"left"}'>密码
            </view>
            <input style="padding: 0 30upx"
                   :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,.6) inset","backgroundColor":"rgba(15, 93, 159, 0)","borderColor":"rgba(0,0,0,.6)","borderRadius":"20rpx","color":"rgba(16, 15, 15, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
                   v-model="ruleForm.mima" placeholder="密码"></input>
        </view>

          
        <view :style='{"boxShadow":"","backgroundColor":"rgba(15, 93, 159, 0.56)","borderColor":"#ccc","margin":"","borderWidth":"0","borderStyle":"solid","height":"108rpx"}'
              v-if="tableName=='yonghu'" class="cu-form-group">
            <view class="title"
                  :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(4, 239, 243, 1)","textAlign":"left"}'>手机号
            </view>
            <input style="padding: 0 30upx"
                   :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,.6) inset","backgroundColor":"rgba(15, 93, 159, 0)","borderColor":"rgba(0,0,0,.6)","borderRadius":"20rpx","color":"rgba(16, 15, 15, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
                   v-model="ruleForm.shoujihao" placeholder="手机号"></input>
        </view>

          
        <view :style='{"boxShadow":"","backgroundColor":"rgba(15, 93, 159, 0.56)","borderColor":"#ccc","margin":"","borderWidth":"0","borderStyle":"solid","height":"108rpx"}'
              v-if="tableName=='yonghu'" class="cu-form-group">
            <view class="title"
                  :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(4, 239, 243, 1)","textAlign":"left"}'>地址
            </view>
            <input style="padding: 0 30upx"
                   :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,.6) inset","backgroundColor":"rgba(15, 93, 159, 0)","borderColor":"rgba(0,0,0,.6)","borderRadius":"20rpx","color":"rgba(16, 15, 15, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
                   v-model="ruleForm.dizhi" placeholder="地址"></input>
        </view>

                          
        <view :style='{"boxShadow":"","backgroundColor":"rgba(15, 93, 159, 0.56)","borderColor":"#ccc","margin":"","borderWidth":"0","borderStyle":"solid","height":"108rpx"}'
              v-if="tableName=='shangjia'" class="cu-form-group">
            <view class="title"
                  :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(4, 239, 243, 1)","textAlign":"left"}'>账号
            </view>
            <input style="padding: 0 30upx"
                   :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,.6) inset","backgroundColor":"rgba(15, 93, 159, 0)","borderColor":"rgba(0,0,0,.6)","borderRadius":"20rpx","color":"rgba(16, 15, 15, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
                   v-model="ruleForm.zhanghao" placeholder="账号"></input>
        </view>

          
        <view :style='{"boxShadow":"","backgroundColor":"rgba(15, 93, 159, 0.56)","borderColor":"#ccc","margin":"","borderWidth":"0","borderStyle":"solid","height":"108rpx"}'
              v-if="tableName=='shangjia'" class="cu-form-group">
            <view class="title"
                  :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(4, 239, 243, 1)","textAlign":"left"}'>密码
            </view>
            <input style="padding: 0 30upx"
                   :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,.6) inset","backgroundColor":"rgba(15, 93, 159, 0)","borderColor":"rgba(0,0,0,.6)","borderRadius":"20rpx","color":"rgba(16, 15, 15, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
                   v-model="ruleForm.mima" placeholder="密码"></input>
        </view>

          
        <view :style='{"boxShadow":"","backgroundColor":"rgba(15, 93, 159, 0.56)","borderColor":"#ccc","margin":"","borderWidth":"0","borderStyle":"solid","height":"108rpx"}'
              v-if="tableName=='shangjia'" class="cu-form-group">
            <view class="title"
                  :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(4, 239, 243, 1)","textAlign":"left"}'>手机号
            </view>
            <input style="padding: 0 30upx"
                   :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,.6) inset","backgroundColor":"rgba(15, 93, 159, 0)","borderColor":"rgba(0,0,0,.6)","borderRadius":"20rpx","color":"rgba(16, 15, 15, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
                   v-model="ruleForm.shoujihao" placeholder="手机号"></input>
        </view>

          
        <view :style='{"boxShadow":"","backgroundColor":"rgba(15, 93, 159, 0.56)","borderColor":"#ccc","margin":"","borderWidth":"0","borderStyle":"solid","height":"108rpx"}'
              v-if="tableName=='shangjia'" class="cu-form-group">
            <view class="title"
                  :style='{"width":"160rpx","fontSize":"28rpx","color":"rgba(4, 239, 243, 1)","textAlign":"left"}'>商家名称
            </view>
            <input style="padding: 0 30upx"
                   :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,.6) inset","backgroundColor":"rgba(15, 93, 159, 0)","borderColor":"rgba(0,0,0,.6)","borderRadius":"20rpx","color":"rgba(16, 15, 15, 1)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
                   v-model="ruleForm.shangjiamingcheng" placeholder="商家名称"></input>
        </view>

            <view class="btn">
            <view class="box" :style="{width: 'auto'}">
                <button @tap="update()" class="cu-btn lg"
                        :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"#409EFF","borderColor":"#409EFF","borderRadius":"32rpx","color":"#fff","borderWidth":"1","width":"auto","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'>
                    保存
                </button>
            </view>

            <view class="box" :style="{width: 'auto'}">
                <button @tap="logout()" class="cu-btn lg"
                        :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"#E6A23C","borderColor":"#E6A23C","borderRadius":"32rpx","color":"#fff","borderWidth":"2rpx","width":"auto","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'>
                    退出登录
                </button>
            </view>
        </view>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {},
                tableName: "",
                                                                                                                                            }
        },
        async onLoad() {
            let table = uni.getStorageSync("nowTable");
            let res = await this.$api.session(table)
            this.ruleForm = res.data
            this.tableName = table;
                                                                                                                                            this.styleChange()
        },
        methods: {
            // 下拉变化
                                                                                                                                            styleChange() {
                this.$nextTick(() => {
                    // document.querySelectorAll('.cu-form-group .uni-input-input').forEach(el=>{
                    //   el.style.backgroundColor = this.userInfoForm.list.input.backgroundColor
                    // })
                })
            },
            // 获取uuid
            getUUID() {
                return new Date().getTime();
            },
            logout() {
                uni.setStorageSync('token', '');
                this.$utils.jump('../login/login');
            },
            // 注册
            async update() {
                let table = uni.getStorageSync("nowTable");
                await  this.$api.update(table, this.ruleForm);
                this.$utils.msgBack('修改成功');
                ;
            } ,
                                                                                                                                                                                                                                                        }
    }
</script>

<style lang="scss" scoped>
    .content:after {
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

    .avator {
        width: 110 upx;
        height: 110 upx;
        border-radius: 50%;
        margin-left: 30 upx;
    }

    .cu-form-group.active {
        justify-content: space-between;
    }

    .cu-btn {
        width: 100%;
    }

    .right-input {
        flex: 1;
        text-align: left;
        line-height: 88 rpx;
    }

    .btn {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-wrap: wrap;
        padding: 20 upx 0;
    }

    .box {
        width: auto;
        padding: 0 10 upx;
        box-sizing: border-box;
        margin-bottom: 20 upx;
    }

    .cu-btn {
        width: 100% !important;
    }
</style>
