package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.security.auth.login.Configuration;

import org.apache.log4j.Logger;

import com.revature.util.ConnectionUtil;

public class Session 
{
	private static Logger logger = Logger.getLogger(Session.class);
	private static Connection conn = null;
	private static Config configuration = null;
	private static ConnectionUtil connection;
	
	public Session()
	{
		connection = new ConnectionUtil();
	}
	
	public Session(Config configuration)
	{
		connection = new ConnectionUtil();
		this.configuration = configuration;
		connection.setUrl(configuration.getUrl());
		connection.setUsername(configuration.getUsername());
		connection.setPassword(configuration.getPassword());
	}
	
	public void setConfiguration(Config configuration)
	{
		this.configuration = configuration;
		connection.setUrl(configuration.getUrl());
		connection.setUsername(configuration.getUsername());
		connection.setPassword(configuration.getPassword());
	}
	
	public Connection getConnection()
	{
		return connection.getConnection();
	}
}
