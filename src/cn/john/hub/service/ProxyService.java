/**  
 * @Title: ProxyService.java

 * @Package: cn.john.hub.spider.service

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午2:33:35

 * @version: V1.0  

 */
package cn.john.hub.service;

import java.util.List;

import cn.john.hub.domain.Proxy;
import cn.john.hub.domain.ProxyStatis;

/**

 * @ClassName: ProxyService1

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午2:33:35


 */
public interface ProxyService {
	boolean saveProxy(List<Proxy> pList);
	List<ProxyStatis> getProxyStatis();
}
