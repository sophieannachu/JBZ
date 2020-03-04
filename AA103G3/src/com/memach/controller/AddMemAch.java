package com.memach.controller;

import com.groupList.model.GroupListService;
import com.memach.model.MemAchService;
import com.pedometer.model.PedometerService;
import com.sport.model.*;

public class AddMemAch {
	private MemAchService memAchSvc;
	private SportService sportSvc;
	private PedometerService pedometerSvc;
	private GroupListService groupListSvc;
	
	public void addMemAch(Integer memno){
		memAchSvc = new MemAchService();
		sportSvc = new SportService();
		pedometerSvc = new PedometerService();
		groupListSvc = new GroupListService();
		
		Sport sport = sportSvc.getSumByMem(memno);
		Integer pedo_total = pedometerSvc.getSumByMemno(memno);
		Integer group_total = groupListSvc.getCountMyGroup(memno);
		
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		//新增運動時間成就
		try{
			if(memAchSvc.getOneMemAch(memno, 110)==null&&sport.getSport_duration()/3600>=10){
				memAchSvc.addMemAch(memno, 110,date);
			}
			if(memAchSvc.getOneMemAch(memno, 120)==null&&sport.getSport_duration()/3600>=20){
				memAchSvc.addMemAch(memno, 120,date);
			}
			if(memAchSvc.getOneMemAch(memno, 130)==null&&sport.getSport_duration()/3600>=30){
				memAchSvc.addMemAch(memno, 130,date);
			}
			if(memAchSvc.getOneMemAch(memno, 140)==null&&sport.getSport_duration()/3600>=50){
				memAchSvc.addMemAch(memno, 140,date);
			}
			if(memAchSvc.getOneMemAch(memno, 150)==null&&sport.getSport_duration()/3600>=100){
				memAchSvc.addMemAch(memno, 150,date);
			}
		}catch(Exception e){
			
		}
		//新增消耗卡路里成就
		try{
			if(memAchSvc.getOneMemAch(memno, 160)==null&&sport.getSport_cal()>=5000){
				memAchSvc.addMemAch(memno, 160,date);
			}
			if(memAchSvc.getOneMemAch(memno, 170)==null&&sport.getSport_cal()>=10000){
				memAchSvc.addMemAch(memno, 170,date);
			}
			if(memAchSvc.getOneMemAch(memno, 180)==null&&sport.getSport_cal()>=15000){
				memAchSvc.addMemAch(memno, 180,date);
			}
			if(memAchSvc.getOneMemAch(memno, 190)==null&&sport.getSport_cal()>=25000){
				memAchSvc.addMemAch(memno, 190,date);
			}
			if(memAchSvc.getOneMemAch(memno, 200)==null&&sport.getSport_cal()>=35000){
				memAchSvc.addMemAch(memno, 200,date);
			}
		}catch(Exception e){
			
		}
		//新增步數成就
		try{
			if(memAchSvc.getOneMemAch(memno, 10)==null&&pedo_total>=30000){
				memAchSvc.addMemAch(memno, 10,date);
			}
			if(memAchSvc.getOneMemAch(memno, 20)==null&&pedo_total>=50000){
				memAchSvc.addMemAch(memno, 20,date);
			}
			if(memAchSvc.getOneMemAch(memno, 30)==null&&pedo_total>=100000){
				memAchSvc.addMemAch(memno, 30,date);
			}
			if(memAchSvc.getOneMemAch(memno, 40)==null&&pedo_total>=150000){
				memAchSvc.addMemAch(memno, 40,date);
			}
			if(memAchSvc.getOneMemAch(memno, 50)==null&&pedo_total>=200000){
				memAchSvc.addMemAch(memno, 50,date);
			}
		}catch(Exception e){
			
		}
		
		try{
			if(memAchSvc.getOneMemAch(memno, 60)==null&&group_total>=5){
				memAchSvc.addMemAch(memno, 60,date);
			}
			if(memAchSvc.getOneMemAch(memno, 70)==null&&group_total>=10){
				memAchSvc.addMemAch(memno, 70,date);
			}
			if(memAchSvc.getOneMemAch(memno, 80)==null&&group_total>=20){
				memAchSvc.addMemAch(memno, 80,date);
			}
			if(memAchSvc.getOneMemAch(memno, 90)==null&&group_total>=30){
				memAchSvc.addMemAch(memno, 90,date);
			}
			if(memAchSvc.getOneMemAch(memno, 100)==null&&group_total>=50){
				memAchSvc.addMemAch(memno, 100,date);
			}
		}catch(Exception e){
			
		}
	}
}
