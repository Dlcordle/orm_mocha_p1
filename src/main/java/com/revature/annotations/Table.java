package com.revature.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the primary table for the annotated entity.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

	// name, catalog, schema, unique constrants
	
	String tableName(); // @Table(tableName="table_name")
	String tableCatalog(); //@Table(tableCatalog="table_catalog")
	String tableSchema(); //@Table(tableSchema="table_schema")
	UniqueConstraint[] tableUniqueConstraints() default {};
}
