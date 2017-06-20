/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: SiteServiceImpl.java

 * @Prject: Test3

 * @Package: cn.john.hub.service.impl

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午7:07:26

 * @version: V1.0  

 */
package cn.john.hub.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.john.hub.dao.SiteMapper;
import cn.john.hub.domain.SiteDO;
import cn.john.hub.service.SiteService;

/**

 * @ClassName: SiteServiceImpl

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午7:07:26


 */
@Service
public class SiteServiceImpl implements SiteService{

	private Logger log = LogManager.getLogger("logger");
	@Autowired
	private SiteMapper sMapper;
	/* (non Javadoc)
	
	 * @Title: listSites
	
	 * @Description: TODO
	
	 * @return
	
	 * @see cn.john.hub.service.SiteService#listSites()
	
	 */
	@Override
	public List<SiteDO> listSites() {
		return sMapper.listSites();
	}

}
