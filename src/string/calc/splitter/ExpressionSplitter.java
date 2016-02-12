package string.calc.splitter;

import java.util.regex.Pattern;

public class ExpressionSplitter {
	private final String regexp;

	public ExpressionSplitter(String[] delimiters) {
		this.regexp = prepareRegexp(delimiters);
	}

	public String[] split(String expression) {
		return expression.split(regexp);
	}

	private String prepareRegexp(String[] delimiters) {
		String regexp = "";

		for(String delimiter : delimiters) {
			regexp += Pattern.quote(delimiter);
			regexp += regexpOr();
		}
		regexp = stripLastChar(regexp);
		return regexp;
	}

	private String regexpOr() {
		return "|";
	}

	private String stripLastChar(String regexp) {
		return regexp.substring(0, regexp.length() - 1);
	}

}
