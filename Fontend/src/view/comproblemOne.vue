<template>
   <div class="comproblemOne">
     <div v-for="(val,i) in comproblemOneImg">
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
     name: 'comproblemOne',
     data () {
       return {
         comproblemOneImg:[
           {src:require('../assets/img/comproblemOne/img1.jpg')},
           {src:require('../assets/img/comproblemOne/img2.jpg')},
           {src:require('../assets/img/comproblemOne/img3.jpg')},
           {src:require('../assets/img/comproblemOne/img4.jpg')},
           {src:require('../assets/img/comproblemOne/img5.jpg')},
           {src:require('../assets/img/comproblemOne/img6.jpg')},
           {src:require('../assets/img/comproblemOne/img7.jpg')},
           {src:require('../assets/img/comproblemOne/img8.jpg')},
           {src:require('../assets/img/comproblemOne/img9.jpg')},
           {src:require('../assets/img/comproblemOne/img10.jpg')},
           {src:require('../assets/img/comproblemOne/img11.jpg')},
           {src:require('../assets/img/comproblemOne/img12.jpg')},
           {src:require('../assets/img/comproblemOne/img13.jpg')},
           {src:require('../assets/img/comproblemOne/img14.jpg')},
           {src:require('../assets/img/comproblemOne/img15.jpg')},
           {src:require('../assets/img/comproblemOne/img16.jpg')},
           {src:require('../assets/img/comproblemOne/img17.jpg')},
           {src:require('../assets/img/comproblemOne/img18.jpg')},
           {src:require('../assets/img/comproblemOne/img19.jpg')},
           {src:require('../assets/img/comproblemOne/img20.jpg')},
           {src:require('../assets/img/comproblemOne/img21.jpg')},
           {src:require('../assets/img/comproblemOne/img22.jpg')}
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
  .comproblemOne{
    margin:20px auto;
    width:800px;
  }
  .comproblemOne img{
    width:800px;
    margin:10px 0;
  }
</style>

