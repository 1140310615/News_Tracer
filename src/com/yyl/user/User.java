package com.yyl.user;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hyc.news.*;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class User extends ActionSupport{
  
  private String name;        //新闻标题,varchar(30)
  private String keywords;    //新闻关键字,varchar(20)
  private Date date;          //新闻日期date
  private String body;        //内容
  private String type;        //新闻类型varhcar(5)
  
  static private int num = 0;            //该用户添加的的新闻数量
  
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
		int result = DbUtils.find(userName,password);
		if(result == 1){
		  session.put("userName", userName);
		  num = DbUtils.findnum(userName);
		  System.out.println(num);
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
	
	public static boolean isLogin(){
	  ActionContext context = ActionContext.getContext();
    Map<String, Object> session = context.getSession();
    if (session.get("userName")==null)
      return false;
    else 
      return true;
	}
	
	public void validateAddNews()
  {
	  if (name == null || name.trim().equals(""))
    {
       addFieldError("name","请输入题目");
    }//name
	  if (keywords == null || keywords.trim().equals(""))
    {
       addFieldError("keywords","请输入关键字");
    }//keywords
	  if (body == null || body.trim().equals(""))
    {
       addFieldError("body","请输入内容");
    }//keywords
  }
	public String addNews(){
	  if (isLogin()){
	    ActionContext context = ActionContext.getContext();
	    Map<String, Object> session = context.getSession();
  	  newsVo vo = new newsVo();
  	  vo.setName(name);
  	  vo.setKeywords(keywords);
  	  vo.setCount(0);
  	  
  	  java.util.Date ddate = new java.util.Date();  
      String today = DateFormat.getDateInstance(DateFormat.MEDIUM).format(ddate);
  	  vo.setDate(today);
  	  
  	  vo.setType("me");
  	  
  	  vo.setUrl("local"+session.get("userName").toString()+num);
  	  System.out.println("local"+session.get("userName").toString()+num);
  	  
  	  num++;
  	  
  	  newsDao dao = new newsDao(); 
  	  dao.openConnection();
  	  boolean flag1 = dao.insertLocalNews(vo.getUrl(), body);
      boolean flag = dao.insertNews(vo);
      System.out.println("body:"+flag1+" info:"+flag);
      dao.closeConnection();
      
  	  return SUCCESS;
	  }else{
	    return ERROR;
	  }
	}
	
  public String getKeywords() {
    return keywords;
  }
  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
