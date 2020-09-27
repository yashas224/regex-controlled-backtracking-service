package pattern.finder.com.example.regexchecker.dto;

public class RegexResponse {
	private String match;
	private boolean error;

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public RegexResponse(String match, boolean error) {
		super();
		this.match = match;
		this.error = error;
	}

	public RegexResponse() {
		super();
	}

}
