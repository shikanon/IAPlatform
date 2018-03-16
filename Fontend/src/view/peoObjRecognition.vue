<template>
  <div class="peoObjRecognition" style="overflow: hidden">
    <div class="loader">
      <titles :titleContent="titleContent"></titles>
      <div class="main">
        <div class="analysisPhoto">
          <div class="imgupload" id="imgupload">
            <div class="imguploadContent" >
              <img class="liftBg"  :src="liftBg"   alt="">
              <div class="analysisBtn">
                智能人流物流识别
              </div>
              <div class="analysisImg">
               <img :src="videoUrl" alt="">
             </div>
            </div>
            <div class="distPeople">
              <div style="height:5%"></div>
              <div class="distPeopleContent" >
                <div  style="min-height:1000px;">
                  <div class="distList"  v-for="(item,index) in videoUrlList" @click="videoUrlClick(item,index)">
                    <img src="../assets/img/image/lift_2.png" alt=""   >
                    <img :src="item.url" alt="">
                  </div>
                </div>
              </div>
            </div>
            <!--<button class="updatebBtn" @click="startClick(btntext)">{{btntext}}</button>
            <img :src="videoUrl" alt="">-->
          </div>

        </div>
        <div class="titleupload" id="titleupload">
          <div class="analysisText"  v-loading="loading" element-loading-text="拼命加载中...">
            <div class="table" >
              <img :src="tablebg" alt="">
              <ul class="dateList">
                <li v-for="(item,i) in getDateDaySplit"  :class="[currIndex==i?'active':'']" @click='getDateList(item,i)'>{{item}}</li>
                <li @click='showMoreClick'>更多</li>
              </ul>
              <div v-show="showMore" class="showMore">
                <li v-for="(item,i) in getDateDay"  :class="[currIndex==i?'active':'']" @click='getDateList(item,i)'>{{item}}</li>
              </div>
              <table>
                <tr>
                  <td><span>(时间/min)</span></td>
                  <td>全园区</td>
                  <td>入口处</td>
                  <td>购物区</td>
                  <td>展览区</td>
                </tr>
                <tr>
                  <td>今日人流量</td>
                  <td v-for="(item,i) in 4">100</td>
                </tr>
                <tr>
                  <td>平均停留时长</td>
                  <td v-for="(item,i) in 4">200</td>
                </tr>
              </table>
            </div>
            <div class="echart">
              <h3>各时间段人流分析</h3>
              <img :src="echartBg" alt="">
              <chart class='chartLine' id="chartLine" :option="echartData.data1"></chart>
            </div>
            <div class="planeContent">
              <h3>园区平面图</h3>
              <img :src="planeBg" alt="" class="planeBg">
              <img  class="planeImg" src="../assets/img/comproblemOne/img1.jpg" alt="">
            </div>
            <!--<div id="addHtml" >

            </div>-->


          </div>

        </div>

      </div>
    </div>

  </div>
</template>

<script>
  import $ from 'jquery'
  import { HOST } from '../service/HOST'
  import post from '../service/post'
  import get from '../service/get'
  import { Message } from 'element-ui';
  import titles from '../components/titles.vue'
  import chart from '../components/charts.vue'
   export default {
     name: 'item',
     data () {
       return {
         titleContent:'智能商铺人流物体识别系统',
         liftBg:require("../assets/img/image/lift_1.png"),
         loading:false,
         echartBg:require("../assets/img/image/right_2.png"),
         btntext:'开始',
         html:'',
         showMore:false,
         currIndex:0,
         fileup:'',
         mockData:{
            todayPeople:{

            },
         },
         echartData:{
             data1:{}
         },
         tablebg:require('../assets/img/image/right_1.png'),
         fileShow:'',
         videoUrlList:[
          {url:HOST+"/video_feed"}
          //{url:"http://192.168.0.245:8787/video_feed"}
         ],
         videoUrl:"",
         imgShow:true,
         dataShow:true,
         getVideo:null,
         planeBg:require('../assets/img/image/right_3.png'),
         journalInfo:{
           people_num:'0(点击开始进行检测)',
           total_people_num:'0(点击开始进行检测)'
         }
       }
     },
     components:{
      titles,chart
     },
     computed: {
       getDateDay:function () {
           let　_that=this;
           let arr=[];
           for(let i=0;i>-30;i--){
             arr.push(_that.$GetDateStr(i))
           }

         return arr
       },
       getDateDaySplit:function () {
         return  this.getDateDay.slice(0,5)
       }
     },
     beforeMount:function () {
         this.videoUrl=this.videoUrlList[0].url
     },
     mounted:function(){
       let _that=this;
       this.echartData.data1= {
         title: {
           text: ''
         },
         xAxis: {
           show:true,
           axisLine:{
             show:true,
             lineStyle:{
               color:'#172c59'
             }
           },
           axisLabel:{
             color:'#fff'
           },
           axisTick:{
             show:false
           },
           splitLine:{
             show:true,
             lineStyle:{
               color:'#172c59'
             }
           },
           boundaryGap : false,
           data: ["10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00"]
         },
         tooltip: {

         },
         legend: {
           data:['节假日','工作日'],
           right:10,
           top:35,
           textStyle:{color:'#fff'}
         },
         yAxis: {
           show:true,
           name:'人次/时间',
           nameTextStyle:{
             color:'#5e7187',
           },
           axisLine:{
             show:true,
             lineStyle:{
               color:'#172c59'
             }
           },
           axisLabel:{
             color:'#fff'
           },
           splitLine:{
             show:true,
             lineStyle:{
                 color:'#172c59'
             }
           },
           axisTick:{
             show:false
           }
         },
         series : [
           {
             name:'节假日',
             type:'line',
             symbol:'circle',
             symbolSize:7,
             smooth:true,
             areaStyle:{
               normal:{
                 color: {
                   type: 'linear',
                   x: 0,
                   y: 0,
                   x2: 0,
                   y2: 1,
                   colorStops: [{
                     offset: 0, color: '#7bd433'
                   }, {
                     offset: 1, color:'transparent'
                   }],
                   globalCoord: false
                 }
               }
             },
             lineStyle:{
               normal:{
                 type:'dashed',
                 width:1
               }

             },
             itemStyle: {
               normal: {

                   color:'#7bd433'

               }
             },
             data:[100, 700, 1650, 2800, 1520, 2650,1500]
           },
           {
             name:'工作日',
             type:'line',
             symbolSize:7,
             smooth:true,
             symbol:'circle',
             areaStyle:{
               normal:{
                 color: {
                   type: 'linear',
                   x: 0,
                   y: 0,
                   x2: 0,
                   y2: 1,
                   colorStops: [{
                     offset: 0, color: '#ffc000'
                   }, {
                     offset: 1, color:'transparent'
                   }],
                   globalCoord: false
                 }
               }
             },
             lineStyle:{
                 normal:{
                   type:'dashed',
                   width:1
                 }

             },

             itemStyle: {
               normal: {

                   color:'#ffc000'

               }
             },
             data:[10, 600, 800, 1495, 900, 1250,900]
           }
         ]

       };

       this.autoScreen()

     },
     methods:{
       autoScreen:function () {
         let imgupload=document.querySelector('#imgupload');
         let titleupload=document.querySelector('#titleupload');
         let chartLine=document.querySelector('#chartLine');
         let dateList=$(".peoObjRecognition .dateList li");
         let tableList=$(".peoObjRecognition .table table")
         let width=$(window).width();
         let height=$(window).height();
         imgupload.style.height=height-60-62-15+'px';
         titleupload.style.height=height-90-62-15+'px';
         dateList.css({fontSize:"0.9vw"});
         tableList.find('td').css({fontSize:"0.9vw"});
         if(height>700){
           tableList.css({height:"68%"});
         } else if(600<height<700){
           tableList.css({height:"55%"});
         }else if(500<height<=600){
           tableList.css({height:"45%"});
         }else{
           tableList.css({height:"35%"});
         }
         $(window).resize(function () {
           let height=$(window).height();
           let width=$(window).width();
           imgupload.style.height=height-60-62-15+'px';
           titleupload.style.height=height-90-62-15+'px';
           dateList.css({fontSize:"0.9vw"});
           tableList.find('td').css({fontSize:"0.9vw"});
           if(height>700){
             tableList.css({height:"68%"});
           } else if(600<height<700){
             tableList.css({height:"55%"});
           }else if(500<height<=600){
             tableList.css({height:"45%"});
           }else{
             tableList.css({height:"35%"});
           }
         })
       },
       videoUrlClick:function(item,index){
         this.videoUrl=item.url
       },
       getDateList:function(item,i) {
         this.currIndex=i;
         this.showMore=false
       },
       showMoreClick:function () {
         this.showMore=!this.showMore
       },
       startClick:function (text) {
         let _that=this;
             if(text=='开始'){
                 _that.btntext='暂停'
               _that.getVideo=setInterval(function () {
                 let datavideo = new FormData();
                 let timestamp = (new Date()).valueOf();
                 datavideo.append('start',timestamp);
                 _that.axios.post("/count",datavideo).then(function(data) {
                   _that.journalInfo=data
                   _that.html=`<div><p class="time">${_that.$getDate()}</p><p>人数:${data.people_num}</p><p>总检测物体数:${data.total_people_num}</p></div>`
                   $("#addHtml").prepend(_that.html)
                 },function (error) {
                   Message({message: '系统异常,请重试', type: 'error'})
                 })
               },1000)

             }else{
               clearInterval(_that.getVideo);
               _that.btntext='开始'
             }


       },
       showFile:function () {
         let _that=this;
         this.fileup=$('#imgObjUpload').val();
         let file_img=document.getElementById("image");
         let imgObjUpload = document.getElementById('imgObjUpload');
         _that.$getPath(file_img,imgObjUpload,file_img);
         _that.imgShow=false
       },
       back:function () {
         this.$router.push("/topMenu/itemShow")
       },

     }
   }
</script>

<style>
  .peoObjRecognition{
    margin: 0;
  }

  .peoObjRecognition .title{
    margin:0;
  }
  .peoObjRecognition .btn-back{
    background-position: right;
  }
  .peoObjRecognition .analysisPhoto{
     position: relative;
  }
  .peoObjRecognition .titleupload .analysisText{
    background: transparent;
    border:0;
  }
  .peoObjRecognition .analysisBtn{
    position:absolute;
    padding:20px 0 0 20px;
    color:#fff;
    width: 80%;
  }
  .peoObjRecognition .distPeople{
      height:30%;
      position: relative;

  }
  .peoObjRecognition .distList{
    float: left;
    width:33%;
    position: relative;

  }
  .peoObjRecognition .distList img:last-child{
    position: relative;
    padding: 25px 30px;
  }
  .peoObjRecognition .distList img:first-child{
    position:absolute;
    top:0;
    width: 100%;
    height:100%;
  }
  /*定义滚动条轨道 内阴影+圆角*/


  .peoObjRecognition .distPeople:before{
    content: '';
    display: block;
    position: absolute;
    border-top:2px solid #172c59;
    width:100%;
    top:10px;
  }
  .peoObjRecognition .distPeople .distPeopleContent{
    overflow: auto;
    padding-top: 10px;
    height:95%;

  }

  .peoObjRecognition .analysisImg{
    position: absolute;
    top:40px;
    height:90%;
    padding:10px;
    box-sizing: border-box;
    width:100%;
  }
  .peoObjRecognition .analysisBtn button{
    margin-left:8%;
    height:28px;
    width:80px;
    color:transparent;
    border:0;
    outline: 0;
    background: url("../assets/img/image/btn_start.png") no-repeat center center;
  }
  .peoObjRecognition .titleupload{
   margin-top:20px;
  }

 /* .peoObjRecognition  .upload-file div{
    text-align: right;
    width:60%;
  }*/
  .peoObjRecognition .analysisPhoto h3 button{
   min-width: 100px;
  }
  .peoObjRecognition .analysisPhoto h3{
    margin-bottom:10px;
    padding: 5px 0;
    text-align: right;

  }
  .peoObjRecognition .titleupload h3{
    margin-bottom:10px;
  }
  .peoObjRecognition .imgupload{
    padding: 0;
    background: transparent;
    box-shadow: 0 0 0 #ccc;
    margin-left:20px;
  }
  .peoObjRecognition .imgupload .liftBg{
    width:100%;
    padding: 0;
    top:0;
    position:absolute;
    height: 100%;
  }
  .peoObjRecognition .imgupload .imguploadContent{
    height:70%;
    position: relative;
  }
  .peoObjRecognition .imgupload img{
    width:100%;
    height:100%;
  }
  .peoObjRecognition .analysisText{
    overflow: auto;
  }
  .peoObjRecognition .analysisText .time{
    font-size: 12px;
    text-align: right;
  }
  .peoObjRecognition .table{
    height: 30%;
    margin-left: -4px;
    width: 100%;
    position: relative;
    margin-top:-3px;
  }
  .peoObjRecognition .charts.chartLine{
    margin-top:10px;
  }
  .peoObjRecognition .table img {
    position: absolute;
    top:-4px;
    left:5px;
    width:100%;
    height:100%;
  }
  .peoObjRecognition .table table{
    width:95%;

  }
  .peoObjRecognition .chartLine{
    position: absolute;
    top:0;
    left:0;
  }
  .peoObjRecognition .table table td span{
    color:#5e7187;
  }
  .peoObjRecognition .dateList:after{
    content: '';
    display: block;
    clear: both;
  }
  .peoObjRecognition .dateList{
    position: relative;
  }
  .peoObjRecognition .dateList li:first-child{
    border-bottom:0;
    height:32px;
    line-height: 32px;
  }
  .peoObjRecognition .showMore{
    position: absolute;
    left:7px;
    background: url("../assets/img/image/bg.png");
  }
  .peoObjRecognition .showMore li{
    float: left;
    width:20%;
    cursor: pointer;
    text-align: center;
    color:#fff;
    height:28px;
    line-height: 28px;
  }
  .peoObjRecognition .showMore li.active,
  .peoObjRecognition .dateList li.active{
    color:#ff0;
  }
  .peoObjRecognition .dateList li{
    cursor: pointer;
    float: left;
    height:30px;
    line-height: 30px;
    color:#fff;
    width:18%;
    text-indent:0.8em;
    border-bottom:2px solid #172c59 ;
    background: url("../assets/img/image/right1_com3.png") no-repeat right;
  }
  .peoObjRecognition .dateList li:last-child{
    background: none;
    width:10%;
    text-indent: 0;
    text-align: center;
  }
  .peoObjRecognition .dateList li:last-child .el-date-editor.el-input{
    width:35px;
  }
  .peoObjRecognition .el-input__icon+.el-input__inner{
    padding: 0;
      opacity: 0;
    cursor: pointer;
  }
  .peoObjRecognition .el-input__icon.el-icon-close{
    opacity: 0;
  }
  .peoObjRecognition .el-input__icon.el-icon-close:before{
   content: '更多';
  }
  .peoObjRecognition .el-icon-date:before{
    content: '更多';
  }
  .peoObjRecognition .el-input__inner{
    border:0;
    background: transparent;
    height:auto;
  }
  .peoObjRecognition .table table{
    margin:0 auto;
  }
  .peoObjRecognition .echart{
     position: relative;
     height:40%;
  }
  .peoObjRecognition .echart,
  .peoObjRecognition .planeContent{
    position: relative;

  }
  .peoObjRecognition .echart h3,
  .peoObjRecognition .planeContent h3{

    color:#fff;
    font-size:2vh;
    font-weight: normal;
  }
  .peoObjRecognition .echart img{
    position: absolute;
    height:100%;
    width: 100%;
    top:0;
    left:0;
  }
  .peoObjRecognition .planeContent{
    position: relative;
    height:30%;
  }
  .peoObjRecognition .planeContent .planeBg{
    position: absolute;
    height:100%;
    width: 100%;
    top:0;
    left:0;
  }
  .peoObjRecognition .planeContent .planeImg{
    position: absolute;
    top:40px;
    left:0;
    width:90%;
    margin-left:5%;
    height:70%
  }
  .peoObjRecognition .table table td{
    border:2px solid #172c59;
    text-align: center;
    color:#fff;
  }
</style>
