package iii.jbz.mobile.foodrec;

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
@WebServlet("/MobileFoodrecServlet")
public class MobileFoodrecServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String memno = request.getParameter("memno");
		Gson gson = new Gson();
		FoodListService foodListService = new FoodListService();
		if (action.equals("getAllbymemno")){
			List<FoodListVO> foodListVOs = foodListService.getAllByMemFoodList(Integer.parseInt(memno));
			writeText(response, gson.toJson(foodListVOs));
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
		FoodListService foodListService = new FoodListService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAllbymemno")){
			int memno = jsonObject.get("memno").getAsInt();
			List<FoodListVO> foodListVOs = foodListService.getAllByMemFoodList(memno);
			writeText(response, gson.toJson(foodListVOs));
		}
		if (action.equals("foodListInsert")){
			String foodListJson = jsonObject.get("foodList").getAsString();
			FoodListVO foodListVO = gson.fromJson(foodListJson, FoodListVO.class);
			int count = 0;
			count = foodListService.addFoodList(foodListVO);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("foodListDelete")){
			int fdrecno = jsonObject.get("fdrecno").getAsInt();
			int count = 0;
			count = foodListService.deleteFoodList(fdrecno);
			writeText(response, String.valueOf(count));
			
		}
		if (action.equals("getAllFoods")){
			List<FoodVO> foodVOs = foodListService.getAllFoods();
			writeText(response, gson.toJson(foodVOs));
		}
		if (action.equals("getAllListAndFoods")){
			JsonObject jsonObjectOut = new JsonObject();
			int memno = jsonObject.get("memno").getAsInt();
			List<FoodListVO> foodListVOs = foodListService.getAllByMemFoodList(memno);
			List<FoodVO> foodVOs = foodListService.getAllFoods();
			jsonObjectOut.addProperty("foodListVOs", gson.toJson(foodListVOs));
			jsonObjectOut.addProperty("foodVOs", gson.toJson(foodVOs));
			writeText(response,jsonObjectOut.toString());
		}
		if (action.equals("getByKeyWord")){
			String fd_name = jsonObject.get("fd_name").getAsString();
			List<FoodVO> foodVOs = foodListService.getByKeyWord(fd_name);
			writeText(response, gson.toJson(foodVOs));
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