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
              <!--          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">-->
              <div style="padding: 14px;float: left;height: 130px">
                <span>{{value.author.username}}</span>
                <el-tag>{{value.author.signature}}</el-tag>
                <el-button type="text" class="button">关注</el-button>
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

<!--    评论区-->
<!--    <div>-->
<!--        <el-row>-->
<!--&lt;!&ndash;          <el-col>{{value.commentList.user.username}}</el-col>&ndash;&gt;-->
<!--&lt;!&ndash;          <el-col>{{value.commentList.comment.content}}</el-col>&ndash;&gt;-->
<!--          <p>评论者</p>-->
<!--          <p>评论内容</p>-->
<!--    v-bind:type="[hasCollect ? success:warning]"-->
<!--        </el-row>-->
<!--    </div>-->
    <div>
      <div style="height: 200px">
        <el-button :type="hasCollect ? 'success':''" icon="el-icon-star-off" circle @click="onCollect"></el-button>收藏
        <el-button  :type="hasFavor ? 'success':''" icon="el-icon-thumb" circle @click="onFavor"></el-button>点赞
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
        <!--          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">-->
        <div style="padding: 14px;float: left;">
          <!--        <div style="height: 60px">-->
          <!--          <h1>{{value.commentList}}</h1>-->
          <!--        </div>-->
          <!--        <div style="height: 60px" v-for="commentObject in value.commentList" :key="commentObject">-->

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
      //value:'<h2><a id="_0"></a>新的改变</h2> <p>我们对Markdown编辑器进行了一些功能拓展与语法支持，除了标准的Markdown编辑器功能，我们增加了如下几点新功能，帮助你用它写博客：</p> <ol> <li><strong>全新的界面设计</strong> ，将会带来全新的写作体验；</li> <li>在创作中心设置你喜爱的代码高亮样式，Markdown <strong>将代码片显示选择的高亮样式</strong> 进行展示；</li> <li>增加了 <strong>图片拖拽</strong> 功能，你可以将本地的图片直接拖拽到编辑区域直接展示；</li> <li>全新的 <strong>KaTeX数学公式</strong> 语法；</li> </ol> ',
      //defaultData: "preview",
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
      //
      // postRequest("/checkCollected",{userId:this.form.userId,essayId: this.form.essayId}).then(res=>{
      //   this.hasCollect = res.data
      //   console.log(res.data+'kkkkkk');
      // });
      // postRequest("/checkFavor",{userId:this.form.userId,essayId: this.form.essayId}).then(res=>{
      //   this.hasCollect = res.data
      //   console.log(res.data+'ffff');
      // });

      postRequest('/showEssayPageById',{userId:this.form.userId,essayId:this.form.essayId}).then(res=>{
        this.value=res.data
        this.hasCollect=this.value.hasCollect;
        this.hasFavor=this.value.hasFavor;
        console.log(this.value)
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
    }
  },
  // computed: {
  //   prop () {
  //     let data = {
  //       subfield: false,// 单双栏模式
  //       defaultOpen: 'preview',//edit： 默认展示编辑区域 ， preview： 默认展示预览区域
  //       editable: false,
  //       toolbarsFlag: false,
  //       scrollStyle: true
  //     }
  //     return data
  //   }
  // }
  created() {
   // console.log('----------'+this.$route.params.essayId);
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
