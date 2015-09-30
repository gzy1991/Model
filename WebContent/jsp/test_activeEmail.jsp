<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活邮箱</title>
</head>
<body>
        <%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());out.print("</br>");
        out.println("邮箱："+member.getEmail());out.print("</br>");
        out.println("邮箱状态："+member.getEmail_active()+"</br>"+"注意：0代表未激活，1代表激活");out.print("</br>");
        %></br>

          <form action="../member/activeEmail_send" class="email" method="POST">
       
                                发送激活邮件：<input type="submit" name="submit" value="发送激活邮件 " />   
  
          </form>
        

</body>
</html>