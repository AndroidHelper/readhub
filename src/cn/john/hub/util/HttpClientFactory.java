/**  
 
 * @Title: HttpClientFactory.java

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年10月13日 下午3:31:10

 * @version: V1.0  

 */
package cn.john.hub.util;

import java.util.List;

import org.apache.http.Header;

import cn.john.hub.domain.Proxy;
import cn.john.hub.spider.ProxyPool;

/**
 * 
 * @ClassName: HttpClientFactory
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年10月13日 下午3:31:10
 * 
 * 
 */
public class HttpClientFactory {

	public static final HttpClient createUsingLocalIP(String url, List<Header> headers) {
		return new HttpClient(url, headers, HubConsts.GET, null);
	}

	public static final HttpClient createUsingProxy(String url, List<Header> headers, boolean waitWhenNotEnough) {
		ProxyPool proxyPool = ProxyPool.getInstance();
		Proxy proxy = waitWhenNotEnough ? proxyPool.getAndWaitAtSize(20) : proxyPool.getUntilEmpty();
		LogUtil.getSpiderLogger().info("creating httpClient...pool size is " + proxyPool.size());
		return new HttpClient(url, headers, HubConsts.GET, proxy);
	}

	public static final void recycleProxy(Proxy proxy) {
		ProxyPool.getInstance().recycle(proxy);
	}

	public static final void discardProxy(Proxy proxy) {
		ProxyPool.getInstance().discard(proxy);
	}

	public static final void recordLocalHost() {
		Proxy local = new Proxy();
		local.setIpAddr("localhost");
		local.setUseCount(1);
		local.setProxySpiderId(1024);
		ProxyPool.getInstance().discard(local);
	}

}
