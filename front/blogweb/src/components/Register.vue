<template>
  <div class="classstyle">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input type="text" v-model="ruleForm.username" ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
          <el-input type="password" v-model="ruleForm.pwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input  type="text" v-model="ruleForm.sex"></el-input>
        </el-form-item>
        <el-form-item label="个性签名" prop="signature">
          <el-input  type="text" v-model="ruleForm.signature"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button type="success" @click="toLogin">去登录</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
import {postRequest} from "../utils/api";

export default {
  name: "Register",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pwd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username:'',
        pwd: '',
        checkPass: '',
        sex: '',
        signature:'',
        image:''
      },
      rules: {
        username:[
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        pwd: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '性别不能为空', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          postRequest("/register",this.ruleForm).then(res=>{
            if(res.data.state){
              this.$message.success(res.data.message)
              this.resetForm('ruleForm');
            }else{
              this.$message.error(res.data.message);
            }
          })
        } else {
          this.$message.error('error submit!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    toLogin(){
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.classstyle{
  width: 900px;
  height: 800px;
  margin-left: 200px;
  margin-top: 100px;
}
</style>
