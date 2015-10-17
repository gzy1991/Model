<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
</head>
<body>
     <%
     Admin admin=(Admin) session.getAttribute("MEMBER_CONTEXT");  %></br>
     
<%--      <%if(admin!=null){
     out.println("已登陆管理员的ID:"+admin.getAdminId());
     out.print("</br>");
     out.println("已登陆管理员的姓名:"+admin.getAdminName()); }
     %></br>
 --%>     
                 处理结果：  <%=request.getParameter("ERROR_MSG_KEY")%>

</body>
</html>