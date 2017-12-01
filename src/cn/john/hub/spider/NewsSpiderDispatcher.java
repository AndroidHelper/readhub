/**  

 * @Title: NewsController.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月25日 下午8:26:20

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.john.hub.domain.Heartbeat;
import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.NewsSpiderWithTime;
import cn.john.hub.service.NewsService;
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
	private ProxyPool proxyPool;
	@SuppressWarnings("rawtypes")
	private List<NewsSpiderWithTime> spiderList;
	private LinkedList<AbstractNewsSpider> executeQueue;
	@Autowired
	private Heartbeat hb;
	@Autowired
	private NewsService nService;

	@SuppressWarnings("rawtypes")
	public NewsSpiderDispatcher() {
		cacheThreadPool = Executors.newCachedThreadPool();
		spiderList = new ArrayList<NewsSpiderWithTime>();
		executeQueue = new LinkedList<AbstractNewsSpider>();
		rand = new Random();
		// 先初始化代理Ip池
		proxyPool = ProxyPool.getInstance();
		init();
	}

	private void init() {
		// 注册newsspider类的实例
		DateTime now = new DateTime();

		ClassLoader loader = this.getClass().getClassLoader();
		String classPath = this.getClass().getResource("").getFile();
		String packageName = this.getClass().getPackage().getName();

		String newsClassPath = classPath + "news/";
		String newsPackage = packageName + ".news";

		File classes = new File(newsClassPath);
		for (File f : classes.listFiles()) {
			String fileName = f.getName();
			// 代码尚不成熟
			if (fileName.contains("Alpha")) {
				continue;
			}
			try {
				String className = newsPackage + "." + fileName.replace(".class", "");
				@SuppressWarnings("unchecked")
				Class<AbstractNewsSpider> clazz = (Class<AbstractNewsSpider>) loader.loadClass(className);
				NewsSpiderWithTime<AbstractNewsSpider> spider = new NewsSpiderWithTime<>();
				spider.setClazz(clazz);
				spider.setExeTime(now.plusMinutes(rand.nextInt(1)));
				spiderList.add(spider);
			} catch (ClassNotFoundException e) {
				log.error(e);
			}
		}
		
		log.info("News Spider List initiated:"+spiderList);
		
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
		if (executeQueue.size() < 6) {
			try {
				offerSpiderToQueue(spiderList);
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
	private void offerSpiderToQueue(List<NewsSpiderWithTime> spiderList)
			throws InstantiationException, IllegalAccessException {
		DateTime now = new DateTime();
		Iterator<NewsSpiderWithTime> it = spiderList.iterator();
		while (it.hasNext()) {

			NewsSpiderWithTime spider = it.next();
			DateTime exeTime = spider.getExeTime();

			if (now.isAfter(exeTime)) {

				AbstractNewsSpider spiderIns = (AbstractNewsSpider) spider.getClazz().newInstance();
				executeQueue.offer(spiderIns);

				int delayTime = spiderIns.getDelayFactor() + rand.nextInt(30);
				DateTime nextExeTime = exeTime.plusSeconds(delayTime);
				spider.setExeTime(nextExeTime);

				log.info("Offer " + spider.getClazz().getSimpleName() + " to queue! Next exe time:"
						+ spider.getExeTime());
			}
		}
	}

}
