package webTestCases;

import org.testng.TestNG;

public class TestRunner {

	static TestNG testng;
	
	public static void main(String[] args) {
		
		testng=new TestNG();
		testng.setTestClasses(new Class[] {Module1_Registration.class,Module2_Login.class,Module3_Search.class});
		testng.run();
	}

}
