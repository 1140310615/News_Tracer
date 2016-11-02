package com.hyc.action;

import java.util.ArrayList;

import com.hyc.news.newsDao;
import com.hyc.news.newsVo;
import com.hyc.webInfo.crawler;
import com.opensymphony.xwork2.ActionSupport;
import com.hyc.process.*;

public class hycNewsAction extends ActionSupport
{
	private String type;
	private String name;
	private String url;
	private String searchKey;
	private ArrayList<String> nList;
	private ArrayList<newsVo> list;
	public void setSearchKey(String searchKey)
	{
		this.searchKey = searchKey;
	}
	public String getSearchKey()
	{
		return this.searchKey;
	}
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
		newsDao dao = new newsDao();
		dao.openConnection();
		list = dao.selectByType(type);
		//list = dao.selectAll();
		list = new newsProcess().newsSort(list);
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
	
	public String search()
	{
		ArrayList<newsVo> temp = new ArrayList<newsVo>();
		newsDao dao = new newsDao();
		list = new ArrayList<newsVo>();
		dao.openConnection();
		temp = dao.selectAll();
		dao.closeConnection();
		if (searchKey==null || searchKey.equals(""))
			return "fail";
		for (int i = 0;i < temp.size();i++)
		{
			newsVo vo = temp.get(i);
			String str1 = vo.getKeywords();
			String str2 = vo.getName();
			if (str1.indexOf(searchKey)!=-1 || str2.indexOf(searchKey)!=-1)
				list.add(vo);
		}
		if (list.size()==0)
			return "fail";
		return "success";
	}
	
	public String recomd()
	{
		newsDao dao = new newsDao();
		dao.openConnection();
		list = dao.selectAll();
		dao.closeConnection();
		return "success";
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
}
