package com.ach.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Reader
 */

public class AchImg extends HttpServlet {
	
	Connection con;
	
    public AchImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try{
			Statement stmt = con.createStatement();
			String achno = req.getParameter("achno");
			ResultSet rs = stmt.executeQuery("Select achpic from ach where achno="+achno);
			
			
			if(rs.next()){
				BufferedInputStream bin = new BufferedInputStream(rs.getBinaryStream("achpic"));
				byte[] buf = new byte[4 * 1024];
				int len;
				while((len=bin.read(buf))!=-1){
					out.write(buf,0,len);
				}
				bin.close();
			}else{
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void init() throws ServletException{
		try{
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
			con = ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void destroy(){
		try{
			if(con!=null) con.close();
		}catch(SQLException se){
			System.out.println(se);
		}
	}

}
