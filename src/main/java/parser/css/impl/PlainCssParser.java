package parser.css.impl;

import parser.css.BlockBuilder;
import parser.css.CommentaryDeleter;
import parser.css.CssParser;
import parser.css.model.CssBlock;
import parser.css.model.CssDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlainCssParser implements CssParser {

	private final BlockBuilder blockBuilder = new CssBlockBuilder();
	private final CommentaryDeleter commentaryDeleter = new CssCommentaryDeleter();

	@Override
	public CssDocument parse(final String plainCss) {
		CssDocument resultDocument = new CssDocument();
		Map<String, CssBlock> selectorMap = resultDocument.getSelectorMap();
		String plainCssWithoutComments = commentaryDeleter.purifyDocument(plainCss);

		List<CssBlock> build = blockBuilder.build(plainCssWithoutComments);

		for (CssBlock cssBlock : build) {
			selectorMap.put(cssBlock.getPlainName(), cssBlock);
		}

		return resultDocument;
	}
}
