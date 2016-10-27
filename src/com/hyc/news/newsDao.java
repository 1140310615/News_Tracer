package com.hyc.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class newsDao 
{
	private Connection con;
	//鎵撳紑鏁版嵁搴�
	public void openConnection()
	{
		try
		{
			if (con == null)
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news?characterEncoding=utf8","root","123456");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	//鍏抽棴鏁版嵁搴�
	public void closeConnection()
	{
		try
		{
			if (con != null)
			{
				con.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	//鎻掑叆涓�鏉℃柊闂�
	public boolean insertNews(newsVo vo)
	{
		String sql = "insert into newsList values(?,?,?,?,?)";
		try
		{
			int count = 0;
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, vo.getUrl());         //鎻掑叆閾炬帴
			
			pre.setString(2, vo.getName());
			
			pre.setString(3, vo.getKeywords());
			pre.setString(4, vo.getType());
			pre.setDate(5, vo.getDate());
			//System.out.println(sql);
			count = pre.executeUpdate();
			pre.close();
			return count>0?true:false;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	//鐐瑰嚮鏌愪釜鏂伴椈绫诲埆鏃讹紝杩斿洖姝ょ被鍒殑鎵�鏈夋柊闂�
	public ArrayList<newsVo> selectByType(String type)
	{
		ArrayList<newsVo> newsList = new ArrayList<newsVo>();
		String sql = "select * from newsList where type=?";
		try
		{
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, type);
			ResultSet rs = pre.executeQuery();
			while(rs.next())
			{
				newsVo vo = new newsVo();
				vo.setUrl(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setKeywords(rs.getString(3));
				vo.setType(rs.getString(4));
				vo.setDate(rs.getDate(5));
				newsList.add(vo);
			}
			if (rs != null)
				rs.close();
			if (pre!= null)
				pre.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return newsList;
	}
	public boolean deleteByDate(String date)
	{
		String sql = "delete from newsList where date < ?";
		try
		{
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, date);
			int count = pre.executeUpdate();
			if (pre != null)
				pre.close();
			return count>0?true:false;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
}
