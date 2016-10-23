package com.hyc.action;

import java.util.ArrayList;

import com.hyc.news.newsDao;
import com.hyc.news.newsVo;
import com.hyc.webInfo.crawler;
import com.opensymphony.xwork2.ActionSupport;

public class hycNewsAction extends ActionSupport
{
	private String type;
	private String name;
	private String url;
	private ArrayList<String> nList;
	private ArrayList<newsVo> list;
	public void setNList(ArrayList<String> nList)
	{
		this.nList = nList;
	}
	public ArrayList<String> getNList()
	{
		return this.nList;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getUrl()
	{
		return this.url;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getType()
	{
		return this.type;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	
	public void setList(ArrayList<newsVo> list)
	{
		this.list = list;
	}
	public ArrayList<newsVo> getList()
	{
		return list;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String newsType()
	{
	//	newsGet.getNews();
	//	newsGet.run();
		newsDao dao = new newsDao();
		dao.openConnection();
		list = dao.selectByType(type);
		dao.closeConnection();
		return "success";
	}
	
	public String showBody()
	{
		crawler newsCra = new crawler();
		name = newsCra.getHead(url);
//		para = newsCra.getText(url);
		list = newsCra.getText(url);
	
		return "success";
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
}
