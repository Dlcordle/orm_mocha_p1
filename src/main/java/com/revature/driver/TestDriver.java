package com.revature.driver;

import java.util.LinkedList;

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
		
		
	}

}
