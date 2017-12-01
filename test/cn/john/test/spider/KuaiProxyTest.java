/**  
 
 * @Title: KuaiProxyTest.java

 * @Package: cn.john.test.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年12月1日 上午11:57:04

 * @version: V1.0  

 */
package cn.john.test.spider;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HeaderUtil;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.HttpClientFactory;

/**
 * 
 * @ClassName: KuaiProxyTest
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年12月1日 上午11:57:04
 * 
 * 
 */
public class KuaiProxyTest {
	public static void main(String[] args) throws InterruptedException {
		String addr = "http://www.kuaidaili.com/ops/proxylist/";
		for (int i = 1; i < 11; i++) {
			String addr1 = addr + i;
			System.out.println(addr1);
			HttpClient httpClient = HttpClientFactory.createUsingLocalIP(addr1, HeaderUtil.getBrowserLikeHeaders());
			String html = httpClient.getData();
			Document doc = Jsoup.parse(html);
			Element tbody = doc.getElementsByTag("tbody").get(2);
			Elements trList = tbody.children();
			Iterator<Element> it = trList.iterator();
			while (it.hasNext()) {
				Element tr = it.next();
				Elements tdList = tr.children();
				if (!tdList.get(2).text().contains("透明")) {
					Proxy p = new Proxy();
					p.setIpAddr(tdList.get(0).text());
					p.setPort(tdList.get(1).text());
					System.out.println(p);
				}
			}
			
			Thread.sleep(3000);
		}
	}
}
