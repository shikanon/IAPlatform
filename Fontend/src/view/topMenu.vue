<template>
  <div class="topMenu" >
    <div class="topMenuTop"   v-show="topMenuToggle"  id="topMenuToggle" >
      <nav class="nav">
        <div class="navBar">
          <h1><img :src="logoImg" alt="" @click="titleClick"></h1>

          <ul>
            <li v-for="(val ,index) in itemlist"  @click="itmeClick(val,index)">
              <a href="javascript:;" :class="[index===currentindex ? 'active' : '']">{{val.title}}</a>
            </li>
          </ul>
          <div class="register">
            <ul>
              <li  @click="getToken"><i class="icon-user"></i> <span v-text="userName" ></span></li>
              <li @click="logoutClick">退出</li>
            </ul>
          </div>
        </div>
      </nav>
    </div>

    <div>
      <div style="height:8vh;min-height: 60px"  v-show="topMenuToggle"  id="top_Menu"></div>
      <router-view></router-view>
    </div>
  </div>

</template>

<script>
   import $ from 'jquery'
   import post from '../service/post'
   import get from '../service/get'
   import { Message } from 'element-ui';
   import main from '../js/main'
   import * as types from '../store/types'
   export default {
     name: 'topMenu',
     data () {
       return {
         msg: 'Welcome to Your Vue.js App',
         logoImg:require("../assets/img/logo.png"),
         currentindex:0,
         topMenuToggle:true,
         userName:'未登录',
         itemlist:[
           {title:'项目展示'},
           {title:'智能实验室'},
           {title:'数据采集'}
         ]
       }
     },
     beforeMount:function () {
       this.$topMenuToggle();
       this.itmeCurrent();
       if(this.$store.state.title!=null||this.$store.state.title!=''||this.$store.state.title!=undefined){
         this.userName=this.$store.state.title
       }
     },
     mounted:function(){
       this.$topMenuToggle()
     },
     watch: {
       "$route":function () {
         this.$topMenuToggle();
         this.itmeCurrent();
         if(this.$store.state.title!=null||this.$store.state.title!=''||this.$store.state.title!=undefined){
           this.userName=this.$store.state.title
         }
       }

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
       itmeCurrent:function () {
         let _that=this;
         let path=_that.$route.path;
         if(path=="/topMenu/itemShow"){
           _that.currentindex=0
         }else if(path=="/topMenu/intellLaboratory"){
           _that.currentindex=1
         }
       },
       titleClick:function () {
         this.$router.push("/topMenu/itemShow");
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

       }
     }
   }
</script>

<style>

</style>
