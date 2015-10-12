<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加teacher</title>
</head>
<body>
表单：添加老师 </br>
<form action="../teacher/addOne" class="Teacher" method="POST" >
       老师id：<input type="text" name="teacherId"  ><br/> 
       老师姓名：<input type="text" name="teacherName"  ><br/>      
       老师密码：<input type="password" name="password"  ><br/> 
       重复老师密码：<input type="password" name="password2"  ><br/> 
  <input type="submit" name="submit" value="添加" />   
</form>
        
        
</body>
</html>