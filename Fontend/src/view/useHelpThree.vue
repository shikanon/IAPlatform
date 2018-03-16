<template>
   <div class="useHelpThree">
     <div v-for="(val,i) in useHelpThreeImg">
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
     name: 'useHelpThree',
     data () {
       return {
         useHelpThreeImg:[
           {src:require('../assets/img/useHelpThree/img1.jpg')},
           {src:require('../assets/img/useHelpThree/img2.jpg')},
           {src:require('../assets/img/useHelpThree/img3.jpg')},
           {src:require('../assets/img/useHelpThree/img4.jpg')},
           {src:require('../assets/img/useHelpThree/img5.jpg')},
           {src:require('../assets/img/useHelpThree/img6.jpg')},
           {src:require('../assets/img/useHelpThree/img7.jpg')},
           {src:require('../assets/img/useHelpThree/img8.jpg')},
           {src:require('../assets/img/useHelpThree/img9.jpg')},
           {src:require('../assets/img/useHelpThree/img10.jpg')},
           {src:require('../assets/img/useHelpThree/img11.jpg')},
           {src:require('../assets/img/useHelpThree/img12.jpg')},
           {src:require('../assets/img/useHelpThree/img13.jpg')},
           {src:require('../assets/img/useHelpThree/img14.jpg')},
           {src:require('../assets/img/useHelpThree/img15.jpg')},
           {src:require('../assets/img/useHelpThree/img16.jpg')},
           {src:require('../assets/img/useHelpThree/img17.jpg')},
           {src:require('../assets/img/useHelpThree/img18.jpg')},
           {src:require('../assets/img/useHelpThree/img19.jpg')},
           {src:require('../assets/img/useHelpThree/img20.jpg')},
           {src:require('../assets/img/useHelpThree/img21.jpg')},
           {src:require('../assets/img/useHelpThree/img22.jpg')},
           {src:require('../assets/img/useHelpThree/img23.jpg')},
           {src:require('../assets/img/useHelpThree/img24.jpg')},
           {src:require('../assets/img/useHelpThree/img25.jpg')},
           {src:require('../assets/img/useHelpThree/img26.jpg')},
           {src:require('../assets/img/useHelpThree/img27.jpg')},
           {src:require('../assets/img/useHelpThree/img28.jpg')},
           {src:require('../assets/img/useHelpThree/img29.jpg')},
           {src:require('../assets/img/useHelpThree/img30.jpg')}

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
  .useHelpThree{
    margin:20px auto;
    width:800px;
  }
  .useHelpThree img{
    width:800px;
    margin:10px 0;
  }
</style>

