/**
 * Status Code用来描述状态码
 */
package HttpHeader;

public enum StatusCode {
	Code100("Continue"), 
	Code101("Switching Protocols"), 
	
	Code200("OK"),
	Code201("Created"),
	Code202("Accepted"),
	Code203("Non-Authoritative Information"),
	Code204("No Content"),
	Code205("Reset Content"),
	Code206("Partial Content"),
	
	Code300("Multiple Choices"),
	Code301("Moved Permanently"),
	Code302("Found"),
	Code303("See Other"),
	Code304("Not Modified"),
	Code305("Use Proxy"),
	Code306("Unused"),
	Code307("Temporary Redirect"),
	
	Code400("Bad Request"),
	Code401("Unauthorized"),
	Code402("Payment Required"),
	Code403("Forbidden"),
	Code404("Not Found")
	; 
	
	private String code;
	private StatusCode(String code) {
		this.code = code;
	}
	
	public String toString() {
		return code;
	}
}
