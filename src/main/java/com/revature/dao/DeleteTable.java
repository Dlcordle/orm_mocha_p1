package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class DeleteTable {

	Connection conn = ConnectionUtil.getConnection();

	private String tableName; // @Table tableName
	private String tableSchema; // @Table tableSchema

	// SQL statement to DROP table
	// We will build this throughout the program.
	private String defineTableSQL = "";

	// No args constructor
	public DeleteTable() {
		super();
	}

	/**
	 * DeleteTable Constructor with all parameters
	 * 
	 * @param tableName
	 * @param tableSchema
	 */

	public DeleteTable(String tableName, String tableSchema) {
		super();
		this.tableName = tableName;
		this.tableSchema = tableSchema;
	}

	/**
	 * This method does all the work. Instantiate a DeleteTable object and call. For
	 * production, it returns the SQL script to drop the tables. We can change this
	 * for testing.
	 */
	public String dropTable() {
		defineTableSQL += "DROP TABLE IF EXISTS " + tableSchema + "." + tableName + " CASCADE;";

		// Ready for a connection

		try {
			PreparedStatement stmt = conn.prepareStatement(defineTableSQL);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return defineTableSQL;
	}

	
}
