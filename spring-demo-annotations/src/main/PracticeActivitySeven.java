package src.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import src.coach.Coach;
import src.config.BadmintonConfig;

public class PracticeActivitySeven {
    public static void main(String[] args) {
        // Get the context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BadmintonConfig.class);

        // Get the bean
        Coach badminton = context.getBean("badmintonCoach", Coach.class);

        // Call the fortune method
        System.out.println(badminton.getDailyFortune());

        // Close the context
        context.close();
    }
}
