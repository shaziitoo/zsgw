<template>
    <view class="content">
        <form>
                                        <view class="cu-form-group">
                    <view class="title">评论内容</view>
                    <textarea v-model="ruleForm.content" placeholder="评论内容"></textarea>
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
                                                                                                                                refid:'',
                                                                                                            userid:'',
                                                                                                            nickname:'',
                                                                                                            content:'',
                                                                                                            reply:'',
                                                        },
                                                                                                                                                                                                                                // 登陆用户信息
                user: {},
            ro: {
                                                                                                                                    refid:false,
                                                                                userid:false,
                                                                                nickname:false,
                                                                                content:false,
                                                                                reply:false,
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
            res = await this.$api.info(`discussdianpuxinxi`, this.ruleForm.id);
            this.ruleForm = res.data;
        }
        // 跨表
        this.cross = options.cross;
        if (options.cross) {
             var obj = JSON.parse(uni.getStorageSync('crossObj'));
            for (var o in obj) {
                                                                                                                                    if (o == 'refid') {
                            this.ruleForm.refid= obj[o];
                            this.ro.refid = true;
                            continue;
                        }
                                                                                if (o == 'userid') {
                            this.ruleForm.userid= obj[o];
                            this.ro.userid = true;
                            continue;
                        }
                                                                                if (o == 'nickname') {
                            this.ruleForm.nickname= obj[o];
                            this.ro.nickname = true;
                            continue;
                        }
                                                                                if (o == 'content') {
                            this.ruleForm.content= obj[o];
                            this.ro.content = true;
                            continue;
                        }
                                                                                if (o == 'reply') {
                            this.ruleForm.reply= obj[o];
                            this.ro.reply = true;
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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                    getUUID() {
            return new Date().getTime();
        },
        async onSubmitTap() {
                                                                                                                                                                                                                            if (this.ruleForm.id) {
                await this.$api.update(`discussdianpuxinxi`, this.ruleForm);
            } else {
                await this.$api.add(`discussdianpuxinxi`, this.ruleForm);
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
