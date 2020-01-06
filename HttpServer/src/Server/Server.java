package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import HttpHeader.Request;

public class Server {
    private Selector sel = null;
    private ServerSocketChannel ssChannel = null;
    private int port = 8080;
    private String hostname;
    private ExecutorService threadPool;
    
    public Server(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
		threadPool = Executors.newCachedThreadPool();
    }
    
    public Server() {
    	this("127.0.0.1", 8080);
    }
    
    public void process() throws IOException {
    	Iterator<SelectionKey> it = sel.selectedKeys().iterator();
    	while (it.hasNext()) {
    		SelectionKey key = it.next();
    		if (key.isAcceptable()) {
    			SocketChannel sChannel = ssChannel.accept();
    			sChannel.configureBlocking(false);
    			sChannel.register(sel, SelectionKey.OP_READ);
    		}
    		else if (key.isReadable()) {
    			SocketChannel sChannel = (SocketChannel) key.channel();
    			Processor pro = new Processor(sChannel, sel);
    			if (pro.isReady())
    			    threadPool.submit(pro);
    			key.interestOps(key.interestOps() & ~SelectionKey.OP_READ);
    		}
    		it.remove();
    	}
    }

    
    public void start() throws IOException {
    	Date date = new Date();
    	System.out.println("Server start at " + date.toString());
    	System.out.println("Working at " + hostname + ":" + port);
    	ssChannel = ServerSocketChannel.open();
    	ssChannel.configureBlocking(false);
    	ssChannel.bind(new InetSocketAddress(hostname, port));
		sel = Selector.open();
		ssChannel.register(sel, SelectionKey.OP_ACCEPT);
		
		while (true) {
			if (sel.select() > 0) 
				process();
		}
    }
    
    public void close() {
    	if (sel != null) 
    		try {
				sel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	if (ssChannel != null)
			try {
				ssChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
