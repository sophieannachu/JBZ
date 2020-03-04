package tool;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;



import com.mem.model.MemVO;

public class LoginFilter implements Filter{
	
	private FilterConfig config;
	
	@Override
	public void destroy() {
		config=null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		  res.setHeader("Cache-Control","no-store"); //HTTP 1.1
		  res.setHeader("Pragma","no-cache");        //HTTP 1.0
		  res.setDateHeader ("Expires", 0);
		
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		if(memVO==null){
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/front-end/newLogin.jsp");
			return;
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config=config;
	}

}
