/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: main.java

 * @Prject: Test4

 * @Package: cn.john.hub.main

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午4:37:26

 * @version: V1.0  

 */
package cn.john.hub.main;

import cn.john.hub.util.Consts;
import cn.john.hub.util.HttpClient;

/**

 * @ClassName: main

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午4:37:26


 */
public class main1 {
	public static void main(String arg[]){
		HttpClient hc = new HttpClient();
		String s = hc.getData(Consts.PROXY_XICI);
		System.out.println(s);
	}
}
