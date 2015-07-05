package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Thierry Edson Noumessi
 * @
 * @date Jul 2, 2015
 * @3:08:04 PM
 * @BaseDAO.java
 */

public abstract class BaseDAO<T> {
	
  

//	String driver = "com.mysql.jdbc.Driver";
//	String connection = "jdbc:mysql://localhost:3306/library";
//	String user = "root";
//	String pass = "edson999";
	
	private Connection Connection;
	public BaseDAO(Connection conn) throws Exception{
		this.Connection = conn;
	}
	private BaseDAO(){
		
	}
		
	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException{
	
	return Connection;
	}
	
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public abstract List<T> extractData(ResultSet rs) throws Exception;
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public abstract List<T> extractDataFirstLevel(ResultSet rs) throws Exception;

	/**
	 * 
	 * @param query
	 * @param vals
	 * @throws Exception
	 */
	public void save(String query, Object[] vals) throws Exception{
		Connection conn = getConnection();

		PreparedStatement stmt = conn.prepareStatement(query);
		int count = 1;
		for(Object o: vals){
			stmt.setObject(count, o);
			count++;
		}
       // System.out.println(stmt.toString());
        if(stmt.executeUpdate()==1){
        	
        }else{
        	System.out.println("This Id does not exist");
        }
        	
	}
	/**
	 * 
	 * @param query
	 * @param vals
	 * @return
	 * @throws Exception
	 */
	
	public int saveWithID(String query, Object[] vals) throws Exception{
		Connection conn = getConnection();

		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		for(Object o: vals){
			stmt.setObject(count, o);
			count++;
		}
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()){
			return rs.getInt(1);
		}else{
			return -1;
		}
	}

	/**
	 * Return list of books along with the details(Authors,Publishers,Genres) about the books
	 * @param query
	 * @param vals
	 * @return
	 * @throws Exception
	 */
	public List<?> read(String query, Object[] vals) throws Exception{
		List<T> objects = new ArrayList<T>();
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		if(vals!=null){
			int count = 1; 
			for(Object o: vals){
				stmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = stmt.executeQuery();
	//	System.out.println(rs.toString());
		return extractData(rs);
	}
/**
 * Return list of books without revealing the details(Authors,Publishers,Genres) about the books
 * @param query
 * @param vals
 * @return
 * @throws Exception
 */
	public List<?> readFirstLevel(String query, Object[] vals) throws Exception{
		List<T> objects = new ArrayList<T>();
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count = 1;
			for(Object o: vals){
				stmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = stmt.executeQuery();
		return extractDataFirstLevel(rs);
	}
	
	
	

}