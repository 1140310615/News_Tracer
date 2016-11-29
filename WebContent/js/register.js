/**
 * 
 */

	var xmlHttp;
	function createXMLHttpRequest(){
		if(window.ActiveXOject){
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}
	}
	function checkUserName(){
		var userName = document.getElementById("userName").value;
		if(userName=="" || userName.indexOf(" ")!=-1){
			document.getElementById("userNameSpan").innerHTML="用户名必须不为空且不含空格";
			$("#btn").attr({"disabled":"disabled"});
			document.getElementById("userName").focus();
			return;
		}		
		createXMLHttpRequest();
		var url = "userCheck.action?userName=" +userName;
		xmlHttp.open("POST",url,true);
		xmlHttp.onreadystatechange = ajaxStatus;
		xmlHttp.send(null);
	}
	function ajaxStatus(){
		var responseContext="";
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				responseContext = xmlHttp.responseText;
				if(responseContext=="true"){
					document.getElementById("userNameSpan").innerHTML="用户名已存在";
					$("#btn").attr({"disabled":"disabled"});
					document.getElementById("userName").focus();
				}else{
					document.getElementById("userNameSpan").innerHTML="用户名可以使用";
					$("#btn").removeAttr("disabled");
				}
			}
		}
	}
	
	function checkpassword(){
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;	
		if(password.length<6 || isNaN(password)==false){
			document.getElementById("userNameSpan").innerHTML="密码长度至少为6且不可为纯数字";
			$("#btn").attr({"disabled":"disabled"});
			document.getElementById("userName").focus();
			return;
		}
		if(confirmPassword!=password){
			document.getElementById("userNameSpan").innerHTML="密码不一致";
			$("#btn").attr({"disabled":"disabled"});
			document.getElementById("userName").focus();
			return;
		}
				
	}
