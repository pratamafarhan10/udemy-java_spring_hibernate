package src.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import src.coach.Coach;
import src.coach.SwimCoach;
import src.fortune.FortuneService;
import src.fortune.SadFortuneService;

@Configuration
@ComponentScan("src")
@PropertySource("classpath:src/sport.properties")
public class SportConfig {

    // Define bean for our sadFortuneService
    @Bean
    public FortuneService sadFortuneService(){
        return new SadFortuneService();
    }
    
    // Define bean for our swimCoach
    @Bean
    public Coach swimCoach(){
        // Inject the dependency for swimCoach
        return new SwimCoach(sadFortuneService());
    }

    @Bean
    public SwimCoach swimCoach2(){
        // Inject the dependency for swimCoach
        return new SwimCoach(sadFortuneService());
    }
}
