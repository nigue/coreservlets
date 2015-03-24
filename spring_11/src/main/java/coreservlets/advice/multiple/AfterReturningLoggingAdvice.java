package coreservlets.advice.multiple;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class AfterReturningLoggingAdvice implements AfterReturningAdvice {

    private static final Logger log = Logger.getLogger(AfterReturningLoggingAdvice.class);

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] arguments, Object target) throws Throwable {

        String argTypes = Arrays.toString(method.getParameterTypes());
        log.debug(getClass().getSimpleName()
                + ":\n\t" + target.getClass().getName()
                + "#" + method.getName()
                + "(" + argTypes.substring(1, argTypes.length() - 1) + ")"
                + "\n\texit=return[" + returnValue + "]");

    }
}
