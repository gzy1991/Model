<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member" import="org.springframework.web.servlet.ModelAndView" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加结果页面</title>
</head>
<body>
           <%request.setCharacterEncoding("utf-8"); %>
        <%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());
        %></br>
        
       
       
       
       </br>  
       处理结果：ERROR_MSG_KEY:   <%=request.getParameter("ERROR_MSG_KEY")%>
      
</body>
</html>