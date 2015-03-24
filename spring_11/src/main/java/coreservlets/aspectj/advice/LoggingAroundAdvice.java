package coreservlets.aspectj.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAroundAdvice {

    @Around("coreservlets.aspectj.advice.CoreservletsPointcuts.queryLayer()"
            + "|| coreservlets.aspectj.advice.CoreservletsPointcuts.reportLayer()")
    public Object log(ProceedingJoinPoint jp) throws Throwable {
        Logger log = Logger.getLogger(jp.getTarget().getClass());
        try {
            log.debug("before");
            log.debug("#" + jp.getSignature().getName() + "()");
            Object returnValue = jp.proceed();
            log.debug("after return");
            return returnValue;
        } catch (Throwable t) {
            log.debug("after throws");
            throw t;
        }
    }
}
