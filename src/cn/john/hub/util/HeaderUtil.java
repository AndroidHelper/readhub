/**  
 
 * @Title: HeaderUtil.java

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年11月29日 下午1:07:32

 * @version: V1.0  

 */
package cn.john.hub.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

/**
 * 
 * @ClassName: HeaderUtil
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年11月29日 下午1:07:32
 * 
 * 
 */
public class HeaderUtil {

	public static final List<Header> getBrowserLikeHeaders() {
		List<Header> list = new ArrayList<Header>(10);
		list.add(new BasicHeader(HttpHeaders.ACCEPT, HubConsts.ACCEPT));
		list.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, HubConsts.ACCEPT_ENCODING));
		list.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, HubConsts.ACCEPT_LANGUAGE));
		list.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, HubConsts.CACHE_CONTROL));
		list.add(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, HubConsts.ACCEPT_CHARSET));
		list.add(new BasicHeader("DNT", HubConsts.DNT));
		list.add(new BasicHeader(HttpHeaders.USER_AGENT, HubConsts.USER_AGENT));
		return list;
	}

	public static final List<Header> getNaiveAuthHeaders() {
		List<Header> list = new ArrayList<Header>(5);
		list.add(new BasicHeader(HttpHeaders.AUTHORIZATION, HubConsts.AUTHORIZATION));
		list.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, HubConsts.CONTENT_TYPE));
		return list;
	}
}
