package RouteController;

import java.util.Map;

import HttpHeader.HttpMethod;

public interface Routable {
    public Content run(Map<String, String> params, HttpMethod method);
    public boolean isAccessable(HttpMethod method);
}
