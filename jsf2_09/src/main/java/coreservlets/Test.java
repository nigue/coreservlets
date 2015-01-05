package coreservlets;

import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
public class Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    private List<String> options = Arrays.asList("foo", "bar", "baz");
    private List<Object> selections;

    public List<Object> getSelections() {
        return (selections);
    }

    public void setSelections(List<Object> selections) {
        this.selections = selections;
        LOGGER.debug("Setting selections to " + selections);
    }

    public List<String> getOptions() {
        return (options);
    }

    public String blah() {
        return (null);
    }
}
