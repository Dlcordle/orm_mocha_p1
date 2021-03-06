package com.revature.util;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;
import com.revature.annotations.JoinColumn;
import com.revature.annotations.Table;
import com.revature.service.Parser;

/**
 * The purpose of this class is to gather as much information as possible about
 * the class we want to transpose into a DB entity (table)
 * 
 * This class' job is to model data about another class.
 */
public class MetaModel<T> 
{ 
	private Class<?> clazz;
	private String tableName = "";
	private String tableSchema = "";
	private PrimaryKeyField primaryKeyField;
	private List<ColumnField> columnFields;
	private List<ForeignKeyField> foreignKeyFields;
	
	private static Logger logger = Logger.getLogger(MetaModel.class);

	// create a method in which we pass a class through and generate a meta model OF
	// the class
	public static MetaModel<Class<?>> of(Class<?> clazz) {
		// check that the class we're attempting to transpose is annotated with @Entity
		if (clazz.getAnnotation(Entity.class) == null) {
			throw new IllegalStateException("Cannot create MetaModel object from this class! Provided class "
					+ clazz.getName() + " is not annotated with @Entity");
		}
		
		// if it IS annotated with @Entity, generate a MetaModel object of it.
		return new MetaModel<Class<?>>(clazz);
	}

	// constructor to build a metamodel
	public MetaModel(Class<?> clazz) {
		this.clazz = clazz; // since we've set the class as equal to the original class, we still intel on
							// its fields
		this.columnFields = new LinkedList<ColumnField>();
		this.foreignKeyFields = new LinkedList<ForeignKeyField>();
		setTableNameSchema();
		primaryKeyField = getPrimaryKey();
		columnFields = getColumns();
		foreignKeyFields = getForeignKeys();
	}
	
	
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableNameSchema() {
		try {
			System.out.println(clazz);
			tableName = clazz.getAnnotation(Table.class).tableName();
			tableSchema = clazz.getAnnotation(Table.class).tableSchema();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	
	public void InsertColumn(ColumnField newCol)
	{
		columnFields.add(newCol);
	}

	// getColumns() - returns a list of ColumnField
	public List<ColumnField> getColumns() {

		Field[] fields = clazz.getDeclaredFields();

		// check if each field has a @Column annotation
		// if it does, add it to the metamodel's columnField's list
		for (Field field : fields) {
			//System.out.println("fields: "+fields.length);
			// the column reference variable will NOT be null if the field is indeed
			// annotated with @Column
			Column column = field.getAnnotation(Column.class);

			if (column != null) {
				// if the column is indeed marked with @Colum, instantiate a new ColumnField
				// object with its data
				if(!columnFields.contains(new ColumnField(field)))
				{
					columnFields.add(new ColumnField(field));
				}
				// now that we've transposed the field to a column Field object, we can capture
				// data like Type, columnName, etc...
			}
		}
		try
		{
			if (columnFields.isEmpty()) {
				throw new RuntimeException("No columns found in: " + clazz.getName());
			}
		}
		catch (Exception e)
		{
			logger.warn(e.getMessage());
		}
		return columnFields;
	}

	// getPrimaryKey() - return a PrimaryKeyField or throw a runtime exception if we
	// can't find one
	public PrimaryKeyField getPrimaryKey() {

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {

			Id primaryKey = field.getAnnotation(Id.class);

			if (primaryKey != null) { // we se the primaryKeyField of this MetaModel to the field we find 
				this.primaryKeyField = new PrimaryKeyField(field);
				return new PrimaryKeyField(field); // if there is a PK found, we end the method here and return it
			}
		}
		try
		{
			throw new RuntimeException("Did not find a field annotated with @Id in " + clazz.getName());
		}
		catch (Exception e)
		{
			logger.warn(e.getMessage());
		}
		return null;
	}

	// getForeignKey - returns a list of foreignKeyFields
	public List<ForeignKeyField> getForeignKeys() {

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) 
		{

			JoinColumn foreignKey = field.getAnnotation(JoinColumn.class);

			if (foreignKeyFields != null && foreignKey != null) {
				//System.out.println(field);
				if(!foreignKeyFields.contains(new ForeignKeyField(field)))
				{
					foreignKeyFields.add(new ForeignKeyField(field));
				}
			}

		}
		try
		{
			if (foreignKeyFields.isEmpty()) {
				throw new RuntimeException("No foreign keys found in: " + clazz.getName());
			}
		}
		catch (Exception e)
		{
			logger.warn(e.getMessage());
		}
		
		return foreignKeyFields;
	}
	
	public String getSimpleClassName() {
		return clazz.getSimpleName();
	}
	
	public String getClassName() {
		return clazz.getName(); // reutrns  the package of where the class came from as well
	}

	public PrimaryKeyField getPrimaryKeyField() {
		return primaryKeyField;
	}

	public void setPrimaryKeyField(PrimaryKeyField primaryKeyField) {
		this.primaryKeyField = primaryKeyField;
	}

	public List<ColumnField> getColumnFields() {
		return columnFields;
	}

	public void setColumnFields(List<ColumnField> columnFields) {
		this.columnFields = columnFields;
	}

	public List<ForeignKeyField> getForeignKeyFields() {
		return foreignKeyFields;
	}

	public void setForeignKeyFields(List<ForeignKeyField> foreignKeyFields) {
		this.foreignKeyFields = foreignKeyFields;
	}

	public void AddNewColumnField(Field field)
	{
		columnFields.add(new ColumnField(field));
	}
	
	@Override
	public String toString() {
		return "MetaModel [clazz=" + clazz + ", primaryKeyField=" + primaryKeyField + ", columnFields=" + columnFields
				+ ", foreignKeyFields=" + foreignKeyFields + "]";
	} 

	
}
