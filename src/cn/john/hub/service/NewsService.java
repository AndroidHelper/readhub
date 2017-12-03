/**  

 * @Title: NewsService.java

 * @Package: cn.john.hub.service

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午6:55:48

 * @version: V1.0  

 */
package cn.john.hub.service;

import java.util.List;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.NewsSpiderRecord;
import cn.john.hub.domain.NewsVO;
import cn.john.hub.spider.AbstractNewsSpider;

/**
 * 
 * @ClassName: NewsService
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午6:55:48
 * 
 * 
 */
public interface NewsService {
	boolean saveNews(List<NewsDO> newsList, AbstractNewsSpider ans);

	List<NewsVO> listNews();
	
	List<NewsSpiderRecord> listNewsStatis();
}
