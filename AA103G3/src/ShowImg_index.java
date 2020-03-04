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

import com.emp.model.EmpVO;

public class ShowImg_index extends HttpServlet {
	HttpSession session;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		session = req.getSession();
		List<EmpVO> list = (List<EmpVO>) session.getAttribute("photolist");
		int index=Integer.parseInt(req.getParameter("index"));
		byte[] photo = list.get(index).getPhoto();
		if(photo == null){
//			 System.out.println(req.getServletContext().getRealPath("/"));
			 InputStream in = new FileInputStream(req.getServletContext().getRealPath("/")+"emp\\img\\nopic.jpg");
			 photo = new byte[in.available()];
			 in.read(photo);
			 in.close();
		}
		res.setContentLength(photo.length);
		ServletOutputStream out = res.getOutputStream();
		out.write(photo);

		// res.setContentType("image/gif");
		// ServletOutputStream out = res.getOutputStream();
		// HttpSession session = req.getSession();
		// byte[] photo=(byte[])session.getAttribute("photo");
		// System.out.println(req.getParameter("photo"));
		// out.write(photo);

	}

}
