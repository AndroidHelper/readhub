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
import org.springframework.beans.factory.annotation.Autowired;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.SiteDO;
import cn.john.hub.service.NewsService;
import cn.john.hub.service.SiteService;
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
public abstract class AbstractNewsSpider implements Runnable{
	
	protected Logger log = LogManager.getLogger("logger");
	protected int delayFactor;
	protected HttpClient httpClient;

	public AbstractNewsSpider() {
		init();
	}
	
	protected void init() {
	}

	protected abstract String getNews(SiteEnum site);
	
	protected abstract List<NewsDO> parseNews(String html);

}
