<script setup lang="ts">
  import {defineAsyncComponent, reactive, ref, toRefs} from 'vue';
  import base from "@/utils/base";
  import {uploadImg} from '@/api/common/media';
  import request from "@/utils/request";
  import {notify} from '@/utils/element'
  import {Session} from '@/utils/storage';

  const Editor = defineAsyncComponent(() => import('@/components/editor/index.vue'));
  const formRef = ref();
  const state = reactive({
    loadding: false,//加载框
    visible: false,//隐藏显示
    formData: {} as any,
                                                                                                                                                                                                                                title: '注册',
    tableName: ''
  })
  const initData = {}
  const {
    loadding,
    visible,
    formData,
    title,
    tableName,
                                                                                                                                                                                                                              } = {...toRefs(state)};


  function open(tableName: string) {

    state.tableName = tableName;
    state.visible = true;
  }


  const emit = defineEmits(['refresh']);

  const close = () => {
    state.visible = false;
  }

  function submitForm() {
    formRef.value?.validate((valid: boolean) => {

      if (!valid) return;
      state.loadding = true;
      request({
        url: `${state.tableName}/register`,
        method: 'post',
        data: state.formData
      }).then(({data}) => {
        notify("注册成功", {type: 'success'});
        state.loadding = false;
        close();

      })


    })

  }

  defineExpose({
    open
  })









                  
              
              
              
              
              
              
      
                  
              
              
              
              
              
              
      
    

</script>

<template>
  <el-dialog :title="''" v-model="visible"
             center draggable :before-close="close" width="400px"
             :close-on-click-modal="false" destroy-on-close
             class="register-dialog-custom"
             :style="{ background: '#2C3E50', border: 'none', boxShadow: 'none' }">
    <div v-loading="loadding" class="register-card">
      <div class="form-header">Register</div>
      <el-form ref="formRef" :model="formData"
               label-width="85px" label-right="right"
               label-suffix=":" status-icon
               class="register-form">
                                                                                        <el-form-item v-if="tableName=='yonghu'" label="账号"
                            prop="zhanghao"
                            :rules="{required: true, message: '账号为必填项！', trigger: 'blur'}">
                <div class="input-group-meetup">
                  <i class="fas fa-user input-icon-meetup"></i>
                  <el-input v-model="formData.zhanghao" maxlength="30" clearable show-word-limit
                            placeholder="请输入账号" class="input-meetup"/>
                </div>
              </el-form-item>
                                                <el-form-item v-if="tableName=='yonghu'" label="密码"
                            prop="mima"
                            :rules="{required: true, message: '密码为必填项！', trigger: 'blur'}">
                <div class="input-group-meetup">
                  <i class="fas fa-user input-icon-meetup"></i>
                  <el-input v-model="formData.mima" maxlength="30" clearable show-word-limit
                            placeholder="请输入密码" class="input-meetup"/>
                </div>
              </el-form-item>
                                                <el-form-item v-if="tableName=='yonghu'" label="手机号"
                            prop="shoujihao"
                            :rules="{required: true, message: '手机号为必填项！', trigger: 'blur'}">
                <div class="input-group-meetup">
                  <i class="fas fa-user input-icon-meetup"></i>
                  <el-input v-model="formData.shoujihao" maxlength="30" clearable show-word-limit
                            placeholder="请输入手机号" class="input-meetup"/>
                </div>
              </el-form-item>
                                                <el-form-item v-if="tableName=='yonghu'" label="地址"
                            prop="dizhi"
                            :rules="{required: true, message: '地址为必填项！', trigger: 'blur'}">
                <div class="input-group-meetup">
                  <i class="fas fa-user input-icon-meetup"></i>
                  <el-input v-model="formData.dizhi" maxlength="30" clearable show-word-limit
                            placeholder="请输入地址" class="input-meetup"/>
                </div>
              </el-form-item>
                                                                                                                                    <el-form-item v-if="tableName=='shangjia'" label="账号"
                            prop="zhanghao"
                            :rules="{required: true, message: '账号为必填项！', trigger: 'blur'}">
                <div class="input-group-meetup">
                  <i class="fas fa-user input-icon-meetup"></i>
                  <el-input v-model="formData.zhanghao" maxlength="30" clearable show-word-limit
                            placeholder="请输入账号" class="input-meetup"/>
                </div>
              </el-form-item>
                                                <el-form-item v-if="tableName=='shangjia'" label="密码"
                            prop="mima"
                            :rules="{required: true, message: '密码为必填项！', trigger: 'blur'}">
                <div class="input-group-meetup">
                  <i class="fas fa-user input-icon-meetup"></i>
                  <el-input v-model="formData.mima" maxlength="30" clearable show-word-limit
                            placeholder="请输入密码" class="input-meetup"/>
                </div>
              </el-form-item>
                                                <el-form-item v-if="tableName=='shangjia'" label="手机号"
                            prop="shoujihao"
                            :rules="{required: true, message: '手机号为必填项！', trigger: 'blur'}">
                <div class="input-group-meetup">
                  <i class="fas fa-user input-icon-meetup"></i>
                  <el-input v-model="formData.shoujihao" maxlength="30" clearable show-word-limit
                            placeholder="请输入手机号" class="input-meetup"/>
                </div>
              </el-form-item>
                                                <el-form-item v-if="tableName=='shangjia'" label="商家名称"
                            prop="shangjiamingcheng"
                            :rules="{required: true, message: '商家名称为必填项！', trigger: 'blur'}">
                <div class="input-group-meetup">
                  <i class="fas fa-user input-icon-meetup"></i>
                  <el-input v-model="formData.shangjiamingcheng" maxlength="30" clearable show-word-limit
                            placeholder="请输入商家名称" class="input-meetup"/>
                </div>
              </el-form-item>
                                                          </el-form>
      <div class="register-btns">
        <el-button @click="close" class="register-cancel">取 消</el-button>
        <el-button @click="submitForm" type="primary" class="register-submit">注 册</el-button>
      </div>
    </div>
  </el-dialog>
</template>

<style lang="scss" scoped>

  .register-dialog-custom .el-dialog__wrapper {
    border-radius: 8px;
    overflow: hidden;
    background: #2C3E50 !important;
  }

  /* 移除对话框的默认白色背景和边框 */
  .register-dialog-custom .el-dialog {
    background: #2C3E50 !important;
    border: none !important;
    box-shadow: none !important;
    margin: 0 !important;
    padding: 0 !important;
  }

  /* 移除对话框头部的默认样式 */
  .register-dialog-custom .el-dialog__header {
    display: none !important;
    padding: 0 !important;
    margin: 0 !important;
  }

  /* 确保对话框内容区域没有默认背景 */
  .register-dialog-custom .el-dialog__body {
    background: #2C3E50 !important;
    padding: 0 !important;
    margin: 0 !important;
    border: none !important;
  }

  /* 移除对话框容器的所有默认样式 */
  .register-dialog-custom .el-dialog__container {
    background: #2C3E50 !important;
    border: none !important;
    box-shadow: none !important;
    padding: 0 !important;
    margin: 0 !important;
  }

  /* 强制移除所有可能的边框 */
  .register-dialog-custom * {
    border: none !important;
    outline: none !important;
  }

  /* 特别针对可能的白色边框 */
  .register-dialog-custom .el-dialog__wrapper,
  .register-dialog-custom .el-dialog,
  .register-dialog-custom .el-dialog__body,
  .register-dialog-custom .el-dialog__container {
    background: #2C3E50 !important;
    border: none !important;
    box-shadow: none !important;
    outline: none !important;
  }

  /* 移除Element Plus的默认样式 */
  .register-dialog-custom .el-dialog__wrapper {
    background: #2C3E50 !important;
  }

  /* 确保没有任何白色背景 */
  .register-dialog-custom {
    background: #2C3E50 !important;
  }

  /* 使用更高优先级的选择器 */
  body .register-dialog-custom .el-dialog,
  body .register-dialog-custom .el-dialog__wrapper,
  body .register-dialog-custom .el-dialog__body {
    background: transparent !important;
    border: none !important;
    box-shadow: none !important;
    outline: none !important;
  }

  /* 全局覆盖Element Plus的默认样式 */
  :deep(.register-dialog-custom .el-dialog) {
    background: #2C3E50 !important;
    border: none !important;
    box-shadow: none !important;
  }

  /* 强制覆盖所有可能的白色背景 */
  body .register-dialog-custom .el-dialog,
  body .register-dialog-custom .el-dialog__wrapper,
  body .register-dialog-custom .el-dialog__body,
  body .register-dialog-custom .el-dialog__container {
    background: #2C3E50 !important;
    border: none !important;
    box-shadow: none !important;
    outline: none !important;
  }

  /* 使用最高优先级的选择器 */
  html body .register-dialog-custom .el-dialog,
  html body .register-dialog-custom .el-dialog__wrapper,
  html body .register-dialog-custom .el-dialog__body {
    background: #2C3E50 !important;
    border: none !important;
    box-shadow: none !important;
  }

  .register-dialog-custom .el-overlay {
    border-radius: 8px;
    overflow: hidden;
    background: transparent !important;
  }

  .register-dialog-custom .el-dialog,
  .register-card {
    border-radius: 8px !important;
  }

  .register-card {
    overflow: hidden;
    max-height: none;
  }

  .register-dialog-custom .el-dialog {
    border-radius: 8px !important;
    overflow: hidden;
  }

  .register-dialog-custom .el-dialog__body {
    border-radius: 8px !important;
    background: #2C3E50 !important;
    overflow: hidden;
    padding: 0;
  }

  .register-card {
    background: #2C3E50;
    border-radius: 8px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
    margin: 0;
    padding: 0;
    width: 100%;
    height: auto;
    max-width: 400px;
    max-height: none;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }

  /* 表单头部 */
  .form-header {
    background: #34495E;
    color: white;
    padding: 20px;
    text-align: center;
    font-size: 1.5rem;
    font-weight: bold;
    font-family: 'Arial', sans-serif;
    width: 100%;
  }

  /* 注册表单 */
  .register-form {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 20px;
    padding: 30px;
  }

  /* 表单项标签 */
  .register-form :deep(.el-form-item__label) {
    color: #ECF0F1 !important;
    font-size: 0.9rem;
    font-weight: 500;
  }

  /* 输入框样式 */
  .register-input :deep(.el-input__inner),
  .register-input {
    width: 100%;
    padding: 12px 15px;
    border: 1px solid #BDC3C7;
    border-radius: 8px;
    font-size: 1rem;
    background: white;
    color: #2C3E50;
    outline: none;
    transition: border-color 0.3s ease;
  }

  .register-input :deep(.el-input__inner):focus,
  .register-input:focus {
    border-color: #E91E63;
  }

  /* 选择框样式 */
  .register-select {
    width: 100% !important;
  }

  .register-select :deep(.el-input__inner) {
    width: 100%;
    padding: 12px 15px;
    border: 1px solid #BDC3C7;
    border-radius: 8px;
    font-size: 1rem;
    background: white;
    color: #2C3E50;
    outline: none;
    transition: border-color 0.3s ease;
  }

  .register-select :deep(.el-input__inner):focus {
    border-color: #E91E63;
  }

  /* 按钮区域 */
  .register-btns {
    display: flex;
    justify-content: center;
    gap: 15px;
    margin: 20px 0;
    width: 100%;
    padding: 0 30px 30px 30px;
  }

  .register-cancel, .register-submit {
    flex: 1;
    padding: 12px;
    border-radius: 4px;
    font-size: 0.9rem;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    text-transform: uppercase;
    letter-spacing: 0.5px;
  }

  .register-cancel {
    background: transparent;
    color: #E91E63;
    border: 1px solid #E91E63;
  }

  .register-cancel:hover {
    background: #E91E63;
    color: white;
    transform: translateY(-1px);
    box-shadow: 0 4px 8px rgba(233, 30, 99, 0.3);
  }

  .register-submit {
    background: linear-gradient(135deg, #E91E63, #9C27B0, #3F51B5, #2196F3);
    color: white;
    border: none;
  }

  .register-submit:hover {
    background: linear-gradient(135deg, #C2185B, #7B1FA2, #303F9F, #1976D2);
    transform: translateY(-1px);
    box-shadow: 0 4px 8px rgba(233, 30, 99, 0.3);
  }

  /* 头像上传器 */
  .avatar-uploader {
    border: 2px solid #BDC3C7;
    border-radius: 8px;
    margin-bottom: 1rem;
    background: white;
    transition: all 0.3s ease;
  }

  .avatar-uploader:hover {
    border-color: #E91E63;
    box-shadow: 0 4px 8px rgba(233, 30, 99, 0.2);
  }

  .avatar-uploader .el-upload {
    border: 2px dashed #BDC3C7;
    border-radius: 8px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: all 0.3s ease;
    background: white;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #E91E63;
    box-shadow: 0 4px 8px rgba(233, 30, 99, 0.2);
  }

  .el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #E91E63;
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
  }

  /* 头像图片 */
  .avatar {
    width: 100px;
    height: 100px;
    border-radius: 8px;
    object-fit: cover;
  }
</style>