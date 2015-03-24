package coreservlets.advice.before;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class BeforeLoggingAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method mtd, Object[] args, Object target) throws Throwable {
        Logger.getLogger(target.getClass()).debug(
                target.getClass().getSimpleName()
                + "#" + mtd.toGenericString()
                + ". args=" + Arrays.toString(args));
    }
}
