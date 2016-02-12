package string.calc.preamble;

import java.util.regex.Pattern;

class DelimiterHandler {
	private final String[] delimiters;
	private final static String[] DEFAULT_DELIMITERS = new String[] {",", "\n"};

	DelimiterHandler(String delimiterSection) {
		if (delimiterSection.isEmpty()) {
			this.delimiters = DEFAULT_DELIMITERS;
			return;
		}

		if (isMultiCharDelimiter(delimiterSection)) {
			this.delimiters = extractDelimiters(delimiterSection);
		} else {
			this.delimiters = new String[]{delimiterSection};
		}
	}

	DelimiterHandler() {
		this.delimiters = DEFAULT_DELIMITERS;
	}

	String[] getDelimiters() {
		return delimiters;
	}

	private String[] extractDelimiters(String delimiterSection) {
		String withoutPrefixAndsuffix = stripPrefixAndSuffix(delimiterSection);
		return withoutPrefixAndsuffix.split(Pattern.quote(delimiterSeparator()));
	}

	private String delimiterSeparator() {
		return delimiterSectionSuffix() + delimiterSectionPrefix();
	}

	private String stripPrefixAndSuffix(String delimiterSection) {
		return delimiterSection.substring(delimiterSectionPrefix().length(),
				delimiterSection.length() - delimiterSectionSuffix().length());
	}

	private boolean isMultiCharDelimiter(String delimiterSection) {
		return delimiterSection.startsWith(delimiterSectionPrefix())
				&& delimiterSection.endsWith(delimiterSectionSuffix());
	}

	private String delimiterSectionSuffix() {
		return "]";
	}

	private String delimiterSectionPrefix() {
		return "[";
	}


}
