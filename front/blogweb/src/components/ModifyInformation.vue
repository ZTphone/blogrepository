<template>
  <div>
    <div class="demo-type">
      <el-row>
        <el-col :span="90" style="margin-left: 30px">
          <el-avatar :size="60"  icon="el-icon-user-solid" :src="userImage" @error="errorHandler" >
            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
          </el-avatar>
        </el-col>
        <el-col :span="90">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="http://localhost:9191/update_image"
            :limit="1"
            :data="userdata"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-col>
      </el-row>

    </div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username" disabled></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pwd">
        <el-input v-model="ruleForm.pwd"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-input v-model="ruleForm.sex"></el-input>
      </el-form-item>
      <el-form-item label="个性签名" prop="signature">
        <el-input v-model="ruleForm.signature"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
import {postRequest} from "../utils/api";

export default {
  name: "ModifyInformation",
  data() {
    return {
      ruleForm: {
        id:'',
        username: '',
        pwd:'',
        sex:'',
        signature:'',
        image:''
      },
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        //pwd:[{ required: true, message: '请输入密码', trigger: 'blur' }],
        sex:[{ required: true, message: '请输入性别', trigger: 'blur' }],
        signature:[{ required: true, message: '请输入个性签名', trigger: 'blur' }],
      },
      userdata:{userId:window.sessionStorage.getItem('activeId')},
      fileList: [],
      userImage: ""
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var salt = '6cb755e000cf62e'
          var user= this.ruleForm
          console.log('??'+user.pwd)
          if(user.pwd!='') user.pwd = this.$md5(salt+user.pwd)
          console.log(user.pwd)
          postRequest('/modifyInformation',user).then(res=>{
            this.$message.warning(res.data.message);
            this.freshenFormData()
          })
        } else {
          return false;
        }
      });
    },
    freshenFormData(){
      postRequest('/getUserById',{userId:window.sessionStorage.getItem('activeId')}).then(res=>{
        this.ruleForm=res.data;
        this.userImage = "http://localhost:9191/static/images/"+ this.ruleForm.image;
      });

    },
    errorHandler() {
      return true
    },
    submitUpload() {
      this.$refs.upload.submit();
      this.$message.success("上传成功")
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
  },
  created() {
    this.freshenFormData()
  }

}
</script>

<style scoped>

</style>
