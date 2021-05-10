<template>
  <div class="div-height">
    <div class="button-div-height">
      <el-button type="success" round @click="listLastest">最新</el-button>
      <el-button type="success" round @click="listHotest">最热</el-button>
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
export default {
  name: "LastestEssay",
  data(){
    return{
        listItemData: [],
        isHot:false
    }
  },
  methods:{
  listLastest(){
    this.isHot=false
    this.$http.get('/listLastestEssays').then(res=>{
      this.listItemData=res.data
    })
    },
    listHotest(){
      this.isHot=true
      this.$http.get('/listHotestEssays').then(res=>{
        this.listItemData=res.data
      })
    },
    // 点击文章列表事件
    clickEssay(row,event,column){
      if(this.isHot) this.$router.push({name:'ShowEssay',params:{essayId:row.essayId}})
      else
      this.$router.push({name:'ShowEssay',params:{essayId:row.essayId}})
    },
    getImagePath(image){
      var path = "http://localhost:9191/static/images/"+image
      return path
    }
  },
  created(){
    this.listLastest();
  }
}
</script>

<style scoped>
.div-height{width:100%; height:600px}
.button-div-height{height:30px;
  margin-top:5px;margin-bottom:6px;margin-left:4px;
  padding-top:10px;padding-bottom:10px;
}
</style>
