<template>
  <div class="intellLaboratory">
    <div class="mainContainer">
      <div class="smartLabs">
        <div class="leftMenuBar">
          <intellBar :type="type" @leftMenuClick="leftMenuClick"></intellBar>
          <bigMuenBar
            :menuItems="leftItems"
            @menuItemClick="menuItemClick"
            @searchLiClick="searchLiClick"
          ></bigMuenBar>
        </div>
        <div class="mainContent">
          <labModel :ruleForm="ruleForm"
                    :nodeInfoToogle="nodeInfoToogle"
                    :nodeInfo="nodeInfo"
                    :currindex="currindex"
          ></labModel>
          <div class="leftContent">
            <div class="dragArea">
              <div class="jtk-demo-main dragList" style="position: relative">
                <formListBar :type="type" @uploadFileClick="uploadFileClick"></formListBar>
                <div id="lableText" style="display: none"></div>
                <div class="jtk-demo-canvas canvas-wide flowchart-demo jtk-surface jtk-surface-nopan" id="canvas" style="position: relative">
                </div>
                <operation
                  @startClick="startClick"
                  @refreshClick="refreshClick"
                  @clearClick="clearClick"
                  @cloneClick="cloneClick"
                  @stopClick="stopClick"
                  :btnStatus="btnStatus">
                </operation>
              </div>
            </div>
          </div>
        </div>
      </div>
      <maskNodeChart></maskNodeChart>
    </div>
  </div>
</template>

<script>
  import $ from 'jquery'
  import get from '../service/get'
  import post from '../service/post'
  import intellBar from '../components/intellBar.vue'
  import labModel from '../components/labModel.vue'
  import operation from '../components/operation.vue'
  import bigMuenBar from '../components/bigMenuBar.vue'
  import formListBar from '../components/formListBar.vue'
  import maskNodeChart from '../components/maskNodeChart.vue'
  import { Message } from 'element-ui';
   export default {
     name:'intellLaboratory',
     data () {
       return {
         numID:0,
         timer:null,
        // cloneNum:0,
         jobDataAll:null,
         //类型序号
         leftMenuIndex:0,
         ruleForm:{
           jobName:'',
           account:'',
           jobId:'',
           itemProgress:'',
           stratTime: '',
           /*endTime:'',
           useTime:'',*/
           description:'',
         },
         //最终数据
        // promNameCode:'',
         //flowWorkXml:'',
         nodeInfoToogle:true,
         nodeInfoList:[],
         relation:[],
         leftItems:[],
         nodeInfo:{},
         currindex:0,
         //如果为true,则调用clear按钮
         clickIndex:false,
        // xotree: new XML.ObjTree(),
         //按钮禁用或启用状态
         btnStatus:{
           startBtn:true,
           clearBtn:true,
           cloneBtn:true,
           stopBtn:true,
           refreshBtn:false,
         },
         //jsPlumb设置
         jsPlumbDatas:{
           instance:null,
           sourceEndpoint:{
             endpoint: "Dot",
             isSource:true,
             paintStyle: {
               stroke: "#509ff0",
               fill: "transparent",
               radius: 4,
               strokeWidth: 1
             },
             connector:["Bezier", { curviness:1 }],
             connectorStyle:{strokeWidth: 1, stroke: "#61B7CF", joinstyle: "round", outlineStroke: "white", outlineWidth:1},
             hoverPaintStyle:{fill: "#216477", stroke: "#216477"},
             connectorHoverStyle:{strokeWidth: 3, stroke: "#216477", outlineWidth: 5, outlineStroke: "white"},
             maxConnections: -1,
           overlays: [[ "Label", {location: [0.5, 1.5], label: "Drag", cssClass: "endpointSourceLabel", visible:false} ]]
           },
           targetEndpoint:{endpoint: "Dot", paintStyle: { fill: "#509ff0", radius: 4 }, hoverPaintStyle: {fill: "#216477", stroke: "#216477"}, dropOptions: { hoverClass: "hover", activeClass: "active" }, isTarget: true,
             overlays: [[ "Label", { location: [0.5, -0.5], label: "Drop", cssClass: "endpointTargetLabel", visible:false } ]]
           },
           getInstanceData:{
             DragOptions: { cursor: 'pointer', zIndex: 2000 },
             ConnectionOverlays: [
                 [ "Arrow", {location: 1, visible:false, width:11, length:11, id:"ARROW"} ],
                  [ "Label", {location: 0.5,visible:false, id: "label", cssClass: "aLabel"}]
                ],
             Container: "canvas",
           },
           init:function (connInfo) {
             connInfo.connection.getOverlay("label").setLabel(connInfo.connection.sourceId.substring(15) + "-" + connInfo.connection.targetId.substring(15)+'('+connInfo.sourceEndpoint.anchor.x+'-'+connInfo.targetEndpoint.anchor.x+')')},
           _addEndpoints:function (toId, sourceAnchors, targetAnchors,instance) {
             for (let i = 0; i < sourceAnchors.length; i++) {
               let sourceUUID = toId + sourceAnchors[i];
               instance.addEndpoint("flowchart" + toId, this.sourceEndpoint, {
                 anchor: sourceAnchors[i], uuid: sourceUUID
               });
             }
             for (let j = 0; j < targetAnchors.length; j++) {
               let targetUUID = toId + targetAnchors[j];
               instance.addEndpoint("flowchart" + toId,  this.targetEndpoint, {
                 anchor: targetAnchors[j], uuid: targetUUID
               });
             }
           },
           _docNum:function(nodeNum,source,target,instance) {
             let targetAnchors=[];
             let  sourceAnchors=[];
             for(let i=0;i<source;i++){
               if(source==1){
                 sourceAnchors.push([0.5, 1, 0, 1 ])
               }else{
                 sourceAnchors.push([i/(source-1), 1, 0, 1 ])
               }
             }
             for(let i=0;i<target;i++){
               if(target==1){
                 targetAnchors.push([0.5,  0, 1, 0])
               }else{
                 targetAnchors.push([i/(target-1), 0, 1, 0])
               }

             }
             let dom = "Window" + nodeNum;
             this._addEndpoints(dom,sourceAnchors,targetAnchors,instance);
           }
         },
         type:{
           //存储data类型的数据
           dataType:[],
           //存储prog类型的数据
           progType:[],
           jobType:[]
         }

       }
     },
     beforeMount:function () {
       let _that=this;
     },
     created(){
     },
     watch:{


     },
     mounted:function(){
       let _that=this;
         _that.typeClassify();
         _that.jsPlumbs();
     },
     beforeUpdate: function () {
       let _that=this;
       //鼠标划过节点提示
       _that.nodeInfoList.forEach(function (item,i) {
           let dom=$('#canvas').find('#'+item.domID);
           let domNext=dom.nextUntil('.window');
           let putArr=item.inputOut.concat(item.inputIn);
           $(domNext).each(function (i,item) {
              $(item).attr('name',putArr[i])
           });
           let domLable=document.querySelector('#lableText');
           $(dom).hover(function (e) {
             _that.labelText=$(this).text();
             _that.mouseFn(e)
           },function(){
             domLable.style.display='none';
           });
           $(domNext).hover(function (e) {
              _that.labelText=$(this).attr('name');
             _that.mouseFn(e)
           },function(){
              domLable.style.display='none';
           })

       })

     },
     components: {
       intellBar,labModel,operation,bigMuenBar,formListBar,maskNodeChart
     },
     methods:{
       uploadFileClick:function () {
         let _that=this;
         _that.typeClassify();
       },
       //鼠标划过节点方法
       mouseFn:function (e) {
         let _that=this;
         let domLable=document.querySelector('#lableText');
         if(_that.labelText!=undefined){
           domLable.style.display='block';
           domLable.innerText=_that.labelText;
           domLable.style.left=e.clientX+10+'px';
           domLable.style.top=e.clientY+10+'px';

         }else{
           domLable.style.display='none';
         }
       },
       //搜索关键词列表点击事件
       searchLiClick:function (item,i) {
           let _that=this;
         _that.menuItemClick(item,i)
       },
       //目录分类以及数据形式处理
       typeClassify:function () {
         let _that=this;
          _that.axios.get('/ueSparkMLSys/categoryAction!getList.action').then(function(data) {
           _that.type.dataType=[];
           _that.type.progType=[];
           _that.type.jobType=[];
           let obj=data.data;
            if(obj.status==true||obj.status==200||obj.status=='OK'||obj.status=='ok'){
                if(obj!=''||obj!=undefined||obj!=null){
                  obj.object.forEach(function (value,index) {
                    if(value.type=="data"){
                      _that.type.dataType.push(value)
                    }else if(value.type=="prog"){
                      _that.type.progType.push(value)
                    }else if(value.type=="job"){
                      _that.type.jobType.push(value)
                    }
                  });
                  //返回的数据进行处理
                  _that.type.progType.forEach(function (value,index) {
                    let item = _that.type.progType[index];
                    if(item.childTree){
                      item.childTree.forEach(function (val) {
                        item.programList.push(val);
                      })
                    }
                  });
                  _that.leftItems=_that.type.progType;
                  _that.type.dataType.forEach(function (value,index) {
                    let item =value;
                    if(item.dataSetList){
                      item.dataSetList.forEach(function (val) {
                        item.programList.push(val);
                      })
                    }
                  });
                  _that.type.jobType.forEach(function (value,index) {
                    if(value.oozieJobList){
                      value.oozieJobList.forEach(function (val) {
                        value.programList.push(val);
                      })
                    }
                  });
                }


             }else{
               Message({message:data.message, type: 'error'})
            }
         }, function(error) {
              console.log(error)
           Message({message: '网络请求失败', type: 'error'})
         });
       },
       //点击最左侧导航栏
       leftMenuClick:function (item,index) {
           let _that=this;
          _that.leftItems=[];
         _that.ruleForm.itemProgress='';
         _that.ruleForm.jobId='';
         _that.ruleForm.jobName='';
         _that.ruleForm.account='';
         _that.ruleForm.description='';
         _that.ruleForm.stratTime='';
          _that.leftMenuIndex=index;
           if(index==0){
              _that.leftItems=_that.type.progType;
            }else if(index==1){
              _that.leftItems=_that.type.dataType;
           }else if(index==2){
              _that.nodeInfo=null;
              _that.leftItems=_that.type.jobType;
           }
       },
       //实例化jsPlumbs
       jsPlumbs:function () {
         let _that=this;
         jsPlumb.ready(function () {
             //注册jsPlumbs
           let instance = window.jsp = jsPlumb.getInstance(_that.jsPlumbDatas.getInstanceData);
           _that.jsPlumbDatas.instance=instance;
           let basicType = {
             connector: "StateMachine",
             paintStyle: { stroke: "red", strokeWidth:1},
             hoverPaintStyle: { stroke: "blue" },
             overlays: ["Arrow"]
           };
           instance.registerConnectionType("basic", basicType);
           instance.batch(function () {
                 //删除节点
             instance.on(document, "click", ".node-delete", function () {
                 let g= this.parentNode;
                 let selIndex = -1;
                  _that.nodeInfoList.forEach(function (value,index) {
                     if(value.domID==g.id){
                       selIndex = index;
                     }
                  });
                 _that.nodeInfo=null;
                  if(selIndex>=0){
                     _that.nodeInfoList.splice(selIndex,1);
                   }
                 _that.deletNodes(g.id);
                 instance.removeAllEndpoints($(this).parent().attr("id"));
                 $(this).parent().remove();
                 let domLable=document.querySelector('#lableText');
                 domLable.innerText='';
                 domLable.style.display='none';
                 });
                 //点击节点事件
             instance.on(document, "click", ".jtk-node", function (e) {
                     let thisNode=this.id;

                     _that.nodeInfoList.forEach(function (value,index) {
                       if(value.domID==thisNode){
                         _that.nodeInfo=value
                       }
                     });

                 });
             instance.on(document, "dblclick", ".jtk-node", function (e) {
                let thisNode=this.id;
                let  maskNodeChartTog=true;
                let  maskNodeChartData=null;
                let  maskNodejobData=_that.jobDataAll;
                _that.nodeInfoList.forEach(function (value,index) {
                    if(value.domID==thisNode){
                     maskNodeChartData=value;
                    }
                 });
                  _that.$children[5].childAction(maskNodejobData,maskNodeChartData,maskNodeChartTog,_that.nodeInfo);
               });
                 //连接事件
             instance.bind("connection", function (connInfo, originalEvent) {
                   if(connInfo.sourceId!=connInfo.targetId){
                     _that.jsPlumbDatas.init(connInfo);
                     let relationList={
                       source:{
                         sourceId:connInfo.sourceId,
                         source_x:connInfo.sourceEndpoint.anchor.x
                       },
                       target:{
                         targetId:connInfo.targetId,
                         target_x:connInfo.targetEndpoint.anchor.x
                       }

                     };
                       _that.relation.push(relationList);
                   }else{
                     alert("不能连接自己");
                     connInfo.connection.toggleType("basic");
                   }
                 });
                 //不能连接自己
             instance.bind('beforeDrop', function (conn) {
                   if (conn.sourceId === conn.targetId) {
                     return false
                   } else {
                     return true
                   }
               });
             instance.bind("beforeDetach", function (conn) {
                   for(let i=0;i<_that.relation.length;i++){
                       if(_that.relation[i].source.sourceId==conn.sourceId&&_that.relation[i].target.targetId==conn.targetId){
                         _that.relation.splice(i,1)
                       }
                   }
                 });
           });
           instance.draggable($("#canvas"),{grid: [0, 0] });
           jsPlumb.fire("jsPlumbDemoLoaded", instance);

         });
       },
       //点击运行按钮
       startClick:function () {
           let _that=this;
          for(let i=0;i<_that.nodeInfoList.length;i++){
          _that.nodeInfoList[i].path={
              targetIdList:[],
              sourceIdList:[]
             }
          }
           //计算节点有多少指向目标targetIdList
           for(let i=0;i<_that.relation.length;i++){
             for(let j=0;j<_that.nodeInfoList.length;j++){
               if(_that.nodeInfoList[j].domID==_that.relation[i].source.sourceId){
                 _that.nodeInfoList[j].path.targetIdList.push(_that.relation[i].target)
               }
             }
           }
         //计算节点有多少被指向目标 sourceIdList
         for(let i=0;i<_that.relation.length;i++){
           for(let j=0;j<_that.nodeInfoList.length;j++){
             if(_that.nodeInfoList[j].domID==_that.relation[i].target.targetId){
               _that.nodeInfoList[j].path.sourceIdList.push(_that.relation[i].source)
             }
           }
          }
         //判断节点头和节点尾
         let start=[];
         let end=[];
         let never=[];
         for(let i=0;i<_that.nodeInfoList.length;i++){
           if((_that.nodeInfoList[i].path.sourceIdList.length==0)&&(_that.nodeInfoList[i].path.targetIdList.length>0)){
             start.push(_that.nodeInfoList[i]);
           }
           if((_that.nodeInfoList[i].path.targetIdList.length==0)&&(_that.nodeInfoList[i].path.sourceIdList.length>0)){
             end.push(_that.nodeInfoList[i])
           }
           if((_that.nodeInfoList[i].path.targetIdList.length==0)&&(_that.nodeInfoList[i].path.sourceIdList.length==0)){
             never.push(_that.nodeInfoList[i])
           }
         }
         //获取每个节点的位置；
        let dom=document.querySelectorAll('.window');
         for(let i=0;i<dom.length;i++){
             for(let j=0;j<_that.nodeInfoList.length;j++){
               if(_that.nodeInfoList[j].domID==dom[i].id){
                 let nodePOS={
                   left:dom[i].style.left,
                   top:dom[i].style.top,
                 };
                 _that.nodeInfoList[j].nodePOS=nodePOS;
               }
             }
         }
/*
        let runData={};
         runData.jobName=_that.ruleForm.jobName
         runData.account=_that.ruleForm.account;
         runData.description=_that.ruleForm.description;
         runData.graphmodel=JSON.stringify(_that.nodeInfoList);
         runData.graphxml=_that.graphNodeXml();*/

         let runData= new FormData();
         runData.append('jobName',_that.ruleForm.jobName);
         runData.append('account',_that.ruleForm.account);
         runData.append('description',_that.ruleForm.description);
         runData.append('graphmodel',JSON.stringify(_that.nodeInfoList));
         runData.append('graphxml',_that.graphNodeXml());
         console.log(_that.graphNodeXml())
         let jobName=_that.ruleForm.jobName;
        if(jobName!=''){
           _that.axios.post("/ueSparkMLSys/jobAction!submit.action",runData).then(function(data) {
               let obj=data.data;
              if(data.status==true||data.status==200){
                   if(obj!=null){
                       _that.uploadFileClick();
                     _that.btnStatus.startBtn=true;
                     _that.btnStatus.clearBtn=true;
                     _that.btnStatus.cloneBtn=true;
                     _that.btnStatus.stopBtn=false;
                     let jobdata=new FormData();
                     jobdata.append('jobId',obj.object.jobId);
                     _that.jobItemPost(jobdata);
                   }else{
                     Message({message: '无数据返回', type: 'error'})
                   }

               }else{
                 Message({message: '无数据返回', type: 'error'})
               }
           }, function(error) {
               console.log(error)
             Message({message: '系统异常,请重试', type: 'error'})
           });
         }else{
            Message({message: '请添加项目名', type: 'warning'})
         }


       },
       //刷新页面
       deletNodes:function (id) {
           let _that=this;
           for(let i=0;i<_that.nodeInfoList.length;i++) {
             if(_that.nodeInfoList[i].domID==id){
               _that.nodeInfoList.splice(i,1)
             }
           }
           for(let i=0;i<_that.nodeInfoList.length;i++) {
             for(let z=0;z<_that.nodeInfoList[i].path.targetIdList.length;z++){
               if(_that.nodeInfoList[i].path.targetIdList[z].targetId==id){
                 _that.nodeInfoList[i].path.targetIdList.splice(z,1)
               }
             }
             for(let s=0;s<_that.nodeInfoList[i].path.sourceIdList.length;s++){
               if(_that.nodeInfoList[i].path.sourceIdList[s].sourceId==id){
                 _that.nodeInfoList[i].path.sourceIdList.splice(s,1)
               }
             }
           }
       },
       //删除节点
       clearClick:function () {
         let _that=this;
         _that.nodeInfoList=[];
         _that.nodeInfo=null;
         _that.jsPlumbDatas.instance.deleteEveryEndpoint();
         $("#canvas").empty();
         _that.timer=null;
         clearInterval(_that.timer);
       },
       //停止按钮
       stopClick:function () {
           let _that=this;
           if(this.ruleForm.jobId!=''){
             let job={
               jobId:this.ruleForm.jobId
             };
             _that.axios.post("/ueSparkMLSys/jobAction!killByBdajobID.action",job).then(function(data) {
                 clearInterval(_that.timer);
             }, function(error) {
               Message({message: '系统异常,请重试', type: 'error'})
             });
           }

       },
       //点击左侧导航栏
       menuItemClick:function (item,index) {
          let _that=this;
          if( _that.clickIndex==true){
            _that.nodeInfoList=[];
            _that.nodeInfo=null;
            _that.jsPlumbDatas.instance.deleteEveryEndpoint();
            $("#canvas").empty();
            _that.timer=null;
            clearInterval(_that.timer);
          }
          _that.clickIndex=false;
         _that.jobDataAll=null;
         if(!item.hasChild){
           if(_that.leftMenuIndex==0){
             //按钮状态改变
             _that.btnStatus.startBtn=false;
             _that.btnStatus.clearBtn=false;
             _that.btnStatus.stopBtn=true;
             _that.btnStatus.cloneBtn=true;
             let ids = new FormData();
             ids.append('id',item.id);
             _that.axios.post("/ueSparkMLSys/programAction!getProgramById.action",ids).then(function(data) {
                 let obj=data.data;
               if(data.status==true||data.status==200||data.status=='OK'||data.status=='ok'){
                 obj.domID='flowchartWindow'+ _that.numID;
                 _that.nodeInfo=obj;
                 _that.addNode(item,index,_that.nodeInfo,_that.leftMenuIndex);
                 _that.nodeInfoList.push(obj);
                 _that.numID++;
               }else{
                 Message({message:data.message, type: 'error'})
               }
             }, function(error) {
               Message({message: '系统异常,请重试', type: 'error'})
             });
           }else if(_that.leftMenuIndex==1){
             //按钮状态改变
             _that.btnStatus.startBtn=false;
             _that.btnStatus.clearBtn=false;
             _that.btnStatus.stopBtn=true;
             _that.btnStatus.cloneBtn=true;
             let ids = new FormData();
             ids.append('id',item.id);
             _that.axios.post("/ueSparkMLSys/dataSetAction!getDataSetById.action",ids).then(function(data) {
                 let obj=data.data;
               if(data.status==true||data.status==200||data.status=='OK'||data.status=='ok'){
                 obj.domID='flowchartWindow'+ _that.numID;
                 _that.nodeInfo=obj;
                 _that.addNode(item,index,_that.nodeInfo,_that.leftMenuIndex);
                 _that.nodeInfoList.push(obj);
                 _that.numID++;
               }else{
                 Message({message:data.message, type: 'error'})
               }



             }, function(error) {
               Message({message: '系统异常,请重试', type: 'error'})
             });
           }else if(_that.leftMenuIndex==2){
             _that.btnStatus.startBtn=true;
             _that.btnStatus.clearBtn=true;
             _that.btnStatus.cloneBtn=false;
            let jobdata = new FormData();
             jobdata.append('jobId',item.jobId);
             _that.jobItemPost(jobdata)
             _that.clickIndex=true;
           }


         }
       },
       //点击job目录下的工作流
       jobItemPost:function (jobdata) {
         let _that=this;
         _that.axios.post("/ueSparkMLSys/jobAction!getJobByid.action",jobdata).then(function(data) {
           let obj=data.data;
           if(data.status==200||data.status==true||data.status=='ok'||data.status=='OK'){
             _that.ruleForm.account=obj.object.bdaJob.account;
             _that.ruleForm.description=obj.object.bdaJob.description;
             let model=JSON.parse(obj.object.bdaJob.graphmodel);
             _that.jobClick(model);
             _that.ruleForm.jobId=obj.object.bdaJob.jobId;
             _that.ruleForm.jobName=obj.object.bdaJob.jobName;
             _that.ruleForm.stratTime=obj.object.bdaJob.lastSubmitTime;
             _that.jobDataAll=obj;
             clearInterval(_that.timer);
             _that.getjobStatusStyle(obj.object,jobdata);
           }else if(obj==null||obj==undefined||obj==''){
             Message({message:'暂无数据', type: 'error'})
           }else{
             Message({message:data.message, type: 'error'})
           }
         }, function(error) {
              console.log(error);
           Message({message:error, type: 'error'})
         });
       },
       //得到流程状态并改变样式
       getjobStatusStyle:function (data,jobdata) {
         let _that=this;
         let domClass=$('.window');
         $(domClass).removeClass('successStyle');
         $(domClass).removeClass('alertStyle');
         $(domClass).removeClass('waitStyle');
         let jobStatus=data.jobStatus;
         let actionStatus=data.actionStatus;
         _that.ruleForm.itemProgress=jobStatus;
         if(jobStatus=="SUCCEEDED"){
           _that.btnStatus.stopBtn=true;
           for(let key in actionStatus){
             if(actionStatus[key]=="OK"&&(key!=':start:')&&(key!='end')){
               if($(domClass).hasClass('modelType')){
                 let dom="#flowchartWindow"+key.split('-')[key.split('-').length-1];
                 $(dom).addClass('successStyle');
               }
             }
           }
         }else if(jobStatus=="KILLED"){
           _that.btnStatus.stopBtn=true;
           for(let key in actionStatus){
               if((key!=':start:')&&(key!='end')&&$(domClass).hasClass('modelType')){
                 let dom="#flowchartWindow"+key.split('-')[key.split('-').length-1];
                 if(actionStatus[key]=='ERROR'){
                     $(dom).addClass('alertStyle');
                 }else if((actionStatus[key]=='OK')){
                     $(dom).addClass('successStyle');
                 }else if((actionStatus[key]=="KILLED")){
                     $(dom).addClass('alertStyle');
                 }
               }

           }

         }else if(jobStatus=="RUNNING"){
           _that.btnStatus.stopBtn=false;
            _that.runStatusFn(actionStatus,jobdata)
         }

       },
       //运行流程状态
       runStatusFn:function (actionStatus,jobdata) {
         let _that=this;
         _that.intervalFn(actionStatus,jobdata);
         _that.timer=setInterval(function(){
            _that.intervalFn(actionStatus,jobdata);
         },2000);
       },
       //监听流程状态
       intervalFn:function (actionStatus,jobdata) {
         let _that=this;
         _that.axios.post("/ueSparkMLSys/jobAction!getStatueByBdajobID.action",jobdata).then(function(data) {
          let obj=data.data;
          if(data.status==true||data.status==200||data.status=='OK'||data.status=='ok'){
            let jobStatus=obj.object.jobStatus;
            if(jobStatus=="RUNNING"){
              _that.btnStatus.stopBtn=false;
              for(let i in obj.object.actionStatus){
                let action=obj.object.actionStatus[i];
                if((i!=':start:')&&(i!='end')){
                  let dom="#flowchartWindow"+i.split('-')[i.split('-').length-1];
                  if($(dom).hasClass('modelType')){
                    if(action=='OK'){
                      $(dom).removeClass('waitStyle');
                      $(dom).addClass('successStyle');
                    }else if(action=='ERROR'){
                      $(dom).removeClass('waitStyle');
                      $(dom).addClass('alertStyle');
                    }else if(action=='RUNNING'){
                      $(dom).addClass('waitStyle');
                    }
                  }
                }
              }
            }else if(jobStatus=="SUCCEEDED"){
              _that.btnStatus.clearBtn=false;
              _that.btnStatus.cloneBtn=false;
              _that.btnStatus.stopBtn=true;
              _that.ruleForm.itemProgress=jobStatus;
              let domClass=$('.window');
              domClass.each(function (item,i) {
                if($(this).hasClass("modelType")){
                  $(this).removeClass('alertStyle');
                  $(this).removeClass('waitStyle');
                  $(this).addClass('successStyle');
                }
              });
              Message({message: '运行成功', type: 'success'})
              clearInterval(_that.timer);
            }else if(jobStatus=="KILLED"){
              _that.btnStatus.clearBtn=false;
              _that.btnStatus.cloneBtn=false;
              _that.btnStatus.stopBtn=true;
              let domClass=$('.window');
              for(let key in obj.object.actionStatus){
                if((key!=':start:')&&(key!='end')&&$(domClass).hasClass('modelType')){
                  let dom="#flowchartWindow"+key.split('-')[key.split('-').length-1];
                  if(obj.object.actionStatus[key]=='ERROR'){
                    $(dom).removeClass('waitStyle');
                    $(dom).addClass('alertStyle');
                  }else if((obj.object.actionStatus[key]=='OK')){
                    $(dom).removeClass('waitStyle');
                    $(dom).addClass('successStyle');
                  }else if((data.object.actionStatus[key]=="KILLED")){
                    $(dom).removeClass('waitStyle');
                    $(dom).addClass('alertStyle');
                  }
                }

              }
              clearInterval(_that.timer);
            }else{
              Message({message:data.message, type: 'error'})
            }
          }

         }, function(error) {
           clearInterval(_that.timer);
           Message({message: '系统异常,请重试', type: 'error'})
         })
       },
       //复制节点
       cloneClick:function () {
           let _that=this;
          // _that.jsPlumbDatas.instance.clear();
           _that.nodeInfoList=[];
           _that.relation=[];
           _that.nodeInfo=null;
           _that.clickIndex=false;
           _that.jsPlumbDatas.instance.deleteEveryEndpoint();
           $("#canvas").empty();
         if(this.ruleForm.jobId!=''){
           _that.btnStatus.startBtn=false;
           _that.btnStatus.clearBtn=false;
           _that.btnStatus.cloneBtn=true;
           _that.btnStatus.stopBtn=true;
           let jobdata = new FormData();
           jobdata.append('jobId',this.ruleForm.jobId);
           //let jobdata={jobId:this.ruleForm.jobId};
          _that.axios.post("/ueSparkMLSys/jobAction!getJobByid.action",jobdata).then(function(data) {
               let obj=data.data;
             if(data.status==true||data.status==200){
                _that.ruleForm.account='';
               _that.ruleForm.description='';
               _that.ruleForm.jobId='';
               _that.ruleForm.jobName='';
               _that.ruleForm.stratTime='';
               _that.ruleForm.itemProgress='';
               let model=JSON.parse(obj.object.bdaJob.graphmodel);
               _that.jobClick(model,'copy');
             }else{
               Message({message:data.message, type: 'error'})
             }
           }, function(error) {
             Message({message: '系统异常,请重试', type: 'error'})
           });
         }

       },
       //清除
       refreshClick:function () {
         location.reload();
       },
       //添加节点
       addNode:function (item,index,data,type) {
         let _that=this;
         let inputIn=[];
         let inputOut=[];
         let str='';
         if(type==0){
           let commandLineArr=data.commandLine.split('--').slice(2);
           commandLineArr.forEach(function (value,index) {
             if(value.indexOf('{in:')>0){
               inputIn.push(value)
             }else if(value.indexOf('{out:')>0){
               inputOut.push(value)
             }
           });
           str += '<div class="window jtk-node modelType" id="flowchartWindow' + _that.numID+ '"  name="'+item.id+'"><div class="node-delete"></div>'+item.name+'</div>';
         }else if(type==1){
           inputIn=[];
           inputOut=[item.name];
           str += '<div class="window jtk-node dataSourceType" id="flowchartWindow' + _that.numID+ '"  name="'+item.id+'"><div class="node-delete"></div><span>'+item.name+'</span></div>';
            data.file=data.path;
         }
         $("#canvas").append(str);
         let d=document.getElementById('flowchartWindow'+_that.numID);
         d.style.left =parseInt(_that.randomNumBoth(10,500)) + "px";
         d.style.top =parseInt(_that.randomNumBoth(10,500)) + "px";
         let dom=jsPlumb.getSelector(".flowchart-demo .window");
         _that.jsPlumbDatas.instance.draggable(dom[dom.length-1]);
         _that.jsPlumbDatas._docNum(_that.numID,inputOut.length,inputIn.length,_that.jsPlumbDatas.instance);
        //添加位置和input名字
           data.inputOut=inputOut;
           data.inputIn=inputIn;
           data.outPostion=_that.docPostion(data.inputOut.length);
           data.inPostion=_that.docPostion(data.inputIn.length);
           data.path={
             targetIdList:[],
             sourceIdList:[]
           };
           data.nodePOS= {
             left: d.style.left,
             top: d.style.top
           }

       },
       //将节点数转换为坐标数组
       docPostion:function (num) {
         let  outPostion=[];
         for(let i=0;i<num;i++){
           if(num==1){
             outPostion.push(0.5)
           }else{
             outPostion.push(i/(num-1))
           }
         }
         return  outPostion
       },
       //生成流程图
       jobClick:function (data,par) {
             let _that=this;
           _that.nodeInfoList=[];
           _that.nodeInfo=null;
           _that.jsPlumbDatas.instance.deleteEveryEndpoint();
           $('#canvas').empty();
           _that.nodeInfoList=data;
           _that.numID=parseInt(_that.nodeInfoList[_that.nodeInfoList.length-1].domID.slice(15))+1;
           for(let i=0;i<_that.nodeInfoList.length;i++) {
             let str = '';
             let delNode='';
             if(par=='copy'){
               delNode='<div class="node-delete"></div>';
             }
             if(!_that.nodeInfoList[i].file){
               str += '<div class="window jtk-node modelType" id="' + _that.nodeInfoList[i].domID + '"  name="' + _that.nodeInfoList[i].id + '">'+delNode+ _that.nodeInfoList[i].name + '</div>';
             }else{
               str += '<div class="window jtk-node dataSourceType" id="' + _that.nodeInfoList[i].domID + '"  name="' + _that.nodeInfoList[i].id + '">'+delNode+'<span>' + _that.nodeInfoList[i].name + '</span></div>';
             }
             $("#canvas").append(str);
             let d = document.getElementById(_that.nodeInfoList[i].domID);
             d.style.left = _that.nodeInfoList[i].nodePOS.left;
             d.style.top = _that.nodeInfoList[i].nodePOS.top;
             let dom = jsPlumb.getSelector(".flowchart-demo .window");
             if(par=='copy'){
               _that.jsPlumbDatas.instance.draggable(dom[dom.length - 1]);
             }
             _that.jsPlumbDatas._docNum(_that.nodeInfoList[i].domID.slice(15), _that.nodeInfoList[i].inputOut.length, _that.nodeInfoList[i].inputIn.length, _that.jsPlumbDatas.instance);
             //连线
           }
           for(let j=0;j<_that.nodeInfoList.length;j++) {
             let targetIdList=_that.nodeInfoList[j].path.targetIdList;
             for(let x=0;x<targetIdList.length;x++){
               let targetId=targetIdList[x].targetId;
               let targetX=targetIdList[x].target_x;
               for(let s=0;s<_that.nodeInfoList.length;s++){
                 if(_that.nodeInfoList[s].domID==targetId){
                   let sourceIdList=_that.nodeInfoList[s].path.sourceIdList;
                   for(let m=0;m<sourceIdList.length;m++){
                     if(sourceIdList[m].sourceId==_that.nodeInfoList[j].domID){
                       let sourceId=sourceIdList[m].sourceId;
                       let sourceX=sourceIdList[m].source_x;
                       let reattach=true;
                       if(par=='copy'){
                         reattach=false;
                       }
                       _that.jsPlumbDatas.instance.connect({
                         source:sourceId,
                         target:targetId,
                         detachable:true,
                         reattach:reattach,
                         endpoints:["Dot", "Blank"],
                         endpointStyle:{fill:"transparent",radius:3},
                         anchors:[[sourceX,1, 0, 1 ], [targetX,  0, 1, 0]],
                         connector:["Bezier", { curviness:1}],
                         paintStyle: { stroke: "#509ff0", strokeWidth: 1,cornerRadius: 5},
                       });
                     }

                   }
                 }
               }
             }

           }
       },
       //转换xml
       graphNodeXml:function () {
         let _that=this;
//edge
          let edgeList=[];
          for(let i=0;i<_that.relation.length;i++){
             let edge={
               source:'',
               destination:'',
             };
           for(let j=0;j<_that.nodeInfoList.length;j++){
             let nameId='';
             if(_that.nodeInfoList[j].file){
               nameId=_that.nodeInfoList[j].name
             }else{
               let lineSplit=_that.nodeInfoList[j].commandLine.split('--');
               let splitArr=lineSplit[1].split(/\s+/)[1].split(".");
               let idName=splitArr[splitArr.length-1];
               nameId=idName
             }
             let idArr=_that.nodeInfoList[j].id.split("-");
             let lastId=idArr[idArr.length-1];
             if(_that.nodeInfoList[j].domID==_that.relation[i].source.sourceId){

               edge.source=nameId+'-'+lastId+'-'+_that.nodeInfoList[j].domID.slice(15)+':'+_that.nodeInfoList[j].outPostion.indexOf(_that.relation[i].source.source_x)
             }
             if(_that.nodeInfoList[j].domID==_that.relation[i].target.targetId){
               edge.destination=nameId+'-'+lastId+'-'+_that.nodeInfoList[j].domID.slice(15)+':'+_that.nodeInfoList[j].inPostion.indexOf(_that.relation[i].target.target_x)
             }

           }
           edgeList.push(edge)
         }

         let edgeListXml='';
         for(let index=0;index<edgeList.length;index++){
             if(edgeList[index].source!=''&&edgeList[index].destination!=''){
               edgeListXml+=`  <edge>\n    <source>${edgeList[index].source}</source>\n    <destination>${edgeList[index].destination}</destination>\n  </edge>\n`
             }

         }

             //widget
          let widgetList=[];
         for(let index=0;index<_that.nodeInfoList.length;index++){
            let widget={};
               widget.moduleId=_that.nodeInfoList[index].id;
               widget.x=_that.nodeInfoList[index].nodePOS.left;
               widget.y=_that.nodeInfoList[index].nodePOS.top;
             if(_that.nodeInfoList[index].file){
               widget.type="dataset";
               widget.file=_that.nodeInfoList[index].file;
               let idArr=_that.nodeInfoList[index].id.split("-");
               let lastId=idArr[idArr.length-1];
               widget.id=_that.nodeInfoList[index].name+'-'+lastId+'-'+_that.nodeInfoList[index].domID.slice(15);
             }else{
               widget.type="program";
               widget.oozJob="latest";
               widget.is_distributed=true;
               widget.is_standalone_script=false;
               let commandLineSplit=_that.nodeInfoList[index].commandLine.split('--');
               let spark=commandLineSplit[0]+'--'+commandLineSplit[1];
               let paramStr=_that.paramStr(_that.nodeInfoList[index]);

               let input=_that.inputVal(_that.nodeInfoList[index],_that.nodeInfoList);

               let splitArr=commandLineSplit[1].split(/\s+/)[1].split(".");
               let idName=splitArr[splitArr.length-1];
               let idArr=_that.nodeInfoList[index].id.split("-");
               let lastId=idArr[idArr.length-1];
               widget.id=idName+'-'+lastId+'-'+_that.nodeInfoList[index].domID.slice(15);

               widget.work_path='${appPath}/'+widget.id;
              let outputFile='';
                 if(_that.nodeInfoList[index].inputOut.length==1){
                   let outsplit0=_that.nodeInfoList[index].inputOut[0].split(":");

                   widget.file=escape(outsplit0[outsplit0.length-1]).replace(/%/g, "y" );

                   outputFile='--'+_that.nodeInfoList[index].inputOut[0].split("{")[0]+' '+widget.work_path+'/'+widget.file+' ';
                 }else if(_that.nodeInfoList[index].inputOut.length>1){
                   let inputOut=_that.nodeInfoList[index].inputOut;
                   widget.file=[];
                   for(let i=0;i<inputOut.length;i++){
                     let outsplitIndex=_that.nodeInfoList[index].inputOut[i].split(":");

                     let output=escape(outsplitIndex[outsplitIndex.length-1]).replace(/%/g, "y" );

                     outputFile+='--'+_that.nodeInfoList[index].inputOut[i].split("{")[0]+' '+widget.work_path+'/'+output+' ';
                     widget.file.push(output)
                   }
                 }
               widget.cmd_line=spark+input+outputFile+paramStr;
             }
           widgetList.push(widget);

         }
         return `<graph>\n${_that.widgetListFn(widgetList)}${edgeListXml}</graph>`
       },
       //转换xml
       widgetListFn:function (widgetList) {

         let widgetStr='';
         for(let s=0;s<widgetList.length;s++){
           if(widgetList[s].type=='dataset'){
             widgetStr+=`  <widget type='${widgetList[s].type}'>\n    <id>${widgetList[s].id}</id>\n    <moduleId>${widgetList[s].moduleId}</moduleId>\n    <x>${widgetList[s].x}</x>\n    <y>${widgetList[s].y}</y>\n    <file>${widgetList[s].file}</file>\n  </widget>\n`
           }else if(widgetList[s].type=='program'){
             let file='';
            if(typeof widgetList[s].file=="string"){
               file='\n    <file>'+widgetList[s].file+'</file>'
             }else{
               if(widgetList[s].file){
                 for(let j=0;j<widgetList[s].file.length;j++){
                   file+='\n    <file>'+widgetList[s].file[j]+'</file>'
                 }
               }
             }
             widgetStr+=`  <widget type='${widgetList[s].type}'>\n    <id>${widgetList[s].id}</id>\n    <moduleId>${widgetList[s].moduleId}</moduleId>\n    <oozJob>${widgetList[s].oozJob}</oozJob>\n<x>${widgetList[s].x}</x>\n    <y>${widgetList[s].y}</y>\n    <work_path>${widgetList[s].work_path}</work_path>${file}\n    <cmd_line>${widgetList[s].cmd_line}</cmd_line>\n    <is_distributed>${widgetList[s].is_distributed}</is_distributed>\n    <is_standalone_script>${widgetList[s].is_standalone_script}</is_standalone_script>\n  </widget>\n`
           }
          }
         return widgetStr
       },
       //转换xml
       paramStr:function (obj) {
         let paramStr='';
         if(obj.commandLine){
           let commandLineArr= obj.commandLine.split('--');
           let parameterInput=[];
           commandLineArr.forEach(function (value, index) {
             if ((value.indexOf('{in:') == -1) && (value.indexOf('{out:') == -1) && index != 0 && index != 1) {
               let headStr = value.split('[')[0];
               let nextStr = value.split('[')[value.split('[').length - 1];
               let nextfristStr = nextStr.split(",");
               let lastStr = nextfristStr[nextfristStr.length - 1];
               let andStr = lastStr.split(']');
               let andStrs = headStr + andStr[0];
               parameterInput.push(andStrs)
             }
           });
           parameterInput.forEach(function (item,i) {
             paramStr+=' --'+item
           });
         }else{
           paramStr=''
         }

         return  paramStr;
       },
       //转换入口值
       inputVal:function (obj,objs) {
         let valIn='';
         let _that=this;
         let sourceIdList = obj.path.sourceIdList;
         for (let index = 0; index <sourceIdList.length; index++) {
             let source = sourceIdList[index];
             let sourceId =source.sourceId;//dom2
             let sourceX = source.source_x;
             let strval='';
             for(let j=0; j<objs.length; j++){
               let currentObj = objs[j];
               if (currentObj.domID == sourceId) {
                 let sourcePath = _that.$argumentPath(currentObj);
                 let outPostion = currentObj.outPostion;
                 let position = outPostion.indexOf(sourceX);
                 strval = sourcePath[position];
                 let curtargetIdList =currentObj.path.targetIdList;
                 for (let m = 0; m < curtargetIdList.length; m++) {
                   let curtargetId = curtargetIdList[m];
                   if (curtargetId.targetId == obj.domID) {
                     let curtargetX = curtargetId.target_x;
                     let inPostion = obj.inPostion;
                     let curPosition = inPostion.indexOf(curtargetX);
                     let curName = obj.inputIn[curPosition];
                     valIn +=` `+'--'+curName.split("{")[0]+` `+strval+` `;
                   }
                 }
               }
             }
           }


         return valIn
       },
       //转换xml

       //生成随机数范围
       randomNumBoth:function (Min,Max){
        let Range = Max - Min;
        let Rand = Math.random();
        let num = Min + Math.round(Rand * Range);
        return num;
       }

     }
   }
</script>

<style>
.el-input,
.el-textarea,
.el-date-editor.el-input{
  width:175px;

}
.el-input__inner{
  height:25px !important;
}
.el-menu--dark{
  background-color: #e1e9f2;
}
.el-menu--dark .el-menu-item, .el-menu--dark .el-submenu__title{
  color:#444;
}
.el-submenu__title{
  background-color:#d6dde6;
  border-bottom:1px solid #e1e9f2;
}
.el-menu-item, .el-submenu__title{
  height: 30px;
  line-height: 30px;
 }
.flowchart-demo .window.modelType{
  width:170px;
  height:30px;
  border:1px solid #509ff0;
  border-radius: 15px;
  box-shadow: 2px 2px 19px #aaa;
  -o-box-shadow: 2px 2px 19px #aaa;
  -webkit-box-shadow: 2px 2px 19px #aaa;
  -moz-box-shadow: 2px 2px 19px #aaa;
}
.intellLaboratory #lableText{
  position:fixed;
  padding: 5px;
  z-index: 1000;
  background: #fff;
  border: 2px solid #509ff0;
}
</style>
