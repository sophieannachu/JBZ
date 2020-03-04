package com.sport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sport.model.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GetWeightJson
 */
public class SportGetJson extends HttpServlet {
    public SportGetJson() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		if("getLocation".equals(request.getParameter("action"))){

			Integer sportrec_no = Integer.parseInt(request.getParameter("sportrec_no"));
			SportService sportSvc = new SportService();
			Sport sport = sportSvc.getOneGPSSport(sportrec_no);
			String jsonStr = sport.getGps_track();
			JSONArray array=null;
			try {
				array = new JSONArray(jsonStr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

						
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
		
		if("getGPSinfo".equals(request.getParameter("action"))){
			List<Sport> list =null;

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			SportService sportSvc = new SportService();
			list = sportSvc.getGPSSumbydate(memno);

			List array = new ArrayList();
			
			for(Sport sport:list){
				JSONObject obj = new JSONObject();
				SimpleDateFormat formatter = new SimpleDateFormat("M/d");
				try{
					obj.put("distance", sport.getGps_distance());
					obj.put("cal", sport.getSport_cal());
					obj.put("duration", sport.getSport_duration());
					obj.put("date",formatter.format(sport.getSport_date()));
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
		if("getGPS".equals(request.getParameter("action"))){
			List<Sport> list =null;

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			SportService sportSvc = new SportService();
			list = sportSvc.getGPSSumbydate(memno);

			List array = new ArrayList();
			
			for(Sport sport:list){
				JSONObject obj = new JSONObject();

				try{
					obj.put("distance", sport.getGps_distance());
					obj.put("date",sport.getSport_date().getTime());
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
		
		if("getWatchinfo".equals(request.getParameter("action"))){
			List<Sport> list =null;

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			SportService sportSvc = new SportService();
			list = sportSvc.getWatchSumbydate(memno);

			List array = new ArrayList();
			
			for(Sport sport:list){
				JSONObject obj = new JSONObject();
				SimpleDateFormat formatter = new SimpleDateFormat("M/d");
				try{
					obj.put("cal", sport.getSport_cal());
					obj.put("duration", sport.getSport_duration());
					obj.put("date",formatter.format(sport.getSport_date()));
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
		
		if("getAllinfo".equals(request.getParameter("action"))){
			List<Sport> list =null;

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			SportService sportSvc = new SportService();
			list = sportSvc.getAllSumbydate(memno);

			List array = new ArrayList();
			
			for(Sport sport:list){
				JSONObject obj = new JSONObject();
				SimpleDateFormat formatter = new SimpleDateFormat("M/d");
				try{
					obj.put("cal", sport.getSport_cal());
					obj.put("duration", sport.getSport_duration());
					obj.put("date",formatter.format(sport.getSport_date()));
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
		if("getAll".equals(request.getParameter("action"))){
			List<Sport> list =null;

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			SportService sportSvc = new SportService();
			list = sportSvc.getAllSumbydate(memno);

			List array = new ArrayList();
			
			for(Sport sport:list){
				JSONObject obj = new JSONObject();
				try{
					obj.put("cal", sport.getSport_cal());
					obj.put("duration", sport.getSport_duration());
					obj.put("date",sport.getSport_date().getTime());
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
		if("getAllanyl".equals(request.getParameter("action"))){
			List<Sport> list =null;

			SportService sportSvc = new SportService();
			list = sportSvc.getAllAvgbydate();

			List array = new ArrayList();
			
			for(Sport sport:list){
				JSONObject obj = new JSONObject();
				SimpleDateFormat formatter = new SimpleDateFormat("M/d");
				try{
					obj.put("cal", sport.getSport_cal());
					obj.put("duration", sport.getSport_duration());
					obj.put("date",formatter.format(sport.getSport_date()));
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
		}
	}

}
