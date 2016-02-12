package string.calc.add;

import string.calc.factor.FactorExtractor;

class ResultCalculator {
	private final FactorExtractor factorExtractor;

	ResultCalculator(FactorExtractor factorExtractor) {
		this.factorExtractor = factorExtractor;

	}

	int sum(String[] parts) {
		int result = 0;
		for (String part : parts) {
			result = applySum(result, factorExtractor.get(part));
		}
		return result;
	}

	private int applySum(int result, int part) {
		return result + part;
	}
}
