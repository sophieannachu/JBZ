package iii.jbz.mobile.sleepcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import iii.jbz.server.pedometer.Pedometer;

@SuppressWarnings("serial")
@WebServlet("/MobileSleepCheckServlet")
public class MobileSleepCheckServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String memno = request.getParameter("memno");
		Gson gson = new Gson();
		SleepCheckService sleepCheckService = new SleepCheckService();
		if (action.equals("getAllbymemno")){
			List<SleepCheckVO> sleepCheckVOList = sleepCheckService.getAllByMemSleepCheck(Integer.parseInt(memno));
			writeText(response, gson.toJson(sleepCheckVOList));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		SleepCheckService sleepCheckService = new SleepCheckService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAllbymemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<SleepCheckVO> sleepCheckVOList = sleepCheckService.getAllByMemSleepCheck(memno);
			writeText(response, gson.toJson(sleepCheckVOList));
		}
		if (action.equals("sleepCheckInsert")){
			String sleepCheckJson = jsonObject.get("sleepCheck").getAsString();
			SleepCheckVO sleepCheckVO = gson.fromJson(sleepCheckJson, SleepCheckVO.class);
			int count = 0;
			count = sleepCheckService.addSleepCheck(sleepCheckVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("sleepCheckUpdate")){
			String sleepCheckJson = jsonObject.get("sleepCheck").getAsString();
			SleepCheckVO sleepCheckVO = gson.fromJson(sleepCheckJson, SleepCheckVO.class);
			int count = 0;
			count = sleepCheckService.updateSleepCheck(sleepCheckVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("sleepCheckDelete")){
			int checkno = jsonObject.get("checkno").getAsInt();
			int count = 0;
			count = sleepCheckService.deleteSleepCheck(checkno);
			writeText(response, String.valueOf(count));
			
		}
		
	}

	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		System.out.println("outText: " + outText);
		out.print(outText);
	}
}
