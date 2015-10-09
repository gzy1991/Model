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
	/* background:url(../images/PowerPoint.png); */
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
	<form action="/Model/file/changeRoot"   class="rootForm" method="post">
	<input name="filePath" value="${filePath}" type="hidden">
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
	<div ><a href="/Model/file/tListFile">返回根目录 </a></div>
	<div ><a href="${parent }">返回上一级</a></div>
	<form action="/Model/file/delete" method="post" onsubmit="return confirm('你要删除选中的文件吗')">
	<input type="hidden" name="filePath" value="${filePath }">
	<div class="ul">
	<c:forEach items="${files}" var="file">
	<div>
	<input type="checkbox" name="files" value="${file.getPath()}"><c:if test="${file.isDirectory()}">
	<!-- <img src="../images/directory.png" class="icon"></img> -->
	<span>(文件夹)</span>
	<a class="dir" href="/Model/file/tListFile?filePath=${file.getPath()}">${file.getName()}</a>
	</c:if>
	<c:if test="${file.isFile() }">
	<!-- <img src="../images/file.png" class="icon"></img> -->
	<span>(文件)</span>
	<a class="file" href="/Model/file/download?filePath=${file.getPath()}" >${file.getName()}</a>
	</c:if>
	</div>
	</c:forEach>	
	</div>
	<br><br><br><br>
	<button >删除选中的文件</button>
	<br><br><br><br>
	<p>上传文件</p>
	</form>
	<form action="/Model/file/upload" method="post" enctype="multipart/form-data">
	<input name="filePath" value="${filePath}" type="hidden">
	<input type="file" name="file">
	<button>上传</button>
	</form>
	<br><br>
	<form action='/Model/file/newFolder'>
		<input name="filePath" value="${filePath}" type="hidden">
		<input name="fileName" placeholder="请输入要新建文件夹名称">
		<button>新建文件夹</button>
	</form>
</body>
</html>