<template>
    <view class="content">
        <form>
                                                                                                                                                        <view class="cu-form-group">
                            <view class="title">名称
                            </view>
                            <input :disabled="ro.mingcheng" v-model="ruleForm.mingcheng" placeholder="名称"></input>
                        </view>
                                                                                                            <view class="cu-form-group" @tap="tupianTap" :class='left == "left"?"":"active"'>
                            <view class="title">图片
                            </view>
                            <view class="right-input" style="padding:0">
                                <image style="width: 50px;height: 50px;"  class="avator" v-if="ruleForm.tupian" :src="ruleForm.tupian" mode="aspectFill"></image>
                                <image style="width: 50px;height: 50px;" class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
                            </view>
                        </view>
                                                                                                            <view class="cu-form-group select">
                            <view class="title">分类名称
                            </view>
                            <picker @change="fenleimingchengChange" :value="fenleimingchengIndex" :range="fenleimingchengOptions">
                                <view class="uni-input">{{ruleForm.fenleimingcheng?ruleForm.fenleimingcheng:"请选择分类名称"}}
                                </view>
                            </picker>
                        </view>
                                                                                                            <!-- 否 -->
                        <view class="cu-form-group">
                            <view class="title">介绍
                            </view>
                            <textarea
                                    v-model="ruleForm.jieshao" placeholder="介绍"></textarea>
                        </view>
                                                                                                                                                                            <view class="cu-form-group select">
                            <view class="title">店铺名称
                            </view>
                            <picker @change="dianpumingchengChange" :value="dianpumingchengIndex" :range="dianpumingchengOptions">
                                <view class="uni-input">{{ruleForm.dianpumingcheng?ruleForm.dianpumingcheng:"请选择店铺名称"}}
                                </view>
                            </picker>
                        </view>
                                                                                                                                                                            <view class="cu-form-group">
                            <view class="title">价格
                            </view>
                            <input :disabled="ro.price" v-model="ruleForm.price" placeholder="价格"></input>
                        </view>
                                                                                                            <view class="cu-form-group">
                            <view class="title">单限
                            </view>
                            <input :disabled="ro.onelimittimes" v-model="ruleForm.onelimittimes" placeholder="单限"></input>
                        </view>
                                                                                                            <view class="cu-form-group">
                            <view class="title">库存
                            </view>
                            <input :disabled="ro.alllimittimes" v-model="ruleForm.alllimittimes" placeholder="库存"></input>
                        </view>
                                                                                                                                                                                                                                                                                                                            <view class="btn">
                <button @tap="onSubmitTap" class="bg-red">提交
                </button>
            </view>
        </form>
                                                                                                                                                                                                                                                                                                                                            </view>
</template>

<script>
    import wPicker from "@/components/w-picker/w-picker.vue";
    export default {
        data() {
            return {
                ruleForm: {
                                                                                                                                mingcheng:'',
                                                                                                            tupian:'',
                                                                                                            fenleimingcheng:'',
                                                                                                            jieshao:'',
                                                                                                            xiangqing:'',
                                                                                                            dianpumingcheng:'',
                                                                                                            userid:'',
                                                                                                            price:'',
                                                                                                            onelimittimes:'',
                                                                                                            alllimittimes:'',
                                                                                                            sfsh:'',
                                                                                                            shhf:'',
                                                                                                            thumbsupnum:'',
                                                                                                            crazilynum:'',
                                                        },
                                                                                                                                                                                                fenleimingchengOptions: [],
                                fenleimingchengIndex: 0,
                                                                                                                                                                            dianpumingchengOptions: [],
                                dianpumingchengIndex: 0,
                                                                                                                                                                                                                                                                                                // 登陆用户信息
                user: {},
            ro: {
                                                                                                                                    mingcheng:false,
                                                                                tupian:false,
                                                                                fenleimingcheng:false,
                                                                                jieshao:false,
                                                                                xiangqing:false,
                                                                                dianpumingcheng:false,
                                                                                userid:false,
                                                                                price:false,
                                                                                onelimittimes:false,
                                                                                alllimittimes:false,
                                                                                sfsh:false,
                                                                                shhf:false,
                                                                                thumbsupnum:false,
                                                                                crazilynum:false,
                                                },
        }
        },
        components: {
            wPicker
        },
        computed: {},
        async onLoad(options) {
        let table = uni.getStorageSync("nowTable");
        // 获取用户信息
        let res = await this.$api.session(table);
        this.user = res.data;
        // ss读取
        // 下拉框
                                                                                                                                                                                        res = await this.$api.option(`shangpinfenlei`, `fenleimingcheng`, {});
                    this.fenleimingchengOptions = res.data;
                                                                                                                                                                res = await this.$api.option(`dianpuxinxi`, `dianpumingcheng`, {});
                    this.dianpumingchengOptions = res.data;
                                                                                                                                                                                                                                                                                                                        // 如果有登陆，获取登陆后保存的userid
        this.ruleForm.userid = uni.getStorageSync("userid")
        if (options.refid) {
            // 如果上一级页面传递了refid，获取改refid数据信息
            this.ruleForm.refid = options.refid;
            this.ruleForm.nickname = uni.getStorageSync("nickname");
        }
        // 如果是更新操作
        if (options.id) {
            this.ruleForm.id = options.id;
            // 获取信息
            res = await this.$api.info(`shangpinguanli`, this.ruleForm.id);
            this.ruleForm = res.data;
        }
        // 跨表
        this.cross = options.cross;
        if (options.cross) {
             var obj = JSON.parse(uni.getStorageSync('crossObj'));
            for (var o in obj) {
                                                                                                                                    if (o == 'mingcheng') {
                            this.ruleForm.mingcheng= obj[o];
                            this.ro.mingcheng = true;
                            continue;
                        }
                                                                                if (o == 'tupian') {
                            this.ruleForm.tupian= obj[o];
                            this.ro.tupian = true;
                            continue;
                        }
                                                                                if (o == 'fenleimingcheng') {
                            this.ruleForm.fenleimingcheng= obj[o];
                            this.ro.fenleimingcheng = true;
                            continue;
                        }
                                                                                if (o == 'jieshao') {
                            this.ruleForm.jieshao= obj[o];
                            this.ro.jieshao = true;
                            continue;
                        }
                                                                                if (o == 'xiangqing') {
                            this.ruleForm.xiangqing= obj[o];
                            this.ro.xiangqing = true;
                            continue;
                        }
                                                                                if (o == 'dianpumingcheng') {
                            this.ruleForm.dianpumingcheng= obj[o];
                            this.ro.dianpumingcheng = true;
                            continue;
                        }
                                                                                if (o == 'userid') {
                            this.ruleForm.userid= obj[o];
                            this.ro.userid = true;
                            continue;
                        }
                                                                                if (o == 'price') {
                            this.ruleForm.price= obj[o];
                            this.ro.price = true;
                            continue;
                        }
                                                                                if (o == 'onelimittimes') {
                            this.ruleForm.onelimittimes= obj[o];
                            this.ro.onelimittimes = true;
                            continue;
                        }
                                                                                if (o == 'alllimittimes') {
                            this.ruleForm.alllimittimes= obj[o];
                            this.ro.alllimittimes = true;
                            continue;
                        }
                                                                                if (o == 'sfsh') {
                            this.ruleForm.sfsh= obj[o];
                            this.ro.sfsh = true;
                            continue;
                        }
                                                                                if (o == 'shhf') {
                            this.ruleForm.shhf= obj[o];
                            this.ro.shhf = true;
                            continue;
                        }
                                                                                if (o == 'thumbsupnum') {
                            this.ruleForm.thumbsupnum= obj[o];
                            this.ro.thumbsupnum = true;
                            continue;
                        }
                                                                                if (o == 'crazilynum') {
                            this.ruleForm.crazilynum= obj[o];
                            this.ro.crazilynum = true;
                            continue;
                        }
                                                }
            var statusColumnName = uni.getStorageSync('statusColumnName');
            var statusColumnValue = uni.getStorageSync('statusColumnValue');
            if (statusColumnName != '') {
                var obj = JSON.parse(uni.getStorageSync('crossObj'));
                for (var o in obj) {
                    if (o == statusColumnName) {
                        obj[o] = statusColumnValue;
                    }
                }
                var tableName = uni.getStorageSync('crossTable');
                await this.$api.update(tableName, obj);
            }
        }
        this.styleChange()
    },
    methods: {
        styleChange() {
            this.$nextTick(() => {
                // document.querySelectorAll('.app-update-pv .cu-form-group .uni-input-input').forEach(el=>{
                //   el.style.backgroundColor = this.addUpdateForm.input.content.backgroundColor
                // })
            })
        },

        // 多级联动参数
        // 日长控件选择日期时间
                                                                                                                                                                                                                                                                                                                                                                                                                                            // 下拉变化
                    fenleimingchengChange(e) {
                this.fenleimingchengIndex = e.target.value
                this.ruleForm.fenleimingcheng = this.fenleimingchengOptions[this.fenleimingchengIndex]
            },
                                                                        // 下拉变化
                    dianpumingchengChange(e) {
                this.dianpumingchengIndex = e.target.value
                this.ruleForm.dianpumingcheng = this.dianpumingchengOptions[this.dianpumingchengIndex]
            },
                                                                                                                                                                                                                                                                                        tupianTap() {
                let _this = this;
                    this.$api.upload(function (res) {
                    _this.ruleForm.tupian = _this.$base.url + 'upload/' + res.file;
                    _this.$forceUpdate();
                    _this.$nextTick(() => {
                        _this.styleChange()
                })
                });
            },
                                                                                                                                                                                                                                                                            getUUID() {
            return new Date().getTime();
        },
        async onSubmitTap() {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (this.ruleForm.id) {
                await this.$api.update(`shangpinguanli`, this.ruleForm);
            } else {
                await this.$api.add(`shangpinguanli`, this.ruleForm);
            }
            this.$utils.msgBack('提交成功');
        },
        optionsChange(e) {
            this.index = e.target.value
        },
        bindDateChange(e) {
            this.date = e.target.value
        },
        getDate(type) {
            const date = new Date();
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            let day = date.getDate();
            if (type === 'start') {
                year = year - 60;
            } else if (type === 'end') {
                year = year + 2;
            }
            month = month > 9 ? month : '0' + month;
            ;
            day = day > 9 ? day : '0' + day;
            return `${year}-${month}-${day}`;
        },
        toggleTab(str) {
                this.$refs[str].show();
        }
    }
    }
</script>

<style lang="scss" scoped>
    .cu-form-group .title {
        min-width: calc(4em + 15px);
    }
    /*.container {*/
        /*padding: 20upx;*/
    /*}*/

    /*.content:after {*/
        /*position: fixed;*/
        /*top: 0;*/
        /*right: 0;*/
        /*left: 0;*/
        /*bottom: 0;*/
        /*content: '';*/
        /*background-attachment: fixed;*/
        /*background-size: cover;*/
        /*background-position: center;*/
    /*}*/

    /*textarea {*/
        /*border: 1upx solid #EEEEEE;*/
        /*border-radius: 20upx;*/
        /*padding: 20upx;*/
    /*}*/

    /*.title {*/
        /*width: 180upx;*/
    /*}*/

    /*.avator {*/
        /*width: 150upx;*/
        /*height: 60upx;*/
    /*}*/

    /*.right-input {*/
        /*flex: 1;*/
        /*text-align: left;*/
        /*padding: 0 24upx;*/
    /*}*/

    /*.cu-form-group.active {*/
        /*justify-content: space-between;*/
    /*}*/

    /*.btn {*/
        /*display: flex;*/
        /*align-items: center;*/
        /*justify-content: center;*/
        /*flex-wrap: wrap;*/
        /*padding: 20upx 0;*/
    /*}*/

    /*.cu-form-group {*/
        /*padding: 0 24upx;*/
        /*background-color: transparent;*/
        /*min-height: inherit;*/
    /*}*/

    /*.cu-form-group+.cu-form-group {*/
        /*border: 0;*/
    /*}*/

    /*.cu-form-group uni-input {*/
        /*padding: 0 30upx;*/
    /*}*/

    /*.uni-input {*/
        /*padding: 0 30upx;*/
    /*}*/

    /*.cu-form-group uni-textarea {*/
        /*padding: 30upx;*/
        /*margin: 0;*/
    /*}*/

    /*.cu-form-group uni-picker::after {*/
        /*line-height: 88rpx;*/
    /*}*/

    /*.select .uni-input {*/
        /*line-height: 88rpx;*/
    /*}*/

    /*.input .right-input {*/
        /*line-height: 88rpx;*/
    /*}*/
</style>
