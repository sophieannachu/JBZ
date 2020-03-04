package iii.jbz.server.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import iii.jbz.server.analysis.BGCheckDAO;
import iii.jbz.server.analysis.BGCheckDAO_interface;
import iii.jbz.server.analysis.BGCheckVO;

@SuppressWarnings("serial")
@WebServlet("/BG/BGCheckServlet")
//@MultipartConfig(fileSizeThreshold = 500000000*1024 * 1024, maxFileSize = 500000000 * 1024 * 1024, maxRequestSize = 500000000 * 5 * 1024 * 1024)
public class BGCheckServlet extends HttpServlet {

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
		
		BGCheckDAO_interface bgcheckDao = new BGCheckDAO();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAllbymemno")) {
			int memno = jsonObject.get("memno").getAsInt();
			List<BGCheckVO> bgcheckVOs = bgcheckDao.getAllbymemno(memno);
			writeText(response, gson.toJson(bgcheckVOs));
		} 
		
		else {
			writeText(response, "");
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