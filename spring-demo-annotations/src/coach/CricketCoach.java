package src.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import src.fortune.FortuneService;

@Component
public class CricketCoach implements Coach{
    
    @Autowired
    @Qualifier("sadFortuneService")
    private FortuneService fortuneService;

    public CricketCoach(){
        System.out.println(">> CricketCoach: Inside default constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Batting 100 times";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }
}
