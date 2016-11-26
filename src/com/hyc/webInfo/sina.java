package com.hyc.webInfo;

import java.util.ArrayList;
import java.util.List;

public class sina 
/*
 * 新浪网结构
 */
{
	private final tuple5 news = new tuple5(classifyName.news,"http://news.sina.com.cn","ul","class","list_14");
	//private final tuple5 mili = new tuple5(classifyName.mili,"http://mil.news.sina.com.cn","ul","class","green-dot");
	private final tuple5 tech = new tuple5(classifyName.tech,"http://tech.sina.com.cn","ul","id","newsRankTabC1");
	private final tuple5 ent  = new tuple5(classifyName.ent,"http://ent.sina.com.cn","h2","","");
	private final tuple5 sport= new tuple5(classifyName.sport,"http://sports.sina.com.cn","ul","class","list01");
	private final tuple5 yingchao = new tuple5(classifyName.yingchao,"http://sports.sina.com.cn/g/premierleague/","ul","","");
	public List<String> newsList = new ArrayList<String>();     //爬下来的新闻链接
	public List<String> miliList = new ArrayList<String>();
	public List<String> techList = new ArrayList<String>();
	public List<String> entList = new ArrayList<String>();
	public List<String> sportList = new ArrayList<String>();
	public List<String> yingchaoList = new ArrayList<String>();
	public void getUrl() throws InterruptedException
	{
		newsList = crawler.getUrl(this.news.getUrl());
		Thread.sleep(2000);
		//miliList = crawler.getUrl(this.mili.getUrl());
		techList = crawler.getUrl(this.tech.getUrl());
		Thread.sleep(2000);
		entList  = crawler.getUrl(this.ent.getUrl());
		Thread.sleep(2000);
		sportList= crawler.getUrl(this.sport.getUrl());
		Thread.sleep(2000);
		//yingchaoList = crawler.getUrl(this.yingchao.getUrl());
	}
}

class tuple5
{
	classifyName name;        //新闻分类
	private String url;       //新闻url
	private String tag;       //过滤标签
	private String filter;    //过滤噿
	private String filValue;  //过滤器忿
	public tuple5(classifyName name,String url,String tag,String filter,String filValue)
	{
		this.name = name;
		this.url = url;
		this.tag = tag;
		this.filter = filter;
		this.filValue = filValue;
	}
	public classifyName getName()
	{
		return name;
	}
	public String getUrl()
	{
		return url;
	}
	public String getTag()
	{
		return tag;
	}
	public String getFil()
	{
		return filter;
	}
	public String getFilValue()
	{
		return filValue;
	}
}
enum classifyName
{
	//新闻、军事㿁科抿、娱乐㿁体育㿁博宿
	news,mili,tech,ent,sport,yingchao;     
}