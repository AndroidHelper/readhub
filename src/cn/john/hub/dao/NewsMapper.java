/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: NewsMapper.java

 * @Prject: Test3

 * @Package: cn.john.hub.dao

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午6:54:33

 * @version: V1.0  

 */
package cn.john.hub.dao;

import java.util.List;

import cn.john.hub.domain.NewsDO;

/**

 * @ClassName: NewsMapper

 * @Description: TODO

 * @author: John

 * @date: 2017年6月20日 下午6:54:33


 */
public interface NewsMapper {
	void saveNews(List<NewsDO> newsList);
}
