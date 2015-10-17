<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加或修改邮箱</title>

 
</head>
<body>
<h1>设置邮箱</h1> <hr/>
<%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());
        %></br>
        <%if(member.getEmail()==null||member.getEmail().equals("")) 
        	{out.print("sorry，您当前未设置邮箱！");}
        else{
        	out.println("您当前设置的邮箱是："+member.getEmail());
        	out.print("</br>");
        	out.print("邮箱的激活状态是：");
        	if(member.getEmail_active()==null||member.getEmail_active().equals("0")){
        		out.print("未激活");
        	}
        	else {
        		out.print("已激活");
        	}
        }
        %>
        <hr/>
  <form action="../member/setEmail" class="email" method="POST">
       设置新邮箱：<input type="text" name="user_email" placeholder=<%=member.getEmail()%>  ><br/>  <hr/>      
  <input type="submit" name="submit" value="保存" />   
  </form>

  </body>
  </html>