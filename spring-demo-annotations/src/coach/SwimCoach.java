package src.coach;

import org.springframework.beans.factory.annotation.Value;

import src.fortune.FortuneService;

public class SwimCoach implements Coach {

    private FortuneService fortuneService;

    @Value("${swim.name}")
    private String name;
    @Value("${swim.team}")
    private String team;

    public SwimCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public FortuneService getFortuneService() {
        return this.fortuneService;
    }

    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 100 meters for warm up";
    }
}
