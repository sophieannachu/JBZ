package com.friend.model;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.mem.model.*;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/AddNotify/{memno}/{myMemno}")
public class MyEchoServer {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());

@OnOpen
	public void onOpen(@PathParam("memno") int memno, @PathParam("myMemno") int myMemno, Session userSession) throws IOException {
		allSessions.add(userSession);
		userSession.getUserProperties().put("myMemno", myMemno); 
		userSession.getUserProperties().put("memno", memno); 
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myMemno + "已連線");
		System.out.println("進去"+memno+"的專頁" );
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		Integer memno = (Integer) userSession.getUserProperties().get("memno");
		Integer myMemno = (Integer) userSession.getUserProperties().get("myMemno");
		String jsonStr=null;
		for (Session session : allSessions) {
			if (session.isOpen()&&memno.equals(session.getUserProperties().get("myMemno"))){
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(myMemno);
				jsonStr = new JSONObject(memVO).toString();
				
				session.getAsyncRemote().sendText(jsonStr);
			}
				
		}
		FriendService friendSvc = new FriendService();
		friendSvc.addFriend(myMemno, memno, new java.sql.Date(System.currentTimeMillis()),"0");
		System.out.println("Message received: " + jsonStr);
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
