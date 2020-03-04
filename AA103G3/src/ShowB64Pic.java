

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import java.util.Base64;
import java.util.Collection;
/**
 * Servlet implementation class ShowB64Pic
 */
@WebServlet("/ShowB64Pic")
public class ShowB64Pic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowB64Pic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		String action = request.getParameter("action");
		
		if("insert_One64_Pic".equals(action)){
			System.out.println("sdds");
			String groupName = request.getParameter("groupName").trim();
			String groupName2=groupName.replaceAll(" ","&nbsp;").replaceAll("\r","<br/>").replaceAll("\n","&nbsp;");
			String insertPic = request.getParameter("insertPic");
			JSONObject groupNameText = new JSONObject();
			ServletOutputStream out = null;
			try{
				groupNameText.put("groupNameText", groupName2);
				Collection<Part> parts = request.getParts();				//req.getPart("file").write(getServletContext().getRealPath("/images_uploaded")+"/file.gif");
				int count = 0;
				for(Part part:parts){
					if(part.getName().equals("fname")){
						count++;
					
						InputStream fin = part.getInputStream();
						byte[] bFile = new byte[fin.available()];
						fin.read(bFile);
						fin.close();
						
						final String picBase64 = encoder.encodeToString(bFile);
						groupNameText.put("pic["+count+"]", picBase64);
					}
				}
				String jsonSt = groupNameText.toString();
				request.setAttribute("jsonSt", jsonSt);
				System.out.println("jsonSt"+jsonSt);
				out = response.getOutputStream();
				out.print(jsonSt);
			}catch(Exception e){
				out.print("Âà¥X¿ù»~");
			}
			
		}
		
		
		doGet(request, response);
	}

}
