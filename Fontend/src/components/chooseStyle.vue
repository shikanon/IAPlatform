<template>

  <div class="maskShow">
      <div class="chooseMask"></div>
      <div class="modal-content">

        <el-row :gutter="20" v-for="(val,index) in styleImgData" :class="'ccc'+index">
          <el-col :span="8" v-for="(item,i) in val">
            <div class="grid-content bg-purple" @click="selectImg(item,i,index)" :class="[currIndex==i&&curr==index?'active':'']">
            <img :src="urlImg+item.url" :alt="item.picname">
            </div>
          </el-col>
        </el-row>

        <div class="btn">
          <el-button class="modalBtn" @click="uploadModal">上传风格照片</el-button>
          <el-button class="modalBtn" @click="exitModal">退出</el-button>
        </div>
      </div>
    </div>

</template>

<script>
  import { HOST } from '../service/HOST'
export default {
  name: 'maskShow',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      urlImg:HOST,
      currIndex:-1,
      curr:-1,
      imgName:'5.jpg',
      urlImgData:'',
    }
  },
  props: {
    "styleImgData":{
      type:Array,
    },

  },
   watch:{
     /*'styleImgData':function(value){
       console.log(value);
     },*/
   },
  methods:{
    exitModal:function () {
        let _that=this;
        this.$emit('exitModalClick');
    },
    selectImg:function (item,i,index) {
        this.currIndex=i;
        this.curr=index;
        this.imgName=item.picname;
        this.urlImgData=this.urlImg+item.url
    // console.log(this.imgName)
    },
    uploadModal:function () {

        this.$emit('saveImgClick',this.imgName,this.urlImgData);


  }
}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .maskShow{
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
  }
  .modal-content{
    height:500px;
    width:450px;
    padding:20px;
    background: #fff;
    z-index:100;
    position:relative;
    top:100px;
    box-shadow: 0 5px 15px rgba(0,0,0,.5);
    border: 1px solid rgba(0,0,0,.2);
    border-radius: 6px;
    margin: 0 auto;
  }
  .modal-content .el-row {
    margin-bottom: 20px;

  }
  .grid-content {
    height: 137px;
    background: #ccc;
    border:2px solid #fff
  }
  .modalBtn{
    background-color: #7cd434!important;
    color: #fff;
    height: 30px;
    border-radius: 15px;
    padding: 0 15px;
    border-width: 0;
  }
  .btn .modalBtn:first-child{
    width:130px
  }
  .btn{
    text-align: center;
  }
  .active{
    border:2px solid #509ff0;
    box-shadow: 0 0 9px #ccc;
  }
  .maskShow img{
    width:100%;
    height:100%;
  }
</style>
