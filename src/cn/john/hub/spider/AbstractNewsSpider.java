/**  
 * @Title: NewsSpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年6月20日 下午7:23:46

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.List;

import org.apache.http.Header;

import cn.john.hub.domain.NewsDO;
import cn.john.hub.domain.ParseException;
import cn.john.hub.service.NewsService;
import cn.john.hub.util.BeanUtil;
import cn.john.hub.util.HeaderUtil;
import cn.john.hub.util.HttpClient;
import cn.john.hub.util.HttpClientFactory;

/**
 * 
 * @ClassName: NewsSpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年6月20日 下午7:23:46
 * 
 * 
 */
public abstract class AbstractNewsSpider extends AbstractSpider<NewsDO> {

	public AbstractNewsSpider() {
	}

	@Override
	public void run() {
		super.run();
	}

	protected HttpClient fetchNewHttpClient(String site) {
		ProxyPool pool = fetchProxyPool();
		List<Header> headers = HeaderUtil.getBrowserLikeHeaders();
		return HttpClientFactory.createUsingProxy(site, headers, pool, true);
	}

	@Override
	protected void consume(List<NewsDO> list) {
		BeanUtil.getNewsServiceBean().saveNews(list);
	}

	// 用于调度
	protected abstract int getDelayFactor();

	protected abstract int getSerialNumber();

}
