package com.yyl.user;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;

public class UserCheckAction extends ActionSupport implements ServletRequestAware{
	private String userName;
	protected HttpServletRequest servletRequest = null;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String userCheck() throws Exception{
		String userName = servletRequest.getParameter("userName");
		boolean isNamevalid = DbUtils.findUser(userName);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().print(isNamevalid);
		return null;
	}
	
	public void setServletRequest(HttpServletRequest request){
		this.servletRequest = request;
	}
	
}
