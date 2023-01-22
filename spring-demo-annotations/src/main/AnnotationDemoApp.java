package src.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import src.coach.Coach;
import src.coach.FootballCoach;

public class AnnotationDemoApp {
    public static void main(String[] args) {
        // Read spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("src/applicationContext.xml");
        // get the bean from spring container
        Coach tennis = context.getBean("tennisCoach", Coach.class);
        FootballCoach football = context.getBean("footballCoach", FootballCoach.class);
        Coach cricket = context.getBean("cricketCoach", Coach.class);

        // this will return an error no bean named "thatSillyCoach" is defined
        // Coach myCoach = context.getBean("thatSillyCoach", Coach.class);

        // call some method on the bean
        System.out.println("======= Test dependency injection =======");
        System.out.println(tennis.getDailyFortune());
        System.out.println(football.getDailyFortune());
        System.out.println(cricket.getDailyFortune());
        
        // Properties injection
        System.out.println("======= Test property injection =======");
        System.out.println(football.getName());
        System.out.println(football.getTeam());

        // close the container
        context.close();
    }
}
