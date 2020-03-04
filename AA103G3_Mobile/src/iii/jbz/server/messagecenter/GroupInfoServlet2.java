package iii.jbz.server.messagecenter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.InflaterInputStream;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;



@SuppressWarnings("serial")
@WebServlet("/GroupInfoServlet2")
// @MultipartConfig(fileSizeThreshold = 500000000*1024 * 1024, maxFileSize =
// 500000000 * 1024 * 1024, maxRequestSize = 500000000 * 5 * 1024 * 1024)
public class GroupInfoServlet2 extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	final Base64.Encoder encoder = Base64.getEncoder();
	final Base64.Decoder decoder = Base64.getDecoder();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GroupInfoService groupInfoSvc = new GroupInfoService();
		// req.setCharacterEncoding("UTF-8");
		// String action = req.getParameter("action");

		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);

		GroupInfoDAO_interface groupinfoDao = new GroupInfoDAO();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAll")) {
			int memno = jsonObject.get("memno").getAsInt();
			List<GroupInfoVO> groupinfoVOs = groupinfoDao.getAll(memno);
			writeText(response, gson.toJson(groupinfoVOs));
		} else {
			writeText(response, "");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		System.out.println("outText: " + outText);
		out.print(outText);
	}
}
