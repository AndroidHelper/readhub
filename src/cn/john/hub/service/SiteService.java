/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: SiteService.java

 * @Prject: Test3

 * @Package: cn.john.hub.service

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午7:06:51

 * @version: V1.0  

 */
package cn.john.hub.service;

import java.util.List;

import cn.john.hub.domain.SiteDO;

/**

 * @ClassName: SiteService

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午7:06:51


 */
public interface SiteService {
	List<SiteDO> listSites();
}
