package coreservlets;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class LoggingMethodAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation i) throws Throwable {
        String args = Arrays.toString(i.getArguments());
        String buf = getClass().getSimpleName()
                + "\n  - target    : " + i.getThis().getClass().getName()
                + "\n  - method    : " + i.getMethod().getName()
                + "\n  - arg values: " + args.substring(1, args.length() - 1);
        try {
            Object returnValue = i.proceed();
            buf += "\n  - ex return : " + returnValue;
            return returnValue;
        } catch (Throwable t) {
            buf += "\n  - ex error  : "
                    + t.getClass().getName() + " - " + t.getMessage();
            throw t;
        } finally {
            Logger.getLogger(i.getThis().getClass()).debug(buf);
        }
    }
}
