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
@Qualifier("happyFortuneService")
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