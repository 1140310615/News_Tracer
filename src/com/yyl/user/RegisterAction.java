package com.yyl.user;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
public class RegisterAction extends ActionSupport{
	private String userName;
	private String password;
	private String confirmPassword;
	
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String register() throws Exception{
		int result = DbUtils.save(userName, password);
		if(result==1){
			return SUCCESS;
		}
		return INPUT;
	}
	
	public String input(){
		return INPUT;
	}
}
