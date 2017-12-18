/**  
 
 * @Title: HotSwapHandler.java

 * @Package: cn.john.hub.spider

 * @Description: TODO

 * @author: John  

 * @date: 2017年12月18日 下午3:30:27

 * @version: V1.0  

 */
package cn.john.hub.spider;

import java.io.File;
import java.util.HashMap;

/**
 * 
 * @ClassName: HotSwapHandler
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年12月18日 下午3:30:27
 * 
 * 
 */
public class HotSwapHandler {
	private void scanChange() {
		HashMap<File, Long> map = new HashMap<File, Long>();
		File dir = new File("");
		for (File f : dir.listFiles()) {
			map.put(f, f.lastModified());
		}
		
	}
}
