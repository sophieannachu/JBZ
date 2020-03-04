package back_login;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{
	
	FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config=config;
	}
	
	@Override
	public void destroy() {
		config=null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		
		Object empVO=session.getAttribute("empVO");
		if(empVO == null){
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back-end/login/login.jsp");
			return;
		}else{
			chain.doFilter(request, response);
		}
		
		
	}

	
}
