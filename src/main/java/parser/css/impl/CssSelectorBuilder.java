package parser.css.impl;

import parser.css.model.CssSelector;
import parser.css.model.CssSelectorType;

import java.util.ArrayList;
import java.util.List;

import static parser.css.model.CssSelectorType.*;

class CssSelectorBuilder {

	List<CssSelector> build(final String plainSelectorName) {
		String[] selectors = plainSelectorName.split(",");
		ArrayList<CssSelector> result = new ArrayList<>(selectors.length);

		for (String selectorName : selectors) {
			String trimmedSelectorName = selectorName.trim();
			CssSelector cssSelector = new CssSelector(trimmedSelectorName, getAllSelectorTypes(trimmedSelectorName));
			result.add(cssSelector);
		}

		return result;
	}


	private CssSelectorType getAllSelectorTypes(final String selector) {
		boolean complexSelector = selector.split(" ").length > 1;

		if (!complexSelector && selector.charAt(0) == '.')
			return CLASS;
		if (!complexSelector && selector.charAt(0) == '#')
			return ID;
		if (!complexSelector && Character.isLetter(selector.charAt(0)))
			return ELEMENT;
		if (selector.contains("@"))
			return RULE;
		if (complexSelector)
			return COMPLEX;

		return OTHER;
	}

}
