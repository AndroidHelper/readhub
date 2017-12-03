/**  
 * @Title: NewsServiceImpl.java

 * @Package: cn.john.hub.service.impl

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午6:57:12

 * @version: V1.0  

 */
package cn.john.hub.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.john.hub.dao.NewsMapper;
import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.NewsSpiderRecord;
import cn.john.hub.domain.NewsVO;
import cn.john.hub.service.NewsService;
import cn.john.hub.spider.AbstractNewsSpider;

/**
 * 
 * @ClassName: NewsServiceImpl
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午6:57:12
 * 
 * 
 */
@Service
public class NewsServiceImpl implements NewsService {

	private Logger log = LogManager.getLogger("logger");
	@Autowired
	private NewsMapper nMapper;

	/*
	 * (non Javadoc)
	 * 
	 * @Title: saveNews
	 * 
	 * @Description: TODO
	 * 
	 * @param newsList
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.service.NewsService#saveNews(java.util.List)
	 * 
	 */
	@Override
	public boolean saveNews(List<NewsDO> newsList, AbstractNewsSpider ans) {
		int total = newsList.size();
		int count = 0;
		for (NewsDO n : newsList) {
			if (nMapper.checkUrlIfExists(n.getUrl()) != null) {
				count++;
			}
		}
		NewsSpiderRecord nsr = new NewsSpiderRecord();
		nsr.setTotal(total);
		nsr.setNewsSpiderId(ans.getSpiderId());
		nsr.setRepe_Rate((count * 1.0) / total);
		nsr.setNewItemCount(total - count);
		nMapper.saveNewsSpiderRecord(nsr);
		try {
			nMapper.saveNews(newsList);
			return true;
		} catch (Exception e) {
			log.error("Error saving news!", e.getMessage());
		}
		return false;
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: listNews
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.service.NewsService#listNews()
	 * 
	 */
	@Override
	public List<NewsVO> listNews() {
		return nMapper.listNews();
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: listNewsStatis
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see cn.john.hub.service.NewsService#listNewsStatis()
	 * 
	 */
	@Override
	public List<NewsSpiderRecord> listNewsStatis() {
		// TODO Auto-generated method stub
		return nMapper.listNewsStatis();
	}

}
