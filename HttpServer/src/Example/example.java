package Example;

import java.io.IOException;

import Exceptions.DuplicateRoute;
import RouteController.Controller;
import Server.Server;

public class example {
	/**
	 *  注册路由
	 */
	public static void registerRouteMap() throws DuplicateRoute {
		Controller controller = Controller.getController();
    	controller.register("/", new Index());
	}
	
    public static void main(String[] args) {
    	
    	Server entrance = new Server();
		
    	try {
    		registerRouteMap();
			entrance.start();
		} 
    	catch (IOException e) {} 
    	catch (DuplicateRoute e) {} 
		finally {
			entrance.close();
		}
    }
}
