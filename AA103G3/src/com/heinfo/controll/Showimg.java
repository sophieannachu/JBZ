package com.heinfo.controll;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class Showimg extends HttpServlet {
	private static DataSource ds=null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con = null;
		req.setCharacterEncoding("Big5");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		try {
			con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT heinfophoto FROM heinfo WHERE heinfono =?");
			Integer heinfono = Integer.parseInt(req.getParameter("heinfono"));
			stmt.setInt(1, heinfono);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			byte[] heinfophoto = rs.getBytes("heinfophoto");
			out.write(heinfophoto);
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
