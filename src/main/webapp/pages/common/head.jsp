<%--
  Created by IntelliJ IDEA.
  User: 杨少帅
  Date: 2021/7/14
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String base = request.getScheme()+"://" +
            request.getServerName()+":"
            +request.getServerPort()
            +request.getContextPath()+"/";
    pageContext.setAttribute("basePath",base);
%>
<base href="<%=base%>>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>