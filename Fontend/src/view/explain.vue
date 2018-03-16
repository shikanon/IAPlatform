<template>
  <div class="explain">
    <div class="mainExplain">
      <div class="explainLeft">
        <explainleftBar @explainClick="changerouter"></explainleftBar>
      </div>
      <div class="explainContent">
        <div class="grid-content bg-purple-light">

          <router-view></router-view>
        </div>
      </div>
    </div>
    <div class="top" @click="topClick" v-show="scroll>200">TOP</div>
  </div>
</template>
<script>


</script>

<script>
  import explainleftBar from "../components/explainleftBar.vue"
  import $ from 'jquery'
  export default {
    name: 'explain',
    data () {
      return {
        handleIndex:1,
        activeName:['1'],
        scroll:0,
      }
    },

    mounted:function(){

    },
    components: {
      explainleftBar
    },
    watch:{
   },
    computed: {


    },
    mounted() {
      window.addEventListener('scroll', this.menu)
    },
    methods:{
      menu:function () {
        this.scroll = document.body.scrollTop;

      },
      itemListShow:function (val,index) {
        this.itemShow=!this.itemShow
      },
      topClick:function () {
        $("html,body").animate({scrollTop:0}, 500);
      },
      changerouter:function (key) {
        console.log(key)
        var _that=this;
        switch (key) {
          case '1-1':
            _that.$router.push("/topMenu/explain/photoLabelExplain");
            break;
          case '1-2':
            _that.$router.push("/topMenu/explain/photoLabelExplain");
            break;
          case '1-3':
            _that.$router.push("/topMenu/explain/photoLabelExplain");
            break;
          case '1-4':
            _that.$router.push("/topMenu/explain/photoLabelExplain");
            break;
          case '1-5':
            _that.$router.push("/topMenu/explain/photoLabelExplain");
            break;
          case '2-1':
            _that.$router.push("/topMenu/explain/newConsultOne");
            break;
          case '2-2':
            _that.$router.push("/topMenu/explain/newConsultTwo");
            break;
          case '2-3':
            _that.$router.push("/topMenu/explain/newConsultThree");
            break;
          case '3-1':
            _that.$router.push("/topMenu/explain/useHelpOne");
            break;
          case '3-2':
            _that.$router.push("/topMenu/explain/useHelpTwo");
            break;
          case '3-3':
            _that.$router.push("/topMenu/explain/useHelpThree");
            break;
          case '4-1':
            _that.$router.push("/topMenu/explain/comproblemOne");
            break;
          case '4-2':
            _that.$router.push("/topMenu/explain/comproblemTwo");
            break;
          case '4-3':
          _that.$router.push("/topMenu/explain/comproblemFour");
          break;
          case '4-4':
          _that.$router.push("/topMenu/explain/comproblemThree");
          break;
          default:
            break;
        }

      }
    },

  }
</script>

<style>
  .leftNav{
    position:fixed !important;
  }
  .el-menu-item, .el-submenu__title{
    height:40px;
    line-height: 40px;
  }
  .explain .el-submenu .el-menu-item{
    height:40px;
    line-height: 40px;
  }
  .el-collapse-item__header__arrow{


  }
  .mainExplain{

  }
  .explainLeft{
    position: fixed;
    top:8vh;
    width: 300px;
    height: 100%;
    background: #fff;
  }
  .explainContent{
    margin-left:300px;
  }
  .el-col.el-col-19{
    background:#f3f5f7;
  }
  .explain .el-submenu{
    font-size: 14px !important;
    font-weight:600 !important;
  }
  .explain .el-submenu .el-menu-item{
    font-size: 12px !important;
    font-weight:600 !important;
  }
  .explain .top{
    position: fixed;
    z-index: 4;
    /* top: 0; */
    right: 10px;
    bottom: 10px;
    display: inherit;
    background: #000;
    padding: 10px;
    opacity: 0.7;
    color: #fff;
    cursor: pointer;
    font-family: 'Lucida Console', Monaco, monospace
  }
</style>
