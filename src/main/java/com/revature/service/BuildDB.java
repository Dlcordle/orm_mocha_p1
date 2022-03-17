package com.revature.service;

public class BuildDB 
{
	private Config configuration;
	
	public BuildDB(Config config)
	{
		Session ses = new Session();
		Parser parse = new Parser();
		configuration = config;
		
		ses.setConfiguration(configuration);
		
		ses.getConnection();
	}
}
