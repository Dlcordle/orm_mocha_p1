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

	public static void main(String[] args) {
		InsertData insertData = new InsertData();

		List<String> cols = new ArrayList<String>();
		cols.add("balance");
		cols.add("is_active");
		cols.add("user_id");

		List<Object> vals = new ArrayList<Object>();
		vals.add(4000.00);
		vals.add(true);
		vals.add(10);

		insertData.insert("accounts", "proj1", cols, vals);

	}


	public void setType(PreparedStatement stmt, int position, Object value) {
		
		String type = value.getClass().getSimpleName();
		try {
			if (type.equals("String")) {
				stmt.setString(position, (String) value);
			} else if (type.equals("int")) {
				stmt.setInt(position, (int) value);
			} else if (type.equals("double")) {
				stmt.setDouble(position, (double) value);
			} else if (type.equals("boolean")) {
				stmt.setBoolean(position, (boolean) value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
