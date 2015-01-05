package coreservlets;

import coreservlets.data.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped // Slightly better than making data static
public class ColorOptions {

    private String[] colorNames
            = {"red", "orange", "yellow", "green", "blue", "purple"};
    private List<String> colorValues
            = Arrays.asList("#ff0000", "#ffa500", "#ffff00",
                    "#008000", "#0000ff", "#800080");
    private Map<String, String> colorMap = new LinkedHashMap<>();
    private List<Color> colorList = new ArrayList<>();

    public ColorOptions() {
        for (int i = 0; i < colorNames.length; i++) {
            String colorName = colorNames[i];
            String colorValue = colorValues.get(i);
            colorMap.put(colorName, colorValue);
            colorList.add(new Color(colorName, colorValue));
        }
    }

    public String[] getColorNames() {
        return (colorNames);
    }

    public List<String> getColorValues() {
        return (colorValues);
    }

    public Map<String, String> getColorMap() {
        return (colorMap);
    }

    public List<Color> getColorList() {
        return (colorList);
    }
}
