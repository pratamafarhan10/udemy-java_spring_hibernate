package aspect.oriented.programming.afterreturning.dao;

import aspect.oriented.programming.afterreturning.dto.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private String name;
    private String serviceCode;

    public List<Account> findAccounts(){
        List<Account> accounts = new ArrayList<>();

        // Create sample accounts
        Account windah = new Account("windah", "silver");
        Account joe = new Account("joe", "platinum");
        Account obama = new Account("obama", "bronze");

        // Add sample accounts to our list
        accounts.add(windah);
        accounts.add(joe);
        accounts.add(obama);

        return accounts;
    }
}
