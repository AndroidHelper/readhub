/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: TechWebSpider.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午7:32:41

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.Proxy;
import cn.john.hub.service.NewsService;
import cn.john.hub.service.SiteService;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.SiteEnum;

/**
 * 
 * @ClassName: TechWebSpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午7:32:41
 * 
 * 
 */
@Component
public class TechWebSpider extends NewsSpider implements Runnable {
	
	
	public TechWebSpider(){
		super();
	}
	
	@Autowired
	public void a(NewsService nService){
		super.nService = nService;
	}
	@Autowired
	public void b(ProxySpider pSpider){
		super.pSpider = pSpider;
	}
	@Autowired
	public void c(SiteService sService){
		super.sService = sService;
	}
	
	/*
	 * (non Javadoc)
	 * 
	 * @Title: crawlNews
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.spider.NewsSpider#crawlNews()
	 * 
	 */
	@Override
	public List<NewsDO> crawlNews() {
		log.info("Getting html for "+SiteEnum.TECH_WEB.siteName);
		String html = httpClient.getData(SiteEnum.TECH_WEB.siteAddr);
		log.info("Parsing...");
		Document doc = Jsoup.parse(html);
		Elements news = doc.getElementsByClass("con_list");
		Elements news1 = news.last().getElementsByClass("con_one");
		Iterator<Element> it = news1.iterator();
		List<NewsDO> newsList = new ArrayList<NewsDO>();
		while(it.hasNext()){
			Element e = it.next();
			Element h2 = e.getElementsByTag("h2").last();
			Element a = h2.getElementsByTag("a").last();
			String url = a.attr("href");
			String title = a.html();
			NewsDO newsDO = new NewsDO();
			newsDO.setTitle(title);
			newsDO.setUrl(url);
			
			Elements txt = e.getElementsByClass("con_txt");
			String brief = txt.last().getElementsByTag("p").html();
			newsDO.setBrief(brief);
			newsDO.setSiteId(1);
			
			newsList.add(newsDO);
		}
		log.info("Parse completed!size is "+newsList.size());
		return newsList;
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: run
	 * 
	 * @Description: TODO
	 * 
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 */
	@Override
	public void run() {
		Proxy proxy = null;
		Proxy proxy1 = null;
		try {
			proxy = pSpider.proxyQueue.take();
			proxy1 = pSpider.proxyQueue.take();
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		}
		httpClient = new HttpClient(proxy1.getIpAddr(), Integer.parseInt(proxy1.getPort()));
		nService.saveNews(crawlNews());
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: init
	 * 
	 * @Description: TODO
	 * 
	 * 
	 * @see cn.john.hub.spider.NewsSpider#init()
	 * 
	 */
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

}
