package pattern.finder.com.example.regexchecker.dto;

public class RegexRequest {
	private String regex;
	private String textBody;

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getTextBody() {
		return textBody;
	}

	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	public RegexRequest() {
		super();
	}

	public RegexRequest(String regex, String textBody) {
		super();
		this.regex = regex;
		this.textBody = textBody;
	}

}
