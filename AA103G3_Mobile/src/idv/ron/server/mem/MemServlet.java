package idv.ron.server.mem;

import idv.ron.server.main.ImageUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
@WebServlet("/MemServlet")
public class MemServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		MemDaoOracleImpl memDao = new MemDaoOracleImpl();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);
		
		if(action.equals("deleteRegId")){
			int memno = jsonObject.get("memno").getAsInt();
			int count = memDao.deleteRegId(memno);
			writeText(response, String.valueOf(count));
		}
        if(action.equals("checkLogin"))
        {   
        	String spotJson = jsonObject.get("member").getAsString();
			Mem mem = gson.fromJson(spotJson, Mem.class);
        	String count = "0";
        	System.out.println(count);
        	
			count = memDao.checkLogin(mem);
			if (Integer.parseInt(count) > 0) 
			{
				byte[] userimage = memDao.getImage(Integer.parseInt(count));
				Mem memOut = memDao.findByMemno(Integer.parseInt(count));
				JsonObject jsonObjectOut = new JsonObject();
				jsonObjectOut.addProperty("image", new Gson().toJson(userimage));
				System.out.println(userimage);
				jsonObjectOut.addProperty("memOut", gson.toJson(memOut));
				writeText(response, jsonObjectOut.toString());
			} else {
				writeText(response, String.valueOf(count));
			}
			
			if(count!="0"&&count!="-1"){
				memDao.updateRegId(Integer.parseInt(count), mem.getRegId());
			}
		
        }
        if (action.equals("findByMemno")){
        	int memno = jsonObject.get("memno").getAsInt();
        	Mem mem = memDao.findByMemno(memno);
        	writeText(response, gson.toJson(mem));
        }
        
		if (action.equals("getAll")) {
			List<Mem> mems = memDao.getAll();
			writeText(response, gson.toJson(mems));
		} else if (action.equals("getImage")) {
			OutputStream os = response.getOutputStream();
			int id = jsonObject.get("id").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = memDao.getImage(id);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
			}
			os.write(image);
		} 
		else if (action.equals("memberInsert") || action.equals("memUpdate")) 
		{
			String memJson = jsonObject.get("member").getAsString();
			Mem mem = gson.fromJson(memJson, Mem.class);
			String imageBase64 = jsonObject.get("imageBase64").getAsString();
			byte[] photo = Base64.decodeBase64(imageBase64);
			int count = 0;
			if (action.equals("memberInsert")) 
			{
				count = memDao.insert(mem, photo);
				System.out.println(count);
			} 
			else if (action.equals("memUpdate")) {
				count = memDao.update(mem, photo);
				System.out.println(count);
			}
			writeText(response, String.valueOf(count));
			
			}
//		 else if (action.equals("spotDelete")) {
//			String spotJson = jsonObject.get("spot").getAsString();
//			Mem spot = gson.fromJson(spotJson, Mem.class);
//			int count = spotDao.delete(spot.getId());
//			writeText(response, String.valueOf(count));
//		} else if (action.equals("findById")) {
//			int id = jsonObject.get("id").getAsInt();
//			Mem spot = spotDao.findById(id);
//			writeText(response, gson.toJson(spot));
//		} else {
//			writeText(response, "");
//		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemDao spotDAO = new MemDaoOracleImpl();
		List<Mem> spots = spotDAO.getAll();
		writeText(response, new Gson().toJson(spots));
	}

	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		 System.out.println("outText: " + outText);
		out.print(outText);
	}
}