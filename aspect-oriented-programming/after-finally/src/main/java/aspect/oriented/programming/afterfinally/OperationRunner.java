package aspect.oriented.programming.afterfinally;

import aspect.oriented.programming.afterfinally.dao.AccountDAO;
import aspect.oriented.programming.afterfinally.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationRunner implements CommandLineRunner {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n=====>> Main program @After advice");

        List<Account> accounts = null;
        try {
            boolean tripWire = true;
            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\nMain program....caught exception: " + e);
        }
        System.out.println("Operation runner: " + accounts);

        System.out.println();
        try {
            boolean tripWire = false;
            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\nMain program....caught exception: " + e);
        }
        System.out.println("Operation runner: " + accounts);
    }
}

