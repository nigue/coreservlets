package coreservlets.advice.ordering;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class ErrorThrowingAroundAdvice implements MethodInterceptor {

    private static final Logger log = Logger.getLogger(ErrorThrowingAroundAdvice.class);

    private Class<? extends Throwable> throwableType;

    public ErrorThrowingAroundAdvice() {
    }

    public ErrorThrowingAroundAdvice(Class<? extends Throwable> throwableType) {
        this.throwableType = throwableType;
    }

    public Class<? extends Throwable> getThrowableType() {
        return throwableType;
    }

    public void setThrowableType(Class<? extends Throwable> throwableType) {
        this.throwableType = throwableType;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            throw throwableType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Error: error generation. Failed to generate error: "
                    + this.throwableType);
        }
        return invocation.proceed();
    }
}
