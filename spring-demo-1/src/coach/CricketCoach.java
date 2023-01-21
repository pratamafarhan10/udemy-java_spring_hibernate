package src.coach;

import java.util.UUID;

import org.springframework.beans.factory.DisposableBean;

import src.fortune.FortuneService;

public class CricketCoach implements Coach, DisposableBean {
    private String id;
    private String email;
    private String team;
    private FortuneService fortuneService;

    public CricketCoach() {
    }

    public CricketCoach(String email, String team, FortuneService fortuneService) {
        this.email = email;
        this.team = team;
        this.fortuneService = fortuneService;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public FortuneService getFortuneService() {
        return this.fortuneService;
    }

    public void setFortuneService(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public String getId() {
        return this.id;
    }

    void setId() {
        System.out.println("CricketCoach: Inside our init method");
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("CricketCoach: Inside our destroy method");
        this.id = null;
        this.email = null;
        this.team = null;
    }

    @Override
    public String getDailyWorkout() {
        return "Catch the ball 100 times";
    }

    @Override
    public String getDailyFortune() {
        return this.fortuneService.getFortune();
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", email='" + getEmail() + "'" +
                ", team='" + getTeam() + "'" +
                ", fortuneService='" + getFortuneService() + "'" +
                "}";
    }

}
