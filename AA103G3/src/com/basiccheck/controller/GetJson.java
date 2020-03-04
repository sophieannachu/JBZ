package com.basiccheck.controller;

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

import com.basiccheck.model.BasicCheckService;
import com.basiccheck.model.BasicCheckVO;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class GetWeightJson
 */
public class GetJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
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
		if("getWeight".equals(request.getParameter("action"))){
			List<BasicCheckVO> list =null;
			List<BasicCheckVO> listJson =new ArrayList<BasicCheckVO>();
			Integer memno = Integer.parseInt(request.getParameter("memno"));
			BasicCheckService basicCheckSvc = new BasicCheckService();
			list = basicCheckSvc.getAllByMemBasicCheck(memno);
			for(BasicCheckVO basicCheckVo : list){
				BasicCheckVO basicCheckJson = new BasicCheckVO();
				basicCheckJson.setWeight(basicCheckVo.getWeight());
				basicCheckJson.setBmi(basicCheckVo.getBmi());
				basicCheckJson.setBmr(basicCheckVo.getBmr());
				basicCheckJson.setWaisyline(basicCheckVo.getWaisyline());
				basicCheckJson.setbFat(basicCheckVo.getbFat());
				basicCheckJson.setCheckDate(basicCheckVo.getCheckDate());
				listJson.add(basicCheckJson);
			}
			
			List array = new ArrayList();
			
			for(BasicCheckVO bvo:listJson){
				JSONObject obj = new JSONObject();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
				try{
					obj.put("weight", bvo.getWeight());
					obj.put("bmi", bvo.getBmi());
					obj.put("bmr", bvo.getBmr());
					obj.put("waisyline", bvo.getWaisyline());
					obj.put("bfat", bvo.getbFat());
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
