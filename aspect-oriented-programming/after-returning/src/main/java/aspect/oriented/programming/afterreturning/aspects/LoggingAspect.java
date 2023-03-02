package aspect.oriented.programming.afterreturning.aspects;

import aspect.oriented.programming.afterreturning.dto.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    // Add after return advice
    @AfterReturning(
            pointcut = "execution(* aspect.oriented.programming.afterreturning.dao.AccountDAO.findAccounts())",
            returning = "accounts"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> accounts){
        // Print out which method we're advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("==========>> Executing after return on method " + method);
        // Print out the result of method call
        System.out.println("==========>> Result is: " + accounts);
        convertAccountNameToUppercase(accounts);
        System.out.println("==========>> Result after modified: " + accounts);
    }

    private void convertAccountNameToUppercase(List<Account> accounts){
        // Loop through the records
        for(Account account : accounts){
            // Get uppercase version of the name
            String name = account.getName().toUpperCase();
            // Update the name of the account
            account.setName(name);
        }


    }
}
