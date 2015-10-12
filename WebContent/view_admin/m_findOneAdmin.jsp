<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找单个admin</title>
</head>
<body>
      表单：查找单个管理员 </br>
<form action="../admin/findOneAdmin" class="Admin" method="POST" >
       管理员id：<input type="text" name="adminId"  ><br/> 
     
  <input type="submit" name="submit" value="添加" />   
</form>
      
</body>
</html>