/**  

 * @Title: ProxyController.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午10:27:18

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.john.hub.domain.Heartbeat;
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
@Component
public class ProxySpiderDispatcher implements Runnable {

	private static Logger log = LogManager.getLogger("logger");
	private ExecutorService cacheThreadPool;
	private HashMap<Integer, AbstractProxySpider> proxyMap;
	private AtomicBoolean crawlingFlag;

	@Autowired
	private Heartbeat hb;

	public ProxySpiderDispatcher() {
		cacheThreadPool = Executors.newCachedThreadPool();
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
	 * @Description: 10秒执行一次
	 * 
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 */
	@Override
	public void run() {
		long timestamp = System.currentTimeMillis();
		hb.setProxySpiderBeat(timestamp);
		hb.setProxySpiderPoolInfo(cacheThreadPool.toString());
		synchronized (crawlingFlag) {
			if (isLackOfProxy()) {
				startProxySpider();
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
		Random rand = new Random();
		int randInt = rand.nextInt(10);
		int randKey = 0;
		if (randInt <= 7) {
			randKey = 1;
		}
		AbstractProxySpider proxySpider = proxyMap.get(randKey);
		log.info("Proxy spider get!Detail: " + proxySpider);
		// 启动
		cacheThreadPool.execute(proxySpider);
		try {
			log.info("A proxy spider has been launched! Waiting for it to finish crawling...");
			crawlingFlag.wait();
			log.info("proxy spider has done it's work!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
