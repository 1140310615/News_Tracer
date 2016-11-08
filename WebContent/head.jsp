<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body bgcolor="#858585">
<table align="right">
<tr>
<td>
<s:form action="search" method="post" target="mainFrame">
	<s:textfield name="searchKey" cssStyle="border-radius:45px;width:200px;height:30px;outline:none;background-image:url(search.jpg);background-repeat:no-repeat;background-size:21px 21px;background-position:95%;" placeholder="    搜索关键字"/>
	
</s:form>
	</td>
	</tr>
	</table>
</body>
</html>