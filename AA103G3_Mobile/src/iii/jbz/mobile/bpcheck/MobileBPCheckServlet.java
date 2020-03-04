package iii.jbz.mobile.bpcheck;

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
@WebServlet("/MobileBPCheckServlet")
public class MobileBPCheckServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String memno = request.getParameter("memno");
		Gson gson = new Gson();
		BPCheckService bpCheckService = new BPCheckService();
		if (action.equals("getAllbymemno")){
			List<BPCheckVO> bpCheckVOList = bpCheckService.getAllByMemBPCheck(Integer.parseInt(memno));
			writeText(response, gson.toJson(bpCheckVOList));
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
		BPCheckService bpCheckService = new BPCheckService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAllbymemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<BPCheckVO> bpCheckVOList = bpCheckService.getAllByMemBPCheck(memno);
			writeText(response, gson.toJson(bpCheckVOList));
		}
		if (action.equals("bpCheckInsert")){
			String bpCheckJson = jsonObject.get("bpCheck").getAsString();
			BPCheckVO bpCheckVO = gson.fromJson(bpCheckJson, BPCheckVO.class);
			int count = 0;
			count = bpCheckService.addBPCheck(bpCheckVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("bpCheckUpdate")){
			String bpCheckJson = jsonObject.get("bpCheck").getAsString();
			BPCheckVO bpCheckVO = gson.fromJson(bpCheckJson, BPCheckVO.class);
			int count = 0;
			count = bpCheckService.updateBPCheck(bpCheckVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("bpCheckDelete")){
			int checkno = jsonObject.get("checkno").getAsInt();
			int count = 0;
			count = bpCheckService.deleteBPCheck(checkno);
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
