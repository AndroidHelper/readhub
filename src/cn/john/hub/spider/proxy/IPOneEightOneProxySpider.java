/**  

 * @Title: IPOneEightOneProxySpider.java

 * @Package: cn.john.hub.spider.proxy

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月28日 下午5:02:05

 * @version: V1.0  

 */
package cn.john.hub.spider.proxy;

import java.util.ArrayList;
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

	private final int proxySpiderId = 3;

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
	public String site() {
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
		log.info("Parsing ip181 html...");
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
				// 该网站只能返回GBK编码，httpClient
				// ResponseHandler目前设置为UTF-8的解码，不好修改。改在这里用 ‘͸��’表示“透明”
				if (!tdList.get(2).text().trim().equals("͸��")) {
					proxy.setIpAddr(tdList.get(0).text());
					proxy.setPort(tdList.get(1).text());
					proxy.setProxySpiderId(proxySpiderId);
					proxy.setUseCount(0);
					proxyList.add(proxy);
				}
			}
			log.info("parse ip181 html completed!");
			return proxyList;
		} catch (Exception e) {
			throw new ParseException("Parse ip181 html failed!", e);
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
		return "2----IPOneEightOneProxySpider";
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
		return 15;
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
