<template>
  <div class="bigMenuBar">
    <search :menuItems="menuItems" @searchLiClick="searchLiClick"></search>
    <div  class="childMenuBar" id="childMenuBar">
      <div class="tree well">
          <ul>
            <li v-for="(item,index) in sortMenuItems"
                :style="menuItemStyle(item)"
                :class="menuItemClass(item,index)"
                @click.stop="menuItemClick(item,index)" >
              <i :class="drawdownClass(item)" v-show="showDrawdown(item)"/></i>
              <span>{{item.name}}</span>
            </li>
          </ul>
      </div>
    </div>
  </div>
</template>

<script>
  import search from './search.vue'
    export default {
      name:"bigMenuBar",
      data(){
          return{
            sortMenuItems:[],
            selIndex:0
          }
      },
      props:['menuItems'],

      watch:{
        'menuItems':{
            handler:function (val) {
              let _that=this;
              _that.sortMenuItems = [];
              val.forEach(function (value,index) {
                let menuItem={};
                menuItem.types=value.type;
                menuItem.name = value.name;
                menuItem.id=value.id;
                if(value.jobId){
                  menuItem.jobId=value.jobId;
                }
                menuItem.level = 1;
                menuItem.hasChild = !!value.programList>0;
                if(menuItem.hasChild){
                  menuItem.programList= value.programList;
                }else{
                    menuItem.inputOut=value.inputOut;
                    menuItem.inputIn=value.inputIn;
                }
                menuItem.extend = false;
                _that.sortMenuItems.push(menuItem);
              })
            },
            deep:true
        },
      },
      components: {
        search
      },

      methods:{
        menuItemClass(item,index){
          return this.selIndex==index ? "menuItem selMenuItem" : "menuItem";
        },
        menuItemStyle(item){
          let levelOffset = 8*item.level+ "px";
          return item.level>1 ?  {paddingLeft:levelOffset,background:"#e1e9f2"} : {paddingLeft:levelOffset};
        },
        drawdownClass(item){
            return item.extend ? "up":"down"
        },
        showDrawdown(item){
          return item.hasChild;
        },
        searchLiClick:function (item,i) {
         let _that=this;
          _that.$emit('searchLiClick',item,i)
        },
        menuItemClick(item,index){

          this.selIndex = index;//点击改变样式
          this.$emit("menuItemClick",item,index);
          if(item.extend){//收缩
            var foldCount = 0;
            let itemLevel = item.level;
            item.extend = false;
            for(var itemIndex=index+1;itemIndex<this.sortMenuItems.length;itemIndex++){
              let menuItem = this.sortMenuItems[itemIndex];
              if(menuItem.level > itemLevel){
                foldCount++;
                continue;
              }
              break;
            }
            this.sortMenuItems.splice(index+1,foldCount);
          }else{//展开
            if(!item.hasChild) return;
            item.extend = true;
            let self = this;
            let programList = item.programList;
            let childLevel = item.level + 1;
            programList.forEach(function(value,childIndex){
              var menuItem = {};
              menuItem.name = value.name;
              menuItem.id=value.id;
              if(value.jobId){
                menuItem.jobId=value.jobId;
              }
              menuItem.level = childLevel;
              menuItem.hasChild =!!value.programList;
              if(menuItem.hasChild){
                menuItem.programList = value.programList;
              }else{
                  menuItem.inputOut=value.inputOut;
                  menuItem.inputIn=value.inputIn;
              }
              menuItem.extend = false;
              self.sortMenuItems.splice((index+1+childIndex),0,menuItem);
            })
          }
        },

      }
    }
</script>

<style scoped>
  .menuItem{
    padding-right: 8px;
    cursor: default;
    display: flex;
  }

  .menuItem.selMenuItem{
    color:#0f0 !important;
  }

  .menuItem i{
    display: inline-block;
    width: 16px;
    cursor: pointer;
    height: 16px;
    margin-top:5px;
    margin-right: 5px;
  }
  .menuItem i.up{
    transition: all 0.25s;
    background: url("../assets/img/hcm-file-open.png") no-repeat center center;

  }
  .menuItem i.down{
    transition: all 0.25s;
    background: url("../assets/img/hcm-file-closed.png") no-repeat center center;

  }
  .tree.well>ul>li.menuItem{
     background: #d6dde6;
  }

  .menuItem img.down{
    transition: all 0.5s;
    transform: rotate(0deg);
   }
</style>
