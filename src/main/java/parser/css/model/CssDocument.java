package parser.css.model;

import java.util.HashMap;
import java.util.Map;

public class CssDocument {

    private final Map<String, CssBlock> selectorMap =  new HashMap<>();

    private final Map<String, String> selectorAssociations = new HashMap<>();

	public Map<String, CssBlock> getSelectorMap() {
		return selectorMap;
	}

	public Map<String, String> getSelectorAssociations() {
		return selectorAssociations;
	}

}
