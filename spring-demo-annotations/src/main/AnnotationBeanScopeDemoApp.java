package src.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import src.coach.FootballCoach;

public class AnnotationBeanScopeDemoApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("src/applicationContext.xml");
        
        FootballCoach coach1 = context.getBean("footballCoach", FootballCoach.class);
        FootballCoach coach2 = context.getBean("footballCoach", FootballCoach.class);

        System.out.println("\nAre they the same object: " + (coach1 == coach2));
        System.out.println("\nCoach 1: "+ coach1);
        System.out.println("\nCoach 2: "+ coach2);

        context.close();
    }
}
