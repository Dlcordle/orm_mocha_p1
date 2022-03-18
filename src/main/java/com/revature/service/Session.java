package com.revature.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.login.Configuration;

import org.apache.log4j.Logger;

import com.revature.dao.DaoHandler;
import com.revature.util.ColumnField;
import com.revature.util.ConnectionUtil;
import com.revature.util.MetaModel;

public class Session 
{
	private static Logger logger = Logger.getLogger(Session.class);
	private static Connection conn = null;
	private static Config configuration = null;
	private static ConnectionUtil connectionU;
	private static DaoHandler dao;
	
	private static Parser parse;
	
	public Session()
	{
		connectionU = new ConnectionUtil();
		parse = new Parser();
		dao = new DaoHandler();
	}
	
	public Session(Config configuration)
	{
		connectionU = new ConnectionUtil();
		this.configuration = configuration;
		connectionU.setUrl(configuration.getUrl());
		connectionU.setUsername(configuration.getUsername());
		connectionU.setPassword(configuration.getPassword());
		dao = new DaoHandler();
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
	
	public void buildAllTables()
	{
		for(MetaModel<?> holder : configuration.getModels())
		{
			dao.CreateNewTable(holder, conn);
		}
	}
	
	public void buildNewTable(MetaModel<?> toInsert)
	{
		dao.CreateNewTable(toInsert, conn);
	}
	
	public void addColumn(MetaModel<?> fromTable, String columnName,
			String columnType,
			String isUnique,
			String isNullable,
			String columnLength,
			String columnPrecision)
	{
		dao.CreateNewColumn(fromTable, columnName, columnType, isUnique, isNullable, columnLength, columnPrecision);
	}
	
	public void deleteExistingTable(String tableName, String tableSchema)
	{
		dao.DeleteExistingTable(tableName, tableSchema);
	}
	
	public void insertNewData(MetaModel<?> createFrom, List<Object> columnValues)
	{	
		dao.InsertNewData(createFrom, columnValues);
	}
	
	public void printTable(MetaModel<?> createFrom)
	{
		dao.PrintTable(createFrom);
	}
	
	public void updateExistingData(MetaModel<?> createFrom, String updateColumnName, Object updateThisValue, int primaryKey)
	{
		dao.UpdateExistingData(createFrom, updateColumnName, updateThisValue, primaryKey);
	}
	
//	public void save(Object newObject, Serializable id)
//	{
//		newObject.getClass().
//	}
}
