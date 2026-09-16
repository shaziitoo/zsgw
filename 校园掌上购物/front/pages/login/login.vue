<template>
    


    <view class="login-container">
        <view class="login-box">
            <view class="logo">
                <image src="/static/logo.png" mode="aspectFit"></image>
            </view>
            <view class="title">校园掌上购物</view>

            <view class="input-group">
                <view class="input-item">
                    <image src="/static/icons/username.png" class="icon-image"></image>
                    <input
                            type="text"
                            v-model="username"
                            placeholder="请输入用户名"
                            placeholder-class="placeholder"
                    />
                </view>

                <view class="input-item">
                    <image src="/static/icons/password.png" class="icon-image"></image>
                    <input
                            type="password"
                            v-model="password"
                            placeholder="请输入密码"
                            placeholder-class="placeholder"
                    />
                </view>

                <view class="input-item">
                    <image src="/static/icons/role.png" class="icon-image"></image>
                    <view class="role-picker">
                        <picker
                                :value="index" :range="options"
                                @change="optionsChange"
                        >
                            <view class="picker-text">
                                {{ options[index] || '请选择角色' }}
                            </view>
                        </picker>
                    </view>
                    <image src="/static/icons/arrow_right.png" class="icon-image arrow-right"></image>
                </view>
            </view>

            <button class="login-btn"  @tap="onLoginTap()">
                登录
            </button>

            <view class="tips">

                                            <button class="login-btn"  @tap="onRegisterTap('yonghu')">注册用户</button>
                
                                </view>
        </view>
    </view>



    


</template>

<script>
        import {menu} from '@/utils/menu'

    export default {
        data() {
            return {
                username: '',
                password: '',
                options: [
                    '请选择登录用户类型',
                                                    '用户',
                                                   
                                        ],
                optionsValues: [
                    '',
                    'yonghu',
                        'shangjia',
                    ],
                index: 0
            }
        },
        onLoad() {
            // let options = ['请选择登录用户类型'];
            // let menus = menu.list();
            // this.menuList = menus;
            // for (let i = 0; i < this.menuList.length; i++) {
            //     if (this.menuList[i].hasFrontLogin == '是') {
            //         options.push(this.menuList[i].roleName);
            //     }
            // }
            // this.options = options;

            this.styleChange()
        },
        methods: {
            styleChange() {
                this.$nextTick(() => {
                    // document.querySelectorAll('.uni-input .uni-input-input').forEach(el=>{
                    //   el.style.backgroundColor = this.loginFrom.content.input.backgroundColor
                    // })
                })
            },
            onRegisterTap(tableName) {
                uni.setStorageSync("loginTable", tableName);
                    this.$utils.jump("../"+tableName+"/register")
            },
            onForgetTap() {
                    this.$utils.jump('../forget/forget')
            },
            async onLoginTap() {
                if (!this.optionsValues[this.index]) {
                    this.$utils.msg('请选择登陆用户类型')
                            return
                }
                let res = await this.$api.login(`${this.optionsValues[this.index]}`, {
                username: this.username,
                password: this.password
                });
                uni.setStorageSync("token", res.token);
                uni.setStorageSync("nickname", this.username);
                uni.setStorageSync("nowTable", `${this.optionsValues[this.index]}`);
                res = await this.$api.session(`${this.optionsValues[this.index]}`);
                // 保存用户id
                uni.setStorageSync("userid", res.data.id);
                uni.setStorageSync("role", `${this.options[this.index]}`);
                    this.$utils.tab('../index/index');
            },
            optionsChange(e) {
                this.index = e.target.value
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
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        padding: 40rpx;
        box-sizing: border-box;
        position: relative;

        &::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: radial-gradient(circle at 20% 50%, rgba(255, 255, 255, 0.1) 0%, transparent 50%),
                        radial-gradient(circle at 80% 80%, rgba(255, 255, 255, 0.05) 0%, transparent 50%);
            pointer-events: none;
        }

        .login-box {
            width: 100%;
            max-width: 600rpx;
            padding: 60rpx 40rpx;
            background: rgba(255, 255, 255, 0.95);
            border-radius: 30rpx;
            box-shadow: 0 20rpx 60rpx rgba(0, 0, 0, 0.3),
                        0 0 1rpx rgba(255, 255, 255, 0.5) inset;
            backdrop-filter: blur(10px);
            position: relative;
            z-index: 1;
            border: 1rpx solid rgba(255, 255, 255, 0.2);

            .logo {
                text-align: center;
                margin-bottom: 30rpx;
                animation: slideDown 0.6s ease-out;

                image {
                    width: 180rpx;
                    height: 180rpx;
                    filter: drop-shadow(0 4rpx 12rpx rgba(102, 126, 234, 0.2));
                }
            }

            .title {
                text-align: center;
                font-size: 44rpx;
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                background-clip: text;
                font-weight: 700;
                margin-bottom: 60rpx;
                letter-spacing: 2rpx;
                animation: slideDown 0.8s ease-out;
            }

            .input-group {
                margin-bottom: 50rpx;

                .input-item {
                    display: flex;
                    align-items: center;
                    height: 90rpx;
                    background: linear-gradient(135deg, #f5f7fa 0%, #f0f2f5 100%);
                    border-radius: 20rpx;
                    padding: 0 30rpx;
                    margin-bottom: 20rpx;
                    border: 2rpx solid transparent;
                    transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
                    box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.05);

                    &:focus-within {
                        border-color: #667eea;
                        background: linear-gradient(135deg, #fff 0%, #f8f9fc 100%);
                        box-shadow: 0 8rpx 24rpx rgba(102, 126, 234, 0.15);
                        transform: translateY(-2rpx);
                    }

                    input {
                        flex: 1;
                        height: 90rpx;
                        padding: 0 20rpx;
                        font-size: 28rpx;
                        color: #2c3e50;
                        background: transparent;
                        border: none;
                        outline: none;

                        &::placeholder {
                            color: #bdc3c7;
                        }
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
                            color: #2c3e50;
                            font-weight: 500;
                        }
                    }

                    .icon-image {
                        width: 40rpx;
                        height: 40rpx;
                        margin-right: 15rpx;
                        opacity: 0.7;
                        transition: opacity 0.3s ease;

                        &.arrow-right {
                            margin-right: 0;
                            margin-left: 15rpx;
                            opacity: 0.5;
                        }
                    }

                    &:focus-within .icon-image {
                        opacity: 1;
                    }
                }
            }

            .login-btn {
                width: 100%;
                height: 90rpx;
                line-height: 90rpx;
                background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
                color: #fff;
                border-radius: 20rpx;
                font-size: 32rpx;
                font-weight: 700;
                border: none;
                box-shadow: 0 8rpx 20rpx rgba(255, 107, 107, 0.3);
                transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
                margin-bottom: 20rpx;

                &:active {
                    transform: translateY(-2rpx);
                    box-shadow: 0 12rpx 30rpx rgba(255, 107, 107, 0.4);
                }

                &:hover {
                    box-shadow: 0 12rpx 30rpx rgba(255, 107, 107, 0.4);
                }
            }

            .tips {
                margin-top: 30rpx;
                display: flex;
                gap: 20rpx;
                flex-direction: column;

                .login-btn {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    box-shadow: 0 8rpx 20rpx rgba(102, 126, 234, 0.3);
                    margin-bottom: 0;
                    font-size: 28rpx;

                    &:active {
                        box-shadow: 0 12rpx 30rpx rgba(102, 126, 234, 0.4);
                    }

                    &:hover {
                        box-shadow: 0 12rpx 30rpx rgba(102, 126, 234, 0.4);
                    }
                }
            }
        }
    }

    .placeholder {
        color: #bdc3c7;
        font-size: 28rpx;
    }

    @keyframes slideDown {
        from {
            opacity: 0;
            transform: translateY(-20rpx);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>
