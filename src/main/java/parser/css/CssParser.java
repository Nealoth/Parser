package parser.css;

import parser.css.model.CssDocument;

public interface CssParser {
	CssDocument parse(String plainCss);
}
