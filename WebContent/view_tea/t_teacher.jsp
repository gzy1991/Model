<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Teacher" import="java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>教师管理页面</title>
	<link rel="stylesheet" type="text/css" href="../css/m_teacher.css">
	<script type="text/javascript"src="../js/jquery-2.1.4.min.js"></script>
</head>
<body>
        <%Teacher teacher=(Teacher)session.getAttribute("MEMBER_CONTEXT"); %>


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
	<span>老师:<%=teacher.getTeacherName()%> 您好！</span><span><a>账号设置</a></span><span><a href="../login2/doLoginOut">注销</a></span>
  </div>
<div class="tab">
	<ul>
<!-- 		<li><a href="#" target="iframe">首页</a></li> -->
        <li><a id="#managerSelf" class="nav" href="t_teacher_personalInfor.jsp" target="iframe">个人管理</a></li>
		<li><a id="#managerFile" class="nav" href="/Model/file/tListFile" target="iframe">教学资源管理</a></li>
		<li><a id="#managerVideo" class="nav" href="/Model/video/tList" target="iframe">视频管理</a></li>
		<li><a id="#managerNews" class="nav" href="/Model/news/getPage" target="iframe">新闻管理</a></li>
	</ul>
</div>	
<hr/>
<div class="content" id="content">
	
	<div class="menuList" id="managerFile">
		<ul>
			<li>教学资源管理</li>
			<li><a href="/Model/file/tListFile" class="menuListA"target="iframe">教学资源</a></li>
			
		</ul>
	</div>
	<div class="menuList" id="managerVideo">
		<ul>
			<li>视频管理</li>
			<li><a href="/Model/video/tList" class="menuListA"target="iframe">视频管理</a></li>
		</ul>
	</div>	
	<div class="menuList" id="managerNews">
		<ul>
			<li>新闻管理</li>
			<li><a href="/Model/teacher/getNewsPage" target="iframe">查看新闻</a></li>
			<li><a href="t_newsAdd.jsp" class="menuListA"target="iframe">添加新闻</a></li>
			<li><a href="t_newsDelete.jsp"  class="menuListA"target="iframe">删除新闻</a></li>
		</ul>
	</div>	
	<div class="menuList" id="managerSelf">
		<ul>
			<li>个人管理</li>
			<li><a href="t_teacher_personalInfor.jsp" target="iframe">个人信息</a></li>
			<li><a href="t_teacherModify.jsp" target="iframe">修改信息</a></li>
			<li><a href="t_modifyPassword.jsp"target="iframe">密码管理</a></li>
			<li><a href="t_setEmail.jsp" class="menuListA"target="iframe">设置邮箱</a></li>
			<li><a href="t_activeEmail.jsp" class="menuListA"target="iframe">激活邮箱</a></li>
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



	<iframe class="iframe" src="t_teacher_personalInfor.jsp" name="iframe" scrolling="no" frameborder="no" onload="SetWinHeight(this)"></iframe>
</div>
<div class="footer">
	<div class="footerContent">
		<p>地址：大连市开发区图强街111号 &nbsp;&nbsp;&nbsp;&nbsp;邮编：116620</p>
		<p>电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email:</p>
		<p>Copyright © 2015. 软院学院实验中心（大连理工大学）. Supported by</p>
	</div>
</div>
<script type="text/javascript" src="../js/m_teacher.js"></script>	
</body>
</html>