/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: CnBetaSpider.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider.news

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月2日 下午6:11:11

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

/**

 * @ClassName: CnBetaSpider

 * @Description: TODO

 * @author: John

 * @date: 2017年8月2日 下午6:11:11


 */
public class CnBetaSpider extends AbstractNewsSpider{
	
	private static final int serialNumber = 1;

	private static final int delayFactor = 1;

	private static final String site = "http://www.cnbeta.com/category/tech.htm";
	
	/* (non Javadoc)
	
	 * @Title: getDelayFactor
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractNewsSpider#getDelayFactor()
	
	 */
	@Override
	protected int getDelayFactor() {
		// TODO Auto-generated method stub
		return delayFactor;
	}

	/* (non Javadoc)
	
	 * @Title: getSerialNumber
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractNewsSpider#getSerialNumber()
	
	 */
	@Override
	protected int getSerialNumber() {
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
	
	
	
	@Override
	public String toString() {
		return "CnBetaSpider";
	}

	/* (non Javadoc)
	
	 * @Title: parseHtml
	
	 * @Description: TODO
	
	 * @param html
	
	 * @see cn.john.hub.spider.AbstractSpider#parseHtml(java.lang.String)
	
	 */
	@Override
	protected void parseHtml(String html) {
		log.info("Parsing cnbeta html...");
		Document doc = Jsoup.parse(html);
		Elements items = doc.getElementsByClass("items-area").get(0).children();
		Iterator<Element> it = items.iterator();
		while(it.hasNext()){
			Element e = it.next();
			NewsDO news = new NewsDO();
			Element aTag = e.getElementsByTag("dt").get(0).child(0);
			news.setTitle(aTag.text());
			news.setUrl(aTag.attr("href"));
			Element brief = e.getElementsByTag("dd").get(0);
			news.setBrief(brief.text());
			news.setSiteId(2);
			
			dataList.add(news);
		}
		log.info("Parse cnbeta news completed!size is " + dataList.size());
	}
}
