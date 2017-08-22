/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: NewsService.java

 * @Prject: Test3

 * @Package: cn.john.hub.service

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午6:55:48

 * @version: V1.0  

 */
package cn.john.hub.service;

import java.util.List;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.NewsVO;

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
	void saveNews(List<NewsDO> newsList);

	List<NewsVO> listNews();
}
