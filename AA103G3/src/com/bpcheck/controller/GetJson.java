package com.bpcheck.controller;

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

import com.bpcheck.model.*;

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
		if("getBPcheck".equals(request.getParameter("action"))){
			List<BPCheckVO> list =null;
			List<BPCheckVO> listJson =new ArrayList<BPCheckVO>();

			Integer memno = Integer.parseInt(request.getParameter("memno"));
			BPCheckService bgCheckSvc = new BPCheckService();
			list = bgCheckSvc.getAllByMemBPCheck(memno);
			for(BPCheckVO bpCheckVo : list){
				BPCheckVO bgCheckJson = new BPCheckVO();
				bgCheckJson.setsPressure(bpCheckVo.getsPressure());
				bgCheckJson.setdPressure(bpCheckVo.getdPressure());
				bgCheckJson.setCheckDate(bpCheckVo.getCheckDate());
				listJson.add(bgCheckJson);
			}
			
			List array = new ArrayList();
			
			for(BPCheckVO bvo:listJson){
				JSONObject obj = new JSONObject();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
				try{
					obj.put("sPressure", bvo.getsPressure());
					obj.put("dPressure", bvo.getdPressure());
					obj.put("checkDate",formatter.format(bvo.getCheckDate()));
				}catch(Exception e){}
				array.add(obj);
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("big5");
			PrintWriter out = response.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
	}

}
