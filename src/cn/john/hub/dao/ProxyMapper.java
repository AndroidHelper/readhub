/**  
 * @Title: ProxyMapper.java

 * @Package: cn.john.hub.spider.dao

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月19日 下午4:50:37

 * @version: V1.0  

 */
package cn.john.hub.dao;

import java.util.List;

import cn.john.hub.domain.Proxy;
import cn.john.hub.domain.ProxyStatis;

/**
 * 
 * @ClassName: ProxyMapper
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月19日 下午4:50:37
 * 
 * 
 */
public interface ProxyMapper {

	void saveProxy(List<Proxy> proxyList);

	List<ProxyStatis> getProxyCounts();
}
