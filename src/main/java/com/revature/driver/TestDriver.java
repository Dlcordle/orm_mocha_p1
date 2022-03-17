package com.revature.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

import com.revature.dao.ReadTable;
import com.revature.dao.UpdateTable;
import com.revature.service.Config;
import com.revature.service.Parser;
import com.revature.service.Session;

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
		
		ses.getConnection();
		
		Parser parseTest = new Parser();
		
		LinkedList<Class<?>> testClassList = new LinkedList<>();
		
		testClassList.add(TestClass1.class);
		
		System.out.println(parseTest.inspectClass(testClassList));
	
		
	
		
		Connection con = null;
      try {
         con = DriverManager.
        		 getConnection("jdbc:postgresql://team-1-enterprise.cvtq9j4axrge.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=mocha", "postgres", "postgres");
        		 System.out.println("Connection is successful !!!!!");
        		       } catch(Exception e) {
        		          e.printStackTrace();
        		       }		
//      
      ReadTable read = new ReadTable();
//		
		read.read("recipes",0);
		
	UpdateTable up = new UpdateTable();
	up.update("recipes", "recipe_name","enchilada", "recipe_id", 1);
	
	read.read("recipes", 0);
	}
      
		
	
	//read.read("recipes",0);

}
	
