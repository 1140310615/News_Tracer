<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">

a{

font-family:宋体 ;  
font-size:22px;           
color:#919191;                   
text-decoration:none;  
        

}


a:visited {

font-family:宋体 ;  
font-size:22px;                            
text-decoration:none;  
color:#CD2990;

} 


a:hover{

font-family:微软雅黑 ;  
font-size:25px;           
color:#9ACD32;                   
text-decoration:none;  

}

a:active {

font-family:微软雅黑 ;  
font-size:25px;                            
text-decoration:none;  
color:#CD2990;

} 


</style>
<title></title>
<base target="mainFrame">
</head>
<body bgcolor="#F0F0F0">
	<center>
	<ol>
	<s:iterator value="list" status="news">
	
		<li><a href="showBody.action?url=<s:property value="url" />" target="mainFrame"><s:property value="name"/></a></li>
		<br>
	</s:iterator>
	
	</ol>
	</center>
</body>
</html>