<%@page import="java.util.ArrayList"%>
<%@page import="net.gslab.entity.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	a
	{
	display:block;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<h1>课件目录</h1>
	<%
	List<String> fileList =(ArrayList<String>)request.getAttribute("fileList");
	List<String> pathList =(ArrayList<String>)request.getAttribute("pathList");
	for(int i=0;i<fileList.size();i++)
	{%>
	<a href="<%=pathList.get(i)%>">
	<%=fileList.get(i) %>
	</a>
	<%} %>
</body>
</html>