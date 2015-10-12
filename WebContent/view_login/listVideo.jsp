<%@page import="java.io.File"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
	a
	{
	padding-left:10px;
	height:20px;
	}

</style>

</head>
<body>
	<div ><a href="../video/listCategory">返回课件目录</a></div>
	<div class="ul">
	<%File[] files=(File[])request.getAttribute("files");
	for(File file:files){
	%>
	<div>
	<a class="file" href="/Model/view_login/playVideo.jsp?uri=
	<%=(file.getParentFile().getName()+"/"+file.getName())%>"><%=file.getName() %></a>
	</div>
	<%} %>
	</div>
	<script type="text/javascript">
	</script>
</body>
</html>