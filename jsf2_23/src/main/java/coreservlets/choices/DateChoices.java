package coreservlets.choices;

import coreservlets.utils.DateUtils;
import java.text.DateFormatSymbols;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.*;

// Inspired by the Dates class in Chapter 9 of "Core JavaServer Faces, 3rd Edition".
// TODO: Let the component specify the range of years.
// TODO: Use Ajax to make sure range of days matches month.
// TODO: Make current date the start date, but needs Ajax above.
// Note: all three of these problems fixed in later versions
@ManagedBean
@ApplicationScoped
public class DateChoices {

    private int[] days;
    private int[] years;
    private Map<String, Integer> months;

    public DateChoices() {
        days = DateUtils.intArray(1, 31);
        years = DateUtils.intArray(1900, 2100);
        months = new LinkedHashMap<>();
        String[] names = new DateFormatSymbols().getMonths();
        for (int i = 0; i < 12; i++) {
            months.put(names[i], i + 1);
        }
    }

    public int[] getDays() {
        return (days);
    }

    public int[] getYears() {
        return (years);
    }

    public Map<String, Integer> getMonths() {
        return (months);
    }
}
