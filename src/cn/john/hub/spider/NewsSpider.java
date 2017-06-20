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
	protected NewsService nService;
	protected ProxySpider pSpider;
	protected SiteService sService;

	protected HttpClient httpClient;

	public NewsSpider() {
		init();
	}

	protected abstract void init();

	public abstract List<NewsDO> crawlNews();

	public boolean saveNews(List<NewsDO> newsList) {
		return nService.saveNews(newsList);
	};

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

	public SiteService getsService() {
		return sService;
	}

	public void setsService(SiteService sService) {
		this.sService = sService;
	}

}
