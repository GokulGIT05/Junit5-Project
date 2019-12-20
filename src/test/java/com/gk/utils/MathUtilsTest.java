package com.gk.utils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing MathUtils")
class MathUtilsTest {

	MathUtils testObj;
	
	TestInfo testInfo;
	TestReporter testReporter;

	// @BeforeAll & @AfterAll should be static methods, because this will be called
	// before MathUtilsTest instance is created.
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("@BeforeAll is Called... \n");
	}

	@AfterAll
	static void afterAllDestroy() {
		System.out.println("@AfterAll is called... \n");
	}

	@BeforeEach
	void initMethod(TestInfo testInfo,TestReporter testReporter) {
		this.testInfo  = testInfo;
		this.testReporter = testReporter;
		testObj = new MathUtils();
		System.out.println("@Before Each Calls... " + testObj + "\n");
		testReporter.publishEntry("Using TestReported");
	}

	@AfterEach
	void cleanUpEachMethod() {
		testObj = null;
		System.out.println("@AfterEach Cleaning up Each Method.. " + testObj + "\n");
	}

	// Test Methods
	@Test
	@DisplayName("Adding two Numbers")
	void testAdd() {
		// First Test Case
		int expectedResult = 2;
		int actualResult = testObj.add(1, 1);
		// First Assert Statement
		assertEquals(expectedResult, actualResult, "The add method should add two numbers");
	}

	// Tag & Tags, by using run configuration.
	@Nested
	@DisplayName("Testing Add Method with different inputs")
	@Tag("AddTest")
	class AddNestedTest {

		@Test
		@DisplayName("Adding two Positive Numbers")
		void testAdd() {
			// First Test Case
			int expectedResult = 2;
			int actualResult = testObj.add(1, 1);
			// First Assert Statement
			// Lambda is used, so only when fails, this expression will execute.
			assertEquals(expectedResult, actualResult, () -> "The add method should add two numbers expectedResult: "
					+ expectedResult + ", actualResult: " + actualResult);
			System.out.println("TestInfo testInfo; "+testInfo.getDisplayName() + testInfo.getTags());
		}

		@Test
		@DisplayName("Adding two Negative Numbers")
		void testAddNegative() {
			assertEquals(-2, testObj.add(-1, -1), "The add method should add two numbers");
		}
	}

	@Test
	@EnabledOnOs(OS.WINDOWS)
	@DisplayName("Square of given value")
	void testSquareValue() {
		int expectedResult = 4;
		int actualResult = testObj.squareValue(2);
		/*
		 * boolean isServerUp = false; assumeTrue(isServerUp);
		 */
		assertEquals(expectedResult, actualResult, "Square of given value is failed.");
	}

	//@Test
	@RepeatedTest(3)
	@DisplayName("Testing exception in divideValue()")
	void testDivideValueException() {
		assertThrows(ArithmeticException.class, () -> testObj.divideValue(1, 0), "Divide by Zero is not possible");
		// Executable means lambda expression.
		// assertThrows(NullPointerException.class, () -> testObj.divideValue(1,
		// 0),"Divide by Zero is not possible");
	}

	@Test
	@Disabled
	@DisplayName("TDD method. Development In-Progress")
	public void tddMethodInProgress() {
		fail("Its TDD - Test Driven Development");
	}
	
	// Alternate to nested is assert All.
	@Test
	@DisplayName("Testing Multiplies with different Values")
	public void testMultiplyUsingAssertAll() {
		// It takes collection of executables (lambda Expressions).		
		assertAll(
				() ->	assertEquals(0, testObj.multiplyValue(0, 2)),
				() ->	assertEquals(4, testObj.multiplyValue(2, 2)),
				() ->	assertEquals(8, testObj.multiplyValue(4, 2))				
				);
	}
	
	
}
