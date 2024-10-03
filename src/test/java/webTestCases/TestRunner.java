package webTestCases;

import org.testng.TestNG;

public class TestRunner {

	static TestNG testng;
	
	public static void main(String[] args) {
		
		testng=new TestNG();
		testng.setTestClasses(new Class[] {Module1.class,Module2.class});
		testng.run();
	}

}
