<template>
  <div class="login">
    <img :src="bg_Img" alt="" class="bgImg">
    <h1 class="bigTitle">智能算法基础能力开发平台</h1>
    <div class="loginForm">
      <div>
        <input type="text" name="username" class="username" v-model="ruleForm.username" placeholder="请输入账号">
      </div>
      <div>
        <input type="password" name="password" class="password" v-model="ruleForm.password" placeholder="请输入密码">
      </div>

      <div>
        <input type="button" @click="submitForm(ruleForm)" value="登录">
      </div>
    </div>

  </div>
</template>

<script>
  import $ from 'jquery'
  import {HOST} from '../service/HOST'
  import post from '../service/post'
  import get from '../service/get'
  import { Message } from 'element-ui';
  import * as types from '../store/types'
    export default {
        name: 'login',
      data() {
        return {
          ruleForm: {
            username:'',
            password:'',
          },
          host:'',
          rules: {

          },
          bg_Img:require("../assets/img/bg.png"),
        };
      },
      mounted:function(){
        let _that=this;
        _that.autoScreen();
        $(window).resize(function (e) {
          _that.autoScreen()
        })
      },
      beforeMount:function () {

      },
      methods: {

        autoScreen:function () {
          let width=$(window).width();
          let height=$(window).height();
          $('.login .bigTitle').css({
            top:height-height*0.92+'px'
          });
          $('.login .loginForm').css({
            top:height/2.3+'px'
          });
          let topBottom=height-height*0.99+'px';
          $(".login .loginForm input").css({
            paddingTop:topBottom,
            paddingBottom:topBottom,
            marginTop:topBottom,
            marginBottom:topBottom,
            marginLeft:-(width-width*0.985)+'px',
            width:width-width*0.8+'px',
            borderRadius:height-height*0.99+10+'px'
          });
          $(".login .loginForm input[type='button']").css({
            width:width-width*0.8+4+'px',
            paddingTop:(height-height*0.99-2)+'px',
            paddingBottom:(height-height*0.99-2)+'px',
          });
        },
        submitForm(formName) {
         let _that=this;
          let userName= new FormData();
          for(let key in formName){
            userName.append(key,formName[key]);
          }
       /*  username:'',
            password:'',
         _that.$store.commit(types.LOGIN,'f05716aceba711e785360242ac110009');
          _that.$store.commit(types.TITLE, formName.username);
          _that.$router.push({
            path:'/topMenu/itemShow'
          });
        */
       if(formName.username!=''&&formName.password!=''){
         /*$.ajax({
           url:HOST+"/login",
           type:"POST",
           cache:false,
           dataType:"jsonp",
           data:formName,
           jsonp:"callback", //这里定义了callback的参数名称，以便服务获取callback的函数名即getMessage
           jsonpCallback:"getMessage",
           success:function(data){
             alert("success:"+data);
           },
           error:function(){
             alert("发生异常");
           }
         });
         function getMessage(jsonp){
           alert("message:"+jsonp);
         }*/

           if(HOST=='http://183.3.139.134:8686'){
              post("/login",userName).then(function(data) {
               if(data.token!=undefined){
                 _that.$store.commit(types.LOGIN, data.token);
                 _that.$store.commit(types.TITLE, formName.username);
                 _that.$router.push({path:'/topMenu/itemShow'})
               }else{
                 Message({message:data, type: 'error'})
               }
             }, function(error) {
               Message({message: '网络请求失败', type: 'error'})
             });
           }else{
             _that.$store.commit(types.LOGIN,'f05716aceba711e785360242ac110009');
             _that.$store.commit(types.TITLE, formName.username);
             _that.$router.push({path:'/topMenu/itemShow'});
           }

          }else {
            Message({message: '用户名或密码不能为空', type: 'warning'})
          }

        },
        resetForm(formName) {
          console.log(window.localStorage)
        }
      }
    }
</script>

<style scoped>
.login{
  background:#f3f5f7;
  position: relative;
}
.login .bgImg{
  position:fixed;
  top:0;
  left:0;
  width:100%;
  height:100%;
}
.login .loginForm input{
  padding-left:10px;
  border: 2px solid #128fe9;
  background: transparent;
  outline: none;
  color:#fff;
}
.login .loginForm input[type='button']{
  border: 2px solid transparent;
  background: rgba(18, 143, 233, 0.61);
  font-size: 16px;
}

.login .loginForm input::-webkit-input-placeholder{
  color: #888;
  font-size: 16px;
  letter-spacing:5px;
  text-align: center;
  font-family:'宋体';

}
.login .loginForm input::-moz-placeholder{
 color: #888;
  letter-spacing:5px;
  text-align: center;
  font-family:'宋体';
}
.login .loginForm input:-ms-input-placeholder{
  color: #888;
  letter-spacing:5px;
  text-align: center;
  font-family:'宋体';
}
.login .loginForm{
  position:fixed;
  left:0;
  width:100%;
  text-align: center;
  color:#1adfed;
}
.login .bigTitle{
  position:fixed;
  left:0;
  width:100%;
  margin-left:15px;
  text-align: center;
  font-size:4.5vh;
  color:#1adfed;
}
.demo-ruleForm{


}
</style>
