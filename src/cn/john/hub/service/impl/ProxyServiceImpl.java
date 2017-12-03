/**  
 * @Title: ProxyServiceImpl.java

 * @Package: cn.john.hub.spider.service.impl

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午2:33:23

 * @version: V1.0  

 */
package cn.john.hub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.john.hub.dao.ProxyMapper;
import cn.john.hub.domain.Proxy;
import cn.john.hub.domain.ProxyStatis;
import cn.john.hub.service.ProxyService;
import cn.john.hub.util.LogUtil;

/**
 * 
 * @ClassName: ProxyServiceImpl
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午2:33:23
 * 
 * 
 */
@Service
public class ProxyServiceImpl implements ProxyService {
	@Autowired
	private ProxyMapper pMapper;

	/*
	 * (non Javadoc)
	 * 
	 * @Title: saveProxy
	 * 
	 * @Description: TODO
	 * 
	 * @param pList
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.service.ProxyService#saveProxy(java.util.List)
	 * 
	 */
	@Override
	public boolean saveProxy(List<Proxy> pList) {
		try {
			pMapper.saveProxy(pList);
			return true;
		} catch (Exception e) {
			LogUtil.getSpiderLogger().error("Error saving proxy!", e);
			return false;
		}
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: getProxyStatis
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.service.ProxyService#getProxyStatis()
	 * 
	 */
	@Override
	public List<ProxyStatis> listProxyStatis() {
		return pMapper.getProxyCounts();
	}

}
