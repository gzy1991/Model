<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<hr/>
        <%Admin admin=(Admin)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆admin的姓名:"+admin.getAdminName());
        out.print("<hr/>");
        out.println("已登陆admin的ID:"+admin.getAdminId());
        %>
        <hr/>
              邮箱：<%if(admin.getEmail()==null||admin.getEmail().equals("")) 
        	{out.print("sorry，您当前未设置邮箱！");}
        else{
        	out.println(admin.getEmail());
        	out.print("</br><hr/>");
        	out.print("邮箱的激活状态是：");
        	if(admin.getEmail_active()==null||admin.getEmail_active().equals("0")){
        		out.print("未激活");
        	}
        	else {
        		out.print("已激活");
        	}
        
        }
        
        %>
<hr/>
</body>
</html>
