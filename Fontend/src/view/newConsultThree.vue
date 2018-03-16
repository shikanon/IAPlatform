<template>
  <div class="newConsultThree" >
    <div v-for="(val,i) in  newConsultThreeImg">
      <img :src="val.src" alt="">
    </div>
  </div>
</template>

<script>
  import $ from 'jquery'
  import post from '../service/post'
  import get from '../service/post'
   export default {
     name: 'newConsultThree',
     data () {
       return {
         newConsultThreeImg:[
           {src:require('../assets/img/newConsultThree/img1.jpg')},
           {src:require('../assets/img/newConsultThree/img2.jpg')},
           {src:require('../assets/img/newConsultThree/img3.jpg')},
           {src:require('../assets/img/newConsultThree/img4.jpg')},
           {src:require('../assets/img/newConsultThree/img5.jpg')},
           {src:require('../assets/img/newConsultThree/img6.jpg')},
           {src:require('../assets/img/newConsultThree/img7.jpg')},
           {src:require('../assets/img/newConsultThree/img8.jpg')},
           {src:require('../assets/img/newConsultThree/img9.jpg')},
           {src:require('../assets/img/newConsultThree/img10.jpg')},
           {src:require('../assets/img/newConsultThree/img11.jpg')},
           {src:require('../assets/img/newConsultThree/img12.jpg')},
           {src:require('../assets/img/newConsultThree/img13.jpg')},
           {src:require('../assets/img/newConsultThree/img14.jpg')},
           {src:require('../assets/img/newConsultThree/img15.jpg')},
           {src:require('../assets/img/newConsultThree/img16.jpg')},
           {src:require('../assets/img/newConsultThree/img17.jpg')},
           {src:require('../assets/img/newConsultThree/img18.jpg')},
           {src:require('../assets/img/newConsultThree/img19.jpg')},
           {src:require('../assets/img/newConsultThree/img20.jpg')},
           {src:require('../assets/img/newConsultThree/img21.jpg')},
           {src:require('../assets/img/newConsultThree/img22.jpg')},
           {src:require('../assets/img/newConsultThree/img23.jpg')},
           {src:require('../assets/img/newConsultThree/img24.jpg')},
           {src:require('../assets/img/newConsultThree/img25.jpg')},
           {src:require('../assets/img/newConsultThree/img26.jpg')},
           {src:require('../assets/img/newConsultThree/img27.jpg')},
           {src:require('../assets/img/newConsultThree/img28.jpg')},
           {src:require('../assets/img/newConsultThree/img29.jpg')}
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
  .newConsultThree{
    margin:20px auto;
    width:800px;
  }
  .newConsultThree img{
    width:800px;
    margin:10px 0;
  }
</style>

