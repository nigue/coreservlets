package coreservlets.advice.all;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class AllAdvice implements MethodInterceptor, MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    @Override
    public void before(Method mtd, Object[] args, Object target) throws Throwable {
        Logger.getLogger(target.getClass()).debug("Before");
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        Logger.getLogger(target.getClass()).debug("After return");
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        Logger.getLogger(target.getClass()).debug("After throwing");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Logger log = Logger.getLogger(invocation.getThis().getClass());
        try {
            log.debug("Around before");
            return invocation.proceed();
        } finally {
            log.debug("Around after");
        }
    }
}
