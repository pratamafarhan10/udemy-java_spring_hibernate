package aspect.oriented.programming.beforeadvice.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLogging {
//    @Before("execution(public void add*())")
    @Before("execution(* add*())")
    public void beforeAddAccountAdvice(){
        System.out.println("\n=======>>> Hey i'm executing @Before advice on addAccount()");
    }

    @Before("execution(* addAccount(*))")
    public void beforeAddAccountWithParameterAdvice(){
        System.out.println("\n=======>>> Hey i'm executing @Before advice on addAccount() with ONE PARAMETER");
    }

    @Before("execution(* addAccount(aspect.oriented.programming.beforeadvice.dto.Account))")
    public void beforeAddAccountWithParameterOfAnotherObjectAdvice(){
        System.out.println("\n=======>>> Hey i'm executing @Before advice on addAccount(Account account) with ONE PARAMETER OF ANOTHER OBJECT");
    }

    @Before("execution(* addAccount(String, ..))")
    public void beforeAddAccountWithMoreThanOneParameterAdvice(){
        System.out.println("\n=======>>> Hey i'm executing @Before advice on addAccount(String firstName, String lastName) with MORE THAN ONE PARAMETER");
    }

    @Before("execution(* aspect.oriented.programming.beforeadvice.dto.Account.addAccount(String, ..))")
    public void beforeAddAccountWithSpecificMethodInAClassAdvice(){
        System.out.println("\n=======>>> Hey i'm executing @Before advice on addAccount(String firstName, String lastName) with SPECIFIC METHOD IN A CLASS");
    }
}
