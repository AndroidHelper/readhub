/**  

 * @Title: SiteEnum.java

 * @Prject: Test3

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午8:54:07

 * @version: V1.0  

 */
package cn.john.hub.util;

/**

 * @ClassName: SiteEnum

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午8:54:07


 */
public enum SiteEnum {
	
	TECH_WEB("TechWeb","http://www.techweb.com.cn/news/#wp");
	
	public String siteName;
	public String siteAddr;
	
	private SiteEnum(String siteName,String siteAddr){
		this.siteName = siteName;
		this.siteAddr = siteAddr;
	}
}
