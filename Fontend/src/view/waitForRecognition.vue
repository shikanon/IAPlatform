<template>
  <div class="waitForRecognition">
    <div class="myContainer">
      <titles :titleContent="titleContent"></titles>
      <div class="myContent">
        <div class="startRecognition">
          <span>上传图片</span>&nbsp;<input type="file" value="" name="waitFilename" id="waitFilename" @change="showImage">
          <a type="button" class="return" @click="startClick">开始识别</a>
        </div>
        <div class="photoForRecognition">
          <div class="photoForRecognitionTitle">
            <span class="text-info">待识别图片</span>
            <a type="button" class="return" @click="faceRecognitionClick">补充人脸库</a>
          </div>
        </div>
        <div class="showPhotoForRecognition">
          <img src="" alt="" id="waitFileImg" :src="waitImg">
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import $ from 'jquery'
  import post from '../service/post'
  import get from '../service/get'
  import { Message } from 'element-ui';
  import titles from '../components/titles.vue'
   export default {
     name: 'item',
     data () {
       return {
         titleContent:'人脸识别',
         waitImg:''
       }
     },
     mounted:function(){

     },
     components:{
       titles
     },
     methods:{
       showImage:function () {
         //以下即为完整客户端路径
       /*  let _that=this;
         let file_img=document.getElementById("waitFileImg");
         let fileImage = document.getElementById('waitFilename');
         _that.$getPath(file_img,fileImage,file_img);*/
       },
       faceRecognitionClick:function () {
         this.$router.push('/topMenu/item/faceRecognition')
       },
       back:function () {
         this.$router.push("/topMenu/itemShow")
       },
       startClick:function () {
           let _that=this;
           _that.loading=true;
           let data = new FormData();
           var waitFile= $('#waitFilename').prop('files')[0];
           var fileVal=$('#waitFilename').val()
           data.append('file',waitFile);
         if(fileVal!=''){
           _that.axios.post("/face",data).then(function(data) {
             _that.waitImg="data:image/jpeg;base64,"+data.name
             _that.loading=false;
           }, function(error) {
             Message({message: '系统异常,请重试', type: 'error'});
             _that.loading=false;
           });
         }else{
           Message({message: '请先上传图片', type: 'error'});
         }



       }
   }
   }
</script>

<style>
  .leftNav{
    position:relative;
  }
  .el-collapse-item__header__arrow{


   }
  .waitForRecognition{
    height:100%;
  }
  .waitForRecognition .myContainer{
    height:100%;
  }
  .waitForRecognition img{
    width:100%;
    height:100%;
  }
  .waitForRecognition .myContent{
    padding: 20px;
    margin: 0 20px;
  }
  .startRecognition{
    margin: 0;
  }
</style>
