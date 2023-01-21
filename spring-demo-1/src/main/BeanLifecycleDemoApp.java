package src.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import src.coach.CricketCoach;

public class BeanLifecycleDemoApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "src/beanLifecycle-applicationContext.xml");

        // Get the class
        CricketCoach firstCoach = context.getBean("myCoach", CricketCoach.class);
        CricketCoach secondCoach = context.getBean("myCoach", CricketCoach.class);

        // Check the fields
        System.out.println("======== Check the fields before modify ========");
        System.out.println(firstCoach.toString());
        System.out.println(secondCoach.toString());

        // Change one of the field (Singleton)
        firstCoach.setEmail("anotheremail@gmail.com");

        // Check the fields
        System.out.println("======== Check the fields after modify ========");
        System.out.println(firstCoach.toString());
        System.out.println(secondCoach.toString());

        context.close();

        System.out.println("======== Check the fields after the bean destroyed ========");
        System.out.println(firstCoach.toString());
        System.out.println(secondCoach.toString());
    }
}
