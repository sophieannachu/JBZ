package com.hpl.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basiccheck.model.*;
import com.bgcheck.model.*;
import com.bpcheck.model.*;
import com.hpl.model.*;
import com.mem.model.*;
import com.sport.model.*;

public class HplServlet  extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		HttpSession session=req.getSession();
//		MemVO memVO=(MemVO)session.getAttribute("memVO");
////		Integer memno=memVO.getMemno();
		
		if("main".equals(action)){
			
			try{
				MemVO memVO=(MemVO)session.getAttribute("memVO");
				Integer memno=memVO.getMemno();
				HplService hplSv=new HplService();
				List<HplVO> hplList=(List<HplVO>)hplSv.getAll_Now(memno);
				Map<Integer, Object> countMap=getTotalCount(hplList);
				req.setAttribute("hplList", hplList);
				req.setAttribute("countMap", countMap);
				RequestDispatcher successView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				failureView.forward(req, res);
			}
		}
		if("past".equals(action)){
			
			try{
				
				MemVO memVO=(MemVO)session.getAttribute("memVO");
				Integer memno=memVO.getMemno();
				HplService hplSv=new HplService();
				List<HplVO> hplList=(List<HplVO>)hplSv.getAll_Past(memno);
				Map<Integer, Object> hplMap=getTotalPercent(memno, hplList);
				Map<Integer, Object> daysMap=getTotalDays(hplList);
				req.setAttribute("hplList", hplList);
				req.setAttribute("hplMap", hplMap);
				req.setAttribute("daysMap", daysMap);
				RequestDispatcher successView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if("addCplan".equals(action)){
//			RequestDispatcher successView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
//			successView.forward(req, res);
//		}
		
		if("deletePlan".equals(action)){
			
			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
			
			try{
				
				if(reSubmitCode == reSubmitCode2){
					Integer hpno=new Integer(req.getParameter("hpno"));
					HplService hplSv=new HplService();
					hplSv.delete(hpno);
					req.setAttribute("successMsg", "計畫刪除成功!");
					session.removeAttribute("reSubmitCode");
				}
				
				RequestDispatcher successView=req.getRequestDispatcher("/hpl/HplServlet.do?action=main");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("deletePast".equals(action)){
			
			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
			
			try{
				
				if(reSubmitCode == reSubmitCode2){
					Integer hpno=new Integer(req.getParameter("hpno"));
					HplService hplSv=new HplService();
					hplSv.delete(hpno);
					req.setAttribute("successMsg", "計畫刪除成功!");
					session.removeAttribute("reSubmitCode");
				}
				RequestDispatcher successView=req.getRequestDispatcher("/hpl/HplServlet.do?action=past");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("addHplan".equals(action)){
			
			try{
				
				MemVO memVO=(MemVO)session.getAttribute("memVO");
				Integer memno=null;
				memno=memVO.getMemno();
				List<HplItem>hplList=new ArrayList();
				Integer age=getAge(memVO.getBir());
				
				HplItem weightItem=new HplItem();
				weightItem.setName("體重控制");
				BasicCheckService bcSv=new BasicCheckService();
				BasicCheckVO bcVO=bcSv.getOneAVGBasicCheck_NEWEST_WEIGHT2(memno, new java.sql.Date(new Date().getTime()));//體重 BMI
				req.setAttribute("bcVO", bcVO);
				Double height=new Double(memVO.getHeight());
				Double weight=new Double(memVO.getWeight());
				Double idealWeight1=Math.pow(height/100.0, 2)*22;
				Double idealWeight2=null;
				Double idealWeight3=null;
				Double bmi=bcVO.getBmi();
				if(bmi <= 0){
					bmi=Math.floor(weight/(Math.pow(height/100.0, 2))*10.0)/10.0;
					bcVO.setWeight(memVO.getWeight());
					bcVO.setBmi(bmi);
				}
				if(bmi>=27.0){
					weightItem.setStatus("肥胖");
					weightItem.setLevel(2);
					weightItem.setShow(true);
					System.out.println("bmi= " + bmi + " 肥胖");
				}else if(bmi>=24.0){
					weightItem.setStatus("體重過重");
					weightItem.setLevel(1);
					weightItem.setShow(true);
					System.out.println("bmi= " + bmi + " 體重過重");
				}else if(bmi>=18.5){
					weightItem.setStatus("體重正常");
					weightItem.setShow(false);
					System.out.println("bmi= " + bmi + " 體重正常");
				}else{
					weightItem.setStatus("體重過輕");
					weightItem.setShow(true);
					weightItem.setLevel(1);
					System.out.println("bmi= " + bmi + " 體重過輕");
				}
				
				
				HplItem waistItem=new HplItem();
				waistItem.setName("腰圍控制");
				Double waistline=bcSv.getOneAVGBasicCheck_NEWEST_WAIST2(memno, new java.sql.Date(new Date().getTime()));//腰圍
				req.setAttribute("waistline", waistline);
				if(memVO.getSex().equals("0")){
					idealWeight2=(height-158)*0.5+52;
					idealWeight3=(height-70)*0.6;
					if(age>=75){
						if(waistline >= 91.5){
							waistItem.setValue("91.5");
							System.out.println("waistline= " + waistline + " 腰圍過大");
						}else{
							System.out.println("waistline= " + waistline + " 腰圍正常");
						}
					}else if(age>=65){
						if(waistline >= 87.5){
							waistItem.setValue("87.5");
							System.out.println("waistline= " + waistline + " 腰圍過大");
						}else{
							System.out.println("waistline= " + waistline + " 腰圍正常");
						}
					}else if(age>=50){
						if(waistline >= 82.5){
							waistItem.setValue("82.5");
							System.out.println("waistline= " + waistline + " 腰圍過大");
						}else{
							System.out.println("waistline= " + waistline + " 腰圍正常");
						}
					}else{
						if(waistline >= 80.0){
							waistItem.setValue("80.0");
							System.out.println("waistline= " + waistline + " 腰圍過大");
						}else{
							System.out.println("waistline= " + waistline + " 腰圍正常");
						}
					}
					
				}else{
					idealWeight2=(height-170)*0.6+62;
					idealWeight3=(height-80)*0.7;
					if(age>=75){
						if(waistline >= 89.5){
							waistItem.setValue("89.5");
							System.out.println("waistline= " + waistline + " 腰圍過大");
						}else{
							System.out.println("waistline= " + waistline + " 腰圍正常");
						}
					}else if(age>=65){
						if(waistline >= 90.5){
							waistItem.setValue("90.5");
							System.out.println("waistline= " + waistline + " 腰圍過大");
						}else{
							System.out.println("waistline= " + waistline + " 腰圍正常");
						}
					}else if(age>=50){
						if(waistline >= 84.5){
							waistItem.setValue("84.5");
							System.out.println("waistline= " + waistline + " 腰圍過大");
						}else{
							System.out.println("waistline= " + waistline + " 腰圍正常");
						}
					}else{
						if(waistline >= 90.0){
							waistItem.setValue("90.0");
							System.out.println("waistline= " + waistline + " 腰圍過大");
						}else{
							System.out.println("waistline= " + waistline + " 腰圍正常");
						}
					}
				}
				System.out.println("waistline= " + waistItem.getValue());
				if(waistItem.getValue() != null){
					waistItem.setStatus("腰圍過大");
					waistItem.setLevel(2);
					waistItem.setShow(true);
				}else{
					waistItem.setShow(false);
				}
				Integer idealWeight4=(int)((idealWeight1 + idealWeight2 + idealWeight3)/3.0);
				weightItem.setValue(String.valueOf(idealWeight4));
				
				System.out.println("idealWeight1 "+idealWeight1);
				System.out.println("idealWeight2 "+idealWeight2);
				System.out.println("idealWeight3 "+idealWeight3);
				System.out.println("idealWeight4 "+idealWeight4);
				
				
				HplItem bpItem=new HplItem();
				bpItem.setName("血壓控制");
				BPCheckService bpSv=new BPCheckService();//血壓
				BPCheckVO bpVO=bpSv.getOneNewestBPCheck(memno);
				req.setAttribute("bpVO", bpVO);
				Integer sPressure=bpVO.getsPressure();
				Integer dPressure=bpVO.getdPressure();
				if(sPressure >=160 && dPressure >=100){
					bpItem.setStatus("血壓過高");
					bpItem.setValue("5");
					bpItem.setLevel(2);
					bpItem.setShow(true);
					System.out.println("sPressure= " + sPressure + " dPressure= " + dPressure + " 血壓過高");
				}else if(sPressure >=140 && dPressure >=90){
					bpItem.setStatus("血壓偏高");
					bpItem.setValue("4");
					bpItem.setLevel(1);
					bpItem.setShow(true);
					System.out.println("sPressure= " + sPressure + " dPressure= " + dPressure + " 血壓偏高");
				}else if(sPressure >=120 && dPressure >=80){
					bpItem.setStatus("血壓略高");
					bpItem.setValue("3");
					bpItem.setLevel(0);
					bpItem.setShow(true);
					System.out.println("sPressure= " + sPressure + " dPressure= " + dPressure + " 血壓略高");
				}else{
					bpItem.setShow(false);
					System.out.println("sPressure= " + sPressure + " dPressure= " + dPressure + " 血壓正常");
				}
				
				
				
				HplItem bgItem=new HplItem();
				bgItem.setName("血糖控制");
				BGCheckService bgSv=new BGCheckService();//血糖
				BGCheckVO bgVO=bgSv.getAllAvgNewest(memno);
				req.setAttribute("bgVO", bgVO);
				String BGBFMEAT="";
				String BGAFMEAT="";
				String BGBFSLEEP="";
				Integer bgIndex=0;
				
				if(bgVO.getBgBfMeat() != 0){
					if(bgVO.getBgBfMeat() >= 126){
						bgIndex+=10;
						BGBFMEAT="飯前血糖過高";
					}else if(bgVO.getBgBfMeat() >= 100){
						bgIndex+=3;
						BGBFMEAT="飯前血糖偏高";
					}else{
						BGBFMEAT="飯前血糖正常";
					}
				}
				
				if(bgVO.getBgAfMeat() != 0){
					if(bgVO.getBgAfMeat() >= 200){
						bgIndex+=10;
						BGAFMEAT="飯後血糖過高";
					}else if(bgVO.getBgAfMeat() >= 140){
						bgIndex+=3;
						BGAFMEAT="飯後血糖偏高";
					}else{
						BGAFMEAT="飯後血糖正常";
					}
				}
				
				if(bgVO.getBgBfSleep() != 0){
					if(bgVO.getBgBfSleep() >= 150){
						bgIndex+=10;
						BGBFSLEEP="睡前血糖過高";
					}else if(bgVO.getBgBfSleep() >= 110){
						bgIndex+=3;
						BGBFSLEEP="睡前血糖偏高";
					}else{
						BGBFSLEEP="睡前血糖正常";
					}
				}
				
				if(bgIndex>=10){
					bgItem.setStatus("血糖過高");
					bgItem.setValue("8");
					bgItem.setLevel(2);
					bgItem.setShow(true);
				}else if(bgIndex>=6){
					bgItem.setStatus("血糖偏高");
					bgItem.setValue("7");
					bgItem.setLevel(1);
					bgItem.setShow(true);
				}else if(bgIndex>=3){
					bgItem.setStatus("血糖略高");
					bgItem.setValue("6");
					bgItem.setLevel(0);
					bgItem.setShow(true);
				}else{
					bgItem.setShow(false);
				}
				
				System.out.println("BGBFMEAT "+bgVO.getBgBfMeat());
				System.out.println(BGBFMEAT);
				System.out.println("BGAFMEAT "+bgVO.getBgAfMeat());
				System.out.println(BGAFMEAT);
				System.out.println("BGBFSLEEP "+bgVO.getBgBfSleep());
				System.out.println(BGBFSLEEP);
				
				
				HplItem spItem=new HplItem();
				spItem.setName("每周運動量");
				Integer sportDur=null;
				java.sql.Date now=new java.sql.Date(new Date().getTime());
				SportService sportSv=new SportService();
				sportDur=sportSv.getDurWeek(memno, now);
				req.setAttribute("sportDur", sportDur);
				if(sportDur<=0){
					spItem.setShow(true);
					spItem.setLevel(2);
					spItem.setStatus("運動量過少");
					spItem.setValue("3");//3*30
				}else if(sportDur<90){
					spItem.setShow(true);
					spItem.setLevel(1);
					spItem.setStatus("運動量偏少");
					spItem.setValue("5");//5*30
				}else{
					spItem.setShow(false);
				}
				System.out.println("sportDur "+sportDur);
				
				hplList.add(weightItem);
				hplList.add(waistItem);
				hplList.add(bpItem);
				hplList.add(bgItem);
				hplList.add(spItem);
				req.setAttribute("hplList", hplList);
				
				RequestDispatcher successView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("insertHplan".equals(action)){
			
			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
			try{
				
				if(reSubmitCode == reSubmitCode2){
					
					MemVO memVO=(MemVO)session.getAttribute("memVO");
					Integer memno=memVO.getMemno();
					HplVO hplVO=new HplVO();
					hplVO.setMemno(memno);
					
					String name=req.getParameter("name").trim();
					if(name.equals("")){
						name="我的健康計劃";
					}
					hplVO.setHpname(name);
					
					java.sql.Date hpstdate=new java.sql.Date(new Date().getTime());
					hplVO.setHpstdate(hpstdate);
					
					java.sql.Date hpeddate=null;
					try {
						hpeddate=java.sql.Date.valueOf(req.getParameter("hpeddate").trim());
					} catch (IllegalArgumentException e) {
						hpeddate=new java.sql.Date(0);
					}
					
					Integer hpweight=null;
					if(req.getParameter("hpweight") != null){
						try {
							hpweight=new Integer(req.getParameter("hpweight").trim());
							hplVO.setHpbmi(hpweight);
							System.out.println("ok hpweight= " + req.getParameter("hpweight").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpweight= " + req.getParameter("hpweight").trim());
						}
					}else{
						hpweight=-1;
					}
					
					Double hpwaist=null;
					if(req.getParameter("hpwaist") != null){
						try {
							hpwaist=new Double(req.getParameter("hpwaist").trim());
							hplVO.setHpwaist(hpwaist);
							System.out.println("ok hpwaist= " + req.getParameter("hpwaist").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpwaist= " + req.getParameter("hpwaist").trim());
						}
					}else{
						hpwaist=-1.0;
					}
					
					Integer hpbp=null;
					if(req.getParameter("hpbp") != null){
						try {
							hpbp=new Integer(req.getParameter("hpbp").trim());
							hplVO.setHpbp(hpbp);
							System.out.println("ok hpbp= " + req.getParameter("hpbp").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpbp= " + req.getParameter("hpbp").trim());
						}
					}else{
						hpbp=-1;
					}
					
					Integer hpbg=null;
					if(req.getParameter("hpbg") != null){
						try {
							hpbg=new Integer(req.getParameter("hpbg").trim());
							hplVO.setHpbg(hpbg);
							System.out.println("ok hpbg= " + req.getParameter("hpbg").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpbg= " + req.getParameter("hpbg").trim());
						}
					}else{
						hpbg=-1;
					}
					
					Integer hpsp=null;
					if(req.getParameter("hpsp") != null){
						try {
							hpsp=new Integer(req.getParameter("hpsp").trim());
							hplVO.setHpexer(hpsp);
							System.out.println("ok hpsp= " + req.getParameter("hpsp").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpsp= " + req.getParameter("hpsp").trim());
						}
					}else{
						hpsp=-1;
					}
					
					Integer hpstep=-1;
					
					Integer hpcal=-1;
					
					HplService hplSv=new HplService();
					hplSv.addHpl(memno, name, hpstdate, hpstep, hpsp, hpweight, hpwaist, hpcal, hpbp, hpbg, hpeddate);
					session.removeAttribute("reSubmitCode");
					req.setAttribute("successMsg", "計畫新增成功!");
				}
				
				RequestDispatcher successView=req.getRequestDispatcher("/hpl/HplServlet.do?action=main");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");//
				failureView.forward(req, res);
			}
		}
		
		if("insertCplan".equals(action)){
			
			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
			
			try{
				
				if(reSubmitCode == reSubmitCode2){
					
					MemVO memVO=(MemVO)session.getAttribute("memVO");
					Integer memno=memVO.getMemno();
					HplVO hplVO=new HplVO();
					hplVO.setMemno(memno);
					
					String name=req.getParameter("name").trim();
					if(name.equals("")){
						name="我的健康計劃";
					};
					hplVO.setHpname(name);
					
					java.sql.Date hpstdate=new java.sql.Date(new Date().getTime());
					hplVO.setHpstdate(hpstdate);
					
					java.sql.Date hpeddate=null;
					try {
						hpeddate=java.sql.Date.valueOf(req.getParameter("hpeddate").trim());
					} catch (IllegalArgumentException e) {
						hpeddate=new java.sql.Date(0);
					}
					hplVO.setHpeddate(hpeddate);
					
					Integer hpweight=null;
					if(req.getParameter("hpweight") != null){
						try {
							hpweight=new Integer(req.getParameter("hpweight").trim());
							hplVO.setHpbmi(hpweight);
							System.out.println("ok hpweight= " + req.getParameter("hpweight").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpweight= " + req.getParameter("hpweight").trim());
						}
					}else{
						hpweight=-1;
					}
					
					Double hpwaist=null;
					if(req.getParameter("hpwaist") != null){
						try {
							hpwaist=new Double(req.getParameter("hpwaist").trim());
							hplVO.setHpwaist(hpwaist);
							System.out.println("ok hpwaist= " + req.getParameter("hpwaist").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpwaist= " + req.getParameter("hpwaist").trim());
						}
					}else{
						hpwaist=-1.0;
					}
					
					Integer hpbp=null;
					if(req.getParameter("hpbp") != null){
						try {
							hpbp=new Integer(req.getParameter("hpbp").trim());
							hplVO.setHpbp(hpbp);
							System.out.println("ok hpbp= " + req.getParameter("hpbp").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpbp= " + req.getParameter("hpbp").trim());
						}
					}else{
						hpbp=-1;
					}
					
					Integer hpbg=null;
					if(req.getParameter("hpbg") != null){
						try {
							hpbg=new Integer(req.getParameter("hpbg").trim());
							hplVO.setHpbg(hpbg);
							System.out.println("ok hpbg= " + req.getParameter("hpbg").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpbg= " + req.getParameter("hpbg").trim());
						}
					}else{
						hpbg=-1;
					}
					
					Integer hpsp=null;
					if(req.getParameter("hpsp") != null){
						try {
							hpsp=new Integer(req.getParameter("hpsp").trim());
							hplVO.setHpexer(hpsp);
							System.out.println("ok hpsp= " + req.getParameter("hpsp").trim());
						} catch (NumberFormatException e) {
							System.out.println("no hpsp= " + req.getParameter("hpsp").trim());
						}
					}else{
						hpsp=-1;
					}
					
					Integer hpstep=-1;
					
					Integer hpcal=-1;
					
					HplService hplSv=new HplService();
					hplSv.addHpl(memno, name, hpstdate, hpstep, hpsp, hpweight, hpwaist, hpcal, hpbp, hpbg, hpeddate);
					session.removeAttribute("reSubmitCode");
					req.setAttribute("successMsg", "計畫新增成功!");
				}
				
				RequestDispatcher successView=req.getRequestDispatcher("/hpl/HplServlet.do?action=main");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("hplDetail".equals(action)){
			
			try{
				
				MemVO memVO=(MemVO)session.getAttribute("memVO");
				Integer memno=memVO.getMemno();
				Integer hpno=new Integer(req.getParameter("hpno"));
				HplService hplSv=new HplService();
				HplVO hplVO=hplSv.getOne(hpno);
				java.sql.Date end=new java.sql.Date(new Date().getTime());
//			double days=getDays(hplVO.getHpstdate(), hplVO.getHpeddate());
//			Map<String, Object> hplMap=new HashMap();
				
				Map<String, Object> hplMap=getPercent(memno, hplVO, end);
				
				req.setAttribute("hplVO", hplVO);
				req.setAttribute("hplMap", hplMap);
				
				RequestDispatcher successView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("pastDetail".equals(action)){
			
			try{
				
				MemVO memVO=(MemVO)session.getAttribute("memVO");
				Integer memno=memVO.getMemno();//
				Integer hpno=new Integer(req.getParameter("hpno"));
				HplService hplSv=new HplService();
				HplVO hplVO=hplSv.getOne(hpno);
				
				Map<String, Object> hplMap=getPercent(memno, hplVO, hplVO.getHpeddate());
				
				req.setAttribute("hplVO", hplVO);
				req.setAttribute("hplMap", hplMap);
				
				RequestDispatcher successView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/index_hpl.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
	
	public Integer getAge(java.sql.Date date){
		SimpleDateFormat df = null;
		Integer age=null;
		try {
			df=new SimpleDateFormat("yyyy-MM-dd");
			Date bir = df.parse(date.toString());
			Date now = new Date();
			Long diff=(now.getTime()-bir.getTime())/1000/60/60/24/365;
			age=diff.intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}
	
	public Integer getDays(java.sql.Date date, java.sql.Date date2){
		SimpleDateFormat df = null;
		Integer days=null;
		try {
			df=new SimpleDateFormat("yyyy-MM-dd");
			Date start = df.parse(date.toString());
			Date end = df.parse(date2.toString());
			Long diff=(end.getTime()-start.getTime())/1000/60/60/24;
			days=diff.intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days+1;
	}
	
	public Integer getCountdown(java.sql.Date date){
		SimpleDateFormat df = null;
		Integer countdown=null;
		try {
			df=new SimpleDateFormat("yyyy-MM-dd");
			Date eddate = df.parse(date.toString());
			Date now = new Date();
			Long diff=(long) Math.ceil((eddate.getTime()-now.getTime())/1000/60/60/24.0);
			countdown=diff.intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return countdown;
	}
	
	public Map<String, Object> getPercent(Integer memno, HplVO hplVO, java.sql.Date date){
		
		MemVO memVO=new MemService().getOneMem(memno);
		Map<String, Object> hplMap=new HashMap();
		double totalPercent=0;
		double count=0;
		double days=getDays(hplVO.getHpstdate(), hplVO.getHpeddate());
		
		Integer hpbmi=hplVO.getHpbmi();
		double now_weight=-1;
		if(hpbmi != -1 && hpbmi != 0){
			BasicCheckService bcSv=new BasicCheckService();
			java.sql.Date hpstdate=new java.sql.Date(hplVO.getHpstdate().getTime()-(24*60*60*1000));
			double set=bcSv.getOneAVGBasicCheck_NEWEST_WEIGHT2(memno, hpstdate).getWeight();
			now_weight=bcSv.getOneAVGBasicCheck_NEWEST_WEIGHT2(memno, date).getWeight();
			if(set == 0){
				set=memVO.getWeight();
			}
			if(now_weight == 0){
				now_weight=memVO.getWeight();
			}
			double mole=set-now_weight;
			double Deno=hpbmi-set;
			double percent=0;
			if(Deno<0){//要瘦
				if(mole<0){//卻胖
					percent=0;
				}else{
					percent=mole/Math.abs(Deno);
					if(percent>1.0){
						percent=1.0;
					}
				}
			}else{//要胖
				if(mole>0){//卻瘦
					percent=0;
				}else{
					percent=Math.abs(mole)/Deno;
					if(percent>1.0){
						percent=1.0;
					}
				}
			}
			count++;
			totalPercent+=percent;
			hplMap.put("weight", percent);
			hplMap.put("now_weight", (int)now_weight);
			System.out.println("set" + set);
			System.out.println("now_weight" + now_weight);
			System.out.println("mole" + mole);
			System.out.println("Deno" + Deno);
			System.out.println("weight" + percent);
		}
		
		Double hpwaist=hplVO.getHpwaist();
		double now_waist=-1;
		if(hpwaist != -1 && hpwaist != 0){
			BasicCheckService bcSv=new BasicCheckService();
			java.sql.Date hpstdate=new java.sql.Date(hplVO.getHpstdate().getTime()-(24*60*60*1000));
			double set=bcSv.getOneAVGBasicCheck_NEWEST_WAIST2(memno, hpstdate);
			now_waist=bcSv.getOneAVGBasicCheck_NEWEST_WAIST2(memno, date);
			double mole=set-now_waist;
			double Deno=hpwaist-set;
			double percent=0;
			
			if(mole<0){
				percent=0;
			}else{
				percent=(mole/Math.abs(Deno));
				if(percent>1.0){
					percent=1.0;
				}
			}
			count++;
			totalPercent+=percent;
			hplMap.put("waist", percent);
			hplMap.put("now_waist", now_waist);
			System.out.println("waist" + percent);
		}
		
		Integer hpbp=hplVO.getHpbp();
		if(hpbp != -1 && hpbp != 0){
			BPCheckService bpSv=new BPCheckService();
			double times=bpSv.getTotalTimes(memno, hplVO.getHpstdate(), date);
			int goal_times=bpSv.getGoalTimes(memno, hplVO.getHpstdate(), date, hpbp);
			Integer today_times=bpSv.getTodayTimes(memno);
			double percent=0;
			if(days<1){
				days=1;
			}
			percent=times/(days*hpbp);
			if(percent>1.0){
				percent=1;
			}
			count++;
			totalPercent+=percent;
			hplMap.put("bp", percent);
			hplMap.put("hpbp_times", today_times);
			hplMap.put("hpbp_goalTimes", (int)goal_times);
			hplMap.put("hpbp_Endtimes", (int)times);
			System.out.println("bp" + percent);
			System.out.println("hpbp" + hpbp);
		}
		
		Integer hpbg=hplVO.getHpbg();
		if(hpbg != -1 && hpbg != 0){
			BGCheckService bgSv=new BGCheckService();
			double times=bgSv.getTotalTimes(memno, hplVO.getHpstdate(),date);
			int goal_times=bgSv.getGoalTimes(memno, hplVO.getHpstdate(),date, hpbg);
			Integer today_times=bgSv.getTodayTimes(memno);
			double percent=0;
			if(days<1){
				days=1;
			}
			percent=times/(days*hpbg);
			if(percent>1.0){
				percent=1;
			}
			count++;
			totalPercent+=percent;
			hplMap.put("bg", percent);
			hplMap.put("hpbg_times", today_times);
			hplMap.put("hpbg_goalTimes", (int)goal_times);
			hplMap.put("hpbg_Endtimes", (int)times);
			System.out.println("bg" + percent);
		}
		
		Integer hpsp=hplVO.getHpexer();
		if(hpsp != -1 && hpsp != 0){
			SportService spSv=new SportService();
			double times=spSv.getDurCount(memno, hplVO.getHpstdate(), date);
			double percent=0;
			if(days<7){
				days=7;
			}
			percent=times/((days/7.0)*hpsp);
			if(percent>1.0){
				percent=1;
			}
			count++;
			totalPercent+=percent;
			hplMap.put("sp", percent);
			hplMap.put("hpsp_times", (int)times);
			System.out.println("sp" + percent);
		}
		hplMap.put("totalPercent", (totalPercent/count));
		hplMap.put("totalDays", days);
		System.out.println("count " + count);
		System.out.println("totalPercent " + totalPercent);
		System.out.println("totalPercent/count " + (totalPercent/count));
		return hplMap;
	}
	public Map<Integer, Object> getTotalPercent(Integer memno, List<HplVO> list){
		Map<Integer, Object> hplMap=new HashMap();
		for(HplVO hplVO:list){
			hplMap.put(hplVO.getHpno(), getPercent(memno, hplVO, hplVO.getHpeddate()).get("totalPercent"));
			System.out.println("? " + hplMap.get(hplVO.getHpno()));
		}
		return hplMap;
	}
	public Map<Integer, Object> getTotalDays(List<HplVO> list){
		Map<Integer, Object> hplMap=new HashMap();
		for(HplVO hplVO:list){
			hplMap.put(hplVO.getHpno(), getDays(hplVO.getHpstdate(), hplVO.getHpeddate()));
			System.out.println("Days " + hplMap.get(hplVO.getHpno()));
		}
		return hplMap;
	}
	public Map<Integer, Object> getTotalCount(List<HplVO> list){
		Map<Integer, Object> hplMap=new HashMap();
		for(HplVO hplVO:list){
			hplMap.put(hplVO.getHpno(), getCountdown(hplVO.getHpeddate()));
			System.out.println("Countdown " + hplMap.get(hplVO.getHpno()));
		}
		return hplMap;
	}

}
