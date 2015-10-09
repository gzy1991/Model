<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活邮箱</title>
</head>
<body>
        <%Admin admin=(Admin)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆admin的姓名:"+admin.getAdminName());
        out.print("</br>");
        out.println("已登陆admin的ID:"+admin.getAdminId());out.print("</br>");
        out.println("邮箱："+admin.getEmail());out.print("</br>");
        out.println("邮箱状态："+admin.getEmail_active()+"</br>"+"注意：0代表未激活，1代表激活");out.print("</br>");
        %></br>

          <form action="../admin/activeEmail_send" class="email" method="POST">
       
                                发送激活邮件：<input type="submit" name="submit" value="发送激活邮件 " />   
  
          </form>
        

</body>
</html>