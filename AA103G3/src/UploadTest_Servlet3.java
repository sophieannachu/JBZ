import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.sql.DataSource;

@WebServlet("/uploadServlet3_Lab.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException ���`
public class UploadTest_Servlet3 extends HttpServlet {
	
	Connection con;
	String saveDirectory = "/images_uploaded"; // �W���ɮת��ئa�ؿ�;
											   // �N�ѩ��U����26~30��� java.io.File �� ContextPath ���U, �۰ʫإߥئa�ؿ�

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("Big5"); // �B�z�����ɦW
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();

		Part part = req.getPart("pic"); // Servlet3.0�s�W�FPart�����A���ڭ̤�K���i���ɮפW�ǳB�z

				// �B�~���� InputStream �P byte[] (���N��model��VO�w�@�ǳ�)
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				Integer gpstype_no=0;
				try {
					String query="Update sporttype set type_pic= ? WHERE type_no =?";
					PreparedStatement pstmt = con.prepareStatement(query);
					gpstype_no = Integer.parseInt(req.getParameter("gpstype_no"));
					pstmt.setBytes(1, buf);
					pstmt.setInt(2, gpstype_no);

					pstmt.executeUpdate();
				}catch(Exception e) {
					System.out.println("no");
					System.out.println(e);
				}
				
				in.close();
				out.println("buffer length: " + buf.length);
				out.println("buffer length: " + gpstype_no);
				
				// �B�~���ըq��

				out.println();
				out.println("</PRE>");
			}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}