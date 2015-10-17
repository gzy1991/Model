<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>teacher添加或修改邮箱</title>

 <h1>邮箱管理</h1><hr/>
</head>
<body>
        <%Teacher teacher=(Teacher)session.getAttribute("MEMBER_CONTEXT"); 
        %></br>

        <%if(teacher.getEmail()==null||teacher.getEmail().equals("")) 
        	{out.print("sorry，您当前未设置邮箱！<hr/>");}
        else{
        	out.println("您当前设置的邮箱是："+teacher.getEmail());
        	out.print("</br>");
        	out.print("邮箱的激活状态是：");
        	if(teacher.getEmail_active()==null||teacher.getEmail_active().equals("0")){
        		out.print("未激活");
        	}
        	else {
        		out.print("已激活");
        	}
        }
        %>
        <hr/>
  <form action="../teacher/setEmail" class="email" method="POST">
       设置新邮箱：<input type="text" name="teacherEmail" placeholder=<%=teacher.getEmail()%>  ><br/> 
      
  <input type="submit" name="submit" value="设置" />   
  </form>

  </body>
  </html>