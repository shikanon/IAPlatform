<template>
    <div id="maskNodeChart" v-show="maskNodeChartTog">
      <div class="mask"></div>
      <div class="maskNode">
         <button class="closeBtn el-icon-close" @click="tolMaskChartClick"></button>
        <el-tabs style="min-height:600px;" @tab-click="handleClick" v-model="activeName">
            <el-tab-pane label="参数设置" name="tab-0">
              <div style="width: 80%;margin: 0 auto">
                <nodeMask  :nodeInfo="nodeInfo"></nodeMask>
              </div>
            </el-tab-pane>
            <el-tab-pane label="数据可视化" name="tab-1">
              <div class="chartTilte" v-for="(item,index) in titleChart" @click="titleChartClick(item.par,item.name,item.type,item.dataType,index)" :style="index==0?{color:'#333'} : {color:'#ccc'}" v-show="nodeChartTog">{{item.name}}</div>
              <chart :option="chartContext"  id="nodeChart" v-show="nodeChartTog"></chart>
              <div v-show="!nodeChartTog" class="onData">暂无数据</div>
              <div class="chartMask" v-show="chartMaskTog">
                <el-form ref="form" :model="sizeForm" label-width="40px" size="mini" :inline="true">
                  <el-form-item label="x轴">
                    <el-select v-model="sizeForm.xAxis" placeholder="x轴" @change="changeAxis(sizeForm.xAxis)">
                      <el-option  v-for="(item,index) in sizeForm.axis" :label="item.name" :value="item.name" :key="item.name">{{item.name}}</el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="y轴">
                    <el-select v-model="sizeForm.yAxis" placeholder="y轴" @change="changeAxis(sizeForm.yAxis)">
                      <el-option  v-for="(item,index) in sizeForm.axis" :label="item.name" :value="item.name" :key="item.name">{{item.name}}</el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
              </div>
            </el-tab-pane>
            <!--<el-tab-pane  v-for="(item,index) in editableTabsChart"
                          :key="item.name"
                          :label="item.title"
                          :name="item.name"
                          @click="tabTableClick(item,index)">
                <chart :option="chartContext"  class="nodeChart" v-show="nodeChartTog"></chart>
                <div v-show="!nodeChartTog" class="onData">暂无数据</div>
            </el-tab-pane>-->
            <el-tab-pane
              v-for="(item,index) in editableTabs"
              :key="item.name"
              style="margin:0 10px;"
              :label="item.title"
              :name="item.name"
              @click="tabTableClick(item,index)"
            >
              <el-table :data="elDataTable" max-height="500" stripe border v-loading="loading">
                <el-table-column type="index"  :index="indexMethod" width="80" label="序号" fixed></el-table-column>
                 <el-table-column  v-for="(value,i) in editableTitle" :key="value" :prop="value"  :label="value" width="150" ></el-table-column>
              </el-table>
              <div class="block">
                <el-pagination
                  small
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-sizes="[5, 10, 30, 40]"
                  :page-size="pagesize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="item.tableData.length">
                </el-pagination>
              </div>
            </el-tab-pane>

        </el-tabs>
        <!--<div slot="footer" class="dialog-footer">
          <el-button  size="small" type="primary">确定</el-button>
          <el-button  size="small" @click="tolMaskChartClick">取 消</el-button>
        </div>-->
      </div>
    </div>
</template>

<script>
  import $ from 'jquery'
  import get from '../service/get'
  import post from '../service/post'
  import chart from '../components/charts.vue'
  import nodeMask from './nodeMask.vue';
  import { Message } from 'element-ui';
    export default {
        name: 'maskNodeChart',
        data(){
            return {
              //customChartTitle:'',
              //outputNode:[],
              maskNodeChartTog:false,
              activeName:'tab-0',
              chartContext:null,
              currentPage:1,
              pagesize:10,
              nameNum:2,
              color:'#fff000',
              bdaJob:null,
              parameter:{
                  path:'',
                  ooziejobID:''
              },
              loading:false,
              editableTabs: [],
              titleChart:[],
              chartMaskTog:false,
              nodeChartTog:false,
              sizeForm: {
                yAxis: '',
                xAxis:'',
                axis:[]
              },
              activeChartType:'bar',
              chartList:[],
              name:'',
              dataType:'',
              nodeInfo:null
            }
        },
        beforeMount: function () {
            let _that=this;
        },
        watch:{

        },
        mounted:function () {
         /* let dom=document.getElementById('nodeChart');
          dom.style.width='100%';
          dom.style.height='500px';*/
        },
        components:{chart,nodeMask},
        computed: {

          elDataTable: function () {
              let _that=this;
              for(let index=0;index<_that.editableTabs.length;index++){
                return this.editableTabs[index].tableData.slice((this.currentPage-1)*this.pagesize,this.currentPage*this.pagesize)
              }
          },
          editableTitle:function () {
             let _that=this;
             let list=[];

             for(let index=0;index<_that.elDataTable.length;index++){
              for(let key in _that.elDataTable[index]){
                list.push(key)
              }
               break
             }
            return list
          },

        },
        methods: {
            //在父组件中intellLaboratory双击事件调用该方法，驱动此组件的运行
          childAction:function(val,value,bol,nodeInfo){
            let _that=this;
            _that.nodeInfo=nodeInfo;
            _that.maskNodeChartTog=bol;
            if(val!=null){
             _that.startData(val,value,bol)
            }else{
              _that.editableTabs=[];
              _that.titleChart=[];
              _that.nodeChartTog=false;
            }

          },
          //请求表格数据和chart数据对prog类型和data类型进行判断
          startData:function (val,value,bol) {
             let _that=this;
             let jobId=val.object.bdaJob.oozieJob;
             _that.parameter.ooziejobID=jobId;
             _that.editableTabs=[];
             _that.titleChart=[];
             _that.activeName='tab-0';
             if(_that.parameter.ooziejobID!=''){
                 if(value.file){
                     _that.parameter.path=value.file;
                     _that.chartData(_that.parameter,value.name);
                     _that.dataType='data';
                     _that.commonOption(_that.parameter,value.name,'bar','data');
                 }else{
                     value.inputOut.forEach(function (data,index) {
                           _that.$argumentPath(value).forEach(function (item,i) {
                               _that.parameter.path=item;
                               let vrl=data.split(':')[data.split(':').length-1].split("\"");
                               _that.chartData(_that.parameter,vrl[1]);
                               let titleData={
                                 par:_that.parameter,
                                 name:vrl[1],
                                 type:'bar',
                                 dataType:'prog'
                               };
                           _that.titleChart.push(titleData)
                           })
                      });
                 _that.dataType=_that.titleChart[0].dataType;
                 _that.commonOption(_that.titleChart[0].par,_that.titleChart[0].name,_that.titleChart[0].type,_that.titleChart[0].dataType);
                 }
             }

          },
          handleSizeChange(val) {
             this.pagesize=val;
            // this.loading=true;
          },
          handleCurrentChange(val) {
              this.currentPage=val;
              // this.loading=true;
          },
          handleClick:function (tab, event) {
          },
          indexMethod:function(index) {
            let _that=this;
            return (index+1)+((_that.currentPage-1)*10);
          },
          //创建表格模块
          chartData:function (val,name) {
            let _that=this;
            let form = new FormData();
            for(let key in val){
               form.append(key,val[key]);
            }
            _that.axios.post("/ueSparkMLSys/presentAction!preview.action",form).then(function(data) {
                let obj=data.data;
                if(obj!=''){
                  let table={
                    title:name,
                    name:'tab-'+_that.nameNum,
                    tableData:obj.data
                  };
                  _that.editableTabs.push(table);
                  _that.nameNum++;
                }

            }, function(error) {
              Message({message: '系统异常,请重试', type: 'error'})
            });
            let data={
                 title:{
                     text:"标题",
                     subtext:"副标题"
                 },
                 normalChart:"radar",
                   dataList:[{
                    max:1,
                    name:"评估结果",
                    skillValue:[
                      {
                        text:"精确度",
                        value:0.9
                      },
                      {
                        text:"准确度",
                        value:0.7
                      },
                      {
                        text:"召回率",
                        value:0.2
                      },
                      {
                        text:"F1值",
                        value:0.6
                      }
                    ]}
                    ]
                };

          },
          //请求图表数据进行分类调用
          commonOption:function (par,name,type,dataType) {
            let _that=this;
            let commonOption='';
            let form = new FormData();
            for(let key in par){
              form.append(key,par[key]);
            }
            _that.nodeChartTog=false;
            _that.axios.post("/ueSparkMLSys/presentAction!getBarChart.action",form).then(function(data) {
              let obj=data.data;
              if(obj==undefined||obj==null||obj==''){
                _that.nodeChartTog=false;
                _that.chartMaskTog=false;
              }else{
                _that.nodeChartTog=true;
                _that.chartList=obj.object.data;

                _that.name=name;
                if(type=='bar'){
                  _that.chartContext=_that.barChart(obj.object.data,name,type,dataType);
                }else if(type=='line'){
                  _that.chartContext=_that.lineChart(obj.object.data,name,type,dataType);
                }else if(type=='scatter'){
                  _that.chartContext=_that.scatterChart(obj.object.data,name,type,dataType);
                }

              }
            }, function(error) {
              Message({message: '系统异常,请重试', type: 'error'})
            }).then(function () {
               return  commonOption
            });
          },
          //柱状图类型
          barChart:function (data,name,type,dataType) {
            let _that=this;
            _that.chartMaskTog=false;
            let commonTitle=name;
            let xAxisAry=[];
            let seriesData=[];
            let legendData=[];
            let titleTog=true;
            let typeChart=type;
            let visualMap=[];
            let nomalSelected={};
            //xAxisAry=data[0].skillValue;
            _that.sizeForm.axis=[];
            for(let index=0;index<data.length;index++){
              //if (data[index].name!="user_id"){
                for(let i=0;i<data[index].skillValue.length;i++){
                  let reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
                  if(!reg.test(data[index].skillValue[i])){
                    let val={
                      name:data[index].name,
                      type:type,
                      data:data[index].skillValue
                    };
                    seriesData.push(val);
                    legendData.push(data[index].name);
                  }
                  break;
                }
              //}
            }
            for(let m=0;m<data[0].skillValue.length;m++){
              xAxisAry.push(m+1)
            }
            for(let i=0;i<legendData.length;i++){
              if(i==0){
                nomalSelected[legendData[i]]=true
              }else{
                nomalSelected[legendData[i]]=false
              }
            }
            if(dataType=="prog"){
              titleTog=false;
            }
            if(type=='scatter'){
              visualMap= [
                {
                  left: 'right',
                  top: '10%',
                  show:false,
                  dimension: 1,
                  min: 0,
                  max: 250,
                  itemWidth: 30,
                  itemHeight: 120,
                  calculable:true,
                  precision: 0.1,
                  textGap: 30,
                  inRange: {
                    symbolSize: [10, 30]
                  },
                }
              ]
            }
            let option = {
              title:{
                show:titleTog,
                text:commonTitle
              },
              tooltip : {
                trigger: 'axis',
                axisPointer: {
                  type: 'shadow',
                  label: {
                    show: true,

                  },

                }
              },
              visualMap:visualMap,
              toolbox: {
                show: true,
                feature:_that.featureData(data,name,dataType)
              },
              calculable : true,
              legend: {
                data:legendData,
                orient:'vertical',
                right:'1%',
                bottom:0,
                selected:nomalSelected
              },
              grid: {
                right: '25%',
              },
              xAxis: {
                data:xAxisAry,
              },
              yAxis: {
                type: 'value'
              },
              dataZoom: [
                {
                  show: true,
                  start: 0,
                  end: 100
                },
                {
                  type: 'inside',
                  start:0,
                  end: 100
                },
                {
                  show: true,
                  yAxisIndex: 0,
                  filterMode: 'empty',
                  width: 30,
                  height: '80%',
                  showDataShadow: false,
                  left: '77%'
                },

              ],
              series :seriesData
            };
            return option
          },
         //散点图类型
          scatterChart:function (data,name,type,dataType) {
            let _that=this;
            _that.chartMaskTog=true;
            let commonTitle=name;
            let titleTog=true;
            let selectData=[];
            for(let index=0;index<data.length;index++){
              for(let i=0;i<data[index].skillValue.length;i++){
                let reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
                if(!reg.test(data[index].skillValue[i])){
                  selectData.push(data[index]);
                }
                break;
              }
            }
            _that.sizeForm.axis=selectData;
            let xAxis=[];let yAxis=[];
            for(let index=0;index<selectData.length;index++){
              if(selectData[index].name==_that.sizeForm.yAxis){
                yAxis=selectData[index].skillValue
              }
              if(selectData[index].name==_that.sizeForm.xAxis){
                xAxis=selectData[index].skillValue
              }
            }
            let axisObj=[];
            for(let m=0;m<xAxis.length;m++){
                let axisArr=null;
                  if(xAxis[m]==undefined||xAxis[m]==''||xAxis[m]==null){
                   axisArr={x:0,y:yAxis[m],i:m}
                }else if(yAxis[m]==undefined||yAxis[m]==''||yAxis[m]==null){
                   axisArr={x:xAxis[m],y:0,i:m}
                }else if((xAxis[m]!=undefined||xAxis[m]!=''||xAxis[m]!=null)&&(yAxis[m]!=undefined||yAxis[m]!=''||yAxis[m]!=null)){
                  axisArr={x:xAxis[m],y:yAxis[m],i:m}
                }
                axisObj.push(axisArr)
            }
            let setSort=_that.setSortPoint(axisObj);
            let axis=[];
            for(let m=0;m<setSort.length;m++){
              let axisArr=[setSort[m].x,setSort[m].y];
              axis.push(axisArr)
            }
            if(dataType=="prog"){
              titleTog=false;
            }
            let option = {
              title:{
                show:titleTog,
                text:commonTitle
              },
              tooltip: {
                trigger: 'item',
                axisPointer: {
                  type: 'cross'
                },
                formatter: function (obj) {
                  let str='';
                  let index=0;
                  for(let j=0;j<axisObj.length;j++){
                    if(axisObj[j].x==obj.data[0]&&axisObj[j].y==obj.data[1]){
                       index=axisObj[j].i+1;
                      break;
                    }
                  }
                  for(let i=0;i<obj.data.length;i++){
                      str+=obj.data[i]+' '
                  }
                  return '<div>' + index+'</div><div>'+obj.seriesName+'</div>'
                    + str + '<br>';
                }
              },
              toolbox: {
                show: true,
                feature:_that.featureData(data,name,dataType)
              },
              xAxis: {
                splitLine: {
                  lineStyle: {
                    type: 'dashed'
                  }
                }
              },
              yAxis: {
                splitLine: {
                  lineStyle: {
                    type: 'dashed'
                  }
                },
                scale: true
              },
              series: [{
                name: _that.sizeForm.xAxis+','+_that.sizeForm.yAxis,
                data:axis,
                type:type,
                symbolSize:10,
              }]
            };
            return option
           },
          //点击标题重新加载（如果echart为多个）
          titleChartClick:function (par,name,type,dataType) {
            let _that=this;
            _that.commonOption(par,name,type,dataType);
          },
          //线形图类型
          lineChart:function (data,name,type,dataType) {
            let _that=this;
            _that.chartMaskTog=true;
             return _that.scatterChart(data,name,type,dataType)
          },
          //下拉框x轴y轴
          changeAxis:function(value){
              let _that=this;
            _that.chartContext=_that.scatterChart(_that.chartList,_that.name,_that.activeChartType,_that.dataType);
          },
          customChartClick:function (item) {
            let _that=this;
          },
          /*取消模态框事件*/
          tolMaskChartClick:function () {
            let _that=this;
            _that.maskNodeChartTog=false
          },
          /*图表类型切换*/
          featureData:function (data,name,dataType) {
            let _that=this;
                return {
                  /* myToolRadar: {
                   show: true,
                   title: '雷达图',
                   icon: 'image://http://echarts.baidu.com/images/favicon.png',
                   onclick: function (){
                   _that.chartContext=_that.linechart(data,name,'scatter');
                   }
                   },*/
                  myToolScatter: {
                    show: true,
                    title: '散点',
                    icon: 'image://http://echarts.baidu.com/images/favicon.png',
                    onclick: function (){
                      _that.activeChartType='scatter';
                      _that.chartContext=_that.scatterChart(data,name,'scatter',dataType);
                    }
                  },
                  myToolLine: {
                    show: true,
                    title: '折线图',
                    icon: 'image://http://echarts.baidu.com/images/favicon.png',
                    onclick: function (){
                      _that.activeChartType='line';
                      _that.chartContext=_that.lineChart(data,name,'line',dataType);
                    }
                  },
                  myToolBar: {
                    show: true,
                    title: '柱状图',
                    icon: 'image://http://echarts.baidu.com/images/favicon.png',
                    onclick: function (){
                      _that.activeChartType='bar';
                      _that.chartContext=_that.barChart(data,name,'bar',dataType);
                    }
                  },
                  mark : {show: true},
                  dataView : {show: true, readOnly: false},
                  restore : {show: true},
                  saveAsImage : {show: true},
                }
          },
          /*坐标排序*/
          setSortRule:function(p1, p2) {
            if (parseInt(p1.x)< parseInt(p2.x)) {
              return true;
            }
            else if (parseInt(p1.x)==parseInt(p2.x)) {
              return (parseInt(p1.y)<parseInt(p2.y));
            }
            else {
              return false;
            }
          },
          setSortPoint:function (arry) {
            let _that=this;
            let len = arry.length;
            for (let i = 0; i < len - 1; i++) {
              for (let j = 0; j < len - 1 - i; j++) {
                if (_that.setSortRule(arry[j], arry[j + 1])) {
                  let tmp = arry[j];
                  arry[j] = arry[j + 1];
                  arry[j + 1] = tmp;
                }
              }
            }
            return arry;
          }
        },
        props:['maskNodeChartList'],
    }
</script>

<style scoped>
#maskNodeChart{
  position: fixed;
  width: 100%;
  height:100%;
  top:0;
  left: 0;
  z-index: 200;
}
#maskNodeChart .mask{
  position:absolute;
  width: 100%;
  height:100%;
  top:0;
  left: 0;
  background:rgba(0,0,0,0.5);
  z-index: 200;
}
#maskNodeChart .maskNode{
  background: #fff;
  position:relative;
  margin: 0 auto;
  top: 50%;
  transform: translateY(-50%);
  width:900px;
  border:2px solid #eef1f6;
  z-index: 300;

}
#maskNodeChart .nodeChart{
  margin: 0 auto;
}
#maskNodeChart .closeBtn{
  position:absolute;
  z-index:300;
  right:0;
  top:10px;
  border:0;
  outline: none;
  background: transparent;
  padding: 5px 15px;
  color:#444;
}
#maskNodeChart .closeBtn:hover{
  color:#20a0ff
}
#maskNodeChart .dialog-footer{
  text-align: center;
  margin-bottom: 20px;
}
.onData{
  text-align: center;
  color: #f00;
  height: 200px;
  line-height: 200px;
}
.chartTilte{
  font-size: 18px;
  font-weight: bold;
  color: #333;
  text-indent: 0.4rem;
  position: absolute;
  z-index:100;
}
  .chartMask{
    position: absolute;
    top:0;
    left:30%;
    width:52%;
  }
</style>
