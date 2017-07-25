/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: AbstractProxySpider.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午9:56:22

 * @version: V1.0  

 */
package cn.john.hub.spider;

import cn.john.hub.util.HttpClient;

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
public abstract class AbstractProxySpider implements Runnable {

	protected HttpClient httpClient;

	protected AbstractProxySpider() {
		httpClient = new HttpClient();
	}

	protected String getHtml(String proxySite) {
		return httpClient.getData(proxySite);
	}

	protected abstract void parseHtmlAndSaveProxy(String html);
}
