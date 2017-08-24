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
package cn.john.hub.spider.news;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.spider.AbstractNewsSpider;
import cn.john.hub.util.SiteEnum;

/**
 * 
 * @ClassName: TechWebSpider
 * 
 * @Description: 从ProxyQueue中拿到proxy,然后发起http请求获取html，解析
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午7:32:41
 * 
 * 
 */
public class TechWebSpider extends AbstractNewsSpider{

	private static final int serialNumber = 0;

	private static final int delayFactor = 20;

	private static final String site = SiteEnum.TECH_WEB.siteAddr;

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
	protected void parseHtml(String html) {
		log.info("Parsing techweb news...");
		Document doc = Jsoup.parse(html);
		Elements news = doc.getElementsByClass("con_list");
		Elements news1 = news.last().getElementsByClass("con_one");
		Iterator<Element> it = news1.iterator();
		while (it.hasNext()) {
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

			dataList.add(newsDO);
		}
		log.info("Parse completed!size is " + dataList.size());
	}

	@Override
	public String toString() {
		return "TechWebSpider";
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: getFactor
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.spider.AbstractNewsSpider#getFactor()
	 * 
	 */
	@Override
	public int getDelayFactor() {
		// TODO Auto-generated method stub
		return delayFactor;
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: getSerialNumber
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.spider.AbstractNewsSpider#getSerialNumber()
	 * 
	 */
	@Override
	public int getSerialNumber() {
		// TODO Auto-generated method stub
		return serialNumber;
	}

	/* (non Javadoc)
	
	 * @Title: site
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractSpider#site()
	
	 */
	@Override
	protected String site() {
		// TODO Auto-generated method stub
		return site;
	}

}
