package com.yyl.user;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAction extends ActionSupport{
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() throws Exception{
		int result = DbUtils.find(userName,password);
		if(result == 1){
			return SUCCESS;
		}
		return INPUT;
	}
}
