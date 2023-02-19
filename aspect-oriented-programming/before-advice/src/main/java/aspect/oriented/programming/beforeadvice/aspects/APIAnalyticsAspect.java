package aspect.oriented.programming.beforeadvice.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class APIAnalyticsAspect {

    @Before("aspect.oriented.programming.beforeadvice.aspects.AspectUtil.accountClassPointcutExcludeGetterAndSetter()")
    public void deleteAccountAPIAnalytics(){
        System.out.println("=======>>> PERFORM API ANALYTICS ADVICE with a POINTCUT DECLARATIONS");
    }
}
