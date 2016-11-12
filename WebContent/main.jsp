<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "java.util.ArrayList" %> 
<%@ page import = "com.hyc.news.newsVo" %>
<%@ page import = "java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<style type="text/css">

</style>
</head>
<body bgcolor="#F0F0F0">
<center>
	<h2><s:property value="name"/></h2>
</center>
<hr>
<%
	@SuppressWarnings("unchecked")
	List<newsVo> textList = (ArrayList<newsVo>)request.getAttribute("list");
	@SuppressWarnings("unchecked")
	List<String> imgList  = (ArrayList<String>)request.getAttribute("imgList");
	int a = imgList.size();
	int b = textList.size();
	int c = 0;
	int j = 0;
	int count = 0;
	if (a != 0)
	{
		c = b / a;
	}
	for (int i = 0;i < b;i++)
	{
		if (a != 0 && i != 1)
		{
			if (count == 0 )
			{
				String url = (String)(imgList.get(j));
				%>
				<center><img src="<%=url %>"/></center> <br>
				<%
				j++;
				if (j == a)
				{
					a = 0;
				}
			}
			count++;
			if (count == c)
			{
				count = 0;
			}
		}
		String text = ((newsVo)textList.get(i)).getUrl();
		%><p><%=text %></p><% 
	}
%>

</body>
</html>