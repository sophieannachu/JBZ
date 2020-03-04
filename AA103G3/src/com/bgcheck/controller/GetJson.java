package com.bgcheck.controller;

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

import com.bgcheck.model.*;

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
		if("getBGcheck".equals(request.getParameter("action"))){
			List<BGCheckVO> list =null;
			List<BGCheckVO> listJson =new ArrayList<BGCheckVO>();

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			BGCheckService bgCheckSvc = new BGCheckService();
			list = bgCheckSvc.getAllByMemBGCheck(memno);
			for(BGCheckVO bgCheckVo : list){
				BGCheckVO bgCheckJson = new BGCheckVO();
				bgCheckJson.setBgBfMeat(bgCheckVo.getBgBfMeat());
				bgCheckJson.setBgAfMeat(bgCheckVo.getBgAfMeat());
				bgCheckJson.setBgBfSleep(bgCheckVo.getBgBfSleep());
				bgCheckJson.setCheckDate(bgCheckVo.getCheckDate());
				listJson.add(bgCheckJson);
			}
			
			List array = new ArrayList();
			
			for(BGCheckVO bvo:listJson){
				JSONObject obj = new JSONObject();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
				try{
					obj.put("bgBfMeat", bvo.getBgBfMeat());
					obj.put("bgAfMeat", bvo.getBgAfMeat());
					obj.put("bgBfSleep", bvo.getBgBfSleep());
					obj.put("checkDate",formatter.format(bvo.getCheckDate()));
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
