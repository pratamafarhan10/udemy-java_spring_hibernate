package aspect.oriented.programming.around;

import aspect.oriented.programming.around.service.TrafficFortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//import java.util.logging.Logger;

@Component
public class OperationRunner implements CommandLineRunner {
    @Autowired
    private TrafficFortuneService trafficFortuneService;

//    private final static Logger logger = Logger.getLogger(OperationRunner.class.getName());

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========>> Operation runner");
        System.out.println("========>> Calling getFortune");

        String myFortune = trafficFortuneService.getFortune();
        System.out.println("My fortune is: " + myFortune);

        System.out.println("\n========>> Finish");

        System.out.println("\n========>> Calling getFortune with an Exception");

        myFortune = trafficFortuneService.getFortune(true);
        System.out.println("My fortune is: " + myFortune);

        System.out.println("\n========>> Finish");
    }
}

