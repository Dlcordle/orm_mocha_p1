package com.revature.driver;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;

@Entity(entityName = "TestClass1")
public class TestClass1 
{
	@Id(columnName = "testInt")
	private int testInt;
}
