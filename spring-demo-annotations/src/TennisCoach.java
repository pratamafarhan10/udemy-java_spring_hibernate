package src;

import org.springframework.stereotype.Component;

// @Component("myTennisCoach")
@Component()
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
