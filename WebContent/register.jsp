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

<title>Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/login.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
<script>$(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script>
 <!--SIGN UP-->
 <div class="login">
 	<h2>&nbsp</h2>
	<div class="login-top" style="filter:alpha(opacity=10);">
	<center>
		<h1>注册</h1>
		<s:form action="register_register" method="post" >
			<s:textfield name="userName" id="userName" type="text" aria-label="姓名" placeholder="姓名"/>
			<s:textfield name="password" type="password" aria-label="密码" placeholder="密码"/>
			<s:textfield name="confrimPassword" type="password" aria-label="确认密码" placeholder="确认密码"/>
			
		<div class="forgot">
	    	<input type="submit" value="注册" >
	    </div>
		
		</s:form>
		
	    </center>
	</div>
</div>	

</body>
</html>