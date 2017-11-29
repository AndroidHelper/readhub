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

	private static ProxyPool proxyPool;

	public static final HttpClient createUsingLocalIP(String url, List<Header> headers) {
		return new HttpClient(url, headers, true, null);
	}

	public static final HttpClient createUsingProxy(String url, List<Header> headers, boolean unInterrupt) {
		proxyPool = ProxyPool.getInstance();
		Proxy proxy = unInterrupt ? proxyPool.getUnwait() : proxyPool.get();
		return new HttpClient(url, headers, true, proxy);
	}

}
