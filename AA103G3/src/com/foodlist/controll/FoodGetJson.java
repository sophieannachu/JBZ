package com.foodlist.controll;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodlist.model.FoodService;
import com.foodlist.model.FoodlistService;
import com.foodlist.model.FoodlistVO;
import com.sport.model.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GetWeightJson
 */
public class FoodGetJson extends HttpServlet {
    public FoodGetJson() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		if("getFoodCal".equals(request.getParameter("action"))){

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			List array = new ArrayList();
			FoodlistService foodlistSvc = new FoodlistService();
			FoodService foodSvc = new FoodService();
			List<FoodlistVO> fdlistDate = foodlistSvc.findDateByMemno(memno);
			for(FoodlistVO foodlistVO :fdlistDate){
				List<FoodlistVO> fdlist = foodlistSvc.findByDateAndMemno(foodlistVO.getFddate(), memno);
				Double cal=0.0;
				for(FoodlistVO foodlistByDate : fdlist){
					cal+=(foodlistByDate.getFdqua()*foodSvc.getOneFood(foodlistByDate.getFd_no()).getCal());
				}
				JSONObject obj = new JSONObject();
				try{
					obj.put("cal", cal);
					obj.put("date",foodlistVO.getFddate());
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
		
		if ("getByDateMem".equals(request.getParameter("action"))) {
			Integer memno = Integer.parseInt((request.getParameter("memno")));
			String date = request.getParameter("date").trim();
			java.sql.Date fdd=null;
			fdd = java.sql.Date.valueOf(date.split("_")[3]);
		
			
			FoodlistService foodlistSvc = new FoodlistService();
			FoodService foodSvc = new FoodService();
			List<FoodlistVO> foodlistVO = foodlistSvc.findByDateAndMemno(fdd,memno);
			List array = new ArrayList();
			if(foodlistVO.size()>0){
				Integer cal=0;
				Integer prot=0;
				Integer fat=0;
				Integer cho=0;
				Integer sugar=0;
				Integer df=0;
				Integer na=0;
				Integer ca=0;
				Integer k=0;
				for(FoodlistVO foodlist :foodlistVO){
						cal+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getCal());
						prot+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getProt());
						fat+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getFat());
						cho+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getCho());
						sugar+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getSugar());
						df+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getDf());
						na+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getNa());
						ca+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getCa());
						k+=(foodlist.getFdqua()*foodSvc.getOneFood(foodlist.getFd_no()).getK());
					}
					JSONObject obj = new JSONObject();
					try{
						obj.put("cal", cal);
						obj.put("prot", prot);
						obj.put("fat", fat);
						obj.put("cho", cho);
						obj.put("sugar", sugar);
						obj.put("df", df);
						obj.put("na", na);
						obj.put("ca", ca);
						obj.put("k", k);
					}catch(Exception e){}
					array.add(obj);
			}else{
				return;
			}			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
	}

}
