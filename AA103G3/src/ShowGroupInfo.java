

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.groupinfo.model.GroupInfoService;
import com.groupinfo.model.GroupInfoVO;

/**
 * Servlet implementation class ShowGroupInfo
 */
@WebServlet("/group_info/ShowGroupInfo")
public class ShowGroupInfo extends HttpServlet {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JBZDB");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
	Connection con;
    public ShowGroupInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String group_no = request.getParameter("group_no");
		Integer group_No = Integer.parseInt(group_no);  
		PreparedStatement stmt = null;
		ServletOutputStream out = response.getOutputStream();
		
		try {
			GroupInfoService groupInfoSvc = new GroupInfoService();
			byte[] buf = groupInfoSvc.showGroupInfoPhoto(group_No);
//			System.out.println("test="+buf);
			if(buf==null){
				out.close();
				return;
			}
			out.write(buf);
			out.close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
