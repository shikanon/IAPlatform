<template>
  <div class="itemShow">
    <div class="mainContainer">
      <div class="container">
        <div class="jumbotron">
          <ul class="banner">
            <li v-for="(item,index) in mockTitle">
              <div class="banner_title">
                <h3><img :src="item.src" alt=""><span>{{item.title}}</span></h3>
                <div class="new">
                  <ol>
                    <li v-for="(val,i) in item.content" @click="itemContentClick(val,i)">
                      {{val.title}}
                    </li>
                  </ol>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="creatNewItem">
          <h3>项目</h3>
          <!--<div>
            <span class="addItem">创建新项目</span>
          </div>-->
        </div>
        <ul class="navList">
          <li v-for="(obj,index) in mockData"
              @mouseover="show(obj,index)"
              @mouseout="hide(obj,index)"
              v-show="obj.display!='none'">
            <div class="card">
              <h3>{{obj.title}}</h3>
              <div class="title">{{obj.titleName}}</div>
              <img :src="obj.src"  alt=" ">
               <div class="number"><i class="icon-look"></i><a href="javascript:;">{{index>=3?(index):(index+1)}}</a> </div>
                <div class="maskCard" :class="[childCardShow===index?'open':'close']">
                  <div class="childCard" >
                  <span>
                    <button class="btn1" @click="itemClick(index)">模型</button>
                  </span>
                    <span>
                    <button class="btn2" @click="explainClick(index)">文档</button>
                  </span>
                  </div>
                </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>

   export default {
     name:'itemShow',
     data () {
       return {
         mockTitle:[
           {title:'最新咨询',src:require("../assets/img/new_function.png"),
             content:[
             {title:'基于云平台的分布式网络爬虫的研究与设计'},
             {title:'智慧城市与城市计算'},
             {title:'电信大数据指数研究探讨'}
           ]},
           {title:'使用帮助',src:require("../assets/img/help.png"),
             content:[
               {title:'英语作文自动批阅系统'},
               {title:'机器学习算法讲义之体系结构介绍'},
               {title:'机器学习算法讲义之机器视觉'},
             ]},
           {title:'常见问题',src:require("../assets/img/query.png"),
             content:[
               {title:'检察院卷宗智能化处理技术交流会'},
               {title:'数据整合及变现思考'},
               {title:'大数据平台整合笔记'}
             ]},
         ],
         mockData:[
           {title:'图片标签化',titleName:' ',src:require("../assets/img/photoLable.png")},
           {title:'图片风格迁徙',titleName:' ',src:require("../assets/img/photoStyle.png")},
           {title:'人脸识别',titleName:' ',src:require("../assets/img/pic.png"),display:'none'},
           {title:'图像物体抽取',titleName:' ',src:require("../assets/img/imgExtract.png")},
           {title:'智能商铺人流物流识别系统',titleName:' ',src:require("../assets/img/imgStore.png")},
           {title:'物体检测',titleName:' ',src:require("../assets/img/pic.png"),display:'none'},
           {title:'语言切割',titleName:' ',src:require("../assets/img/pic.png"),display:'none'},
           {title:'自动英文作文阅读',titleName:' ',src:require("../assets/img/pic.png"),display:'none'},
           {title:'自动文摘系统',titleName:' ',src:require("../assets/img/pic.png"),display:'none'},
           {title:'智能问答系统',titleName:' ',src:require("../assets/img/pic.png"),display:'none'}
         ],
         childCardShow:-1,
       }
     },

     mounted:function(){

     },
     methods:{
       show:function(obj,index){
         let _that=this;
         _that.childCardShow=index;
       },
       hide:function(obj,index){
         let _that=this;
         _that.childCardShow='close'
       },
       explainClick:function (index) {
         let _that=this;
         switch (index) {
           case 0:
             _that.$router.push("/topMenu/explain/photoLabelExplain");
             break;
           case 1:
           /*  _that.$router.push("/topMenu/explain/photoStyleExplain");*/
             break;
           default:
             break;
         }
       },
       itemContentClick:function (val,i) {
         let _that=this;
         switch (val.title) {
           case '基于云平台的分布式网络爬虫的研究与设计':
             _that.$router.push("/topMenu/explain/newConsultOne");
             break;
           case '智慧城市与城市计算':
             _that.$router.push("/topMenu/explain/newConsultTwo");
             break;
           case '电信大数据指数研究探讨':
             _that.$router.push("/topMenu/explain/newConsultThree");
             break;
           case '英语作文自动批阅系统':
             _that.$router.push("/topMenu/explain/useHelpOne");
             break;
           case '机器学习算法讲义之体系结构介绍':
             _that.$router.push("/topMenu/explain/useHelpTwo");
             break;
           case '机器学习算法讲义之机器视觉':
             _that.$router.push("/topMenu/explain/useHelpThree");
             break;
           case '检察院卷宗智能化处理技术交流会':
             _that.$router.push("/topMenu/explain/comproblemOne");
             break;
           case '数据整合及变现思考':
           _that.$router.push("/topMenu/explain/comproblemTwo");
           break;
           case '大数据平台整合笔记':
             _that.$router.push("/topMenu/explain/comproblemFour");
             break;
           default:
             break;
         }
       },
       itemClick:function (index) {
         let _that=this;
         switch (index) {
           case 0:
             _that.$router.push("/topMenu/item/photoLabel");
             break;
           case 1:
             _that.$router.push("/topMenu/item/photoStyle");
             break;
           case 2:
             _that.$router.push("/topMenu/item/waitForRecognition");
             break;
           case 3:
             _that.$router.push("/topMenu/item/imgObjExtraction");
             break;
           case 4:
             _that.$router.push("/topMenu/item/peoObjRecognition");
             break;
           default:
             break;
         }
       }
     }
   }
</script>

<style scoped>
.close{
  transition:all 2s;
  -webkit-transition:all 2s;
  -moz-transition:all 2s;
  display: none;
  opacity: 0;

}
.open{
  transition:all 2s;
  -webkit-transition:all 2s;
  -moz-transition:all 2s;
  display:block;
  opacity: 1;

}
.title{
  min-height: 30px;
}
</style>
