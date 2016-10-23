package com.hyc.news;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hyc.webInfo.crawler;
import com.hyc.webInfo.sina;

public class newsGet 
{
	public static void run()
	{
		sina mySina = new sina();
		mySina.getUrl();
		getNews(mySina.entList,"ent");
		getNews(mySina.newsList,"news");
		getNews(mySina.sportList,"sport");
		getNews(mySina.techList,"tech");
	}
	private static void getNews(List<String> entList,String type)
	{
		newsVo  vo  = new newsVo ();
		newsDao dao = new newsDao();
		dao.openConnection();
		for (int i=0;i < entList.size();i++)
		{
			String url = entList.get(i);
			vo.setUrl(url);
			if (vo.getUrl().endsWith("html"))
			{
				crawler myCrawler = new crawler();
				vo.setName(myCrawler.getHead(url));
				vo.setKeywords(myCrawler.getKeywords(url));
				vo.setDate(getDate(url));
				vo.setType(type);
				dao.insertNews(vo);
			}
		}
		dao.closeConnection();
	}
	private static String getDate(String str)
	{
		String[] temp = str.split("/");
		Pattern p = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");
		for (int i = 0;i < temp.length;i++)
		{
			Matcher m = p.matcher(temp[i]);
			if (m.matches())
				return temp[i];
		}
		return "";
	}
}
