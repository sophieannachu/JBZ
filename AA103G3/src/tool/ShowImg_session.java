package tool;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


public class ShowImg_session extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		HttpSession session=req.getSession();
//		EmpService sv=new EmpService();
//		int empno;
		byte[] photo=null;
		try{
//			empno = Integer.parseInt(req.getParameter("empno"));
//			photo = sv.getImg(empno);
			photo=(byte[])session.getAttribute("photo");
			res.setContentLength(photo.length);
		}catch(Exception e){
			InputStream in = new FileInputStream(req.getServletContext().getRealPath("/")+"mem\\img\\nopic.jpg");
			photo = new byte[in.available()];
			in.read(photo);			
			in.close();
			res.setContentLength(photo.length);
		}finally{
			ServletOutputStream out = res.getOutputStream();
			out.write(photo);
		}



	}

}
