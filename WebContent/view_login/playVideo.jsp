<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%String uri=request.getParameter("uri"); 
	uri="/Model/teaVideos/"+uri;
%>
<video width="320" height="240" controls>
	<source src="<%=uri%>" >
	<object data="<%=uri%>" width="320" height="240"></object>
</video>
</body>
</html>