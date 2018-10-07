package parser.css.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CssBlock {

	private String plainCss;

	private String plainName;

	private final List<CssSelector> selectors = new ArrayList<>();

	private int childrenCount;

	private final List<CssBlock> children = new ArrayList<>();

	private CssBlock parent;

	private final Map<String, String> properties = new HashMap<>();

	public String getPlainCss() {
		return plainCss;
	}

	public void setPlainCss(String plainCss) {
		this.plainCss = plainCss;
	}

	public int getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}

	public List<CssBlock> getChildren() {
		return children;
	}

	public CssBlock getParent() {
		return parent;
	}

	public void setParent(CssBlock parent) {
		this.parent = parent;
	}

	public String getPlainName() {
		return plainName;
	}

	public void setPlainName(String plainName) {
		this.plainName = plainName;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public List<CssSelector> getSelectors() {
		return selectors;
	}
}
