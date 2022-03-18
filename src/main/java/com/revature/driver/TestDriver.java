package com.revature.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.revature.dao.CreateColumn;
import com.revature.dao.DaoHandler;
import com.revature.dao.ReadTable;
import com.revature.dao.UpdateTable;
import com.revature.service.Config;
import com.revature.service.Parser;
import com.revature.service.Session;
import com.revature.util.MetaModel;

public class TestDriver 
{

	public static void main(String[] args) 
	{
		Session ses = new Session();
		Config config = new Config();
		config.setUrl(System.getenv("DB_URL"));
		config.setUsername(System.getenv("DB_USERNAME"));
		config.setPassword(System.getenv("DB_PASSWORD"));
		
		ses.setConfiguration(config);
		
		Connection conn = ses.getConnection();
		
		LinkedList<Class<?>> testClassList = new LinkedList<>();
		
		testClassList.add(TestClass1.class);
		testClassList.add(TestClass2.class);
		
		config.setClassesToRead(testClassList);
		
		List<MetaModel<?>> holder = config.getModels();
		DaoHandler dao = new DaoHandler();
		dao.CreateNewTable(holder.get(0), conn);
		dao.CreateNewTable(holder.get(1), conn);
	}

}
	
