package aspect.oriented.programming.beforeadvice.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLogging {
//    @Before("execution(public void add*())")
    @Before("execution(* add*())")
    public void beforeAddAccountAdvice(){
        System.out.println("=======>>> @Before advice on A METHOD THAT STARTS WITH ADD");
    }

    @Before("execution(* addAccount(*))")
    public void beforeAddAccountWithParameterAdvice(){
        System.out.println("=======>>> @Before advice on addAccount() with ONE PARAMETER");
    }

    @Before("execution(* addAccount(aspect.oriented.programming.beforeadvice.dto.Account))")
    public void beforeAddAccountWithParameterOfAnotherObjectAdvice(){
        System.out.println("=======>>> @Before advice on addAccount(Account account) with ONE PARAMETER OF ANOTHER SPECIFIC OBJECT");
    }

    @Before("execution(* addAccount(String, ..))")
    public void beforeAddAccountWithMoreThanOneParameterAdvice(){
        System.out.println("=======>>> @Before advice on addAccount(String firstName, String lastName) with FIRST PARAMETER IS STRING AND HAVE MORE THAN ONE PARAMETER");
    }

    @Before("execution(* aspect.oriented.programming.beforeadvice.dto.Account.addAccount(String, ..))")
    public void beforeAddAccountWithSpecificMethodInAClassAdvice(){
        System.out.println("=======>>> @Before advice on addAccount(String firstName, String lastName) with SPECIFIC METHOD IN A CLASS");
    }

    @Pointcut("execution(* deleteAccount(aspect.oriented.programming.beforeadvice.dto.Account))")
    private void deleteAccountPointcut(){}

    @Before("deleteAccountPointcut()")
    public void peformAPIAnalytics(){
        System.out.println("=======>>> @Before PERFORM API ANALYTICS ADVICE with a POINTCUT DECLARATIONS");
    }

    @Before("deleteAccountPointcut()")
    public void deleteAccountAdvice(){
        System.out.println("=======>>> @Before DELETE ACCOUNT ADVICE with a POINTCUT DECLARATIONS");
    }

    // Pointcut for all methods in account class
    @Pointcut("execution(* aspect.oriented.programming.beforeadvice.dto.Account.*(..))")
    private void accountClassPointcut(){}
    // Pointcut for getter methods in account class
    @Pointcut("execution(* aspect.oriented.programming.beforeadvice.dto.Account.get*(..))")
    private void accountClassGetterPointcut(){}
    // Pointcut for setter methods in account class
    @Pointcut("execution(* aspect.oriented.programming.beforeadvice.dto.Account.set*(..))")
    private void accountClassSetterPointcut(){}
    // Pointcut for all methods within account class exclude the getter and setter
    @Pointcut("accountClassPointcut() && !(accountClassGetterPointcut() || accountClassSetterPointcut())")
    private void accountClassPointcutExcludeGetterAndSetter(){}

    @Before("accountClassPointcutExcludeGetterAndSetter()")
    public void accountClassAdviceExcludeGetterAndSetter(){
        System.out.println("=======>>> @Before on ACCOUNT CLASS excluding the GETTER and SETTER methods");
    }
}
