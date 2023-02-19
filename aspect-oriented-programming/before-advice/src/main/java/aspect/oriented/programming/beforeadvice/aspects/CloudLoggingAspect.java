package aspect.oriented.programming.beforeadvice.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CloudLoggingAspect {

    @Before("aspect.oriented.programming.beforeadvice.aspects.AspectUtil.accountClassPointcutExcludeGetterAndSetter()")
    public void accountClass(){
        System.out.println("=======>>> Log to cloud");
    }
}
