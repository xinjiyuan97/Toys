/**
 * 用于处理接入时的请求头
 */
package HttpHeader;

import java.util.HashMap;
import java.util.Map;

import Exceptions.IllegalHeader;

public class Request {
	private StringBuilder sb;
	private RequestLine.Request request;
	private Map<String, String> body;
	public Request() {
		body = new HashMap<String, String>();
		sb = new StringBuilder();
	}
	
	/**
	 * 逐段获取请求头
	 * @param bytes
	 * @param len
	 */
	public void attach(byte[] bytes, int len) {
		String slice = new String(bytes, 0, len);
		sb.append(slice);
	}
	
	/**
	 * 处理请求头
	 * @throws IllegalHeader
	 */
	public void getHeader() throws IllegalHeader {
		String header = sb.toString();
		int end = header.indexOf("\r\n\r\n");
		if (end != -1)
			header = header.substring(0, end);
		String[] elements = header.split("\r\n");
		request = RequestLine.getRequest(elements[0]);
		for (int i = 1; i < elements.length; i++) {
			if (!elements[i].contains(":")) 
				continue;
			String[] params = elements[i].split(": ");
			body.put(params[0], params[1]);
		}
	}
	
	public HttpMethod getMethod() {
		return request.getMethod();
	}
	
	public String getResource() {
		return request.getResource();
	}
}
