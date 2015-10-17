<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改老师信息</title>
</head>
<body>
        <%Teacher teacher=(Teacher)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆老师的姓名:"+teacher.getTeacherName());
        out.print("</br>");
        out.println("已登陆老师的ID:"+teacher.getTeacherId());
        %></br>
          
            
            老师ID:<%=teacher.getTeacherId()%></br><hr/>
            老师姓名:<%=teacher.getTeacherName() %></br><hr/>
       <form action="../teacher/changeInf" class="Teacher" method="POST" >
      1 地址：<input type="text" name="address" placeholder=<%=teacher.getAddress() %>><br/> <hr/>
      2 生日：<input type="date" name="birthDate" placeholder=<%=teacher.getBirthDate() %>/>  <hr/>
      3 性别：<input type="text" name="gender" placeholder=<%=teacher.getGender() %>><br/> <hr/>
      4 电话：<input type="text" name="mobilePhone" placeholder=<%=teacher.getMobilePhone() %>><hr/>
      5 QQ: <input type="text" name="QQ" placeholder=<%=teacher.getQQ() %>><br/> <hr/>
      6 自我介绍: <input type="text" name="selfEvaluation" placeholder=<%=teacher.getSelfEvaluation() %>><br/><hr/> 
      
        <input type="submit" name="submit" value="保存" />   
        </form>
</body>
</html>