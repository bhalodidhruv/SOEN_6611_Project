package org.apache.hadoop.yarn.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestCalculateCohesion extends TestCase {

	public void testCohesion() {

		int noOfMethods = 0;
		int cohesion = 0;

		// find out attributes
		Class cls;
		List<String> listOfAttribs = new ArrayList<String>();
		try {
			cls = Class.forName("org.apache.hadoop.yarn.client.NMProxy");
			Field fields[] = cls.getFields();
			for (int i = 0; i < fields.length; i++) {
				System.out.println(fields[i].getName());
				listOfAttribs.add(fields[i].getName());
			}

			Method[] methods = cls.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].toString().contains(cls.getPackage().getName())
						&& !methods[i].toString().contains(cls.getSuperclass().getName())) {

					++noOfMethods;

				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (!listOfAttribs.isEmpty()) {
			Scanner sc;
			String content1 = "";
			String content2 = "";
			String content3 = "";
			String content4 = "";
			List<String> listOfContent = new ArrayList<String>();
			File file = new File("C:\\Users\\Navjot\\Desktop\\SEM 2\\SOEN 6611\\PRoject\\release 3\\client\\createNMProxy.txt");

			try {
				sc = new Scanner(file);

				while (sc.hasNext()) {
					content1 += sc.nextLine();

				}
				listOfContent.add(content1);
				System.out.println(content1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 

			// No of method pairs
			int noOfPairs = 4 * (4 - 1) / 2;

			HashSet<Integer> pairs = new HashSet<Integer>();
			int countIfPair = 0;
			for (String s : listOfAttribs) {
				for (String st : listOfContent) {
					if (st.contains(s)) {
						++countIfPair;
						if (countIfPair == 2) {
							pairs.add(countIfPair);

							break;

						}
					}

				}
			}
			System.out.println("LCOM : " + (4 - pairs.size()));
			cohesion = 4 - pairs.size();
		} else {
			System.out.println("LCOM : " + noOfMethods);
			cohesion = noOfMethods;

		}

		Assert.assertEquals(1, cohesion);

	}
}
