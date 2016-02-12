package string.calc.factor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FactorExtractor {
	private final List<Integer> negatives = new ArrayList<>();

	public int get(String part) {
		return extractFactor(part);
	}

	private int extractFactor(String part) {
		int factor = Integer.parseInt(part.trim());
		if (factor < 0) {
			cumulateNegativeFactor(factor);
		}
		if (isAboveThreshold(factor)) {
			return getAboveThresholdFactor();
		}
		return factor;
	}

	private int getAboveThresholdFactor() {
		return 0;
	}

	private boolean isAboveThreshold(int factor) {
		return factor > 1000;
	}

	private void cumulateNegativeFactor(int factor) {
		negatives.add(factor);
	}

	public boolean hadErrors() {
		return !negatives.isEmpty();
	}

	public String getErrorMessage() {
		if (hadErrors()) {
			String message = "Negatives not allowed: ";
			message += negatives.stream().map(Object::toString).collect(Collectors.joining(", "));
			return message;
		}
		return "No error";
	}
}
