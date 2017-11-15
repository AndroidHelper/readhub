/**  
 
 * @Title: Spider.java

 * @Package: cn.john.hub.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年10月13日 下午3:38:58

 * @version: V1.0  

 */
package cn.john.hub.domain;

import java.util.List;
import java.util.concurrent.Callable;

import cn.john.hub.util.HttpClient;

/**
 * 
 * @ClassName: Spider
 * 
 * @Description: TODO
 * 
 * @author: John
 * @param <T>
 * 
 * @date: 2017年10月13日 下午3:38:58
 * 
 * 
 */
public interface Spider<T> extends Callable<List<T>> {
	
	void setHttpClient(HttpClient httpClient);

	String getSite();
}
