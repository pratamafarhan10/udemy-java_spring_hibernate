package aspect.oriented.programming.beforeadvice.dto;

import org.springframework.stereotype.Component;

@Component
public class Account {
    public void addAccount(){
        System.out.println(this.getClass() + ": Doing my DB work: Adding an account");
    }

    public Boolean addAccountSuccessful(){
        return true;
    }
}
