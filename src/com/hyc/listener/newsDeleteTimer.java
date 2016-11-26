package com.hyc.listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hyc.news.newsDao;

public class newsDeleteTimer implements ServletContextListener 
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
		new newsDelete();
	}
	
}

class newsDelete
{
	public newsDelete()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND,0);
		Date t = calendar.getTime();    //得出执行任务的时间
		Timer myTimer = new Timer();
		if (t.before(new Date()))
		{
			t = addDay(t, 1);
		}
		myTimer.scheduleAtFixedRate(new TimerTask(){
			public void run()
			{
				newsDao dao = new newsDao();
				dao.openConnection();
				String todayBefore = getDate();
				dao.deleteByDate(todayBefore);
				dao.closeConnection();
			}
		}, t, 1000*60*60*24);
	}
	public String getDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, -30);    //获取7天之前的日期
		Date b = calendar.getTime();
		return sdf.format(b);
	}
	 public static Date addDay(Date date, int num) 
	 {
		 Calendar startDT = Calendar.getInstance();
		 startDT.setTime(date);
		 startDT.add(Calendar.DAY_OF_MONTH, num);
		 return startDT.getTime();
	 }

}

