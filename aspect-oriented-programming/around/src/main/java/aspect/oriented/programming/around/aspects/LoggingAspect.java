package aspect.oriented.programming.around.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* aspect.oriented.programming.around.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint pjp) throws Throwable {
        // Print out method we are advising on
        System.out.println("=======>> Execute @Around advice for method: " + pjp.getSignature().toShortString());

        // Get begin timestamp
        long start = System.currentTimeMillis();

        // Now let's execute the method
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Exception e) {
            // Log the exception
            System.out.println(e.getMessage());

            // Give user custom message
            result = "Major accident! But no worries, your private AOP helicopter is on the way!";
//            throw e;
        }

        // Get end timestamp
        long end = System.currentTimeMillis();

        // Compute the duration
        long duration = end - start;
        System.out.println("=======>> Execution duration: " + duration + " miliseconds");

        return result;
    }
}
