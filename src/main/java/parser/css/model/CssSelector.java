package parser.css.model;

import java.util.List;

public class CssSelector {

	private final String plainName;
	private final CssSelectorType types;
	private final List<CssSelector> children;

	public CssSelector(String plainName, CssSelectorType types) {
		this.plainName = plainName;
		this.types = types;
		this.children = null;
	}

	public CssSelector(String plainName, CssSelectorType types, List<CssSelector> children) {
		this.plainName = plainName;
		this.types = types;
		this.children = children;
	}

	public String getPlainName() {
		return plainName;
	}

	public CssSelectorType getTypes() {
		return types;
	}
}
