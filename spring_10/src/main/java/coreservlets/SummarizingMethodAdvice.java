package coreservlets;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * Similar to a full logging method interceptor, this implementation produces a
 * simplified debugging log message.
 */
public class SummarizingMethodAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation inv) throws Throwable {
        String buffer = "[" + inv.getThis().getClass().getSimpleName() + "]"
                + " " + inv.getMethod().toString()
                + ". args=" + Arrays.toString(inv.getArguments());
        try {
            Object returnValue = inv.proceed();
            buffer += ". exit=return[" + returnValue + "]";
            return returnValue;
        } catch (Throwable t) {
            buffer += "  - exit=error[" + t + "]";
            throw t;
        } finally {
            Logger log = Logger.getLogger(inv.getThis().getClass());
            log.debug(">>> " + buffer);
        }
    }
}
