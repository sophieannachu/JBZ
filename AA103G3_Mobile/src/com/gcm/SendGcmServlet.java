package com.gcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.friend.model.*;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import idv.ron.server.mem.Mem;
import idv.ron.server.mem.MemDaoOracleImpl;

import static com.gcm.MyData.*;

/*
 see 
 http://developer.android.com/google/gcm/server.html#params
 http://developer.android.com/google/gcm/adv.html#collapsible
 */
@SuppressWarnings("serial")
public class SendGcmServlet extends HttpServlet {
	
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private ServletContext context;
	private static Executor threadPool;

	@Override
	public void init() throws ServletException {
		super.init();
		context = getServletContext();
		int numberOfProcessors = Runtime.getRuntime().availableProcessors();
		threadPool = Executors.newFixedThreadPool(numberOfProcessors);
	}

	@Override
	protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		doPost(rq, rp);
	}

	@Override
	protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		List<String> devicesSend = new ArrayList<String>();
		rq.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = rq.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		MemDaoOracleImpl memDao = new MemDaoOracleImpl();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);
		// 取得發出SOS的memVO還有其位置
		Integer memno = Integer.parseInt(jsonObject.get("memno").getAsString());
		Double fromLat = Double.parseDouble(jsonObject.get("fromLat").getAsString());
		Double fromLng = Double.parseDouble(jsonObject.get("fromLng").getAsString());
		Mem memVO = memDao.findByMemno(memno);
		// 取得發出SOS者的好友的regId，並加到devicesSend
		FriendService friendSvc = new FriendService();
		List<FriendVO> list = friendSvc.getAllByMemFriend(memno, "1");
		for (FriendVO friendVO : list) {
			String regId = memDao.findByMemno(friendVO.getFrino()).getRegId();
			if (regId != null) {
				devicesSend.add(regId);
			}
		}
		if ("sendForHelp".equals(action)) {
			JSONObject obj = new JSONObject();
			obj.put("devicesSend", devicesSend);
			obj.put("fromLat", fromLat);
			obj.put("fromLng", fromLng);
//			obj.put("memName", memVO.getName());
			obj.put("memno", memno);
			obj.put("action", "sendForHelp");
//			System.out.println(memVO.getName());
			String jsonStr = obj.toString();
			if (devicesSend.size() > 0) {
				asyncSend(devicesSend, jsonStr);
				writeText(rp, "已發出救援!!!");
			} else {
				writeText(rp, "沒有人可以求救，快打119!!!");
			}
		}

		if ("sendGoHelp".equals(action)) {
//			取得要前往解救的memeno和其所在位置
			
			Integer soserno = Integer.parseInt(jsonObject.get("soser").getAsString());
			Double localLat = Double.parseDouble(jsonObject.get("localLat").getAsString());
			Double localLng = Double.parseDouble(jsonObject.get("localLng").getAsString());
//			取的要前往解救的memVO
			Mem soserVO = memDao.findByMemno(soserno);

			JSONObject obj = new JSONObject();
			obj.put("regId", soserVO.getRegId());
			obj.put("fromLat", fromLat);
			obj.put("fromLng", fromLng);
			obj.put("localLat", localLat);
			obj.put("localLng", localLng);
			obj.put("memno", memno);
//			obj.put("memName", memVO.getName());
			obj.put("soserno", soserno);
			obj.put("action", "sendGoHelp");
			String jsonStr = obj.toString();
			if (devicesSend.size() > 0) {
				asyncSend(devicesSend, jsonStr);
			} else {
				return;
			}

		}

		// String msgDeviceNotSelected = "";
		// String msgSendResult = "";
		// List<String> devicesSend = new ArrayList<String>();
		//// String[] regIds_send = rq.getParameterValues("devicesSend");
		// String[] regIds_send = rq.getParameterValues("regId");
		//
		// String latlng = rq.getParameter("latlng");
		// if (regIds_send == null || regIds_send.length <= 0) {
		// msgDeviceNotSelected = "No device is selected!";
		// } else {
		// devicesSend = Arrays.asList(regIds_send);
		// // send a multicast message using JSON
		// // must split in chunks of 1000 devices (GCM limit)
		// int total = devicesSend.size();
		// List<String> partialDevices = new ArrayList<String>(total);
		// int counter = 0;
		// int tasks = 0;
		// for (String device : devicesSend) {
		// counter++;
		// partialDevices.add(device);
		// int partialSize = partialDevices.size();
		// // send messages when the number of devices >= 1000 or
		// // >= the total amount of devices to be sent
		// if (partialSize >= MULTICAST_SIZE || counter >= total) {
		// asyncSend(partialDevices, latlng);
		// partialDevices.clear();
		// tasks++;
		// }
		// }
		// msgSendResult = "Asynchronously sending " + tasks
		// + " multicast messages to " + total + " device(s)";
		// }
	}

	private void asyncSend(List<String> devicesSend, final String jsonStr) {
		threadPool.execute(new Runnable() {
			public void run() {
				Message message = new Message.Builder().addData("jsonStr", jsonStr).build();
				MulticastResult multicastResult;
				Sender sender = new Sender(SERVER_KEY);
				try {
					// after sending a message to many devices,
					// GCM server will return MulticastResult storing each
					// result for a device
					multicastResult = sender.send(message, devicesSend, 5);
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
				List<Result> results = multicastResult.getResults();
				// analyze the results
				for (int i = 0; i < devicesSend.size(); i++) {
					String regId = devicesSend.get(i);
					Result result = results.get(i);
					String messageId = result.getMessageId();
					if (messageId != null) {
						System.out
								.println("Succesfully sent message to device: " + regId + "; messageId = " + messageId);
						String canonicalRegId = result.getCanonicalRegistrationId();
						if (canonicalRegId != null) {
							// same device has more than one registration id:
							// update it
							// see
							// https://developer.android.com/google/gcm/adv.html#canonical
							System.out.println("canonicalRegId: " + canonicalRegId);
							MyData.updateRegistration(regId, canonicalRegId);
							System.out.println("Updating " + regId + " to " + canonicalRegId);
						}
					} else {
						String error = result.getErrorCodeName();
						// the user has uninstalled the Android app or turned
						// off notifications. Sender should stop sending
						// messages to this device and delete the registration
						// id.
						if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
							devicesSend.remove(regId);
							System.out.println("Unregistered device: " + regId);
						} else {
							System.out.println("Error sending message to " + regId + ": " + error);
						}
					}
				}
			}
		});
	}
	
	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		 System.out.println("outText: " + outText);
		out.print(outText);
	}
}
