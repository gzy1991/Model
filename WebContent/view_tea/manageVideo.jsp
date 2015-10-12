<%@page import="java.io.File"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
	a
	{
	padding-left:10px;
	height:20px;
	}
	.icon
	{
		width:20px;
		height:20px;
	}
	.bg
	{
		background:#ececec;
	}
	.rootName
	{
		font-size:15px;
	}
	.rootInput
	{
		border:none;
	}

	.cBtn
	{
	 margin-left:30px;
	}
	.change
	{
		display:none;
	}
</style>

</head>
<body>
	<div class="show"><span class="rootName">${root }</span><button id="in" class="cBtn">修改根文件夹名称</button></div>
	<div class="change">
	<form action="/Model/video/changeRoot"   class="rootForm" method="post">
		<input name="root" class="rootInput" value="${root }">
	</form> <button id="exit" class="cBtn">完成修改</button> </div>
	<script>
		function change()
		{
			$(".show").hide();
			$(".change").show();
			$(".rootInput").focus();
		}
		function exitChange()
		{
			$(".show").show();
			$(".change").hide();
			$(".rootForm").submit();
		}
		$(function(){
			$("#in").click(function(){
				change();
			});
			$("#exit").click(function(){
				exitChange();
			});
			$(".rootInput").blur(function(){
				exitChange();
			});
			
		});
	</script>
	<br><br>
	<form action="/Model/video/delete" method="post" onsubmit="return confirm('你要删除选中的文件吗')">
	<div class="ul">
	<%File[] files=(File[])request.getAttribute("files");
	for(File file:files){
	%>
	<div>
	<input type="checkbox" name="files" value="<%=file.getPath() %>">
	<a class="file" href="/Model/view_login/playVideo.jsp?uri=
	<%=(file.getParentFile().getName()+"/"+file.getName())%>"><%=file.getName() %></a>
	</div>
	<%} %>	
	</div>
	<br><br><br><br>
	<button >删除选中的文件</button>
	<br><br><br><br>
	<p>上传文件</p>
	</form>
	<form action="/Model/video/upload" method="post" enctype="multipart/form-data">
	<input type="file" name="file">
	<button>上传</button>
	</form>
	<br>
</body>
</html>