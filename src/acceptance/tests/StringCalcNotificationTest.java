package acceptance.tests;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import string.calc.IWebService;
import string.calc.StringCalc;

/**
 */
public class StringCalcNotificationTest {

	private StringCalc calc = new StringCalc();

	private class TestLogger extends Logger {

		public TestLogger() {
			super("test.logger", null);
		}

		@Override
		public void info(String msg) {
			throw new RuntimeException("Exception if Logging");
		}
	}

	@Test
	public void add_internalLoggerThrowsException_notifiesWebService() {
		// given
		TestWs ws = new TestWs();
		calc.setLogger(new TestLogger());
		calc.setFallbackWs(ws);
		// when
		calc.add("1,2");
		//then
		Assert.assertEquals("Exception if Logging", ws.getMessage());
	}

	private class TestWs implements IWebService {
		private String message;

		@Override
		public void send(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
}
