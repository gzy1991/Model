<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加或修改邮箱</title>

 
</head>
<body>
<%Teacher teacher=(Teacher)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆老师的姓名:"+teacher.getTeacherName());
        out.print("</br>");
        out.println("已登陆老师的ID:"+teacher.getTeacherId());
        %></br>

  <form action="../teacher/setEmail" class="email" method="POST">
       邮箱：<input type="text" name="teacherEmail" placeholder=<%=teacher.getEmail()%>  ><br/> 
      
  <input type="submit" name="submit" value="保存" />   
  </form>

  </body>
  </html>