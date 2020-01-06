package Example;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import HttpHeader.HttpMethod;
import RouteController.Content;
import RouteController.Routable;

public class Index implements Routable {

	@Override
	public Content run(Map<String, String> params, HttpMethod method) {
		Content content = new Content();
		try {
			content.setContent("<h1>Hello World!</h1>".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {}
		content.setType("text/html");
		return content;
	}

	/**
	 * 如果当前路径不接收某种方法，则返回false，否则为true（未实现）
	 */
	@Override
	public boolean isAccessable(HttpMethod method) {
		// TODO Auto-generated method stub
		return true;
	}

}
