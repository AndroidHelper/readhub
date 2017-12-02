/**  
 
 * @Title: KuaiProxySpider.java

 * @Package: cn.john.hub.spider.proxy

 * @Description: TODO

 * @author: John  

 * @date: 2017年12月1日 上午11:19:35

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
 * @ClassName: KuaiProxySpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年12月1日 上午11:19:35
 * 
 * 
 */
public class KuaiProxySpider extends AbstractProxySpider {

	private final int proxySpiderId = 4;

	private Random rand = new Random();

	/* (non Javadoc)
	
	 * @Title: getPossiblity
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractProxySpider#getPossiblity()
	
	 */
	@Override
	public int getPossiblity() {
		// TODO Auto-generated method stub
		return 20;
	}

	/* (non Javadoc)
	
	 * @Title: site
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.spider.AbstractSpider#site()
	
	 */
	@Override
	public String site() {
		return "http://www.kuaidaili.com/ops/proxylist/" + (rand.nextInt(11) + 1);
	}

	/* (non Javadoc)
	
	 * @Title: parseHtml
	
	 * @Description: TODO
	
	 * @param html
	 * @return
	 * @throws ParseException
	
	 * @see cn.john.hub.spider.AbstractSpider#parseHtml(java.lang.String)
	
	 */
	@Override
	protected List<Proxy> parseHtml(String html) throws ParseException {
		log.info("Parsing kuai proxy html...");
		try {
			Document doc = Jsoup.parse(html);
			Element tbody = doc.getElementsByTag("tbody").get(2);
			Elements trList = tbody.children();
			List<Proxy> proxyList = new LinkedList<>();
			Iterator<Element> it = trList.iterator();
			while (it.hasNext()) {
				Element tr = it.next();
				Elements tdList = tr.children();
				if (!tdList.get(2).text().contains("透明")) {
					Proxy p = new Proxy();
					p.setIpAddr(tdList.get(0).text());
					p.setPort(tdList.get(1).text());
					p.setProxySpiderId(proxySpiderId);
					p.setUseCount(0);
					proxyList.add(p);
				}
			}
			log.info("Parse kuai proxy html completed!");
			return proxyList;
		} catch (Exception e) {
			throw new ParseException("Parse kuai proxy html failed!", e);
		}
	}

	@Override
	public String toString() {
		return "3----KuaiProxySpider";
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
