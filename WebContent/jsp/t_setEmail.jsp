<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加或修改邮箱</title>

 
</head>
<body>
<%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());
        %></br>

  <form action="../member/setEmail" class="email" method="POST">
       邮箱：<input type="text" name="user_email" placeholder=<%=member.getEmail()%>  ><br/> 
      
  <input type="submit" name="submit" value="保存" />   
  </form>

  </body>
  </html>