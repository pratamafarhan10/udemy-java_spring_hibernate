package aspect.oriented.programming.afterthrowing.dao;

import aspect.oriented.programming.afterthrowing.dto.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private String name;
    private String serviceCode;

    public List<Account> findAccounts(boolean tripWire){
        if (tripWire){
            throw new RuntimeException("No soup for youu!");
        }
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
