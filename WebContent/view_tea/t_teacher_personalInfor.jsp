<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<hr/>
        <%Teacher teacher=(Teacher)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆teacher的姓名:"+teacher.getTeacherName());
        out.print("<hr/>");
        out.println("已登陆teacher的ID:"+teacher.getTeacherId());
        %>
        <hr/>
              邮箱：<%if(teacher.getEmail()==null||teacher.getEmail().equals("")) 
        	{out.print("sorry，您当前未设置邮箱！");}
        else{
        	out.println(teacher.getEmail());
        	out.print("</br><hr/>");
        	out.print("邮箱的激活状态是：");
        	if(teacher.getEmail_active()==null||teacher.getEmail_active().equals("0")){
        		out.print("未激活");
        	}
        	else {
        		out.print("已激活");
        	}
        }
        %>
        <hr/>出生日期：<%if(teacher.getBirthDate()!=null) out.print(teacher.getBirthDate()); %>
        <hr/>地址：<%if(teacher.getAddress()!=null) out.print(teacher.getAddress()); %>
        <hr/>性别:<%if(teacher.getGender()!=null) out.print(teacher.getGender()); %>
        <hr/>电话：<%if(teacher.getMobilePhone()!=null) out.print(teacher.getMobilePhone()); %>
        <hr/>QQ：<%if(teacher.getQQ()!=null) out.print(teacher.getQQ()); %>
        <hr/>简介：<%if(teacher.getSelfEvaluation()!=null) out.print(teacher.getSelfEvaluation()); %>
        <hr/>
<hr/>
</body>
</html>
