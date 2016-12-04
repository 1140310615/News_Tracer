<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "java.util.ArrayList" %> 
<%@ page import = "com.hyc.news.newsVo" %>
<%@ page import = "java.util.List" %>
<%@ page import = "com.hyc.webInfo.crawler" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
<head>
<title>add</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/input-text.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
 <!--SIGN UP-->
	<div class="dark-matter">
		<h1>添加新闻</h1>
		<s:form action="addNews" method="post" >
			<s:textfield name="name" type="text" aria-label="标题" placeholder="标题"/>
			<s:textfield name="keywords" type="text" aria-label="关键词" placeholder="关键词"/>
						
			<s:textarea name="body" type="text" aria-label="内容" placeholder="内容" id="textarea1"/>
			
			<s:submit value="提交" />
		</s:form>
	</div>	
</body>
</html>