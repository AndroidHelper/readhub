/**  
 * @Title: AbstractSpider.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月1日 上午9:40:19

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.domain.ParseException;
import cn.john.hub.util.HttpClient;

/**
 * 
 * @ClassName: AbstractSpider
 * 
 * @Description: TODO
 * 
 * @author: John
 * @param <T>
 * 
 * @date: 2017年8月1日 上午9:40:19
 * 
 * 
 */
public abstract class AbstractSpider<T> implements Callable<List<T>> {

	protected static final Logger log = LogManager.getLogger("spider");

	protected AbstractSpider() {
	}

	protected String getHtml(String site) {
		String html = null;
		while (html == null) {
			html = getHttpClient(site).getData();
		}
		return html;
	}

	protected abstract HttpClient getHttpClient(String site);

	protected abstract String site();

	protected abstract List<T> parseHtml(String html) throws ParseException;

	@Override
	public List<T> call() throws ParseException {
		return parseHtml(getHtml(site()));
	}
}
