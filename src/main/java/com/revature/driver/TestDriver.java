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
		
		Parser parseTest = new Parser();
		
		LinkedList<Class<?>> testClassList = new LinkedList<>();
		
		//testClassList.add(TestClass1.class);
		testClassList.add(TestClass2.class);
		
		
		List<MetaModel<?>> holder = parseTest.inspectClass(testClassList);
		//System.out.println(parseTest.inspectClass(testClassList));
		DaoHandler dao = new DaoHandler();
		dao.CreateNewTable(holder.get(0), conn);
		
		Connection con = null;
      try {
         con = DriverManager.
        		 getConnection("jdbc:postgresql://team-1-enterprise.cvtq9j4axrge.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=mocha", "postgres", "postgres");
        		 System.out.println("Connection is successful !!!!!");
        		       } catch(Exception e) {
        		          e.printStackTrace();
        		       }		
////      
//      ReadTable read = new ReadTable();
////		
//		read.read("recipes",0);
//		
//	UpdateTable up = new UpdateTable();
//	up.update("recipes", "recipe_name","enchilada", "recipe_id", 1);
//	
//	read.read("recipes", 0);
//      
		
	
	//read.read("recipes",0);
	
	LinkedHashMap<String, String> hashMap2 = new LinkedHashMap();

	hashMap2.put("columnName", "extra_column_1");
	hashMap2.put("columnType", "String");
	hashMap2.put("isUnique", "false");
	hashMap2.put("isNullable", "true");
	hashMap2.put("columnLength", "50");
	hashMap2.put("columnPrecision", "0");

	CreateColumn cc = new CreateColumn();
	cc.addColumn("users2", "mocha", hashMap2);

	}

}
	
