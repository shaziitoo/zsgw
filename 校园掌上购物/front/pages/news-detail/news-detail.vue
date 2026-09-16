<template>
    <view>
                <view class="title">
            {{detail.title}}
        </view>
        <view class="content">
            <rich-text :nodes="detail.content"></rich-text>
        </view>
    </view>
</template>
<script>
    export default {
        data() {
            return {
                detail: {},
                id: '',
            }
        },
        async onLoad(options) {
            this.id = options.id;
            let res = await this.$api.info('news', options.id)
            this.detail = res.data;
        }
    }
</script>

<style scoped>
    page {
        background: #FFFFFF;
    }

    .title {
        text-align: center;
        font-size: 40 upx;
        font-weight: bold;
        margin: 20 upx;
    }

    .content {
        margin: 40 upx;
        font-size: 30 upx;
        line-height: 50 upx;
        letter-spacing: 5 upx;
    }
</style>
