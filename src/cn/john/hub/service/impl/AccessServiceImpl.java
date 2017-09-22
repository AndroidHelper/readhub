/**  
 
 * @Title: AccessServiceImpl.java

 * @Package: cn.john.hub.service.impl

 * @Description: TODO

 * @author: John  

 * @date: 2017年9月22日 下午4:05:27

 * @version: V1.0  

 */
package cn.john.hub.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.john.hub.dao.AccessMapper;
import cn.john.hub.domain.AccRcdDO;
import cn.john.hub.domain.IPInfo;
import cn.john.hub.domain.Visitor;
import cn.john.hub.service.AccessService;
import cn.john.hub.util.HttpClient2;
import cn.john.hub.util.HubConsts;

/**
 * 
 * @ClassName: AccessServiceImpl
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年9月22日 下午4:05:27
 * 
 * 
 */
@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	private AccessMapper aMapper;

	private final Logger log = LogManager.getLogger("web");

	private HttpClient2 httpClient = new HttpClient2();

	private ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);

	@PostConstruct
	private void getIPInfo() {
		stpe.scheduleWithFixedDelay(getLocater(), 0, 5, TimeUnit.MINUTES);
		log.info("ip locater started!");
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: saveAccessRecord
	 * 
	 * @Description: TODO
	 * 
	 * @param ip
	 * 
	 * @see cn.john.hub.service.AccessService#saveAccessRecord(java.lang.String)
	 * 
	 */
	@Override
	public void saveAccessRecord(String ip) {
		aMapper.saveAccessRecord(ip);
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: listAccessRecordToday
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.service.AccessService#listAccessRecordToday()
	 * 
	 */
	@Override
	public List<Visitor> listAccessRecordToday() {
		return aMapper.listAccessRecordToday();
	}

	private IPLocater getLocater() {
		IPLocater locater = null;
		return locater == null ? new IPLocater() : locater;
	}

	private class IPLocater implements Runnable {
		private String ipRegex = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";

		/*
		 * (non Javadoc)
		 * 
		 * @Title: run
		 * 
		 * @Description: TODO
		 * 
		 * 
		 * @see java.lang.Runnable#run()
		 * 
		 */
		@Override
		public void run() {
			List<AccRcdDO> ipList = aMapper.listUnLocatedIp();
			int size = ipList.size();
			log.info("Starting locating ip.Count is " + size);
			if (size > 0) {
				Iterator<AccRcdDO> it = ipList.iterator();
				while (it.hasNext()) {

					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e1) {
						log.error(e1.getMessage());
					}

					AccRcdDO acc = it.next();

					if (Pattern.matches(ipRegex, acc.getIp())) {
						IPInfo info = null;
						try {
							info = JSON.parseObject(httpClient.getData(HubConsts.IP_API + acc.getIp()), IPInfo.class);
						} catch (Exception e) {
							log.error(e.getMessage());
							log.error("Error in locating for ip:" + acc.getIp());
						}
						if (info != null) {
							acc.setCity(info.getCity());
							acc.setProvince(info.getProvince());
							acc.setRectangle(info.getRectangle());
						}
					} else {
						log.debug(acc.getIp() + " is invalid ip!Removing it from locating queue!");
						it.remove();
					}
				}
				if (ipList.size() > 0) {
					aMapper.updateIpLocation(ipList);
				}
				log.info("Locating job done!Update " + ipList.size() + " in total!");
			}
		}
	}

}
