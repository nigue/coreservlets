package coreservlets.advice.ordering;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class NamedBeforeAdvice implements MethodBeforeAdvice {

    private static final Logger log = Logger.getLogger(NamedBeforeAdvice.class);

    private String name;

    public NamedBeforeAdvice() {
    }

    public NamedBeforeAdvice(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void before(Method method, Object[] arguments, Object target) throws Throwable {
        log.debug("before: " + this.name);
    }
}
