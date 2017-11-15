/**  
 
 * @Title: HttpClientException.java

 * @Package: cn.john.hub.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年10月13日 下午3:27:40

 * @version: V1.0  

 */
package cn.john.hub.domain;

/**
 * 
 * @ClassName: HttpClientException
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年10月13日 下午3:27:40
 * 
 * 
 */
public class HttpClientException extends Exception {

	/**
	 * 
	 * @fieldName: serialVersionUID
	 * 
	 * @fieldType: long
	 * 
	 * @Description: TODO
	 * 
	 */
	private static final long serialVersionUID = 3950732397625062959L;

	public HttpClientException() {
		super();
	}

	public HttpClientException(String msg) {
		super(msg);
	}

	public HttpClientException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public HttpClientException(Throwable cause) {
		super(cause);
	}

}
