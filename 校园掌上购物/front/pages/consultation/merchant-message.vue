<template>
  <view class="page">
    <scroll-view class="scroll-view" scroll-y scroll-with-animation :scroll-top="top">
      <view style="padding: 30rpx 30rpx 240rpx;">
        <view class="message" :class="[item.userType]" v-for="(item,index) in list" :key="index">
          <image src="/static/avatar/avatar1.png" v-if="item.userType === 'friend'" class="avatar" mode="widthFix"></image>
          <view class="content" v-if="item.messageType === 'image'">
            <image :src="item.content" mode="widthFix"></image>
          </view>
          <view class="content" v-else>
            {{ item.content }}
          </view>
          <image :src="item.avatar" v-if="item.userType === 'self'" class="avatar" mode="widthFix"></image>
        </view>
      </view>
    </scroll-view>
    <view class="tool">
      <input type="text" v-model="content" class="input" @confirm="sendMessage" />
      <button class="send-btn" @click="sendMessage" :disabled="!content">发送</button>
    </view>
  </view>
</template>

<script>
import http from '../../api/http'

export default {
  data() {
    return {
      content: '',
      list: [],
      top: 0,
      userId: '',
      srcUserId: '',
      srcUserNicheng: '',
      dianpuId: '',
      ws: this.$store.state.ws,
      userNicheng: ''
    };
  },
  onLoad(options) {
    uni.setNavigationBarTitle({
      title: options.name + ' (咨询)'
    })
    this.srcUserId = options.srcUserId
    this.srcUserNicheng = options.srcUserNicheng
    this.userId = options.id
    this.dianpuId = options.dianpuId
    this._friendAvatar = '/static/avatar/avatar1.png'
    this._selfAvatar = '/static/avatar/avatar5.jpeg'

    // 获取当前商家昵称
    let table = uni.getStorageSync("nowTable");
    this.$api.session(table).then(res => {
      this.userNicheng = res.data.nicheng
      // 标记已读消息
      this.markAsRead()
    })

    this.ws.onOpen(() => {
      console.log('WebSocket连接已打开');
    })
    this.ws.onMessage((res) => {
      let message = JSON.parse(res.data)
      // 只接收发给这个商家的消息
      if (message.targetUserId == this.srcUserId && message.srcUserId == this.userId) {
        this.list.push({
          content: message.msg,
          userType: 'friend',
          avatar: this._friendAvatar
        })
        this.scrollToBottom()
      }
    });
    this.ws.onError((event) => {
      console.error('WebSocket连接发生错误', event);
    });
    this.ws.onClose((event) => {
      console.log('WebSocket连接已关闭', event);
    });

    this.getHistoryMsg();
  },
  methods: {
    getHistoryMsg() {
      http.request({
        url:`consultation/querySomeBodyMsg`,
        method:'get',
        data: {sender: this.userId}
      }).then(resp => {
        let data = resp.data;

        for (let i = 0; i < data.length; i++) {
          if(data[i]['sender'] != this.userId) {
            this.list.push({
              content: data[i]['msg'],
              userType: 'self',
              avatar: this._selfAvatar
            })
          } else {
            this.list.push({
              content: data[i]['msg'],
              userType: 'friend',
              avatar: this._friendAvatar
            })
          }
        }
        this.scrollToBottom()
      })
    },
    sendMessage() {
      if (!this.content.trim()) {
        uni.showToast({
          title: '请输入内容',
          icon: 'none'
        });
        return;
      }
      if (this.ws) {
        let message = {
          type: 'MESSAGE',
          srcUserId: this.srcUserId,
          srcUserNicheng: this.srcUserNicheng,
          targetUserId: this.userId,  //给用户发消息
          dianpuId: this.dianpuId,    //店铺ID
          msg: this.content
        };

        this.ws.send({data: JSON.stringify(message)});

        this.list.push({
          content: this.content,
          userType: 'self',
          avatar: this._selfAvatar
        })
        this.content = ''
        this.scrollToBottom()
      } else {
        console.error('WebSocket未连接');
      }
    },
    scrollToBottom() {
      this.top = this.list.length * 1000
    },
    markAsRead() {
      // 标记来自该用户的消息为已读
      http.request({
        url: 'consultation/update',
        method: 'post',
        data: {
          sender: this.userId
        }
      }).then(res => {
        console.log('Messages marked as read')
      }).catch(err => {
        console.error('Failed to mark messages as read', err)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.scroll-view {
  /* #ifdef H5 */
  height: calc(100vh - 44px);
  /* #endif */
  /* #ifndef H5 */
  height: 100vh;
  /* #endif */
  background: #eee;
  box-sizing: border-box;
}
.message {
  display: flex;
  align-items: flex-start;
  margin-bottom: 30rpx;

  .avatar {
    width: 80rpx;
    height: 80rpx;
    border-radius: 10rpx;
    margin-right: 30rpx;
  }
  .content {
    min-height: 80rpx;
    max-width: 60vw;
    box-sizing: border-box;
    font-size: 28rpx;
    line-height: 1.3;
    padding: 20rpx;
    border-radius: 10rpx;
    background: #fff;
    image {
      width: 200rpx;
    }
  }
  &.self {
    justify-content: flex-end;
    .avatar {
      margin: 0 0 0 30rpx;
    }
    .content {
      position: relative;
      &::after {
        position: absolute;
        content: '';
        width: 0;
        height: 0;
        border: 16rpx solid transparent;
        border-left: 16rpx solid #fff;
        right: -28rpx;
        top: 24rpx;
      }
    }
  }
  &.friend {
    .content {
      position: relative;
      &::after {
        position: absolute;
        content: '';
        width: 0;
        height: 0;
        border: 16rpx solid transparent;
        border-right: 16rpx solid #fff;
        left: -28rpx;
        top: 24rpx;
      }
    }
  }
}

.tool {
  position: fixed;
  width: 100%;
  min-height: 120rpx;
  left: 0;
  bottom: 0;
  background: #fff;
  display: flex;
  align-items: flex-start;
  box-sizing: border-box;
  padding: 20rpx 24rpx 20rpx 40rpx;
  padding-bottom: calc(20rpx + constant(safe-area-inset-bottom)/2) !important;
  padding-bottom: calc(20rpx + env(safe-area-inset-bottom)/2) !important;
  .input {
    background: #eee;
    border-radius: 10rpx;
    height: 70rpx;
    margin-right: 30rpx;
    flex: 1;
    padding: 0 20rpx;
    box-sizing: border-box;
    font-size: 28rpx;
  }
  .send-btn {
    background: #4a6cf7;
    color: #fff;
    border: none;
    border-radius: 10rpx;
    height: 70rpx;
    padding: 0 30rpx;
    font-size: 28rpx;
    font-weight: 600;
    transition: all 0.3s ease;

    &:disabled {
      background: #ccc;
      opacity: 0.6;
    }

    &:active:not(:disabled) {
      transform: scale(0.95);
      background: #3a5ce7;
    }
  }
}
</style>
