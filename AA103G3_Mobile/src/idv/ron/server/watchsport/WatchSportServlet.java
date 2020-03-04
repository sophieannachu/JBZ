package idv.ron.server.watchsport;

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
@WebServlet("/WatchSportServlet")
public class WatchSportServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WatchSportDao_interface sportDao = new WatchSportDao();
		List<WatchSport> sportList = sportDao.getAll();
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
		WatchSportDao_interface sportDao = new WatchSportDao();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAll")) {
			List<WatchSport> sportList = sportDao.getAll();
			writeText(response, gson.toJson(sportList));
		}
		if (action.equals("findByMemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<WatchSport> sportList = sportDao.findByMemno(memno);
			writeText(response, gson.toJson(sportList));
		}
		if (action.equals("watchinsert")){
			String sportJson = jsonObject.get("sport").getAsString();
			WatchSport sport = gson.fromJson(sportJson, WatchSport.class);
			int count = 0;
			count = sportDao.watchinsert(sport);
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
