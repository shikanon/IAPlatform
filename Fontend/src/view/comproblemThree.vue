<template>
   <div class="comproblemThree">
     <div v-for="(val,i) in comproblemThreeImg">
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
     name: 'comproblemThree',
     data () {
       return {
         comproblemThreeImg:[
           {src:require('../assets/img/comproblemThree/img1.jpg')},
           {src:require('../assets/img/comproblemThree/img2.jpg')},
           {src:require('../assets/img/comproblemThree/img3.jpg')},
           {src:require('../assets/img/comproblemThree/img4.jpg')},
           {src:require('../assets/img/comproblemThree/img5.jpg')},
           {src:require('../assets/img/comproblemThree/img6.jpg')},
           {src:require('../assets/img/comproblemThree/img7.jpg')},
           {src:require('../assets/img/comproblemThree/img8.jpg')},
           {src:require('../assets/img/comproblemThree/img9.jpg')},
           {src:require('../assets/img/comproblemThree/img10.jpg')},
           {src:require('../assets/img/comproblemThree/img11.jpg')},
           {src:require('../assets/img/comproblemThree/img12.jpg')},
           {src:require('../assets/img/comproblemThree/img13.jpg')},
           {src:require('../assets/img/comproblemThree/img14.jpg')},
           {src:require('../assets/img/comproblemThree/img15.jpg')},
           {src:require('../assets/img/comproblemThree/img16.jpg')},
           {src:require('../assets/img/comproblemThree/img17.jpg')},
           {src:require('../assets/img/comproblemThree/img18.jpg')},
           {src:require('../assets/img/comproblemThree/img19.jpg')},
           {src:require('../assets/img/comproblemThree/img20.jpg')},
           {src:require('../assets/img/comproblemThree/img21.jpg')},
           {src:require('../assets/img/comproblemThree/img22.jpg')},
           {src:require('../assets/img/comproblemThree/img23.jpg')},
           {src:require('../assets/img/comproblemThree/img24.jpg')},
           {src:require('../assets/img/comproblemThree/img25.jpg')},
           {src:require('../assets/img/comproblemThree/img26.jpg')},
           {src:require('../assets/img/comproblemThree/img27.jpg')},
           {src:require('../assets/img/comproblemThree/img28.jpg')},
           {src:require('../assets/img/comproblemThree/img29.jpg')},
           {src:require('../assets/img/comproblemThree/img30.jpg')}

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
  .comproblemThree{
    margin:20px auto;
    width:800px;
  }
  .comproblemThree img{
    width:800px;
    margin:10px 0;
  }
</style>

