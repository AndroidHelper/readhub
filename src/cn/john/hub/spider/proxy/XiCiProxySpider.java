/**  
 * 
 * @Title: ProxySpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午3:03:19

 * @version: V1.0  

 */
package cn.john.hub.spider.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.Proxy;
import cn.john.hub.spider.AbstractProxySpider;
import cn.john.hub.util.HubConsts;

/**
 * 
 * @ClassName: ProxySpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午3:03:19
 * 
 * 
 */
public class XiCiProxySpider extends AbstractProxySpider{

	public static final int spiderNumber = 0;
		
	public XiCiProxySpider(AtomicBoolean crawlingFlag){
		super.crawlingFlag = crawlingFlag;
	}
	
	/*
	 * (non Javadoc)
	 * 
	 * @Title: parseHtmlAndSaveProxy
	 * 
	 * @Description: TODO
	 * 
	 * @see cn.john.hub.spider.AbstractProxySpider#parseHtmlAndSaveProxy()
	 * 
	 */
	@Override
	protected void parseHtml(String html) {
		log.info("Start parsing proxy html...");
		Document doc = Jsoup.parse(html);
		Element news = doc.getElementById("ip_list");
		Elements trs = news.getElementsByTag("tr");
		List<HashMap<Integer, Object>> list = new ArrayList<HashMap<Integer, Object>>();
		for (Element e : trs) {
			Elements tds = e.getElementsByTag("td");
			int i = 0;
			HashMap<Integer, Object> map = new HashMap<Integer, Object>();
			for (Element e1 : tds) {
				i++;
				if (i == 2 || i == 3 || i == 5 || i == 6) {
					map.put(i, e1.html());
				}
				if (i == 7 || i == 8) {
					String s = e1.child(0).attr("title").trim();
					String s1 = s.substring(0, s.length() - 1);
					map.put(i, s1);
				}
			}
			if (i > 1) {
				list.add(map);
			}
		}

		Iterator<HashMap<Integer, Object>> it = list.iterator();

		while (it.hasNext()) {
			HashMap<Integer, Object> map = it.next();
			Proxy proxy = new Proxy();
			proxy.setIpAddr((String) map.get(2));
			proxy.setPort((String) map.get(3));
			dataList.add(proxy);
		}
	}

	@Override
	public String toString() {
		return "XiCiProxySpider";
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
		return HubConsts.PROXY_XICI;
	}

}
