package com.revature.driver;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;
import com.revature.annotations.Table;

@Entity(entityName = "TestClass2")
@Table(tableName = "potato", tableSchema = "mocha")
public class TestClass2
{
	@Id(columnName = "testIntARoo")
	@Column(isUnique = "true", columnLength = "50", columnName = "testint", columnPrecision = "0", columnType = "int", isNullable = "false")
	private int testInt;
	
	@Column(isUnique = "false", columnLength = "50", columnName = "testint", columnPrecision = "0", columnType = "int", isNullable = "false")
	private int testIn2t;
}

