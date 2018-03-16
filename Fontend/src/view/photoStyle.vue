<template>
  <div class="photoStyle">
      <div class="myContainer">
         <titles :titleContent="titleContent"></titles>
          <div class="myContent">
            <div class="styleTitle">
                <img :src="bgStylePhoto" class="bgStylePhoto" alt="">
                <div class="styleRLeft">
                  <h3>风格选择</h3>
                  <input type="button" @click="styleMigrateClick" :disabled="isdisbaled" :value="isdisbaled==true?'识别中...':'风格迁徙'">
                </div>
                <div class="styleRight">
                  <button>图片风格</button>
                  <img :src="topComRight" class="topComRight">
                </div>
                <div class="stylePhotoList">
                  <ul >
                    <li v-for="(item,index) in stylePhoto" :class="[index==currIndex?'active':'']" @click="selectStyle(item,index)">
                      <div class="stylePhotoBg">
                        <img :src="[index==currIndex?styleBgPressed:styleBg]" class="styleBg" alt="">
                        <img :src="urlImg+item.url" alt="" class="stylePhoto">
                      </div>
                    </li>
                  </ul>
                </div>
              </div>
            <div class="photoContent">
              <div class="contentPhoto">
                <img :src="leftBg" alt="" class="leftBg">
                <span class="text-info">原图</span>
                <div class="contentPhotoBorder">
                  <div class="contentPhotoContent">
                    <img id="photoStyleimg" class="photoStyleimg"  :src="photoContent">
                    <div class="button">
                      <input type='file' class="fileImage" id="fileImage"  value='' @change="showImage"/>
                      <img :src="uploadImg" alt="" >
                      <div>点击上传图片</div>
                    </div>

                  </div>

                </div>
              </div>
              <div class="combinePhoto">
                <img :src="rightBg" alt="" class="rightBg">
                <span class="text-info"> 合成图</span>
                <div class="combinePhotoBorder">
                  <div class="combinePhotoContent" v-loading="loading1" :element-loading-text="loadContent">
                    <img id="combineimg" alt="" :src="combineimg">
                  </div>
                </div>
              </div>
            </div>

            <!--
              <div class="contentPhoto">
                <span class="text-info">内容图片</span>
                <div class="contentPhotoBorder">
                  <div class="contentPhotoContent">
                    <img id="photoStyleimg" alt="" :src="photoContent">
                  </div>
                </div>
              </div>
              <div class="myArrow"></div>
              <div class="combinePhoto">
                  <span class="text-info"> 合成图片</span>
                  <div class="combinePhotoBorder">
                    <div class="combinePhotoContent" v-loading="loading1" :element-loading-text="loadContent">
                      <img id="combineimg" alt="" :src="combineimg">
                    </div>
                  </div>
              </div>
              -->
          </div>
      </div>
   <!-- <transition name="fade">
     <maskShow v-show="selectMask" @exitModalClick="exitchooseStyle" @saveImgClick="savechooseStyle" :styleImgData="childData"></maskShow>
    </transition>-->
  </div>

</template>

<script>
  import $ from 'jquery'
  import post from '../service/post'
  import get from '../service/get'
  import { HOST } from '../service/HOST'
  import { Message } from 'element-ui';
  import titles from '../components/titles.vue'
  //import maskShow from "../components/chooseStyle.vue"
   export default {
     name: 'item',
     data () {
       return {
         titleContent:'图像风格迁徙',
         chooseStyle:require("../assets/img/image/styleBtn.png"),
         topComRight:require("../assets/img/image/top_com2.png"),
         bgStylePhoto:require("../assets/img/image/top_com1.png"),
         leftBg:require("../assets/img/image/left1.png"),
         rightBg:require("../assets/img/image/right1.png"),
         uploadImg:require("../assets/img/image/add.png"),
         styleBg:require("../assets/img/image/top_box_nor.png"),
         styleBgPressed:require("../assets/img/image/top_box_pressed.png"),
         urlImg:HOST,
         stylePhoto:[],
         currIndex:0,
         imgIndexName:'',
         loading:true,
         loading1:false,
         loadContent:'拼命加载中...',
         selectMask:false,
         mockData:[],
         childData:[],
         combineimg:'',
         isdisbaled:false,
         styleImg:'',
         urlImage:'',
         photoContent:'',

       }
     },
     components: {
       //maskShow,
       titles
     },
     beforeMount:function () {
       this.getStyle()
     },
     mounted:function(){
       let _that=this;
       _that.autoScreen();
       $(window).resize(function () {
         _that.autoScreen()
       })
     },
     methods:{
       autoScreen:function () {
         let width=$(window).width();
         let height=$(window).height();
         $(".photoStyle .photoContent").css({
             height:height-60-69-240+"px"
         });
         $(".photoStyle .contentPhotoBorder .button img").css({
           marginTop:(height-60-69-240)/4+"px"
         });
         $(".photoStyle .combinePhoto .text-info").css({
             left:width-width*0.986+'px'
         });
         $(".photoStyle .contentPhoto .text-info").css({
             right:width-width*0.985+'px'
         })

       },
       mockeach:function (data) {
         this.childData = [];
         for(let i = 0; i<data.length; i++){
           if(i%3 == 0){
             var items = [];
             for(let j = 0; j<3; j++){
               if(data[i+j] !== undefined){
                 items.push(data[i+j]);
               }
             }
             this.childData.push(items);
           }
         }
       },
       //得到图片风格
       getStyle:function () {
         let _that=this;
         _that.axios.get("/return/thumbnail").then(function(data) {
            _that.stylePhoto=data.data;
            _that.imgIndexName=data.data[0].picname;
         }, function(error) {
             console.log(error)
           Message({message: '系统异常,请重试', type: 'error'})
           _that.loading=false;
         });
       },
       selectStyle:function (item,index) {
         let _that=this;
         _that.currIndex=index;
         _that.imgIndexName=item.picname;
         _that.urlImage=item.url;
       },

       showImage:function () {
         //以下即为完整客户端路径
         let _that=this;
         let file_img=document.getElementById("photoStyleimg");
         let fileImage = document.getElementById('fileImage');
         _that.$getPath(file_img,fileImage,file_img);
       },

       styleMigrateClick:function () {
         let _that=this;
         var fileVal=$('#fileImage').val()
         let dataImg = new FormData();
         dataImg.append('file',$('#fileImage').prop('files')[0]);
         dataImg.append('picname',this.imgIndexName);

         _that.loading1=true;

             if(this.imgIndexName==''&&fileVal!=''){
               Message({message: '请选择图片风格', type: 'error'})
               _that.loading1=false;
             }else if(this.imgIndexName!=''&&fileVal==''){
               Message({message: '上传内容图片', type: 'error'})
               _that.loading1=false;
             }else if(this.imgIndexName==''&&fileVal==''){
               Message({message: '请上传内容图片和选择图片风格', type: 'error'})
               _that.loading1=false;

             }else{
               _that.axios.post("/api/image2",dataImg).then(function(data) {
                 _that.isdisbaled=true;
                 let obj=data.data;
                 let url=obj.task_url;
                 let imgUrl=obj.image_url;
                 let getImg;
               getImg=setInterval(function () {
                      get(url).then(function(data) {
                      if(data.status=='PROGRESS'||data.status=='PENDING'){
                        _that.loadContent='正在识别图片...'
                      }else if(data.status=='SUCCESS'){
                        clearInterval(getImg);
                        _that.loading1=false;
                        _that.isdisbaled=false;
                        let imgUrls = imgUrl.split('/');
                        _that.combineimg=_that.urlImg+'/image/'+imgUrls[imgUrls.length-1];
                        Message({message: '图片迁徙失败成功', type: 'success'});
                      }else if(data.status=='FAILURE'){
                        clearInterval(getImg);
                        _that.loading1=false;
                        _that.isdisbaled=false;
                        Message({message: '图片迁徙失败', type: 'error'});
                      }
                    }, function(error) {
                      _that.isdisbaled=false;
                      Message({message: '系统异常,请重试', type: 'error'})
                    });
                  },2000);
               }, function(error) {
                 Message({message: '系统异常,请重试', type: 'error'})
                 _that.loading1=false;
                 _that.isdisbaled=false;
               });
             }


       }
     }
   }
</script>

<style>
  .photoStyle .styleTitle{
    height:229px;

    position: relative;
    /*border:1px solid #fff;*/
  }
  .photoStyle .myContent{
    margin:-20px 20px 0 20px;
  }
  .photoStyle .styleTitle .bgStylePhoto{
    position: absolute;
    top:0;
    left:0;
    width: 100%;
    height:100%
  }
  .photoStyle .styleRight{
    position: absolute;
    top:-1px;
    right:-3px;
  }
  .photoStyle .styleRight button{
    color: #fff;
    position: absolute;
    top:0;
    right:0;
    width:167px;
    height:55px;
    background: transparent;
    border:0;
    outline: none;
  }
  .photoStyle .styleRLeft{
    position: absolute;
    color:#fff;
  }
  .photoStyle .styleRLeft h3{
    margin: 20px 0 20px 20px;
    float: left;
  }
  .photoStyle .styleRLeft input{
    float: left;
    margin: 15px 0 20px 60px;
    background: transparent;
    outline: none;
    position: relative;
    border:0;
    height:32px;
    width:88px;
    background: url("../assets/img/image/styleBtn.png") no-repeat center center;
    color:#7bd433;
    font-weight: bold;
    text-shadow: 0 0 6px #7bd433;
  }
  .photoStyle .styleRLeft button img{
    position: absolute;
    top:0;
    left:0;
    width:100%;
    height:100%;
  }
  .photoStyle .stylePhotoList{
    overflow: auto;
    position: absolute;
    top: 63px;
    width:97%;
    margin: 0 10px 0 20px;
    height: 154px;
  }
  .photoStyle .stylePhotoList li{
    float: left;
    width:16.5%;
    padding:5px 10px;
    box-sizing: border-box;

  }
  .photoStyle .stylePhotoList li .stylePhotoBg{
    height:145px;
    padding: 15px;
    position: relative;
    box-sizing: border-box;
  }
  .photoStyle .stylePhotoList li .stylePhotoBg .styleBg{
    width:100%;
    height:100%;
    position: absolute;
    top:0;
    left: 0;
  }
  .photoStyle .stylePhotoList li .stylePhotoBg .stylePhoto{
    width:100%;
    height:100%;
  }
  .photoStyle .photoContent:after{
    content: '';
    display: block;
    clear: both;
  }
  .photoStyle .photoContent{
    margin-top:6px;
    background: url("../assets/img/image/arrow.png") no-repeat center center;
  }
  .photoStyle .contentPhoto{
    float: left;
    width: 50%;
    position: relative;
    height: 100%;
  }
  .photoStyle .contentPhoto .text-info{
    position: absolute;
    right:10px;
    display: inline-block;
    color:#fff;
    text-align: center;
    top:30px;
    width:20px;
  }
  .photoStyle .contentPhoto .leftBg{
    width: 100%;
    height: 100%;
  }
  .photoStyle .photoContent .combinePhoto{
    float: right;
    width: 50%;
    position: relative;
    height: 100%;
  }
  .photoStyle .photoContent .combinePhoto .text-info{
    position: absolute;
    left:10px;
    display: inline-block;
    color:#fff;
    text-align: center;
    bottom:20px;
    width:20px;
  }
  .photoStyle .photoContent .combinePhoto .rightBg{
    width: 100%;
    height: 100%;
    position: absolute;
    left:0;
    top:0;
  }
  .photoStyle .photoContent .combinePhoto .combinePhotoContent,
  .photoStyle .photoContent .combinePhoto .combinePhotoBorder{
    height:100%;
  }

  .photoStyle .photoContent .contentPhoto .contentPhotoBorder{
    position: absolute;
    top:7%;
    left:4%;
    width:85%;
    height:85%;
    border:2px dashed #172c59;
  }
  .photoStyle  .contentPhoto .contentPhotoContent{
    max-height:100%;
    overflow: auto;
  }
  .photoStyle  .contentPhoto .contentPhotoBorder .button{
    width:100%;
    height: 100%;
    background:transparent;
    border:0;
    text-align: center;
    outline: none;
    color:#4e9bea;
  }
  .photoStyle  .contentPhoto .contentPhotoBorder .button input{
    position: absolute;
    opacity: 0;
    top:0;
    left:0;
    width:100%;
    height: 100%;
  }
  .photoStyle  .contentPhoto .contentPhotoBorder .button img{
    margin-top:80px;
  }
  .photoStyle  .contentPhoto .photoStyleimg{

  }
  .photoStyle .combinePhotoBorder .el-loading-mask{
    background: transparent;
  }
  .photoStyle .combinePhoto .combinePhotoContent #combineimg{
    min-width: 88%;
    margin-top: 2%;
    margin-left: 9%;
    min-height: 91%;
    position: absolute;
  }
  /*
  .leftNav{
    position:relative;
  }
  .chooseMask{
    position:absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 10;
    background-color: #000;
    opacity: 0.5;
  }
  .el-button.chooseStyle{
    padding: 0;
  }
  .fade-enter-active, .fade-leave-active {

    transition: opacity .5s
  }
  .contentPhotoContent img{
    width:100%;
    height:100%;
  }
  .fade-enter, .fade-leave-to {
    opacity: 0
  }
  .photoStyle img{
    width:100%;
    height:100%;
  }
  .photoStyle{
    height:100%;
  }
  .photoStyle .myContainer{
    height:100%;
  }
  .photoStyle .myContent{
    margin:0 20px;
    padding: 20px 0;
  }
  .photoStyle .styleRound{
    outline: none;
    border-width: 0;
  }
  .photoStyle .styleRound{
    outline: none;
    border-width: 0;
  }
  .photoStyle .styleRound[disabled='disabled']{
    cursor: wait;
    background: #ccc !important;
  }*/
  /*.row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }*/
</style>
