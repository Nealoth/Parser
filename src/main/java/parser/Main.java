package parser;


import parser.parser.CssParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        CssParser cssParser = new CssParser();

        try (FileInputStream inputStream = new FileInputStream("src/main/resources/test.css")) {
            byte []a = new byte[inputStream.available()];
            inputStream.read(a);

            for (byte b : a) {
                char ch = (char) b;
                if (!Character.isWhitespace(ch)) {
                    stringBuilder.append(ch);
                }
            }

            HashMap<String, String> parsed = cssParser.parse(stringBuilder.toString());

            parsed.forEach((s, s2) -> System.out.println(s + " " + s2 + "\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
