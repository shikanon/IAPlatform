<template>
  <div class="faceRecognition">
    <div class="myContainer">
      <titles :titleContent="titleContent"></titles>
      <div class="myContent">
        <div class="content">
          <span>补充人脸库:</span>&nbsp;&nbsp;<input type="file" value="" name="faceFilename" id="faceFilename" @change="showImage">
          <form class="form-inline">
            <label>人脸标签&nbsp;:&nbsp;</label><input id="faceLabel" type="text" name="faceLabel" value="" v-model="faceLabel">
          </form>
          <div class="showPic">
            <div class="inner">
              <img src="" alt="" id="faceImg" >
            </div>
          </div>
          <span class="upLoadPic">请上传单张人脸照片</span>
          <br>
          <a type="button" class="mySubmit" @click="saveface">提交</a>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import $ from 'jquery'
  import post from '../service/post'
  import get from '../service/get'
  import { HOST } from '../service/HOST'
  import { Message } from 'element-ui';
  import titles from '../components/titles.vue'
  export default {
     name: 'item',
     data () {
       return {
         titleContent:'人脸识别库',
         faceLabel:'',
       }
     },
     mounted:function(){

     },
     components:{
      titles
     },

     methods:{
       back:function () {
         this.$router.push('/topMenu/item/waitForRecognition')
       },
       showImage:function () {
         //以下即为完整客户端路径
         let _that=this;
         let file_img=document.getElementById("faceImg");
         let fileImage = document.getElementById('faceFilename');
         _that.$getPath(file_img,fileImage,file_img);

       },
       saveface:function () {
           let _that=this;
           let data = new FormData();
           var fileContent=$('#faceFilename').prop('files')[0];
           var imgSrc=$("#faceImg").attr('src');
           data.append('file',fileContent);
           data.append('faceLabel',this.faceLabel);
           if(_that.faceLabel==''&&imgSrc!=''){
             Message({message: '请添加人脸标签', type: 'error'})
           }else if(_that.faceLabel!=''&&imgSrc==''){
             Message({message: '请添加补充人脸库图片', type: 'error'})
           }else if(_that.faceLabel==''&&imgSrc==''){
             Message({message: '请添加补充人脸库图片和人脸标签', type: 'error'})
           }else{
             _that.axios.post("/upload/face",data).then(function(data) {
                 if(data=='success'){
                   Message({message: '提交成功', type: 'success'})
                 }
             },function(error) {
               Message({message: '系统异常,请重试', type: 'error'})

             });
           }


       }
   }
   }
</script>

<style>
  .leftNav{
    position:relative;
  }

  .faceRecognition img{
    width:100%;
    height:100%;
  }
  .el-collapse-item__header__arrow{


   }
  .faceRecognition{
    height: 100%;
  }
  .faceRecognition .myContainer{
    height: 100%;
  }
  .faceRecognition .myContent{
    margin: 0 20px;
    padding: 20px;
  }
</style>
