<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
a{

font-family:宋体 ;  
font-size:25px;           
color:black;                   
text-decoration:none;
    
}

a:hover{

font-family:微软雅黑 ;  
font-size:27px;           
color:red;                   
text-decoration:none;  

}


</style>



<title>newsKind</title>
</head>
<body>


<table width="130" border="0" align="center" bgcolor="#FFF68F" style="height:350px;">
	<tr>
		<td><a href="recomd.action" target="mainFrame">&nbsp;&nbsp;&nbsp;&nbsp;推荐</a></td>
	</tr>
	<tr>
		<td><a href="newsType.action?type=news" target="mainFrame">&nbsp;&nbsp;&nbsp;&nbsp;综合</a></td>
	</tr>
	<tr>
		<td><a href="newsType.action?type=ent" target="mainFrame">&nbsp;&nbsp;&nbsp;&nbsp;娱乐</a></td>
	</tr>
	<tr>
		<td><a href="newsType.action?type=sport" target="mainFrame">&nbsp;&nbsp;&nbsp;&nbsp;运动</a></td>
	</tr>
	<tr>
		<td><a href="newsType.action?type=tech" target="mainFrame">&nbsp;&nbsp;&nbsp;&nbsp;科技</a></td>
	</tr>
</table>

</body>
</html>