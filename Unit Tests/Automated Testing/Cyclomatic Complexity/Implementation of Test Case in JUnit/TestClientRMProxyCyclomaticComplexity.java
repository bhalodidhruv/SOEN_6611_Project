package org.apache.hadoop.yarn.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestClientRMProxyCyclomaticComplexity extends TestCase {

	private int methodWithReturnTypeCount;

	public void testGetRMAddress() throws ClassNotFoundException {
		// File codeSnippet = new
		// File("C:\\Users\\drvg5\\Downloads\\m3-SCM\\getRMAddress.txt");
		File codeSnippet = new File(
				"C:\\Users\\Navjot\\Desktop\\JUNIT CC\\RELEASE 3.0.0\\RELEASE 3.0.0\\AUTOMATED UNIT TESTING RELEASE 3.0.0\\CLASS_GETRMADDRESS_UT_LOGS\\ClientRMProxy.java");
		
		Scanner sc;
		try {
			sc = new Scanner(codeSnippet);
			List<String> lines = new ArrayList<String>();

			while (sc.hasNext()) {
				lines.add(sc.nextLine());

			}

			int cycloComplexity = 0;
			int bracket = 0;
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
			/*
			 * contingency for return type condition
			 *
			 * Class cls = Class.forName("ebay.ShoppingCart"); Method[] methods =
			 * cls.getMethods(); for (int i = 0; i < methods.length; i++) { if
			 * (!methods[i].toString().contains("Object") &&
			 * (!methods[i].getReturnType().toString().contains("void"))) {
			 * System.out.println(methods[i]); ++methodWithReturnTypeCount;
			 * 
			 * } }
			 */
			/*
			 * contingency for return type condition
			 *
			 * //cycloComplexity = cycloComplexity - methodWithReturnTypeCount;
			 */
			Assert.assertEquals(16, cycloComplexity);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
