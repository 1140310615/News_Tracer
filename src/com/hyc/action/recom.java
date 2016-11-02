package com.hyc.action;

import java.util.ArrayList;

import com.hyc.news.newsDao;
import com.hyc.news.newsVo;
import com.opensymphony.xwork2.Action;

public class recom implements Action
{
	/**
	 * 
	 */

	private ArrayList<newsVo> newsL;
	private String Nname;
//	private String url;
//	public void setUrl(String url)
//	{
//		this.url = url;
//	}
//	public String getUrl()
//	{
//		return this.url;
//	}
	public void setName(String name)
	{
		this.Nname = name;
	}
	public String getName()
	{
		return Nname;
	}
	public void setList(ArrayList<newsVo> list)
	{
		this.newsL = list;
	}
	public ArrayList<newsVo> getList()
	{
		return this.newsL;
	}
	public String execute() 
	{
		// TODO Auto-generated method stub
		//return super.execute();
		System.out.println("Ö´ÐÐexecuteº¯Êý");
//		newsDao dao = new newsDao();
//		dao.openConnection();
//		newsL = dao.selectAll();
//		dao.closeConnection();
		Nname = "heyongchao";
		return "success";
	}

}
