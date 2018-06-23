package parser.css.model;

import java.util.Map;
import java.util.Set;

public class CssDocument {

    private Map<String, CssBlock> generalSelectorMap;
    private Set<String> allUniqueClassSelectors;
    private Set<String> allUniqueIdSelectors;

    public CssDocument() { }

    public CssDocument(Map<String, CssBlock> generalSelectorMap) {
        this.generalSelectorMap = generalSelectorMap;
    }

    public Map<String, CssBlock> getGeneralSelectorMap() {
        return generalSelectorMap;
    }

    public void setGeneralSelectorMap(Map<String, CssBlock> generalSelectorMap) {
        this.generalSelectorMap = generalSelectorMap;
    }

    public Set<String> getAllUniqueClassSelectors() {
        return allUniqueClassSelectors;
    }

    public void setAllUniqueClassSelectors(Set<String> allUniqueClassSelectors) {
        this.allUniqueClassSelectors = allUniqueClassSelectors;
    }

    public Set<String> getAllUniqueIdSelectors() {
        return allUniqueIdSelectors;
    }

    public void setAllUniqueIdSelectors(Set<String> allUniqueIdSelectors) {
        this.allUniqueIdSelectors = allUniqueIdSelectors;
    }
}
