package string.calc.add;

import string.calc.factor.FactorExtractor;
import string.calc.splitter.ExpressionSplitter;

class StringAdderImpl implements StringAdder {
	private final FactorExtractor factorExtractor = new FactorExtractor();
	private int result = 0;

	StringAdderImpl(String expression, ExpressionSplitter expressionSplitter) {
		String[] parts = expressionSplitter.split(expression);
		ResultCalculator resultCalculator = new ResultCalculator(factorExtractor);
		this.result = resultCalculator.sum(parts);
	}

	@Override
	public Integer result() {
		checkErrors();
		return result;
	}

	private void checkErrors() {
		if (factorExtractor.hadErrors()) {
			throw new RuntimeException(factorExtractor.getErrorMessage());
		}
	}
}
