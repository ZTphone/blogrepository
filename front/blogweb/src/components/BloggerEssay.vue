<template>
  <div class="div-height">
    <div class="button-div-height">
      <h1>博主的文章</h1>
    </div>
    <el-table
      :data="listItemData"
      :show-header="false"
      style="width: 100%"
      @row-click="clickEssay"
    >
      <el-table-column
        prop="headImage"
        width="60">
        <template slot-scope="scope">
          <el-avatar :src="getImagePath(scope.row.headImage)"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column
        prop="author"
        width="120">
      </el-table-column>
      <el-table-column
        prop="title"
        width="750">
      </el-table-column>

      <el-table-column
        width="30"
      >
        <i class="el-icon-star-off"></i>
      </el-table-column>
      <el-table-column
        prop="collectCount"
        width="30">
      </el-table-column>

      <el-table-column
        width="30"
      >
        <i class="el-icon-thumb"></i>
      </el-table-column>
      <el-table-column
        prop="favorCount"
        width="30">

      </el-table-column>
      <el-table-column
        width="30"
      >
        <i class="el-icon-chat-dot-round"></i>
      </el-table-column>
      <el-table-column
        prop="commentCount"
      >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {postRequest} from '../utils/api'
export default {
  name: "BloggerEssay",
  data(){
    return{
      listItemData: []
    }
  },
  methods:{
    clickEssay(row,event,column){
      this.$router.push({name:'ShowEssay',params:{essayId:row.essayId}})
    },
    getImagePath(image){
      console.log(image+"*****")
      var path = "http://localhost:9191/static/images/"+image
      return path
    }
  },
  created(){
    var bid = this.$route.params.bloggerId
    postRequest('/listPublishedEssays',{userId:bid}).then(res=>{
      this.listItemData=res.data
    })
  }
}
</script>

<style scoped>

.div-height{width:100%; height:600px}
.button-div-height{height: 30px;
  margin-top:5px;margin-bottom:6px;margin-left:4px;
  padding: 5px;
}
</style>
