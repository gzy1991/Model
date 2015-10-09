<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改学生信息</title>
</head>
<body>
        <%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());
        %></br>
          
            表单2：
            学生ID:<%=member.getMemberId()%></br>
            学生姓名:<%=member.getMemberName() %></br>
            登陆账户:<%=member.getLoadname() %></br>
       <form action="../member/modifyMember" class="Member" method="POST" >
      1 年级：<input type="text" name="grade" placeholder=<%=member.getGrade() %>><br/> 
      2 地址：<input type="text" name="address" placeholder=<%=member.getAddress() %>><br/> 
      3 生日：<input type="date" name="birthDate" placeholder=<%=member.getBirthDate() %>/>  
      4 班级：<input type="text" name="classNo" placeholder=<%=member.getClassNo() %>><br/> 
      5 性别：<input type="text" name="gender" placeholder=<%=member.getGender() %>><br/> 
      6 电话：<input type="text" name="mobilePhone" placeholder=<%=member.getMobilePhone() %>>
      7 专业：<input type="text" name="major" placeholder=<%=member.getMajor() %>><br/> 
      8 QQ: <input type="text" name="QQ" placeholder=<%=member.getQQ() %>><br/> 
        <input type="submit" name="submit" value="保存" />   
        </form>
</body>
</html>