package iii.jbz.server.sport;

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
@WebServlet("/SportServlet")
public class SportServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SportDao_interface sportDao = new SportDao();
		List<Sport> sportList = sportDao.getAll();
		writeText(response, new Gson().toJson(sportList));
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
		SportDao_interface sportDao = new SportDao();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAll")) {
			List<Sport> sportList = sportDao.getAll();
			writeText(response, gson.toJson(sportList));
		}
		if (action.equals("findByMemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<Sport> gpsSportList = sportDao.findByMemno(memno);
			writeText(response, gson.toJson(gpsSportList));
		}
		if (action.equals("sportInsert")){
			String sportJson = jsonObject.get("sport").getAsString();
			Sport sport = gson.fromJson(sportJson, Sport.class);
			int count = 0;
			count = sportDao.sportInsert(sport);
			writeText(response, String.valueOf(count));
		}
		if(action.equals("sportDelete")){
			int sportrec_no = jsonObject.get("sportrec_no").getAsInt();
			int count = 0;
			count = sportDao.sportDelete(sportrec_no);
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
