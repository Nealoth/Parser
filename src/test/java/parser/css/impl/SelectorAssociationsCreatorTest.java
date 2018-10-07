package parser.css.impl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectorAssociationsCreatorTest {

	@Test
	public void asd () {
		HashMap<String, ArrayList<String>> a = new HashMap<>();

		a.get("1")
				.add("1");
		a.get("1")
				.add("2");

		System.out.println(a.get("1"));
		System.out.println(a.get("1"));
	}

}