/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: ProxyServiceImpl.java

 * @Prject: Test3

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
import cn.john.hub.service.ProxyService;
import cn.john.hub.util.HttpClient;

/**

 * @ClassName: ProxyServiceImpl

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午2:33:23


 */
@Service
public class ProxyServiceImpl implements ProxyService{
	@Autowired
	private ProxyMapper pMapper;
	
	private List<Proxy> proxyList;

	/* (non Javadoc)
	
	 * @Title: saveProxy
	
	 * @Description: TODO
	
	
	 * @see cn.john.hub.service.ProxyService#saveProxy()
	
	 */
	@Override
	public void getProxy() {
		
	}
	
	
}
