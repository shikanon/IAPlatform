<template>
   <div class="comproblemTwo">
     <div v-for="(val,i) in comproblemTwoImg">
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
     name: 'comproblemTwoTwo',
     data () {
       return {
         comproblemTwoImg:[
           {src:require('../assets/img/comproblemTwo/img1.jpg')},
           {src:require('../assets/img/comproblemTwo/img2.jpg')},
           {src:require('../assets/img/comproblemTwo/img3.jpg')},
           {src:require('../assets/img/comproblemTwo/img4.jpg')},
           {src:require('../assets/img/comproblemTwo/img5.jpg')},
           {src:require('../assets/img/comproblemTwo/img6.jpg')},
           {src:require('../assets/img/comproblemTwo/img7.jpg')},
           {src:require('../assets/img/comproblemTwo/img8.jpg')},
           {src:require('../assets/img/comproblemTwo/img9.jpg')},

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
  .comproblemTwo{
    margin:20px auto;
    width:800px;
  }
  .comproblemTwo img{
    width:800px;
    margin:10px 0;
  }
</style>

