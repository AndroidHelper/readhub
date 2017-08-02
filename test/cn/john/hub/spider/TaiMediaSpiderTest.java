/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: TaiMediaSpiderTest.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月2日 下午7:00:51

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.util.HttpClient;

/**

 * @ClassName: TaiMediaSpiderTest

 * @Description: TODO

 * @author: John

 * @date: 2017年8月2日 下午7:00:51


 */
public class TaiMediaSpiderTest {
	public static void main(String[] args) {
		HttpClient httpClient = new HttpClient();
		Document doc = Jsoup.parse(httpClient.getData("http://www.tmtpost.com/column/2581216"),"http://www.tmtpost.com/column/2581216");
		Elements lis = doc.getElementsByClass("mod-article-list clear").get(0).child(0).children();
		Iterator<Element> it = lis.iterator();
		while(it.hasNext()){
			Element e = it.next();
			NewsDO news = new NewsDO();
			Element aTag = e.getElementsByTag("h3").get(0).child(0);
			String title = aTag.attr("title");
			String url = aTag.attr("abs:href");
			String brief = e.getElementsByTag("p").get(0).text();
			news.setBrief(brief);
			news.setUrl(url);
			news.setTitle(title);
			System.out.println(news);
		}
	}
}
