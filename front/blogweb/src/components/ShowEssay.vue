<template>
  <div >
        <el-card :body-style="{ padding: '0px' }">
          <el-row>
            <el-col span="70%" style="margin-left: 200px;margin-right: 350px" >
              <span ><h1 style="height: 30px">{{ value.essay.title }}</h1></span>
              <span ><p style="height: 30px">{{ value.essay.modifyTime }}</p></span>
            </el-col>
            <el-col span="30%" style="margin-top: 10px;float: right">
              <el-image
                style="width: 100px; height: 100px;float: left;"
                :src="headsrc"
                :fit="fill"></el-image>
              <div style="padding: 14px;float: left;height: 130px">
                <div><el-button type="text" class="button" @click="follow">关注</el-button></div>
                <div style="height: 45px;line-height: 20px">{{value.author.username}}</div>
                <div><el-tag>{{value.author.signature}}</el-tag></div>
              </div>
            </el-col>
          </el-row>
        </el-card>

    <mavon-editor
      class="md"
      style="height: 600px"
      v-model="value.essayContent.content"
      :subfield = "subfield"
      :defaultOpen = "defaultOpen"
      :toolbarsFlag = "toolbarsFlag"
      :editable="editable"
      :scrollStyle="scrollStyle"
    ></mavon-editor>

    <div>
      <div style="height: 200px">
        <div style="padding: 5px">
          <el-button :type="hasCollect ? 'success':''" icon="el-icon-star-off" circle @click="onCollect"></el-button>收藏
          <el-button  :type="hasFavor ? 'success':''" icon="el-icon-thumb" circle @click="onFavor"></el-button>点赞
        </div>
        <br>
        <el-form ref="form" :model="form" label-width="80px">
          <el-row>
            <el-col>
              <el-form-item>
                <el-input v-model="form.content"></el-input>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">发送评论</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </div>
    <div>
      <el-card :body-style="{ padding: '0px' }">
        <div style="padding: 14px;float: left;">
          <div style="height: 60px;text-align:left" v-for="o in value.commentList" :key="o">
              <el-tag>{{o.user.username}}</el-tag>
              <span>{{o.comment.content}}</span>
          </div>

          <div style="height: 60px;">
          </div>
        </div>

        <br>
      </el-card>
    </div>

  </div>

</template>

<script>
import {postRequest} from "../utils/api";

export default {
  name: "ShowEssay",
  data(){
    return{
      headsrc:'',
      currentDate: new Date(),
      form:{
        userId:0,
        content:'',
        essayId:0
      },
      value:{},
      subfield: false,// 单双栏模式
      defaultOpen: 'preview',//edit： 默认展示编辑区域 ， preview： 默认展示预览区域
      editable: false,
      toolbarsFlag: false,
      scrollStyle: true,

      hasCollect:false,
      hasFavor:false
    }
  },
  methods:{
    initValue(){

      this.form.userId=window.sessionStorage.getItem('activeId');
      this.form.essayId=this.$route.params.essayId;

      postRequest('/showEssayPageById',{userId:this.form.userId,essayId:this.form.essayId}).then(res=>{
        this.value=res.data
        this.hasCollect=this.value.hasCollect;
        this.hasFavor=this.value.hasFavor;
        this.headsrc="http://localhost:9191/static/images/"+this.value.author.image;
      });

    },
    onSubmit(){
      if(this.form.content!='') {
        postRequest('/addComment', this.form).then(res => {
          if (res.data.state) {
            this.$message.success(res.data.message)
            this.initValue();
            this.form.content = '';
          } else {
            this.$message.error(res.data.message)
          }
        })
      }
    },
    onCollect(){
      if(this.hasCollect){
        this.hasCollect=false;
        postRequest('/cancelCollect',{userId:this.form.userId,essayId:this.form.essayId});
      }else{
        this.hasCollect=true;
        postRequest(('/addCollect'),{userId:this.form.userId,essayId:this.form.essayId})
      }
    },
    onFavor(){
      if(this.hasFavor){
        this.hasFavor=false;
        postRequest('/cancelFavor',{userId:this.form.userId,essayId:this.form.essayId});
      }else{
        this.hasFavor=true;
        postRequest(('/addFavor'),{userId:this.form.userId,essayId:this.form.essayId})
      }
    },
    follow(){
      postRequest('/follow',{userId:this.form.userId,bloggerId:this.value.author.id}).then(res=>{
        if (res.data.state) {
          this.$message.success(res.data.message)
          this.initValue();
          this.form.content = '';
        } else {
          this.$message.error(res.data.message)
        }
      });
    }
  },
  created() {
    this.initValue()
  }
}
</script>

<style scoped>
.div-height{width:100%; height:600px}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}
.head_div{height: 100px}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>
