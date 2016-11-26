<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "java.util.ArrayList" %> 
<%@ page import = "com.hyc.news.newsVo" %>
<%@ page import = "java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>Newspaper</title>
		<style type="text/css">
	form{
	position:absolute;
	right:0;
	top:20%;
	
	}
	
	</style>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css"  type="text/css">
	
	<!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
    <link href="owl-carousel/owl.carousel.css" rel="stylesheet">
    <link href="owl-carousel/owl.theme.css" rel="stylesheet">
	
	<!-- Custom Fonts -->
    <link rel="stylesheet" href="font-awesome-4.4.0/css/font-awesome.min.css"  type="text/css">
	
	<!-- jQuery and Modernizr-->
	<script src="js/jquery-2.1.1.js"></script>
	
	<!-- Core JavaScript Files -->  	 
    <script src="js/bootstrap.min.js"></script>
    
</head>

<body>
<header>
	<!--Navigation-->
    <nav id="menu" class="navbar container">
		<div class="navbar-header">
			<button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><i class="fa fa-bars"></i></button>
			<a class="navbar-brand" href="#">
				<div class="logo"><span>Newspaper</span></div>
			</a>
		</div>
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li><a href="recomd.action">首页</a></li>
				<li><a href="newsType.action?type=news"">综合</a></li>
				<li><a href="newsType.action?type=ent">娱乐</a></li>
				<li><a href="newsType.action?type=sport"> 运动</a></li>
				<li><a href="newsType.action?type=tech"> 科技</a></li>
				<li><a href="#">国际</a></li>
				<li><a href="#">社会</a></li>
				<li><a href="#">军事</a></li>
				<li><a href="#">新鲜事</a></li>

			</ul>
						<ul class="list-inline navbar-right">
				<li>
					<s:form action="search" method="post">
					<s:textfield name="searchKey" cssStyle="font-family:'楷体';font-size:22px;border-radius:10px;width:200px;height:30px;outline:none;background-image:url(search.jpg);background-repeat:no-repeat;background-size:21px 21px;background-position:95%;" placeholder=" 搜索关键字"/>
				</s:form>
				</li>
			</ul>
		</div>
	</nav>
</header>	

	<div id="page-content" class="archive-page container">
		<div class="">
			<div class="row">
				<div id="main-content" class="col-md-8" style="position:relative;width:85%;left:7%;">
				<div class="box">
						<div class="wrap-vid">
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
<p><a href="<s:property value="newsurl"/>">阅读原文</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer>
		<div class="copy-right">
			<p>Copyright &copy; 2016.HEYONGCHAO All rights reserved.</p>
		</div>
	</footer>
	<!-- Footer -->
	
	<!-- JS -->
	<script src="owl-carousel/owl.carousel.js"></script>
    <script>
    $(document).ready(function() {
      $("#owl-demo").owlCarousel({
        autoPlay: 3000,
        items : 5,
        itemsDesktop : [1199,4],
        itemsDesktopSmall : [979,4]
      });

    });
    </script>
	
</body>
</html>
	
