<template>
  <div class="creatJob">
    <div class="creatJobFrom">

    </div>
  </div>
</template>

<script>
    export default {
        name: 'creatJob',
        data(){
            return {}
        },
        beforeMount: function () {

        },
        methods: {}
    }
</script>

<style scoped>
 .creatJob{
   position:fixed;
   height:100%;
   width: 100%;
   top:0;
   left:0;

 }
 .creatJob .creatJobFrom{
   background: #fff;
   position:relative;
   margin: 0 auto;
   top: 50%;
   transform: translateY(-50%);
   height:500px;
   width:1000px;
   border:2px solid #509ff0;

 }
</style>
