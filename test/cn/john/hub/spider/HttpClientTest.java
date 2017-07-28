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

import javax.swing.plaf.synth.SynthSeparatorUI;

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
		HttpClient httpClient = new HttpClient("111.13.2.138",80);
		String s = httpClient.getData(SiteEnum.TECH_WEB.siteAddr);
		System.out.println(s);
	}
}
