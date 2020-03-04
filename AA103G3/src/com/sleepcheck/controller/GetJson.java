package com.sleepcheck.controller;

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

import com.sleepcheck.model.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class GetWeightJson
 */
public class GetJson extends HttpServlet {
    public GetJson() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("getSleepcheck".equals(request.getParameter("action"))){
			List<SleepCheckVO> list =null;
			List<SleepCheckVO> listJson =new ArrayList<SleepCheckVO>();

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			SleepCheckService sleepCheckSvc = new SleepCheckService();
			list = sleepCheckSvc.getAllByMemSleepCheck(memno);
			for(SleepCheckVO sleepCheckVo : list){
				SleepCheckVO sleepCheckJson = new SleepCheckVO();
				sleepCheckJson.setBedTime(sleepCheckVo.getBedTime());
				sleepCheckJson.setWakeTime(sleepCheckVo.getWakeTime());
				sleepCheckJson.setSleepTime(sleepCheckVo.getSleepTime());
				listJson.add(sleepCheckJson);
			}
			
			List array = new ArrayList();
			
			for(SleepCheckVO bvo:listJson){
				JSONObject obj = new JSONObject();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
				try{
					obj.put("wakeDate", formatter.format(bvo.getWakeTime()));
					obj.put("sleepTime",bvo.getSleepTime());
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
	}

}
