package parser.css;

import parser.css.builder.CssBlocksBuilder;
import parser.css.builder.CssDocumentBuilder;
import parser.css.model.CssBlock;
import parser.css.model.CssDocument;

import java.util.Map;

public class PlainCssParser implements CssParser {

    private CssBlocksBuilder builder = new CssBlocksBuilder();
    private CssCommentaryDeleter cssCommentaryDeleter = new CssCommentaryDeleter();
    private CssDocumentBuilder documentBuilder = new CssDocumentBuilder();

    @Override
    public CssDocument parse(String plainCss) {

        String cssWithoutComments = cssCommentaryDeleter.deleteComments(plainCss);

        Map<String, CssBlock> stringCssBlockMap = builder.buildBlocksMap(cssWithoutComments);

        return documentBuilder.build(stringCssBlockMap);
    }
}
