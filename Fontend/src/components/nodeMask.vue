<template>
<div class="nodeMask">
  <div class="nodeInfo">
    <div class="nodeContent">
      <table v-show="nodeValToogle">
          <tr>
            <td>Name</td>
            <td><input type="text" :value="nodeVal.name" name="name" disabled></td>
          </tr>
          <tr>
            <td>Description</td>
            <td><input type="text"  :value="nodeVal.description" name="description" disabled></td>
          </tr>
          <tr>
            <td>Determinacy</td>
            <td><input type="text" :value="nodeVal.deterministic" name="determinacy" disabled></td>
          </tr>
          <tr>
            <td>Version</td>
            <td><input type="text" :value="nodeVal.version" name="version" disabled></td>
          </tr>
          <tr>
            <td>Create Time</td>
            <td><input type="text"  :value="nodeVal.createDate" name="createtime" disabled></td>
          </tr>
          <tr>
            <td>Owner</td>
            <td><input type="text"  :value="nodeVal.owner" name="owner" disabled></td>
          </tr>
        <tr>
          <td>Deprecated</td>
          <td><input type="text" :value="nodeVal.deprecated" name="deprecated" disabled></td>
        </tr>

      </table>
       <table v-show="parameterInputArr.length>0&&nodeVal.commandLine">
         <thead>
           <tr>
             <th>Parameter</th>
             <th>Type</th>
             <th>Value</th>
           </tr>
           <tr v-for="(item,i) in parameterInputArr">
             <td v-for="(val,index) in item.slice(0,3)" v-show="index!=0">{{val.replace("\"","").replace("\"","")}}</td>
             <td v-for="(val,index) in item.slice(3,4)"><input :id="'input'+i" type="text" :value='val.replace("\"","").replace("\"","")' @input="blurInput(val,i)"></td>
           </tr>
         </thead>
       </table>
    </div>

  </div>

</div>
</template>

<script>
    export default {
        name:"nodeMask",
        data(){
            return{
              parameterInputArr:[],
              nodeVal:{},
              nodeValToogle:false,
            }
        },
        methods:{
          maskClick:function () {
            let _that=this;
            _that.$emit('maskClick')
          },
          blurInput:function (val,i) {
             let _that=this;
             let commandLineStr=_that.nodeInfo.commandLine;
             let commandLineArr=commandLineStr.split('--');
             let inputInStr='';
             let inputOutStr='';
             let commandLineHead=commandLineArr[0]+'--'+commandLineArr[1];
              commandLineArr.forEach(function (val,index) {
                if(val.indexOf('{in:')>0){
                  inputInStr+='--'+val;
                }else if(val.indexOf('{out:')>0){
                  inputOutStr+='--'+val;
                }
              });
              let commandLineStrStart=commandLineHead+inputInStr+inputOutStr;
              let input=document.getElementById('input'+i);
              let parameter=this.parameterInputArr[i];
              parameter[parameter.length-1]=input.value;
              let str='';
              _that.parameterInputArr.forEach(function (value,index) {
                str+= _that.arrToStr(value)+' '
              });

              _that.nodeInfo.commandLine=commandLineStrStart+str;
          },
          strToArr:function (str) {
            let strArr=str.split('[');
            let strIndexFrist=strArr[0];
            let strIndexNext=strArr[strArr.length-1].split(':');
            let strIndex=strIndexNext[strIndexNext.length-1];
            let strIndexLast=strIndex.substring(8,strIndex.length-1).replace("]","");
            strIndexNext.pop();
            strIndexNext.unshift(strIndexFrist)
            strIndexNext.push(strIndexLast)

            return strIndexNext
          },
          arrToStr:function (arr) {

              let fristStr='';
              let lastStr='';
              let nextStr1='';
              let nextStr2='';
            arr.forEach(function (item,i) {
              if(i==0){
                fristStr='--'+item+'['
              }else if(i==arr.length-1){
                lastStr='default,'+item+']'
              }else if(i==1){
                nextStr1=item+':'
              }else if(i==2){
                nextStr2=item+':'
              }
            });
            return fristStr+nextStr1+nextStr2+lastStr
          }
        },
      watch: {
        'nodeInfo': {
          handler: function (val) {
            let _that = this;
            if(val==null){
              _that.parameterInputArr=[];
              _that.nodeVal={};
              _that.nodeValToogle=false
            }else{
             _that.nodeVal=val;
             if(val.commandLine){
               let commandLineArr= val.commandLine.split('--').slice(2);
               let inputIn=[];
               let inputOut=[];
               let parameterInput=[];
               commandLineArr.forEach(function (value,index) {
                 if(value.indexOf('{in:')>0){
                   inputIn.push(value)
                 }else if(value.indexOf('{out:')>0){
                   inputOut.push(value)
                 }else {
                   parameterInput.push(value)
                 }
               });
               _that.parameterInputArr=[];
               if(parameterInput.length>0){
                 parameterInput.forEach(function (value,index) {
                   _that.parameterInputArr.push(_that.strToArr(value))
                 })
               }

             }
              _that.nodeValToogle=true
             }



          },
        },
        'currindex': {
          handler: function (val) {
            let _that=this;
            // console.log(val-1)
          },
        }
      },
        props:[
         'nodeInfo'
        ]
    }
</script>

<style scoped>

.nodeMask  .nodeInfo{
    width:100%;
    margin:0 auto;

  }
.nodeMask  .nodeContent{
  padding: 5px;
}
.nodeMask  .nodeContent table{
  width: 100%;

}
.nodeMask  .nodeContent table:first-child tr td{
  border:0;
}
.nodeMask  .nodeContent table:first-child tr td:first-child{
  width:10%;
}
.nodeMask  .nodeContent table:first-child tr td input{
  background: #ededed;
  border-radius: 4px;
  margin-bottom:5px;
  border: 1px solid #bfcbd9;
}
.nodeMask  .nodeContent table:last-child tr td{
  text-align: center;
}
.nodeMask  .nodeContent table:last-child tr td input{
  border: 1px solid #bfcbd9;
  border-radius: 4px;
  margin: 5px 0;
  font-size: 12px;
  color:#444;
  width:80%;

}
.nodeMask  .nodeContent table th,
.nodeMask  .nodeContent table td{
  border:1px solid #ccc;
  width:33.33%;
  font-size: 12px;
  text-align: left;
}
.nodeMask input{
  width: 100%;
  border:0;
}
</style>
