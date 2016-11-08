<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<base target="mainFrame">
</head>
<body bgcolor="#F0F0F0">
	<center>
	<table border=0>
	<s:iterator value="list" status="news">
	<tr> 
		<td><a href="showBody.action?url=<s:property value="url" />" target="mainFrame"><s:property value="name"/></a></td>
	</tr>
	</s:iterator>
	
	</table>
	</center>
</body>
</html>