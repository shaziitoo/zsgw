<template>
  <div class="hm-news-detail">
    <div class="hd">
      <img class="yunshu" :src="options.tupian" />
      <div class="container">
        <div class="outer">
          <span class="author">{{ options.biaoti }}</span>
         
        </div>
        <span class="time">{{ options.addtime }}</span>
      </div>
    </div>
    <span class="title">{{ options.title }}</span>
    <div class="main">
      <div class="entryPicWrap"><img class="img" :src="options.tupian" /></div>
    </div>
    <div class="ft">
      <span class="content" v-html="options.neirong"></span>
    </div>
  </div>
</template>
<script>
export default {
  name: 'HmNewsDetail',
  props: {
    dataId: {
      type: String,
      default: 'hm-news-detail'
    },
    options: {
      type: Object,
    }
  },
  data() {
    return {};
  },
  methods: {
    
  }
};
</script>
<style>
@import './index.response.css';
</style>
