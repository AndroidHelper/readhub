/**  
 * @Title: Proxy.java

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

	@Override
	public String toString() {
		return "Proxy [ipAddr=" + ipAddr + ", port=" + port + "]";
	}

}
