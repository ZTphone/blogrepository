<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          :router="true"
          :default-active="$route.path"
        >


<!--          导航一：首页-->
          <el-menu-item index="/lastest_essay">
            <i class="el-icon-lollipop"></i>
            <span slot="title">首页</span>
          </el-menu-item>

          <!--          导航二：写文章-->
          <el-menu-item index="/edit_essay">
            <i class="el-icon-document"></i>
            <span slot="title">写文章</span>
          </el-menu-item>

<!--          个人中心-->
          <el-submenu index="3" :default-active="$route.path">
            <template slot="title">
              <i class="el-icon-user-solid"></i>
              <span >个人中心</span>
            </template>

            <el-menu-item-group>
              <template slot="title">文章管理</template>
              <el-menu-item index="/follow_list">关注的人</el-menu-item>
              <el-menu-item index="/publish_essay">发布的文章</el-menu-item>
              <el-menu-item index="/draft_essay">草稿</el-menu-item>
              <el-menu-item index="/collected_essay">收藏的文章</el-menu-item>
              <el-menu-item index="/favor_essay">点赞的文章</el-menu-item>
              <el-menu-item index="commented_essay">评论的文章</el-menu-item>
            </el-menu-item-group>

            <el-menu-item-group>
              <template slot="title">设置</template>
              <el-menu-item index="/modify_information">
                <el-avatar style="margin: 5px;" :src="userImage"></el-avatar>
              </el-menu-item>
              <el-menu-item index="/modify_information">修改信息</el-menu-item>
              <el-menu-item  index="3-7" @click="exitLogin">退出登录</el-menu-item>
            </el-menu-item-group>

          </el-submenu>

        </el-menu>

      </el-aside>



      <el-container>
<!--        <el-header>Header</el-header>-->
        <el-main height="1000px">
          <router-view></router-view>
        </el-main>
<!--        <el-footer >Footer</el-footer>-->
      </el-container>

    </el-container>

  </div>

</template>

<script>
import {postRequest} from "../utils/api";

export default {
  name: "Home",
  data(){
    return{
     userImage:''
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    exitLogin(){
      window.sessionStorage.clear();
      this.$router.push('/login');
    },
    initImagePath() {
     postRequest('/getUserById',{userId:window.sessionStorage.getItem('activeId')}).then(res=>{
       this.userImage = "http://localhost:9191/static/images/"+res.data.image;
     })

    }
  },
  created() {
    this.initImagePath()
  }

}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
