<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.springframework.web.servlet.ModelAndView"%>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量上传结果页面</title>
</head>
<body>
 <p>
                    批量上传结果:</br>
        
      
      <%out.println("request:"+request.getParameter("message"));%></br>
      <%out.println("request:upload_result_message:"+request.getParameter("upload_result_message"));%></br>
      <%out.println("request:handle_result_message:"+request.getParameter("handle_result_message"));%></br>
      <%out.println("request:file_type:"+request.getParameter("file_type"));%></br>
      <%out.println("request:file_name:"+request.getParameter("file_name"));%></br>
      </br>
      mav.upload_result_message: ${upload_result_message}</br>
      mav.handle_result_message: ${handle_result_message}</br>
      mav.file_type: ${file_type}</br>
      mav.file_name:  ${file_name }  </br> 
      
      
      
  </p>
</body>
</html>