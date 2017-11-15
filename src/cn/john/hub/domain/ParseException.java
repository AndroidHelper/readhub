/**  
 
 * @Title: ParseException.java

 * @Package: cn.john.hub.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年10月13日 下午3:29:33

 * @version: V1.0  

 */
package cn.john.hub.domain;

/**
 * 
 * @ClassName: ParseException
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年10月13日 下午3:29:33
 * 
 * 
 */
public class ParseException extends Exception {

	/**
	 * 
	 * @fieldName: serialVersionUID
	 * 
	 * @fieldType: long
	 * 
	 * @Description: TODO
	 * 
	 */
	private static final long serialVersionUID = 6617957054563686541L;

	public ParseException() {
		super();
	}

	public ParseException(String msg) {
		super(msg);
	}

	public ParseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ParseException(Throwable cause) {
		super(cause);
	}
}
