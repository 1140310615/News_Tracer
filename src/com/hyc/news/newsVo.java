package com.hyc.news;

import java.sql.Date;

public class newsVo 
{
	private String url;         //���ŵ�����,varchar(50)
	private String name;        //���ű���,varchar(30)
	private String keywords;    //���Źؼ���,varchar(20)
	//private String body;        //新闻内容
	private String type;        //��������varhcar(5)
	private Date date;          //��������date
	
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
