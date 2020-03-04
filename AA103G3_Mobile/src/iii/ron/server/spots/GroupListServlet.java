package iii.ron.server.spots;

import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.util.zip.InflaterInputStream;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import idv.ron.server.main.ImageUtil;

@SuppressWarnings("serial")
@WebServlet("/GroupListServlet")
//@MultipartConfig(fileSizeThreshold = 500000000*1024 * 1024, maxFileSize = 500000000 * 1024 * 1024, maxRequestSize = 500000000 * 5 * 1024 * 1024)
public class GroupListServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	    final Base64.Encoder encoder = Base64.getEncoder();
		final Base64.Decoder decoder = Base64.getDecoder();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		GroupInfoService groupInfoSvc = new GroupInfoService();
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
		
		
		
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
		GroupListDAO_interface grouplistDao = new GroupListDAO();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("INSERT_GroupList")) {
			int group_no = jsonObject.get("group_no").getAsInt();
			int memno = jsonObject.get("memno").getAsInt();
			int count = 0;
			count = grouplistDao.insert(group_no, memno);
			writeText(response, gson.toJson(count));
		} 
		if (action.equals("DELETE_GroupList")){
			int group_no = jsonObject.get("group_no").getAsInt();
			int memno = jsonObject.get("memno").getAsInt();
			int count = 0;
			count = grouplistDao.delete(group_no, memno);
			writeText(response, gson.toJson(count));
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		System.out.println("outText: " + outText);
		out.print(outText);
	}
}
		
