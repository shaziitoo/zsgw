<template>
  <view class="comment-list">
    <scroll-view scroll-y class="comment-scroll" :style="{ height: scrollViewHeight }">
      <view v-for="comment in commentss" :key="comment.id" class="comment-item">
        <view class="user-info">
          <image src="https://randomuser.me/api/portraits/women/4.jpg" class="avatar"></image>
          <view class="user-details">
            <text class="username">{{ comment.nickname }}</text>
            <text class="time">{{ comment.addtime }}</text>
          </view>
        </view>
        <view class="comment-content">{{ comment.content }}</view>
        
       
      </view>
    </scroll-view>

    <view class="reply-input-container" :style="{ bottom: keyboardHeight + 'px' }">
      <input
        class="reply-input"
        v-model="ruleForm.content"
        :placeholder="replyPlaceholder"
        @focus="onInputFocus"
        @blur="onInputBlur"
        :adjust-position="false"
        cursor-spacing="10"
      />
      <button class="send-button" @tap="sendReply" :disabled="!ruleForm.content.trim()">发送</button>
    </view>
  </view>
</template>

<script>

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
		  user:{},
      content: '',
      replyPlaceholder: '说点什么...',
      currentReplyTo: null,
      isReplyInputFocused: false,
      keyboardHeight: 0,
      scrollViewHeight: '100vh'
    }
  },
  props: {
  			   commentss: {
  			       type: Array,
  			       default: function () {
  			       return [];
  			       }
  			     },
				 refid:String
  },

  

  methods: {
	  
	 async sendReply(){
		  let table = uni.getStorageSync("nowTable");
		  // 获取用户信息
		  let res = await this.$api.session(table);
		  this.user = res.data;
		  
		  this.ruleForm.refid = this.refid;
		  this.ruleForm.userid=this.user.id;
		  this.ruleForm.nickname=this.user.yonghuming
		  
		  
		  if (this.ruleForm.id) {
		            await this.$api.update(`discussqiyexinwen`, this.ruleForm);
		        } else {
		            await this.$api.add(`discussqiyexinwen`, this.ruleForm);
		        }
		        this.$utils.msg('提交成功');
				this.ruleForm.content="";
				// this.$emit('childByValue', this.refid);
				 
				
				
		    },
		  
	 
	  
	  
    // likeComment(comment) {
    //   comment.isLiked = !comment.isLiked;
    //   comment.likes += comment.isLiked ? 1 : -1;
    // },

   
    // focusReplyInput() {
    //   this.$nextTick(() => {
    //     const inputElement = uni.createSelectorQuery().select('.reply-input');
    //     inputElement.boundingClientRect(rect => {
    //       uni.pageScrollTo({
    //         scrollTop: rect.top,
    //         duration: 300
    //       });
    //     }).exec();
    //   });
    // },
    // sendReply() {
    //   if (!this.replyContent.trim()) return;

    //   const newReply = {
    //     id: Date.now(),
    //     username: '当前用户',
    //     avatar: 'https://randomuser.me/api/portraits/women/4.jpg',
    //     content: this.replyContent,
    //     likes: 0,
    //     isLiked: false,
    //     time: '刚刚',
    //     replyTo: this.currentReplyTo ? this.currentReplyTo.username : null
    //   };

    //   if (this.currentReplyTo) {
    //     if (!this.currentReplyTo.replies) {
    //       this.currentReplyTo.replies = [];
    //     }
    //     this.currentReplyTo.replies.push(newReply);
    //   } else {
    //     this.comments.unshift({
    //       ...newReply,
    //       replies: []
    //     });
    //   }

    //   this.replyContent = '';
    //   this.replyPlaceholder = '说点什么...';
    //   this.currentReplyTo = null;
    // },
    onInputFocus(e) {
      this.isReplyInputFocused = true;
      this.keyboardHeight = e.detail.height || 0;
      this.adjustScrollViewHeight();
    },
    onInputBlur() {
      this.isReplyInputFocused = false;
      this.keyboardHeight = 0;
      this.adjustScrollViewHeight();
    },
    adjustScrollViewHeight() {
      const query = uni.createSelectorQuery().in(this);
      query.select('.reply-input-container').boundingClientRect(data => {
        const inputHeight = data.height;
        const windowHeight = uni.getSystemInfoSync().windowHeight;
        this.scrollViewHeight = `${windowHeight - inputHeight - this.keyboardHeight}px`;
      }).exec();
    }
  },
  mounted() {
    this.adjustScrollViewHeight();
  }
}
</script>

<style scoped>
.comment-list {
  display: flex;
  flex-direction: column;
  height: 100vh;
  
  
}

.comment-scroll {
  flex: 1;
  background-color: #f8f8f8;
}

.comment-item {
  margin-bottom: 20px;
  background-color: #ffffff;
  border-radius: 8px;
  padding: 15px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  margin: 30rpx;
}

.user-info {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}

.user-details {
  display: flex;
  flex-direction: column;
}

.username {
  font-weight: bold;
  font-size: 14px;
  color: #333;
}

.time {
  font-size: 12px;
  color: #999;
  margin-top: 2px;
}

.comment-content, .reply-content {
  font-size: 14px;
  color: #333;
  margin-bottom: 10px;
  line-height: 1.4;
}

.comment-actions, .reply-actions {
  display: flex;
  justify-content: flex-end;
}

.action {
  display: flex;
  align-items: center;
  margin-left: 15px;
}

.iconfont {
  font-size: 16px;
  color: #999;
}

.icon-like.liked {
  color: #ff2c55;
}

.count {
  font-size: 12px;
  color: #999;
  margin-left: 4px;
}

.reply-list {
  margin-top: 15px;
  padding-top: 15px;
  border-top: 1px solid #f0f0f0;
}

.reply-item {
  margin-bottom: 15px;
}

.reply-to {
  color: #576b95;
  font-weight: bold;
}

.reply-input-container {
  position: fixed;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  padding: 10px 15px;
  background-color: #f8f8f8;
  border-top: 1px solid #e0e0e0;
}

.reply-input {
  flex: 1;
  height: 36px;
  background-color: #fff;
  border-radius: 18px;
  padding: 0 15px;
  font-size: 14px;
}

.send-button {
  margin-left: 10px;
  background-color: #ff2c55;
  color: #fff;
  border: none;
  border-radius: 18px;
  padding: 0 15px;
  height: 36px;
  line-height: 36px;
  font-size: 14px;
}

.send-button:disabled {
  background-color: #ffa5b5;
}
</style>