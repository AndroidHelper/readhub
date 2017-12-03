/**  

 * @Title: Heartbeat.java

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月25日 下午4:20:23

 * @version: V1.0  

 */
package cn.john.hub.domain;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: Heartbeat
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年8月25日 下午4:20:23
 * 
 * 
 */
@Component
public class Heartbeat {

	private volatile long newsSpiderBeat;
	private volatile long ipLocaterBeat;

	/**
	 * 
	 * @return the newsSpiderBeat
	 * 
	 */
	public long getNewsSpiderBeat() {
		return newsSpiderBeat;
	}

	/**
	 * @param newsSpiderBeat
	 *            the newsSpiderBeat to set
	 */
	public void setNewsSpiderBeat(long newsSpiderBeat) {
		this.newsSpiderBeat = newsSpiderBeat;
	}

	/**
	 * 
	 * @return the ipLocaterBeat
	 * 
	 */
	public long getIpLocaterBeat() {
		return ipLocaterBeat;
	}

	/**
	 * @param ipLocaterBeat
	 *            the ipLocaterBeat to set
	 */
	public void setIpLocaterBeat(long ipLocaterBeat) {
		this.ipLocaterBeat = ipLocaterBeat;
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: toString
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 */
	@Override
	public String toString() {
		return "Heartbeat [newsSpiderBeat=" + newsSpiderBeat + ", ipLocaterBeat=" + ipLocaterBeat + "]";
	}

}
