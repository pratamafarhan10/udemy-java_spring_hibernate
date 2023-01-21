package src.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import src.coach.CricketCoach;

public class HelloSpringApp {
    public static void main(String[] args) {
        // Load spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("src/applicationContext.xml");
        // retrive from spring container
        CricketCoach theCoach = context.getBean("myCoach", CricketCoach.class);
        // call methods on bean
        System.out.println(theCoach.getDailyWorkout());
        // call methods fortune service
        System.out.println(theCoach.getDailyFortune());
        System.out.println(theCoach.getEmail());
        System.out.println(theCoach.getTeam());
        // close the context
        context.close();
    }
}
