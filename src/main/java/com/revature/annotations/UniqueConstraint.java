package com.revature.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraint {
    String constraintName(); // @UniqueConstraint(constraintName="constraint_name")
    String[] columnNames(); // @UniqueConstraint(columnNames={col1, col2, col3})
}
