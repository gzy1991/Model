<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>
<form action="/Model/login/doLogin">
	用户名：<input type="text" name="loadname"/><br/>
	密    码：<input type="password" name="password"/>
	学生：<input type="radio" checked="checked" name="type" value="student" />
           老师：<input type="radio" name="type" value="teacher" />  
           管理员：<input type="radio" name="type" value="admin" />  <br />
	
	<input type="submit" value="登录"/>
	</form>
</body>
</html>