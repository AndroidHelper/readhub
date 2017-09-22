/**  

 * @Title: NewsController.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月25日 下午8:26:20

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.john.hub.domain.Heartbeat;
import cn.john.hub.domain.NewsSpiderWithTime;
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
@Component
public class NewsSpiderDispatcher implements Runnable {
	private final Logger log = LogManager.getLogger("spider");
	private ExecutorService cacheThreadPool;
	private Random rand;
	@SuppressWarnings("rawtypes")
	private List<NewsSpiderWithTime> spiderList;
	private LinkedList<AbstractNewsSpider> executeQueue;
	@Autowired
	private Heartbeat hb;

	@SuppressWarnings("rawtypes")
	public NewsSpiderDispatcher() {
		cacheThreadPool = Executors.newCachedThreadPool();
		spiderList = new ArrayList<NewsSpiderWithTime>();
		executeQueue = new LinkedList<AbstractNewsSpider>();
		rand = new Random();
		init();
	}

	private void init() {
		//注册newsspider类的实例
		NewsSpiderWithTime<TechWebSpider> ns1 = new NewsSpiderWithTime<TechWebSpider>();
		NewsSpiderWithTime<CnBetaSpider> ns2 = new NewsSpiderWithTime<CnBetaSpider>();
		NewsSpiderWithTime<TaiMediaSpider> ns3 = new NewsSpiderWithTime<TaiMediaSpider>();
		ns1.setClazz(TechWebSpider.class);
		ns2.setClazz(CnBetaSpider.class);
		ns3.setClazz(TaiMediaSpider.class);
		DateTime now = new DateTime();
		ns1.setExeTime(now.plusMinutes(rand.nextInt(10)));
		ns2.setExeTime(now.plusMinutes(rand.nextInt(10)));
		ns3.setExeTime(now.plusMinutes(rand.nextInt(10)));

		spiderList.add(ns1);
		spiderList.add(ns2);
		spiderList.add(ns3);
		log.info("News spider initialing....NewsSpiderMap: " + spiderList);
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: run
	 * 
	 * @Description:10秒执行一次
	 * 
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 */
	@Override
	public void run() {
		
		long timestamp = System.currentTimeMillis();
		hb.setNewsSpiderBeat(timestamp);
		hb.setNewsSpiderExeQueueInfo(executeQueue.toString());
		hb.setNewsSpiderPoolInfo(cacheThreadPool.toString());
		if (Queue.proxyQueue.size() > 10 && executeQueue.size() < 6) {
			try {
				offerSpiderToQueue();
			} catch (InstantiationException e) {
				log.error(e.getMessage());
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
			}
		}
		if (executeQueue.size() > 0) {
			AbstractNewsSpider newsSpider = executeQueue.poll();
			log.info("Executing " + newsSpider);
			cacheThreadPool.execute(newsSpider);
		}

	}

	@SuppressWarnings("rawtypes")
	private void offerSpiderToQueue() throws InstantiationException, IllegalAccessException {
		DateTime now = new DateTime();
		Iterator<NewsSpiderWithTime> it = spiderList.iterator();
		while (it.hasNext()) {

			NewsSpiderWithTime spider = it.next();
			DateTime exeTime = spider.getExeTime();

			if (now.isAfter(exeTime)) {

				AbstractNewsSpider spiderIns = (AbstractNewsSpider) spider.getClazz().newInstance();
				executeQueue.offer(spiderIns);

				int delayTime = spiderIns.getDelayFactor() + rand.nextInt(30);
				DateTime nextExeTime = exeTime.plusMinutes(delayTime);
				spider.setExeTime(nextExeTime);

				log.info("Offer " + spider.getClazz().getSimpleName() + " to queue! Next exe time:"
						+ spider.getExeTime());
			}
		}
	}
}
