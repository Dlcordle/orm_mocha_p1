package com.revature.dao;

public class DeleteTable {

	private String tableName; // @Table tableName
	private String tableSchema; // @Table tableSchema
	private String primaryKeyColumnName; // @Id columnName
	private Object deleteThisValue;


	/**
	 * This method deletes a row in the database.
	 * It takes in the table name, the table schema, the primary key column name of the row to 
	 * be deleted, and the primary key value (in order to find the correct row)
	 * 
	 * @param tableName
	 * @param tableSchema
	 * @param primaryKeyColumnName
	 * @param deleteThisValue
	 * @return
	 */
	
	public int delete(String tableName, String tableSchema, String primaryKeyColumnName, Object deleteThisValue) {
		int result = 0;

		// SQL statement to DELETE row
		// We will build this throughout the program.

		String deleteValueType = deleteThisValue.getClass().getSimpleName();
		
		String defineDeleteSQL = "DELETE FROM " + tableSchema + "." + tableName 
									+ " WHERE " + primaryKeyColumnName + " = ?;";

		// Ready for a connection
		
//		try {
//			PreparedStatement stmt = conn.prepareStatement(defineDeleteSQL);
//
//			// Find the type of the value being inserted into the prepared statement.
//			// Must do this because value are stored as Objects (Strings and Wrapper classes) 
//			
//			if (deleteValueType.equals("String")) {
//				stmt.setString(1, (String) deleteThisValue);
//			} else if (deleteValueType.equals("Integer")) {
//				stmt.setInt(1, (int) deleteThisValue);
//			} else if (deleteValueType.equals("Double")) {
//				stmt.setDouble(1, (double) deleteThisValue);
//			} else if (deleteValueType.equals("Boolean")) {
//				stmt.setBoolean(1, (boolean) deleteThisValue);
//			}
//			result = stmt.executeUpdate();
//		} catch (SQLException e) {
//			result = -1;
//			e.printStackTrace();
//		}
		
		return result;
	}

	
}
