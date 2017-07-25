/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: ProxyController.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午10:27:18

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @ClassName: ProxyController
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月24日 上午10:27:18
 * 
 * 
 */
public class ProxyDispatcher implements Runnable {

	private static Logger log = LogManager.getLogger("logger");
	private HashMap<Integer, AbstractProxySpider> proxyMap;
	public static volatile boolean fetchingFlag;

	public ProxyDispatcher() {
		fetchingFlag = false;
		proxyMap = new HashMap<Integer, AbstractProxySpider>();
		init();
	}

	private void init() {
		try {
			proxyMap.put(XiCiProxySpider.spiderNumber, XiCiProxySpider.class.newInstance());
			log.info("proxy map initialized!Detail: " + proxyMap);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: run
	 * 
	 * @Description: TODO
	 * 
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (!fetchingFlag && isLackOfProxy()) {
				startProxySpider();
				try {
					//给出时间让线程启动修改fetchingFlag
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private boolean isLackOfProxy() {
		if (Queue.proxyQueue.size() < 20) {
			log.warn("lack of proxy!");
			return true;
		}
		return false;
	}

	private void startProxySpider() {
		Integer[] keys = proxyMap.keySet().toArray(new Integer[0]);
		Random rand = new Random();
		int randKey = keys[rand.nextInt(keys.length)];
		AbstractProxySpider proxySpider = proxyMap.get(randKey);
		log.info("Proxy spider get!Detail: " + proxySpider);
		SpiderDispatcher.cacheThreadPool.execute(proxySpider);
	}

}
