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
	
	// constructor
	public ColumnField(Field field) {
		
		// check if it has the annotation we're looking for 
		if (field.getAnnotation(Column.class) == null) { // if it's NOT equal to @Column...
			throw new IllegalStateException("Cannot create ColumnField object! Provided field " + getName() + " is not Annotated");
		}
		
		this.field = field;
		
	}
	
	public String getName() {
		return field.getName();
	}
	
	// return the TYPE of the field that's annotated
	public Class<?> getType() {
		
		return field.getType(); // think about how we could this to our advantage when we (as the ORM framework developers
							    // are crafting a way in which we can  set up a way to determine the RDBMS type for the column
	}

	// getColumnName() --> extract the column name that the user sets for that field
	public String getColumnName() {
		return field.getAnnotation(Column.class).columnName(); // extract the columnName() property that the user sets
	}
	
	public String getColumnType()
	{
		return field.getAnnotation(Column.class).columnType();
	}
	
	public String getIsUnique()
	{
		return field.getAnnotation(Column.class).isUnique();
	}
	
	public String getIsNullable()
	{
		return field.getAnnotation(Column.class).isNullable();
	}
	
	public String getColumnLength()
	{
		return field.getAnnotation(Column.class).columnLength();
	}
	
	public String getColumnPrecision()
	{
		return field.getAnnotation(Column.class).columnPrecision();
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(field);
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