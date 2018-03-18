
package testebay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import junit.framework.TestCase;

public class CalculateCBO2 extends TestCase {

	public void testCalculatedCbo() throws IOException {

		// getting all the .class file names for the set of classes
		File folder = new File(
				"C:\\\\Users\\\\drvg5\\\\Downloads\\\\JUnitTutorial\\\\JUnitTutorial\\\\UnitTest\\\\src\\\\ebay");

		HashMap<Integer, String> allClassName = new HashMap<Integer, String>();

		int i = 1;
		for (String fileName : folder.list()) {
			System.out.println(i + ". " + fileName);
			allClassName.put(i, "ebay." + fileName);
			++i;

		}

		// choose file for which CBO is to be calculated
		// enter the serial number
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Choice ");

		try {
			calculateCBO(allClassName.get(sc.nextInt()), allClassName);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void calculateCBO(String classFileName, HashMap<Integer, String> allClassName)
			throws IOException, ClassNotFoundException {

		Class cls;

		int CBO = 0;

		// step 1 - get methods and fields of the class for which CBO is to be
		// calculated

		File file = new File(
				"C:\\\\Users\\\\drvg5\\\\Downloads\\\\JUnitTutorial\\\\JUnitTutorial\\\\UnitTest\\\\src\\\\ebay\\\\"
						+ classFileName.substring(5, classFileName.length()));

		Scanner sc;
		List<String> lines = new ArrayList<String>();

		try {
			sc = new Scanner(file);

			while (sc.hasNext()) {
				lines.add(sc.nextLine());

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// step 2 - get methods and fields of the other classes in the package and
		// calculate CBO
		// for inherited properties and import coupling

		// common list of attributes
		List<String> listOfAttribs = new ArrayList<String>();
		// list of attributes for class for which CBO is to be calculated
		List<String> cboClassAttribs = new ArrayList<String>();

		String otherClassFileName = "";

		// calculation for inherited properties and import coupling
		for (Integer key : allClassName.keySet()) {
			if (!allClassName.get(key).equals(classFileName)) {

				otherClassFileName = allClassName.get(key);
				otherClassFileName = otherClassFileName.replaceAll(".java", "");
				cls = Class.forName(otherClassFileName);

				listOfAttribs = computeListOfAttributes(cls);

				CBO += incrementCBO(lines, listOfAttribs);
			} else {
				otherClassFileName = allClassName.get(key);
				otherClassFileName = otherClassFileName.replaceAll(".java", "");
				cls = Class.forName(otherClassFileName);
				cboClassAttribs = computeListOfAttributes(cls);

			}

		}

		// step 3 - calculation for export coupling
		classFileName = classFileName.replaceAll(".java", "");
		cls = Class.forName(classFileName);
		System.out.println(cls.getName());

		for (Integer key : allClassName.keySet()) {
			if (!allClassName.get(key).equals(cls.getName() + ".java")
					&& !allClassName.get(key).equals(cls.getSuperclass().getName() + ".java")) {
				CBO += calculateforExportCoupling(allClassName.get(key), cboClassAttribs);

			}
		}

		System.out.println("CBO : " + CBO);

	}

	private int calculateforExportCoupling(String name, List<String> cboClassAttribs) {
		File file = new File(
				"C:\\\\Users\\\\drvg5\\\\Downloads\\\\JUnitTutorial\\\\JUnitTutorial\\\\UnitTest\\\\src\\\\ebay\\\\"
						+ name.substring(5, name.length()));
		Scanner sc;
		List<String> lines = new ArrayList<String>();

		try {
			sc = new Scanner(file);

			while (sc.hasNext()) {
				lines.add(sc.nextLine());

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String l : lines) {
			for (String attrib : cboClassAttribs) {
				if (l.contains(attrib)) {
					return 1;
				}

			}
		}
		return 0;
	}

	int incrementCBO(List<String> lines, List<String> listOfAttribs) {
		for (String line : lines) {
			for (String attribs : listOfAttribs) {
				if (line.contains(attribs)) {
					return 1;
				}
			}
		}
		return 0;
	}

	private List<String> computeListOfAttributes(Class cls) {
		List<String> listOfAttribs = new ArrayList<String>();

		// returns all the public in the specified class

		Field fields[] = cls.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName());
			listOfAttribs.add(fields[i].getName());
		}

		// returns all the public methods in the specified class

		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].toString().contains(cls.getPackage().getName())
					&& !methods[i].toString().contains(cls.getSuperclass().getName())) {
				listOfAttribs.add(methods[i].getName());
				System.out.println(methods[i].getName());

			}
		}
		return listOfAttribs;

	}

}
