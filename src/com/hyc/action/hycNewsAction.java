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
	public static ArrayList<newsVo> nList;
	private ArrayList<String> imgList;
	private ArrayList<newsVo> list;
	public static ArrayList<newsVo> recomList;
	public static ArrayList<newsVo> topList;
	private String choice;
	public void setTopList(ArrayList<newsVo> list)
	{
		topList = list;
	}
	public ArrayList<newsVo> getTopList()
	{
		return topList;
	}
	public void setRecomList(ArrayList<newsVo> list)
	{
		recomList = list;
	}
	public ArrayList<newsVo> getRecomList()
	{
		return recomList;
	}
	public void setChioce(String c)
	{
		if (c == null || c.equals(""))
			this.choice = "0";
		else
			this.choice = c;
	}
	public String getChioce()
	{
		return this.choice;
	}
	public void setImgList(ArrayList<String> list)
	{
		this.imgList = list;
	}
	public ArrayList<String> getImgList()
	{
		return this.imgList;
	}
	public void setSearchKey(String searchKey)
	{
		this.searchKey = searchKey;
	}
	public String getSearchKey()
	{
		return this.searchKey;
	}
	public void setNList(ArrayList<newsVo> nList)
	{
		hycNewsAction.nList = nList;
	}
	public ArrayList<newsVo> getNList()
	{
		return nList;
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
		list = new newsProcess().sortByDate(list);
		dao.closeConnection();
		return "success";
	}
	
	public String showBody()
	{
		crawler newsCra = new crawler();
		name = newsCra.getHead(url);   
		list = newsCra.getText(url);
		imgList = newsCra.getImg(url);
		
		newsDao dao = new newsDao();
		dao.openConnection();
		dao.add(url);      //访问量加一
		dao.closeConnection();
	
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
		nList = dao.selectAll();
		list = new ArrayList<newsVo>();
		dao.closeConnection();
		for (int i = 0;i < nList.size();i++)
		{
			int a = dateProcess.getE(nList.get(i));
			if (a > 3)
			{
				continue;
			}
			list.add(nList.get(i));
		}
		list = new newsProcess().newsSort(list);
		recomList = list;
		list = new newsProcess().top(list);
		topList = new ArrayList<newsVo>();
		int a = list.size();
		a = 10 < a? 10 : a;
		for (int i = 0;i < a;i++)
		{
			topList.add(list.get(i));
		}
		return "success";
	}
	
	public String select()
	{
		return "success";
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
}
