package iii.jbz.mobile.bgcheck;

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
@WebServlet("/MobileBGCheckServlet")
public class MobileBGCheckServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String memno = request.getParameter("memno");
		Gson gson = new Gson();
		BGCheckService bgCheckService = new BGCheckService();
		if (action.equals("getAllbymemno")){
			List<BGCheckVO> bgCheckVOList = bgCheckService.getAllByMemBGCheck(Integer.parseInt(memno));
			writeText(response, gson.toJson(bgCheckVOList));
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
		BGCheckService bgCheckService = new BGCheckService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAllbymemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<BGCheckVO> bgCheckVOList = bgCheckService.getAllByMemBGCheck(memno);
			writeText(response, gson.toJson(bgCheckVOList));
		}
		if (action.equals("bgCheckInsert")){
			String bgCheckJson = jsonObject.get("bgCheck").getAsString();
			BGCheckVO bgCheckVO = gson.fromJson(bgCheckJson, BGCheckVO.class);
			int count = 0;
			count = bgCheckService.addBGCheck(bgCheckVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("bgCheckUpdate")){
			String bgCheckJson = jsonObject.get("bgCheck").getAsString();
			BGCheckVO bgCheckVO = gson.fromJson(bgCheckJson, BGCheckVO.class);
			int count = 0;
			count = bgCheckService.updateBGCheck(bgCheckVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("bgCheckDelete")){
			int checkno = jsonObject.get("checkno").getAsInt();
			int count = 0;
			count = bgCheckService.deleteBGCheck(checkno);
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
