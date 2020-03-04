package iii.jbz.mobile.clock;

import idv.ron.server.main.ImageUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/ClockServlet")
public class ClockServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

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
		ClockDao clockDao = new ClockDaoOracleImpl();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAll")) {
			List<Clock> clocks = clockDao.getAll();
			writeText(response, gson.toJson(clocks));
		} else if (action.equals("getImage")) {
			OutputStream os = response.getOutputStream();
			int id = jsonObject.get("id").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] typeimage = clockDao.getImage(id);
			if (typeimage != null) {
				typeimage = ImageUtil.shrink(typeimage, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(typeimage.length);
			}
			os.write(typeimage);
		} else if (action.equals("clockInsert") || action.equals("clockUpdate")) {
			String clockJson = jsonObject.get("clock").getAsString();
			Clock clock = gson.fromJson(clockJson, Clock.class);
			System.out.println(clockJson);
//			String imageBase64 = jsonObject.get("imageBase64").getAsString();
//			byte[] typeimage = Base64.decodeBase64(imageBase64);
			int count = 0;
			if (action.equals("clockInsert")) {
				count = clockDao.insert(clock);
			} else if (action.equals("clockUpdate")) {
				count = clockDao.update(clock);
			}
			writeText(response, String.valueOf(count));
		}
		 else if (action.equals("clockDelete")) {
			String clocktJson = jsonObject.get("clock").getAsString();
			Clock clock = gson.fromJson(clocktJson, Clock.class);
			int count = clockDao.delete(clock.getClockNo());
			writeText(response, String.valueOf(count));
		} else if (action.equals("findById")) {
			int id = jsonObject.get("id").getAsInt();
			Clock clock = clockDao.findById(id);
			writeText(response, gson.toJson(clock));
		} else {
			writeText(response, "");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClockDao clockDAO = new ClockDaoOracleImpl();
		List<Clock> clocks = clockDAO.getAll();
		writeText(response, new Gson().toJson(clocks));
	}

	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		// System.out.println("outText: " + outText);
		out.print(outText);
	}
}