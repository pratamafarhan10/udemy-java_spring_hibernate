package project.customerrelationshipmanager.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
    @Pointcut("execution(* project.customerrelationshipmanager.*.*Controller.*(..))")
    private void controllerPackage(){}

    @Pointcut("execution(* project.customerrelationshipmanager.*.service.*.*(..))")
    private void servicePackage(){}

    @Pointcut("execution(* project.customerrelationshipmanager.*.dto.*.*(..))")
    private void dtoPackage(){}

    @Pointcut("controllerPackage() || servicePackage() || dtoPackage()")
    private void appFlow(){}
    
    @Before("appFlow()")
    public void before(JoinPoint joinPoint){
        System.out.println("=======>> in @Before aspect");
        // Display the method we're calling
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Method: " + method);
        // Display the arguments to the method
        Object[] args = joinPoint.getArgs();
        System.out.println("Arguments: ");
        for (Object arg :
                args) {
            System.out.println("- " + arg);
        }
        System.out.println("=======>>\n");
    }

    @AfterReturning(
            pointcut = "appFlow()",
            returning = "res"
    )
    public void afterReturning(JoinPoint joinPoint, Object res){
        System.out.println("\n=======>> in @AfterReturning aspect");
        // Display the method that we're returning from
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Method: " + method);
        // Display the data returned
        System.out.println("Return: " + res);
    }
}
