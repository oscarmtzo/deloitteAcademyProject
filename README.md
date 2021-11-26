# Deloitte Academy - final project
## Introduction
This project covers the following topics:
- <a src="#backend">Backend</a>
  - Spring JPA - Java Persistence API
  - Spring Data JPA

- Frontend
  - JS / HTML5 / CSS3
  
## <a id="backend">Backend</a>
### Spring JPA - Java Persistence API
It is basically a specification for accessing, persisting (not loosing data while stopping the execution of a  Java app) and / or managing data between Java Objects.

#### *Implementation of JPA through Hibernate*

It takes any java object and maps it to a Database using the concept of ORM (**O**bject **R**elational **M**apping).

An Example of that implementation is as follows:
- A Java class is represented as a SQL Table, therefore we can perfom CRUD (**C**reate **R**ead **U**pdate **D**elete) operations.

```Spring Data JPA``` is an abstraction on top of ``JPA`` and ``Hibernate`` which makes easy to work with applications that needs access to ``database``, it gives us lots of SQL generated queries without having to write SQL code.

### Some Backend Specific Dependencies installed on this project

- **Spring Web**: is used to build web apps, to develop API's towards RESTful (type of **RE**presentational **S**tate **T**ransfer protocol) to make CRUD operations over HTTP (Hyper Text Transfer Protocol); it uses Tomcat (*software that allows a web server manage dynamic content based on Java using HTTP*) as the default embedded container.

- **Thymeleaf**: is a template engine/processor, also a software that allows HTML to be correctly displayed in browsers and as static prototypes.
  - <img src="https://upload.wikimedia.org/wikipedia/commons/c/c7/TempEngGen015.svg" width="100px"/>
  
- **<a src="https://h2database.com/html/main.html">H2</a>**: as our SQL-type database engine, it allow us to connect to an H2 database.
  - <img src="https://h2database.com/html/images/console-2.png" width="200px"/>
- **spring-boot-starter-data-jpa**: it is an specification for managing relational databases (specific form of access and persistance of data between object / class and relational databases).

### Configuring Database Schema for H2
- Using the provided database tables and definitions on ``src/resources/schema.sql``, and using on the same location `data.sql` as the one SQL to insert data to tables.

- In `./pom.xml` (Project Object Model) for the Maven configuration to download dependencies is necessary to insert the following XML code to configure dependencies not added while generating the project on <a src ="https://start.spring.io/">Spring initializer</a> :
- 
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

- On ``./src/main/resources/application.properties`` file, is neccesary to configure in order to let spring create the schema and run the SQL for database: 
```
spring.jpa.hibernate.ddl-auto=none
logging.level.org.springframework.jdbc.datasource.init.ScriptUtils=debug
```
- Reloading all Maven projects <img src="https://i.stack.imgur.com/6HnBZ.png" width="150px"/>, helps to download the new added dependencies.
  - Now we are good to run the main java file on ```./src/main/java/com.example.deloitteAcademy.deloitteAcademyDeloitteAcademyApplication.java```, to display output on the console of SQL declarations of insertions and creation of the database.
Example output:
```
2021-11-25 15:31:40.820 DEBUG 26596 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : 0 returned as update count for SQL: ALTER TABLE TREATMENT_MEDICATION ADD FOREIGN KEY (MEDICATION_ID) REFERENCES MEDICATIONS(MEDICATION_ID)
2021-11-25 15:31:40.823 DEBUG 26596 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : 0 returned as update count for SQL: ALTER TABLE TREATMENT_MEDICATION ADD FOREIGN KEY (TREATMENT_ID) REFERENCES TREATMENTS(TREATMENT_ID)
2021-11-25 15:31:40.826 DEBUG 26596 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : 0 returned as update count for SQL: ALTER TABLE PETS ADD FOREIGN KEY (OWNER_ID) REFERENCES OWNERS(OWNER_ID)
2021-11-25 15:31:40.829 DEBUG 26596 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : 0 returned as update count for SQL: ALTER TABLE PETS ADD FOREIGN KEY (SPECIE_ID) REFERENCES SPECIES(SPECIE_ID)
2021-11-25 15:31:40.832 DEBUG 26596 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : 0 returned as update count for SQL: ALTER TABLE PET_VACCINE ADD FOREIGN KEY (PET_ID) REFERENCES PETS(PET_ID)
2021-11-25 15:31:40.834 DEBUG 26596 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : 0 returned as update count for SQL: ALTER TABLE PET_VACCINE ADD FOREIGN KEY (VACCINE_ID) REFERENCES VACCINES(VACCINE_ID)
```

### Creating entities 

**<a src="https://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html#:~:text=An%20entity%20is%20a%20lightweight,entities%20can%20use%20helper%20classes."> Entity</a>** is a lightweight persistence domain object, whose purpose is to **represent a table in a relational database**, and each instance corresponds to a row in that table.
Therefore the persistent state of an entity or java class is represented through:
- Persistent fields or properties
  - those fields use object/mapping annotations to map the entities and their relation with data that will be stored.

Spring Data JPA will serve us to tell it that our POJO - Plain Old Java Object, will be the key entrance to a SQL database by using an ```@Table``` to describe the same name as the tables created, also it needs to specify that each instance of an entity will be a row in a table using ```@Entity``` annotation.

1. Adding the directory ``entity`` inside ```./src/main/java/com.example.deloitteAcademy.deloitteAcademy```, for our 2 entities according to the 2 tables *TourPackage* and *Tour*. 
   1. Inside we wil create 2 Data Table Objects **TourPackage** and **Tour**
      1. ``TourPackage`` needs several fields as well as getters, setters and toString methods: 

```
@Table(name="tour_package")
@Entity
public class TourPackage implements Serializable {
    @Id
    private String code;
    @Column
    private String name;
    
    protected TourPackage() {
    }

    // getters, setters and toString()
}
```

  b. In addition to ``TourPackage``, before creation of **Tour entity** it is needed 2 enum classes:
1. ``Region`` enum class to transform values from "region" field of the "Tour" table to attribute in the Entity and vice versa.
```
public enum Region {
    Central_Coast("Central Coast"),
    Southern_California("Southern California"),
    Northern_California("Northern California"),
    Varies("Varies");
    private String label;
    private Region(String label) {
        this.label = label;
    }
    public static Region findByLabel(String byLabel) {
        for(Region r:Region.values()) {
            if (r.label.equalsIgnoreCase(byLabel))
            return r;
        }
        return null;
    }
    public String getLabel() {
        return label;
    }
}
```
2. ``RegionConverter`` class is used when having textual values in the database and these values can be different (uppercase, lowercase, accents, etc..).
```
@Converter(autoApply = true)
public class RegionConverter implements AttributeConverter<Region, String> {
    @Override
    public String convertToDatabaseColumn(Region region) {
        return region.getLabel();
    }

    @Override
    public Region convertToEntityAttribute(String s) {
        return Region.findByLabel(s);
    }
}
```
3. ``Difficulty`` enum class to add different Difficulty options.
```
public enum Difficulty {
    Easy, Medium, Difficult, Varies;
}
```

ii. Tour Entity Data Transfer Object
```
@Entity
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String blurb;

    @Column
    private Integer price;

    @Column
    private String duration;

    @Column(length = 2000)
    private String bullets;
    @Column
    private String keywords;
    @ManyToOne
    @JoinColumn(name="tour_package_code")
    private TourPackage tourPackage;
    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @Column
    private Region region;

    //getters, setters and toString() methods
}

```

TourPackage attribute has ”@ManyToOne @JoinColumn(name="tour_package_code")”
because “Tour” table have several “tour_Package code”. One TourPackage has Many different
tours.

### Creating Spring Boot Restful Services

## Frontend
- Added an index.html file on the path ``./src/main/resources/static``.