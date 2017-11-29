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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
	private LinkedBlockingQueue<Proxy> pool;
	private ReentrantLock lock = new ReentrantLock();
	private Condition lackCondition = lock.newCondition();

	private ProxyPool() {
		pool = new LinkedBlockingQueue<Proxy>();
	}

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
	public void offer(Proxy proxy) {
		pool.offer(proxy);
	}

	public void putAll(List<Proxy> proxyList) {
		pool.addAll(proxyList);
	}

	/**
	 * 
	 * @Title: get
	 * 
	 * @Description: 从代理池获取代理,如果池为空则等待直到有代理可用
	 * 
	 * @return
	 * 
	 * @return: Proxy
	 * 
	 */
	public Proxy get() {
		lock.lock();
		try {
			if (pool.size() < 20) {
				lackCondition.awaitUninterruptibly();
			}
		} finally {
			lock.unlock();
		}

		Proxy p = null;
		try {
			p = pool.take();
		} catch (InterruptedException e) {
			LogUtil.getSpiderLogger().error(e);
		}
		return p;
	}

	public int size() {
		return pool.size();
	}

	public void signalSufficient() {
		lock.lock();
		try {
			lackCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
