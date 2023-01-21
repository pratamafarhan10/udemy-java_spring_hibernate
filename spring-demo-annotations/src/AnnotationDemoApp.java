package src;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {
    public static void main(String[] args) {
        // Read spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("src/applicationContext.xml");
        // get the bean from spring container
        Coach myCoach = context.getBean("tennisCoach", Coach.class);
        Coach football = context.getBean("footballCoach", Coach.class);

        // this will return an error no bean named "thatSillyCoach" is defined
        // Coach myCoach = context.getBean("thatSillyCoach", Coach.class);

        // call some method on the bean
        System.out.println(myCoach.getDailyWorkout());
        System.out.println(football.getDailyWorkout());
        // close the container
        context.close();
    }
}
