# Spring And Hibernate Course

## Section 4: Spring Inversion of Control - XML Configuration

Inversion of control is the approact of outsourcing the construction and management of objects. In other words, it's an outsource to an object factory.

### Spring container
Primary functions is
1. Create and manage objects (Inverson of control)
2. Inject object's dependencies (Dependency injection)

### Configuring spring container
- XML configuration file (legacy, but most legacy apps still use this)
- Java annotations (modern)
- Java source code (modern)

### Spring development process
1. Configure your spring beans
```xml
<beans>
    <bean id="myCoach" class="com.luv2code.springdemo.BaseballCoach"></bean>
</beans>
```
The **id** is like an alias

The **class** is fully qualified class name of implementation class

2. Create spring container

Spring container is generally known as ApplicationContext
```java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
```

3. Retrieve beans from spring container
 
```java
Coach theCoach = context.getBean("myCoach", Coach.class);
```
myCoach is referring to the id of the bean

Coach.class is referring to the interface that implemented by the BaseballCoach class

## Section 5: Spring Dependency Injection - XML Configuration
Outsource the construction and injection of your object to an external entity

### Injection types
There are many types of injection in spring, the most common one is:
- Constructor injection
- Setter injection

We will talk about "auto-wiring" in the annotations section later

#### Constructor injection
1. Define the dependency interface and class
2. Create a constructor in your class for injection
3. Configure the dependency injection in spring config file

```xml
<bean>
    <bean id="myCoach" class="com.luv2code.springdemo.BaseballCoach">
        <constructor-arg ref="myFortuneService"/>
    </bean>
</bean>
<bean>
    <bean id="myFortuneService" class="com.luv2code.springdemo.HappyFortuneService"></bean>
</bean>
```

#### Setter injection
Inject dependencies by calling setter methods on your class

Process:
1. Create the setter method for injection
2. Configure the dependency injection in spring config file

```xml
<bean>
    <bean id="myCoach" class="com.luv2code.springdemo.BaseballCoach">
        <property name="fortuneService" ref="myFortuneService"/>
    </bean>
</bean>
<bean>
    <bean id="myFortuneService" class="com.luv2code.springdemo.HappyFortuneService"></bean>
</bean>
```

The spring will find a public setter method with the name of **set**FortuneService

## Section 6: Spring Bean Scopes and Lifecycle
1. Refers to the lifecycle of a bean
2. How long does the bean live?
3. How many instances are created?
4. How is the bean shared?

The default scope of a bean is a **Singleton** so
- Spring container creates only one instance of the bean, by default
- It is cached in memory
- All request for the bean will return a **SHARED** reference to the **SAME** bean

Explicitly specify the bean scope
```xml
<beans>
    <bean id="myCoach" class="com.luv2code.springdemo.BaseballCoach" scope="singleton"></bean>
</beans>
```
### Type of scopes
| Scope          |                         Description                         |
| -------------- | :---------------------------------------------------------: |
| singleton      | Create a single shared instance of the bean. Default scope  |
| prototype      |    Create a new bean instance for each container request    |
| request        |    Scoped to an HTTP web request. Only used for web apps    |
| session        |    Scoped to an HTTP web session. Only used for web apps    |
| global-session | Scoped to a global HTTP web session. Only used for web apps |

### Bean lifecycle

1. Container started
2. Bean instantiated
3. Dependencies injected
4. Internal spring processing
5. *Your custom init method*
6. Bean is ready for use
7. Container shutdown (context.close()) or *your custom destroy method*
8. Bean stopped

### Bean lifecycle methods/hooks
You can add custom code during **bean initialization** or **bean destruction**

- Calling custom business logic methods
- Setting/clean up handles to resources

```xml
<beans>
    <bean id="myCoach" class="com.luv2code.springdemo.BaseballCoach" init-method="doMyStartupStuff" destroy-method="doMyCleanUpStuff"></bean>
</beans>
```

init-method: set up bean initialization, you can put any method name

destroy-method: set up bean destroy method, you can put any method name


Development process:
1. Define your methods for init and destroy
2. Configure the method names in spring config file

Notes:
"Spring does not manage the complete lifecycle of a prototype bean: the container instantiates, configures, decorates and otherwise assembles a prototype object, hands it to the client and then has no further knowledge of that prototype instance. For releasing resources try to implement a custom bean post processor."

"Unlike singleton beans where the spring container manages the complete life-cycle."

## Section 7: Spring Configuration with Java Annotations - Inversion of Control

Java annotations are
1. Special labes/markers added to java classes
2. Provide meta-data about the class
3. Processed at compile time or run-time for special processing

### Why spring configuration with annotations?
1. XML configuration can be verbose. Imagine we have 100 beans, then we have to define it one by one in our xml file
2. Configure your spring beans with annotations
3. Annotations minimizes the XML configuration

### Scanning for component class
1. Spring will scan your Java classes for special annotations
2. Automatically register the beans in the spring container

### Development process
1. Enable component scanning in Spring config file

Spring will scan this package (recursively) to find a component that have annotations on it. And it will automatically register them in the spring container.

```xml
<beans>
    <context:component-scan base-package="com.luv2code.springdemo"/>
</beans>
```

2. Add the @Component Annotation to your java classes

Add annotations to tell spring that this class is a special spring bean. I'd like you to register it.

@Component(**Bean ID**)

```java
@Component("thatSillyCoach")
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout(){
        return "Practice your backhand volley";
    }
}
```

If we don't specify the bean ID, spring will generate the default Bean ID.
| Class Name  | Default Bean ID |
| ----------- | :-------------: |
| TennisCoach |   tennisCoach   |


3. Retrieve bean from spring container
Same coding as before...

```java
Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
```

## Section 8: Spring Configuration with Java Annotations - Dependency Injection

### Autowiring
For dependency injection, spring can use autowiring. Spring will look for a class that matches the property (matches by type: class or interface). Spring will inject it automatically, hence it is autowired.

@Autowired inject any dependencies by calling ANY method on your class

### Autowiring injection types
- Constructor injection
- Setter injection
- Field injection

#### Constructor injection
1. Define the dependency interface and class

```java
public interface FortuneService {
    public String getFortune();
}

@Component
public class HappyFortuneService implements FortuneService{
    public String getFortune(){
        return "Today is your lucky day";
    }
}
```

2. Create a constructor in your class for injections

```java
@Component
public class TennisCoach implements Coach{
    private FortuneService fortuneService;

    public TennisCoach(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }
}
```

3. Configure the dependency injection with **@Autowired** annotation

Spring will scan for a component that implements FortuneService interface

```java
@Component
public class TennisCoach implements Coach{
    private FortuneService fortuneService;

    **@Autowired**
    public TennisCoach(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }
}
```
#### Setter injection
Inject dependencies by calling setter methods on your class

1. Create setter method(s) in your class for injections

```java
@Component
public class TennisCoach implements Coach{
    private FortuneService fortuneService;
    
    public TennisCoach(){}

    public void setFortuneService(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }
}
```

2. Configure the dependency injection with **@Autowired** annotation

```java
@Component
public class TennisCoach implements Coach{
    private FortuneService fortuneService;
    
    public TennisCoach(){}

    **@Autowired**
    public void setFortuneService(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }
}
```

#### Field injection
Inject dependencies by setting field values on your class directly (even private fields). Accomplished by using java reflection.

1. Configure the dependency injection with Autowired annotation
    - Applied directly to the field
    - No need for setter methods

```java
@Component
public class TennisCoach implements Coach{

    **@Autowired**
    private FortuneService fortuneService;
    
    public TennisCoach(){}
    
    // ... No need for setter methods
}
```

### Qualifier
Used when there are multiple implementation of an interface. We need to tell specific bean to use. It can be use to any injection types.

```java
@Component
public class TennisCoach implements Coach{
    private FortuneService fortuneService;
    
    public TennisCoach(){}

    @Autowired
    public void setFortuneService(@Qualifier("fortuneService") FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }
}
```

Specified the bean ID that we want to use

## Section 9: Spring Configuration with Java Annotations - Bean Scopes and Lifecycle Methods

Explicitly specify bean scope
```java
@Component
@Scope("singleton")
@Scope("prototype")
public class TennisCoach implements Coach{
...
}
```

Bean lifecycle:
1. Define your methods for init and destroy
2. Add annotations: @PostConstruct and @PreDestroy
 
## Section 11: Spring MVC - Building Spring Web Apps

### Components of a spring MVC application
- A set of web pages to layout UI components
- A collection of spring beans (controllers, services, etc...)
- Spring configuration (XML, annotations or java)

Front controller known as **DispatcherServlet**:
- Part of the spring framework
- Already developed by spring dev team

You will create:
- Model objects
- View templates
- Controller classes

#### Controller
Code created by developer. Contains your business logic:
- Handle the request 
- Store/retrieve data (db, web service)
- Place data in model

And send it to appropriate view template

#### Model
Contains your data. Store/retrieve data via backend systems:
- Database, web services, etc...
- Use a spring bean if you like

Place your data in the model. Data can be any java object/collection

#### View template
Spring MVC is flexible. Supports many view templates, most common is JSP + JSTL. Views is used to display data

JSP: Java Server Pages
JSTL: JSP Standard Tag Library

Other view template supported:
- Thymeleaf, Groovy...
- Velocity, Freemarker, etc...

### Spring MVC configuration process - part 1
Add configurations to file: **WEB-INF/web.xml**
1. Configure spring MVC dispatcher servlet
2. Set up URL mappings to spring MVC dispatcher servlet

### Spring MVC configuration process - part 2
Add configurations to file: **WEB-INF/spring-mvc-demo-servlet.xml**
3. Add support for spring component scanning
4. Add support for conversion, formatting, and validation
5. Configure spring MVC view resolver

## Section 12: Spring MVC - Creating Controllers and Views
Development process:
1. Create controller class
2. Define controller method
3. Add request mapping to controller method
4. Return view name
5. Develop view page

### Create controller class
Annotate a class with @Controller
@Controller inherits from @Component supports scanning

```java
@Controller
public class HomeController{

}
```

### Define controller method

```java
@Controller
public class HomeController{
    public String showMyPage(){
        ...
    }
}
```

### Add request mapping to controller method

```java
@Controller
public class HomeController{

    @RequestMapping("/")
    public String showMyPage(){
        ...
    }
}
```

### Return view name

```java
@Controller
public class HomeController{

    @RequestMapping("/")
    public String showMyPage(){
        return "main-menu";
    }
}
```

### Develop view page

```html
<html>
    <body>
        <h1>Hello world</h1>
    </body>
</html>
```

### Reading HTML form data

Development process:
1. Create controller class
2. Show HTML form
   - Create controller method to show HTML form
   - Create view page for html form
3. Process HTML form
   - Create controller method to process HTML form
   - Develop view page for confirmation

### Spring model
A container for application data. In your controller you can put anyhting in the model (strings, objects, info from database, etc). Your view page can access data from the model

## Section 13: Spring MVC - Request Params and Request Mappings
Spring has special method to bind request parameter. @RequestParam

```java
public String letShoutDude(@RequestParam("name") String name, Model model){
    // ...
}
```

### Controller level request mapping

- Serves as parent mapping for controller
- All request mappings on methods in the controller are relative
- Similar to folder directory structures

```java
@RequestMapping("/funny")
public class FunnyController {
    @RequestMapping("/showForm") // /funny/showForm
    public String showForm(){
        // ...
    }

    @RequestMapping("/processForm") // /funny/processForm
    public String process(HttpServletRequest request, Model model){
        // ...
    }
}
```

## Section 14: Spring MVC - Form Tags and Data Binding

- Spring MVC form tags are the building block for a web page
- Form tags are configurable and reusable on a web page

### Text fields
The big picture:
1. We have a form that submits first name and last name
2. The controller will retrieve the form values
3. The controller will show it on another page

In spring controller
- Before you show the form, you must add a model attribute
- This is a bean that will hold form data for the data binding

Show form - add model attribute
```java
@RequestMapping("/showForm")
public String showForm(Model model){
    model.addAttribute("student", new Student());

    return "student-form";
}

@RequestMapping("/processForm")
public String processForm(@ModelAttribute("student") Student student){
    return "student/confirmation";
}
```

## Section 15: Spring MVC Form Validation - Applying Built-In Validation Rules
Bean validation features:
- Required
- length
- numbers
- regular expression
- custom validation

annotations:
- @NotNull: is not null
- @Min: minimum value
- @Max: maximum value
- @Size: match the size
- @Pattern: regular expression pattern
- @Future / @Past: DATE must be in future or past of given date
- @InitBinder: works as a preprocessor, it will pre process each web request to our controller.
- others...

### Perform validation rules on our object

```java
@RequestMapping("/processForm")
public String processForm(@Valid @ModelAttribute() Customer customer, BindingResult theBindingResult){
    if(theBindingResult.hasErrors()){
        return "customer-form";
    }

    return "customer-confirmation";
}
```

@Valid: Perform validation rules on our object

BindingResult: Results of validation placed in the BindingResult

**the BindingResult parameter must appear immediately after the model attribute**

## Section 16: Spring MVC Form Validation - Validating Number Ranges and Regular Expressions

### Custom error messages

Create a file messages.property in resources file.

```text
typeMistmach.customer.freePasses=Invalid number
```
error type -> spring model attribute name -> field name -> custom message

## Section 17: Spring MVC Form Validation - Creating Custom Validation Rules

1. Create @CourseCode annotation

```java
@Constraint(validatedBy = CourseCodeConstraintValidator.class) // Helper class that contains business rules / validation logic
@Target( {ElementType.METHOD, ElementType.FIELD} ) // Can apply your annotation to a method or field
@Retention(RetentionPolicy.RUNTIME) // Retain this annotation in the Java class file. Process it at runtime
public @interface CourseCode{
    // This is how you define a custom annotation in java

    // define default course code
    public String value() default "LUV";

    public String message() default "must start with LUV";
}
```

2. Creat CourseCodeConstraintValidator

```java
package form.validation.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        this.coursePrefix = courseCode.value();
    }

    @Override
    // userInput = that's the user input that we want to validate
    // constraintValidatorContext = we can add additional error messages here
    public boolean isValid(String userInput, ConstraintValidatorContext constraintValidatorContext) {
        return userInput.startsWith(this.coursePrefix);
    }
}

```

3. Add validation rule to customer class

## Section 18: Introduction to Hibernate
A framework for persisting / saving java objects in a database

your java app -> hibernate -> database

```java
// Create java object
Student student = new Student("John", "Doe", "john@luv2code.com");

// Save it to database
int theId = (Integer) session.save(student);

// Retrieve from database using the primary key
Student myStudent = session.get(Student.class, theId);

// Querying for java objects
Query query = session.createQuery("from Student");

List<Student> students = query.list();
```

### Benefits of hibernate
- Hibernate handles all of the low-level SQL
- Minimizes the amount of JDBC code you have to develop
- Hibernate provides the Object-to-Relational Mapping (ORM)

### Hibernate and JDBC
Hibernate uses JDBC for all database communications. In the background, Hibernate will do all the low level JDBC works.

## Section 20: Hibernate Configuration with Annotations
To do list:
1. Add hibernate configuration file
2. Annotate java class
3. Develop java code to perform database operations

### Annotate java class

Terminology:

Entity Class, java class that is mapped to a database table. it's just a plain old java class with a field and setter method.

Two options for mapping:
1. XML config file (legacy)
2. Java annotations (modern, preferred)

#### Java annotations
1. Map class to database table

```java
@Entity
@Table(name="student")
public class Student{
    // ...
}
```

2. Map fields to database columns

```java
@Entity
@Table(name="student")
public class Student{
    
    @Id
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;
}
```

## Section 21: Hibernate CRUD Features: Create, Read, Update and Delete

Two key players:
- SessionFactory: Reads the hibernate config file, creates session objects, heavy-weight object, only created once in your app
- Wraps a JDBC connection, main object used to save/retrieve objects, short-lived object, retrieved from SessionFactory

### Primary key
A uniquely identifies each row in a table, must be a unique value, and cannot contain NULL values.

```java
@Entity
@Table(name="student")
public class Student{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
}
```

@GenerationType: Let MySQL to handle the generation AUTO_INCREMENT

Other generation types:

| Name                    |                                 Description                                 |
| ----------------------- | :-------------------------------------------------------------------------: |
| GenerationType.AUTO     |          Pick an appropriate strategy for the particular database           |
| GenerationType.IDENTITY |             Assign primary keys using database identity column              |
| GenerationType.SEQUENCE |                Assign primary keys using a database sequence                |
| GenerationType.TABLE    | Assign primary keys using an underlying database table to ensure uniqueness |

You can define your own CUSTOM generation strategy if your company needs it

- Create the implementation of org.hibernate.id.IdentifierGenerator
- Override the method: public Seriazable generate(...) to add your custom business logic

WARNING:
1. Your generator ALWAYS generate unique value
2. It works fine in high-volume, multi-threaded environment
3. If using server clusters, always generate unique value

### Hibernate query language
When use @Query annotations you refer to the class name not the table name. Example

```java
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    
    @Query("SELECT s FROM Student s WHERE s.lastName = :lastName")
    public List<Student> findStudentWithLastName(@Param("lastName") String lastName);
}
```

Student refers to the student object

lastName refers to the field name in the student object

## Section 22: Hibernate Advanced Mappings

- One-to-One mapping
- One-to-Many mapping
- Many-to-Many mapping

### Database concept

Primary key: Identify unique row in a table

Foreign key: link tables together, a field in one table that refers to primary key in another table

Cascade: You can cascade operations, apply the same operations to related entities. For example when we delete the instructor, we should also delete their instructor_detail

Fetch types: Eager vs Lazy Loading
- Eager: will retrieve everything
- Lazy: will retrieve on request

Uni directional: When you access instructor you can get data from instructor detail

Bi-directional: You can access data from both table (instructor to instructor detail or instructor detail to instructor)

## Section 23: Hibernate Advanced Mappings - @OneToOne

Development process:
1. Prep work - define database tables
2. Create InstructorDetail class
3. Create Instructor class
4. Create main app

### Entity lifecycle

| Operations |                                   Description                                   |
| ---------- | :-----------------------------------------------------------------------------: |
| Detach     |      If entity is detached, it is not associated with a hibernate session       |
| Merge      |    If instance is detached from session, then merge will reattach to session    |
| Persist    |  Transitions new instances to managed state. Next flush/commit will save in db  |
| Remove     | Transitions managed entity to be removed. Next flush/commit will delete from db |
| Refresh    |            Reload/sync object with data from db. Prevents stale data            |

### @OneToOne - cascade types

| Cascade type |                                         Description                                         |
| ------------ | :-----------------------------------------------------------------------------------------: |
| Persist      |             If entity is persisted/saved, related entity will also be persisted             |
| Remove       |              If entity is removed/deleted, related entity will also be deleted              |
| Refresh      |                If entity is refreshed, related entity will also be refreshed                |
| Detach       | If entity is detached (not associated w/session), then related entity will also be detached |
| Merge        |                If entity is merged, then related entity will also be merged                 |
| All          |                               All of the above cascade types                                |


### Configure cascade type

```java
@Entity
@Table(name="instructor")
public class Instructor {
    // ...

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;

    // ...
}
```

**By default, no operations are cascaded. So we have to define it by ourself**

Configure multiple cascade types

```java
@OneToOne(cascade={
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH,
    CascadeType.REMOVE
})
```

### Bi-directional

Development process:
1. Make updates to InstructorDetail class:
   1. Add new field to reference Instructor
   2. Add getter/setter methods for Instructor
   3. Add @OneToOne annotation
2. Create main app

```java
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
    @OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL)
    private Instructor instructor;

    // setter/getter for instructor
}
```

mappedBy: Refers to "instructorDetail" **PROPERTY IN** "instructor" **CLASS**

#### mappedBy

mappedBy tells hibernate:
- Look at the instructorDetail property in the Instructor class
- Use information from the Instructor class @JoinColumn
- To help find associated instructor

### NOTES
Owned entity: the table that have the foreign key
Parent entity: the table that have the id referenced

- Ideally you want to save your data from the owned entity to save the foreign key from the parent entity
- If you want to save the data from the parent entity you can do it. BUT, you have to set the referenced entity in the owned entity, so when you save it, the owned entity store the foreign key as well. IF YOU DON'T DO IT THEN THE OWNED ENTITY WILL NOT STORE THE FOREIGN KEY, BUT THE PARENT ENTITY WILL SAVE THE REFERENCE TO THE ID (THIS ONLY HAPPEN ON SPRING APP BECAUSE OF FLUSH/COMMIT)
- If you want to delete the parent entity but don't want to delete the weak entity then you have to DEREFERENCE your parent entity in your owned entity (ONLY USE IT YOU HAVE AN OBJECT PERSISTED ALREADY)
- ONCE your object PERSISTED into the database, then IF you MODIFY it, it will MODIFY it on the database too

## Section 24: Hibernate Advanced Mappings - @OneToMany

Development process:
1. Prep work - define database tables
2. Create course class
3. Update instructor class
4. Create main app

## Section 25: Hibernate Advanced Mappings - Eager vs Lazy Loading
When we fetch/retrieve data, should we retrieve everything?
- Eager will retrieve everything
- Lazy will retrieve on request

### Best practice
Only load data when absolutely needed. Prefer lazy loading instead of eager loading

### Eager loading
Eager loading will load all dependent entities. Load instructor and all of their courses at once. It may not be a big deal if you have a small number of data. But if you have large amount of data, it can impact the performance.

### Lazy loading
Lazy loading will load the main entity first then load the dependet entities on demand (lazy).

When we do lazy load, the data is only retrieved on demand. However, this requires an open Hibernate session and need a connection to database to retrieve data.

If the hibernate session is closed, and we attempt to retrieve lazy data, **Hibernate will throw an exception**.

Retrieve lazy data using
- Option 1: session.get and call appropriate getter method(s)
- Option 2: Hibernate query with HQL

How to resolve the hibernate exception when we retrieve data after the session closed:
- Option 1: call the getter method while session is open
- Option 2: load all the courses using hibernate query (HQL)

### Fetch type
When we define the mapping relationship, we can specify the fetch type: EAGER/LAZY

```java
@Entity
@Table(name="instructor")
public class Instructor{
    @OneToMany(fetch=FetchType.LAZY, mappedBy="instructor")
    private List<Courses> courses;
}
```

Default fetch type
| Mapping     | Default fetch type |
| ----------- | :----------------: |
| @OneToOne   |  FetchType.Eager   |
| @OneToMany  |   FetchType.LAZY   |
| @ManyToOne  |  FetchType.Eager   |
| @ManyToMany |   FetchType.LAZY   |

## Section 26: Hibernate Advanced Mappings - @OneToMany - Unidirectional
A course can have many reviews. The relation is uni-directional. If you delete a course, also delete the reviews. Because reviews without a course have no meaning.

Development process:
1. Define database table
2. Create review class
3. Update course class
4. Create main app

## Section 27: Hibernate Advanced Mappings - @ManyToMany
A table that provides a mapping between two tables. It has foreign keys for each table to define the mapping relationship.

Development process:
1. Define database tables
2. Update course class
3. Update student class
4. Create main app

```java
@Entity
@Table(name = "course")
public class Course{
    @ManyToMany
    @JoinTable(
        name = "course_student"
        joinColumns = @JoinColumn(name = "course_id")
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;
}
```

@JoinTable tells hibernate
- Look at the **course_id** columnt at the **course_student** table
- For other side (inverse), look at the student_id column in the **course_student** table
- Hibernate will use this information to find relationship between **course** and **student**

More on inverse:
- In this context, we are defining the relationship in **Course** class
- The **Student** class is on the "other side", so it is considered the "inverse"
- "Inverse" refers to the "other side" of the relationship

### Notes
We have 3 tables:
- course
- student
- course_student

Notes **WHEN YOU HAVE A TRUNCATE QUERY BEFORE RUNNING ALL THE CODE**:
1. We can only set the relationship from 1 entity only. Example, course set students/student set courses. If we set it on both objects, then we can get constraint error.
2. Depends from what object do you save the data to the database, you can only get the data from the entity that you saved. Example, when you save the data through course entity, then you have to retrieve the course entity to get the complete data. If you try to retrieve it from student, then you will get null courses. (THIS ONLY HAPPEN WHEN YOU HAVE A PERSISTED OBJECT BEFORE IT)

## Section 28-33: Build a Database Web App - Spring MVC and Hibernate Project

### @Transactional annotation
Spring will provide session.beginTransaction() and session.commit();

### @Repository annotation
Spring will provide transalation for any JDBC related exceptions

### @RequestMapping
request mapping receive all methods (GET, POST, PUT, etc), if we want to constraint the request mapping then we have to specify it

```java
@RequestMapping(path="/processForm", method=RequestMethod.GET)
```

## Section 34: AOP: Aspect-Oriented Programming Overview

Aspect-oriented programming is a programming technique based on concept of an aspect. An aspect encapsulates **cross-cutting logic** or **cross-cutting concern**. Concern means logic/functionality. It's like a basic infrastructure code that all applications would need.

Before:
1. If we need logging or security, we add them into our class one by one
2. This is not scalable, when we have to add new functionality/methods, we have to add the log or security manually. Or, if we want to modify the logging or security, then we have to modify it to all of our class

After:
1. Using cross-cutting concerns is we take that logging code and encapsulate it into a reusable module or class and then we can have that code be called when we make appropriate calls to our controller, service, etc

So an aspects is:
- Aspect can be reused at multiple location
- Same aspect/class can be applied based on configuration

### AOP Solution
Apply the proxy design pattern. For example, we have a main application that call a method in our target object. The main application has no idea about AOP or any aspects or proxy.

Main App -> AOP Proxy (logging/security aspect) -> target object

#### AOP frameworks for java
##### Spring AOP
Spring provides AOP support. It is build within spring like security, transaction, caching, etc. It uses run-time weaving of aspect.

Advantages
1. Simpler to use than AspectJ
2. Uses proxy pattern
3. Can migrate to AspectJ when using @Aspect annotation

Disadvantages
1. Only supports method-level join points
2. Can only be applied to aspects to beans created by the spring apps context
3. Minor performance cost for aspect execution (run-time weaving)

##### AspectJ
The original AOP framework, released in 2001. Provides complete support of AOP, rich support of:
- Join points: method-level, constructor, field
- Code weaving: compile-time, post compile-time, and load-time

Advantages
1. Support all join points
2. Works with any POJO, not just beans from app context
3. Faster performance compared to spring AOP
4. Complete AOP support

Disadvantages
1. Compile-time weaving requires extra compilation steps
2. AspectJ pointcut syntax can become complex

##### Comparing spring AOP and AspectJ
- Spring AOP is the lightweight implementation of AOP
- Solves common problems in enterprise applications
- Recommended to start with Spring AOP since it is easy to get started with. Unless, you ahve complex requirement then move to aspectJ

### Benefits of AOP
- Code for aspect is defined in a single class
  - Much better than being scattered everywhere
  - Promotes code reuse and easier to change
- Business code in your application is cleaner
  - Only applies to business functionality: addAccount
  - Reduces code complexity
- Configurable
  - Based on configuration, apply aspects selectively to different parts of app
  - No need to make changes to main application code!

### Use cases
- Most common
  - logging, security, transactions
- Audit logging
  - who, what, when, where
- Exception handling
  - log exception and notify DevOps team via SMS/email
- API Management
  - how many times has a method been called by the user
  - analytics: what are peak times? what is average load? who is top user

### Advantages and disadvantages
Advantages:
- Reusable modules
- Resolve code tangling
- Resolve code scatter
- Applide selectively based on configuration

Disadvantages:
- Too many aspects and app flow is hard to follow
- Minor performance cost for aspect execution (run-time weaving)

### AOP terminology
- Aspect: module of code for a cross cutting concern (logging, security, ...)
- Advice: what action is taken and when it should be applied
- Joint point: when to apply code during program execution
- Pointcut: a predicate expression for where advice should be applied

#### Advice types
- Before advice: run before the method gets called
- After finally advice: run after the method gets called (like a finally block in a try catch)
- After returning advice: run after the method has been called successfully (success execution)
- After throwing advice: run after the method has been called if an exception get thrown
- Around advice: run before and after method

#### Weaving
Connecting aspects to target objects to create an adviced object

Differnt type of weaving:
- Compile time
- Load time
- Run time

Regarding performance, run time is the slowest

### Our Spring AOP roadmap
1. Create **aspects**
2. Develop **advices** (before, after returning, after throwing, after finally, around)
3. Create **pointcut** expression
4. Apply it to our big CRM project (Spring MVC + Hibernate)

## Section 35: AOP: @Before Advice Type
Development process:
1. Create target object: AccountDAO

```java
@Component
public class AccountDAO {
    public void addAccount(){
        System.out.println("doing my db work: adding my account");
    }
}
```

2. Create spring java config class

```java
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.luv2code.aopdemo")
public class DemoConfig{

}
```

3. Create main app
4. Create an aspect with @Before advice

```java
@Aspect
@Component
public class MyDemoLoggingAspect {
    // A pointcut expression
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice(){
        // Add our custom code logging, security, etc
    }
}
```
Run this code before - target object method: "public void addAccount()"

Best practices:
- Keep the code small
- Keep the code fast
- Do not perform any expensive/slow operations
- Get in and out as quickly as possible

## Section 36: AOP: Pointcut Expressions - Match Methods and Return Types
Pointcut expression language


### Execution
```text
execution(modifiers-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern?)
```
The pattern is optional if it has "?". Example

- Match on method names

Match only addAccount() method in AccountDAO class

```java
@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
```

- Match any addAccount() method in any class

```java
@Before("execution(public void addAccount())")
```

- Match on method names (using wildcards)

Match on methods **starting** with **add** in any class
```java
@Before("execution(public void add*())")
```

- Use wildcards on any return type

```java
@Before("execution(public * processCreditCard*())")
```

- Modifier is optional so we don't have to list it

```java
@Before("execution(* processCreditCard*())")
```

## Section 37: AOP: Pointcut Expressions - Match Method Parameter Types
Parameter pattern widlcards
- (): matches a method with no arguments
- (*): matches a method with one argument of any type
- (..): matchs a method with 0 or more arguments of any type

## Section 38: AOP: Pointcut Declarations
Problem:
How can we reuse the same pointcut expression for other advice

Possible solution:
1. Use the old copy paste method
2. **Create a pointcut expression once, and apply it to multiple advices**

Reusing pointcut expression
```java
@Aspect
@Component
public class MyDemoLoggingAspect {

    // A pointcut expression
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage(){} // name of the pointcut declaration, you can have any name

    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice(){}

    @Before("forDaoPackage()")
    public void performAPIAnalytics(){}
}
```

Benefits of pointcut declarations
- Easily reuse pointcut expression
- Update pointcut in one location
- Can also share and combine pointcut expressions (coming up later)

Another problem
- How to apply multiple pointcut expression to single advice?
- Execute an advice only if certain conditions are met. For example, all methods in a package EXCEPT getter/setter methods

We can combine pointcut expression
- Combine pointcut expression using logic operators
  - AND (&&)
  - OR (||)
  - NOT (!)

Works like an if statement and execution happens only if it evaluates to true
```java
@Before("expressionOne() && expressionTwo()")
@Before("expressionOne() || expressionTwo()")
@Before("expressionOne() && !expressionTwo()")
```

## Section 39: AOP: Ordering Aspects
How we can controll the order of advices being applied. Imagine we have 3 aspects
- beforeAddAccountAdvice
- performAPIAnalytics advice
- logToCloudAdvice

Base the specification the order of the aspect is undefined, so spring will call the order randomly.

To control order
- Refactor: place advices in separate aspects

When we call other method in another aspect/class, we have to pass the fully qualified name which is **package name** + **class name**

- Add @Order annotation

Control order on aspects using the @Order annotation
```java
@Aspect
@Component
@Order(1)
public class MyCloudLogAspect {}
```

This guarantees the order of when Aspects are applied. Lower numbers have higher precedence/priority. The range of the number between Integer.MIN_VALUE to Integer.MAX_VALUE. Negative numbers are allowed and it doesn't have to be consecutive. So we can make the order to 0, 9, 17.

What if aspects have the exact same @Order annotation? For example we have the order of 1, 6, 6, and 123. The order at this point is undefined, so spring will run the aspect randomly. But, it will still run **after** 1 and **before** 123

## Section 40: AOP: JoinPoints
Problem: When we are in an aspect (ie for logging), how we can access method parameters. Just in case if we want to log the params with AOP.

MainDemoApp.java
```java
// Call the business method
Account account = new Account();
theAccountDAO.addAccount(account, true);
```


1. Access and display method signature

```java
@Before("...")
public void beforeAddAccountAdvice(JoinPoint joinPoint){
    // Display the signature
    MethodSignature sig = (MethodSignature) joinPoint.getSignature();
    
    System.out.println("Method: " + sig);
}
```
The joinPoint has will give us the information about the method that we are actually executing. It has the metadata about our method call.

```shell
Merthod: void com.luv2code.aopdemo.dao.AccountDAO.addAccount(Account, boolean)
```

The signature will return the class return type, the qualified name of the class, and the parameters that the method has.

2. Access and display method arguments

```java
@Before("...")
public void beforeAddAccountAdvice(JoinPoint jointPoint){
    // Display method arguments

    // Get args
    Object[] args = jointPoint.getArgs();

    // Loop through args
    for (Object arg : args){
        System.out.println(args);
    }
}
```
The code above will print out the arguments that the method receives
```java
com.luv2code.aopdemo.Account@81234
true
```

## Section 41: AOP: @AfterReturning Advice Type
After returning gives us a chance to write our custom code once we have a successful execution for a given method call with no exceptions

Use case:
- Most common
  - Logging, security, transactions
- Audit logging
  - who, what, when, where
- Post processing data
  - Post process the data before returning to caller
  - Format the data or enrich the data (really cool but be careful)

Access the return value
```java
@AfterReturning(
    pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
    returning = "result" // Parameter name for return value
    public void afterRetunringFindAccountsAdvice(JoinPoint joinPoint, List<Account> result){
        // Print out the result of the method call
        System.out.println("\n=====>> result is: " + result)
    }
)
```

Modify the return value for **post-processing data**. We want to modify the data before it makes back to the caller.

```java
@AfterReturning(
    pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
    returning = "result" // Parameter name for return value
    public void afterRetunringFindAccountsAdvice(JoinPoint joinPoint, List<Account> result){
        // Print out the result of the method call
        if(!result.isEmpty()){
            Account tempAccount = result.get(0);
            tempAccount.setName("Daffy duck");
        }
    }
)
```

## Section 42: AOP: @AfterThrowing Advice Type
Capture the exception after it got thrown.

Use cases:
1. Logging the exception
2. Perform auditing on the exception
3. Notify DevOps team via email or SMS (use with care, don't spam)
4. Encapsulate this functionality in AOP aspect for easy reause


This advice will run after an exception is thrown
```java
@AfterThrowing(
    pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
    throwing = "exceptionThrown"
    )
public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exceptionThrown){
    System.out.println("Executing @AfterThrowing advice")
    System.out.println("\n========>> The exception is: " + exceptionThrown)
}
```

Exception propagation:
1. At this point, we are only intercepting the exception (reading it)
2. However, the exception is still propagated to calling program
3. If you want to stop the exception propagation then use **@Around** advice