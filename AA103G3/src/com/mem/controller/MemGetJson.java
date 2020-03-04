package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;

import com.mem.model.*;

/**
 * Servlet implementation class MemServlet
 */
@WebServlet("/MemServlet")
@MultipartConfig
public class MemGetJson extends HttpServlet {

	public MemGetJson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		List<MemVO> list = new ArrayList<MemVO>();
		
		if("getMemByKey".equals(request.getParameter("action"))){
			try{
				
			String query = request.getParameter("query").trim();
			if(! "".equals(query)){
				MemService memSvc = new MemService();
				list = memSvc.findByKeyWord(query);
			}
			}catch(Exception e){
				return;
			}
			JSONArray array=null;
			if(list.size()==0){
				out.write("查無結果");
			}else{
				array = new JSONArray(list);
				out.write(array.toString());
			}
			out.flush();
			out.close();
		}
		
		if("updatePhoto".equals(request.getParameter("action"))){
			System.out.println(12333);
			MemService memSvc = new MemService();
			byte[] buffer;
			Integer memno = Integer.parseInt(request.getParameter("memno"));
			Part part =request.getPart("data");
			
			if(part.getSize() > 0){
				InputStream in=part.getInputStream();
				buffer=new byte[in.available()];
				in.read(buffer);
				in.close();
				System.out.println(buffer.length);
				memSvc.updatePhoto(memno,buffer);
			}
			

			
		}
		
	}

}
