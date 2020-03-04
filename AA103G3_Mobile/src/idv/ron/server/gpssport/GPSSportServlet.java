package idv.ron.server.gpssport;

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

@SuppressWarnings("serial")
@WebServlet("/GPSSportServlet")
public class GPSSportServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GPSSportDao_interface gpsSportDao = new GPSSportDao();
		List<GPSSport> gpsSportList = gpsSportDao.getAll();
		writeText(response, new Gson().toJson(gpsSportList));
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
		GPSSportDao_interface gpsSportDao = new GPSSportDao();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAll")) {
			List<GPSSport> gpsSportList = gpsSportDao.getAll();
			writeText(response, gson.toJson(gpsSportList));
		}
		if (action.equals("findByMemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<GPSSport> gpsSportList = gpsSportDao.findByMemno(memno);
			writeText(response, gson.toJson(gpsSportList));
		}
		if (action.equals("GPSinsert")){
			String gpsSportJson = jsonObject.get("gpsSport").getAsString();
			GPSSport gpsSport = gson.fromJson(gpsSportJson, GPSSport.class);
			int count = 0;
			count = gpsSportDao.GPSinsert(gpsSport);
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
