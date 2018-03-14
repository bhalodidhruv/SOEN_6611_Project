package org.apache.hadoop.yarn.client;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/*
 * Junit test for testing the cyclomatic complexity calculated using metrics tools
 */
public class TestClientRMProxyCyclomaticComplexity {

	@Test
	public void testGetRMAddress() {
		File codeSnippet = new File(
				"C:\\Users\\Dhruv\\Desktop\\6611PRoject\\getRMAddress.txt");
		Scanner sc;
		try {
			sc = new Scanner(codeSnippet);
			List<String> lines = new ArrayList<String>();

			while (sc.hasNext()) {
				lines.add(sc.nextLine());

			}

			int cycloComplexity = 0;
			for (int i = 0; i < lines.size(); i++) {
				String[] singleLine = lines.get(i).split(" ");

				for (String s : singleLine) {
					if (s.equals("if") || singleLine.equals("for") || s.equals("while") || s.equals("case")
							|| s.equals("catch") || s.equals("throw") || s.equals("return") || s.equals("&&")
							|| s.equals("||") || s.equals("?")) {
 						++cycloComplexity;
					}

				}
			}

			System.out.println("CC : " + cycloComplexity);

		//junit test for comparing the result retrieved using metrics tools and calculation done in the test method 
		assertEquals(8,cycloComplexity);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
