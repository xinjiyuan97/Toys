# HttpServer

## 介绍
一个简易的Http服务器，功能仍不完善，功能结构仿照Flask。

## 安装
无需安装，在项目中import相关类即可。

## 使用
### 注册
```Java
Server entrance = new Server(hostname, port);
entrance.start();
```

### 注册路由
```Java
Controller controller = Controller.getController();
controller.register("/", new Index());
```

其中`Controller`是服务器中的路由管理组件，所有请求经路由转发获得最终的结果。

需要通过`Controller.register(url, routable)`来注册路由路径。其中`url`为路由路径，`routable`为一个`routable`类，用以处理路由请求。
