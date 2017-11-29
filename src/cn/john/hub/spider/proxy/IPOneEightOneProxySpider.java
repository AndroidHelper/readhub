/**  

 * @Title: IPOneEightOneProxySpider.java

 * @Package: cn.john.hub.spider.proxy

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月28日 下午5:02:05

 * @version: V1.0  

 */
package cn.john.hub.spider.proxy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.ParseException;
import cn.john.hub.domain.Proxy;
import cn.john.hub.spider.AbstractProxySpider;

/**
 * 
 * @ClassName: IPOneEightOneProxySpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月28日 下午5:02:05
 * 
 * 
 */
public class IPOneEightOneProxySpider extends AbstractProxySpider {

	public static final int spiderNumber = 2;

	/*
	 * (non Javadoc)
	 * 
	 * @Title: site
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.spider.AbstractSpider#site()
	 * 
	 */
	@Override
	protected String site() {
		// TODO Auto-generated method stub
		return "http://www.ip181.com/";
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: parseHtml
	 * 
	 * @Description: TODO
	 * 
	 * @param html
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 * 
	 * @see cn.john.hub.spider.AbstractSpider#parseHtml(java.lang.String)
	 * 
	 */
	@Override
	protected List<Proxy> parseHtml(String html) throws ParseException {
		log.info("Parsing proxy html...");
		try {
			Document doc = Jsoup.parse(html);
			Element table = doc.getElementsByTag("tbody").get(0);
			Elements trList = table.getElementsByTag("tr");
			trList.remove(0);
			List<Proxy> proxyList = new LinkedList<Proxy>();
			Iterator<Element> it = trList.iterator();
			while (it.hasNext()) {
				Proxy proxy = new Proxy();
				Element tr = it.next();
				Elements tdList = tr.getElementsByTag("td");
				if(!tdList.get(2).text().trim().equals("透明")){
					proxy.setIpAddr(tdList.get(0).text());
					proxy.setPort(tdList.get(1).text());					
					proxyList.add(proxy);
				}
			}
			log.info("parse proxy html completed!");
			return proxyList;
		} catch (Exception e) {
			log.error("Parse 66ip html failed!", e);
			return null;
		}
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: toString
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 */
	@Override
	public String toString() {
		return "IPOneEightOneProxySpider";
	}

}
