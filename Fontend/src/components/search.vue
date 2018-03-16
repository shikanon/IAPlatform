<template>
  <div class="search">
    <div class="searchIcon">
      <input type="text" value="" name="search" id="search" placeholder="请输入关键词" @input="searchInput">
    </div>
    <ul>
       <li v-for="(item,i) in searchArr" @click="searchLiClick(item,i)">{{item.name}}</li>
    </ul>
  </div>
</template>

<script>
    export default {
        name:'search',
        props:['menuItems'],
        data(){
              return{
                searchList:[],
                list:[],
                searchArr:[]
              }
        },
       beforeMount:function () {

       },
       mounted:function () {

       },
      watch:{
        'menuItems':{
          handler:function (val) {
            let _that=this;
            _that.searchList=[];
            _that.listSearch(val);
          },
          deep:true
        },
      },
      methods:{
          listSearch:function (val) {
            let _that=this;
            for(let index=0;index<val.length;index++){
              let list=val[index].programList;
              if (val[index].programList) {
                 _that.listSearch(list)
              }else{
                _that.searchList.push(val[index])
              }
            }
          },

        searchInput:function () {
           let _that=this;
           _that.searchArr=[];
           let inputText=document.querySelector('#search');
           for(let index=0;index<_that.searchList.length;index++){
             if(_that.searchList[index].name.indexOf(inputText.value)>0){
               _that.searchArr.push(_that.searchList[index]);
             }

           }
          },
         searchLiClick:function (item,i) {
            let _that=this;
            _that.$emit("searchLiClick",item,i)
         }
      },
    }

</script>

<style scoped>
.search ul li{
  height:35px;
  line-height:35px;
  text-indent: 1em;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  cursor: pointer;
}
</style>
