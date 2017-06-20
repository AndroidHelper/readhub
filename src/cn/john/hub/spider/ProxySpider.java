/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: ProxySpider.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午3:03:19

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import cn.john.hub.domain.Proxy;
import cn.john.hub.util.Consts;
import cn.john.hub.util.HttpClient;

/**

 * @ClassName: ProxySpider

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午3:03:19


 */
@Component
public class ProxySpider {
	
	public ProxySpider(){
		log.info("Constructing...");
		proxyList = new ArrayList<Proxy>();
	}
	
	private List<Proxy> proxyList;
	private HttpClient httpClient;
	private final static Logger log = LogManager.getLogger("logger");
	
	@PostConstruct
	public void init(){
		log.info("Start fetching proxy...");
		if(proxyList.size()==0){
			httpClient = new HttpClient("220.160.10.95",808);
			String html = httpClient.getData(Consts.PROXY_SITE);
			parseProxyHtml(html);
		}
	}
	
	private void parseProxyHtml(String html){
		log.info("Start parsing...");
		Document doc = Jsoup.parse(html);
		Element news = doc.getElementById("ip_list");
		Elements trs = news.getElementsByTag("tr");
		List<HashMap<Integer,Object>> list = new ArrayList<HashMap<Integer,Object>>();
		for(Element e:trs){
			Elements tds = e.getElementsByTag("td");
			int i = 0;
			HashMap<Integer,Object> map = new HashMap<Integer,Object>();
			for(Element e1:tds){
				i++;
				if(i==2||i==3||i==5||i==6){
					map.put(i, e1.html());
				}
				if(i==7||i==8){
					String s = e1.child(0).attr("title").trim();
					String s1 = s.substring(0, s.length()-1);
					map.put(i, s1);
				}
			}
			if(i>1){
				list.add(map);				
			}
		}
		Iterator<HashMap<Integer, Object>> it = list.iterator();
		while(it.hasNext()){
			HashMap<Integer,Object> map = it.next();
			Proxy proxy = new Proxy();
			proxy.setIpAddr((String) map.get(2));
			proxy.setPort((String) map.get(3));
			proxy.setAnonymity((String) map.get(5));
			proxy.setType((String) map.get(6));
			proxy.setSpeed((String) map.get(7));
			proxy.setConnectTime( (String) map.get(8));
			proxyList.add(proxy);
		}
		log.info(proxyList);
	}
}
