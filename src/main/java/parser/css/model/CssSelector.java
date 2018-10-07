package parser.css.model;

import java.util.List;

public class CssSelector {

	private final String plainName;
	private final CssSelectorType type;
	private final List<CssSelector> children;

	public CssSelector(String plainName, CssSelectorType type) {
		this.plainName = plainName;
		this.type = type;
		this.children = null;
	}

	public CssSelector(String plainName, CssSelectorType type, List<CssSelector> children) {
		this.plainName = plainName;
		this.type = type;
		this.children = children;
	}

	public String getPlainName() {
		return plainName;
	}

	public CssSelectorType getType() {
		return type;
	}
}
