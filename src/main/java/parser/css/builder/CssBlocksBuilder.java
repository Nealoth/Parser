package parser.css.builder;

import parser.css.model.CssBlock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CssBlocksBuilder {

    public Map<String, CssBlock> buildBlocksMap(String plainCss) {

        Map<String, CssBlock> resultCssPropertiesMap = new HashMap<>();

        boolean isCursorInsideBlock = false;

        final char[] chars = plainCss.toCharArray();

        int lastBlockIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' && !isCursorInsideBlock) {
                isCursorInsideBlock = true;
                lastBlockIndex = i;
            }
            if (chars[i] == '}' && isCursorInsideBlock) {
                isCursorInsideBlock = false;
                CssBlock cssBlock = separateCssProperties(chars, lastBlockIndex, i + 1);
                resultCssPropertiesMap.put(cssBlock.getSelectorName(), cssBlock);
            }
        }
        return resultCssPropertiesMap;
    }

    private String sliceCssProperties(char[] chars, int indexOfBlockStart, int indexOfBlockClose) {
        char[] copiedChars = Arrays.copyOfRange(chars, indexOfBlockStart, indexOfBlockClose);
        return String.copyValueOf(copiedChars);
    }

    private String sliceSelectorName(char[] chars, int indexOfBlockStart) {
        int iterationPoint = indexOfBlockStart;
        while (true) {
            if (chars[iterationPoint] != '}' && iterationPoint > 0) {
                iterationPoint--;
            } else {
                break;
            }
        }
        return String.copyValueOf(Arrays.copyOfRange(chars, iterationPoint + 1, indexOfBlockStart)).trim();
    }

    private CssBlock separateCssProperties(char[] chars, int indexOfBlockStart, int indexOfBlockClose) {
        String cssProperties = sliceCssProperties(chars, indexOfBlockStart, indexOfBlockClose);
        String selectorName = sliceSelectorName(chars, indexOfBlockStart);
        return new CssBlock(selectorName, cssProperties);
    }
}
