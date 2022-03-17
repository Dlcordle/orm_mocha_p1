package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.revature.util.ConnectionUtil;

public class CreateTable {
	Connection conn = ConnectionUtil.getConnection();

	private String tableName; // @Table tableName
	private String tableSchema; // @Table tableSchema
	private String primaryKeyColumnName; // @Id columnName

	/**
	 * @GeneratedValue(strategy=GenerationType.IDENTITY) We need to save this
	 *                                                   somewhere when making meta
	 *                                                   models.
	 */
	private boolean primaryKeyIsSerial;

	/**
	 * @Column List of column data saved in a key=value LinkedHashMap
	 *         columnName=String, columnType=String of type name, use .getType() to
	 *         get "String", "int", "double", "boolean" isUnique=false
	 *         isNullable=true, *false* means NOT NULL columnLength=50, if 0
	 *         defaults to 50 columnPrecision=2 if NUMERIC, else 0
	 */
	private List<LinkedHashMap<String, String>> columns;

	/**
	 * @JoinColumn List of column data saved in a key=value LinkedHashMap
	 *             columnName=String columnType=String of type name, use .getType()
	 *             to get "String", "int", "double", "boolean" isUnique=false
	 *             isNullable=true, *false* means NOT NULL columnLength=50, if 0
	 *             defaults to 50 columnPrecision=2 if NUMERIC, else 0
	 */
	private List<LinkedHashMap<String, String>> foreignKeyColumns;

	// SQL statement to CREATE table
	// We will build this throughout the program.
	private String defineTableSQL = "";

	// No args constructor
	public CreateTable() {
		super();
	}

	/**
	 * CreateTable Constructor with all parameters
	 * 
	 * @param tableName
	 * @param tableSchema
	 * @param primaryKeyColumnName
	 * @param primaryKeyIsSerial
	 * @param columns
	 * @param foreignKeyColumns
	 */
	public CreateTable(String tableName, String tableSchema, String primaryKeyColumnName, boolean primaryKeyIsSerial,
			List<LinkedHashMap<String, String>> columns, List<LinkedHashMap<String, String>> foreignKeyColumns) {
		super();
		this.tableName = tableName;
		this.tableSchema = tableSchema;
		this.primaryKeyColumnName = primaryKeyColumnName;
		this.primaryKeyIsSerial = primaryKeyIsSerial;
		this.columns = columns;
		this.foreignKeyColumns = foreignKeyColumns;

	}

	/**
	 * This method does all the work. Instantiate a CreateTable object and call. For
	 * production, it returns the SQL script to create the tables. We can change
	 * this for testing.
	 */
	public String createTable() {
		defineTableSQL += "CREATE TABLE " + tableSchema + "." + tableName + " (" + primaryKeyColumnName
				+ (primaryKeyIsSerial == true ? " SERIAL" : "") + " PRIMARY KEY, ";

		defineTableSQL += getSQLType(columns);

		for (int i = 0; i < foreignKeyColumns.size(); i++) {
			defineTableSQL += getSQLType(foreignKeyColumns);
		}

		if (defineTableSQL.endsWith(", ")) {
			defineTableSQL = defineTableSQL.substring(0, (defineTableSQL.length()) - 2);
		}

		defineTableSQL += ");";

		// Ready for a connection

		try {
			PreparedStatement stmt = conn.prepareStatement(defineTableSQL);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return defineTableSQL;

	}

	/**
	 * Utility method that determines type of columns and returns SQL based on the
	 * type. Requires .getType() input - "String", "int", "double", "boolean" in the
	 * columns HashMap for the key "columnType" - I'm thinking we can set this while
	 * doing meta models - If not, we can add it to the @Column and @JoinColumn
	 * annotations properties.
	 */
	public String getSQLType(List<LinkedHashMap<String, String>> cols) {

		// Variable for SQL script to return
		String sqlTypeReturn = "";

		if (cols != null) {

			// Iterate through columns based on how many
			// works for @Columns and @JoinColumn columns
			for (int i = 0; i < cols.size(); i++) {
				String sqlType = "";
				String columnName = cols.get(i).get("columnName");
				String columnType = cols.get(i).get("columnType");
				String isUnique = cols.get(i).get("isUnique");
				String isNullable = cols.get(i).get("isNullable");
				String columnLength = cols.get(i).get("columnLength");
				String columnPrecision = cols.get(i).get("columnPrecision");

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

				sqlTypeReturn += columnName + " " + sqlType + (isUnique.equals("true") ? " UNIQUE" : "")
						+ (isNullable.equals("false") ? " NOT NULL" : "") + ", ";
			}
		} else {
			System.out.println("You left out some information.");
		}
		return sqlTypeReturn;
	}
}
