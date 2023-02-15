package aspect.oriented.programming.beforeadvice.dto;

import org.springframework.stereotype.Component;

@Component
public class Membership {

    public void addSillyMember(){
        System.out.println("Doing my stuff: CREATING NEW MEMBERSHIP ACCOUNT...");
    }
}
