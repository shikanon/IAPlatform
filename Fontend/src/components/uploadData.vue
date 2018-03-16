<template>
 <div class="uploadData" v-show="uploadData.uploadDataToggle">
   <div class="uploadDataFrom">
     <button class="closeBtn el-icon-close" @click="uploadDataToggleHide"></button>
     <el-form :model="uploadDataInfo"  :rules="rules" ref="uploadDataInfo"  class="demo-ruleForm">
         <div class="grid-content bg-purple">
         <ul class="uploadDataList">
           <li>
             <el-form-item label="数据名称" prop="name">
               <el-input v-model="uploadDataInfo.name"></el-input>
             </el-form-item>
           </li>
           <li>
             <el-form-item label="目录" prop="category">
               <el-select v-model="uploadDataInfo.category">
                 <el-option v-for="(item,i) in uploadDataCategory" :label="item.name" :value="item.id" :key="i"></el-option>
               </el-select>
             </el-form-item>
           </li>
           <li>
             <el-form-item label="数据类型" prop="storeType">
               <el-select v-model="uploadDataInfo.storeType">
                 <el-option label="General" value="General"></el-option>
                 <el-option label="CSV" value="CSV"></el-option>
                 <el-option label="TSV" value="TSV"></el-option>
               </el-select>
             </el-form-item>
           </li>
           <li>
             <el-form-item label="版本" prop="version">
               <el-input v-model="uploadDataInfo.version" placeholder="请输入"></el-input>
             </el-form-item>
           </li>
           <li>
             <el-form-item label="上传时间" prop="createDate">
               <el-input placeholder="选择日期" v-model="uploadDataInfo.createDate" :disabled="true"></el-input>
             </el-form-item>
           </li>
           <li>
             <el-form-item label="上传用户" prop="owner">
               <el-input v-model="uploadDataInfo.owner" placeholder="请输入"  :disabled="true"></el-input>
             </el-form-item>
           </li>
           <li>
             <el-form-item label="简要说明" prop="description">
               <el-input type="textarea" v-model="uploadDataInfo.description"></el-input>
             </el-form-item>
           </li>
           <li>
             <div class="upload_progress">
               <el-form-item prop="fileName" label="上传文件">
                 <el-input v-model="uploadDataInfo.fileName"></el-input>
                 <input type='file' id='uploadDataFile' name='myfile' @change="changefileName"/>
               </el-form-item>
               <!-- <input type='file' id='file' name='myfile' />-->
               <el-progress id='progressDataBar' :percentage="percentage"></el-progress>
             </div>
           </li>
         </ul>


       </div>
         <el-form-item style="text-align: center;margin-bottom: 20px;">
           <el-button type="primary" size="small" @click="upladFile('uploadDataInfo')">上传</el-button>
           <el-button @click="uploadDataToggleHide" size="small">取 消</el-button>
         </el-form-item>
     </el-form>
   </div>

 </div>
</template>

<script>
  import { HOST } from '../service/HOST'
  import get from '../service/get'
  import post from '../service/post'
  import $ from 'jquery'
  import { Message } from 'element-ui';
    export default {
        name: 'uploadData',
        data(){
            return {
              uploadDataInfo:{
                  name:'',
                  category:'',
                  storeType:'General',
                  version:'0.1',
                  createDate:this.$getDate(),
                  owner:'sigma@hotmail.com',
                  description:'',
                  upload:null,
                  fileName:'',
              },
              categoryName:'',
              chooseCategory:[],
              percentage:0,
              rules: {
                name: [
                  { required: true, message: '请输入名称', trigger: 'blur' },
                ],
                category: [
                  { required: true, message: '请选择目录', trigger: 'blur' },
                ],
                storeType: [
                  { required: true, message: '请输入算法类型', trigger: 'blur' },
                ],
                description: [
                  { required: true, message: '请输入算法简要说明', trigger: 'blur' },
                ],
                version: [
                  { required: true, message: '请输入版本号', trigger: 'blur' },
                ],
                createDate: [
                  { required: true, message: '请输入算法用户邮箱', trigger: 'blur' },
                ],
                owner:[
                  { required: true, message: '请输入创建日期', trigger: 'blur' },
                ],
                fileName:[
                  { required: true, message: '请上传文件', trigger: 'change' },
                ],
              },
              uploadDataCategory:[]
            }
        },
        beforeMount: function () {

        },
       mounted:function () {

       },
       watch:{
         'uploadData':{
             handler:function (val) {
               let _that=this;
               _that.uploadDataCategory=val.uploadDataCategory;
               _that.uploadDataInfo.category=val.uploadDataCategory[0].id;
              /* _that.uploadDataInfo.categoryName=val.uploadDataCategory[0].name;*/
             },
             deep:true
           }
        },
        props:["uploadData"],
        methods: {
          dataCategoryFn:function () {
            let _that=this;

          },
          uploadDataToggleHide:function () {
            let _that=this;
            _that.uploadData.uploadDataToggle=false
          },
          changefileName:function () {
            let _that=this;
            let fileObj = document.getElementById('uploadDataFile').files[0]; // js 获取文件对象
            _that.uploadDataInfo.upload=fileObj;
            _that.uploadDataInfo.fileName=document.getElementById('uploadDataFile').value;
          },
          upladFile:function (formName) {
              let _that=this;
           _that.$refs[formName].validate((valid) => {
              if (valid) {
                _that.saveUpload()
              } else {
                console.log('error submit!!');
                return false;
              }
            });
          },
          saveUpload:function () {
            let _that=this;
            let form = new FormData();
            for(let key in _that.uploadDataInfo){
                if(key!='fileName'){
                  form.append(key,_that. uploadDataInfo[key]);
                }
            }
            let progressData= {
              onUploadProgress: progressEvent => {
                let _that=this;
                if (progressEvent.lengthComputable) {
                  _that.percentage=Math.round(progressEvent.loaded / progressEvent.total * 100);
                }
              }
            };
            _that.axios.post("/ueSparkMLSys/dataSetAction!uploadData.action",form,progressData).then(function(data) {
                console.log(data)
              if(data.status==200||data!=null){
                _that.$emit('uploadFileClick');
                _that.uploadDataToggleHide()
              }else{
                Message({message: '无数据返回', type: 'error'})
              }
            }, function(error) {
              Message({message: '系统异常,请重试', type: 'error'})
            });
          },
        }
    }
</script>

<style scoped>
  .uploadData{
    position:fixed;
    height:100%;
    width: 100%;
    background: rgba(0,0,0,0.7);
    top:0;
    left:0;
  }
  .uploadDataFrom .grid-content  ul>li{
    margin:10px 0;
  }
  .uploadDataFrom .upload_progress{
    margin-top:20px;
  }
  .uploadDataFrom .uploadDataList{
    width:50%;
    margin:0 auto;
  }
  .uploadDataFrom .el-form-item{
    margin-bottom: 0;
  }
  .uploadDataFrom .upload_progress #uploadDataFile{
    position: absolute;
    top:5px;
    width: 200px;
    opacity: 0;
    right: 0;
  }
  .uploadDataFrom ul li span{
     display: inline-block;
     width:100px;
    vertical-align: top;
     text-align: right;
  }
  .uploadData .uploadDataFrom{
    background: #fff;
    position:relative;
    margin:0 auto;
    top: 50%;
    transform: translateY(-50%);
    width:700px;
    border:2px solid #eef1f6;
    padding-top:5px;

  }
  .uploadData .grid-content.bg-purple{
    margin-left:10px;
  }
  .uploadData #progressDataBar{
    margin-top:10px;
    margin-right:-50px;
  }
  .uploadData  .closeBtn{
    position:absolute;
    z-index:300;
    right:0;
    top:10px;
    border:0;
    outline: none;
    background: transparent;
    padding: 5px 15px;
    color:#444;
  }
  .uploadData  .closeBtn:hover{
    color:#20a0ff
  }

</style>
