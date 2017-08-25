/**  
 * @Title: SiteMapper.java

 * @Package: cn.john.hub.dao

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午7:06:14

 * @version: V1.0  

 */
package cn.john.hub.dao;

import java.util.List;

import cn.john.hub.domain.SiteDO;

/**

 * @ClassName: SiteMapper

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午7:06:14


 */
public interface SiteMapper {
	List<SiteDO> listSites();
}
