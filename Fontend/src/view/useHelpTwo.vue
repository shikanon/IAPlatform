<template>
   <div class="useHelpTwo">
     <div v-for="(val,i) in useHelpTwoImg">
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
     name: 'useHelpTwo',
     data () {
       return {
         useHelpTwoImg:[
           {src:require('../assets/img/useHelpTwo/img1.jpg')},
           {src:require('../assets/img/useHelpTwo/img2.jpg')},
           {src:require('../assets/img/useHelpTwo/img3.jpg')},
           {src:require('../assets/img/useHelpTwo/img4.jpg')},
           {src:require('../assets/img/useHelpTwo/img5.jpg')},
           {src:require('../assets/img/useHelpTwo/img6.jpg')},
           {src:require('../assets/img/useHelpTwo/img7.jpg')},
           {src:require('../assets/img/useHelpTwo/img8.jpg')},
           {src:require('../assets/img/useHelpTwo/img9.jpg')},
           {src:require('../assets/img/useHelpTwo/img10.jpg')},
           {src:require('../assets/img/useHelpTwo/img11.jpg')},
           {src:require('../assets/img/useHelpTwo/img12.jpg')},
           {src:require('../assets/img/useHelpTwo/img13.jpg')},
           {src:require('../assets/img/useHelpTwo/img14.jpg')},
           {src:require('../assets/img/useHelpTwo/img15.jpg')},
           {src:require('../assets/img/useHelpTwo/img16.jpg')},
           {src:require('../assets/img/useHelpTwo/img17.jpg')},
           {src:require('../assets/img/useHelpTwo/img18.jpg')},
           {src:require('../assets/img/useHelpTwo/img19.jpg')},
           {src:require('../assets/img/useHelpTwo/img20.jpg')},
           {src:require('../assets/img/useHelpTwo/img21.jpg')},
           {src:require('../assets/img/useHelpTwo/img22.jpg')},
           {src:require('../assets/img/useHelpTwo/img23.jpg')},
           {src:require('../assets/img/useHelpTwo/img24.jpg')},
           {src:require('../assets/img/useHelpTwo/img25.jpg')},
           {src:require('../assets/img/useHelpTwo/img26.jpg')},
           {src:require('../assets/img/useHelpTwo/img27.jpg')},
           {src:require('../assets/img/useHelpTwo/img28.jpg')},
           {src:require('../assets/img/useHelpTwo/img29.jpg')},
           {src:require('../assets/img/useHelpTwo/img30.jpg')},
           {src:require('../assets/img/useHelpTwo/img31.jpg')},
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
  .useHelpTwo{
    margin:20px auto;
    width:800px;
  }
  .useHelpTwo img{
    width:800px;
    margin:10px 0;
  }
</style>

