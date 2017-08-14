/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: NewsController.java

 * @Prject: Test3

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月25日 下午8:26:20

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import cn.john.hub.spider.news.CnBetaSpider;
import cn.john.hub.spider.news.TaiMediaSpider;
import cn.john.hub.spider.news.TechWebSpider;

/**
 * 
 * @ClassName: NewsController
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月25日 下午8:26:20
 * 
 * 
 */
public class NewsSpiderDispatcher implements Runnable {
	private final Logger log = LogManager.getLogger("logger");
	private Random rand;
	private HashMap<Integer, AbstractNewsSpider> newsSpiderMap;
	private LinkedList<AbstractNewsSpider> newsSpiderQueue;

	public Map<Integer, DateTime> timerMap;

	public NewsSpiderDispatcher() {
		newsSpiderMap = new HashMap<Integer, AbstractNewsSpider>();
		newsSpiderQueue = new LinkedList<AbstractNewsSpider>();
		rand = new Random();
		timerMap = Collections.synchronizedMap(new HashMap<Integer, DateTime>());
		init();
	}

	private void init() {
		// 通过关联spider的序列号，注册newsspider类的实例
		try {
			AbstractNewsSpider newsSpider = TechWebSpider.class.newInstance();
			AbstractNewsSpider newsSpider1 = CnBetaSpider.class.newInstance();
			AbstractNewsSpider newsSpider2 = TaiMediaSpider.class.newInstance();
			newsSpiderMap.put(newsSpider.getSerialNumber(),newsSpider);
			newsSpiderMap.put(newsSpider1.getSerialNumber(),newsSpider1);
			newsSpiderMap.put(newsSpider2.getSerialNumber(),newsSpider2);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		log.info("News spider initialing....NewsSpiderMap: "+newsSpiderMap);
		// 将类的启动时间与spider序列号关联，初始化启动时间为当前时间
		Set<Integer> nsSet = newsSpiderMap.keySet();
		DateTime now = new DateTime();
		for (Integer i : nsSet) {
			timerMap.put(i, now);
		}
		log.info("News spider initialized!timerMap: "+timerMap);
	}

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
		log.info("News Spider dispatcher start!");
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Queue.proxyQueue.size()>0){
				offerSpiderToQueue();
			}
			if (newsSpiderQueue.size() > 0) {
				AbstractNewsSpider newsSpider = newsSpiderQueue.poll();
				int delayTime = newsSpider.getDelayFactor() + rand.nextInt(2);
				int sn = newsSpider.getSerialNumber();
				DateTime nextExeTime = timerMap.get(sn).plusMinutes(delayTime);
				timerMap.put(sn, nextExeTime);
				log.info("Executing " + newsSpider+"and it's next execute time is "+timerMap.get(sn));
				SpiderDispatcher.cacheThreadPool.execute(newsSpider);
			}
		}
	}
	
	private void offerSpiderToQueue(){
		DateTime now = new DateTime();
		Set<Integer> snSet = timerMap.keySet();
		Iterator<Integer> it = snSet.iterator();
		while(it.hasNext()){
			int sn = it.next();
			DateTime timeOfSpider = timerMap.get(sn);
			if(now.isAfter(timeOfSpider)){
				newsSpiderQueue.offer(newsSpiderMap.get(sn));
				log.info("Offer spider to queue!"+newsSpiderMap.get(sn)+"And it's execute time is "+timerMap.get(sn));
			}
		}
	}
}
