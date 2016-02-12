package string.calc.preamble;

public class PreambleHandler {
	private final DelimiterHandler delimiterHandler;
	private final String expression;
	private final String preamble;

	public PreambleHandler(String input) {
		this.preamble = extractPreamble(input);
		if (hasPreamble()) {
			String delimiterSection = extractDelimiterSection(preamble);
			this.delimiterHandler = new DelimiterHandler(delimiterSection);
			this.expression = extractExpression(input);
		} else {
			this.delimiterHandler = new DelimiterHandler();
			this.expression = input;
		}
	}

	private String extractDelimiterSection(String preamble) {
		return preamble.substring(preamblePrefix().length());
	}

	public String[] getDelimiters() {
		return delimiterHandler.getDelimiters();
	}

	public String getExpressionStrippedOfPreamble() {
		return expression;
	}

	private String extractExpression(String input) {
		return input.substring(preamble.length()).trim();
	}

	private String extractPreamble(String input) {
		String firstLine = getInputFirstLine(input);
		if (firstLine.startsWith(preamblePrefix())) {
			return firstLine;
		} else {
			return emptyPreamble();
		}
	}

	private String emptyPreamble() {
		return "";
	}

	private String getInputFirstLine(String exp) {
		return exp.split(preambleEndString())[0];
	}

	private String preambleEndString() {
		return "\\n";
	}

	private boolean hasPreamble() {
		return !preamble.equals(emptyPreamble());
	}

	private String preamblePrefix() {
		return "//";
	}
}
