<template>
  <div class="leftBar">

    <el-menu default-active="2" class="el-menu-vertical-demo" @select="handleSelect" @open="handleOpen" @close="handleClose" :default-openeds="navselected" :default-active="navselect" :active="navselect">
      <el-submenu  index="1">
        <template slot="title"><i class="el-icon-menu"></i>机器视觉</template>
        <el-menu-item index="1-1">
          <template slot="title">图片标签化</template>
        </el-menu-item>
        <el-menu-item index="1-2">
          <template slot="title">图片风格迁徙</template>
        </el-menu-item>
        <el-menu-item index="1-3">
          <template slot="title">人脸识别</template>
        </el-menu-item>
        <el-menu-item index="1-4">
          <template slot="title">图像物体抽取</template>
        </el-menu-item>
        <el-menu-item index="1-5">
          <template slot="title">智能商铺人流物流识别系统</template>
        </el-menu-item>
        <el-menu-item index="1-6">
          <template slot="title">物体检测</template>
        </el-menu-item>
        <el-menu-item index="1-7">
          <template slot="title">语言切割</template>
        </el-menu-item>

      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>自然语言处理</template>
        <el-menu-item index="2-1">
          <template slot="title">自动英文作文阅读</template>
        </el-menu-item>
        <el-menu-item index="2-2">
          <template slot="title">自动文摘系统</template>
        </el-menu-item>
        <el-menu-item index="2-3">
          <template slot="title">智能问答系统</template>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'leftBar',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',

      navselected:["0"],
      navselect:'0-0'
    }
  },
  beforeMount:function () {
    this.tabClick()
  },
  watch: {
    "$route":function () {
      this.tabClick()
    }
  },

  methods:{
    handleSelect(key, keyPath){
      this.$emit('itemlistClick',key);
    },
    tabClick:function () {
      let _that=this;
      let path=_that.$route.path;
      if(path=="/topMenu/item/photoLabel"){
        _that.navselected=["1"];
        _that.navselect='1-1'
      }else if(path=="/topMenu/item/photoStyle"){
        _that.navselected=["1"];
        _that.navselect='1-2'
      }else if(path=="/topMenu/item/waitForRecognition"){
        _that.navselected=["1"];
        _that.navselect='1-3'
      }else if(path=="/topMenu/item/imgObjExtraction"){
        _that.navselected=["1"];
        _that.navselect='1-4'
      }else if(path=="/topMenu/item/peoObjRecognition"){
        _that.navselected=["1"];
        _that.navselect='1-5'
      }
     _that.leftToggle=false
    },
    handleOpen(key, keyPath) {
      console.log('开');
    },
    handleClose(key, keyPath) {
      console.log('关');
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-submenu{
    color: #5e7187;
    font-size: 16px;
    font-family: "Hiragino Sans GB W6";
    font-weight: bold;

  }
  .leftBar .el-submenu :hover,
  .leftBar .el-menu-item:hover{
    background-color: transparent !important;
  }
  .leftBar .el-menu{
    background: transparent;
  }
  .leftBar .el-submenu{
    text-indent: 1em;
    font-weight: normal;
    font-size: 14px;
    color:#509ff0;
  }



  .leftBar .el-icon-menu{
    background: url("../assets/img/image/mouseover_com_pack_up.png") no-repeat 15px center;

  }
  .leftBar .el-submenu.is-opened .el-icon-menu{
    background: url("../assets/img/image/mouseover_com_unfold.png") no-repeat 15px center;

  }
  .leftBar .el-icon-menu{
    position: absolute;
    top:0;
    left:0;
    width:100%;
    height:100%;
    margin:0;
  }
  .leftBar .el-icon-menu::before{
    content: '';
  }
  .el-icon-menu{
    margin-right: 15px;
  }

  .el-menu-item.is-active{
    background-color:rgb(43, 138, 194);
    color:#fff;
  }
  .el-menu-item{
     color:#629cb7;
  }
  .el-menu-item{
    background: url("../assets/img/second.png")no-repeat 40px center;
    text-indent: 20px;
  }
</style>
