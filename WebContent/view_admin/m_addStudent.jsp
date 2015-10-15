<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>添加叙述</title>
<link href="../css/MP_addPerson.css" type="text/css" rel="stylesheet">
<script src="../js/jquery-2.1.4.min.js"></script>

</head>

<body>

<h1>添加单个学生</h1>
<hr/>
   <form action="/Model/member/adduser" class="adduser" method="post" >
    <div class="formBlock">           
    	 <label for="memberId">登陆账户:</label>
    	 <input name="memberId" id="memberId" placeholder="请输入登陆账号"  class="formElement" >
     </div>
     
     <div class="formBlock">
    	 <label for="password">用户姓名:</label>
         <input name="name"  id="name"  placeholder="请输入用户姓名" class="formElement" >
     </div>
     
     <div class="formBlock">
    	 <label for="password">密码:</label>
         <input name="password"  id="password" type="password" placeholder="请输入登陆密码" class="formElement" >
     </div>
     
     <div class="formBlock">
    	 <label for="password2">确认密码:</label>
         <input name="password2" id="password2" placeholder="请输入登陆密码" type="password" class="formElement" >
     </div>

       
     </div>
     <div class="formBlock">
    	<label></label><button  type="submit" class="submit" >确认</button>
     	<button type="button" class="reset">重置</button>
     </div>
   </form>
   <script type="text/javascript">
       (function(){
		   $('.reset').click(function(){
			   $('input').val("");
			   });
		   })();
   </script>
</body>
</html>
