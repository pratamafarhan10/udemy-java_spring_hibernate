package aspect.oriented.programming.beforeadvice.dto;

import org.springframework.stereotype.Component;

@Component
public class Account {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account(){}
    public Account(String name){
        this.name = name;
    }

    public void addAccount(){
        System.out.println(this.getClass() + ": Doing my DB work: Adding an account\n");
    }

    public Boolean addAccountSuccessful(){
        return true;
    }

    public void addAccount(String name){
        System.out.println(this.getClass() + ": adding new account with " + name + " name\n");
    }

    public void addAccount(Account account){
        System.out.println(this.getClass() + ": adding new account with " + account.name + " name\n");
    }

    public void addAccount(String firstName, String lastName){
        System.out.println(this.getClass() + ": adding new account with " + firstName + " " + lastName + " name\n");
    }

    public void deleteAccount(Account account){
        System.out.println(this.getClass() + ": deleting account with name of " + account.name + "\n");
    }
}
