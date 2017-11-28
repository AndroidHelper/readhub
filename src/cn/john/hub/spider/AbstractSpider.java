/**  
 * @Title: AbstractSpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月1日 上午9:40:19

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.domain.HttpClientException;
import cn.john.hub.domain.ParseException;
import cn.john.hub.domain.Proxy;
import cn.john.hub.domain.Spider;
import cn.john.hub.util.HttpClient;

/**
 * 
 * @ClassName: AbstractSpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * @param <T>
 * 
 * @date: 2017年8月1日 上午9:40:19
 * 
 * 
 */
public abstract class AbstractSpider<T> implements Callable<List<T>> {

	protected static final Logger log = LogManager.getLogger("spider");

	protected LinkedBlockingQueue<Proxy> proxyQueue = Queue.proxyQueue;

	protected AbstractSpider() {
	}

	protected String getHtml(String site) {

		HttpClient httpClient = initHttpClient();
		String html = null;
		while (html == null) {
			httpClient = initHttpClient();
			log.info("httpClient initialized!Proxy queue size is " + proxyQueue.size());
			html = httpClient.getData(site);
		}

		Proxy proxy = httpClient.getProxy();
		// 有可能是本机ip
		if (proxy != null) {
			try {
				proxyQueue.put(proxy);
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
		}
		return html;
	}

	protected abstract HttpClient initHttpClient();

	protected abstract String site();

	protected abstract List<T> parseHtml(String html) throws ParseException;

	@Override
	public List<T> call() throws ParseException {
		return parseHtml(getHtml(site()));
	}
}
