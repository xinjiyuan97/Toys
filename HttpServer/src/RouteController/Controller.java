/**
 * {@author xinjiyuan}
 * 用于创建和维护路由表
 */
package RouteController;

import java.util.HashMap;
import java.util.Map;

import Exceptions.DuplicateRoute;

public class Controller {
    
    private static final Controller controller = new Controller();
    private Map<String, Routable> routeMap;
    
    private Controller() {
    	routeMap = new HashMap<String, Routable>();
    }
    
    public void register(String route, Routable function) throws DuplicateRoute {
    	if (routeMap.containsKey(route))
    		throw new DuplicateRoute(route + " is already exists.");
    	routeMap.put(route, function);
    }
    
    public Routable getRoute(String route) {
    	return routeMap.get(route);
    }
    
    public static Controller getController() {
    	return controller;
    }
}
