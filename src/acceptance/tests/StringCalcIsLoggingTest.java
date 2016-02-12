package acceptance.tests;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import string.calc.StringCalc;

/**
 */
public class StringCalcIsLoggingTest {

	private StringCalc calc = new StringCalc();

	private class TestLogger extends Logger {

		private String infoLogged;

		public TestLogger() {
			super("test.logger", null);
		}

		@Override
		public void info(String msg) {
			infoLogged = msg;
		}

		public String getInfoLogged() {
			return infoLogged;
		}
	}

	@Test
	public void add_isLoggingSum() {
		// given
		String exp = "1,2";
		TestLogger logger = new TestLogger();
		calc.setLogger(logger);
		// when
		calc.add(exp);
		//then
		Assert.assertEquals("Add result: 3", logger.getInfoLogged());
	}

}
