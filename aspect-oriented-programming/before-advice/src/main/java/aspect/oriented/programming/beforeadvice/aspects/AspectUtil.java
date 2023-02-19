package aspect.oriented.programming.beforeadvice.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectUtil {
    @Pointcut("execution(* deleteAccount(aspect.oriented.programming.beforeadvice.dto.Account))")
    public void deleteAccountPointcut(){}

    // Pointcut for all methods in account class
    @Pointcut("execution(* aspect.oriented.programming.beforeadvice.dto.Account.*(..))")
    public void accountClassPointcut(){}
    // Pointcut for getter methods in account class
    @Pointcut("execution(* aspect.oriented.programming.beforeadvice.dto.Account.get*(..))")
    public void accountClassGetterPointcut(){}
    // Pointcut for setter methods in account class
    @Pointcut("execution(* aspect.oriented.programming.beforeadvice.dto.Account.set*(..))")
    public void accountClassSetterPointcut(){}
    // Pointcut for all methods within account class exclude the getter and setter
    @Pointcut("accountClassPointcut() && !(accountClassGetterPointcut() || accountClassSetterPointcut())")
    public void accountClassPointcutExcludeGetterAndSetter(){}
}
