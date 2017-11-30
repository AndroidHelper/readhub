/**  
 
 * @Title: Ip181Test.java

 * @Package: cn.john.test.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年11月30日 下午3:09:23

 * @version: V1.0  

 */
package cn.john.test.spider;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HeaderUtil;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.HttpClientFactory;

/**
 * 
 * @ClassName: Ip181Test
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年11月30日 下午3:09:23
 * 
 * 
 */
public class Ip181Test {
	public static void main(String[] args) {
		HttpClient httpClient = HttpClientFactory.createUsingLocalIP("http://www.ip181.com/",
				HeaderUtil.getBrowserLikeHeaders());
		String html = httpClient.getData();
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
			if (!tdList.get(2).text().trim().equals("͸��")) {
				proxy.setIpAddr(tdList.get(0).text());
				proxy.setPort(tdList.get(1).text());
				proxy.setProxySpiderId(1);
				proxy.setAnonymity(tdList.get(2).text());
				proxyList.add(proxy);
				System.out.println(proxy);
			}
		}
	}
}
