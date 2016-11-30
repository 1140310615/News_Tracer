package com.yyl.user;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class User extends ActionSupport{
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
	
	public String register() throws Exception{
		int result = DbUtils.save(userName, password);
		if(result==1){
			return SUCCESS;
		}
		return INPUT;
	}
	
	public String login() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.put("userName", userName);
		int result = DbUtils.find(userName,password);
		if(result == 1){
			return SUCCESS;
		}
		return INPUT;
	}
	
	public String logout() throws Exception{
		ActionContext ac = ActionContext.getContext(); 
		Map<String, Object> session = ac.getSession(); 
		session.clear(); 
		return SUCCESS; 
	}

}
