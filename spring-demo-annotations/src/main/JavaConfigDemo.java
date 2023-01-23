package src.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import src.coach.Coach;
import src.coach.FootballCoach;
import src.coach.SwimCoach;
import src.config.SportConfig;

public class JavaConfigDemo {
    public static void main(String[] args) {
        // Read spring config file
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);

        // get the bean from spring container
        Coach tennis = context.getBean("tennisCoach", Coach.class);
        FootballCoach football = context.getBean("footballCoach", FootballCoach.class);
        Coach cricket = context.getBean("cricketCoach", Coach.class);
        Coach swim = context.getBean("swimCoach", Coach.class);
        SwimCoach swimCoach = context.getBean("swimCoach2", SwimCoach.class);

        // this will return an error no bean named "thatSillyCoach" is defined
        // Coach myCoach = context.getBean("thatSillyCoach", Coach.class);

        // call some method on the bean
        System.out.println("======= Test dependency injection =======");
        System.out.println(tennis.getDailyFortune());
        System.out.println(football.getDailyFortune());
        System.out.println(cricket.getDailyFortune());
        System.out.println(swim.getDailyFortune());
        System.out.println(swimCoach.getDailyFortune());
        
        // Properties injection
        System.out.println("======= Test property injection =======");
        System.out.println("Name: " + swimCoach.getName());
        System.out.println("Team: " + swimCoach.getTeam());

        // close the container
        context.close();
    }
}
