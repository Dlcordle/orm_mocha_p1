package com.revature.util;

import java.lang.reflect.Field;
import java.util.Objects;

import com.revature.annotations.Column;

/**
 * The whole purpose of this class is to extract the
 * fields of class marked with the @Column annotation
 * which we've designed in the com.revature.annotations package.
 * 
 * What is the info we need about a field of a user's class in order
 * to generate the appropriate column?
 * 
 * 
 * - Type? 
 * - Field name?
 * - column Name (which they indicated in the column annotation meta data)
 */
public class ColumnField {
	// this class models the column that will be generated in the database
	// we will use Reflection (using classes from the Java.lang.reflect package) to understand the field
	
	private Field field; // from java.lang.reflect
	private Class<?> type;
	private String name;
	private String columnName;
	private String columnType;
	private String isUnique;
	private String isNullable;
	private String columnLength;
	private String columnPrecision;
	
	public ColumnField(String columnName,
										String columnType,
										String isUnique,
										String isNullable,
										String columnLength,
										String columnPrecision) 
	{	
		this.field = null;
		this.type = null;
		this.name = null;
		this.columnName = columnName;
		this.columnType = columnType;
		this.isUnique = isUnique;
		this.isNullable = isNullable;
		this.columnLength = columnLength;
		this.columnPrecision = columnPrecision;
		
	}
	// constructor
	public ColumnField(Field field) {
		
		// check if it has the annotation we're looking for 
		if (field.getAnnotation(Column.class) == null) { // if it's NOT equal to @Column...
			throw new IllegalStateException("Cannot create ColumnField object! Provided field " + getName() + " is not Annotated");
		}
		
		this.field = field;
		name = field.getName();
		type = field.getType();
		columnName = field.getAnnotation(Column.class).columnName();
		columnType = field.getAnnotation(Column.class).columnType();
		isUnique = field.getAnnotation(Column.class).isUnique();
		isNullable = field.getAnnotation(Column.class).isNullable();
		columnLength = field.getAnnotation(Column.class).columnLength();
		columnPrecision = field.getAnnotation(Column.class).columnPrecision();
		
	}
	
//	public String getName() {
//		return field.getName();
//	}
//	
//	// return the TYPE of the field that's annotated
//	public Class<?> getType() {
//		
//		return field.getType(); // think about how we could this to our advantage when we (as the ORM framework developers
//							    // are crafting a way in which we can  set up a way to determine the RDBMS type for the column
//	}
//
//	// getColumnName() --> extract the column name that the user sets for that field
//	public String getColumnName() {
//		return field.getAnnotation(Column.class).columnName(); // extract the columnName() property that the user sets
//	}
//	
//	public String getColumnType()
//	{
//		return field.getAnnotation(Column.class).columnType();
//	}
//	
//	public String getIsUnique()
//	{
//		return field.getAnnotation(Column.class).isUnique();
//	}
//	
//	public String getIsNullable()
//	{
//		return field.getAnnotation(Column.class).isNullable();
//	}
//	
//	public String getColumnLength()
//	{
//		return field.getAnnotation(Column.class).columnLength();
//	}
//	
//	public String getColumnPrecision()
//	{
//		return field.getAnnotation(Column.class).columnPrecision();
//	}

	
	@Override
	public int hashCode() {
		return Objects.hash(field);
	}

	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getIsUnique() {
		return isUnique;
	}
	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}
	public String getColumnPrecision() {
		return columnPrecision;
	}
	public void setColumnPrecision(String columnPrecision) {
		this.columnPrecision = columnPrecision;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColumnField other = (ColumnField) obj;
		return Objects.equals(field, other.field);
	}

	@Override
	public String toString() {
		return "ColumnField [field=" + field + "]";
	}

	
}