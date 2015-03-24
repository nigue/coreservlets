package coreservlets.advice.afterthrows;

import org.apache.log4j.Logger;

import coreservlets.Customer;
import coreservlets.CustomerQuery;

/**
 * Mock CustomerQuery implementation designed to throw errors under all
 * circumstances.
 */
public class ErrorThrowingMockCustomerQuery implements CustomerQuery {

    private static final Logger log = Logger.getLogger(ErrorThrowingMockCustomerQuery.class);

    /**
     * The error type.
     */
    private Class<? extends RuntimeException> throwableType;

    /**
     * Creates a new ErrorThrowingMockCustomerQuery.
     *
     * @param throwableType required
     */
    public ErrorThrowingMockCustomerQuery(Class<? extends RuntimeException> throwableType) {
        if (throwableType == null) {
            throw new IllegalArgumentException("Required: throwable type.");
        }
        this.throwableType = throwableType;
    }

    /*
     * @see coreservlets.CustomerQuery#getCustomerByName(java.lang.String)
     */
    @Override
    public Customer getCustomerByName(String name) {
        try {
            throw throwableType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Error: error generation. Failed to generate error: "
                    + this.throwableType);
            return null;
        }
    }
}
