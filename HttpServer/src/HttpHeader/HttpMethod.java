package HttpHeader;

public enum HttpMethod {
    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), 
    HEAD("HEAD"), TRACE("TRACE"), CONNECT("CONNECT"), OPTIONS("OPTIONS");
	private String method;
	
	private HttpMethod(String method) {
		this.method = method;
	}
}
