import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth.model.*;
import com.emp.model.*;

public class LoginServlet  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		
		if("login".equals(req.getParameter("action"))){
			String acc=req.getParameter("acc").trim();
			String pwd=req.getParameter("pwd").trim();
			session.setAttribute("acc", acc);
			session.setAttribute("pwd", pwd);
			EmpVO empVO=new EmpService().getOneByAcc(acc);
			if(acc.equals("") || pwd.equals("")){
				session.setAttribute("error", "請輸入帳號及密碼 !");
				res.sendRedirect(req.getContextPath() + "/back-end/login/login.jsp");
				return;
			}else if(empVO == null){
				session.setAttribute("error", "查無此帳號 !");
				res.sendRedirect(req.getContextPath() + "/back-end/login/login.jsp");
				return;
			}else if(!pwd.equals(empVO.getPwd())){
				session.setAttribute("error", "密碼錯誤 !");
				res.sendRedirect(req.getContextPath() + "/back-end/login/login.jsp");
				return;
			}else{
				session.removeAttribute("acc");
		    	session.removeAttribute("pwd");
				session.setAttribute("empVO", empVO);
				List<Integer> authList=new AuthService().getOne(empVO.getEmpno());
				session.setAttribute("authList", authList);
				String location =(String)session.getAttribute("location");
				if(location != null){
					res.sendRedirect(location);
					session.removeAttribute("location");
					return;
				}
				res.sendRedirect(req.getContextPath() + "/back-end/index_emp.jsp");
				return;
			}
		}
		
	    if("logout".equals(req.getParameter("action"))){
	    	 session.removeAttribute("empVO");
	    	 session.removeAttribute("authList");
	    	 res.sendRedirect(req.getContextPath() + "/back-end/login/login.jsp");
             return;
	    }
	}
}
