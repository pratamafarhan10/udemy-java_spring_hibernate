package aspect.oriented.programming.beforeadvice;

import aspect.oriented.programming.beforeadvice.dto.Account;
import aspect.oriented.programming.beforeadvice.dto.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OperationRunner implements CommandLineRunner {

    @Autowired
    private Account account;
    @Autowired
    private Membership membership;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n===== Operation Runner ====");
        Account windah = new Account("windah");
        this.account.addAccount();
        this.membership.addSillyMember();
        System.out.println(this.account.addAccountSuccessful() + "\n");
        this.account.addAccount(windah.getName());
        this.account.addAccount(new Account("travis scott"));
        this.account.addAccount("john", "doe");
        this.account.deleteAccount(new Account("Drake"));

        System.out.println("\nLet's call it again");
        this.account.addAccount();
    }
}
