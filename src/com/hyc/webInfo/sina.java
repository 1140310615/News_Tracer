package com.hyc.webInfo;

import java.util.ArrayList;
import java.util.List;

public class sina 
{
	private final tuple5 news = new tuple5(classifyName.news,"http://news.sina.com.cn","ul","class","list_14");
	//private final tuple5 mili = new tuple5(classifyName.mili,"http://mil.news.sina.com.cn","ul","class","green-dot");
	private final tuple5 tech = new tuple5(classifyName.tech,"http://tech.sina.com.cn","ul","id","newsRankTabC1");
	private final tuple5 ent  = new tuple5(classifyName.ent,"http://ent.sina.com.cn","h2","","");
	private final tuple5 sport= new tuple5(classifyName.sport,"http://sports.sina.com.cn","ul","class","list01");
	private final tuple5 yingchao = new tuple5(classifyName.yingchao,"http://sports.sina.com.cn/g/premierleague/","ul","","");
	public List<String> newsList = new ArrayList<String>();     //鐖笅鏉ョ殑鏂伴椈閾炬帴
	public List<String> miliList = new ArrayList<String>();
	public List<String> techList = new ArrayList<String>();
	public List<String> entList = new ArrayList<String>();
	public List<String> sportList = new ArrayList<String>();
	public List<String> yingchaoList = new ArrayList<String>();
	public void getUrl()
	{
		newsList = crawler.getUrl(this.news.getUrl());
		//miliList = crawler.getUrl(this.mili.getUrl());
		techList = crawler.getUrl(this.tech.getUrl());
		entList  = crawler.getUrl(this.ent.getUrl());
		sportList= crawler.getUrl(this.sport.getUrl());
		//yingchaoList = crawler.getUrl(this.yingchao.getUrl());
	}
}

class tuple5
{
	classifyName name;        //鏂伴椈鍒嗙被
	private String url;       //鏂伴椈url
	private String tag;       //杩囨护鏍囩
	private String filter;    //杩囨护鍣�
	private String filValue;  //杩囨护鍣ㄥ��
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
	//鏂伴椈銆佸啗浜嬨�佺鎶�銆佸ū涔愩�佷綋鑲层�佸崥瀹�
	news,mili,tech,ent,sport,yingchao;     
}