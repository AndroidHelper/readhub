/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: Heartbeat.java

 * @Prject: Test3

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
	
	private long newsSaverBeat;
	private long newsSpiderBeat;
	private long proxySpiderBeat;

	public long getNewsSaverBeat() {
		return newsSaverBeat;
	}

	public void setNewsSaverBeat(long newsSaverBeat) {
		this.newsSaverBeat = newsSaverBeat;
	}

	public long getNewsSpiderBeat() {
		return newsSpiderBeat;
	}

	public void setNewsSpiderBeat(long newsSpiderBeat) {
		this.newsSpiderBeat = newsSpiderBeat;
	}

	public long getProxySpiderBeat() {
		return proxySpiderBeat;
	}

	public void setProxySpiderBeat(long proxySpiderBeat) {
		this.proxySpiderBeat = proxySpiderBeat;
	}

	@Override
	public String toString() {
		return "Heartbeat [newsSaverBeat=" + newsSaverBeat + ", newsSpiderBeat=" + newsSpiderBeat + ", proxySpiderBeat="
				+ proxySpiderBeat + "]";
	}

}
