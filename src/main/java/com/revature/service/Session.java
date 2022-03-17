package com.revature.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.security.auth.login.Configuration;

import org.apache.log4j.Logger;

import com.revature.dao.DaoHandler;
import com.revature.util.ConnectionUtil;
import com.revature.util.MetaModel;

public class Session 
{
	private static Logger logger = Logger.getLogger(Session.class);
	private static Connection conn = null;
	private static Config configuration = null;
	private static ConnectionUtil connectionU;
	private static DaoHandler dao;
	
	public Session()
	{
		connectionU = new ConnectionUtil();
	}
	
	public Session(Config configuration)
	{
		connectionU = new ConnectionUtil();
		this.configuration = configuration;
		connectionU.setUrl(configuration.getUrl());
		connectionU.setUsername(configuration.getUsername());
		connectionU.setPassword(configuration.getPassword());
	}
	
	public void setConfiguration(Config configuration)
	{
		this.configuration = configuration;
		connectionU.setUrl(configuration.getUrl());
		connectionU.setUsername(configuration.getUsername());
		connectionU.setPassword(configuration.getPassword());
	}
	
	public Connection getConnection()
	{
		return connectionU.getConnection();
	}
	
	public void buildTables()
	{
		for(MetaModel<?> holder : configuration.getModels())
		{
			dao.CreateNewTable(null, conn);
		}
	}
	
	public void save(Object newObject, Serializable id)
	{
		
	}
}
