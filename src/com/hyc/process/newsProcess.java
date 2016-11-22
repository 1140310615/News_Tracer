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
	
	//���������ַ����Ĳ���ֵ
	public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
	        if (s == null || t == null) {
	           //�ݴ��׳�������쳣�Ǳ����ڴ��ε�ʱ�򣬴�����һ�����Ϸ�����ȷ�Ĳ����� ���������ã�illegal:�Ƿ���Argument:������֤�ݡ�
	           throw new IllegalArgumentException("Strings must not be null");
	        }
	        //���㴫��������ַ�������
	        int n = s.length(); 
	        int m = t.length(); 
	        //�ݴ�ֱ�ӷ��ؽ�������������
	        if (n == 0) {
	            return m;
	        } else if (m == 0) {
	            return n;
	        }
	        //��һ���Ǹ����ַ������̴��������tΪ���ַ�����sΪ���ַ�����������洦��
	       if (n > m) {
	            CharSequence tmp = s;
	            s = t;
	            t = tmp;
	            n = m;
	            m = t.length();
	        }

	        //����һ���ַ����飬���n�Ƕ��ַ����ĳ���
	        int p[] = new int[n + 1]; 
	        int d[] = new int[n + 1]; 
	        //���ڽ���p��d������
	        int _d[];

	        int i; 
	        int j; 
	        char t_j; 
	        int cost; 
	        //����ֵ
	        for (i = 0; i <= n; i++) {
	            p[i] = i;
	        }

	        for (j = 1; j <= m; j++) {
	            //t���ַ��������Ǹ��ַ�
	            t_j = t.charAt(j - 1);
	            d[0] = j;

	            for (i = 1; i <= n; i++) {
	                //���������ַ��Ƿ�һ����һ������0��
	                cost = s.charAt(i - 1) == t_j ? 0 : 1;
	                //���Խ�d���ַ�����ȫ����ֵ��
	                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
	            }

	            //����p��d
	            _d = p;
	            p = d;
	            d = _d;
	        }
	        
	        //����һ��ֵ��Ϊ����ֵ
	        return p[n];
	}
}
