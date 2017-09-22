/**  
 * @Title: AbstractProxySpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午9:56:22

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

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
public abstract class AbstractProxySpider extends AbstractSpider<Proxy>{
	
	protected Random rand;

	protected AtomicBoolean crawlingFlag;
	
	protected AbstractProxySpider() {
		rand = new Random();
	}

	@Override
	public void run() {
		log.info("Start fetching proxy from "+site());
		synchronized(crawlingFlag){
			log.debug("execute super class run...");
			super.run();
			log.debug("finished!notify..");
			crawlingFlag.notify();
		}
	}
	
	protected void initHttpClient() {
		Proxy proxy = null;
		proxy = proxyQueue.poll();
		if (proxy != null) {
			log.info("Fetching proxys using proxy ip...");
			httpClient = new HttpClient(proxy);
			log.debug("httpclient init finished: "+httpClient);
		} else {
			log.info("Fetching proxys using local ip address...");
			httpClient = new HttpClient();
			// 直接使用本机ip，如果本机ip访问出错，避免本机ip迅速访问被封
			try {
				TimeUnit.SECONDS.sleep(rand.nextInt(60));
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
		}
	}

	protected void putDataToQueue() {
		Iterator<Proxy> it = dataList.iterator();
		while (it.hasNext()) {
			try {
				proxyQueue.put(it.next());
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
		}
		log.info("Put "+dataList.size()+" proxys into proxy queue!And now proxy queue size is "+proxyQueue.size());
		dataList.clear();
	}

}
