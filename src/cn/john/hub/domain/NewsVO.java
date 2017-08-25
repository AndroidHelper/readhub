/**  
 * @Title: NewsVO.java

 * @Package: cn.john.hub.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月22日 下午5:02:16

 * @version: V1.0  

 */
package cn.john.hub.domain;

/**
 * 
 * @ClassName: NewsVO
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年8月22日 下午5:02:16
 * 
 * 
 */
public class NewsVO {
	private String title;
	private String brief;
	private String url;
	private String siteName;
	private String siteAddr;
	private String time;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "NewsVO [title=" + title + ", brief=" + brief + ", url=" + url + ", siteName=" + siteName + ", siteAddr="
				+ siteAddr + ", time=" + time + "]";
	}

}
