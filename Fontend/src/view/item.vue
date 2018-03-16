
<template>
  <div class="item">
    <nav class="nav">
      <div class="navBar">
       <h1><img :src="logoImg" alt="" @click="titleClick"></h1>
        <div class="itemTabContent">
          <ul style="float: left">
            <li v-for="(val ,index) in itemlist"  @click="itmeClick(val,index)">
              <a href="javascript:;" :class="[index===currentindex ? 'active' : '']">{{val.title}}</a>
            </li>
          </ul>
          <ul style="float: right">
            <li  @click="getToken"><i class="icon-user"></i> <span v-text="userName" ></span></li>
            <li @click="logoutClick">退出</li>
          </ul>
        </div>
        <div class="register">
          <ul>
            <li>
              <div @click="back"></div>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div style="height:60px;min-height: 60px"></div>
    <div class="mainItem">
      <transition  name="fade"  mode="out-in" appear>
      <div class="mainLeft"  v-show="leftToggle" >
        <div class="leftBarContent">
          <img src="../assets/img/image/left_bg.png" alt="" >
          <leftBar @itemlistClick="changerouter" ></leftBar>
        </div>
        <div class="rightBar" @click="rightBarClick"></div>
      </div>
      </transition>
      <div class="mainContent">
        <button @click='openLeftMenu' class="leftBarBtn" :class="leftToggle?'active':''"><img :src="leftBarListBtn" alt=""></button>
        <div class="grid-content bg-purple-light">
          <router-view></router-view>
        </div>
      </div>
    </div>
    <!--<el-row>
      <el-col :span="4">
         <leftBar @itemlistClick="changerouter"></leftBar>
      </el-col>
      <el-col :span="20">
        <div class="grid-content bg-purple-light">
          <router-view></router-view>
        </div>
      </el-col>
    </el-row>
    -->
  </div>
</template>

<script>
  import $ from 'jquery'
  import post from '../service/post'
  import get from '../service/get'
  import { Message } from 'element-ui';
  import * as types from '../store/types'
 import leftBar from "../components/leftBar.vue"
   export default {
     name: 'item',
     data () {
       return {
         handleIndex:1,
         activeName:['1'],
         leftToggle:false,
         userName:'未登录',
         leftBarListBtn:require("../assets/img/image/menu.png"),
         msg: 'Welcome to Your Vue.js App',
         logoImg:require("../assets/img/image/logo.png"),
         currentindex:0,
         itemlist:[
           {title:'项目展示'},
           {title:'智能实验室'},
           {title:'数据采集'}
         ]
       }
     },

     mounted:function () {

     },
     beforeMount:function () {
       if(this.$store.state.title!=null||this.$store.state.title!=''||this.$store.state.title!=undefined){
         this.userName=this.$store.state.title
       }
     },
     components: {
       leftBar
     },
     watch: {

       "$route":function () {
         let _that=this;
         let path=_that.$route.path;
         if(path=="/topMenu/itemShow"){
           _that.currentindex=0
         }else if(path=="/topMenu/intellLaboratory"){
           _that.currentindex=1
         }
         if(this.$store.state.title!=null||this.$store.state.title!=''||this.$store.state.title!=undefined){
           this.userName=this.$store.state.title
         }
       }
     },
     computed: {

     },
     methods:{
       getToken:function () {
         console.log(window.localStorage)
       },
       logoutClick:function () {
         let _that=this;
         let tokenForm= new FormData();
         tokenForm.append('token',this.$store.state.token);
         post("/logout",tokenForm).then(function(data) {
           _that.$store.commit(types.LOGOUT);
           _that.$router.push('/login');
           Message({message: '登出成功', type: 'success'})
         }, function(error) {
           Message({message: '系统异常,请重试', type: 'error'})
         });

       },
       back:function () {
         if(this.$route.path=='/topMenu/item/faceRecognition'){
           this.$router.push("/topMenu/item/waitForRecognition")
         }else{
           this.$router.push("/topMenu/itemShow")
         }


       },
       rightBarClick:function () {
         this.leftToggle=false
       },
       titleClick:function () {
         this.$router.push("/topMenu/itemShow")
       },
       openLeftMenu:function () {
         this.leftToggle=!this.leftToggle
       },
       itmeClick:function(val,index){
         let _that=this;
         _that.currentindex=index;
         switch (index) {
           case 0:
             _that.$router.push("/topMenu/itemShow");
             break;
           case 1:
             _that.$router.push("/topMenu/intellLaboratory");
             break;
           case 2:
             location.href='http://183.3.139.134:9000/app/#/usertasks';
             break;
           default:
             break;
         }

       },
       itemListShow:function (val,index) {
         this.itemShow=!this.itemShow
       },
       changerouter:function (key) {
          console.log(key)
           var _that=this;
         switch (key) {
           case '1-1':
             _that.$router.push("/topMenu/item/photoLabel");
             break;
           case '1-2':
             _that.$router.push("/topMenu/item/photoStyle");
             break;
           case '1-3':
             _that.$router.push("/topMenu/item/waitForRecognition");
             break;
           case '1-4':
             _that.$router.push("/topMenu/item/imgObjExtraction");
             break;
           case '1-5':
             _that.$router.push("/topMenu/item/peoObjRecognition");
             break;
           case '2-1':
             _that.$router.push("/topMenu/item/autoEngComRead");
             break;
           case '2-2':
             _that.$router.push("/topMenu/item/autoAbstracting");
             break;
           default:
             break;
         }

       }
     },

   }
</script>

<style>
 .fade-enter-active, .fade-leave-active{
       transition: all 1s ease
   }

.fade-enter, .fade-leave-active{  opacity: 0}

  .leftNav{
    position:fixed !important;
  }
  .item .nav{
    height:60px !important;
    background: transparent;
    border-bottom: 0;
  }
 .item .leftBarContent{
   height:100%;
   width:240px;
   padding:5px;
   float: left;
   position: relative;
   box-sizing: border-box;
   margin-top:35px;
   background: #172c59;
 }
 .rightBar{
   margin-left: 240px;
   height: 100%;
 }
 .item .leftBarContent>img{
   position: absolute;
   top:0;
   left:0;
   width:100%;
   height:85%;
 }
  .item .navBar h1{
    position: absolute;
    top:0;
    left:0;
    margin-left:0;
    padding-top:5px;
  }
  .item .leftBarBtn {
    position: fixed;
    left: 10px;
    background: transparent;
    border: 0;
    width:34px;
    top:70px;
    height:24px;
    outline: none;
    z-index: 1000;
  }
 .item .leftBarBtn.active{
   background: url("../assets/img/image/head_Selected2.png") #172c59 no-repeat center center;
   background-size: contain;

 }

  .item .navBar ul{
    margin-top: 10px;
    margin-left:0;
    line-height: 40px;
  }
  .item .navBar ul li a{
    line-height: 40px;
    height:40px;
  }
  .itemTabContent li{
    color:#509ff0
  }
  .itemTabContent{
    margin-left:270px;
    margin-right: 145px;
    height: 63px;
    line-height: 63px;
    background-size:contain;
    background: url("../assets/img/image/head_com1.png") no-repeat center bottom;
  }
  /*.el-menu-item, .el-submenu__title{
    height:40px;
    line-height: 40px;
  }*/
  .item .el-submenu .el-menu-item{
    height:30px;
    line-height: 30px;
  }
  .el-col.el-col-20{
    background: #f3f5f7;
  }
  .el-collapse-item__header__arrow{


   }
  .mainLeft{
    position: fixed;
    width:100%;
    height: 100%;
    z-index: 100;

   background-color:rgba(0,0,0,0.8);
  }
  /*
 .mainLeft::after{
   content:'';
   display: block;
   position: absolute;
   top: 10px;
   left: 25px;
   width:80px;
   height:30px;
   z-index: -1;
   border-top:2px solid #172c59;
   border-right:2px solid #172c59;
 }
 .mainLeft::before{
   content:'';
   display: block;
   position: absolute;
   width:10px;
   height:10px;
   top:25px;
   z-index: 1;
   left: 101px;
   border-radius: 5px;
   background: #509ff0;
 }

  .mainLeft .leftBar{

    border: 2px dashed #224377;
  }
 .mainLeft .leftBarContent::before{
   content: '';
   width:10px;
   height:10px;
   display: block;
   position: absolute;
   bottom:77px;
   left:225px;
   border-bottom: 5px solid #3466a5;
   border-right: 5px solid #3466a5;
 }
 .mainLeft .leftBarContent::after{
   content: '';
   width:10px;
   height:10px;
   display: block;
   position: absolute;
   bottom:77px;
   left:0;
   border-bottom: 5px solid #3466a5;
   border-left: 5px solid #3466a5;
 }
 .mainLeft .leftBar::before{
   content: '';
   width:10px;
   height:10px;
   display: block;
   position: absolute;
   top:30px;
   left:225px;
   border-top: 5px solid #3466a5;
   border-right: 5px solid #3466a5;
 }
 .mainLeft .leftBar::after{
   content: '';
   width:10px;
   height:10px;
   display: block;
   position: absolute;
   top:30px;
   left:0;
   border-top: 5px solid #3466a5;
   border-left: 5px solid #3466a5;
 }*/

  .mainContent .grid-content.bg-purple-light,.mainContent .photoLabel,.mainContent .loader{
    height: 100%;
  }

  .item .navBar ul li a{
    border:0;
    color:#5a6d82;
    font-size: 16px;
    font-weight: normal;
  }
  .item .navBar ul li a.active{
    background:url("../assets/img/image/head_Selected.png") no-repeat center center;
    background-size:90px 34px;
    color:#ffff00;
  }
  .item .icon-user{
    width:20px;
    height: 20px;
     background: url("../assets/img/image/user.png") no-repeat center center;
  }
  .item .register{
    margin:0;
    float: none;
    position: absolute;
    right:0;
    top:0;
  }
  .item .register ul{
    margin-top:0;
    height:60px;
    float: none;
    margin-left:0;
  }
  .item .register li:last-child{
      height:60px;
    width: 125px;
  }
  .item .register li:last-child div{
    width: 100%;
    height:100%;
    margin-top:5px;
    background: url("../assets/img/image/btn_back.png") no-repeat center center;
  }
  .item{
    background: url("../assets/img/image/bg.png") #0c1927 no-repeat;
    height:100vh;
  }

  .item .navBar{
    top:0;
    transform:translateY(0);
    overflow: inherit;
  }
  .item .register li{
    color:#509ff0;
    margin-top:0;
    line-height: 60px;
  }
</style>
