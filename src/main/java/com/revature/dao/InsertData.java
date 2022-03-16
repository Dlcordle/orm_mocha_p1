package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class InsertData {

	private String tableName; // @Table tableName
	private String tableSchema; // @Table tableSchema
	private List<String> columnList;
	private List<Object> valueList;

	/**
	 * This method inserts a row into the the table.
	 * It takes in the table name, the table schema, 
	 * a list of type String of the column names
	 * and a list of type Object of the column values.
	 * Storing the values as Objects allows us to decipher the
	 * type due to String and Wrapper classes 
	 * 
	 * 
	 * @param tableName
	 * @param tableSchema
	 * @param columnNames
	 * @param columnValues
	 * @return 1 for success, -1 for failure, 0 if nothing happened
	 */
	
	public int insert(String tableName, String tableSchema, List<String> columnNames, List<Object> columnValues) {

		int result = 0;

		String columns = "";

		String placeholders = "";

		if (columnNames != null && columnValues != null) {
			if (columnNames.size() == columnValues.size()) {
				for (int i = 0; i < columnNames.size(); i++) {
					if (i != (columnNames.size() - 1)) {
						columns += columnNames.get(i) + ", ";
						placeholders += "?, ";
					} else {
						columns += columnNames.get(i);
						placeholders += "?";
					}
				}
				result = 1;
			} else {
				result = -1;
				System.out.println("Something went wrong inserting data.");
			}
		}

		String defineInsertSQL = "INSERT INTO " + tableSchema + "." + tableName + " (" + columns + ") " + "VALUES ("
				+ placeholders + ");";

		// Ready for a connection

//		try {
//			PreparedStatement stmt = conn.prepareStatement(defineInsertSQL);
//
//			for (int i = 0; i < columnValues.size(); i++) {
//				
//				Object value = columnValues.get(i);
//				String valueType = value.getClass().getSimpleName();
//
//				int position = i+1;
//		
//				// Find the type of the value being inserted into the prepared statement.
//				// Must do this because value are stored as Objects (Strings and Wrapper classes) 
//		
//				if (valueType.equals("String")) {
//					stmt.setString(position, (String) value);
//				} else if (valueType.equals("Integer")) {
//					stmt.setInt(position, (int) value);
//				} else if (valueType.equals("Double")) {
//					stmt.setDouble(position, (double) value);
//				} else if (valueType.equals("Boolean")) {
//					stmt.setBoolean(position, value.equals(true));
//				}
//			}
//			System.out.println(stmt);
//
//			stmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}


		return result;
	}
}
