/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: BeanUtil.java

 * @Prject: Test3

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月25日 下午5:41:47

 * @version: V1.0  

 */
package cn.john.hub.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.john.hub.domain.Heartbeat;
import cn.john.hub.service.NewsService;

/**
 * 
 * @ClassName: BeanUtil
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年8月25日 下午5:41:47
 * 
 * 
 */
public class BeanUtil {
	private static ApplicationContext appContext = new ClassPathXmlApplicationContext(
			"spring/applicationContext-*.xml");

	public static Heartbeat getHeartbeatBean() {
		return (Heartbeat) appContext.getBean("heartBeat");
	}

	public static NewsService getNewsServiceBean() {
		return (NewsService) appContext.getBean("newsService");
	}
}
