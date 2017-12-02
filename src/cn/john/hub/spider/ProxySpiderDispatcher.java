/**  

 * @Title: ProxyController.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午10:27:18

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.john.hub.domain.Proxy;
import cn.john.hub.spider.proxy.DoubleSixProxySpider;
import cn.john.hub.spider.proxy.IPOneEightOneProxySpider;
import cn.john.hub.spider.proxy.KuaiProxySpider;
import cn.john.hub.spider.proxy.XiCiProxySpider;

/**
 * 
 * @ClassName: ProxyController
 * 
 * @Description: 代理爬虫调度器
 * 
 * @author: John
 * 
 * @date: 2017年7月24日 上午10:27:18
 * 
 * 
 */
public class ProxySpiderDispatcher implements Runnable {

	private static Logger log = LogManager.getLogger("spider");

	private ExecutorService cacheThreadPool;
	private HashMap<Integer, AbstractProxySpider> proxyMap;
	private Random rand = new Random();

	public ProxySpiderDispatcher() {
		cacheThreadPool = Executors.newCachedThreadPool();
		proxyMap = new HashMap<Integer, AbstractProxySpider>();
		init();
	}

	// 注册已有的代理爬虫以供选择
	private void init() {

		Class<? extends ProxySpiderDispatcher> clazz = this.getClass();

		ClassLoader loader = clazz.getClassLoader();
		String classPath = clazz.getResource("").getFile();
		String classPackage = clazz.getPackage().getName();

		String proxyPackage = classPackage + ".proxy";
		String proxyPath = classPath + "proxy/";

		File classes = new File(proxyPath);
		String className = null;
		for (File f : classes.listFiles()) {
			String fileName = f.getName();
			if (fileName.contains("Alpha")) {
				continue;
			}

			className = proxyPackage + "." + fileName.replace(".class", "");
			try {
				AbstractProxySpider aps = (AbstractProxySpider) loader.loadClass(className).newInstance();
				proxyMap.put(aps.getProxySpiderId(), aps);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		log.info("proxy map initialized!Detail: " + proxyMap);
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: run
	 * 
	 * @Description: 10秒执行一次
	 * 
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 */
	@Override
	public void run() {

		ProxyPool proxyPool = ProxyPool.getInstance();

		while (proxyPool.size() < 20) {
			startProxySpider(proxyPool);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		proxyPool.signalEnough();
	}

	// 启动代理爬虫
	private void startProxySpider(ProxyPool proxyPool) {
		// 随机选择代理爬虫

		Proxy p = proxyPool.peek();
		AbstractProxySpider proxySpider = proxyMap.get(selectSpider(p));
		log.info("Proxy spider selected: " + proxySpider + " And coming proxy is " + p);

		if (proxySpider instanceof KuaiProxySpider) {
			log.info("kuai dai li ");
			Class<? extends AbstractProxySpider> clazz = proxySpider.getClass();
			AbstractProxySpider newSpider = null;
			for (int i = 0; i < 3; i++) {
				try {
					newSpider = clazz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cacheThreadPool.execute(newSpider);

				try {
					Thread.sleep(rand.nextInt(5000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return;
		}

		cacheThreadPool.execute(proxySpider);
	}

	/**
	 * 
	 * @Title: selectSpider
	 * 
	 * @Description: 检查proxy来源，尽量不用某个proxy去爬它的来源站。
	 * 
	 * @param p
	 * @return
	 * 
	 * @return: int
	 * 
	 */
	private int selectSpider(Proxy p) {
		int randKey = -1;
		if (p != null) {
			int id = p.getProxySpiderId();
			List<Possiblity> list = new ArrayList<>();
			Set<Entry<Integer, AbstractProxySpider>> entrySet = proxyMap.entrySet();

			for (Entry<Integer, AbstractProxySpider> entry : entrySet) {
				if (entry.getKey() != id) {
					Possiblity psb = new Possiblity();
					psb.setProxySpiderId(entry.getKey());
					psb.setPossiblity(entry.getValue().getPossiblity());
					list.add(psb);
				}
			}
			// 重新按照概率比在排除了源站的spider列表中随机选取proxy spider
			randKey = genRandKey(list);
		}

		// 如果返回-1 则随机选取一个
		if (randKey == -1) {
			Integer[] arr = proxyMap.keySet().toArray(new Integer[0]);
			randKey = arr[rand.nextInt(arr.length)];
		}
		return randKey;
	}

	/**
	 * 
	 * @Title: genRandKey
	 * 
	 * @Description: list中的元素都指定了概率，根据概率随机选取List中的一个id。
	 * 
	 * @param psbList
	 * @return
	 * 
	 * @return: int
	 * 
	 */
	private int genRandKey(List<Possiblity> psbList) {

		double sum = 0.0;

		for (Possiblity p : psbList) {
			sum += p.getPossiblity();
		}

		for (Possiblity p : psbList) {
			p.setPossiblity(p.getPossiblity() * 100 / sum);
		}

		int randInt = rand.nextInt(100);

		double psum = 0.0;
		for (Possiblity p : psbList) {
			psum += p.getPossiblity();
			if (randInt < psum) {
				return p.getProxySpiderId();
			}
		}
		// 由于精度问题不能完全保证不等式一定涵盖所有情况
		return -1;
	}

	private class Possiblity {
		private int proxySpiderId;
		private double possiblity;

		public int getProxySpiderId() {
			return proxySpiderId;
		}

		public void setProxySpiderId(int proxySpiderId) {
			this.proxySpiderId = proxySpiderId;
		}

		public double getPossiblity() {
			return possiblity;
		}

		public void setPossiblity(double possiblity) {
			this.possiblity = possiblity;
		}

		@Override
		public String toString() {
			return "Possiblity [proxySpiderId=" + proxySpiderId + ", possiblity=" + possiblity + "]";
		}

	}
}
