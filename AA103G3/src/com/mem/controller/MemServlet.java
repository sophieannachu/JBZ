package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.*;
@MultipartConfig
public class MemServlet  extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		

		if("detail".equals(action)){
			String reqSourceURL=req.getParameter("reqSourceURL");
			try{
				Integer memno=new Integer(req.getParameter("memno"));
				MemService memSv=new MemService();
				MemVO memVO=memSv.getOneMem(memno);
				
				req.setAttribute("memVO", memVO);
				RequestDispatcher successView=req.getRequestDispatcher("/back-end/mem/showOne_mem.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher(reqSourceURL);
				failureView.forward(req, res);
			}
			
		}
		
		if("register".equals(action)){
			HttpSession session=req.getSession();
			List<String> errMsgs=new LinkedList();
			req.setAttribute("errMsgs", errMsgs);

			try{
			
				String acc=req.getParameter("acc").trim();
				if(acc.equals("")){
					acc="123456";
					errMsgs.add("請輸入帳號");
				}
				String pwd=req.getParameter("pwd").trim();
				if(pwd.equals("")){
					pwd="123456";
					errMsgs.add("請輸入密碼");
				}
				String name=req.getParameter("name").trim();
				if(name.equals("")){
					name="賴吉盛";
					errMsgs.add("請輸入姓名");
				}
				String id=req.getParameter("id").trim();
				if(id.equals("")){
					id="吉";
					errMsgs.add("請輸入暱稱");
				}
				String sex=req.getParameter("sex");
				if(sex == null){
					sex="1";
					errMsgs.add("請選擇性別");
				}
				
				Date bir=null;
				try {
					bir=Date.valueOf(req.getParameter("bir").trim());
				} catch (IllegalArgumentException e) {
					bir=new java.sql.Date(0);
					errMsgs.add("請輸入日期");
				}
				String phone=req.getParameter("phone").trim();
				if(phone.equals("")){
					phone="0931126000";
					errMsgs.add("請輸入手機");
				}
				String email=req.getParameter("email").trim();
				if(email.equals("")){
					email="s80091901@gmail.com";
					errMsgs.add("請輸入電子信箱");
				}
				String act_type=req.getParameter("act_type").trim();
				if(act_type.equals("0")){
					act_type="1";
					errMsgs.add("請選擇活動類型");
				}
				
				Integer height=null;
				try{
					height=Integer.parseInt(req.getParameter("height").trim());
				}catch(Exception e){
					height=178;
					errMsgs.add("請輸入身高");
				}
				
				Integer weight=null;
				try{
					weight=Integer.parseInt(req.getParameter("weight").trim());
				}catch(Exception e){
					weight=65;
					errMsgs.add("請輸入體重");
				}
				
//				String sos1=req.getParameter("sos1").trim();
//				String sos2=req.getParameter("sos2").trim();
//				String sos3=req.getParameter("sos3").trim();
				String sos1="";
				String sos2="";
				String sos3="";
				
				String[] disease=req.getParameterValues("disease");
				
				byte[] photo=(byte[])session.getAttribute("photo");
				Part part =req.getPart("photo");
				if(part.getSize() > 0){
					InputStream in=part.getInputStream();
					photo=new byte[in.available()];
					in.read(photo);
					in.close();
				}
				
				MemVO memVO=new MemVO();
				memVO.setAcc(acc);
				memVO.setPwd(pwd);
				memVO.setName(name);
				memVO.setId(id);
				memVO.setSex(sex);
				memVO.setBir(bir);
				memVO.setPhone(phone);
				memVO.setEmail(email);
				memVO.setAct_type(act_type);
				memVO.setHeight(height);
				memVO.setWeight(weight);
				memVO.setSos1(sos1);
				memVO.setSos2(sos2);
				memVO.setSos3(sos3);
				memVO.setPhoto(photo);
				insertDis(memVO, disease);
				
				if(!errMsgs.isEmpty()){
					session.setAttribute("photo", photo);
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView=req.getRequestDispatcher("/mem/register_mem.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println(123);
				MemService memSv=new MemService();
				memSv.addMem(id, acc, pwd, name, sex, bir, height, weight, phone, email, act_type, sos1, sos2, sos3, memVO.getBlood(), memVO.getSugar(), memVO.getOil(), memVO.getBone(), memVO.getBreathe(), memVO.getHeart(), memVO.getStom(), photo);
				RequestDispatcher successView=req.getRequestDispatcher("/front-end/newLogin.jsp");
				successView.forward(req, res);
				
			}catch(Exception e){
				errMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/mem/register_mem.jsp");
				failureView.forward(req, res);
			}
		}
		if("acc_check".equals(action)){
			Boolean accCheck=false;
			String acc=req.getParameter("acc").trim();
			MemService memSv=new MemService();
			MemVO memVO=memSv.getOneByAccMem(acc);
			PrintWriter out=res.getWriter();
			if(memVO == null){
				accCheck=true;
				out.print(accCheck);
			}else{
				out.print(accCheck);
			}
		}
		
		if("update_for_cpt".equals(action)){
			
			Integer memno = Integer.parseInt(req.getParameter("memno").trim());
			String cpt = req.getParameter("cpt").trim();
			
			MemService memSvc = new MemService();
			memSvc.updatecpt(memno, cpt);
	}
	}
	
	public MemVO insertDis(MemVO memVO, String[] disease){
		for(int i=0;i<7;i++){
			memVO.setBlood("0");
			memVO.setSugar("0");
			memVO.setOil("0");
			memVO.setBone("0");
			memVO.setBreathe("0");
			memVO.setHeart("0");
			memVO.setStom("0");
		}
		if(disease != null){
			List<String> disList=Arrays.asList(disease);
			if(disList.contains("blood")){
				memVO.setBlood("1");
			}
			if(disList.contains("sugar")){
				memVO.setSugar("1");
			}
			if(disList.contains("oil")){
				memVO.setOil("1");
			}
			if(disList.contains("bone")){
				memVO.setBone("1");
			}
			if(disList.contains("breathe")){
				memVO.setBreathe("1");
			}
			if(disList.contains("heart")){
				memVO.setHeart("1");
			}
			if(disList.contains("stom")){
				memVO.setStom("1");
			}
		}
		return memVO;
	}
}
