/**  
 * @Title: SpiderController.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午11:15:17

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: SpiderController
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月24日 上午11:15:17
 * 
 * 
 */
@Component
public class SpiderDispatcher {
	private static Logger log = LogManager.getLogger("logger");
	public static ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
	
	@Autowired
	private NewsSaver ns;
	@Autowired
	private NewsSpiderDispatcher nsd;
	@Autowired
	private ProxySpiderDispatcher psd;

	@PostConstruct
	private void startDeamon() {

		ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(3);
		// 负责维护代理ip
		stpe.scheduleWithFixedDelay(psd, 0, 10, TimeUnit.SECONDS);
		// 负责爬取新闻信息
		stpe.scheduleWithFixedDelay(nsd, 30, 10, TimeUnit.SECONDS);
		// 负责存储数据
		stpe.scheduleWithFixedDelay(ns, 180, 60, TimeUnit.SECONDS);

		log.info("Spider controller is constructed!proxy spider,news spider and news saver are started!");
	}
}
