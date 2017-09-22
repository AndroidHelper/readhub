/**  
 
 * @Title: AccRcdDO.java

 * @Package: cn.john.hub.domain

 * @Description: TODO

 * @author: John  

 * @date: 2017年9月22日 下午6:09:36

 * @version: V1.0  

 */
package cn.john.hub.domain;

/**
 * 
 * @ClassName: AccRcdDO
 * 
 * @Description: TODO
 * 
 * @author: John
 * 
 * @date: 2017年9月22日 下午6:09:36
 * 
 * 
 */
public class AccRcdDO {
	private Integer id;
	private String ip;
	private String time;
	private String province;
	private String city;
	private String rectangle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRectangle() {
		return rectangle;
	}

	public void setRectangle(String rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public String toString() {
		return "AccRcdDO [id=" + id + ", ip=" + ip + ", time=" + time + ", province=" + province + ", city=" + city
				+ ", rectangle=" + rectangle + "]";
	}
	
	

}
