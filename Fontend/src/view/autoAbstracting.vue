<template>
  <div class="autoAbstracting">
    <div class="back">
      <div class="title">自动文摘系统</div>
      <a class="btn-back" @click="back"></a>
    </div>
    <div class="mainAbstracting">
      <div class="mainComputed">
        <p>请输入英文文章</p>
        <editor class="editor" style="width:100%;"></editor>
      </div>
      <div class="computedTitle">
        <p>
          <span>请输入文章总分:&nbsp;</span><input type="text">
          <a type="button" class="produce" onclick="">点击评分</a>
        </p>
        <div id="contentBox"></div>
        <div style="height:30px;background:#f3f5f7;margin:0 60px;"></div>
      </div>
    </div>

  </div>
</template>

<script>
  import editor from '../components/editor.vue'
  import $ from 'jquery'
  export default {
     name:'autoAbstracting',
     data () {
       return {

       }
     },

     components:{
       editor
     },
     beforeMount:function () {
     },

     methods:{
       back:function () {
         this.$router.push("/topMenu/itemShow")

       },
     }
   }
</script>

<style>
  .autoAbstracting{
    background:#f3f5f7;
    height:92vh
  }
  .mainAbstracting p,
  .computedTitle p{
    padding: 20px 10px 10px 10px;
  }
  .mainAbstracting .editor{
    padding: 10px;
  }
  .mainAbstracting{
    background: #fff;
    margin:0 10px;
  }
  .w-e-text-container{
    height:71vh !important;
  }
  .mainComputed{
    float: left;
    width: 50%;
  }
  .computedTitle{
    float:right;
    width: 50%;

  }
  #contentBox{
    margin-left: 60px;
    margin-right: 60px;
    margin-top: 10px;

    height:71vh !important;
    background-color: #f3f5f7
  }

  .mainAbstracting{
    overflow: hidden;

  }
</style>
