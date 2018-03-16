<template>
  <div class="autoEngComRead" style="overflow: scroll">
    <div class="myContainer">
      <div class="myBtn clearfix">
        <a type="button" class="return">返回</a>
      </div>
      <div class="myContent clearfix">
        <div class="col-lg-6 col-md-6 col-sm-6">
          <p>请输入文章</p>
          <div>
            <editor style="width:800px;height:500px;"></editor>
          </div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6">
          <a type="button" class="produce" onclick="getPlainTxt()">点击生成文摘</a>
          <div id="contentBox" style="width:100%;background-color: #f4f5f7;overflow-y: scroll; ">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import $ from 'jquery'
  import editor from '../components/editor.vue'
   export default {
     name:'autoEngComRead',
     data () {
       return {

       }
     },
     beforeMount:function () {
     },
     mounted:function(){
         let _that=this;
     },
     components:{
       editor
     },
     methods:{

     }
   }
</script>

<style>

</style>
