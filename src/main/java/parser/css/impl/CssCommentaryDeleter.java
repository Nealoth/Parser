package parser.css.impl;

import parser.css.CommentaryDeleter;

import java.util.ArrayList;

class CssCommentaryDeleter implements CommentaryDeleter {

	public String deleteMultiLineComments(String plainCss) {

		char[] chars = plainCss.toCharArray();

		boolean isInCommentBlock = false;

		ArrayList<Character> uncommentedCharacters = new ArrayList<>(chars.length);

		for (int i = 0; i < chars.length; i++) {
			if (!isInCommentBlock) {
				if (chars[i] == '/') {
					if (i < chars.length - 1 && chars[i + 1] == '*') {
						isInCommentBlock = true;
					} else {
						uncommentedCharacters.add(chars[i]);
					}
				} else {
					uncommentedCharacters.add(chars[i]);
				}
			} else {
				if (chars[i] == '/' && chars[i - 1] == '*') {
					isInCommentBlock = false;
				}
			}
		}

		uncommentedCharacters.trimToSize();

		StringBuilder result = new StringBuilder();

		for (Character uncommentedCharacter : uncommentedCharacters) {
			result.append(uncommentedCharacter);
		}

		return result.toString();
	}

	@Override
	public String purifyDocument(String plainCss) {
		return deleteMultiLineComments(plainCss);
	}

}
