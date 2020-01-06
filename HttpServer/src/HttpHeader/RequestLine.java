package HttpHeader;

import java.util.HashMap;
import java.util.Map;

import Exceptions.IllegalHeader;

public class RequestLine {
	
	public static class Request {
		private HttpMethod method;
		private String resource;
		private String version;
		private Map<String, String> params;
		private Request() {
			params = new HashMap<String, String> ();
		}
		public HttpMethod getMethod() {
			return method;
		}
		public void setMethod(HttpMethod method) {
			this.method = method;
		}
		public String getResource() {
			return resource;
		}
		public void setResource(String resource) {
			this.resource = resource;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		
	}
	
	public static Request getRequest(String request) throws IllegalHeader {
		String[] stage = request.split(" ");
		if (stage.length != 3) 
			throw new IllegalHeader("Illegal Request Line.");
	    Request req = new Request();
	    req.setMethod(getMethod(stage[0]));
	    req.setResource(getResource(stage[1], req));
	    req.setVersion(stage[2]);
		return req;
	}
	
	
    private static HttpMethod getMethod(String request){
    	return HttpMethod.valueOf(request);
    }
    
    public static String getResource(String request, Request req) throws IllegalHeader {
    	if (request.contains("?")) {
    		int resPart = request.indexOf("?");
    		String params = request.substring(resPart);
    		String res = request.substring(0, resPart);
    		return null;
    	}
    	else 
    		return request;
    }
    
}
