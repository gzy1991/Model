<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
</head>
<body>
     <%
     Member stu=(Member) session.getAttribute("MEMBER_CONTEXT"); 
     out.println("已登陆学生的ID:"+stu.getMemberId());
     out.print("</br>");
     out.println("已登陆学生的姓名:"+stu.getMemberName()); %></br>
     处理结果：  <%=request.getParameter("ERROR_MSG_KEY")%>

</body>
</html>