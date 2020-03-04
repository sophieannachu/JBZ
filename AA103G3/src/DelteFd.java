

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.xml.ws.Response;

/**
 * Servlet implementation class DelteFd
 */
@WebServlet("/group_info/DelteFd.do")
public class DelteFd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
    public DelteFd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupno= request.getParameter("group_no");
		String requestURL= request.getParameter("requestURL");
		Integer  groupNo = Integer.parseInt(groupno);
		PreparedStatement stm = null;
//		ServletOutputStream out = response.getOutputStream();
		try {
			con = ds.getConnection();
			stm = con.prepareStatement("DELETE group_info  WHERE group_no =? ");
			stm.setInt(1,groupNo);
			stm.executeUpdate();
			RequestDispatcher fr = request.getRequestDispatcher(requestURL);
			fr.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
	}

}
