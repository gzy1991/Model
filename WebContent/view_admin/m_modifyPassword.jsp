<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>直接修改密码</title>
</head>
<body>
<h1>直接修改密码</h1><hr/>
        <%Admin admin=(Admin)session.getAttribute("MEMBER_CONTEXT"); 
        %></br>
        
<form action="../admin/modifyPass_1" class="Member" method="POST">
      旧密码：<input type="password" name="password"  ><br/> 

      新密码：<input type="password" name="passwordNew" ><br/> 

      重复新密码：<input type="password" name="passwordNew2" ><br/> <hr/>
  <input type="submit" name="submit" value="保存" />   
</form>

</body>
</html>