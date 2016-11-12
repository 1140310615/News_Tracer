<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<style type="text/css">
p{

font-family:'楷体';
font-size:22px;

}

</style>
</head>
<body bgcolor="#F0F0F0">
<center>
	<h2><s:property value="name"/></h2>
</center>
<hr>
	<s:iterator value="list" var="var">
		<p><s:property value="url"/></p>
	</s:iterator>
</body>
</html>