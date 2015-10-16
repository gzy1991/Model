<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改学生密码 方法1 用户登陆后直接修改</title>
</head>
<body>

<%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());
        %></br>
        
<form action="../member/modifyPass_1" class="Member" method="POST">
      旧密码：<input type="password" name="password"  ><br/> 

      新密码：<input type="password" name="passwordNew" ><br/> 

      重复新密码：<input type="password" name="passwordNew2" ><br/> 
  <input type="submit" name="submit" value="保存" />   
</form>



</body>
</html>