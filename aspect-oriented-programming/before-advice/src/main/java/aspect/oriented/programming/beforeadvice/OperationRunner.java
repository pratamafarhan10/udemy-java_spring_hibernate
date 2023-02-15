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
        this.account.addAccount();
        this.membership.addSillyMember();
        System.out.println(this.account.addAccountSuccessful());

        System.out.println("\nLet's call it again");
        this.account.addAccount();
    }
}
