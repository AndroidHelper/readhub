/**  
 * @Title: NewsController.java

 * @Package: cn.john.hub.controller

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月22日 下午5:06:31

 * @version: V1.0  

 */
package cn.john.hub.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.john.hub.domain.Heartbeat;
import cn.john.hub.domain.NewsVO;
import cn.john.hub.service.NewsService;

/**
 * 
 * @ClassName: NewsController
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年8月22日 下午5:06:31
 * 
 * 
 */
@Controller
public class NewsController {
	@Autowired
	private NewsService nService;
	@Autowired
	private Heartbeat hb;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/news")
	@ResponseBody
	public List<NewsVO> getNewsJsp() {
		return nService.listNews();
	}

	@RequestMapping("/monitor")
	public String monitor() {
		return "monitor";
	}

	@RequestMapping("/listMonitorItems")
	@ResponseBody
	public HashMap<String, Integer> listMonitorItems() {
		HashMap<String, Integer> map = new HashMap<>();
		long now = System.currentTimeMillis();
		if ((now - hb.getNewsSaverBeat()) / 1000 > 180) {
			map.put("ns", 0);
		} else {
			map.put("ns", 1);
		}
		if ((now - hb.getNewsSpiderBeat()) / 1000 > 60) {
			map.put("nsd", 0);
		} else {
			map.put("nsd", 1);
		}
		if ((now - hb.getProxySpiderBeat()) / 1000 > 60) {
			map.put("psd", 0);
		} else {
			map.put("psd", 1);
		}
		return map;
	}
}
