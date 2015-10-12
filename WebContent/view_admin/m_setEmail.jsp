<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin添加或修改邮箱</title>

 
</head>
<body>
<%Admin admin=(Admin)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆admin的姓名:"+admin.getAdminName());
        out.print("</br>");
        out.println("已登陆admin的ID:"+admin.getAdminId());
        %></br>

  <form action="../admin/setEmail" class="email" method="POST">
       邮箱：<input type="text" name="adminEmail" placeholder=<%=admin.getEmail()%>  ><br/> 
      
  <input type="submit" name="submit" value="保存" />   
  </form>

  </body>
  </html>