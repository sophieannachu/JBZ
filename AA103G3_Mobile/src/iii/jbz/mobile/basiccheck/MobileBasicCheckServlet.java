package iii.jbz.mobile.basiccheck;

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

import iii.jbz.mobile.bgcheck.BGCheckService;
import iii.jbz.mobile.bgcheck.BGCheckVO;
import iii.jbz.mobile.bpcheck.BPCheckService;
import iii.jbz.mobile.bpcheck.BPCheckVO;
import iii.jbz.mobile.sleepcheck.SleepCheckService;
import iii.jbz.mobile.sleepcheck.SleepCheckVO;
import iii.jbz.server.pedometer.Pedometer;

@SuppressWarnings("serial")
@WebServlet("/MobileBasicCheckServlet")
public class MobileBasicCheckServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String memno = request.getParameter("memno");
		Gson gson = new Gson();
		BasicCheckService basicCheckService = new BasicCheckService();
		if (action.equals("getAllbymemno")){
			List<BasicCheckVO> basicCheckVOList = basicCheckService.getAllByMemBasicCheck(Integer.parseInt(memno));
			writeText(response, gson.toJson(basicCheckVOList));
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
		BasicCheckService basicCheckService = new BasicCheckService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAllbymemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<BasicCheckVO> basicCheckVOList = basicCheckService.getAllByMemBasicCheck(memno);
			writeText(response, gson.toJson(basicCheckVOList));
		}
		if (action.equals("basicCheckInsert")){
			String basicCheckJson = jsonObject.get("basicCheck").getAsString();
			BasicCheckVO basicCheckVO = gson.fromJson(basicCheckJson, BasicCheckVO.class);
			int count = 0;
			count = basicCheckService.addBasicCheck(basicCheckVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("basicCheckUpdate")){
			String basicCheckJson = jsonObject.get("basicCheck").getAsString();
			BasicCheckVO basicCheckVO = gson.fromJson(basicCheckJson, BasicCheckVO.class);
			int count = 0;
			count = basicCheckService.updateBasicCheck(basicCheckVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("basicCheckDelete")){
			int checkno = jsonObject.get("checkno").getAsInt();
			int count = 0;
			count = basicCheckService.deleteBasicCheck(checkno);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("healthyCheckGetAllByMemno")){
			BPCheckService bpCheckService = new BPCheckService();
			BGCheckService bgCheckService = new BGCheckService();
			SleepCheckService sleepCheckService = new SleepCheckService();
			JsonObject jsonObjectOut = new JsonObject();
			int memno = jsonObject.get("memno").getAsInt();
			List<BasicCheckVO> basicCheckVOList = basicCheckService.getAllByMemBasicCheck(memno);
			List<BPCheckVO> bpCheckVOList =  bpCheckService.getAllByMemBPCheck(memno);
			List<BGCheckVO> bgCheckVOList = bgCheckService.getAllByMemBGCheck(memno);
			List<SleepCheckVO> sleepCheckVOList = sleepCheckService.getAllByMemSleepCheck(memno);
			jsonObjectOut.addProperty("basicCheckVOList", gson.toJson(basicCheckVOList));
			jsonObjectOut.addProperty("bpCheckVOList", gson.toJson(bpCheckVOList));
			jsonObjectOut.addProperty("bgCheckVOList", gson.toJson(bgCheckVOList));
			jsonObjectOut.addProperty("sleepCheckVOList", gson.toJson(sleepCheckVOList));
			writeText(response, jsonObjectOut.toString());
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
