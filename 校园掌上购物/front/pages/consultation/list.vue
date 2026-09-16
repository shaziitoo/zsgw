<template>
  <view class="page">
    <!-- 搜索栏 -->
    <view class="search-bar">
      <view class="search-input-group">
        <text class="cuIcon-search search-icon"></text>
        <input
          v-model="searchKeyword"
          type="text"
          placeholder="搜索用户昵称..."
          class="search-input"
          @confirm="search"
        ></input>
      </view>
      <button class="search-btn" @tap="search">搜索</button>
    </view>

    <!-- 咨询列表 -->
    <view class="consultation-list">
      <view
        v-if="consultationList.length > 0"
        class="consultation-item"
        v-for="(item, index) in consultationList"
        :key="index"
        @click="openChat(item)"
      >
        <view class="item-header">
          <view class="user-info">
            <image src="/static/avatar/avatar1.png" class="avatar"></image>
            <view class="user-details">
              <view class="user-name">{{ item.srcUserNicheng }}</view>
              <view class="user-id">用户ID: {{ item.srcUserId }}</view>
            </view>
          </view>
          <view class="time">{{ item.time }}</view>
        </view>
        <view class="item-content">
          <text class="last-msg">{{ item.lastMsg }}</text>
        </view>
        <view v-if="item.unread > 0" class="unread-badge">{{ item.unread }}</view>
      </view>

      <!-- 空状态 -->
      <view v-else class="empty-state">
        <view class="empty-icon">
          <text class="cuIcon-service"></text>
        </view>
        <text class="empty-text">暂无咨询消息</text>
        <text class="empty-subtext">用户咨询时会显示在这里</text>
      </view>
    </view>
  </view>
</template>

<script>
import http from '../../api/http'

export default {
  data() {
    return {
      consultationList: [],
      searchKeyword: '',
      ws: null,
      merchantId: '',
      merchantNicheng: ''
    }
  },
  async onLoad(options) {
    this.merchantId = options.receiver
    this.merchantNicheng = options.receiverName
    this.initWebSocket()
    await this.loadConsultationListFromDB()
  },
  onShow() {
    this.loadConsultationList()
  },
  methods: {
    initWebSocket() {
      // 连接到 WebSocket
      this.ws = this.$store.state.ws

      if (this.ws) {
        this.ws.onMessage((res) => {
          let message = JSON.parse(res.data)
          // 如果是发给这个商家的咨询消息
          if (message.type === 'MESSAGE' && message.targetUserId == this.merchantId) {
            this.addOrUpdateConsultation(message)
          }
        })
      }
    },
    loadConsultationList() {
      // 从本地存储或 API 加载咨询列表
      let stored = uni.getStorageSync('consultationList_' + this.merchantId)
      if (stored) {
        this.consultationList = JSON.parse(stored)
      }
    },
    async loadConsultationListFromDB() {
      try {
        let params = {
          page: 1,
          limit: 100,
          targetUserId: this.merchantId
        }
        let res = await http.request({
          url: 'consultation/queryList',
          method: 'get',
          data: params
        })

        if (res.data && res.data.length > 0) {
          // 按用户分组，每个用户只显示最后一条消息
          let groupedByUser = {}
          res.data.forEach(msg => {
            let userId = msg.sender
            if (!groupedByUser[userId]) {
              groupedByUser[userId] = msg
            } else {
              // 保留最新的消息
              if (new Date(msg.sendTime) > new Date(groupedByUser[userId].sendTime)) {
                groupedByUser[userId] = msg
              }
            }
          })

          // 转换为列表格式
          this.consultationList = Object.values(groupedByUser).map(msg => ({
            srcUserId: msg.sender,
            srcUserNicheng: msg.senderName,
            dianpuId: msg.dianpuId,
            lastMsg: msg.msg,
            time: this.formatTime(msg.sendTime),
            unread: 0
          }))

          // 按时间倒序排列
          this.consultationList.sort((a, b) => new Date(b.time) - new Date(a.time))

          // 保存到本地存储
          uni.setStorageSync('consultationList_' + this.merchantId, JSON.stringify(this.consultationList))
        }
      } catch(e) {
        console.error('Failed to load consultation list from DB', e)
      }
    },
    formatTime(dateStr) {
      let date = new Date(dateStr)
      let hours = date.getHours()
      let minutes = date.getMinutes()
      return hours + ':' + (minutes < 10 ? '0' : '') + minutes
    },
    addOrUpdateConsultation(message) {
      // 查找是否已存在该用户的咨询
      let existingIndex = this.consultationList.findIndex(
        item => item.srcUserId === message.srcUserId
      )

      let now = new Date()
      let timeStr = now.getHours() + ':' + (now.getMinutes() < 10 ? '0' : '') + now.getMinutes()

      if (existingIndex >= 0) {
        // 更新现有咨询
        this.consultationList[existingIndex].lastMsg = message.msg
        this.consultationList[existingIndex].time = timeStr
        this.consultationList[existingIndex].unread++
        // 移到顶部
        let item = this.consultationList.splice(existingIndex, 1)[0]
        this.consultationList.unshift(item)
      } else {
        // 添加新咨询
        this.consultationList.unshift({
          srcUserId: message.srcUserId,
          srcUserNicheng: message.srcUserNicheng,
          dianpuId: message.dianpuId,
          lastMsg: message.msg,
          time: timeStr,
          unread: 1
        })
      }

      // 保存到本地存储
      uni.setStorageSync('consultationList_' + this.merchantId, JSON.stringify(this.consultationList))
    },
    search() {
      if (!this.searchKeyword.trim()) {
        this.loadConsultationList()
        return
      }

      this.consultationList = this.consultationList.filter(item =>
        item.srcUserNicheng.includes(this.searchKeyword)
      )
    },
    openChat(item) {
      uni.navigateTo({
        url: `/pages/consultation/merchant-message?name=${item.srcUserNicheng}&id=${item.srcUserId}&srcUserId=${this.merchantId}&srcUserNicheng=${this.merchantNicheng}&dianpuId=${item.dianpuId}`
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.page {
  background: #f5f7fa;
  min-height: 100vh;
  padding-bottom: 20rpx;
}

.search-bar {
  display: flex;
  gap: 16rpx;
  padding: 20rpx 24rpx;
  background: #fff;
  border-bottom: 1rpx solid #e8eaed;
}

.search-input-group {
  flex: 1;
  display: flex;
  align-items: center;
  background: #f5f7fa;
  border-radius: 20rpx;
  padding: 0 20rpx;
  height: 72rpx;
}

.search-icon {
  font-size: 28rpx;
  color: #4a6cf7;
  margin-right: 12rpx;
}

.search-input {
  flex: 1;
  height: 100%;
  font-size: 28rpx;
  background: transparent;

  &::placeholder {
    color: #a0aec0;
  }
}

.search-btn {
  background: #4a6cf7 !important;
  color: #fff !important;
  border: none;
  border-radius: 20rpx;
  height: 72rpx;
  padding: 0 32rpx;
  font-size: 28rpx;
  font-weight: 600;
}

.consultation-list {
  padding: 0;
}

.consultation-item {
  background: #fff;
  margin: 12rpx 24rpx;
  padding: 24rpx;
  border-radius: 16rpx;
  box-shadow: 0 2rpx 8rpx rgba(0, 0, 0, 0.06);
  transition: all 0.3s ease;
  position: relative;

  &:active {
    transform: translateY(-2rpx);
    box-shadow: 0 4rpx 16rpx rgba(0, 0, 0, 0.1);
  }
}

.item-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 16rpx;
}

.user-info {
  display: flex;
  gap: 16rpx;
  flex: 1;
}

.avatar {
  width: 80rpx;
  height: 80rpx;
  border-radius: 12rpx;
  flex-shrink: 0;
}

.user-details {
  flex: 1;
}

.user-name {
  font-size: 30rpx;
  font-weight: 600;
  color: #2c3e50;
  margin-bottom: 8rpx;
}

.user-id {
  font-size: 24rpx;
  color: #a0aec0;
}

.time {
  font-size: 24rpx;
  color: #a0aec0;
  margin-left: 16rpx;
}

.item-content {
  margin-top: 12rpx;
}

.last-msg {
  font-size: 26rpx;
  color: #718096;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.unread-badge {
  position: absolute;
  top: 24rpx;
  right: 24rpx;
  background: #ff6b6b;
  color: #fff;
  border-radius: 50%;
  width: 40rpx;
  height: 40rpx;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22rpx;
  font-weight: 600;
}

.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 120rpx 0;
  text-align: center;
}

.empty-icon {
  width: 120rpx;
  height: 120rpx;
  background: linear-gradient(135deg, rgba(74, 108, 247, 0.1) 0%, rgba(74, 108, 247, 0.05) 100%);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 32rpx;

  .cuIcon-service {
    font-size: 56rpx;
    color: #4a6cf7;
    opacity: 0.7;
  }
}

.empty-text {
  font-size: 32rpx;
  font-weight: 600;
  color: #2c3e50;
  margin-bottom: 12rpx;
}

.empty-subtext {
  font-size: 26rpx;
  color: #95a5a6;
}
</style>
