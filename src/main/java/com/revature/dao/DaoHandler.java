package com.revature.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.service.Parser;
import com.revature.util.ColumnField;
import com.revature.util.ForeignKeyField;
import com.revature.util.MetaModel;
import com.revature.util.PrimaryKeyField;

public class DaoHandler {
	private static Logger logger = Logger.getLogger(DaoHandler.class);

	public void CreateNewTable(MetaModel<?> createFrom, Connection conn) {
		List<LinkedHashMap<String, String>> columnsToSend = new ArrayList<LinkedHashMap<String, String>>();
		List<ColumnField> columns;
		int count = 0;

		columns = createFrom.getColumns();
		for (ColumnField col : columns) {
			columnsToSend.add(new LinkedHashMap<String, String>());
			columnsToSend.get(count).put("columnName", col.getColumnName());
			columnsToSend.get(count).put("columnType", col.getColumnType());
			columnsToSend.get(count).put("isUnique", col.getIsUnique());
			columnsToSend.get(count).put("isNullable", col.getIsNullable());
			columnsToSend.get(count).put("columnLength", col.getColumnLength());
			columnsToSend.get(count).put("columnPrecision", col.getColumnPrecision());

			count++;
		}

		List<LinkedHashMap<String, String>> foreignKeyList = new ArrayList<LinkedHashMap<String, String>>();
		count = 0;
		for (ForeignKeyField foreignHolder : createFrom.getForeignKeyFields()) {
			foreignKeyList.add(new LinkedHashMap<String, String>());
			foreignKeyList.get(count).put("columnName", foreignHolder.getColumnName());
			foreignKeyList.get(count).put("columnType", foreignHolder.getColumnType());
			foreignKeyList.get(count).put("isUnique", foreignHolder.getIsUnique());
			foreignKeyList.get(count).put("isNullable", foreignHolder.getIsNullable());
			foreignKeyList.get(count).put("columnLength", foreignHolder.getColumnLength());
			foreignKeyList.get(count).put("columnPrecision", foreignHolder.getColumnPrecision());

			count++;
		}

		// System.out.println(foreignKeyList == null);

		CreateTable tableTool = new CreateTable(createFrom.getTableName(), createFrom.getTableSchema(),
				createFrom.getPrimaryKey().getColumnName(), true, columnsToSend, foreignKeyList);
		tableTool.createTable();
	}

	public void CreateNewColumn(MetaModel<?> createFrom, String columnName, String columnType, String isUnique,
			String isNullable, String columnLength, String columnPrecision) {
		CreateColumn createColumn = new CreateColumn();
		LinkedHashMap builder = new LinkedHashMap<String, String>();
		builder.put("columnName", columnName);
		builder.put("columnType", columnType);
		builder.put("isUnique", isUnique);
		builder.put("isNullable", isNullable);
		builder.put("columnLength", columnLength);
		builder.put("columnPrecision", columnPrecision);

		createColumn.addColumn(createFrom.getTableName(), createFrom.getTableSchema(), builder);

		ColumnField newCol = new ColumnField(columnName, columnType, isUnique, isNullable, columnLength,
				columnPrecision);
		createFrom.InsertColumn(newCol);
	}

	public void DeleteExistingData(MetaModel<?> createFrom, int primaryKey) {
		DeleteData deleteData = new DeleteData();
		String primaryKeyColumnName = createFrom.getPrimaryKeyField().getColumnName();
		deleteData.delete(createFrom.getTableName(), createFrom.getTableSchema(), primaryKeyColumnName, primaryKey);
	}

	public void DeleteExistingTable(String tableName, String tableSchema) {
		DeleteTable deleteTable = new DeleteTable(tableName, tableSchema);
		deleteTable.dropTable();
	}

	public void InsertNewData(MetaModel<?> createFrom, List<Object> columnValues) {
		InsertData insertData = new InsertData();

		LinkedList<ColumnField> columnList = (LinkedList<ColumnField>) createFrom.getColumnFields();
		LinkedList<String> columnNames = new LinkedList<>();

		for (ColumnField col : columnList) {
			columnNames.add(col.getColumnName());
		}

		insertData.insert(createFrom.getTableName(), createFrom.getTableSchema(), columnNames, columnValues);
	}

	public void PrintTable(MetaModel<?> createFrom) {
		ReadTable read = new ReadTable();
		read.read(createFrom.getTableName(), 0);
	}

	public void UpdateExistingData(MetaModel<?> createFrom, String updateColumnName, Object updateThisValue,
			int primaryKey) {
		UpdateTable up = new UpdateTable();
		up.update(createFrom.getTableName(), updateColumnName, updateThisValue, createFrom.getPrimaryKey().getName(),
				primaryKey);
	}
}
