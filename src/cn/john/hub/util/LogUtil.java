/**  
 
 * @Title: LogUtil.java

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年11月29日 上午11:05:48

 * @version: V1.0  

 */
package cn.john.hub.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @ClassName: LogUtil
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年11月29日 上午11:05:48
 * 
 * 
 */
public class LogUtil {

	private static final Logger spider = LogManager.getLogger("spider");
	private static final Logger web = LogManager.getLogger("web");

	public static Logger getSpiderLogger() {
		return spider;
	}
	
	public static Logger getWebLogger() {
		return web;
	}

}
