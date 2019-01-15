package parser.css.impl;

import parser.css.model.CssBlock;
import parser.css.model.CssSelector;

import java.util.*;

import static parser.css.model.CssSelectorType.RULE;

class AssociationMapBuilder {

	public Map<String, List<CssBlock>> build(Map<String, CssBlock> selectorsMap) {

		HashMap<String, List<CssBlock>> associations = new HashMap<>();
		Set<String> keys = selectorsMap.keySet();

		for (String key : keys) {

			CssBlock cssBlock = selectorsMap.get(key);

			boolean ruleExist = cssBlock
					.getSelectors()
					.stream()
					.anyMatch(cssSelector -> cssSelector.getTypes() == RULE);

			if (ruleExist)
				break;

			for (CssSelector selector : cssBlock.getSelectors()) {

				String complexSelector = selector.getPlainName();

				String[] singleSelectors = complexSelector.split(" ");

				for (String singleSelector : singleSelectors) {
					if (singleSelector.length() > 1) {
						if (associations.containsKey(singleSelector)) {
							associations
									.get(singleSelector)
									.add(cssBlock);
						} else {
							ArrayList<CssBlock> blocks = new ArrayList<>();
							blocks.add(cssBlock);

							associations.put(singleSelector, blocks);
						}
					}
				}

			}

		}

		return associations;
	}

}
