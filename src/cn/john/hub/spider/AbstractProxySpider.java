/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: AbstractProxySpider.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午9:56:22

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HttpClient;

/**
 * 
 * @ClassName: AbstractProxySpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月24日 上午9:56:22
 * 
 * 
 */
public abstract class AbstractProxySpider implements Runnable {
	
	protected static Logger log = LogManager.getLogger("logger");
	private static LinkedBlockingQueue<Proxy> proxyQueue;
	private HttpClient httpClient;
	protected Random rand;
	protected List<Proxy> proxyList;

	protected AbstractProxySpider() {
		proxyQueue = Queue.proxyQueue;
		rand = new Random();
		proxyList =  Collections.synchronizedList(new ArrayList<Proxy>());
	}

	protected abstract void parseHtml(String html);

	protected abstract String getProxySite();
		
	@Override
	public void run() {
		log.info("Start fetching proxy from "+getProxySite());
		ProxySpiderDispatcher.fetchingFlag = true;
		//获取
		parseHtml(getHtml(getProxySite()));
		//入队
		offerProxyToQueue(proxyList);
		ProxySpiderDispatcher.fetchingFlag = false;
	}


	private String getHtml(String proxySite) {
		String s = null;
		while (s == null) {
			initHttpClient();
			s = httpClient.getData(proxySite);
		}
		return s;
	}
	
	private void initHttpClient() {
		Proxy proxy = null;
		proxy = proxyQueue.poll();
		if (proxy != null) {
			httpClient = new HttpClient(proxy.getIpAddr(), Integer.parseInt(proxy.getPort()));
		} else {
			httpClient = new HttpClient();
			// 直接使用本机ip，如果本机ip访问出错，避免本机ip迅速访问被封
			try {
				TimeUnit.SECONDS.sleep(rand.nextInt(60));
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
		}
	}

	private void offerProxyToQueue(List<Proxy> proxyList) {
		Iterator<Proxy> it = proxyList.iterator();
		while (it.hasNext()) {
			try {
				proxyQueue.put(it.next());
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
		}
		log.info("Put "+proxyList.size()+" proxys into proxy queue!And now proxy queue size is "+proxyQueue.size());
	}

}
