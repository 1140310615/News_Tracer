package com.yyl.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbUtils {
	
	private static Connection getConnection() throws Exception{
		InitialContext cxt = new InitialContext();	
		DataSource ds = (DataSource) cxt.lookup("java:comp/env/jdbc/Struts2DB");
		if(ds==null){
			throw new Exception("Data source not found!");
		}
		return ds.getConnection();
	}
	
	private static PreparedStatement getPrepareStatement(Connection conn, String sql) throws Exception{
		if(conn == null || sql == null || sql.trim().equals("")){
			return null;
		}
		PreparedStatement pstmt = conn.prepareStatement(sql.trim());
		return pstmt;
	}
	
	private static void setPreparedStatementParam(PreparedStatement pstmt, List<Object> paramList) throws Exception{
		if(pstmt == null || paramList == null || paramList.isEmpty()){
			return;
		}
		for(int i = 0; i<paramList.size();i++){
			if(paramList.get(i) instanceof Integer){
				int paramValue = ((Integer)paramList.get(i)).intValue();
				pstmt.setInt(i+1, paramValue);
			}else if(paramList.get(i) instanceof String){
				pstmt.setString(i+1, (String)paramList.get(i));
			}
		}
		return;
	}
	
	private static ResultSet getResultSet(PreparedStatement pstmt)throws Exception{
		if(pstmt == null){
			return null;
		}
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	private static void closeConn(Connection conn) throws Exception{
		if(conn == null){
			return;
		}
		try{
			conn.close();
		}catch(SQLException e){
			throw new Exception(e);
		}
	}
	
	private static void closeStatement(Statement stmt) throws Exception{
		if(stmt == null){
			return;
		}
		try{
			stmt.close();
		}catch(SQLException e){
			throw new Exception(e);
		}
	}
	
	private static void closeResultSet(ResultSet rs) throws Exception{
		if(rs == null){
			return;
		}
		try{
			rs.close();
		}catch(SQLException e){
			throw new Exception(e);
		}
	}
	
	
	
	public static int save(String username, String password) throws Exception{
		int result = 0;
		String sql = "insert into userList(userName ,password) values(?,?)";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(username);
		paramList.add(password);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DbUtils.getConnection();
			pstmt = DbUtils.getPrepareStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if(pstmt == null){
				return -1;
			}
			result = pstmt.executeUpdate();
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			closeStatement(pstmt);
			closeConn(conn);
		}
		return result;
	}
	
	public static int find(String userName, String password) throws Exception{
		int result = 0;
		String sql = "select * from userList where userName = ? and password = ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(userName);
		paramList.add(password);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DbUtils.getConnection();
			pstmt = DbUtils.getPrepareStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			rs = getResultSet(pstmt);
			if(rs.next()){
				return 1;
			}
		}catch (Exception e){
			throw new Exception(e);
		}finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConn(conn);
		}
		return result;
		
	}
	
	public static boolean findUser(String userName) throws Exception{
		boolean result = false;
		String sql = "select * from userList where userName = ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(userName);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DbUtils.getConnection();
			pstmt = DbUtils.getPrepareStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			rs = getResultSet(pstmt);
			if(rs.next()){
				result = true;
			}
		}catch (Exception e){
			throw new Exception(e);
		}finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConn(conn);
		}
		return result;
	}
	
	public static List<Map<String, String>> findAll() throws Exception{
		String sql = "select * from userList";
		List<Map<String, String>> queryList = null;
		if(sql == null || sql.trim().equals("")){
			return null;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DbUtils.getConnection();
			pstmt = DbUtils.getPrepareStatement(conn, sql);
			rs = getResultSet(pstmt);
			queryList = DbUtils.getQueryList(rs);
		}catch (Exception e){
			throw new Exception(e);
		}finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConn(conn);
		}
		return queryList;
	}
	
	
	private static List<Map<String, String>> getQueryList(ResultSet rs) throws Exception{
		if(rs == null){
			return null;
		}
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount =rsMetaData.getColumnCount();
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		while(rs.next()){
			Map<String, String> dataMap = new HashMap<String, String>();
			for(int i=0; i < columnCount; i++){
				dataMap.put(rsMetaData.getColumnName(i+1),rs.getString(i+1));
			}
			dataList.add(dataMap);		
		}
		
		return dataList;		
		
	}
	
	
}
