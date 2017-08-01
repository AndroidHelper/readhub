/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: AbstractSpider.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月1日 上午9:40:19

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HttpClient;

/**
 * 
 * @ClassName: AbstractSpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * @param <T>
 * 
 * @date: 2017年8月1日 上午9:40:19
 * 
 * 
 */
public abstract class AbstractSpider<T> implements Runnable {

	protected final Logger log = LogManager.getLogger("logger");
	protected HttpClient httpClient;
	protected LinkedBlockingQueue<Proxy> proxyQueue = Queue.proxyQueue;
	protected List<T> dataList = new ArrayList<T>();

	protected AbstractSpider() {
	}

	protected abstract void initHttpClient();

	protected abstract String site();

	protected String getHtml(String site){
		String s = null;
		while(s == null){
			initHttpClient();
			s = httpClient.getData(site);
		}
		
		Proxy proxy = httpClient.getProxy();
		//有可能是本机ip
		if(proxy!=null){
			try {
				proxyQueue.put(proxy);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}

	protected abstract void parseHtml(String html);

	protected abstract void putDataToQueue(List<T> list);

	@Override
	public void run() {
		parseHtml(getHtml(site()));
		putDataToQueue(dataList);
	}
}
