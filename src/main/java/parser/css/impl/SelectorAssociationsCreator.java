package parser.css.impl;

import parser.css.model.CssDocument;

import java.util.Map;
import java.util.Set;

class SelectorAssociationsCreator {

	public void createAssotiations(CssDocument cssDocument) {
		Map<String, String> selectorAssociations = cssDocument.getSelectorAssociations();
		Set<String> keys = cssDocument.getSelectorMap().keySet();

		for (String key : keys) {

			String[] splitted = key.split(",");

			for (String s : splitted) {
				selectorAssociations.put(s, key);

				String[] splittedBySpace = s.split(" ");

				if (splittedBySpace.length > 1) {
					for (String s1 : splittedBySpace) {
						selectorAssociations.put(s1, key);
					}
				}
			}

		}
	}

}
