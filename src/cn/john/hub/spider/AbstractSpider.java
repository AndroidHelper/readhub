/**  
 * @Title: AbstractSpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月1日 上午9:40:19

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.domain.ParseException;
import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HttpClient;

/**
 * 
 * @ClassName: AbstractSpider
 * 
 * @Description: 爬虫抽象类，定义了爬虫的行为。不同类别的爬虫的控制策略、解析方法等交由子类实现。
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

	protected AbstractSpider() {
	}

	/**
	 * 
	 * @Title: getHtml
	 * 
	 * @Description: 向site发起http请求。循环获取新的httpClient不断尝试直到成功请求到html页面，
	 *               同时将可用的代理ip回收入池。
	 * 
	 * @param site
	 * @return
	 * 
	 * @return: String
	 * 
	 */
	protected String getHtml(String site) {
		String html = null;
		HttpClient httpClient = null;
		while (html == null) {
			log.info(this + " trying getting html...");
			httpClient = fetchNewHttpClient(site);
			html = httpClient.getData();
		}
		log.info(this + " html got!");
		// 如果该httpClient使用了代理，并且该代理请求网站成功返回，证明代理可用，重新入池。
		Proxy p = httpClient.getProxy();
		if (p != null) {
			fetchProxyPool().offerFirst(p);
		}
		return html;
	}

	/**
	
	 * @Title: fetchProxyPool
	
	 * @Description: 获取代理池实例
	
	 * @return
	
	 * @return: ProxyPool
	
	 */
	protected ProxyPool fetchProxyPool() {
		return ProxyPool.getInstance();
	}

	/**
	
	 * @Title: fetchNewHttpClient
	
	 * @Description: 获取新的httpClient。具体的获取策略由子类实现。
	
	 * @param site
	 * @return
	
	 * @return: HttpClient
	
	 */
	protected abstract HttpClient fetchNewHttpClient(String site);

	/**
	
	 * @Title: site
	
	 * @Description: 返回站点地址，由子类实现
	
	 * @return
	
	 * @return: String
	
	 */
	protected abstract String site();

	/**
	
	 * @Title: parseHtml
	
	 * @Description: 解析html，由子类实现
	
	 * @param html
	 * @return
	 * @throws ParseException
	
	 * @return: List<T>
	
	 */
	protected abstract List<T> parseHtml(String html) throws ParseException;

	/* (non Javadoc)
	
	 * @Title: call
	
	 * @Description: 线程执行方法
	
	 * @return
	 * @throws ParseException
	
	 * @see java.util.concurrent.Callable#call()
	
	 */
	@Override
	public List<T> call() throws ParseException {
		return parseHtml(getHtml(site()));
	}
}
