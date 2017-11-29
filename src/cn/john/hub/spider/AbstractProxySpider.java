/**  
 * @Title: AbstractProxySpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午9:56:22

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.List;

import org.apache.http.Header;

import cn.john.hub.domain.ParseException;
import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HeaderUtil;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.HttpClientFactory;

/**
 * 
 * @ClassName: AbstractProxySpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月24日 上午9:56:22
 * 
 * 
 */
public abstract class AbstractProxySpider extends AbstractSpider<Proxy> {

	private ProxyPool proxyPool;

	protected AbstractProxySpider() {
		proxyPool = ProxyPool.getInstance();
	}

	@Override
	public List<Proxy> call() throws ParseException {
		return super.call();
	}

	protected HttpClient getHttpClient(String site) {
		List<Header> headers = HeaderUtil.getBrowserLikeHeaders();
		if (proxyPool.size() > 0) {
			log.info("Fetching proxys using proxy ip...");
			return HttpClientFactory.createUsingProxy(site, headers);
		}
		log.info("Fetching proxys using local ip address...");
		return HttpClientFactory.createUsingLocalIP(site, headers);
	}

}
