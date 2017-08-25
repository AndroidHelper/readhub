/**  
 * @Title: NewsMapper.java

 * @Package: cn.john.hub.dao

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午6:54:33

 * @version: V1.0  

 */
package cn.john.hub.dao;

import java.util.List;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.NewsVO;

/**
 * 
 * @ClassName: NewsMapper
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午6:54:33
 * 
 * 
 */
public interface NewsMapper {
	
	void saveNews(List<NewsDO> newsList);

	List<NewsVO> listNews();
}
