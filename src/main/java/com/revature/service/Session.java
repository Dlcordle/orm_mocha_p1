package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Session 
{
	private static Logger logger = Logger.getLogger(Session.class);
	private static Connection conn = null;
	
	//singleton's have a private constructors
	private Session()
	{
		
	}
	
	public static Connection getConnection()
	{
		try {
			if(conn != null && !conn.isClosed())
			{
				//logger.info("returned the re-used connection object");
				return conn;
			}
		}
		catch (SQLException e)
		{
			logger.error("we failed to re-use the connection");
			e.printStackTrace();
			return null;
		}
		
		String url = "";
		String username = "";
		String password = "";
		
		try {
			url = System.getenv("DB_URL");
			username = System.getenv("DB_USERNAME");
			password = System.getenv("DB_PASSWORD");
			
			conn = DriverManager.getConnection(url, username, password);
			
			//logger.info("Successfully connected to DB");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection()
	{
		try 
		{
			conn.close();
			System.out.println("Closing Connection");
		}
		catch(Exception e)
		{
			System.out.println("Connection failed to close");
			e.printStackTrace();
		}
	}
}
