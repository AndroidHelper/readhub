package cn.john.test.spider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

 * @Title: TechWebSpiderTest.java

 * @Package: 

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午9:16:14

 * @version: V1.0  

 */

/**
 * 
 * @ClassName: TechWebSpiderTest
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午9:16:14
 * 
 * 
 */
public class TechWebSpiderTest {
	public static void main(String[] args) {
		HttpClient httpClient = HttpClientFactory.createUsingLocalIP(SiteEnum.TECH_WEB.siteAddr,
				HeaderUtil.getBrowserLikeHeaders());
		String html = httpClient.getData();
		Document doc = Jsoup.parse(html);
		Elements news = doc.getElementsByClass("con_list");
		Elements news1 = news.last().getElementsByClass("con_one");
		Iterator<Element> it = news1.iterator();
		List<NewsDO> newsList = new ArrayList<NewsDO>();
		while (it.hasNext()) {
			Element e = it.next();
			Element h2 = e.getElementsByTag("h2").last();
			Element a = h2.getElementsByTag("a").last();
			String url = a.attr("href");
			String title = a.html();
			NewsDO newsDO = new NewsDO();
			newsDO.setTitle(title);
			newsDO.setUrl(url);

			Elements txt = e.getElementsByClass("con_txt");
			String brief = txt.last().getElementsByTag("p").html();
			newsDO.setBrief(brief);
			newsDO.setSiteId(1);
			newsList.add(newsDO);
		}
		System.out.println(newsList);
	}
}
