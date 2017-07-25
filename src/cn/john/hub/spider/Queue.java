/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: Queue.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午9:52:37

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import cn.john.hub.domain.Proxy;

/**
 * 
 * @ClassName: Queue
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月24日 上午9:52:37
 * 
 * 
 */
public class Queue {
	
	public static LinkedBlockingQueue<Proxy> proxyQueue = new LinkedBlockingQueue<Proxy>();
	public static LinkedBlockingQueue<Object> newsQueue = new LinkedBlockingQueue<Object>();
	
	@PostConstruct
	private void init() {
		
	}
}
