<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加admin结果页面</title>
</head>
<body>

        <%request.setCharacterEncoding("utf-8"); %>
        <%Admin admin=(Admin)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆admin的姓名:"+admin.getAdminName());
        out.print("</br>");
        out.println("已登陆admin的ID:"+admin.getAdminId());
        %></br>
        
       
       
       
       </br>  
                     处理结果：ERROR_MSG_KEY:   <%=request.getParameter("ERROR_MSG_KEY")%>

</body>
</html>