package parser.css.model;

import java.util.List;
import java.util.Map;

public class CssDocument {

    private final Map<String, CssBlock> selectorMap;
    private final Map<String, List<CssBlock>> associationsMap;

	public CssDocument(Map<String, CssBlock> selectorsMap, Map<String, List<CssBlock>> associationsMap) {
		this.selectorMap = selectorsMap;
		this.associationsMap = associationsMap;
	}

	public Map<String, CssBlock> getSelectorMap() {
		return selectorMap;
	}

	public Map<String, List<CssBlock>> getAssociationsMap() {
		return associationsMap;
	}
}
