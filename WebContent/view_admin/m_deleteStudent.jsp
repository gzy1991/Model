<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除学生</title>
</head>
<body>
<h1>删除学生</h1>
 </br><hr/>
        <%Admin admin=(Admin)session.getAttribute("MEMBER_CONTEXT"); 
        %></br>

<form action="/Model/member/deleteMember" class="deleteMember" method="POST" >
     准备删除的学生的id：<input type="text" name="studentId"  ><br/> <hr/>
  <input type="submit" name="submit" value="删除" />   
</form>
        

</body>
</html>