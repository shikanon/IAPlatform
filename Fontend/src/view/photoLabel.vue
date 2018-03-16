<template>
  <div class="photoLabel">
    <div class="loader">
      <titles :titleContent="titleContent"></titles>
      <div class="main">
        <div class="analysisPhoto">
          <div class="imgupload">
            <img :src="liftBg" alt="" class="liftBg">
            <div class="imguploadTitle">图片上传 <button class="saveImgBtn"></button></div>

            <div class="imguploadContent">
              <img id="img">
              <div class="button">
                <input type='file' class="fileupload" id="iptfileupload"  value='' @change="showImage"/>
                <img :src="uploadImg" alt="" v-show="uploadImgTog">
                <div v-show="uploadImgTog">请上传图片</div>
              </div>
            </div>
          </div>
        </div>
        <div class="titleupload">

          <div class="analysisText" >
            <button  @click="updatebBtn"><span>解析文字</span> </button>
            <div class="analysisContent">
              <img :src="analysisTextBg" alt="">
              <ul   v-loading="loading" element-loading-text="拼命加载中...">
              </ul>
            </div>

          </div>

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
  import titles from "../components/titles.vue"
   export default {
     name: 'item',
     data () {
       return {
         titleContent:'图片标签化',
         loading:false,
         fileup:'',
         fileShow:'',
         uploadImgTog:true,
         analysisTextBg:require("../assets/img/image/lift_3.png"),
         uploadImg:require("../assets/img/image/add.png"),
         liftBg:require("../assets/img/image/lift_1.png"),
         leftBarListBtn:require("../assets/img/image/menu.png")
       }
     },
     mounted:function(){
         let _that=this;
       _that.autoScreen();
       $(window).resize(function () {
         _that.autoScreen()
       })
     },
     components:{
        titles
     },
     methods:{
       autoScreen:function () {
         let width=$(window).width();
         let height=$(window).height();
          $('.photoLabel .imgupload').css({
              height:height-60-62-15+'px'
          });
         $('.photoLabel .imguploadContent').css({
             height:height-60-62-115+'px'
         });
         $(".photoLabel .imguploadContent .button img").css({
             marginTop:height/4+"px"
         });
         $('.photoLabel .analysisContent').css({
           height:height-60-62-90+'px'
         })

       },
       showImage:function () {
         //以下即为完整客户端路径
         let _that=this;
         _that. uploadImgTog=false;
         this.fileup=$('#iptfileupload').val();
         let file_img=document.getElementById("img");
         let iptfileupload = document.getElementById('iptfileupload');
         _that.$getPath(file_img,iptfileupload,file_img);
       },
      /* showFile:function () {
         let _that=this;
         this.fileup=$('#iptfileupload').val();
         let file_img=document.getElementById("image");
         let iptfileupload = document.getElementById('iptfileupload');
         _that.$getPath(file_img,iptfileupload,file_img);
         $("analysisPhoto.nodata").hide();
       },
       back:function () {
         this.$router.push("/topMenu/itemShow")
       },*/
       updatebBtn:function(){
         //以下即为完整客户端路径
         let _that=this;
         if(_that.fileup!=''){
           let str3='';
           $('.titleupload ul').empty();
           _that.loading=true;
           let data = new FormData();
          data.append('file', $('#iptfileupload').prop('files')[0]);
           _that.axios.post("/api/image1",data).then(function(data) {
            _that.loading=false;
            console.log(data.data)
            for(var i=0;i<data.data.length;i++){
            str3+=" <li>"+(i+1)+'.'+data.data[i][0]+"</li>"
            }
            $('.titleupload ul').append(str3)
           }, function(error) {

                  Message({message: '系统异常,请重试', type: 'error'})
                  _that.loading=false;
                  $(".nodata").show();
                });
         }else{
            Message({message: '请先上传图片再提交', type: 'warning'});
       }
     }
     }
   }
</script>

<style>
  .leftNav{
    position:relative;
  }

  .photoLabel{
    background: transparent;
  }


  .photoLabel .imgupload{
    background: transparent;
    box-shadow:0 0 0 #fff;
    padding: 0;
  }
  .photoLabel .imgupload .liftBg{
    width:100%;
    padding: 0;
    position:absolute;
    height: 100%;
  }
  .photoLabel .main{
    padding:0 20px 0;
  }
  .photoLabel .analysisPhoto{
    width:60%
  }
  .photoLabel  .analysisContent{
    height:493px;
    margin-top:66px;
    position: relative;
  }
  .photoLabel  .analysisContent img{
    height:100%;
    width:100%;
    margin-top:-3px;
    position: absolute;
  }
  .photoLabel .analysisText{
    background: transparent;
  }
  .photoLabel .analysisText button{
    background: url("../assets/img/image/head_bg2.png") transparent no-repeat center center;
    border:0;
    outline: none;
    width:108px;
    color:#fff;
    position: absolute;
    height:78px;
    z-index:2;
    margin-top:-3px;

  }
  .photoLabel .analysisText button span{
    display:block;
    margin-top:-24px;
  }
  .photoLabel .titleupload{
    width:40%;
    height:0;
  }
  .photoLabel  .saveImgBtn{
    min-width:90px;
    height:30px;
    vertical-align: middle;
    background: transparent;
    margin-left:5%;
    border:0;
  }
  .photoLabel  .saveImgBtn img{
    padding: 0;
  }
  .photoLabel .imguploadTitle{
      padding:20px 0 0 20px;
      color:#fff;
      letter-spacing: 2px;
      position: relative;
  }
  .photoLabel .imguploadContent{
    margin: 20px;
    position: relative;
    overflow: auto;
    border: 2px dashed #172c59;
  }
  .photoLabel .imguploadContent #img{
    position: absolute;
  }
  .titleupload .analysisText{
    border:0;
  }
  .photoLabel .imguploadContent .button{
    display: block;
    margin: 0 auto;
    height: 100%;
    width: 100%;
    border:0;
    color:#fff;
    text-align: center;
    background: transparent;
  }
  .photoLabel .imguploadContent .button img{
    margin-top:150px;
  }
  .photoLabel .imguploadContent .fileupload{
    height:100%;
    opacity: 0;
  }
  .photoLabel .titleupload ul{
    position: absolute;
    color: #fff;
    width: 100%;
    height: 100%;
    padding: 15px 20px;
    box-sizing: border-box;
  }
  .photoLabel .titleupload ul .el-loading-mask{
    background: transparent;
  }
</style>
