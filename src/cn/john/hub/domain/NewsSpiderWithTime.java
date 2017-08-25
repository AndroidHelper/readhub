/**  

 * @Title: NewsSpider.java

 * @Package: cn.john.hub.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月25日 下午7:16:33

 * @version: V1.0  

 */
package cn.john.hub.domain;

import org.joda.time.DateTime;

import cn.john.hub.spider.AbstractNewsSpider;

/**
 * 
 * @ClassName: NewsSpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * @param <AbstractNewsSpider>
 * 
 * @date: 2017年8月25日 下午7:16:33
 * 
 * 
 */
public class NewsSpiderWithTime<T extends AbstractNewsSpider> {
	private Class<T> clazz;
	private DateTime exeTime;

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public DateTime getExeTime() {
		return exeTime;
	}

	public void setExeTime(DateTime exeTime) {
		this.exeTime = exeTime;
	}

	@Override
	public String toString() {
		return "NewsSpider [clazz=" + clazz + ", exeTime=" + exeTime + "]";
	}

}
