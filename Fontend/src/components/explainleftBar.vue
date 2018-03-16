<template>
  <div class="explainleftBar">

    <el-menu default-active="2" class="el-menu-vertical-demo" @select="handleSelect" @open="handleOpen" @close="handleClose" :default-openeds="navselected" :default-active="navselect" :active="navselect">
      <el-submenu  index="1">
        <template slot="title"><i class="el-icon-menu"></i>文档说明</template>
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
          <template slot="title">物体检测</template>
        </el-menu-item>
        <el-menu-item index="1-5">
          <template slot="title">语言切割</template>
        </el-menu-item>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>最新咨询</template>
        <el-menu-item index="2-1">
          <template slot="title">基于云平台的分布式网络爬虫的研究与设计</template>
        </el-menu-item>
        <el-menu-item index="2-2">
          <template slot="title">智慧城市与城市计算</template>
        </el-menu-item>
        <el-menu-item index="2-3">
          <template slot="title">电信大数据指数研究探讨</template>
        </el-menu-item>
      </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-menu"></i>使用帮助</template>
          <el-menu-item index="3-1">
            <template slot="title">英语作文自动批阅系统</template>
          </el-menu-item>
          <el-menu-item index="3-2">
            <template slot="title">机器学习算法讲义之体系结构介绍</template>
          </el-menu-item>
          <el-menu-item index="3-3">
            <template slot="title">机器学习算法讲义之机器视觉</template>
          </el-menu-item>

      </el-submenu>
      <el-submenu index="4">
        <template slot="title"><i class="el-icon-menu"></i>常见问题</template>
        <el-menu-item index="4-1">
          <template slot="title">检察院卷宗智能化处理技术交流会</template>
        </el-menu-item>
        <el-menu-item index="4-2">
          <template slot="title">数据整合及变现思考</template>
        </el-menu-item>
        <el-menu-item index="4-3">
          <template slot="title">大数据平台整合笔记</template>
        </el-menu-item>
        <el-menu-item index="4-4">
          <template slot="title">电信客户消费能力模型研究</template>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'explainleftBar',
  data () {
    return {
      navselected:["0"],
      navselect:'0-0'
    }
  },
  beforeMount:function () {
    console.log(this.$route.path);
    this.tabclick()
  },
  watch: {
    // 如果路由有变化，会再次执行该方法
    "$route":function () {
      this.tabclick()
    }
  },
  methods:{
    handleSelect(key, keyPath){
      this.$emit('explainClick',key);
    },
    handleOpen(key, keyPath) {
      console.log(key);
    },
    handleClose(key, keyPath) {
      console.log(key);
    },
    tabclick:function () {
      let _that=this;
      let path=_that.$route.path;
      if(path=="/topMenu/explain/photoLabelExplain"){
        _that.navselected=["1"];
        _that.navselect='1-1'
      }else if(path=="/topMenu/explain/photoLabelExplain"){
        _that.navselected=["1"];
        _that.navselect='1-2'
      }else if(path=="/topMenu/explain/newConsultOne"){
        _that.navselected=["2"];
        _that.navselect='2-1'
      }else if(path=="/topMenu/explain/newConsultTwo"){
        _that.navselected=["2"];
        _that.navselect='2-2'
      }else if(path=="/topMenu/explain/newConsultThree"){
        _that.navselected=["2"];
        _that.navselect='2-3'
      }else if(path=="/topMenu/explain/useHelpOne"){
        _that.navselected=["3"];
        _that.navselect='3-1'
      }else if(path=="/topMenu/explain/useHelpTwo"){
        _that.navselected=["3"];
        _that.navselect='3-2'
      }else if(path=="/topMenu/explain/useHelpThree"){
        _that.navselected=["3"];
        _that.navselect='3-3'
      }else if(path=="/topMenu/explain/comproblemOne"){
        _that.navselected=["4"];
        _that.navselect='4-1'
      }else if(path=="/topMenu/explain/comproblemTwo"){
        _that.navselected=["4"];
        _that.navselect='4-2'
      }else if(path=="/topMenu/explain/comproblemFour"){
        _that.navselected=["4"];
        _that.navselect='4-3'
      }else if(path=="/topMenu/explain/comproblemTree"){
        _that.navselected=["4"];
        _that.navselect='4-4'
      }
    }
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
    background: #fff;
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
