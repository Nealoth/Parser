package parser;

import parser.css.CssParser;
import parser.css.PlainCssParser;
import parser.css.model.CssDocument;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        CssParser plainCssParser = new PlainCssParser();

        try (FileInputStream inputStream = new FileInputStream("src/main/resources/test.css")) {
            byte[] a = new byte[inputStream.available()];

            inputStream.read(a);

            for (byte b : a) {
                char ch = (char) b;
                stringBuilder.append(ch);
            }

            CssDocument parsed = plainCssParser.parse(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
