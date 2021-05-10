//登录

<template>
  <div class="login" id="login">
    <a href="javascript:;" class="log-close"><i class="icons close"></i></a>
    <div class="log-bg">
      <div class="log-cloud cloud1"></div>
      <div class="log-cloud cloud2"></div>
      <div class="log-cloud cloud3"></div>
      <div class="log-cloud cloud4"></div>

      <div class="log-logo">校园博客</div>
      <div class="log-text">欢迎!</div>
    </div>
    <div class="log-email">
      <input type="text" placeholder="用户名" :class="'log-input' + (loginform.username==''?' log-input-empty':'')" v-model="loginform.username">
      <input type="password" placeholder="密码" :class="'log-input' + (loginform.password==''?' log-input-empty':'')"  v-model="loginform.password">
      <a href="javascript:;" class="log-btn" @click="login">登录</a>
      <el-link type="primary" @click="toRegister">去注册</el-link>
    </div>
    <Loading v-if="isLoging" marginTop="-30%"></Loading>
  </div>
</template>

<script>
import Loading from './Loading.vue'
import {postRequest} from '../utils/api'
export default {
  name: 'Login',
  data(){
    return {
      isLoging: false,
      loginform:{
        username: '',
        password: ''
      }
    }
  },
  components:{
    Loading
  },
  methods:{

    //登录逻辑
    login(){

      if(this.account!='' && this.password!=''){
        this.toLogin();
        console.log('执行login');
      }
    },

    //登录请求
    toLogin(){

      //一般要跟后端了解密码的加密规则
      //这里例子用的哈希算法来自./js/sha1.min.js
     // let password_sha = hex_sha1(hex_sha1( this.password ));

      // //需要想后端发送的登录参数
      // let loginParam = {
      //   account: this.account,
      //   password_sha
      // }

      //设置在登录状态
      // this.isLoging = true;

      //请求后端,比如:
      /*this.$http.post( 'example.com/login.php', {
      param: loginParam).then((response) => {
        if(response.data.code == 1){
          let expireDays = 1000 * 60 * 60 * 24 * 15;
          this.setCookie('session', response.data.session, expireDays);
          //登录成功后
          this.$router.push('/user_info');
        }
      }, (response) => {
          //Error
      });
      */
      // console.log("执行tologin()");
      //
      // axios.get("/selectAllUsers").then(res=>{
      //   console.log(res.data);
      // }).catch(erro=>{
      //   console.log(erro)
      // });
      // var username = 'Tom';
      // var password = '123';
      // console.log("username="+username);
      // console.log("password="+password);
      // this.$http.post('http://localhost:9191/logintest',{username:username,password: password}).then(
      //   res=>{console.log(res.data.message)}
      // );
      var salt = '6cb755e000cf62e'
      var pwd = this.$md5(salt+this.loginform.password);
      console.log('--------')
      console.log(pwd)
      postRequest('/logintest',{username:this.loginform.username,password: pwd}).then(
        res=>{
          if(res.data.state){
            this.$message({
              message: res.data.message,
              type: 'success'
            });
            window.sessionStorage.setItem('activeId',res.data.value);
            this.$router.push("/home");
          }else{
            this.$message.error(res.data.message);
          }

        }
      );

      ///演示用
      // setTimeout(()=>{
      //   //登录状态15天后过期
      //   let expireDays = 1000 * 60 * 60 * 24 * 15;
      //   this.setCookie('session','blablablablabla...', expireDays);
      //   this.isLoging = false;
      //   //登录成功后
      //   this.$router.push('/user_info/');
      // },3000)
    },
    toRegister(){
      this.$router.push('/register');
      // this.$message.warning("clicked")
    }
  }
}
</script>

<style scoped>
.login{position: fixed; overflow: hidden;left: 50%; margin-left: -250px; top:50%; margin-top: -350px; width: 500px; min-height: 555px; z-index: 10; right: 140px; background: #fff;-webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  -ms-border-radius: 5px;
  -o-border-radius: 5px;
  border-radius: 5px; -webkit-box-shadow:  0px 3px 16px -5px #070707; box-shadow:  0px 3px 16px -5px #070707}
.log-close{display: block; position: absolute; top:12px; right: 12px; opacity: 1;}
.log-close:hover .icons{transform: rotate(180deg);}
.log-close .icons{opacity: 1; transition: all .3s}
.log-cloud{background-image: url(../images/login-cloud.png); width: 63px ;height: 40px; position: absolute; z-index: 1}
.login .cloud1{top:21px; left: -30px; transform: scale(.6); animation: cloud1 20s linear infinite;}
.login .cloud2{top:87px; right: 20px; animation: cloud2 19s linear infinite;}
.login .cloud3{top:160px; left: 5px;transform: scale(.8);animation: cloud3 21s linear infinite;}
.login .cloud4{top:150px; left: -40px;transform: scale(.4);animation: cloud4 19s linear infinite;}
.log-bg{background: url(../images/login-bg.jpg); width: 100%; height: 312px; overflow: hidden;}
.log-logo{height: 80px; margin: 120px auto 25px; text-align: center; color: #1fcab3; font-weight: bold; font-size: 40px;}
.log-text{color: #57d4c3; font-size: 23px; text-align: center; margin: 0 auto;}
.log-logo,.log-text{z-index: 2}
.icons{background:url(../images/icons.png) no-repeat; display: inline-block;}
.close{height:16px;width:16px;background-position:-13px 0;}
.login-email{height:17px;width:29px;background-position:-117px 0;}

.log-btns{padding: 15px 0; margin: 0 auto;}
.log-btn{width:402px; display: block; text-align: left; line-height: 50px;margin:0 auto 15px; height:50px; color:#fff; font-size:13px;-webkit-border-radius: 5px; background-color: #3B5999;
  -moz-border-radius: 5px;
  -ms-border-radius: 5px;
  -o-border-radius: 5px;
  border-radius: 5px;
  position: relative;}
.log-btn.tw{background-color: #13B4E9}
.log-btn.email{background-color: #50E3CE}
.log-btn:hover,.log-btn:focus{color: #fff; opacity: .8;}

.log-email{text-align: center; margin-top: 20px;}
.log-email .log-btn{background-color: #50E3CE;text-align: center;}
.log-input-empty{border: 1px solid #f37474 !important;}
.isloading{background: #d6d6d6}
.log-btn .icons{margin-left: 30px; vertical-align: middle;}
.log-btn .text{left: 95px; line-height: 50px; text-align: left; position: absolute;}
.log-input{width: 370px;overflow: hidden; padding: 0 15px;font-size: 13px; border: 1px solid #EBEBEB; margin:0 auto 15px; height: 48px; line-height: 48px; -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  -ms-border-radius: 5px;
  -o-border-radius: 5px;
  border-radius: 5px;}
.log-input.warn{border: 1px solid #f88787}

@-webkit-keyframes cloud1 {
  0%{left: 200px}
  100%{left:-130px;}
}
@keyframes cloud1{
  0%{left: 200px}
  100%{left:-130px;}
}

@-webkit-keyframes cloud2 {
  0%{left:500px;}
  100%{left:-90px;}
}
@keyframes cloud2{
  0%{left:500px;}
  100%{left:-90px;}
}

@-webkit-keyframes cloud3 {
  0%{left:620px;}
  100%{left:-70px;}
}
@keyframes cloud3{
  0%{left:620px;}
  100%{left:-70px;}
}@-webkit-keyframes cloud4 {
   0%{left:100px;}
   100%{left:-70px;}
 }
@keyframes cloud4{
  0%{left:100px;}
  100%{left:-70px;}
}

</style>
