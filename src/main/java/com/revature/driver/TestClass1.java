package com.revature.driver;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;
import com.revature.annotations.Table;

@Entity(entityName = "TestClass1")
@Table(tableName = "tomato", tableSchema = "mocha")
public class TestClass1 
{
	@Id(columnName = "testIntARoo")
	@Column(isUnique = "true", columnLength = "50", columnName = "testint", columnPrecision = "0", columnType = "int", isNullable = "false")
	private int testInt;
}