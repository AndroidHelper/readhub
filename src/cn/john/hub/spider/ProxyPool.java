/**  
 
 * @Title: ProxyPool.java

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年11月29日 上午10:36:52

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;

import cn.john.hub.domain.Proxy;
import cn.john.hub.util.LogUtil;

/**
 * 
 * @ClassName: ProxyPool
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年11月29日 上午10:36:52
 * 
 * 
 */
public class ProxyPool {

	private static volatile ProxyPool proxyPool;

	private LinkedBlockingDeque<Proxy> pool = new LinkedBlockingDeque<Proxy>();
	private ReentrantLock lock = new ReentrantLock();
	private Condition notEnough = lock.newCondition();
	private ExecutorService ste = Executors.newSingleThreadExecutor();
	private ProxySpiderDispatcher psd = new ProxySpiderDispatcher();

	private ProxyPool() {
		init();
	}

	/**
	 * 
	 * @Title: getInstance
	 * 
	 * @Description: 可以将ProxyPool交给spring管理，这里学习下双重检查锁定创建单例
	 * 
	 * @return
	 * 
	 * @return: ProxyPool
	 * 
	 */
	public static ProxyPool getInstance() {
		if (proxyPool == null) {
			synchronized (ProxyPool.class) {
				if (proxyPool == null) {
					proxyPool = new ProxyPool();
				}
			}
		}
		return proxyPool;
	}

	private void init() {
		LogUtil.getSpiderLogger().info("Pool initiation...Lack of proxy!Triggering proxy spider...");
		ste.submit(psd);
	}

	/**
	 * 
	 * @Title: put
	 * 
	 * @Description: 向代理池添加代理，如果池满则等待直到可以添加
	 * 
	 * @param proxy
	 * 
	 * @return: void
	 * 
	 */
	public void offerFirst(Proxy proxy) {
		pool.offerFirst(proxy);
	}

	public void putAll(List<Proxy> proxyList) {
		pool.addAll(proxyList);
	}

	public Proxy poll() {
		return pool.poll();
	}

	public Proxy peek() {
		return pool.peek();
	}

	/**
	 * 
	 * @Title: getAndWaitAt
	 * 
	 * @Description: 当代理数量少于size时等待
	 * 
	 * @param size
	 * @return
	 * 
	 * @return: Proxy
	 * 
	 */
	public Proxy get() {
		if (pool.size() > 30) {
			return pool.poll();
		}
		lock.lock();
		try {
			if (pool.size() < 20) {
				LogUtil.getSpiderLogger()
						.info("Pool size is " + pool.size() + " Lack of proxy!Triggering proxy spider and wait!");
				ste.submit(psd);
				notEnough.awaitUninterruptibly();
				LogUtil.getSpiderLogger().info("Pool size is " + pool.size() + " Unpark and carry on!");
			}
		} finally {
			lock.unlock();
		}
		return pool.poll();
	}

	public int size() {
		return pool.size();
	}

	public void signalEnough() {
		lock.lock();
		try {
			LogUtil.getSpiderLogger().info("Signal proxy enough!And " + "pool size is " + pool.size());
			notEnough.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
