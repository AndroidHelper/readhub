/**  
 
 * @Title: NewsContainer.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年11月29日 下午3:27:55

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.service.NewsService;

/**
 * 
 * @ClassName: NewsContainer
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年11月29日 下午3:27:55
 * 
 * 
 */
@Component
public class NewsContainer {

	@Autowired
	private NewsService nService;

	private static volatile NewsContainer container;

	private ReentrantLock lock = new ReentrantLock();

	private List<NewsDO> newsList = new LinkedList<NewsDO>();

	public static NewsContainer getInstance() {
		if (container == null) {
			synchronized (NewsContainer.class) {
				if (container == null) {
					container = new NewsContainer();
				}
			}
		}
		return container;
	}

	public void putAll(List<NewsDO> newsList) {
		lock.lock();
		try {
			newsList.addAll(newsList);
		} finally {
			lock.unlock();
		}
	}

	public void saveAll() {
		lock.lock();
		try {
			nService.saveNews(newsList);
			newsList.clear();
		} finally {
			lock.unlock();
		}
	}

}
