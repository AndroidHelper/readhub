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
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.spider.proxy.DoubleSixProxySpider;
import cn.john.hub.spider.proxy.XiCiProxySpider;

/**
 * 
 * @ClassName: ProxyController
 * 
 * @Description: 代理爬虫调度器
 * 
 * @author: John
 * 
 * @date: 2017年7月24日 上午10:27:18
 * 
 * 
 */
public class ProxySpiderDispatcher implements Runnable {

	private static Logger log = LogManager.getLogger("logger");
	private HashMap<Integer, AbstractProxySpider> proxyMap;

	private AtomicBoolean crawlingFlag;

	public ProxySpiderDispatcher() {
		crawlingFlag = new AtomicBoolean(false);
		proxyMap = new HashMap<Integer, AbstractProxySpider>();
		init();
	}

	// 注册已有的代理爬虫以供选择
	private void init() {

		XiCiProxySpider xcps = new XiCiProxySpider(crawlingFlag);
		DoubleSixProxySpider dsps = new DoubleSixProxySpider(crawlingFlag);

		proxyMap.put(XiCiProxySpider.spiderNumber, xcps);
		proxyMap.put(DoubleSixProxySpider.spiderNumber, dsps);
		log.info("proxy map initialized!Detail: " + proxyMap);
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
		log.info("Proxy dispatcher is started!");
		while (true) {
			// 最大化同步块，以节约CPU
			synchronized (crawlingFlag) {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (isLackOfProxy()) {
					startProxySpider();
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

	// 启动代理爬虫
	private void startProxySpider() {
		// 随机选择代理爬虫
		Integer[] keys = proxyMap.keySet().toArray(new Integer[0]);
		Random rand = new Random();
		int randKey = keys[rand.nextInt(keys.length)];
		AbstractProxySpider proxySpider = proxyMap.get(randKey);
		log.info("Proxy spider get!Detail: " + proxySpider);
		// 启动
		SpiderDispatcher.cacheThreadPool.execute(proxySpider);
		try {
			log.info("A proxy spider has been launched! Waiting for it to finish crawling...");
			crawlingFlag.wait();
			log.info("proxy spider has done it's work!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}