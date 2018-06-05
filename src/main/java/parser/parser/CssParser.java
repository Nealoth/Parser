package parser.parser;

import java.util.Arrays;
import java.util.HashMap;

public class CssParser {
    private boolean isInBlock = false;
    private char[] chars;
    private HashMap<String,String> cssMap = new HashMap<>();

    public HashMap<String, String> parse(String cssFile) {
        chars = cssFile.toCharArray();
        int lastBlockIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' && !isInBlock) {
                isInBlock = true;
                lastBlockIndex = i;
            }
            if (chars[i] == '}' && isInBlock) {
                isInBlock = false;
                separateElement(lastBlockIndex, i+1);
            }
        }
        return cssMap;
    }

    private String sliceCssProperties(int indexOfBlockStart, int indexOfBlockClose) {
        char[] copiedChars = Arrays.copyOfRange(this.chars, indexOfBlockStart, indexOfBlockClose);
        return String.copyValueOf(copiedChars);
    }

    private String sliceElementName(int indexOfBlockStart) {
        int iterationPoint = indexOfBlockStart;
        while (true) {
            if (chars[iterationPoint] != '}' && iterationPoint > 0) {
                iterationPoint--;
            } else {
                break;
            }
        }
        return String.copyValueOf(Arrays.copyOfRange(this.chars, iterationPoint+1, indexOfBlockStart)).trim();
    }

    private void separateElement(int indexOfBlockStart, int indexOfBlockClose) {
        cssMap.put(sliceElementName(indexOfBlockStart), sliceCssProperties(indexOfBlockStart, indexOfBlockClose));
    }
}
