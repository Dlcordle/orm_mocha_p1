package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class UpdateTable {
	
	private String tableName; // @Table tableName
	private int primaryKey; // @Id columnName
	private String updateColumnName;
	private Object updateThisValue;
	String primaryColumnName;


	/**
	 * This method updates a field in the database.
	 * It takes in the tableName and sets the colu
	 * 
	 * @param tableName
	 * @param tableSchema
	 * @param primaryKey
	 * @param updateThisValue
	 * @return 1 for success, -1 for failure, 0 if nothing happened
	 */
	
	
	
	public int update(String tableName, String updateColumnName, Object updateThisValue, String primaryColumnName, int primaryKey) {
		int result = 0;

		// SQL statement to UPDATE field
		// We will build this throughout the program.

		String updateValueType = updateThisValue.getClass().getSimpleName();
		
//		String primKeySQL = ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM TABLE2");
//		 ResultSetMetaData rsmd = rs.getMetaData();
//		 String name = rsmd.getColumnName(1);
		
		//String to get name of Primary Key columnName
		//String primKeySQL = "SELECT * FROM " + updateColumnName;
//		
		

		Connection conn = ConnectionUtil.getConnection();
		
		try {
			
			//Preparing statements to execute
		//	PreparedStatement stmt0 = conn.prepareStatement(primKeySQL);
			

			// Saving the primary key column name from result set
//			// into a string name primKeyColumnName
//			ResultSet rs = stmt0.executeQuery();
//			ResultSetMetaData rsmd = rs.getMetaData();
//			String primKeyColumnName = rsmd.getColumnName(1);
//			
			
			//input primKeyColumnName into the update sql statement
			String defineUpdateSQL = "UPDATE " + tableName 
					+ " SET " + updateColumnName + " = ? "
					+ "WHERE " + primaryColumnName + " = ?";
			
			
			PreparedStatement stmt = conn.prepareStatement(defineUpdateSQL);
			
			
			//stmt.executeQuery();

			// Find the type of the value being updated into the prepared statement.
			// Must do this because value are stored as Objects (Strings and Wrapper classes) 
			
			
			if (updateValueType.equals("String")) {
				stmt.setString(1, (String) updateThisValue);
			} else if (updateValueType.equals("Integer")) {
				stmt.setInt(1, (int) updateThisValue);
			} else if (updateValueType.equals("Double")) {
				stmt.setDouble(1, (double) updateThisValue);
			} else if (updateValueType.equals("Boolean")) {
				stmt.setBoolean(1, (boolean) updateThisValue);
			}
			stmt.setInt(2,  primaryKey);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			result = -1;
			e.printStackTrace();

	}
		return result;
}
}




