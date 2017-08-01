/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: HttpClientTest.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月28日 下午1:54:09

 * @version: V1.0  

 */
package cn.john.hub.spider;

import cn.john.hub.domain.Proxy;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.SiteEnum;

/**

 * @ClassName: HttpClientTest

 * @Description: TODO

 * @author: John

 * @date: 2017年7月28日 下午1:54:09


 */
public class HttpClientTest {
	public static void main(String[] args) {
		Proxy proxy = new Proxy();
		proxy.setIpAddr("");
		proxy.setPort("");
		HttpClient httpClient = new HttpClient(proxy);
		String s = httpClient.getData(SiteEnum.TECH_WEB.siteAddr);
		System.out.println(s);
	}
}
