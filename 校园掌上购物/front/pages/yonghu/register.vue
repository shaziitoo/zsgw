<template>

        
    <view class="login-container">
        <view class="login-box">
            <view class="title">注册</view>

            <view class="input-group">

                                                                                                                                                                                                                                                                                                        <view class="input-item" v-if="tableName=='yonghu'">
                                                <image src="/static/icons/username.png" class="icon-image"></image>
                                                <input
                                                        type="text"
                                                        v-model="ruleForm.zhanghao"
                                                        placeholder="请输入您的账号"
                                                />
                                            </view>
                                        

                                                                                                                                                                                                    <view class="input-item" v-if="tableName=='yonghu'">
                                                <image src="/static/icons/username.png" class="icon-image"></image>
                                                <input
                                                        type="text"
                                                        v-model="ruleForm.mima"
                                                        placeholder="请输入您的密码"
                                                />
                                            </view>
                                        

                                                                                                                                                                                                    <view class="input-item" v-if="tableName=='yonghu'">
                                                <image src="/static/icons/username.png" class="icon-image"></image>
                                                <input
                                                        type="text"
                                                        v-model="ruleForm.shoujihao"
                                                        placeholder="请输入您的手机号"
                                                />
                                            </view>
                                        

                                                                                                                                                                                                    <view class="input-item" v-if="tableName=='yonghu'">
                                                <image src="/static/icons/username.png" class="icon-image"></image>
                                                <input
                                                        type="text"
                                                        v-model="ruleForm.dizhi"
                                                        placeholder="请输入您的地址"
                                                />
                                            </view>
                                        

                                                                                                                                                        

                                                                            




            </view>

            <button class="login-btn" @tap="register">
                注册
            </button>

            <view class="tips">
                <text @tap="gologin()">已有账号，去登录</text>
            </view>
        </view>
    </view>



    

</template>

<script>
    export default {
        data() {
            return {
                                            ruleForm: {},
                tableName: ""
            }
        },
        async onLoad() {
            let res = [];
            let table = uni.getStorageSync("loginTable");
            this.tableName = table;
            // 自定义下拉框值
            this.styleChange()
        },
        methods: {
            // 下拉变化
                                        styleChange() {
                this.$nextTick(() => {
                    // document.querySelectorAll('.uni-input .uni-input-input').forEach(el=>{
                    //   el.style.backgroundColor = this.registerFrom.content.input.backgroundColor
                    // })
                })
            },
            // 获取uuid
            getUUID() {
                return new Date().getTime();
            },
            gologin(){
                this.$utils.jump("../login/login")
            },
            // 注册
            async register() {
                                            if (`yonghu` == this.tableName && this.ruleForm.shoujihao && (!this.$validate.isMobile(this.ruleForm.shoujihao))) {
                this.$utils.msg(`手机号应输入手机格式`);
            return
        }
                                        await  this.$api.register(`${this.tableName}`,this.ruleForm);
                this.$utils.msgBack('注册成功');
                ;
            }
        }
    }
</script>

<style lang="scss" scoped>
    .login-container {
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        background: #9a2b23;
        padding: 40rpx;
        box-sizing: border-box;

        .login-box {
            width: 100%;
            padding: 60rpx 40rpx;
            background-color: #d3813d;
            border-radius: 30rpx;
            box-shadow: 0 10rpx 50rpx rgba(0, 0, 0, 0.1);

            .logo {
                text-align: center;
                margin-bottom: 20rpx;

                image {
                    width: 180rpx;
                    height: 180rpx;
                }
            }

            .title {
                text-align: center;
                font-size: 40rpx;
                color: #fff;
                font-weight: bold;
                margin-bottom: 60rpx;
                letter-spacing: 4rpx;
            }

            .input-group {
                margin-bottom: 50rpx;

                .input-item {
                    display: flex;
                    align-items: center;
                    height: 90rpx;
                    background-color: #f8f9fa;
                    border-radius: 45rpx;
                    padding: 0 40rpx;
                    margin-bottom: 30rpx;

                    input {
                        flex: 1;
                        height: 90rpx;
                        padding: 0 20rpx;
                        font-size: 28rpx;
                    }

                    .role-picker {
                        flex: 1;
                        height: 90rpx;

                        picker {
                            height: 100%;
                        }

                        .picker-text {
                            height: 90rpx;
                            line-height: 90rpx;
                            font-size: 28rpx;
                            color: #333;
                        }
                    }

                    .icon-image {
                        width: 40rpx;
                        height: 40rpx;
                        margin-right: 20rpx;

                        &.arrow-right {
                            margin-right: 0;
                            margin-left: 20rpx;
                        }
                    }
                }
            }

            .login-btn {
                width: 100%;
                height: 90rpx;
                line-height: 90rpx;
                background: #e4c58d;
                color: #fff;
                border-radius: 45rpx;
                font-size: 32rpx;
                font-weight: bold;
                box-shadow: 0 10rpx 20rpx rgba(118, 75, 162, 0.3);

                &:active {
                    transform: translateY(3rpx);
                    box-shadow: 0 5rpx 10rpx rgba(118, 75, 162, 0.2);
                }
            }

            .tips {
                margin-top: 40rpx;
                display: flex;
                justify-content: space-between;
                font-size: 24rpx;
                color: #666;
            // padding: 0 20rpx;

                text {
                    color: #e4c58d;
                }
            }
        }
    }

    .placeholder {
        color: #999;
        font-size: 28rpx;
    }

</style>
