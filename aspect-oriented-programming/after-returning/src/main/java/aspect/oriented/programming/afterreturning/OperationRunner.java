package aspect.oriented.programming.afterreturning;

import aspect.oriented.programming.afterreturning.dao.AccountDAO;
import aspect.oriented.programming.afterreturning.dto.Account;
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
        System.out.println("\n=============== Main program: AfterReturning");
        List<Account> accounts = accountDAO.findAccounts();
        System.out.println("Operation runner: " + accounts);
    }
}
