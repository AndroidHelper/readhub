/**  

 * @Title: DoubleSixProxySpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月28日 下午3:42:42

 * @version: V1.0  

 */
package cn.john.hub.spider.proxy;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

	public static final int spiderNumber = 1;

	public DoubleSixProxySpider(AtomicBoolean crawlingFlag) {
		this.crawlingFlag = crawlingFlag;
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
	 * @see cn.john.hub.spider.AbstractProxySpider#parseHtml(java.lang.String)
	 * 
	 */
	@Override
	protected void parseHtml(String html) {
		Document doc = Jsoup.parse(html);
		Element table = doc.getElementsByTag("table").get(2);
		Elements trList = table.getElementsByTag("tr");
		trList.remove(0);
		Iterator<Element> it = trList.iterator();
		while (it.hasNext()) {
			Proxy proxy = new Proxy();
			Element tr = it.next();
			Elements tdList = tr.getElementsByTag("td");
			proxy.setIpAddr(tdList.get(0).text());
			proxy.setPort(tdList.get(1).text());
			dataList.add(proxy);
		}
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: getProxySite
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.spider.AbstractProxySpider#getProxySite()
	 * 
	 */
	@Override
	protected String site() {
		String site = "http://www.66ip.cn/areaindex_" + (rand.nextInt(34) + 1) + "/1.html";
		return site;
	}

	@Override
	public String toString() {
		return "DoubleSixProxySpider";
	}
}
