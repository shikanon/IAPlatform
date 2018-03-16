<template>
   <div class="useHelpOne">
     <div v-for="(val,i) in useHelpOneImg">
       <img :src="val.src" alt="">
     </div>
   </div>
</template>


<script>

</script>
<script>
  import $ from 'jquery'

  import post from '../service/post'
  import get from '../service/post'
   export default {
     name: 'useHelpOne',
     data () {
       return {
         useHelpOneImg:[
           {src:require('../assets/img/useHelpOne/img1.jpg')},
           {src:require('../assets/img/useHelpOne/img2.jpg')},
           {src:require('../assets/img/useHelpOne/img3.jpg')},
           {src:require('../assets/img/useHelpOne/img4.jpg')},
           {src:require('../assets/img/useHelpOne/img5.jpg')},
           {src:require('../assets/img/useHelpOne/img6.jpg')},
           {src:require('../assets/img/useHelpOne/img7.jpg')},
           {src:require('../assets/img/useHelpOne/img8.jpg')},
           {src:require('../assets/img/useHelpOne/img9.jpg')},
           {src:require('../assets/img/useHelpOne/img10.jpg')},
           {src:require('../assets/img/useHelpOne/img11.jpg')}
         ]
       }
     },
     mounted:function(){

     },
     methods:{


       }


   }
</script>
<style>
  .useHelpOne{
    margin:20px auto;
    width:800px;
  }
  .useHelpOne img{
    width:800px;
    margin:10px 0;
  }
</style>

