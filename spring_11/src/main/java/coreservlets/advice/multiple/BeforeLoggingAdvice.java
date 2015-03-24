package coreservlets.advice.multiple;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class BeforeLoggingAdvice implements MethodBeforeAdvice {

    private static final Logger log = Logger.getLogger(BeforeLoggingAdvice.class);

    @Override
    public void before(Method method, Object[] arguments, Object target) throws Throwable {
        String args = Arrays.toString(arguments);
        String argTypes = Arrays.toString(method.getParameterTypes());
        log.debug(getClass().getSimpleName()
                + ":\n\t" + target.getClass().getName()
                + "#" + method.getName()
                + "(" + argTypes.substring(1, argTypes.length() - 1) + ")"
                + "\n\targs=" + args);
    }
}
