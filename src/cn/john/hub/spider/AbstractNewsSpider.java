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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.SiteEnum;

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
public abstract class AbstractNewsSpider implements Runnable {

	protected Logger log = LogManager.getLogger("logger");

	protected HttpClient httpClient;

	public AbstractNewsSpider() {
		init();
	}

	protected void init() {
	}

	protected abstract int getDelayFactor();

	protected abstract int getSerialNumber();

	protected abstract String getNews(SiteEnum site);

	protected abstract void parseNews(String html);
	
	protected abstract void offerProxyToQueue(Proxy proxy);

}
