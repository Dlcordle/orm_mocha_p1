# ORM Mocha

## Project Description

Mocha is a java based ORM for simplifying connecting to and from an SQL database without the need for SQL or connection management.

## Technologies Used

- PostgreSQL - version 42.2.12
- Java - version 8.0
- Apache commons - version 2.1

## Features

- Easy to use and straightforward user API.
- No need for SQL or any databse specific language.
- Annotation-based for ease of use.

## Getting Started

Mocha must be included as local dependency.

Start with:

```shell
  git clone https://github.com/orm_mocha_p1.git
  cd orm_mocha_p1
  mvn install
```

Next, place the following inside your project pom.xml file:

```XML
  <dependency>
    <groupId>com.revature</groupId>
    <artifactId>orm_mocha_p1</artifactId>
    <version>1.0-SNAPSHOT</version>
  </dependency>

```

Finally, inside your project structure you need an application.properties file (typically located src/main/resources/).

```
 url=path/to/database
 admin-usr=username/of/database
 admin-pw=password/of/database
```

## Usage

### Annotating classes

All classes which represent objects in database must be annotated.

- #### @Entity(name = "entity_name")
  - Indicates that this class is associated with table 'table_name'
- #### @Table(tableName = "table_name", tableCatalog="table_catalog", tableSchema="table_schema", uniqueConstraints = @UniqueConstraint (columnNames = {"", "", ... }))
  - Indicates that this class is associated with
    - table 'table_name'
    - catalog 'table_catalog', default=""
    - the schema 'table_schema'
    - unique constraints 'unique_constraints'
      - uses @UniqueConstraint annotation, default={}
- #### @Column(columnName = "column_name", columnType = "column_type", isUnique = "is_unique", isNullable = "is_nullable", columnLength = "column_length", columnPrecision = "column_precision")
  - Indicates that the Annotated field is a column in the table with
    - name 'column_name'
    - type 'column_type'
    - unique as a boolean 'is_unique'
    - nullable as a boolean 'is_nullable'
    - length 'column_length'
    - precision 'column_precision'
- #### @Id(name = "column_name")
  - Indicates that the annotated field is the primary key for the table with name 'column_name'
- #### @GeneratedValue(strategy = GenerationType.IDENTITY)
  - sets primary key column as SERIAL in database
- #### @JoinColumn(name = "column_name")
  - Indicates that the annotated field is a foreign key with name 'column_name'

## License

This project uses the following license: [GNU Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
