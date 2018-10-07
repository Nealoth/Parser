package parser.css;

import parser.css.model.CssBlock;

import java.util.List;

public interface BlockBuilder {

	List<CssBlock> build(String plainCss);

	List<CssBlock> build(String plainCss, CssBlock parent);

}
