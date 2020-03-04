package com.pedometer.model;

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
@WebServlet("/PedometerServlet")
public class PedometerServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String memno = request.getParameter("memno");
		Gson gson = new Gson();
		PedometerService pedometerService = new PedometerService();
		if (action.equals("findByMemno")){
			List<Pedometer> pedometerList = pedometerService.findByMemno(Integer.parseInt(memno));
			writeText(response, gson.toJson(pedometerList));
		}
		if (action.equals("findByMemnoToday")){
			System.out.println("memno:"+memno);
			List<Pedometer> pedometerList = pedometerService.findByMemnoToday(Integer.parseInt(memno));
			writeText(response, gson.toJson(pedometerList));
		}
		doPost(request,response);
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
		PedometerService pedometerService = new PedometerService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("findByMemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<Pedometer> pedometerList = pedometerService.findByMemno(memno);
			writeText(response, gson.toJson(pedometerList));
		}
		if (action.equals("pedoInsert")){
			String pedoJson = jsonObject.get("pedometer").getAsString();
			Pedometer pedometer = gson.fromJson(pedoJson, Pedometer.class);
			int count = 0;
			count = pedometerService.pedoInsert(pedometer);
			writeText(response, String.valueOf(count));
		}
		if (action.equals("findByMemnoToday")){
			int memno = jsonObject.get("memno").getAsInt();
			System.out.println("memno:"+memno);
			List<Pedometer> pedometerList = pedometerService.findByMemnoToday(memno);
			writeText(response, gson.toJson(pedometerList));
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
