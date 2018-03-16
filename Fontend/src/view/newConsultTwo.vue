<template>
  <div class="newConsultTwo" >
    <div v-for="(val,i) in  newConsultTwoImg">
      <img :src="val.src" alt="">
    </div>
  </div>
</template>

<script>
  import $ from 'jquery'
  import post from '../service/post'
  import get from '../service/post'
   export default {
     name: 'newConsultTwo',
     data () {
       return {
         newConsultTwoImg:[
           {src:require('../assets/img/newConsultTwo/img1.jpg')},
           {src:require('../assets/img/newConsultTwo/img2.jpg')},
           {src:require('../assets/img/newConsultTwo/img3.jpg')},
           {src:require('../assets/img/newConsultTwo/img4.jpg')},
           {src:require('../assets/img/newConsultTwo/img5.jpg')},
           {src:require('../assets/img/newConsultTwo/img6.jpg')},
           {src:require('../assets/img/newConsultTwo/img7.jpg')},
           {src:require('../assets/img/newConsultTwo/img8.jpg')},
           {src:require('../assets/img/newConsultTwo/img9.jpg')},
           {src:require('../assets/img/newConsultTwo/img10.jpg')},
           {src:require('../assets/img/newConsultTwo/img11.jpg')},
           {src:require('../assets/img/newConsultTwo/img12.jpg')},
           {src:require('../assets/img/newConsultTwo/img13.jpg')},
           {src:require('../assets/img/newConsultTwo/img14.jpg')},
           {src:require('../assets/img/newConsultTwo/img15.jpg')},
           {src:require('../assets/img/newConsultTwo/img16.jpg')},
           {src:require('../assets/img/newConsultTwo/img17.jpg')},
           {src:require('../assets/img/newConsultTwo/img18.jpg')},
           {src:require('../assets/img/newConsultTwo/img19.jpg')},
           {src:require('../assets/img/newConsultTwo/img20.jpg')},
           {src:require('../assets/img/newConsultTwo/img21.jpg')},
           {src:require('../assets/img/newConsultTwo/img22.jpg')},
           {src:require('../assets/img/newConsultTwo/img23.jpg')},
           {src:require('../assets/img/newConsultTwo/img24.jpg')},
           {src:require('../assets/img/newConsultTwo/img25.jpg')},
           {src:require('../assets/img/newConsultTwo/img26.jpg')},
           {src:require('../assets/img/newConsultTwo/img27.jpg')},
           {src:require('../assets/img/newConsultTwo/img28.jpg')},
           {src:require('../assets/img/newConsultTwo/img29.jpg')},
           {src:require('../assets/img/newConsultTwo/img30.jpg')},
           {src:require('../assets/img/newConsultTwo/img31.jpg')},


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
  .newConsultTwo{
    margin:20px auto;
    width:800px;
  }
  .newConsultTwo img{
    width:800px;
    margin:10px 0;
  }
</style>

