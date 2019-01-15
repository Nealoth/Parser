package parser.css.impl;

import parser.css.BlockBuilder;
import parser.css.CommentaryDeleter;
import parser.css.CssParser;
import parser.css.model.CssBlock;
import parser.css.model.CssDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlainCssParser implements CssParser {

	private final BlockBuilder blockBuilder = new CssBlockBuilder();
	private final CommentaryDeleter commentaryDeleter = new CssCommentaryDeleter();
	private final AssociationMapBuilder associationMapBuilder = new AssociationMapBuilder();

	@Override
	public CssDocument parse(final String plainCss) {
		String plainCssWithoutComments = commentaryDeleter.purifyDocument(plainCss);
		List<CssBlock> blocks = blockBuilder.build(plainCssWithoutComments);

		Map<String, CssBlock> selectorsMap = buildSelectorsMap(blocks);
		Map<String, List<CssBlock>> associationsMap = associationMapBuilder.build(selectorsMap);

		return new CssDocument(selectorsMap, associationsMap);
	}

	private Map<String, CssBlock> buildSelectorsMap(List<CssBlock> blocks) {

		HashMap<String, CssBlock> selectorMap = new HashMap<>();

		for (CssBlock cssBlock : blocks) {
			selectorMap.put(cssBlock.getPlainName(), cssBlock);
		}

		return selectorMap;
	}
}
