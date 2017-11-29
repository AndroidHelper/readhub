/**  

 * @Title: Queue.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年7月24日 上午9:52:37

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import cn.john.hub.domain.AccRcdDO;
import cn.john.hub.domain.NewsDO;

/**
 * 
 * @ClassName: Queue
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年7月24日 上午9:52:37
 * 
 * 
 */
public class Queue {
	public static ConcurrentLinkedQueue<AccRcdDO> accQueue = new ConcurrentLinkedQueue<AccRcdDO>();
}
