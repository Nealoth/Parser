package parser.css.impl;

import parser.css.BlockBuilder;
import parser.css.model.CssBlock;

import java.util.*;

class CssBlockBuilder implements BlockBuilder {

	private final CssSelectorBuilder cssSelectorBuilder = new CssSelectorBuilder();

	@Override
	public List<CssBlock> build(final String plainCss) {
		return separateBlocks(plainCss, null);
	}

	@Override
	public List<CssBlock> build(final String plainCss, final CssBlock parent) {
		return separateBlocks(plainCss, parent);
	}

	private List<CssBlock> separateBlocks(String plainCss, final CssBlock parent) {

		plainCss = plainCss.replaceAll("\n", "");

		final ArrayList<CssBlock> resultCssBlocks = new ArrayList<>();

		final char[] chars = plainCss.toCharArray();

		byte openBracersCount = 0;
		byte closeBracersCount = 0;

		int blockBeginIndex = 0;
		int blockCloseIndex = 0;

		for (int i = 0; i < chars.length; i++) {

			if (chars[i] == '{') {
				if (openBracersCount == 0) {
					blockBeginIndex = i;
				}
				openBracersCount++;
			}

			if (chars[i] == '}') {
				closeBracersCount++;

				if (openBracersCount == closeBracersCount) {
					blockCloseIndex = i;

					String resultPlainCssBlock = String.valueOf(Arrays.copyOfRange(chars, blockBeginIndex, blockCloseIndex + 1));

					CssBlock cssBlock = new CssBlock();
					cssBlock.setPlainName(findParentName(chars, blockBeginIndex));
					cssBlock.setPlainCss(resultPlainCssBlock);
					cssBlock.setParent(parent);
					cssBlock.setChildrenCount(closeBracersCount - 1);
					cssBlock.getSelectors()
							.addAll(cssSelectorBuilder.build(cssBlock.getPlainName()));

					if (cssBlock.getChildrenCount() < 1) {
						cssBlock.getProperties().putAll(constructProperties(cssBlock.getPlainCss()));
					}

					if (closeBracersCount > 1) {
						cssBlock.getChildren().addAll(build(trimBlockBracers(resultPlainCssBlock), cssBlock));
					}

					resultCssBlocks.add(cssBlock);

					openBracersCount = 0;
					closeBracersCount = 0;
				}
			}

		}
		resultCssBlocks.trimToSize();
		return resultCssBlocks;
	}

	private String findParentName(final char[] arr, final int index) {

		int parentNameBeginIndex = index;

		for (int i = index - 1; i >= 0; i--) {
			if (arr[i] == '{' || arr[i] == '}' || arr[i] == ';') {
				break;
			}
			parentNameBeginIndex--;
		}

		return String
				.copyValueOf(Arrays.copyOfRange(arr, parentNameBeginIndex, index))
				.trim();
	}

	private String trimBlockBracers(final String plainCss) {
		char[] chars = plainCss.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '{') {
				chars[i] = ' ';
				break;
			}
		}

		for (int i = chars.length - 1; i > 0; i--) {
			if (chars[i] == '}') {
				chars[i] = ' ';
				break;
			}
		}

		return String
				.valueOf(chars)
				.trim();
	}

	private Map<String, String> constructProperties(String plainCss) {

		plainCss = trimBlockBracers(plainCss);

		final HashMap<String, String> resultProperties = new HashMap<>();

		final String[] rows = plainCss.split(";");

		for (String row : rows) {
			final String[] property = row.split(":");

			if (property.length == 2) {
				resultProperties.put(property[0].trim(), property[1].trim());
			}
		}

		return resultProperties;
	}
}
