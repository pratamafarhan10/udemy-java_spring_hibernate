package src.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import src.fortune.FortuneService;

public class BadmintonCoach implements Coach{

    private FortuneService fortuneService;

    public BadmintonCoach(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }

    @Override
    public String getDailyWorkout() {
        return "Smashing 100 times";
    }
}
