package coreservlets.aspectj.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CoreservletsPointcuts {

    @Pointcut("target(coreservlets.CustomerQuery)")
    public void queryLayer() {
    }

    @Pointcut("target(coreservlets.CustomerReport)")
    public void reportLayer() {
    }

}
