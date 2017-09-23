/**  
 
 * @Title: AccessService.java

 * @Package: cn.john.hub.service

 * @Description: TODO

 * @author: John  

 * @date: 2017年9月22日 下午4:05:06

 * @version: V1.0  

 */
package cn.john.hub.service;

import java.util.List;

import cn.john.hub.domain.Visitor;

/**
 * 
 * @ClassName: AccessService
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年9月22日 下午4:05:06
 * 
 * 
 */
public interface AccessService {
	List<Visitor> listAccessRecordToday();
}
