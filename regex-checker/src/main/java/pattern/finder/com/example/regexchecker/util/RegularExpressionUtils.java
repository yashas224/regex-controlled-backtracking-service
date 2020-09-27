package pattern.finder.com.example.regexchecker.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
// component to handle behavior for regular expression running into catastrophic backtracking

@Component
public class RegularExpressionUtils {
	private Matcher matcher;
	private Pattern pattern;

	public String getMatch(String input, int timeOut, String regex) throws Exception {
		String match = "";
		matcher = createMatcherWithTimeout(input, regex, timeOut);
		if (matcher.find()) {
			match = matcher.group();
		}
		return match;
	}

	public Matcher createMatcherWithTimeout(String stringToMatch, String regularExpression, int timeoutMillis) {
		pattern = Pattern.compile(regularExpression);
		return createMatcherWithTimeout(stringToMatch, pattern, timeoutMillis);
	}

	public Matcher createMatcherWithTimeout(String stringToMatch, Pattern regularExpressionPattern, int timeoutMillis) {
		CharSequence charSequence = new TimeoutRegexCharSequence(stringToMatch, timeoutMillis, stringToMatch,
				regularExpressionPattern.pattern());
		return regularExpressionPattern.matcher(charSequence);
	}

	private static class TimeoutRegexCharSequence implements CharSequence {
		private final CharSequence inner;
		private final int timeoutMillis;
		private final long timeoutTime;
		private final String stringToMatch;
		private final String regularExpression;

		public TimeoutRegexCharSequence(CharSequence inner, int timeoutMillis, String stringToMatch,
				String regularExpression) {
			super();
			this.inner = inner;
			this.timeoutMillis = timeoutMillis;
			this.stringToMatch = stringToMatch;
			this.regularExpression = regularExpression;
			timeoutTime = System.currentTimeMillis() + timeoutMillis;
		}

		public char charAt(int index) {
			if (System.currentTimeMillis() > timeoutTime) {
				throw new RuntimeException("Timeout occurred after " + timeoutMillis + "!!!!!!!!!!!");
			}
			return inner.charAt(index);
		}

		public int length() {
			return inner.length();
		}

		public CharSequence subSequence(int start, int end) {
			return new TimeoutRegexCharSequence(inner.subSequence(start, end), timeoutMillis, stringToMatch,
					regularExpression);
		}

		@Override
		public String toString() {
			return inner.toString();
		}
	}

}