package coreservlets.aspectj.schema;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAroundAdvice {

    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = Logger.getLogger(joinPoint.getTarget().getClass());
        try {
            log.debug("before");
            log.debug("#" + joinPoint.getSignature().getName() + "()");
            Object returnValue = joinPoint.proceed();
            log.debug("after return");
            return returnValue;
        } catch (Throwable t) {
            log.debug("after throws");
            throw t;
        }
    }
}
