/**  
 
 * @Title: WebSocketController.java

 * @Package: cn.john.hub.controller

 * @Description: TODO

 * @author: John  

 * @date: 2017年11月23日 下午6:25:59

 * @version: V1.0  

 */
package cn.john.hub.controller;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**

 * @ClassName: WebSocketController

 * @Description: TODO

 * @author: John

 * @date: 2017年11月23日 下午6:25:59


 */
@ServerEndpoint("/newsPusher")
public class WebSocketController {
	
	@OnOpen
	public void test(Session session) throws IOException{
		session.getBasicRemote().sendText("");
	}
}
