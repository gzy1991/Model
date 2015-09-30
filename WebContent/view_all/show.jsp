<%@page import="java.io.File"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
	.icon
	{
	width:20px;
	height:20px;
	}
</style>
</head>
<body>
	
	<ul>
	<%%>
	<c:forEach items="${files}" var="file">
	<%File f; %>
	<li>
	 <c:if test="${file.isDirectory()}"><img src="../images/directory.png" class="icon"></img></c:if>
	 <c:if test="${file.isFile() }"><img src="../images/file.png" class="icon"></img></c:if>
	<a href="../file/show?filePath=${file.getPath()}">${file.getName()}</a>
	</li>
	</c:forEach>	
	</ul>
	<script type="text/javascript">
		document.oncontextmenu=function()
		{
			
		}
	</script>
</body>
</html>