package com.hyc.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * 数据库操作
 * */
public class newsDao 
{
	private static Connection con = null;
	
	public void openConnection()
	{
		try
		{
      String URL ="jdbc:mysql://localhost:3306/app_heyongchaosss?characterEncoding=utf8";
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(URL, "root", "50605060");
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
	public boolean isExist(String url)
	{
	  String sql = "select name from newslist where url = ?";
	  PreparedStatement pre_t = null;
	  ResultSet rs = null;
    try
    {
      pre_t = con.prepareStatement(sql);
      pre_t.setString(1,url);
      rs = pre_t.executeQuery();
      if(rs.next()){
        return true;
      }
      return false;
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
      return false;
    } finally{
      try{
        rs.close();
        pre_t.close();
      }
      catch (SQLException e){
        e.printStackTrace();
      }
    }
	}
	
	public boolean insertNews(newsVo vo)
	/*
	 * 插入时设置去重
	 */
	{
		if (isExist(vo.getUrl())){
		  return false;
		}
		String  sql = "insert into newsList values(?,?,?,?,?,?)";
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
	/*
	 * 访问链接访问量加一
	 * */
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
				//System.out.println(count);
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
