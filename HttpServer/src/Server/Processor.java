/**
 * 用于处理每个响应
 */
package Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;

import Exceptions.IllegalHeader;
import HttpHeader.HttpMethod;
import HttpHeader.Request;
import HttpHeader.Respond;
import HttpHeader.StatusCode;
import RouteController.Content;
import RouteController.Controller;
import RouteController.Routable;

public class Processor implements Callable<String> {
    private SocketChannel sChannel;
    private Selector sel;
    private Request header;
    private boolean ready;
    
	public Processor(SocketChannel sChannel, Selector sel) {
		ready = true;
		header = new Request();
		this.sChannel = sChannel;
		this.sel = sel;
		try {
			readHeader();
		} catch (IllegalHeader e) {
			ready = false;
		}
		catch (IOException e) {
			ready = false;
		}
	}
	/**
	 * 请求头是否合法
	 * @return 若合法则为true
	 */
	public boolean isReady() {
		return ready;
	}
	
	/**
	 * 读取请求
	 * @throws IOException
	 * @throws IllegalHeader
	 */
	public void readHeader() throws IOException, IllegalHeader {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int len;
		while ((len = sChannel.read(buf)) > 0) {
			buf.flip();    				
			header.attach(buf.array(), len);
			buf.clear();
		}
		header.getHeader();
	}
	
	/**
	 * 目前仍存在问题，写回文件大小不得超过1024字节
	 */
	@Override
	public String call() throws Exception {
		Content content = null;
		Respond res;
		// 处理请求头
		HttpMethod method = header.getMethod();
		String resource = header.getResource();
		Controller routeMap = Controller.getController();
		
		//获取响应
		Routable function = routeMap.getRoute(resource);
		if (function != null) {
		    content = function.run(null, method);
		    res = content.generateRespond();
		}
		else {
			res = new Respond();
			res.setStatus("404");
			res.setNote(StatusCode.Code404);
		}
		
		//生成HTTP报文，并写回
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put(res.toString().getBytes());
		buf.put(content.getContent());
		buf.flip();
		sChannel.write(buf);
//		try {
//		    writeBack(content, sChannel);
//		} catch(IOException e) {}
      
		sChannel.close();
		return null;
	}
	
	public void writeBack(Content content, SocketChannel sChannel) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		byte[] cont = content.getContent();
		int index = 0;
		while (index < cont.length) {
			int end = index + 1024 < cont.length ? index + 1024: cont.length - 1;
			buf.put(cont, index, end - index);
			buf.flip();
			sChannel.write(buf);
			index += 1024;
			buf.clear();
		}
		return;
		
	}

}
