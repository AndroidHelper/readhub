/**  
 
 * @Title: SpiderExceptionHandler.java

 * @Package: cn.john.hub.util

 * @Description: TODO

 * @author: John  

 * @date: 2017年12月1日 下午2:54:58

 * @version: V1.0  

 */
package cn.john.hub.util;

import java.lang.Thread.UncaughtExceptionHandler;

/**

 * @ClassName: SpiderExceptionHandler

 * @Description: TODO

 * @author: John

 * @date: 2017年12月1日 下午2:54:58


 */
public class SpiderExceptionHandler implements UncaughtExceptionHandler{

	/* (non Javadoc)
	
	 * @Title: uncaughtException
	
	 * @Description: TODO
	
	 * @param t
	 * @param e
	
	 * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
	
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		
	}

}
