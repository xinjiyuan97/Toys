package RouteController;

import java.util.*;

import HttpHeader.Respond;
import HttpHeader.StatusCode;

public class Content {
	private byte[] content;
	private String type;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public byte[] getContent() {
		return content;
	}
    
	public Respond generateRespond() {
		Respond res = new Respond();
		res.setStatus("200");
		res.setNote(StatusCode.Code200);
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("Date", new Date().toString());
		params.put("Content-Length", String.valueOf(content.length));
		params.put("Content-Type", type);
		params.put("Content-Language", "en-US");
		params.put("Content-Encoding", "UTF-8");
		res.setParams(params);
		return res;
	}
}
