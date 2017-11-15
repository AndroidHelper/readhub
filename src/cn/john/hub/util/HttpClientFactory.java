/**  
 
 * @Title: HttpClientFactory.java

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年10月13日 下午3:31:10

 * @version: V1.0  

 */
package cn.john.hub.util;

import cn.john.hub.domain.Proxy;

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

	public static final HttpClient createLocalBrowserLikeClient() {
		return new HttpClient();
	}

	public static final HttpClient createProxyBrowserLikeClient(Proxy proxy) {
		return new HttpClient(proxy);
	}

	public static final HttpClient2 createLocalNaiveClient() {
		return new HttpClient2();
	}

}
