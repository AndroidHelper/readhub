/**  

 * @Title: SinaSpiderTest.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月23日 下午1:25:44

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
 * @ClassName: SinaSpiderTest
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年8月23日 下午1:25:44
 * 
 * 
 */
public class SinaSpiderTest {
	public static void main(String[] args) {
		HttpClient httpClient = HttpClientFactory.createUsingLocalIP(
				"http://feed.mix.sina.com.cn/api/roll/get?pageid=204&lid=22&num=30&page=1",
				HeaderUtil.getBrowserLikeHeaders());
		Document doc = Jsoup.parse(httpClient.getData());
		Elements divs = doc.getElementsByClass("feed-card-item");
		System.out.println(divs.size());
		Iterator<Element> divIt = divs.iterator();
		int loopCount = 0;
		while (divIt.hasNext()) {
			if (++loopCount <= 2) {
				continue;
			}
			Element e = divIt.next();
			NewsDO news = new NewsDO();
			Element aTag = e.child(0).child(0);
			news.setTitle(aTag.text());
			news.setUrl(aTag.attr("href"));
			String brief = e.child(1).child(1).child(0).text();
			news.setBrief(brief);
			news.setSiteId(4);
			System.out.println(news);
		}
	}
}
