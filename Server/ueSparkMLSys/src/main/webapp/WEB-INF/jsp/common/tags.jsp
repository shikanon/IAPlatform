<%@ page language="java" pageEncoding="UTF-8"%>
<%   
  pageContext.setAttribute("context",request.getContextPath()); 
  
  response.setHeader("Pragma","No-cache"); 
  response.setHeader("Cache-Control","no-cache"); 
  response.setDateHeader("Expires", 0); 
%>

<%     
String path = request.getContextPath();     
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";     
pageContext.setAttribute("path",path);
pageContext.setAttribute("ctxStatic",path + "/static");
pageContext.setAttribute("basePath",basePath);
%>



<script>
var pageScope_context='${pageScope.context}';
var jspath = '${path}';
var basePath = '${basePath}'
</script>
