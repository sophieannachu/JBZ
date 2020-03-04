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
// 當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
// 上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class UploadTest_Servlet3 extends HttpServlet {
	
	Connection con;
	String saveDirectory = "/images_uploaded"; // 上傳檔案的目地目錄;
											   // 將由底下的第26~30行用 java.io.File 於 ContextPath 之下, 自動建立目地目錄

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("Big5"); // 處理中文檔名
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();

		Part part = req.getPart("pic"); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理

				// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
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
				
				// 額外測試秀圖

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
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}