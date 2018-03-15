<%@ page language="java" pageEncoding="UTF-8" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML >
<html>
<head>

</head>
<body>
<h2>Hello World!</h2>
<script type="text/javascript">
    function btn() {
        //  alert("0000000000"+'${basePath}');
        alert('${basePath}userAction!select');
        /* $.ajax({
         type: "POST",
         url:'/userAction!select',
         // dataType: "json",
         success : function(r) {
         var obj = jQuery.parseJSON(r);
         alert(r);
         }
         });*/


    }
    function fileUpload() {
        var fileName = document.getElementById("file").value;
        document.getElementById("pathName").value = fileName;
    }
</script>
　　<form action="fileUpload.action" method="post" enctype="multipart/form-data">
    　　
    username: <input type="text" name="username"><br>
    file: <input type="file" name="file"><br>

    <input type="submit" value="上传">
</form>
<form id="form" action="<%=basePath %>userAction!updateUserStatus.action" method="post">
    <div class="sub">
        <input type='submit' value='点击提交'/>
    </div>
</form>
<h3>upload dataset to hdfs</h3>
<form action="<%=basePath %>dataSetAction!uploadData.action" method="post" enctype="multipart/form-data">
    上传数据：<br/>
    token：<input type="text" name="TOKEN"/><br/>
    数据集名称：<input type="text" name="name"/><br/>
    数据所属目录：<select name="category">
    <option value="System Data">System Data</option>
    <option value="Shared Data">Shared Data</option>
    <option value="My Data">My Data</option>
</select><br/>
    数据格式：<select id="sex" name="storeType">
    <option value="General">General</option>
    <option value="CSV">CSV</option>
    <option value="TSV">TSV</option>
</select><br/>
    版本：<input type="text" name="version"/><br/>
    上传时间：<input type="text" name="createDate" value=<%=sdf.format(new Date())%>/><br/>
    文件所属：<input type="text" name="owner"/><br/>
    文件路径：<input type="file" name="upload" id="pathName"/>
    描述：<input type="text" name="description"/><br/>
    <input type="submit"/>
</form>
<form action="<%=basePath %>dataSetAction!uploadData1.action" method="post" enctype="multipart/form-data">
    文件路径：<input type="file" name="upload" id="pathName1"/>
    <input type="submit"/>
</form>
<%--<h3>Delete record!</h3>
<form action="<%=basePath %>dataSetAction!deleteData.action" method="post">
    <input type="text" name="id">
    <input type="submit"/>
</form>--%>

<form id="form1" action="<%=basePath %>userAction!getList.action" method="post">
    <div class="sub">
        <input type='submit' value='获取用户列表'/>
    </div>
</form>
<form id="form2" action="<%=basePath %>programAction!getList.action" method="post">
    <div class="sub">
        <input type='submit' value='获取算法列表'/>
    </div>
</form>


<form id="form3" action="<%=basePath %>categoryAction!getList.action" method="post">
    <div class="sub">
        token：<input type="text" name="TOKEN"/><br/>
        <input type='submit' value='获取目录列表'/>
    </div>
</form>


<form id="form4" action="<%=basePath %>programAction!getProgramById.action" method="post">
    <div class="sub">
        <input type="text" name="id"/>
        <input type='submit' value='根据id获取算法'/>
    </div>
</form>
<form id="form5" action="<%=basePath %>programAction!deleteProgramById.action" method="post">
    <div class="sub">
        <input type="text" name="id"/>
        <input type='submit' value='根据id删除算法'/>
    </div>
</form>
<form id="form6" action="<%=basePath %>programAction!uploadProgram.action" method="post">
    <div class="sub">
        <input type="hidden" name="id"/>
        <input type='submit' value='上传算法'/>

    </div>
</form>


<form id="form00" action="<%=basePath %>jobAction!submit.action" method="post">
    <div class="sub">
        工作流名称：<input type="text" name="jobName"/><br/>
        <%--工作流ID：<input type="text" name="jobId"/><br/>--%>
        graphxml字符串：<input type="text" name="graphxml"/><br/>
        用户名：<input type="text" name="account"/><br/>
        工作流说明：<input type="text" name="description"/><br/>
        优亿前端图:<input type="text" name="graphmodel"/><br/>
        TOKEN:<input type="text" name="TOKEN"/><br/>

        <input type="submit"/>
    </div>
</form>



<form id="form00" action="<%=basePath %>jobAction!getOozieJobstatus.action" method="post">
    <div class="sub">

        查询工作流状态：<input type="text" name="oozieJob"/><br/>

        <input type="submit"/>
    </div>
</form>



<form id="form00" action="<%=basePath %>dataSetAction!getDataset.action" method="post">
    <div class="sub">

        根据数据的id获取数据信息：<input type="text" name="id"/><br/>

        <input type="submit"/>
    </div>
</form>


<form id="form00" action="<%=basePath %>jobAction!getOozieJobById.action" method="post">
    <div class="sub">

        根据数据的id获取job信息：<input type="text" name="oozieJob"/><br/>

        <input type="submit"/>
    </div>
</form>




<form id="form00" action="<%=basePath %>jobAction!getOozieActionStatus.action" method="post">
<div class="sub">

    根据oozieJobID获取Action的执行状态：<input type="text" name="oozieJob"/><br/>

    <input type="submit"/>
</div>
</form>


<form id="form0011" action="<%=basePath %>wfBuildAction!getWFXML.action" method="post">
    <div class="sub">

        根据graphxml获取workflwo的格式：<input type="text" name="graphxml"/><br/>

        <input type="submit"/>
    </div>
</form>







<form id="form0011" action="<%=basePath %>jobAction!getStrErr.action" method="post">
<div class="sub">
    获取工作流的标准错误输出：<br/>
   OoziejobID：<input type="text" name="oozieJob"/><br/>
    <%--工作流ID：<input type="text" name="jobId"/><br/>--%>
    ActionID：<input type="text" name="actionname"/><br/>



    <input type="submit"/>
</div>
</form>




<form id="form001112" action="<%=basePath %>jobAction!getStdOut.action" method="post">
    <div class="sub">
        获取工作流的标准打印输出：<br/>
        OoziejobID：<input type="text" name="oozieJob"/><br/>
        <%--工作流ID：<input type="text" name="jobId"/><br/>--%>
        ActionID：<input type="text" name="actionname"/><br/>



        <input type="submit"/>
    </div>
</form>





<form id="form001112" action="<%=basePath %>jobAction!getStatueByBdajobID.action" method="post">
    <div class="sub">
        获取工作流的执行状态：<br/>
        bdajobid：<input type="text" name="jobId"/><br/>


        <input type="submit"/>
    </div>
</form>




<form id="form0011212" action="<%=basePath %>presentAction!droeRadar.action" method="post">
    <div class="sub">
       评估结果获取：<br/>


        <input type="submit"  value="获取评估结果"/>
    </div>
</form>



<form action="<%=basePath %>programAction!uploadHDFS.action" method="post" enctype="multipart/form-data">
    上传算法测试：<br/>

    算法名称：<input type="text" name="name"/><br/>
    所属目录： <input type="text" name="category"/><br/>
    文件路径：<input type="file" name="upload" />
    CMDLINE：<input type="text" name="commandLine"/><br/>
    <input type="submit"/>
</form>





<form  action="<%=basePath %>presentAction!getBarChart.action" method="post">
    <div class="sub">

        数据路径：<input type="text" name="path"/><br/>
        工作流Id <input type="text" name="ooziejobID"/><br/>
        <input type="submit"  value="获取线形图数据"/>
    </div>
</form>





<form  action="<%=basePath %>presentAction!preview.action" method="post">
    <div class="sub">

       数据路径：<input type="text" name="path"/><br/>
       工作流Id <input type="text" name="ooziejobID"/><br/>
        <input type="submit"  value="数据预览"/>
    </div>
</form>



<form  action="<%=basePath %>presentAction!extDataFeature.action" method="post">
    根据数据的路径、统计对应列数据的特征
    <div class="sub">

        数据路径：<input type="text" name="path"/><br/>
        数据列名称：<input type="text" name="colname"/><br/>
        <input type="submit"  value="数据统计"/>
    </div>
</form>




<form  action="<%=basePath %>presentAction!getBarChart.action" method="post">
    <div class="sub">
        获取线形图数据格式：折线图、柱状图<br/>
        数据路径：<input type="text" name="path"/><br/>
        工作流Id <input type="text" name="ooziejobID"/><br/>
        <input type="submit"  value="折线图数据"/>
    </div>
</form>



<form  action="<%=basePath %>batchUploadAction!uploadPrograrm.action" method="post">
    <div class="sub">

        <input type="submit"  value="批量上传算法到平台中"/>
    </div>
</form>





<form  action="<%=basePath %>jobAction!getJobByid.action" method="post">
    <div class="sub">

      输入需要回显jobID:  <input type="text" name="jobId"/><br/>
        <input type="submit"  value="回显数据时，使用的接口"/>
    </div>
</form>




</body>
</html>
