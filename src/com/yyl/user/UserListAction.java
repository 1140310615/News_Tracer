package com.yyl.user;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
public class UserListAction extends ActionSupport implements ServletRequestAware {
	protected HttpServletRequest servletRequest = null;
	public String list() throws Exception{
		List<Map<String, String>> userList = null;
		try{
			userList = DbUtils.findAll();
			System.out.println("findAll");
		}catch(RuntimeException e){
			
		}
		servletRequest.setAttribute("userList", userList);
		return "userList";
	}
	public void setServletRequest(HttpServletRequest request){
		this.servletRequest = request;
	}
}
