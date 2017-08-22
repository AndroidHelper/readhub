/**  

 * Copyright © 2017SITI. All rights reserved.

 *

 * @Title: NewsController.java

 * @Prject: Test3

 * @Package: cn.john.hub.controller

 * @Description: TODO

 * @author: John  

 * @date: 2017年8月22日 下午5:06:31

 * @version: V1.0  

 */
package cn.john.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping("/index")
	public String index(){
		System.out.println("....");
		return "index";
	}
	
	@RequestMapping("/news")
	@ResponseBody
	public List<NewsVO> getNewsJsp() {
		return nService.listNews();
	}
}
