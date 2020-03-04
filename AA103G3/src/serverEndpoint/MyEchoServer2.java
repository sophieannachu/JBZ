package serverEndpoint;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

//@ServerEndpoint("/MyEchoServer/{myName}/{sendMem}")
@ServerEndpoint("/MyEchoServer2/{room}")
public class MyEchoServer2 {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen( @PathParam("room") String room, Session userSession) throws IOException {
		allSessions.add(userSession);
//		String myName2 = new String(myName.getBytes("ISO-8859-1"),"utf-8");
//		String sendMem2 = new String(room.getBytes("ISO-8859-1"),"utf-8");
		String room1 = new String(room.getBytes("ISO-8859-1"),"utf-8");
//		System.out.println(myName);
		userSession.getUserProperties().put("room", room1);
//		userSession.getUserProperties().put("myName", myName2);
		System.out.println("已連線至ROOM " + room1);
		System.out.println(userSession.getId() + ": 已連線");
//		System.out.println(myName2 + ": 已連線");
		System.out.println(room1 + ": 房號");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		String room = (String) userSession.getUserProperties().get("room");
//		String myName = (String) userSession.getUserProperties().get("myName");
	
			for (Session session : allSessions) {
				if (session.isOpen()&&room.equals(session.getUserProperties().get("room")))
//					System.out.println(message);
					session.getAsyncRemote().sendText(message);
//				if (session.isOpen()&&sendMem.equals(session.getUserProperties().get("myName"))&&myName.equals(session.getUserProperties().get("sendMem")))
//					session.getAsyncRemote().sendText(message);
//				if (session.isOpen()&&myName.equals(session.getUserProperties().get("myName"))&&sendMem.equals(session.getUserProperties().get("sendMem")))
//					session.getAsyncRemote().sendText(message);
			}
	
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
