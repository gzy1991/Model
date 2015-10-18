<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<hr/>
<h1>账户信息</h1> <hr/>
        <%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("<hr/>");
        out.println("已登陆学生的ID:"+member.getMemberId());
        %>
        <hr/>
              邮箱：<%if(member.getEmail()==null||member.getEmail().equals("")) 
        	{out.print("sorry，您当前未设置邮箱！");}
        else{
        	out.println(member.getEmail());
        	out.print("</br><hr/>");
        	out.print("邮箱的激活状态是：");
        	if(member.getEmail_active()==null||member.getEmail_active().equals("0")){
        		out.print("未激活");
        	}
        	else {
        		out.print("已激活");
        	}
        }
        %>
        <hr/>年级：<%if(member.getGrade()!=null) out.print(member.getGrade()); %>
        <hr/>班级：<%if(member.getClassNo()!=null) out.print(member.getClassNo()); %>
        <hr/>出生日期：<%if(member.getBirthDate()!=null) out.print(member.getBirthDate()); %>
        <hr/>地址：<%if(member.getAddress()!=null) out.print(member.getAddress()); %>
        <hr/>性别:<%if(member.getGender()!=null) out.print(member.getGender()); %>
        <hr/>电话：<%if(member.getMobilePhone()!=null) out.print(member.getMobilePhone()); %>
        <hr/>QQ：<%if(member.getQQ()!=null) out.print(member.getQQ()); %>
        <hr/>简介：<%if(member.getSelfEvaluation()!=null) out.print(member.getSelfEvaluation()); %>
        <hr/>
<hr/>
</body>
</html>
