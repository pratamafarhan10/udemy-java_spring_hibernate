package src.coach;

import src.fortune.FortuneService;

public class BaseballCoach implements Coach {
    private FortuneService fortuneService;

    public BaseballCoach() {
    }

    public BaseballCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "spend 30 minutes in batting practice";
    }

    @Override
    public String getDailyFortune() {
        // TODO Auto-generated method stub
        return this.fortuneService.getFortune();
    }
}