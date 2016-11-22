package com.hyc.process;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.hyc.news.*;
public class newsProcess 
{
/*
 * new sort
 */
	public ArrayList<newsVo> newsSort(ArrayList<newsVo> list)
	{
		if (list.size() == 0)
			return list;
        Collections.sort(list, new Comparator<newsVo>() 
        {
			@Override
			public int compare(newsVo o1, newsVo o2) 
			{
				double a2 = (o2.getCount())/(double)(1 + dateProcess.getE(o2));
				double a1 = (o1.getCount())/(double)(1 + dateProcess.getE(o1));
				// TODO Auto-generated method stub
				if (a1 == a2)
					return o2.getDate().compareTo(o1.getDate());
				if (a1 > a2)
					return -1;
				else
					return 1;
			}
        });
		return list;
	}
	
	public ArrayList<newsVo> sortByDate(ArrayList<newsVo> list)
	{
		if (list.size() == 0 || list.size()==1)
			return list;
        Collections.sort(list, new Comparator<newsVo>() 
        {
			@Override
			public int compare(newsVo o1, newsVo o2) 
			{
				return o2.getDate().compareTo(o1.getDate());
//				double a2 = (o2.getCount());
//				double a1 = (o1.getCount());
//				// TODO Auto-generated method stub
//				int a = o2.getDate().compareTo(o1.getDate());
//				if (a == 0)
//				{
//					if (a1 > a2)
//						return -1;
//					else
//						return 1;
//				}
//				else
//					return a;
			}
        });
		return list;
	}

	public ArrayList<newsVo> top(ArrayList<newsVo> list)
	{
		if (list.size() == 0)
			return list;
        Collections.sort(list, new Comparator<newsVo>() 
        {
			//@Override
			public int compare(newsVo o1, newsVo o2) 
			{
				double a2 = (o2.getCount());
				double a1 = (o1.getCount());
				// TODO Auto-generated method stub
				if (a1 == a2)
					return o2.getDate().compareTo(o1.getDate());
				if (a1 > a2)
					return -1;
				else
					return 1;
			}
        });
		return list;
	}
	
	public static boolean isSimilar(String str1,String str2)
	{
		int a = str1.length();
		int b = str2.length();
		int s = newsProcess.getLevenshteinDistance(str1, str2);
		int c = (a + b)/2;
		double similarity = s/(double)c;
		if (similarity < 0.3)
			return true;
		else
			return false;
	}
	
	//计算两个字符串的差异值
	public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
	        if (s == null || t == null) {
	           //容错，抛出的这个异常是表明在传参的时候，传递了一个不合法或不正确的参数。 好像都这样用，illegal:非法。Argument:参数，证据。
	           throw new IllegalArgumentException("Strings must not be null");
	        }
	        //计算传入的两个字符串长度
	        int n = s.length(); 
	        int m = t.length(); 
	        //容错，直接返回结果。这个处理不错
	        if (n == 0) {
	            return m;
	        } else if (m == 0) {
	            return n;
	        }
	        //这一步是根据字符串长短处理，处理后t为长字符串，s为短字符串，方便后面处理
	       if (n > m) {
	            CharSequence tmp = s;
	            s = t;
	            t = tmp;
	            n = m;
	            m = t.length();
	        }

	        //开辟一个字符数组，这个n是短字符串的长度
	        int p[] = new int[n + 1]; 
	        int d[] = new int[n + 1]; 
	        //用于交换p和d的数组
	        int _d[];

	        int i; 
	        int j; 
	        char t_j; 
	        int cost; 
	        //赋初值
	        for (i = 0; i <= n; i++) {
	            p[i] = i;
	        }

	        for (j = 1; j <= m; j++) {
	            //t是字符串长的那个字符
	            t_j = t.charAt(j - 1);
	            d[0] = j;

	            for (i = 1; i <= n; i++) {
	                //计算两个字符是否一样，一样返回0。
	                cost = s.charAt(i - 1) == t_j ? 0 : 1;
	                //可以将d的字符数组全部赋值。
	                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
	            }

	            //交换p和d
	            _d = p;
	            p = d;
	            d = _d;
	        }
	        
	        //最后的一个值即为差异值
	        return p[n];
	}
}
