package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;


import com.revature.service.Config;
import com.revature.service.Session;

public class ConnectionUtil 
{
	private static Logger logger = Logger.getLogger(Session.class);
	private static Connection conn = null;
	private static Config configuration = null;
	
	private static BasicDataSource ds = new BasicDataSource();
	
	static 
	{
        ds.setUrl("");
        ds.setUsername("");
        ds.setPassword("");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }
	
	public static void setUrl(String URL)
	{
		ds.setUrl(URL);
	}
	public static void setUsername(String URL)
	{
		ds.setUsername(URL);
	}
	public static void setPassword(String URL)
	{
		ds.setPassword(URL);
	}
	
	public static Connection getConnection()
	{
		try 
		{
			conn = ds.getConnection();
			logger.info("Connected to database");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection()
	{
		try 
		{
			ds.close();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
}
