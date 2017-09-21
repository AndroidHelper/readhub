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

	private volatile long newsSaverBeat;
	private volatile long newsSpiderBeat;
	private volatile long proxySpiderBeat;
	private volatile String newsSpiderPoolInfo;
	private volatile String proxySpiderPoolInfo;
	private volatile String newsSpiderExeQueueInfo;
	private volatile Integer lastSavedNewsCount;

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

	public String getNewsSpiderPoolInfo() {
		return newsSpiderPoolInfo;
	}

	public void setNewsSpiderPoolInfo(String newsSpiderPoolInfo) {
		this.newsSpiderPoolInfo = newsSpiderPoolInfo;
	}

	public String getProxySpiderPoolInfo() {
		return proxySpiderPoolInfo;
	}

	public void setProxySpiderPoolInfo(String proxySpiderPoolInfo) {
		this.proxySpiderPoolInfo = proxySpiderPoolInfo;
	}

	public String getNewsSpiderExeQueueInfo() {
		return newsSpiderExeQueueInfo;
	}

	public void setNewsSpiderExeQueueInfo(String newsSpiderExeQueueInfo) {
		this.newsSpiderExeQueueInfo = newsSpiderExeQueueInfo;
	}

	public Integer getLastSavedNewsCount() {
		return lastSavedNewsCount;
	}

	public void setLastSavedNewsCount(Integer lastSavedNewsCount) {
		this.lastSavedNewsCount = lastSavedNewsCount;
	}

	@Override
	public String toString() {
		return "Heartbeat [newsSaverBeat=" + newsSaverBeat + ", newsSpiderBeat=" + newsSpiderBeat + ", proxySpiderBeat="
				+ proxySpiderBeat + ", newsSpiderPoolInfo=" + newsSpiderPoolInfo + ", proxySpiderPoolInfo="
				+ proxySpiderPoolInfo + ", newsSpiderExeQueueInfo=" + newsSpiderExeQueueInfo + ", lastSavedNewsCount="
				+ lastSavedNewsCount + "]";
	}

}
