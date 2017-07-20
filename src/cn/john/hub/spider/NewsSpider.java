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
public abstract class NewsSpider {
	
	protected Logger log = LogManager.getLogger("logger");
	//用于存储数据
	protected NewsService nService;
	//用于获取代理
	protected ProxySpider pSpider;
	//用于获取需要扫描的
	protected List<SiteDO> siteList;

	protected HttpClient httpClient;

	public NewsSpider() {
		init();
	}
	
	
	protected abstract void init();

	protected abstract String getNews(SiteEnum site);
	protected abstract List<NewsDO> parseNews(String html);

	public boolean saveNews(List<NewsDO> newsList) {
		return nService.saveNews(newsList);
	}

	public NewsService getnService() {
		return nService;
	}

	public void setnService(NewsService nService) {
		this.nService = nService;
	}

	public ProxySpider getpSpider() {
		return pSpider;
	}

	public void setpSpider(ProxySpider pSpider) {
		this.pSpider = pSpider;
	}

}
