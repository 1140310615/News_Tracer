package com.hyc.process;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hyc.news.newsVo;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class dateProcess 
/*
 * ��������ܶȵķ�ĸ
 */
{
	public static int getE(newsVo vo)
	/*
	 * ��������������
	 */
	{
		String d = vo.getDate().toString();
		String formatDate = null;  
		Date date = new Date();  
		int a = 0;
		formatDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
		try 
		{
			a = dateProcess.daysBetween(d, formatDate);
		} catch (ParseException | java.text.ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	/**  
     * ������������֮����������  
     * @param smdate ��С��ʱ�� 
     * @param bdate  �ϴ��ʱ�� 
     * @return ������� 
     * @throws ParseException  
	 * @throws java.text.ParseException 
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException, java.text.ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
      
/** 
*�ַ��������ڸ�ʽ�ļ��� 
 * @throws java.text.ParseException 
*/  
    public static int daysBetween(String smdate,String bdate) throws ParseException, java.text.ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
}
