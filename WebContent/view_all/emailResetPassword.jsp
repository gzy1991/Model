<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮箱重置密码  </title>
</head>
<body>

    <%-- ERROR_MSG_KEY:<%=request.getParameter("ERROR_MSG_KEY") %> --%>
   
    <h1>重置密码</h1>
    <hr/>
   <form action="../email/resetPasseord_sent" class="email" method="POST">
      账户类型：<br />
      学生：<input type="radio" checked="checked" name="logintype" value="student" /><br />
      老师：<input type="radio" name="logintype" value="teacher" />  <br />
      管理员：<input type="radio" name="logintype" value="admin" />  <br /><hr/>
      账户ID：<input type="text" name="loadname"/><br/>    <hr/>
      账户绑定的邮箱：<input type="text" name="email"/><br/>        <hr/>
      发送激活邮件：<input type="submit" name="submit" value="发送重置密码的邮件 " />   
  
</form>

</body>
</html>