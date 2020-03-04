package iii.jbz.mobile.healthplan;

import idv.ron.server.main.ImageUtil;
import idv.ron.server.mem.Mem;
import idv.ron.server.mem.MemDaoOracleImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.basiccheck.model.BasicCheckService;
import com.basiccheck.model.BasicCheckVO;
import com.bgcheck.model.BGCheckService;
import com.bgcheck.model.BGCheckVO;
import com.bpcheck.model.BPCheckService;
import com.bpcheck.model.BPCheckVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import iii.jbz.mobile.healthplan.HplItem;
import com.sport.model.SportService;

@SuppressWarnings("serial")
@WebServlet("/MobileHealthPlanServlet")
public class HealthPlanServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

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
		HealthPlanDao healthplanDao = new HealthPlanDaoOracleImpl();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAllByMemno")) {
			int memno = jsonObject.get("memno").getAsInt();
			List<HealthPlanVO> healthplans = healthplanDao.getAllByMemno(memno);
			
			BasicCheckService bcSv=new BasicCheckService();
			BasicCheckVO bcVO=bcSv.getOneAVGBasicCheck_NEWEST_WEIGHT2(memno, new java.sql.Date(new Date().getTime()));
			
			Double waistlineNewest=bcSv.getOneAVGBasicCheck_NEWEST_WAIST2(memno, new java.sql.Date(new Date().getTime()));
			JsonObject jsonObjectOut = new JsonObject();
			jsonObjectOut.addProperty("healthplans",gson.toJson(healthplans));
			jsonObjectOut.addProperty("bcVO", gson.toJson(bcVO));
			jsonObjectOut.addProperty("waistlineNewest", waistlineNewest);
			writeText(response, jsonObjectOut.toString());
		} 
		if (action.equals("addHplan")){
			int memno = jsonObject.get("memno").getAsInt();
			Mem memVO = new MemDaoOracleImpl().findByMemno(memno);
			List<HplItem>hplList=new ArrayList();
			JsonObject jsonObjectOut = new JsonObject();
			Integer age=getAge(memVO.getBir());
			
			HplItem weightItem=new HplItem();
			weightItem.setName("體重控制");
			BasicCheckService bcSv=new BasicCheckService();
			
			BasicCheckVO bcVO=bcSv.getOneAVGBasicCheck_NEWEST_WEIGHT2(memno, new java.sql.Date(new Date().getTime()));//體重 BMI
			jsonObjectOut.addProperty("bcVO", gson.toJson(bcVO));
			
			Double bmi=bcVO.getBmi();
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
			Double height=new Double(memVO.getHeight());
			Double idealWeight1=Math.pow(height/100.0, 2)*22;
			Double idealWeight2=null;
			Double idealWeight3=null;
			
			
			HplItem waistItem=new HplItem();
			waistItem.setName("腰圍控制");
			
			Double waistlineNewest=bcSv.getOneAVGBasicCheck_NEWEST_WAIST2(memno, new java.sql.Date(new Date().getTime()));//腰圍
			jsonObjectOut.addProperty("waistlineNewest", waistlineNewest);
			
			if(memVO.getSex().equals("0")){
				idealWeight2=(height-158)*0.5+52;
				idealWeight3=(height-70)*0.6;
				if(age>=75){
					if(waistlineNewest >= 91.5){
						waistItem.setValue("91.5");
						System.out.println("waistline= " + waistlineNewest + " 腰圍過大");
					}else{
						System.out.println("waistline= " + waistlineNewest + " 腰圍正常");
					}
				}else if(age>=65){
					if(waistlineNewest >= 87.5){
						waistItem.setValue("87.5");
						System.out.println("waistline= " + waistlineNewest + " 腰圍過大");
					}else{
						System.out.println("waistline= " + waistlineNewest + " 腰圍正常");
					}
				}else if(age>=50){
					if(waistlineNewest >= 82.5){
						waistItem.setValue("82.5");
						System.out.println("waistline= " + waistlineNewest + " 腰圍過大");
					}else{
						System.out.println("waistline= " + waistlineNewest + " 腰圍正常");
					}
				}else{
					if(waistlineNewest >= 80.0){
						waistItem.setValue("80.0");
						System.out.println("waistline= " + waistlineNewest + " 腰圍過大");
					}else{
						System.out.println("waistline= " + waistlineNewest + " 腰圍正常");
					}
				}
				
			}else{
				idealWeight2=(height-170)*0.6+62;
				idealWeight3=(height-80)*0.7;
				if(age>=75){
					if(waistlineNewest >= 89.5){
						waistItem.setValue("89.5");
						System.out.println("waistline= " + waistlineNewest + " 腰圍過大");
					}else{
						System.out.println("waistline= " + waistlineNewest + " 腰圍正常");
					}
				}else if(age>=65){
					if(waistlineNewest >= 90.5){
						waistItem.setValue("90.5");
						System.out.println("waistline= " + waistlineNewest + " 腰圍過大");
					}else{
						System.out.println("waistline= " + waistlineNewest + " 腰圍正常");
					}
				}else if(age>=50){
					if(waistlineNewest >= 84.5){
						waistItem.setValue("84.5");
						System.out.println("waistline= " + waistlineNewest + " 腰圍過大");
					}else{
						System.out.println("waistline= " + waistlineNewest + " 腰圍正常");
					}
				}else{
					if(waistlineNewest>= 90.0){
						waistItem.setValue("90.0");
						System.out.println("waistline= " + waistlineNewest + " 腰圍過大");
					}else{
						System.out.println("waistline= " + waistlineNewest + " 腰圍正常");
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
			jsonObjectOut.addProperty("bpVO", gson.toJson(bpVO));
			
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
				System.out.println("sPressure= " + sPressure + " dPressure= " + sPressure + " 血壓偏高");
			}else if(sPressure >=120 && dPressure >=80){
				bpItem.setStatus("血壓略高");
				bpItem.setValue("3");
				bpItem.setLevel(0);
				bpItem.setShow(true);
				System.out.println("sPressure= " + sPressure + " dPressure= " + sPressure + " 血壓略高");
			}else{
				bpItem.setShow(false);
				System.out.println("sPressure= " + sPressure + " dPressure= " + sPressure + " 血壓正常");
			}
			
			
			
			HplItem bgItem=new HplItem();
			bgItem.setName("血糖控制");
			BGCheckService bgSv=new BGCheckService();//血糖
			
			BGCheckVO bgVO=bgSv.getAllAvgNewest(memno);
			jsonObjectOut.addProperty("bgVO", gson.toJson(bgVO));
			
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
			jsonObjectOut.addProperty("sportDur", sportDur);
			
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
			
			//加入
			hplList.add(weightItem);
			hplList.add(waistItem);
			hplList.add(bpItem);
			hplList.add(bgItem);
			hplList.add(spItem);
			
			jsonObjectOut.addProperty("hplList", gson.toJson(hplList));
			writeText(response, jsonObjectOut.toString());
		}
		if(action.equals("insertHplan")){
				
			String planListJson = jsonObject.get("healthPlanVO").getAsString();
			HealthPlanVO healthPlanVO = gson.fromJson(planListJson, HealthPlanVO.class);
			int count = 0;
				
			if(healthPlanVO.getHpname().equals("") || healthPlanVO.getHpname() == null){
				healthPlanVO.setHpname("我的健康計劃");
			}
			java.sql.Timestamp hpstdate=new java.sql.Timestamp(new Date().getTime());
			healthPlanVO.setHpstdate(hpstdate);
				
			Integer hpweight=null;
			if(healthPlanVO.getHpbmi() == null){
				hpweight=-1;
				healthPlanVO.setHpbmi(hpweight);
			}
				
			Double hpwaist=null;
			if(healthPlanVO.getHpwaist() == null){
				hpwaist=(double) -1;
				healthPlanVO.setHpwaist(hpwaist);
			}
				
			Integer hpbp=null;
			if(healthPlanVO.getHpbp() == null){
				hpbp=-1;
				healthPlanVO.setHpbp(hpbp);
			}
				
			Integer hpbg=null;
			if(healthPlanVO.getHpbg() == null){
				hpbg=-1;
				healthPlanVO.setHpbg(hpbg);
			}
				
			Integer hpsp=null;
			if(healthPlanVO.getHpexer() == null){
				hpsp=-1;
				healthPlanVO.setHpexer(hpsp);
			}
			Integer hpstep=-1;
			Integer hpcal=-1;
			healthPlanVO.setHpstep(hpstep);
			healthPlanVO.setHpcal(hpcal);
			
			
			count = healthplanDao.insert(healthPlanVO);
				
			writeText(response, String.valueOf(count));	
		}
				
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		System.out.println("outText: " + outText);
		out.print(outText);
	}
	
	public Integer getAge(java.sql.Timestamp timestamp){
		SimpleDateFormat df = null;
		Integer age=null;
		try {
			df=new SimpleDateFormat("yyyy-MM-dd");
			Date bir = df.parse(timestamp.toString());
			Date now = new Date();
			Long diff=(now.getTime()-bir.getTime())/1000/60/60/24/365;
			age=diff.intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}
}