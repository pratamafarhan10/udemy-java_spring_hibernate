package src.coach;

import src.fortune.FortuneService;

public class FootballCoach implements Coach {

    private FortuneService fortuneService;

    public FootballCoach() {
    }

    public FootballCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Shooting 200 times";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }
}
