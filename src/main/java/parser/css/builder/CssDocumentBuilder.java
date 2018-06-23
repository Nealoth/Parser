package parser.css.builder;

import parser.css.model.CssBlock;
import parser.css.model.CssDocument;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CssDocumentBuilder {

    public CssDocument build(Map<String, CssBlock> blocksMap) {

        Set<String> selectorsSet = findAllClassSelectors(blocksMap.keySet());

        CssDocument cssDocument = new CssDocument();
        cssDocument.setAllUniqueClassSelectors(findAllClassSelectors(selectorsSet));
        cssDocument.setAllUniqueIdSelectors(findAllIdSelectors(selectorsSet));

        return cssDocument;
    }

    private Set<String> findAllClassSelectors(Set<String> selectors) {

        Set<String> result = new HashSet<>();

        for (String selector : selectors) {
            if (selector.charAt(0) == '.') {
                String[] split = selector.split(" ");
                for (String complexSelector : split) {
                    String[] split1 = complexSelector.split(",");
                    for (String singleSelector : split1) {
                        if(singleSelector.charAt(0) == '.')
                            result.add(singleSelector);
                    }
                }
            }
        }

        return result;
    }

    private Set<String> findAllIdSelectors(Set<String> selectors) {

        Set<String> result = new HashSet<>();

        for (String selector : selectors) {
            if (selector.charAt(0) == '#') {
                String[] split = selector.split(" ");
                for (String complexSelector : split) {
                    String[] split1 = complexSelector.split(",");
                    for (String singleSelector : split1) {
                        if(singleSelector.charAt(0) == '#')
                            result.add(singleSelector);
                    }
                }
            }
        }

        return result;
    }

}
