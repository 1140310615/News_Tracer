package com.hyc.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class newsDao 
{
	private Connection con;
	
	public void openConnection()
	{
		try
		{
			if (con == null)
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news?characterEncoding=utf8&useSSL=true","root","50605060");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
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
	
	public boolean insertNews(newsVo vo)
	{
		String sql = "select name from newslist where url = ?";
		try
		{
			PreparedStatement pre_t = con.prepareStatement(sql);
			pre_t.setString(1,vo.getUrl());
			ResultSet rs = pre_t.executeQuery();
			if(rs.next()){
				rs.close();
				pre_t.close();
				return false;
			}
			rs.close();
			pre_t.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		sql = "insert into newsList values(?,?,?,?,?,?)";
		try
		{
			int count = 0;
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, vo.getUrl());         //鎻掑叆閾炬帴
			
			pre.setString(2, vo.getName());
			
			pre.setString(3, vo.getKeywords());
			pre.setString(4, vo.getType());
			pre.setDate(5, vo.getDate());
			pre.setInt(6, 0);
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
	
	public ArrayList<newsVo> selectAll()
	{
		ArrayList<newsVo> newsList = new ArrayList<newsVo>();
		String sql = "select * from newsList";
		try
		{
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next())
			{
				newsVo vo = new newsVo();
				vo.setUrl(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setKeywords(rs.getString(3));
				vo.setType(rs.getString(4));
				vo.setDate(rs.getDate(5));
				vo.setCount(rs.getInt(6));
				newsList.add(vo);
			}
			if (rs != null)
				rs.close();
			if (sta!= null)
				sta.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return newsList;
	}

	
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
				vo.setCount(rs.getInt(6));
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
	public boolean add(String url)
	{
		String sql = "select * from newsList where url=?";
		try
		{
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, url);
			ResultSet rs = pre.executeQuery();
			if(rs.next())
			{
				int count = rs.getInt(6) + 1;
				System.out.println(count);
				sql = "update newsList set count=? where url=?";
				pre = con.prepareStatement(sql);
				pre.setInt(1, count);
				pre.setString(2, url);
				int a = pre.executeUpdate();
				System.out.println("a = "+a);
			}
			if (rs != null)
				rs.close();
			if (pre!= null)
				pre.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}
