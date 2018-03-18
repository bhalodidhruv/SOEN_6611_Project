package testebay;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestClientRMProxyCyclomaticComplexity extends TestCase {

	private int methodWithReturnTypeCount;

	public void testGetRMAddress() throws ClassNotFoundException {
		// File codeSnippet = new
		// File("C:\\Users\\drvg5\\Downloads\\m3-SCM\\getRMAddress.txt");
		File codeSnippet = new File(
				"C:\\Users\\drvg5\\Downloads\\hadoop-rel-release-3.0.0\\hadoop-rel-release-3.0.0\\hadoop-yarn-project\\hadoop-yarn\\hadoop-yarn-common\\src\\main\\java\\org\\apache\\hadoop\\yarn\\client\\ClientRMProxy.java");

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
			 * //cycloComplexity = cycloComplexity - methodWithReturnTypeCount;
			 */
			Assert.assertEquals(16, cycloComplexity);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
