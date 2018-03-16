/**
 * Created by 杨佩成 on 2017/9/7.
 */
const jsPlumbs=function () {
  jsPlumb.ready(function () {

    var instance = window.jsp = jsPlumb.getInstance({
      DragOptions: { cursor: 'pointer', zIndex: 2000 },
      ConnectionOverlays: [
        [ "Arrow", {
          location: 1,
          visible:true,
          width:11,
          length:11,
          id:"ARROW",
          events:{
            click:function() { alert("you clicked on the arrow overlay")}
          }
        } ],
        [ "Label", {
          location: 0.1,
          id: "label",
          cssClass: "aLabel",
          events:{
            tap:function() { alert("hey"); }
          }
        }]
      ],
      Container: "canvas",
      Connector : "Bezier",
    });

    var basicType = {
      connector: "StateMachine",
      paintStyle: { stroke: "red", strokeWidth: 4 },
      hoverPaintStyle: { stroke: "blue" },
      overlays: [
        "Arrow"
      ]
    };
    instance.registerConnectionType("basic", basicType);

    // this is the paint style for the connecting lines..
    var connectorPaintStyle = {
        strokeWidth: 2,
        stroke: "#61B7CF",
        joinstyle: "round",
        Connector : "Bezier",
        outlineStroke: "white",
        outlineWidth: 2
      },
      // .. and this is the hover style.
      connectorHoverStyle = {
        strokeWidth: 3,
        stroke: "#216477",
        outlineWidth: 5,
        outlineStroke: "white"
      },
      nodeNum={
        target:5,
        source:4
      },
      endpointHoverStyle = {
        fill: "#216477",
        stroke: "#216477"
      },
      // the definition of source endpoints (the small blue ones)
      sourceEndpoint = {
        endpoint: "Dot",
        paintStyle: {
          stroke: "#509ff0",
          fill: "transparent",
          radius: 4,
          strokeWidth: 1
        },
        isSource: true,
        connector: [ "Flowchart", { stub: [40, 60], gap: 10, cornerRadius: 5, alwaysRespectStubs: true } ],
        connectorStyle: connectorPaintStyle,
        hoverPaintStyle: endpointHoverStyle,
        connectorHoverStyle: connectorHoverStyle,
        dragOptions: {},
        overlays: [
          [ "Label", {
            location: [0.5, 1.5],
            label: "Drag",
            cssClass: "endpointSourceLabel",
            visible:false
          } ]
        ]
      },
      // the definition of target endpoints (will appear when the user drags a connection)
      targetEndpoint = {
        endpoint: "Dot",
        paintStyle: { fill: "#509ff0", radius: 4 },
        hoverPaintStyle: endpointHoverStyle,
        maxConnections: -1,
        dropOptions: { hoverClass: "hover", activeClass: "active" },
        isTarget: true,
        overlays: [
          [ "Label", { location: [0.5, -0.5], label: "Drop", cssClass: "endpointTargetLabel", visible:false } ]
        ]
      },
      init = function (connection) {
        connection.getOverlay("label").setLabel(connection.sourceId.substring(15) + "-" + connection.targetId.substring(15));
      };

    var _addEndpoints = function (toId, sourceAnchors, targetAnchors) {
      for (var i = 0; i < sourceAnchors.length; i++) {
        var sourceUUID = toId + sourceAnchors[i];
        instance.addEndpoint("flowchart" + toId, sourceEndpoint, {
          anchor: sourceAnchors[i], uuid: sourceUUID
        });
      }
      for (var j = 0; j < targetAnchors.length; j++) {
        var targetUUID = toId + targetAnchors[j];
        instance.addEndpoint("flowchart" + toId, targetEndpoint, {
          anchor: targetAnchors[j], uuid: targetUUID
        });
      }
    };
    var  _docNum=function(nodeNum,target,source) {
      let targetAnchors=[];
      for(let i=0;i<target;i++){
        if(target==1){
          targetAnchors.push([0.5, 1, 0, 1 ])
        }else{
          targetAnchors.push([i/(target-1), 1, 0, 1 ])
        }
      }
      let  sourceAnchors=[];
      for(let i=0;i<source;i++){
        if(source==1){
          sourceAnchors.push([0.5,  0, 1, 0])
        }else{
          sourceAnchors.push([i/(source-1), 0, 1, 0])
        }

      }
      var dom = "Window" + nodeNum;
        _addEndpoints(dom,targetAnchors,sourceAnchors);
    };
    var _nodeConnect=function (start,end) {
      instance.connect({source: "flowchartWindow"+start,target: "flowchartWindow"+end,type:"basic",scope:"someScope"} );
    };


    instance.batch(function () {

   /*  for(let i=0;i<6;i++){
       var str = '<div class="window jtk-node addList" id="flowchartWindow' + i+ '"><div class="node-delete"></div><label><i class="icon-add"></i>huhuh</label></div>'
       $("#canvas").append(str);
      instance.draggable(jsPlumb.getSelector('.flowchart-demo .window')[i]);
     }*/
      var numID=6+1;
      jsPlumb.on($('.tree li span'), "click", function(e) {
        if($(this).find('i').length==0) {
          var str = '<div class="window jtk-node addList" id="flowchartWindow' + numID+ '"><div class="node-delete"></div><label><i class="icon-add"></i>huhuh</label></div>'
          $("#canvas").append(str)
          _docNum(numID,3,2);
          var dom=jsPlumb.getSelector(".flowchart-demo .window");
          instance.draggable(dom[dom.length-1]);
          numID++;

        }

      });

   /*  for(let i=0;i<6;i++){
       instance.connect({
         source:"flowchartWindow"+i,
         target:"flowchartWindow"+(i+1),
         anchor:[ "Continuous", { faces:["top","bottom"]}],
         endpoint:[ "Dot", { radius:4, hoverClass:"myEndpointHover" } ],
         connector:[ "Bezier", { curviness:50 }],
       });
     }*/
 var relation=[];
      instance.on(document, "click", ".node-delete", function () {
        var g = this.parentNode;
        instance.remove(g.id)
      });
      instance.bind("connection", function (connInfo, originalEvent) {
        relation=[];

        init(connInfo.connection);
        let relationList={
          sourceId:connInfo.sourceId,
          targetId:connInfo.targetId,
          id:connInfo.sourceId+connInfo.targetId
        };
        relation.push(relationList)
      });
      instance.bind("click", function (conn, originalEvent) {
        conn.toggleType("basic");
      });
      instance.bind("beforeDetach", function (conn) {
      console.log(conn.sourceId)
        console.log(conn.targetId)
      });
      instance.on($('#startIdClick'), "click", function(e) {
       console.log(relation)

      });
    });
    jsPlumb.fire("jsPlumbDemoLoaded", instance);

  });

  /*
   function delNode(op,index) {
   var dom="Window"+index;
   $('#flowchart'+dom).remove();

   }*/
};

export {
  jsPlumbs,
}
