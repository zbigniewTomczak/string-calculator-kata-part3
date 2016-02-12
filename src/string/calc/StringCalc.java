package string.calc;

import java.util.Optional;
import java.util.logging.Logger;

import string.calc.add.StringAdder;
import string.calc.add.StringAdderFactory;

public class StringCalc {
	private Optional<Logger> logger = Optional.empty();
	private Optional<IWebService> fallbackWs = Optional.empty();

	public Integer add(String exp) {
		StringAdderFactory factory = new StringAdderFactory();
		StringAdder adder = factory.create(exp);
		Integer result = adder.result();
		try {
			log(result);
		} catch (Exception e) {
			fallbackWs.ifPresent(ws -> ws.send(e.getMessage()));
		}
		return result;
	}

	private void log(Integer result) {
		logger.ifPresent(log -> log.info("Add result: " + result));
	}

	public void setLogger(Logger logger) {
		this.logger = Optional.of(logger);
	}

	public void setFallbackWs(IWebService fallbackWs) {
		this.fallbackWs = Optional.of(fallbackWs);
	}
}
