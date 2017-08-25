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

	private Long newsSaverBeat;
	private Long newsSpiderBeat;
	private Long proxySpiderBeat;
	private String newsSpiderPoolInfo;
	private String proxySpiderPoolInfo;
	private String newsSpiderExeQueueInfo;
	private Integer lastSavedNewsCount;

	public Long getNewsSaverBeat() {
		return newsSaverBeat;
	}

	public void setNewsSaverBeat(Long newsSaverBeat) {
		this.newsSaverBeat = newsSaverBeat;
	}

	public Long getNewsSpiderBeat() {
		return newsSpiderBeat;
	}

	public void setNewsSpiderBeat(Long newsSpiderBeat) {
		this.newsSpiderBeat = newsSpiderBeat;
	}

	public Long getProxySpiderBeat() {
		return proxySpiderBeat;
	}

	public void setProxySpiderBeat(Long proxySpiderBeat) {
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
