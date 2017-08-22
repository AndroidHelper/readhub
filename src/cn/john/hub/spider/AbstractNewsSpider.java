/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: NewsSpider.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午7:23:46

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HttpClient;

/**
 * 
 * @ClassName: NewsSpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午7:23:46
 * 
 * 
 */
public abstract class AbstractNewsSpider extends AbstractSpider<NewsDO> {

	private static LinkedBlockingQueue<List<NewsDO>> newsQueue;

	public AbstractNewsSpider() {
		newsQueue = Queue.newsQueue;
	}

	@Override
	public void run() {
		super.run();
	}

	protected void putDataToQueue(List<NewsDO> newsList) {
		try {
			newsQueue.put(dataList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void initHttpClient() {
		Proxy proxy = null;
		try {
			proxy = proxyQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		httpClient = new HttpClient(proxy);

	}

	// 用于调度
	protected abstract int getDelayFactor();

	protected abstract int getSerialNumber();

}
