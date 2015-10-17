<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin" import="java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>admin管理页面</title>
	<link rel="stylesheet" type="text/css" href="../css/m_admin.css">
	<script type="text/javascript"src="../js/jquery-2.1.4.min.js"></script>
</head>
<body>
        <%Admin admin=(Admin)session.getAttribute("MEMBER_CONTEXT"); %>


<div class="logo"></div>
  <div class="time">
	<span>今日时间：</span>
	<%Date dNow=new Date() ;
	SimpleDateFormat ft = 
	new SimpleDateFormat ("yyyy.MM.dd  HH:mm   E");
	out.print(  ft.format(dNow) );
	%>
  </div>
  <div class="welcome">
	<span>admin:<%=admin.getAdminName()%>您好！</span><span><a>账号设置</a></span><span><a href="../login2/doLoginOut">注销</a></span>
  </div>
<div class="tab">
	<ul>
<!-- 		<li><a href="#" target="iframe">首页</a></li> -->
<li><a id="#managerSelf" class="nav" href="m_admin_personalInfor.jsp" target="iframe">个人管理</a></li>
		<li><a id="#managerStudent" class="nav" href="/Model/member/getPage" target="iframe">学生管理</a></li>
		<li><a id="#managerTeacher" class="nav" href="/Model/teacher/getPage" target="iframe">教师管理</a></li>
		<li><a id="#managerAdmin" class="nav" href="/Model/admin/getPage" target="iframe">管理员管理</a></li>
		<li><a id="#managerNews" class="nav" href="/Model/news/getPage" target="iframe">新闻管理</a></li>
	</ul>
</div>	
<hr/>
<div class="content" id="content">
	
	<div class="menuList" id="managerStudent">
		<ul>
			<li>学生管理</li>
			<li><a href="/Model/member/getPage" class="menuListA" target="iframe">查看学生</a></li>
			<li><a href="m_addStudent.jsp" class="menuListA" target="iframe">添加学生</a></li>
			<li><a href="excelUpload.jsp" class="menuListA" target="iframe">批量添加</a></li>
			<li><a href="m_deleteStudent.jsp" class="menuListA" target="iframe">删除学生</a></li>

		</ul>
	</div>
	<div class="menuList" id="managerTeacher">
		<ul>
			<li>教师管理</li>
			<li><a href="/Model/teacher/getPage" target="iframe">查看教师</a></li>
			<li><a href="m_teacherAdd.jsp" class="menuListA"target="iframe">添加教师</a></li>
			<li><a href="m_teacherExcelUpload.jsp" class="menuListA"target="iframe">批量添加</a></li>
			
		</ul>
	</div>
	<div class="menuList" id="managerAdmin">
		<ul>
			<li>管理员管理</li>
			<li><a href="/Model/admin/getPage" target="iframe">查看管理员</a></li>
			<li><a href="m_addAdmin.jsp" class="menuListA"target="iframe">添加管理员</a></li>
			<li><a href="m_deleteAdmin.jsp" class="menuListA"target="iframe">删除管理员</a></li>
		</ul>
	</div>	
	<div class="menuList" id="managerNews">
		<ul>
			<li>新闻管理</li>
			<li><a href="/Model/news/getPage" target="iframe">查看新闻</a></li>
			<li><a href="m_newsAdd.jsp" class="menuListA"target="iframe">添加新闻</a></li>
			<li><a href="m_newsDelete.jsp"  class="menuListA"target="iframe">删除新闻</a></li>
		</ul>
	</div>	
	<div class="menuList" id="managerSelf">
		<ul>
			<li>个人管理</li>
			<li><a href="m_admin_personalInfor.jsp" target="iframe">个人信息</a></li>
			<li><a href="m_modifyPassword.jsp"target="iframe">密码管理</a></li>
			<li><a href="m_setEmail.jsp" class="menuListA"target="iframe">设置邮箱</a></li>
			<li><a href="m_activeEmail.jsp" class="menuListA"target="iframe">激活邮箱</a></li>
		</ul>
	</div>
<script type="text/javascript">
function SetWinHeight(ob) {                     //自动调整iframe高度
    var win=ob; 
    if (document.getElementById) { 
        if (win && !window.opera) { 
            if (win.contentDocument && win.contentDocument.body.offsetHeight) 
                {win.height = win.contentDocument.body.offsetHeight; }

            else if(win.Document && win.Document.body.scrollHeight) 
                {win.height = win.Document.body.scrollHeight; }
        } 
    } 
} 
 
</script>



	<iframe class="iframe" src="m_admin_personalInfor.jsp" name="iframe" scrolling="no" frameborder="no" onload="SetWinHeight(this)"></iframe>
</div>
<div class="footer">
	<div class="footerContent">
		<p>地址：大连市开发区图强街111号 &nbsp;&nbsp;&nbsp;&nbsp;邮编：116620</p>
		<p>电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email:</p>
		<p>Copyright © 2015. 软院学院实验中心（大连理工大学）. Supported by</p>
	</div>
</div>
<script type="text/javascript" src="../js/m_admin.js"></script>	
</body>
</html>