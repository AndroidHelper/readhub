/**  

 * @Title: CnBetaSpiderTest.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月2日 下午6:24:52

 * @version: V1.0  

 */
package cn.john.test.spider;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.util.HeaderUtil;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.HttpClientFactory;
import cn.john.hub.util.SiteEnum;

/**
 * 
 * @ClassName: CnBetaSpiderTest
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年8月2日 下午6:24:52
 * 
 * 
 */
public class CnBetaSpiderTest {
	public static void main(String[] args) {
		HttpClient httpClient = HttpClientFactory.createUsingLocalIP("http://www.cnbeta.com/category/tech.htm",
				HeaderUtil.getBrowserLikeHeaders());
		Document doc = Jsoup.parse(httpClient.getData());
		Elements items = doc.getElementsByClass("items-area").get(0).children();
		Iterator<Element> it = items.iterator();
		while (it.hasNext()) {
			Element e = it.next();
			NewsDO news = new NewsDO();
			Element aTag = e.getElementsByTag("dt").get(0).child(0);
			news.setTitle(aTag.text());
			news.setUrl(aTag.attr("href"));
			Element brief = e.getElementsByTag("dd").get(0);
			news.setBrief(brief.text());
			System.out.println(news);
		}
	}
}
