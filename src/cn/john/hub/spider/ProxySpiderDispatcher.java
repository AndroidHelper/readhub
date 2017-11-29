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
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.john.hub.domain.Proxy;
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

	private static Logger log = LogManager.getLogger("spider");
	
	private ExecutorService cacheThreadPool;
	private HashMap<Integer, AbstractProxySpider> proxyMap;

	private ProxyPool proxyPool;

	public ProxySpiderDispatcher() {
		cacheThreadPool = Executors.newCachedThreadPool();
		proxyMap = new HashMap<Integer, AbstractProxySpider>();
		proxyPool = ProxyPool.getInstance();
		init();
	}

	// 注册已有的代理爬虫以供选择
	private void init() {

		XiCiProxySpider xcps = new XiCiProxySpider();
		DoubleSixProxySpider dsps = new DoubleSixProxySpider();

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

		while (proxyPool.size() < 20) {
			startProxySpider();
		}

		proxyPool.signalSufficient();
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

		try {
			List<Proxy> proxyList = cacheThreadPool.submit(proxySpider).get();
			if (proxyList != null && proxyList.size() > 0) {
				proxyPool.putAll(proxyList);
			}
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error getting proxy...", e);
		}

	}
}
