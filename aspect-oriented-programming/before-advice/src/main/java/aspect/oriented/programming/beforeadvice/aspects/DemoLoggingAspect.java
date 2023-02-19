package aspect.oriented.programming.beforeadvice.aspects;

import aspect.oriented.programming.beforeadvice.dto.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class DemoLoggingAspect {
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
        System.out.println("=======>>> @Before advice on addAccount(String firstName, ...) with SPECIFIC METHOD IN A CLASS");
    }

    @Before("aspect.oriented.programming.beforeadvice.aspects.AspectUtil.deleteAccountPointcut()")
    public void deleteAccountAdvice(JoinPoint jointPoint){
        System.out.println("=======>>> @Before DELETE ACCOUNT ADVICE with a POINTCUT DECLARATIONS");

        // Get signature
        MethodSignature sig = (MethodSignature) jointPoint.getSignature();
        System.out.println("\tMethod: " + sig);
        Object[] args = jointPoint.getArgs();
        System.out.println("\tArguments:");
        for(Object arg : args){
            System.out.println("\t" + arg);
            if (arg instanceof Account){
                Account theAccount = (Account) arg;
                System.out.println("\tThe name of the account: " + theAccount.getName());
            }
        }
    }

    @Before("aspect.oriented.programming.beforeadvice.aspects.AspectUtil.accountClassPointcutExcludeGetterAndSetter()")
    public void accountClassAdviceExcludeGetterAndSetter(){
        System.out.println("=======>>> @Before on ACCOUNT CLASS excluding the GETTER and SETTER methods");
    }
}
