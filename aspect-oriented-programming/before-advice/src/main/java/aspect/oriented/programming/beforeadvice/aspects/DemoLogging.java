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
}
