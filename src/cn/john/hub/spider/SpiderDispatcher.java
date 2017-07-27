/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: SpiderController.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午11:15:17

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	@PostConstruct
	private void startDeamon() {
		log.info("Spider controller is constructed,starting proxy spider and news spider dispatchers...");
		cacheThreadPool.execute(new ProxySpiderDispatcher());
		cacheThreadPool.execute(new NewsSpiderDispatcher());
	}
}
