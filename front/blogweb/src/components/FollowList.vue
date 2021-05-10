<template>
  <div class="div-height">
    <div class="button-div-height">
      <h1>关注的人</h1>
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
        prop="username"
        width="120">
      </el-table-column>
      <el-table-column
        prop="signature"
        width="750">
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
import {postRequest} from '../utils/api'
export default {
  name: "FollowList",
  data(){
    return{
      listItemData: []
    }
  },
  methods:{
    clickEssay(row,event,column){
      this.$router.push({name:'BloggerEssay',params:{bloggerId:row.id}})
    },
    getImagePath(image){
      var path = "http://localhost:9191/static/images/"+image
      return path
    }
  },
  created(){
    postRequest('/follow_list',{userId:window.sessionStorage.getItem('activeId')}).then(res=>{
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
