<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活邮箱</title>
</head>
<body>
        <%Teacher teacher=(Teacher)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆老师的姓名:"+teacher.getTeacherName());
        out.print("</br>");
        out.println("已登陆老师的ID:"+teacher.getTeacherId());out.print("</br>");
        out.println("邮箱："+teacher.getEmail());out.print("</br>");
        out.println("邮箱状态："+teacher.getEmail_active()+"</br>"+"注意：0代表未激活，1代表激活");out.print("</br>");
        %></br>

          <form action="../teacher/activeEmail_send" class="email" method="POST">
       
                                发送激活邮件：<input type="submit" name="submit" value="发送激活邮件 " />   
  
          </form>
        

</body>
</html>