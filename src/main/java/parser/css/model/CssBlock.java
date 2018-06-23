package parser.css.model;

import java.util.Map;

public class CssBlock {

    private String plainCssProperties;

    private Map<String, String> mappedCssProperties;

    private String selectorName;

    public CssBlock(String selectorName, String plainCss) {
        this.plainCssProperties = plainCss;
        this.selectorName = selectorName;
        this.mappedCssProperties = tryToParseCssProperties(plainCss);
    }

    public String getPlainCssProperties() {
        return plainCssProperties;
    }

    public String getSelectorName() {
        return selectorName;
    }

    public Map<String, String> getMappedCssProperties() {
        return mappedCssProperties;
    }

    private Map<String, String> tryToParseCssProperties(String plainCss) {

        plainCss = plainCss.trim()
                .replace("{", "")
                .replace("}", "");

        String[] split = plainCss.split(";");

        for (String s : split) {
            System.out.println(s);
        }
        return null;
    }

    @Override
    public String toString() {
        return "CssBlock{" +
                "plainCssProperties='" + plainCssProperties + '\'' +
                '}';
    }
}
