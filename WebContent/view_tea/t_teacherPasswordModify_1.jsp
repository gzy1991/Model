<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改老师密码 方法1 用户登陆后直接修改</title>
</head>
<body>

<%Teacher teacher=(Teacher)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆老师的姓名:"+teacher.getTeacherName());
        out.print("</br>");
        out.println("已登陆老师的ID:"+teacher.getTeacherId());
        %></br>
        
<form action="../teacher/changePsw" class="Teacher" method="POST">
      旧密码：<input type="password" name="password"  ><br/> 

      新密码：<input type="password" name="passwordNew" ><br/> 

      重复新密码：<input type="password" name="passwordNew2" ><br/> 
  <input type="submit" name="submit" value="保存" />   
</form>



</body>
</html>