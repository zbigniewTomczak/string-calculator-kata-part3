package string.calc.add;

import string.calc.preamble.PreambleHandler;
import string.calc.splitter.ExpressionSplitter;

public class StringAdderFactory {
	public StringAdder create(String input) {
		if (input.isEmpty()) {
			return new EmptyStringAdder();
		}

		PreambleHandler preambleHandler = new PreambleHandler(input);
		ExpressionSplitter expressionSplitter = new ExpressionSplitter(preambleHandler.getDelimiters());
		String expression = preambleHandler.getExpressionStrippedOfPreamble();
		return new StringAdderImpl(expression, expressionSplitter);
	}
}
