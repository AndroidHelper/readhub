/**  
 
 * @Title: MonitorController.java

 * @Package: cn.john.hub.controller

 * @Description: TODO

 * @author: John  

 * @date: 2017年9月22日 下午8:04:11

 * @version: V1.0  

 */
package cn.john.hub.controller;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.john.hub.dao.AccessMapper;
import cn.john.hub.domain.Heartbeat;
import cn.john.hub.domain.Visitor;
import cn.john.hub.service.AccessService;

/**
 * 
 * @ClassName: MonitorController
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年9月22日 下午8:04:11
 * 
 * 
 */
@Controller
public class MonitorController {
	@Autowired
	private AccessService aService;
	@Autowired
	private Heartbeat hb;

	@RequestMapping("/monitor")
	public String monitor() {
		return "monitor";
	}

	@RequestMapping("/listMonitorItems")
	@ResponseBody
	public HashMap<String, Object> listMonitorItems() {
		HashMap<String, Object> map = new HashMap<>();
		List<Visitor> ipList = aService.listAccessRecordToday();
		if (ipList.size() > 0) {
			map.put("visitor", ipList);
		}
		map.put("nsq", hb.getNewsSpiderExeQueueInfo());
		map.put("nsp", hb.getNewsSpiderPoolInfo());
		map.put("psp", hb.getProxySpiderPoolInfo());
		map.put("lsnc", hb.getLastSavedNewsCount());

		long now = System.currentTimeMillis();

		if ((now - hb.getNewsSpiderBeat()) / 1000 > 60) {
			map.put("nsd", "Unknown");
		} else {
			map.put("nsd", "Running");
		}
		if ((now - hb.getProxySpiderBeat()) / 1000 > 60) {
			map.put("psd", "Unknown");
		} else {
			map.put("psd", "Running");
		}
		if ((now - hb.getIpLocaterBeat()) / 1000 > 300) {
			map.put("ip", "Unknown");
		} else {
			map.put("ip", "Running");
		}
		return map;
	}

	@RequestMapping("/checkClasses")
	@ResponseBody
	public String chekcClasses() {
		ClassLoader loader = this.getClass().getClassLoader();
		String thisFolder = this.getClass().getResource("").getFile();
		String newsFolder = thisFolder.replaceAll("controller", "spider")+"news/";
		File dir = new File(newsFolder.substring(1, newsFolder.length()));
		for(File f:dir.listFiles()){
			try {
				Class<?> clazz = loader.loadClass("cn.john.hub.spider.news.Test");
				Method[] method = clazz.getMethods();
				for(Method m:method){
					System.out.println(m.getName());
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		String thisFolder = MonitorController.class.getResource("").getFile();
		String newsFolder = thisFolder.replaceAll("controller", "spider")+"news/";
		File dir = new File(newsFolder.substring(1, newsFolder.length()));
		for(File f:dir.listFiles()){
			
		}
	}
}
