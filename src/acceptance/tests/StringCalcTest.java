package acceptance.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import string.calc.StringCalc;

/**
 */
public class StringCalcTest {

	private StringCalc calc = new StringCalc();

	@Test
	public void emptyString() {
		int res = calc.add("");
		assertEquals(0, res);
	}

	@Test
	public void one() throws Exception {
		int res = calc.add("1");
		assertEquals(1, res);
	}

	@Test
	public void twoNumbers() throws Exception {
		int res = calc.add("1,2");
		assertEquals(3, res);
	}

	@Test
	public void threeNumbers() throws Exception {
		int res = calc.add("1,2,3");
		assertEquals(6, res);
	}

	@Test
	public void threeNumbersWithNewLine() throws Exception {
		int res = calc.add("1\n2,3");
		assertEquals(6, res);
	}

	@Test
	public void withDelimiterPercent() throws Exception {
		int res = calc.add("//%\n1%2");
		assertEquals(3, res);
	}

	@Test
	public void negativeNumbersThrowException() throws Exception {
		try {
			calc.add("//%\n-1%-2");
		} catch (Exception e) {
			assertEquals("Negatives not allowed: -1, -2", e.getMessage());
			return;
		}
		Assert.fail("Should throw an exception");
	}

	@Test
	public void add_numberAboveThreshold_doesNotIncreaseSum() {
		// given
		String numberAboveThreshhold = "1001";
		// when
		int res = calc.add("1,"+numberAboveThreshhold);
		//then
		assertEquals(1, res);
	}

	@Test
	public void add_multiCharDelimiter_isSupported() {
		// given
		String multiCharDelimiter = "***";
		// when
		int res = calc.add("//[" + multiCharDelimiter + "]\n1***2***3");
		//then
		assertEquals(6, res);
	}

	@Test
	public void add_multipleDelimiters_areSupported() {
		// given
		String firstDelim = "*";
		String secondDelim = "%";
		// when
		int res = calc.add(String.format("//[%s][%s]\n1*2%%3", firstDelim, secondDelim));
		//then
		assertEquals(6, res);
	}

	@Test
	public void add_longMultipleDelimiters_areSupported() {
		// given
		String firstDelim = "***";
		String secondDelim = "[]";
		// when
		int res = calc.add(String.format("//[%s][%s]\n1***2[]3", firstDelim, secondDelim));
		//then
		assertEquals(6, res);
	}
}
