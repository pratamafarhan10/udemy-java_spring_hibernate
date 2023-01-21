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
| Scope        | Description           | 
| ------------- |:-------------:| 
| singleton      | Create a single shared instance of the bean. Default scope | 
| prototype      | Create a new bean instance for each container request | 
| request      | Scoped to an HTTP web request. Only used for web apps | 
| session      | Scoped to an HTTP web session. Only used for web apps | 
| global-session      | Scoped to a global HTTP web session. Only used for web apps | 

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
| Class Name    | Default Bean ID   | 
| ------------- |:-----------------:| 
| TennisCoach   | tennisCoach       |


3. Retrieve bean from spring container
Same coding as before...

Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
