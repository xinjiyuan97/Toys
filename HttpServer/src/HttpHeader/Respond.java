package HttpHeader;

import java.util.LinkedHashMap;
import java.util.Map;

import Server.Constant;
public class Respond {
	private String version = Constant.version;
	private String status;
	

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNote(StatusCode note) {
		this.note = note.toString();
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	private String note;
	private Map<String, String> params;
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(version).append(" " + status).append(" " + note + "\r\n");
    	if (params != null)
	    	for (String key: params.keySet()) 
	    		sb.append(key + ": " + params.get(key) + "\r\n");
    	return sb.toString() + "\r\n";
    }
}
