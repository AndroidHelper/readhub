/**  
 
 * @Title: AccessMapper.java

 * @Package: cn.john.hub.dao

 * @Description: TODO

 * @author: John  

 * @date: 2017年9月22日 下午4:02:52

 * @version: V1.0  

 */
package cn.john.hub.dao;

import java.util.List;

import cn.john.hub.domain.AccRcdDO;
import cn.john.hub.domain.Visitor;

/**
 * 
 * @ClassName: AccessMapper
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年9月22日 下午4:02:52
 * 
 * 
 */
public interface AccessMapper {
	
	void saveAccessRecord(String ip);

	List<Visitor> listAccessRecordToday();
	
	List<AccRcdDO> listUnLocatedIp();
	
	void updateIpLocation(List<AccRcdDO> accList);
}
