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

	protected AbstractProxySpider() {
	}

	@Override
	public void run() {
		super.run();
	}

	protected HttpClient fetchNewHttpClient(String site) {
		List<Header> headers = HeaderUtil.getBrowserLikeHeaders();
		return HttpClientFactory.createUsingProxy(site, headers, false);
	}

	/* (non Javadoc)
	
	 * @Title: consume
	
	 * @Description: 定义代理爬虫的消费者
	
	 * @param list
	
	 * @see cn.john.hub.spider.AbstractSpider#consume(java.util.List)
	
	 */
	@Override
	protected void consume(List<Proxy> list) {
		ProxyPool.getInstance().putAll(list);
	}

	public abstract int getPossiblity();

	public abstract int getProxySpiderId();

}
