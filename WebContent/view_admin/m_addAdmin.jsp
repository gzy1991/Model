<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加admin</title>
</head>
<body>
表单：添加管理员 </br>
<form action="../admin/addAdmin" class="Admin" method="POST" >
       管理员id：<input type="text" name="adminId"  ><br/> 
       管理员姓名：<input type="text" name="adminName"  ><br/>      
       管理员密码：<input type="text" name="password"  ><br/> 
       重复管理员密码：<input type="text" name="password2"  ><br/> 
       管理员添加标记：<input type="text" name="addAdminMark"  ><br/>
  <input type="submit" name="submit" value="添加" />   
</form>
        
        
</body>
</html>