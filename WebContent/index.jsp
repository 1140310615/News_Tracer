<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "java.util.ArrayList" %> 
<%@ page import = "com.hyc.news.newsVo" %>
<%@ page import = "java.util.List" %>
<%@ page import = "com.hyc.webInfo.crawler" %>
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

<a href="login.jsp">登录</a>&nbsp;&nbsp;
<a href='<s:url action="user_logout"/>'>登出</a>&nbsp;&nbsp;
<a href="register.jsp">注册</a>&nbsp;&nbsp;
<h5>${sessionScope.userName}</h5>
<!-- a href='<s:url action="userList" method="list"/>'>用户列表</a>&nbsp;&nbsp;-->
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
				</li>
				<li><a href="recomd.action">首页</a></li>
				<li><a href="newsType.action?type=news"">综合</a></li>
				<li><a href="newsType.action?type=ent">娱乐</a></li>
				<li><a href="newsType.action?type=sport"> 运动</a></li>
				<li><a href="newsType.action?type=tech"> 科技</a></li>
				<li><a href="newsType.action?type=inter">国际</a></li>
				
				<li><a href="newsType.action?type=mili">军事</a></li>
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

	<div class="featured container">
		<div id="owl-demo" class="owl-carousel">
		<%
		@SuppressWarnings("unchecked")
		List<newsVo> recomList = (ArrayList<newsVo>)request.getAttribute("recomList");
		int c = recomList.size();
		c = 15 < c?15:c;
		for (int i = 0;i < c;i++)
		{
			newsVo vo = recomList.get(i);
			String name = vo.getName();
			String url = vo.getUrl();
			int temp = i % 5;
			temp++;
			String imgUrl = "images/"+String.valueOf(temp)+".jpg";
			%>
						<div class="item">
				<div class="zoom-container">
					<div class="zoom-caption">
						<a href="showBody.action?url=<%=url %>"></a></a><p><%=name%></p>
						
					</div>
					<img src="<%=imgUrl %>"/>
				</div>
			</div>
			
			<% 
		}
		%>

		</div>
	</div>
	<!-- Header -->
	
	<!-- /////////////////////////////////////////Content -->
	<div id="page-content" class="archive-page container">
		<div class="">
			<div class="row">
				<div id="main-content" class="col-md-8"  name="mainFrame">
					<%
					@SuppressWarnings("unchecked")
					List<newsVo> textList = (ArrayList<newsVo>)request.getAttribute("list");
					//@SuppressWarnings("unchecked")
					//List<String> imgList  = (ArrayList<String>)request.getAttribute("imgList");
					//int a = imgList.size();
					int b = textList.size();
					int index = 0;
					String in = request.getParameter("chioce");
					if (in != null)
						index = Integer.parseInt(in)-1;
					index = index * 5;
					int end = index + 10;
					end = end < b?end : b;
					String type = null;
					for (int i = index;i < end;i++)
					{
						newsVo vo = textList.get(i);
						String name = vo.getName(); 
						String url = vo.getUrl();
						type = vo.getType();
						int temp = i % 10;
						String imgUrl = "images/0"+String.valueOf(temp)+".jpg";
						String date = vo.getDate().toString();
						%>
						<div class="box">
						<a href="showBody.action?url=<%=url %>"><h6 class="vid-name"><%=name%></h6></a>
						<div class="wrap-vid">
							<div class="zoom-container">
								<div class="zoom-caption"></div>
								<img src="<%=imgUrl %>" />
							</div>
							<p><br>标签:<%=vo.getKeywords() %><br>
							日期：<%=date %><br>
							浏览：<%=vo.getCount() %></p>
						</div>
					</div>
					<hr class="line">
					<%
					}
					%>
					<div class="box">
					<center>
						<ul class="pagination">
							<li>
							  <a href="#" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							  </a>
							</li>
							<li><a href="newsType.action?type=<%=type %>&chioce=1">1</a></li>
							<li><a href="newsType.action?type=<%=type %>&chioce=2">2</a></li>
							<li><a href="newsType.action?type=<%=type %>&chioce=3">3</a></li>
							<li><a href="newsType.action?type=<%=type %>&chioce=4">4</a></li>
							<li><a href="newsType.action?type=<%=type %>&chioce=5">5</a></li>
							<li>
							  <a href="#" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							  </a>
							</li>
						</ul>
					</center>
					</div>
					
				</div>
				<div id="sidebar" class="col-md-4">

					<!---- Start Widget ---->
					<div class="widget ">
						<div class="heading"><h4>Top News</h4></div>
						<%
						@SuppressWarnings("unchecked")
						List<newsVo> topList = (ArrayList<newsVo>)request.getAttribute("topList");
						int d = topList.size();
						for (int i = 0;i < d;i++)
						{
							newsVo vo = topList.get(i);
							String name = vo.getName();
							String url = vo.getUrl();
							int temp = i % 10;
							String imgUrl = "images/slide-"+String.valueOf(temp)+".jpg";
							%>
							<div class="wrap-vid">
							<div class="zoom-container">
								<div class="zoom-caption">
									<a href="showBody.action?url=<%=url %>"></a></a><p><%=name%></p>
								</div>
								<img src="<%=imgUrl %>" />
							</div>
						</div>
						<% 
						}
						%>
							
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
