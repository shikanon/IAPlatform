<template>
  <div class="imgObjExtraction">
    <div class="loader">
      <titles :titleContent="titleContent"></titles>
       <div class="main">
        <div class="analysisPhoto">
          <div class="imgupload">
            <img :src="liftBg" alt="" class="liftBg">
            <div class="imguploadTitle">图片上传 <button class="saveImgBtn"><img :src="saveImgBtn" alt=""></button></div>
            <div class="imguploadContent">
              <img id="image">
              <div class="button">
                <input type='file' class="fileupload" id="imgObjUpload"  value='' @change="showFile"/>
                <img :src="uploadImg" alt="" v-show="imgShow">
                <div v-show="imgShow">请上传图片</div>
              </div>
            </div>
          </div>
        </div>
        <div class="titleupload">
          <div class="analysisText" >
            <button><span>解析列表</span> </button>
            <div class="analysisContent"   v-loading="loading" element-loading-text="拼命加载中...">
              <img :src="analysisTextBg" alt="">
              <div class="objListName">
                <div class="objListTitle">
                  <ul>
                   <transition  name="slide-fade" v-for="(item,i) in mockListTitle" :key="item">
                      <li v-if="i==0?firstShow:itemShow" :class="[currIndex===i?'bold':'']" @click="itemClick(item,i)" :style="{width:widthList}">
                        {{item}}
                      </li>
                    </transition>
                  </ul>
                  <div class="objList" @click="listClick" ><i class="icon-list"></i></div>
                </div>
              </div>
              <div  class="loadList">
                <ul v-for="(value,indexs) in mockList" v-show="indexs==currIndex">
                  <li v-for="(key,index) in value"><div> <img :src="listBg" alt="" class="listBg"><img :src="urlImg+key" alt=""></div></li>
                </ul>
              </div>
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
  import { HOST } from '../service/HOST';
  import titles from '../components/titles.vue'
   export default {
     name: 'item',
     data () {
       return {
         titleContent:'图像物体抽取',
         loading:false,
         urlImg:HOST,
         listBg:require("../assets/img/image/lift_3.png"),
         listImg:require("../assets/img/image/facePeople.png"),
         analysisTextBg:require("../assets/img/image/lift_6.png"),
         uploadImg:require("../assets/img/image/add.png"),
         saveImgBtn:require("../assets/img/image/btn_selected.png"),
         liftBg:require("../assets/img/image/lift_1.png"),
         leftBarListBtn:require("../assets/img/image/menu.png"),
         fileup:'',
         firstShow:true,
         fileShow:'',
         itemShow:false,
         imgShow:true,
         dataShow:true,
         currIndex:0,
         mockList:[],
         mockListTitle:[]
       }
     },
     components:{
       titles
     },
     computed: {
       widthList: function () {
         return 90/this.mockList.length+'%'
       }
     },
     mounted:function(){
         let _that=this;
       _that.autoScreen();
       $(window).resize(function () {
         _that.autoScreen();
       })
     },
     methods:{
       autoScreen:function () {
         let width=$(window).width();
         let height=$(window).height();
         $(".imgObjExtraction .imgupload").css({
           height:height-60-79+"px"
         });
         $(".imgObjExtraction .imguploadContent").css({
           height:height-60-180+"px"
         });
         $(".imgObjExtraction .imguploadContent .button img").css({
            marginTop:(height-60-69-240)/2+"px"
         });
         $(".imgObjExtraction .analysisContent").css({
            height:height-60-150+"px"
         });
       },
       showFile:function () {
         let _that=this;
         _that.mockList=[]
         _that.mockListTitle=[]
         this.fileup=$('#imgObjUpload').val();
         let file_img=document.getElementById("image");
         let imgObjUpload = document.getElementById('imgObjUpload');
         _that.$getPath(file_img,imgObjUpload,file_img);
         _that.imgShow=false;
         _that.loading=true;
         let dataImg = new FormData();
         dataImg.append('file',$('#imgObjUpload').prop('files')[0]);
         post("/api/image3",dataImg).then(function(data) {
           _that.loading=false;
             for(let key in data){
               _that.mockListTitle.push(key);
               _that.mockList.push(data[key])
             }
           _that.mockListTitle.sort();
           _that.mockList.sort()
         }, function(error) {
             console.log(error);
           _that.loading=false;
           Message({message: '系统异常,请重试', type: 'error'})
         });
       },
       listClick:function () {
         let _that=this;
         _that.itemShow=!_that.itemShow

       },
       itemClick:function (item,i) {
         let _that=this;
         _that.currIndex=i;
         console.log(i)

       },


     }
   }
</script>

<style scoped>
  .imgObjExtraction{
    background: transparent;
  }


  .imgObjExtraction .imgupload{
    background: transparent;
    box-shadow:0 0 0 #fff;
    padding: 0;
  }
  .imgObjExtraction .imgupload .liftBg{
    width:100%;
    padding: 0;
    position:absolute;
    height: 100%;
  }
  .imgObjExtraction .main{
    padding:0 20px 0;
  }
  .imgObjExtraction .analysisPhoto{
    width:60%
  }
  .imgObjExtraction .mainContent{
    overflow: hidden;
  }
  .imgObjExtraction  .analysisContent{
    height:485px;
    margin-top:66px;
    position: relative;
  }
  .imgObjExtraction  .analysisContent img{
    height:100%;
    width:100%;
    margin-top:-3px;
    position: absolute;
  }
  .imgObjExtraction .analysisText{
    background: transparent;
  }
  .imgObjExtraction .analysisText button{
    background: url("../assets/img/image/head_bg2.png") transparent no-repeat center center;
    border:0;
    outline: none;
    width:108px;
    color:#fff;
    position: absolute;
    height:78px;
    z-index:2;
    margin-top:-2px;

  }
  .imgObjExtraction .loadList{
    position: absolute;
    width: 100%;
    height: 100%;
  }
  .imgObjExtraction .loadList>ul{
    -moz-column-count: 3;
    -webkit-column-count: 3;
    column-count: 3;
    -moz-column-gap: 1em;
    -webkit-column-gap: 1em;
    column-gap: 1em;
    margin-top: 60px !important;
    margin-left: 25px;
    margin-right: 25px;
    overflow-y: auto;
    height:80%;
   /* margin-top: 60px !important;
    margin-left: 25px;

    overflow-y: auto;
    height:80%;*/
  }
  .imgObjExtraction .loadList ul li{
    padding: 10px 0 5px 0;
    margin: 0;
    -moz-page-break-inside: avoid;
    -webkit-column-break-inside: avoid;
    break-inside: avoid;
    /*float: left;
    width:33%;
    padding:8px;
    box-sizing: border-box;*/
  }
  .imgObjExtraction .loadList ul li>div{
    padding: 15px;
    box-sizing: border-box;
    position: relative;
  }
  .imgObjExtraction .loadList ul li>div .listBg{
    position: absolute;
    top:0;
    left:0;
  }
  .imgObjExtraction .loadList ul li img{
    width: 100%;
    height:100%;
    position: inherit;
  }
  .imgObjExtraction .analysisText button span{
    display:block;
    margin-top:-24px;
  }
  .imgObjExtraction .titleupload{
    width:40%;
    height:0;
  }
  .imgObjExtraction  .saveImgBtn{
    min-width:90px;
    height:30px;
    vertical-align: middle;
    background: transparent;
    margin-left:5%;
    border:0;
  }
  .imgObjExtraction  .saveImgBtn img{
    padding: 0;
  }
  .imgObjExtraction .imguploadTitle{
    padding:20px 0 0 20px;
    color:#fff;
    position: relative;
  }
  .imgObjExtraction .imguploadContent{
    margin: 20px;
    position: relative;
    overflow: auto;
    border: 2px dashed #172c59;
  }
  .imgObjExtraction .imguploadContent #image{
    position: absolute;
  }
  .imgObjExtraction .analysisText{
    border:0;
  }
  .imgObjExtraction .imguploadContent .button{
    display: block;
    margin: 0 auto;
    height: 100%;
    width: 100%;
    border:0;
    text-align: center;
    color:#fff;
    background: transparent;
  }
  .imgObjExtraction .imguploadContent .fileupload{
    height:100%;
    opacity: 0;
  }
  .imgObjExtraction .titleupload ul{
    color: #fff;
    box-sizing: border-box;
    margin-top:-5px;
  }
  .imgObjExtraction .objListTitle{
  }
  .imgObjExtraction .objListTitle ul{
       margin:-3px 3px 0;
       height:36px;
       border-bottom:2px solid #172c59 ;
  }
  .imgObjExtraction .objListTitle ul li:first-child{
      height:40px;
  }
  .imgObjExtraction .objListTitle ul li{
        line-height: 38px;
        height:38px;
        min-width: 78px;
        text-align: left;
        text-indent: 1em;
        padding: 0;
        margin:0;
        background: url("../assets/img/image/right1_com3.png") no-repeat right;
  }
  .imgObjExtraction .titleupload ul .el-loading-mask{
    background: transparent;
  }
  .imgObjExtraction .imgupload #image{
    position: absolute;
    top: 0;
    left: 0;
  }
  .imgObjExtraction  .objListTitle{
       overflow: hidden;
     }
  .imgObjExtraction .objListName{
    position: absolute;
    width: 100%;
    z-index:2;
    top:0;
  }
  .imgObjExtraction .objList{
    position: absolute;
    right:8px;
    top:5px;
  }
  .imgObjExtraction .objList .icon-list{
    font-size: 26px;
  }

  .imgObjExtraction .objListName li{
    float: left;
    text-align: center;
    cursor: pointer;
  }
  .imgObjExtraction .bold{
    font-weight: bold;
  }
  .slide-fade-enter-active {
    transition: all .2s ease;
  }
  .slide-fade-leave-active {
    transition: all .2s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slide-fade-enter, .slide-fade-leave-to
    /* .slide-fade-leave-active for below version 2.1.8 */ {
    transform: translateX(50px);
    opacity: 0;
  }

</style>
