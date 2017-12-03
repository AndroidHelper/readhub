/**  

 * @Title: SinaSpider.java

 * @Package: cn.john.hub.spider.news

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月23日 下午1:11:14

 * @version: V1.0  

 */
package cn.john.hub.spider.news;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.spider.AbstractNewsSpider;

/**

 * @ClassName: SinaSpider

 * @Description: TODO

 * @author: John

 * @date: 2017年8月23日 下午1:11:14


 */
public class SinaSpiderAlpha extends AbstractNewsSpider{
	
	private static final int newsSpiderId = 4;

	private static final int delayFactor = 1;

	private static final String site = "http://tech.sina.com.cn/";

	/* (non Javadoc)
	
	 * @Title: getDelayFactor
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractNewsSpider#getDelayFactor()
	
	 */
	@Override
	public int getDelayFactor() {
		return delayFactor;
	}

	/* (non Javadoc)
	
	 * @Title: getSerialNumber
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractNewsSpider#getSerialNumber()
	
	 */
	@Override
	public int getSpiderId() {
		return newsSpiderId;
	}

	/* (non Javadoc)
	
	 * @Title: site
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractSpider#site()
	
	 */
	@Override
	protected String site() {
		return site;
	}

	/* (non Javadoc)
	
	 * @Title: parseHtml
	
	 * @Description: TODO
	
	 * @param html
	
	 * @see cn.john.hub.spider.AbstractSpider#parseHtml(java.lang.String)
	
	 */
	@Override
	protected List<NewsDO> parseHtml(String html) {
		log.info("Parse sina html...");
		Document doc = Jsoup.parse(html);
		Elements divs = doc.getElementsByClass("feed-card-item");
		List<NewsDO> newsList = new LinkedList<NewsDO>();
		Iterator<Element> divIt = divs.iterator();
		while(divIt.hasNext()){
			Element e = divIt.next();
			NewsDO news = new NewsDO();
			Element aTag = e.child(0).child(0);
			news.setTitle(aTag.text());
			news.setUrl(aTag.attr("href"));
			String brief = e.child(1).child(1).child(0).text();
			news.setBrief(brief);
			news.setSiteId(4);
			newsList.add(news);
		}
		log.info("Parse sina news completed!size is " + newsList.size());
		return newsList;
	}
	
}
