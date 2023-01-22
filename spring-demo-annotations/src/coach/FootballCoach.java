package src.coach;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import src.fortune.FortuneService;

@Component
public class FootballCoach implements Coach {

    private FortuneService fortuneService;
    private String name;
    private String team;

    public FootballCoach() {
        System.out.println(">> Inside default constructor");
    }

    @PostConstruct
    public void initMethod(){
        System.out.println(">> This is init method");
    }

    @PreDestroy
    public void destroyMethod(){
        System.out.println(">> This is destroy method");
    }

    @Autowired
    @Qualifier("randomFortuneService")
    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String getName() {
        return this.name;
    }

    @Value("${football.name}")
    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return this.team;
    }

    @Value("${football.team}")
    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String getDailyWorkout() {
        return "Shooting 100 balls";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }
}
