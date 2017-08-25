/**  
 * @Title: SiteDO.java

 * @Package: cn.john.hub.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午7:04:56

 * @version: V1.0  

 */
package cn.john.hub.domain;

/**
 * 
 * @ClassName: SiteDO
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午7:04:56
 * 
 * 
 */
public class SiteDO {
	private Integer siteId;
	private String siteName;
	private String siteAddr;

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteAddr() {
		return siteAddr;
	}

	public void setSiteAddr(String siteAddr) {
		this.siteAddr = siteAddr;
	}

	@Override
	public String toString() {
		return "SiteDO [siteId=" + siteId + ", siteName=" + siteName + ", siteAddr=" + siteAddr + "]";
	}

}
