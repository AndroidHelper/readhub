/**  

 * @Title: News.java

 * @Package: cn.john.hub.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午6:48:53

 * @version: V1.0  

 */
package cn.john.hub.domain;

/**
 * 
 * @ClassName: News
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午6:48:53
 * 
 * 
 */
public class NewsDO {
	private Integer newsId;
	private String title;
	private String brief;
	private String url;
	private Integer siteId;

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

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

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	@Override
	public String toString() {
		return "NewsDO [newsId=" + newsId + ", title=" + title + ", brief=" + brief + ", url=" + url + ", siteId="
				+ siteId + "]";
	}

}
