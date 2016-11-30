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
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;	
		if(userName=="" || userName.indexOf(" ")!=-1){
			document.getElementById("userNameSpan").innerHTML="用户名必须不为空且不含空格";
			return false;
		}
		
		createXMLHttpRequest();
		var url = "userCheck.action?userName=" +userName;
		xmlHttp.open("POST",url,true);
		xmlHttp.onreadystatechange = ajaxStatus;
		xmlHttp.send(null);
		if(password.length<6 || isNaN(password)==false){
			document.getElementById("userNameSpan").innerHTML="密码长度至少为6且不可为纯数字";
			return false;
		}
		if(confirmPassword!=password){
			document.getElementById("userNameSpan").innerHTML="密码不一致";
			return false;
		}
	}
	function ajaxStatus(){
		var responseContext="";
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				responseContext = xmlHttp.responseText;
				if(responseContext=="true"){
					document.getElementById("userNameSpan").innerHTML="用户名已存在";
					document.getElementById("userName").onfocus();
				}else{
					/*document.getElementById("userNameSpan").innerHTML="用户名可以使用";	*/
				}
					
				}
			}
		}
	
	



	
