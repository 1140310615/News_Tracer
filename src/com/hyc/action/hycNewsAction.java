package com.hyc.action;

import java.util.ArrayList;
import java.util.Map;

import com.hyc.news.newsDao;
import com.hyc.news.newsVo;
import com.hyc.webInfo.crawler;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.hyc.process.*;
import com.yyl.user.User;

public class hycNewsAction extends ActionSupport
{
  private String review;
	private String type;
	private String name;
	static private String url;
	private String searchKey;
	private String newsurl;
	public static ArrayList<newsVo> nList;
	private ArrayList<String> imgList;
	private ArrayList<newsVo> list;
	private ArrayList<String> reviewList;
	private ArrayList<String> authorList;
	public static ArrayList<newsVo> recomList;
	public static ArrayList<newsVo> topList;
	private String choice;
	public void setNewsurl(String url)
	{
		this.newsurl = url;
	}
	public String getNewsurl()
	{
		return this.newsurl;
	}
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
	  boolean l = false;
		newsDao dao = new newsDao();
		dao.openConnection();
		list = dao.selectByType(type);
		if (type.equals("me")) l = true;
		//list = dao.selectAll();
		list = new newsProcess().sortByDate(list);
		dao.closeConnection();
		if (l) return "user";
		return "success";
	}
	
	public String addReview(){
	  //System.out.println("here"+url);
	  if (User.isLogin()){
  	  newsDao dao = new newsDao();
  	  ActionContext context = ActionContext.getContext();
      Map<String, Object> session = context.getSession();
      dao.openConnection();
      dao.insertReview(url,session.get("userName").toString(),review);
      dao.closeConnection();
  	  showBody();
  	  return "success";
	  }else return "error";
	}
	
	public String showBody()
	{
		this.setNewsurl(url);
		if (url.indexOf("local") != -1){
		  newsDao dao = new newsDao();
		  dao.openConnection();
      list = dao.selectLocalNews(url);
      imgList = new ArrayList<String>();
      name = list.get(0).getName(); 
      dao.closeConnection();
    }
    else{
      crawler newsCra = new crawler();
		  name = newsCra.getHead(url); 
	    if (url.indexOf("qq") != -1)
  			list = newsCra.getTencentText(url);
  		else if (url.indexOf("sohu") != -1)
  			list = newsCra.getSohuText(url);
  		else
  			list = newsCra.getText(url);
  		imgList = newsCra.getImg(url);
	  }
		
		newsDao dao = new newsDao();
		dao.openConnection();
		dao.add(url);      //访问量加一
		setReviewList(dao.selectLocalReview(url));
		setAuthorList(dao.selectLocalAuthor(url));
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
			Boolean flag1 = newsProcess.isSimilar(searchKey, str1);
			Boolean flag2 = newsProcess.isSimilar(searchKey, str2);
			if (str1.indexOf(searchKey)!=-1 || str2.indexOf(searchKey)!=-1 || flag1==true || flag2==true)
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
			if (!nList.get(i).getType().equals("me"))
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
		return "success";
	}
  public ArrayList<String> getReviewList() {
    return reviewList;
  }
  public void setReviewList(ArrayList<String> reviewList) {
    this.reviewList = reviewList;
  }
  public ArrayList<String> getAuthorList() {
    return authorList;
  }
  public void setAuthorList(ArrayList<String> authorList) {
    this.authorList = authorList;
  }
  public String getReview() {
    return review;
  }
  public void setReview(String review) {
    this.review = review;
  }
}
