package aspect.oriented.programming.beforeadvice.dto;

import org.springframework.stereotype.Component;

@Component
public class Account {

    private String name;

    public Account(){}
    public Account(String name){
        this.name = name;
    }

    public void addAccount(){
        System.out.println(this.getClass() + ": Doing my DB work: Adding an account");
    }

    public Boolean addAccountSuccessful(){
        return true;
    }

    public void addAccount(String name){
        System.out.println(this.getClass() + ": adding new account with " + name + " name");
    }

    public void addAccount(Account account){
        System.out.println(this.getClass() + ": adding new account with " + account.name + " name");
    }

    public void addAccount(String firstName, String lastName){
        System.out.println(this.getClass() + ": adding new account with " + firstName + " " + lastName + " name");
    }
}
