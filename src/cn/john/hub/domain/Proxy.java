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
	private String anonymity;

	/**
	 * 
	 * @return the ipAddr
	 * 
	 */
	public String getIpAddr() {
		return ipAddr;
	}

	/**
	 * @param ipAddr
	 *            the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	/**
	 * 
	 * @return the port
	 * 
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * 
	 * @return the anonymity
	 * 
	 */
	public String getAnonymity() {
		return anonymity;
	}

	/**
	 * @param anonymity
	 *            the anonymity to set
	 */
	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
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
		return "Proxy [ipAddr=" + ipAddr + ", port=" + port + ", anonymity=" + anonymity + "]";
	}

}
