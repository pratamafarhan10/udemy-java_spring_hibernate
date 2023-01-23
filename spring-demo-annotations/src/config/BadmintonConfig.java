package src.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import src.coach.BadmintonCoach;
import src.coach.Coach;
import src.fortune.FortuneService;
import src.fortune.SadFortuneService;

@Configuration
public class BadmintonConfig {

    @Bean
    public FortuneService sadFortuneService(){
        return new SadFortuneService();
    }

    @Bean
    public Coach badmintonCoach(){
        return new BadmintonCoach(sadFortuneService());
    }
}