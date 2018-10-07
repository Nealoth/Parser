package parser.css.impl;

import parser.css.model.CssSelector;
import parser.css.model.CssSelectorType;

import java.util.ArrayList;
import java.util.List;

class CssSelectorBuilder {

	List<CssSelector> build(final String plainSelectorName) {
		String[] selectors = plainSelectorName.split(",");
		ArrayList<CssSelector> result = new ArrayList<>(selectors.length);

		for (String selectorName : selectors) {
			String trimmedSelectorName = selectorName.trim();
			CssSelector cssSelector = new CssSelector(trimmedSelectorName, getSelectorType(trimmedSelectorName));
			result.add(cssSelector);
		}

		return result;
	}


	private CssSelectorType getSelectorType(final String selectorName) {
		if (selectorName.charAt(0) == '.')
			return CssSelectorType.CLASS;
		if (selectorName.charAt(0) == '#')
			return CssSelectorType.ID;
		if (Character.isLetter(selectorName.charAt(0)))
			return CssSelectorType.ELEMENT;

		return null;
	}
}
