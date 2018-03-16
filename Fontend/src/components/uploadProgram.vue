<template>
 <div class="uploadProgram" v-show="uploadProg.uploadProgToggle">
   <div class="uploadProgramFrom">
     <button class="closeBtn el-icon-close" @click="uploadProgToggleHide"></button>
     <el-form :model="uploadProgInfo" status-icon  :rules="rules" ref="uploadProgInfo"  class="demo-ruleForm">
       <el-row :gutter="20" style="margin-top:20px;">
           <el-col :span="8">
               <div class="grid-content bg-purple">
                 <ul>
                   <li>
                     <el-form-item label="算法名称" prop="name">
                       <el-input v-model="uploadProgInfo.name"></el-input>
                     </el-form-item>
                   </li>
                   <li @click="chooseFileClick">
                     <el-form-item label="算法目录" prop="category">
                       <el-input v-model="categoryName"  ></el-input>
                     </el-form-item>
                   </li>
                   <li>
                     <el-form-item label="算法类型" prop="type">
                       <el-select v-model="uploadProgInfo.type" :disabled="true">
                         <el-option label="Standalone" value="Standalone"></el-option>
                         <el-option label="Distributed" value="Distributed"></el-option>
                         <el-option label="ETL" value="ETL"></el-option>
                       </el-select>
                     </el-form-item>
                   </li>
                   <!--<li>
                     <span>Programable</span>
                     <el-radio-group v-model="uploadProgInfo.programable">
                       <el-radio label="yes" value="yes"></el-radio>
                       <el-radio label="no" value="no"></el-radio>
                     </el-radio-group>
                   </li>
                   <li>
                     <span>Determinacy</span>
                     <el-radio-group v-model="uploadProgInfo.determinacy">
                       <el-radio label="yes" value="yes"></el-radio>
                       <el-radio label="no" value="no"></el-radio>
                     </el-radio-group>
                   </li>
-->
                   <li>
                     <el-form-item label="版本" prop="version">
                       <el-input v-model="uploadProgInfo.version" placeholder="请输入"></el-input>
                     </el-form-item>
                   </li>
                   <li>
                     <el-form-item label="上传时间" prop="createDate">
                       <el-input placeholder="选择日期" v-model="uploadProgInfo.createDate" :disabled="true"></el-input>
                     </el-form-item>
                   </li>
                   <li>
                     <el-form-item label="算法用户邮箱" prop="owner">
                       <el-input v-model="uploadProgInfo.owner" placeholder="请输入"  :disabled="true"></el-input>
                     </el-form-item>
                   </li>
                   <li>
                     <el-form-item label="算法简要说明" prop="description">
                       <el-input type="textarea" v-model="uploadProgInfo.description"></el-input>
                     </el-form-item>
                   </li>
                   <li>
                     <div class="upload_progress">
                       <el-form-item prop="fileName" label="上传文件">
                         <el-input v-model="uploadProgInfo.fileName"></el-input>
                         <input type='file' id='uploadProgFile' name='myfile' @change="changefileName"/>
                       </el-form-item>
                       <!-- <input type='file' id='file' name='myfile' />-->
                       <el-progress id='progressBar' :percentage="percentage"></el-progress>
                     </div>
                   </li>
                 </ul>


               </div>
           </el-col>
           <el-col :span="16">
             <div class="grid-content bg-purple">
               <div class="cmdFormatName">CMD生成策略</div>
               <div class="cmdFormat">
                 <div class="cmdInput">
                   <span>CMD</span>
                   <el-input v-model="cmdFormat" placeholder="请输入"></el-input>
                   <el-button type="button" class="addStyle" @click="addClick">ADD</el-button>
                   <el-button type="button" class="delStyle" @click="delClick">DELETE</el-button>
                 </div>
                 <table class="cmdTable" id="cmdTable">
                   <thead>
                     <tr>
                       <th>Type</th>
                       <th>Value</th>
                       <th>Parameter</th>
                       <th>Default</th>
                       <th>description</th>
                       <th width="30"></th>
                     </tr>
                   </thead>
                    <tbody>
                      <tr v-for="i in addInput" :class="['inputNum'+i]">
                        <td>
                          <select name="type" class="type changeType" @change="changeType(['inputNum'+i])">
                            <option value="in">in</option>
                            <option value="out">out</option>
                            <option value="int">int</option>
                            <option value="double">double</option>
                            <option value="string">string</option>
                            <option value="bool">bool</option>
                          </select>
                        </td>
                        <td><input type="text" name="value" class="value changeVal" value="" ></td>
                        <td><input type="text" name="type"  class="parameter" value=""></td>
                        <td><input type="text" name="type"  class="default" value=""></td>
                        <td><input type="text" name="type"  class="description" value=""></td>
                        <td><input type="checkbox" name="checkbox" class="check" value="" @click="checkClick(i)"></td>
                      </tr>
                    </tbody>

                 </table>
                 <el-button class="generateStyle" @click="generateClick">Generate</el-button>
                 <div class="generate">
                   <el-form-item prop="commandLine">
                        <el-input type="textarea" v-model="uploadProgInfo.commandLine"  class="generateTextarea"  :disabled="true" v-show="generateToogle"></el-input>
                   </el-form-item>
                 </div>
               </div>
             </div>
           </el-col>
         </el-row>
       <div class="chooseCategoryMask" v-show="chooseCategoryMask"></div>
       <div class="chooseCategory"  v-show="chooseCategoryMask">
          <div class="chooseCategoryBox">
            <ul>
              <li class="categoryList" v-for="(item,i) in categoryList"  :style="menuItemStyle(item)" @click ="categoryInputClick(item,i)">
                <i :class="drawdownClass(item)" @click.stop="categoryListClick(item,i)" /></i>
                {{item.name}}
              </li>
            </ul>
          </div>
       </div>
       <el-form-item style="text-align: center;margin-bottom: 20px;">
         <el-button type="primary" size="small" @click="uploadFile('uploadProgInfo')">上传</el-button>
         <el-button @click="uploadProgToggleHide" size="small">取 消</el-button>
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
        name: 'uploadProgram',
        data(){
            return {
              uploadProgInfo:{
                  name:'',
                  category:'',
                  /*programable:'no',
                  determinacy:'no',*/
                  type:'Distributed',
                  version:'0.1',
                  createDate:this.$getDate(),
                  owner:'sigma@hotmail.com',
                  description:'',
                  commandLine:'',
                  upload:null,
                  fileName:'',
              },
              categoryName:'Choose Category',
              chooseCategoryMask:false,
              chooseCategory:[],
              categoryList:[],
              cmdFormat:'',
              addInput:1,
              checkList:[],
              generateToogle:false,
              percentage:0,
              rules: {
                name: [
                  { required: true, message: '请输入名称', trigger: 'blur' },
                ],
                category: [
                  { required: true, message: '请选择目录', trigger: 'blur' },
                ],
                type: [
                  { required: true, message: '请输入算法类型', trigger: 'blur' },
                ],
                description: [
                  { required: true, message: '请输入算法简要说明', trigger: 'blur' },
                ],
                commandLine:[
                  { required: true, message: '请输入cmd', trigger: 'blur' },
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
              }

            }
        },
        beforeMount: function () {

        },
       mounted:function () {

       },
       watch:{

       },
        props:["uploadProg"],
        methods: {
          changeType:function (dom) {
            let _that=this;
            let changeType=$("."+dom).find(".changeType");
            let changeVal=$("."+dom).find(".changeVal");
           // let changeVal=$("."+dom).find(".changeVal");
            let type=changeType.val();
            if(type!='in'&&type!='out'){
              changeVal.val(type);
              changeVal.attr('disabled',true)
            }else{
              changeVal.val('');
              changeVal.removeAttr('disabled')
            }
          },
          uploadProgToggleHide:function () {
            let _that=this;
            _that.uploadProg.uploadProgToggle=false
          },
          changefileName:function () {
            let _that=this;
            let fileObj = document.getElementById('uploadProgFile').files[0]; // js 获取文件对象
            _that.uploadProgInfo.upload=fileObj;
            _that.uploadProgInfo.fileName=document.getElementById('uploadProgFile').value;
          },
          uploadFile:function (formName) {
              let _that=this;
            _that.$refs[formName].validate(function (valid) {
              if (valid) {
                _that.saveUpload();
              }else{
                return false;
              }
            });
          },
          saveUpload:function () {
            let _that=this;
            let form = new FormData();
            for(let key in _that. uploadProgInfo){
                if(key!='fileName'){
                  form.append(key,_that. uploadProgInfo[key]);
                }
            }
            let progress= {
                onUploadProgress: progressEvent => {
                  let _that=this;
                  if (progressEvent.lengthComputable) {
                    _that.percentage=Math.round(progressEvent.loaded / progressEvent.total * 100);
                  }
              }
            };
            _that.axios.post("/ueSparkMLSys/programAction!uploadHDFS.action",form,progress).then(function(data) {
                let obj=data.data;
              Message({message: obj.message, type: 'error'});
              if( obj.message=='上传成功'){
                //_that.$emit('updateProgList');
                _that.$emit('uploadFileClick');
                _that.uploadProgToggleHide()
              }
            }, function(error) {
              Message({message: '系统异常,请重试', type: 'error'})
            });
          },
          chooseFileClick:function () {
            let _that=this;

            _that.axios.get('/ueSparkMLSys/categoryAction!getList.action').then(function(data) {
                let obj=data.data;
              if(obj.status==true){
                _that.chooseCategoryMask=true;
                obj.object.forEach(function (value,index) {
                  if(value.type=="prog"){
                    _that.chooseCategory.push(value)
                  }
                });
                _that.categoryListFn(_that.chooseCategory);
              }
            }, function(error) {
              Message({message: '网络请求失败', type: 'error'})
            })
          },
          categoryListFn:function (val) {
            let _that=this;
            for(let index=0;index<val.length;index++){
              val[index].extend=false;
              _that.categoryList.push(val[index])
            }
          },
          drawdownClass(item){
            return item.extend ? "up":"down"
          },
          categoryListClick:function (item,i) {
            let _that=this;
             if(item.extend==false){
                let programList = item.childTree;
                if(programList){
                  programList.forEach(function(value,childIndex){
                    item.extend=true;
                    _that.categoryList.splice((i+1+childIndex),0,value);
                  })
                }
            }else{
               let foldCount = 0;
               let itemLevel = item.level;
               item.extend = false;
               for(let itemIndex=i+1;itemIndex< _that.categoryList.length;itemIndex++){
                 let menuItem = _that.categoryList[itemIndex];
                 if(menuItem.level > itemLevel){
                   foldCount++;
                   continue;
                 }
                 break;
               }
               _that.categoryList.splice(i+1,foldCount);
             }


          },
          menuItemStyle(item){
            let levelOffset = 8*item.level+ "px";
            return item.level>1 ?  {paddingLeft:levelOffset} : {paddingLeft:levelOffset};
          },
          categoryInputClick:function (item,i) {
            let _that=this;
            _that.uploadProgInfo.category=item.id;
            _that.categoryName=item.path;
            _that.chooseCategoryMask=false;
            _that.chooseCategory=[];
            _that.categoryList=[];
          },
          addClick:function () {
            let _that=this;
            _that.addInput++;

          },
          checkClick:function(i){
              let _that=this;
              if($(".inputNum"+i).find('.check').is(':checked')){
                _that.checkList.push(i);
              }else{
                for(let j=0;j<_that.checkList.length;j++){
                    if(_that.checkList[j]==i){
                      _that.checkList.splice(j,1)
                    }
                }


              }

          },
          delClick:function () {
            let _that=this;
             for(let i=0;i<_that.checkList.length;i++){
               $(".inputNum"+_that.checkList[i]).remove()
            }
            _that.checkList=[]
          },
          generateClick:function () {
            let _that=this;
            if(_that.cmdFormat==''){
              Message({message: '请添加CMD名称', type: 'warning'})
            }else{
              _that.generateToogle=true;
              let generateStr='';
            $("#cmdTable").find('tbody>tr').each(function (i,item) {
                if($(this).find('.parameter').val()!=''&&$(this).find('.type').val()!=''&&$(this).find('.value').val()&&$(this).find('.description').val()){
                  generateStr+=' --'+$(this).find('.parameter').val()+' {'+$(this).find('.type').val()+':'+$(this).find('.value').val()+':"'+$(this).find('.description').val()+'"}';
                }else{
                  Message({message: '请将字段填写完整', type: 'warning'})
                }


            });
              _that.uploadProgInfo.commandLine=_that.cmdFormat+generateStr;
            }

          }
        }
    }
</script>

<style scoped>
  .uploadProgram{
    position:fixed;
    height:100%;
    width: 100%;
    background: rgba(0,0,0,0.7);
    top:0;
    left:0;
  }
  .uploadProgramFrom .grid-content  ul>li{
    margin:10px 0;
  }
  .uploadProgramFrom .upload_progress{
    margin-top:20px;
  }
  .uploadProgramFrom .upload_progress #uploadProgFile{
    position: absolute;
    top:5px;
    width: 200px;
    opacity: 0;
    right: 0;
  }
  .uploadProgramFrom ul li span{
     display: inline-block;
     width:100px;
    vertical-align: top;
     text-align: right;
  }
  .uploadProgram .uploadProgramFrom{
    background: #fff;
    position:relative;
    margin:0 auto;
    top: 50%;
    transform: translateY(-50%);
    width:1000px;
    border:2px solid #eef1f6;
    padding-top:5px;

  }
  .uploadProgram .uploadProgramFrom .el-input,
  .uploadProgram .uploadProgramFrom .el-textarea,
  .uploadProgram .uploadProgramFrom .el-date-editor.el-input{
    width:200px;
  }
  .uploadProgram .chooseCategoryMask{
    background: rgba(0,0,0,0.2);
    z-index: 100;
  }
  .uploadProgram .el-row{
    margin-top:0;
  }
  .uploadProgram .chooseCategory, .uploadProgram .chooseCategoryMask{
    position: absolute;
    height:100%;
    width: 100%;
    top:0;
    left:0;
  }
  .uploadProgram .grid-content.bg-purple{
    margin-left:10px;
  }
  .uploadProgram .cmdFormat input,
  .uploadProgram .cmdFormat select{
    border:1px solid #bfcbd9;
  }
  .uploadProgram .chooseCategoryBox{
    background: #fff;
    position:relative;
    border:2px solid #ededed;
    margin: 0 auto;
    z-index:200;
    top: 50%;
    transform: translateY(-50%);
    min-height:400px;
    width:40%;
  }
  .uploadProgram .categoryList i{
    display: inline-block;
    width: 16px;
    cursor: pointer;
    height: 16px;
    margin-top:5px;
    margin-right: 5px;
  }
  .uploadProgram .categoryList i.up{
    transition: all 0.25s;
    background: url("../assets/img/hcm-file-open.png") no-repeat center center;

  }
  .uploadProgram #progressBar{
    margin-top:10px;
    margin-right:-50px;
  }
  .uploadProgram .categoryList i.down{
    transition: all 0.25s;
    background: url("../assets/img/hcm-file-closed.png") no-repeat center center;

  }
  .uploadProgram .cmdFormat{
    width:80%;
    float: left;
    margin:10px auto;
    min-height:300px;
    border: 2px solid #bfcbd9;
  }
  .uploadProgram .cmdFormat .cmdInput{
    margin:5px;
  }
  .uploadProgram .cmdFormatName{
     float: left;
     margin:5px;
  }
  .uploadProgram .cmdFormat .cmdInput .el-input{
    width:350px;
  }
  .uploadProgram .cmdFormat button{
    padding: 4px 5px;
    border-radius: 4px;
    color:#fff;
    border: 1px solid #bfcbd9;
  }
  .uploadProgram .cmdFormat .cmdTable{
    width: 95%;
    margin: 0 auto;
  }
  .uploadProgram .cmdFormat .cmdTable tr td{
    text-align: center;
  }
  .uploadProgram .cmdFormat .cmdTable input{
    width: 80%;
  }
  .uploadProgram .cmdFormat .cmdInput button.addStyle{
     background: #67C23A;
  }
  .uploadProgram .cmdFormat .cmdInput button.delStyle{
    background: #EB9E05;
  }
  .uploadProgram .cmdFormat button.generateStyle{
    background: #409EFF;
    margin-left:12px;
  }
  .uploadProgram .cmdFormat .generate .generateTextarea.el-textarea{
    width: 80%;
    margin-bottom:20px;
  }
  .uploadProgram .cmdFormat .generate{
    text-align:center;
  }
  .uploadProgram  .closeBtn{
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
  .uploadProgram  .closeBtn:hover{
    color:#20a0ff
  }

</style>
