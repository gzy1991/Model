<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
</head>
<body>
     <%
     Teacher tea=(Teacher) session.getAttribute("MEMBER_CONTEXT"); 
     out.println("已登陆老师的ID:"+tea.getTeacherId());
     out.print("</br>");
     out.println("已登陆老师的姓名:"+tea.getTeacherName()); %></br>
                 处理结果：  <%=request.getParameter("ERROR_MSG_KEY")%>
</body>
</html>