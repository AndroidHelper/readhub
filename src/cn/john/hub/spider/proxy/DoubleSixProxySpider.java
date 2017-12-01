/**  

 * @Title: DoubleSixProxySpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月28日 下午3:42:42

 * @version: V1.0  

 */
package cn.john.hub.spider.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.ParseException;
import cn.john.hub.domain.Proxy;
import cn.john.hub.spider.AbstractProxySpider;

/**
 * 
 * @ClassName: DoubleSixProxySpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月28日 下午3:42:42
 * 
 * 
 */
public class DoubleSixProxySpider extends AbstractProxySpider {

	private final int proxySpiderId = 1;

	private Random rand = new Random();

	/*
	 * (non Javadoc)
	 * 
	 * @Title: parseHtml
	 * 
	 * @Description: TODO
	 * 
	 * @param html
	 * 
	 * @see cn.john.hub.spider.AbstractProxySpider#parseHtml(java.lang.String)
	 * 
	 */
	@Override
	protected List<Proxy> parseHtml(String html) throws ParseException {
		log.info("Parsing  double six proxy html...");
		try {
			Document doc = Jsoup.parse(html);
			Element table = doc.getElementsByTag("table").get(2);
			Elements trList = table.getElementsByTag("tr");
			trList.remove(0);
			List<Proxy> proxyList = new LinkedList<Proxy>();
			Iterator<Element> it = trList.iterator();
			while (it.hasNext()) {
				Proxy proxy = new Proxy();
				Element tr = it.next();
				Elements tdList = tr.getElementsByTag("td");
				proxy.setIpAddr(tdList.get(0).text());
				proxy.setPort(tdList.get(1).text());
				proxy.setProxySpiderId(proxySpiderId);
				proxyList.add(proxy);
			}
			log.info("parse  double six proxy html completed!");
			return proxyList;
		} catch (Exception e) {
			throw new ParseException("Parse double six proxy html failed!", e);
		}
	}

	@Override
	public String site() {
		return "http://www.66ip.cn/areaindex_" + (rand.nextInt(34) + 1) + "/1.html";
	}

	@Override
	public String toString() {
		return "1----DoubleSixProxySpider";
	}

	/* (non Javadoc)
	
	 * @Title: getPossiblity
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractProxySpider#getPossiblity()
	
	 */
	@Override
	public int getPossiblity() {
		// TODO Auto-generated method stub
		return 50;
	}

	/* (non Javadoc)
	
	 * @Title: getProxySpiderId
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractProxySpider#getProxySpiderId()
	
	 */
	@Override
	public int getProxySpiderId() {
		// TODO Auto-generated method stub
		return proxySpiderId;
	}
}
