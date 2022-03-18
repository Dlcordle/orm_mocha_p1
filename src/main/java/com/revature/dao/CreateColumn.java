package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.revature.util.ConnectionUtil;

public class CreateColumn {

	Connection conn = ConnectionUtil.getConnection();

	private String tableName; // @Table tableName
	private String tableSchema; // @Table tableSchema
	private LinkedHashMap<String, String> columnData; 

	/**
	 * This method adds a column to the the table.
	 * It takes in the table name, the table schema, 
	 * and a LinkedHashMap<String, String> of properties (including the column name): property=value.
	 * The LinkedHashMap<String, Object> mirrors the way CreateTable creates columns.
	 * List of column data saved in a key=value LinkedHashMap
	 * columnName=String
	 * columnType=String of type name, use .getType() to get "String", "int", "double", "boolean"
	 * isUnique=false
	 * isNullable=true, *false* means NOT NULL
	 * columnLength=50, if 0 defaults to 50
	 * columnPrecision=2 if NUMERIC, else 0
	 * 
	 */


	public int addColumn(String tableName, String tableSchema, LinkedHashMap<String, String> columnData) {

		int result = 0;
		
		// SQL statement to ALTER table and ADD column
		// We will build this throughout the program.

		String defineAddColumnSQL = "";

		String columnName = columnData.get("columnName");
		String columnType = columnData.get("columnType");
		String isUnique = columnData.get("isUnique");
		String isNullable = columnData.get("isNullable");
		String columnLength = columnData.get("columnLength");
		String columnPrecision = columnData.get("columnPrecision");

		
		// Variable for SQL type from Object
		String sqlType = "";

		if (columnType == "String") {
			sqlType += "VARCHAR";
			if (Integer.parseInt(columnLength) > 0) {
				sqlType += "(" + columnLength + ")";
			} else {
				sqlType += "(50)";
			}
		} else if (columnType == "int") {
			sqlType += "INTEGER";
		} else if (columnType == "double") {
			sqlType += "NUMERIC";
			if (Integer.parseInt(columnLength) > 0 && Integer.parseInt(columnPrecision) > 0) {
				sqlType += "(" + columnLength + "," + columnPrecision + ")";
			} else if (Integer.parseInt(columnLength) > 0) {
				sqlType += "(" + columnLength + ")";
			} else {
				sqlType += "(50)";
			}
		} else if (columnType == "boolean") {
			sqlType = "BOOLEAN";
		} else {
			sqlType = null;
		}
		
		defineAddColumnSQL = "ALTER TABLE " + tableSchema + "." + tableName 
										+ " ADD COLUMN IF NOT EXISTS " + columnName + " "
										+ sqlType + (isUnique.equals("true") ? " UNIQUE" : "")
										+ (isNullable.equals("false") ? " NOT NULL" : "") + ";";

		System.out.println(defineAddColumnSQL);
		
		// Ready for a connection

		try {
			PreparedStatement stmt = conn.prepareStatement(defineAddColumnSQL);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return result;
	}

}
