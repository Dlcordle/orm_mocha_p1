package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.revature.annotations.Column;
import com.revature.util.ConnectionUtil;

public class ReadTable {
	
	private String tableName;
	
	public List<Column> read(String tableName, int index) {
		
		String sql = "SELECT * FROM " + tableName;
		
		Connection conn = ConnectionUtil.getConnection();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				int colCount = rs.getMetaData().getColumnCount();
				for (int i = 1; i<= colCount; i++) {
					System.out.print(rs.getObject(i) + "|");
				}
				System.out.println();
			}
					
					
		
		
		} catch(Exception e) {
			
		}
		return null;

	
	}	
	
}
