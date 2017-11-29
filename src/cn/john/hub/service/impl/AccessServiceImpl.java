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
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.http.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.john.hub.dao.AccessMapper;
import cn.john.hub.domain.AccRcdDO;
import cn.john.hub.domain.Heartbeat;
import cn.john.hub.domain.IPInfo;
import cn.john.hub.domain.Visitor;
import cn.john.hub.service.AccessService;
import cn.john.hub.spider.Queue;
import cn.john.hub.util.HeaderUtil;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.HttpClientFactory;
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
	@Autowired
	private Heartbeat hb;
	private final Logger log = LogManager.getLogger("web");

	private ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);
	private List<Header> headers = HeaderUtil.getNaiveAuthHeaders();

	@PostConstruct
	private void locateIp() {
		// 内部类是单例模式，list不是线程安全的类，使用fixedRate,避免并发
		stpe.scheduleAtFixedRate(getLocater(), 2, 3, TimeUnit.MINUTES);
		log.info("ip locater started!");
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

		private HttpClient httpClient;

		private Random rand = new Random();

		private List<AccRcdDO> accList = new ArrayList<>();

		private ConcurrentLinkedQueue<AccRcdDO> accQueue = Queue.accQueue;

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

			long timestamp = System.currentTimeMillis();
			hb.setIpLocaterBeat(timestamp);

			AccRcdDO acc = null;

			try {
				while ((acc = accQueue.poll()) != null) {

					int count = accList.size();
					if (count > 200) {
						aMapper.saveAccessRecord(accList);
						log.info("Saving access record... size is " + count);
						accList.clear();
					}

					try {
						TimeUnit.SECONDS.sleep(rand.nextInt(10));
					} catch (InterruptedException e1) {
						log.error(e1.getMessage());
					}

					if (Pattern.matches(ipRegex, acc.getIp())) {
						IPInfo info = null;
						try {
							httpClient = HttpClientFactory.createUsingLocalIP(HubConsts.IP_API + acc.getIp(), headers);
							info = JSON.parseObject(httpClient.getData(), IPInfo.class);
							log.debug("ip info :" + info);
						} catch (Exception e) {
							log.error(e.getMessage());
							log.error("Error in locating for ip:" + acc.getIp());
						}
						if (info != null) {
							acc.setCity(info.getCity());
							acc.setProvince(info.getProvince());
							acc.setRectangle(info.getRectangle());
						}
					}

					accList.add(acc);
				}
				if (!accList.isEmpty()) {
					aMapper.saveAccessRecord(accList);
					log.info("Saving access record... size is " + accList.size());
					accList.clear();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

}
