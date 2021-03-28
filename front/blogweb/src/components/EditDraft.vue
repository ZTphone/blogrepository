<template>
  <div >
    <el-input
      type="textarea"
      :rows="2"
      placeholder="请输入标题"
      v-model="title">
    </el-input>
    <br>
    <mavon-editor class="div-height"  v-model="value"  />

    <el-button type="primary" class="button-div-height" @click="toIndex">返回首页</el-button>
    <el-button type="info" class="button-div-height" @click="saveDraft">保存草稿</el-button>
    <el-button type="success" class="button-div-height" @click="publishEssay">发布文章</el-button>
  </div>
</template>

<script>
import {postRequest} from '../utils/api'
export default {
  name: "EditDraft",
  data() {
    return {
      value: '',
      defaultOpen: "preview",
      title:''
    };
  },
  methods:{
    initValue(){
      if(this.$route.params.essayId){
        postRequest('/showEssayPageById',{userId:window.sessionStorage.getItem('activeId'),essayId:this.$route.params.essayId}).then(res=>{
          this.title = res.data.essay.title;
          this.value=res.data.essayContent.content;
        })
      }
    },
    toIndex(){
      this.$router.push('/home');
    },
    saveDraft(){
      if(this.value===''||this.title==='') this.$message.warning('文章标题或者内容不能为空！');
      else{
        postRequest('/editDraft',{essayId:this.$route.params.essayId,title:this.title,
          content:this.value,state:1}).then(res=>{
          if(res.data.state){
            this.$message.success(res.data.message);
            this.value='';
            this.title='';
          }else{
            this.$message.error(res.data.message);
          }

        })
      }
      this.toIndex();
    },
    publishEssay(){
      if(this.value===''||this.title==='') this.$message.warning('文章标题或者内容不能为空！');
      else{
        postRequest('/editDraft',{essayId:this.$route.params.essayId,title:this.title,
          content:this.value,state:2}).then(res=>{
          if(res.data.state){
            this.$message.success(res.data.message);
            this.value='';
            this.title='';
          }else{
            this.$message.error(res.data.message);
          }
        })
      }
      this.toIndex();
    }
  },
  created() {
    this.initValue();
  }
}
</script>

<style scoped>
.div-height{width:100%; height:600px}
.button-div-height{
  margin-top:5px;margin-bottom:6px;margin-left:20px;
  padding-top:10px;padding-bottom:10px;
}
</style>
