<template>
  <div class="smartMenuBar">
    <ul>
      <li :class="[normalIndex==index?'active':'']" v-for="(item,index) in leftMenu" @click="leftMenuClick(item,index)">
        <i :class="item.className"></i>
        <a href="javascript:;">{{item.name}}</a>
      </li>
    </ul>
  </div>
</template>

<script>
    export default {
      name:'smartMenuBar',
      data(){
          return{
            leftMenu:[
              {name:'模型',className:'modelIcons_default'},
              {name:'数据源',className:'dataSourceIcons_default'},
              {name:'工作流',className:'workFlowIcons_default'}
            ],
            normalIndex:0
          }
      },
      methods:{
        leftMenuClick:function(item,index){
          let _that=this;
          _that.$emit('leftMenuClick',item,index)
          _that.normalIndex=index;
        },
      },
      props:['type']
    }
</script>

<style scoped>

</style>
