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
	/* background:url(../images/PowerPoint.png); */
	}
	.icon
	{
		width:20px;
		height:20px;
	}
	.bg
	{
		background:#ececec;
	}
</style>

</head>
<body>
	<div ><a href="../file/listCategory">返回课件目录</a></div>
	<div ><a href="${parent }">返回上一级</a></div>
	<div class="ul">
	<%%>
	<c:forEach items="${files}" var="file">
	<div>
	<c:if test="${file.isDirectory()}">
	<!-- <img src="../images/PowerPoint.png" class="icon"></img> -->
	<span>(文件夹)</span>
	<a class="dir" href="../file/listFile?filePath=${file.getPath()}">
	
	${file.getName()}
	</a>
	</c:if>
	<c:if test="${file.isFile() }">
	<span>(文件)</span>
	<!-- <img src="../images/file.png" class="icon"></img> -->
	<a class="file" href="../file/download?filePath=${file.getPath()}" >${file.getName()}</a>
	</c:if>
	</div>
	</c:forEach>	
	</div>
	<script type="text/javascript">
	</script>
</body>
</html>