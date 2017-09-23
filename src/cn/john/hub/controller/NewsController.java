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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.john.hub.domain.AccRcdDO;
import cn.john.hub.domain.Heartbeat;
import cn.john.hub.domain.NewsVO;
import cn.john.hub.domain.Visitor;
import cn.john.hub.service.AccessService;
import cn.john.hub.service.NewsService;
import cn.john.hub.spider.Queue;

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

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/news")
	@ResponseBody
	public List<NewsVO> getNewsJsp(HttpServletRequest req) {
		AccRcdDO acc = new AccRcdDO();
		acc.setIp(req.getRemoteAddr());
		Queue.accQueue.add(acc);
		return nService.listNews();
	}
}
