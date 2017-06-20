/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: Proxy.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月19日 下午4:43:09

 * @version: V1.0  

 */
package cn.john.hub.domain;

/**
 * 
 * @ClassName: Proxy
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月19日 下午4:43:09
 * 
 * 
 */
public class Proxy {
	private String ipAddr;
	private String port;
	private String anonymity;
	private String type;
	private String speed;
	private String connectTime;
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getAnonymity() {
		return anonymity;
	}
	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getConnectTime() {
		return connectTime;
	}
	public void setConnectTime(String connectTime) {
		this.connectTime = connectTime;
	}
	@Override
	public String toString() {
		return "Proxy [ipAddr=" + ipAddr + ", port=" + port + ", anonymity=" + anonymity + ", type=" + type + ", speed="
				+ speed + ", connectTime=" + connectTime + "]";
	}

}
