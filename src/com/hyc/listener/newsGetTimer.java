package com.hyc.listener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hyc.action.hycNewsAction;
import com.hyc.news.newsDao;
import com.hyc.news.newsVo;
import com.hyc.process.dateProcess;
import com.hyc.process.newsProcess;
import com.hyc.webInfo.crawler;
import com.hyc.webInfo.sina;
import com.hyc.webInfo.sohu;
import com.hyc.webInfo.tencent;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class newsGetTimer implements ServletContextListener 
{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//ServletContextListener.super.contextInitialized(sce);
		new newsGet();
	}
	
}

class newsGet 
{
	public newsGet()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 18);
		calendar.set(Calendar.MINUTE,37);
		calendar.set(Calendar.SECOND,00);
		Date t = calendar.getTime();    //得出执行任务的时间
		Timer myTimer = new Timer();
		if (t.before(new Date()))
		{
			t = addDay(t, 1);
		}
		myTimer.scheduleAtFixedRate(new TimerTask(){
			public void run()
			{
				System.out.println("开始");
				sina mySina = new sina();
				try {
					mySina.getUrl();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("获得url");
				getNews(mySina.entList,"ent");
				System.out.println("娱乐");
				getNews(mySina.newsList,"news");
				System.out.println("综合");
				getNews(mySina.sportList,"sport");
				System.out.println("运动");
				getNews(mySina.techList,"tech");
				
				tencent myTencent = new tencent();
				try {
					myTencent.getUrl();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("腾讯国际");
				getNews(myTencent.interList,"inter");
				System.out.println("腾讯军事");
				getNews(myTencent.miliList,"mili");
//				System.out.println("腾讯娱乐");
//				getNews(myTencent.entList,"ent");
				
				sohu mySohu = new sohu();
				try
				{
					mySohu.getUrl();
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				getNews(mySohu.interList,"inter");
				getNews(mySohu.miliList,"mili");
				getNews(mySohu.newsList,"news");
				System.out.println("结束");
					//getNews(mySina.yingchaoList,"yingchao");
			}
		}, t, 1000*60*60*24);
		
	}
	
	private void getNews(List<String> entList,String type)
	{
		newsVo  vo  = new newsVo ();
		newsDao dao = new newsDao(); 
		Date ddate = new Date();  
		String today = DateFormat.getDateInstance(DateFormat.MEDIUM).format(ddate);

		dao.openConnection();
		for (int i=0;i < entList.size();i++)
		{
			String url = entList.get(i);
			vo.setUrl(url);
			String date = getDate(url);
			if (date.equals(""))
				date = getTencentDate(url);
			if (date.equals(""))
				date = today;
			if (vo.getUrl().endsWith("html") || vo.getUrl().endsWith("htm"))
			{
				crawler myCrawler = new crawler();
				vo.setName(myCrawler.getHead(url));
				vo.setKeywords(myCrawler.getKeywords(url));
				vo.setDate(date);
				vo.setType(type);
				boolean flag = dao.insertNews(vo);
				System.out.println(flag);
				//System.out.println(dao.insertNews(vo));
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dao.closeConnection();
	}
	private String getDate(String str)
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
	private String getTencentDate(String str)
	{
		String[] temp = str.split("/");
		Pattern p = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d");
		for (int i = 0;i < temp.length;i++)
		{
			Matcher m = p.matcher(temp[i]);
			if (m.matches())
			{
				String date = "";
				String year = temp[i].substring(0, 4);
				String month= temp[i].substring(4, 6);
				String day  = temp[i].substring(6);
				date = year + "-" + month + "-" + day;
				return date;
			}
		}
		return "";
	}
	 public static Date addDay(Date date, int num) 
	 {
		 Calendar startDT = Calendar.getInstance();
		 startDT.setTime(date);
		 startDT.add(Calendar.DAY_OF_MONTH, num);
		 return startDT.getTime();
	 }

}
