<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活邮箱</title>
</head>
<body>
<h1>激活邮箱</h1> <hr/>
        <%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());out.print("</br>");
        %></br><hr/>

        <%if(member.getEmail()==null||member.getEmail().equals("")) 
        	{out.print("sorry，您当前未设置邮箱！无法激活");}
        else{
        	out.println("已设置的邮箱："+member.getEmail());
        	out.print("邮箱的激活状态是：");
        	if(member.getEmail_active()==null||member.getEmail_active().equals("0")){
        		out.print("未激活."+"</br>"+"你可以点击下面的“发送激活邮件”按钮，进行激活！");
        	%>
        	<hr/>
        	<form action="../member/activeEmail_send" class="email" method="POST">
                                       发送激活邮件：<input type="submit" name="submit" value="发送激活邮件 " />   
            </form>
        	<% }
        	else {
        		out.print("已激活，无需重复激活");
        	}
        }
        %>

</body>
</html>