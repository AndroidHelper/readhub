/**  

 * @Title: TaiMediaSpider.java

 * @Package: cn.john.hub.spider.news

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月2日 下午6:51:03

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
 * 
 * @ClassName: TaiMediaSpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年8月2日 下午6:51:03
 * 
 * 
 */
public class TaiMediaSpider extends AbstractNewsSpider {

	private static final int serialNumber = 2;

	private static final int delayFactor = 15;

	private static final String site = "http://www.tmtpost.com/column/2581216";

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
		return "TaiMediaSpider";
	}

	/* (non Javadoc)
	
	 * @Title: parseHtml
	
	 * @Description: TODO
	
	 * @param html
	
	 * @see cn.john.hub.spider.AbstractSpider#parseHtml(java.lang.String)
	
	 */
	@Override
	protected List<NewsDO> parseHtml(String html) {
		log.info("Parsing taimedia news...");
		try {
			Document doc = Jsoup.parse(html, site);
			Elements lis = doc.getElementsByClass("mod-article-list clear").get(0).child(0).children();
			List<NewsDO> newsList = new LinkedList<NewsDO>();
			Iterator<Element> it = lis.iterator();
			while (it.hasNext()) {
				Element e = it.next();
				NewsDO news = new NewsDO();
				Element aTag = e.getElementsByTag("h3").get(0).child(0);
				String title = aTag.attr("title");
				String url = aTag.attr("abs:href");
				String brief = e.getElementsByTag("p").get(0).text();
				news.setBrief(brief);
				news.setUrl(url);
				news.setTitle(title);
				news.setSiteId(3);
				newsList.add(news);
			}
			log.info("Parsing taimedia news completed!");
			return newsList;
		} catch (Exception e) {
			log.error("Parse taimedia html failed!", e);
			return null;
		}
	}

}
