package com.hyc.news;

import java.sql.Date;

public class newsVo 
{
	private String url;         //新闻的链接,varchar(50)
	private String name;        //新闻标题,varchar(30)
	private String keywords;    //新闻关键字,varchar(20)
	//private String body;        //鏂伴椈鍐呭
	private String type;        //新闻类型varhcar(5)
	private Date date;          //新闻日期date
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getUrl()
	{
		return this.url;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}
	
	public String getKeywords()
	{
		return this.keywords;
	}
	
//	public void setBody(String body)
//	{
//		this.body = body;
//	}
//	public String getBody()
//	{
//		return this.body;
//	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	public String getType()
	{
		return this.type;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	public void setDate(String date)
	{
		this.date = Date.valueOf(date);
	}
	public Date getDate()
	{
		return this.date;
	}
}
