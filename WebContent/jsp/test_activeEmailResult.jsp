<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活邮箱 结果页面</title>
</head>
<body>
        <%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());out.print("</br>");
        %></br>
        
激活邮件已发送: <%=member.getEmail() %></br>
请查收.

</body>
</html>