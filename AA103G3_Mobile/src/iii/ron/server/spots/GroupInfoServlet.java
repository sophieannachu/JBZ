package iii.ron.server.spots;

import java.io.*;
import java.util.*;
import java.util.zip.InflaterInputStream;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import idv.ron.server.*;
import idv.ron.server.main.ImageUtil;

@SuppressWarnings("serial")
@WebServlet("/GroupInfoServlet")
//@MultipartConfig(fileSizeThreshold = 500000000*1024 * 1024, maxFileSize = 500000000 * 1024 * 1024, maxRequestSize = 500000000 * 5 * 1024 * 1024)
public class GroupInfoServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	    final Base64.Encoder encoder = Base64.getEncoder();
		final Base64.Decoder decoder = Base64.getDecoder();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		GroupInfoService groupInfoSvc = new GroupInfoService();
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");

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
		
		GroupInfoDAO_interface groupinfoDao = new GroupInfoDAO();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAll")) {
			List<GroupInfoVO> spots = groupinfoDao.getAll();
			writeText(response, gson.toJson(spots));
		}
//		else if(action.equals("getOne")){
//			
//			Integer group_no = jsonObject.get("group_no").getAsInt();
//			GroupInfoVO spot = groupinfoDao.getOneGroup(group_no);
//			writeText(response, gson.toJson(spot));
//		}
		else if (action.equals("getImage")) {
			OutputStream os = response.getOutputStream();
			int group_no = jsonObject.get("group_no").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = groupinfoDao.getImg(group_no);
			final String picBase64 = encoder.encodeToString(image);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
			}
			os.write(image);
		} else if (action.equals("spotInsert") || action.equals("spotUpdate")) {
			String spotJson = jsonObject.get("spot").getAsString();
			GroupInfoVO spot = gson.fromJson(spotJson, GroupInfoVO.class);
			String imageBase64 = jsonObject.get("imageBase64").getAsString();
			byte[] image = decoder.decode(imageBase64);
			int count = 0;
//			if (action.equals("spotInsert")) {
//				count = groupinfoDao.insert(spot);
//			} 
//			else if (action.equals("spotUpdate")) {
//				count = groupinfoDao.update(spot);
//			}
			writeText(response, String.valueOf(count));
		} else if (action.equals("spotDelete")) {
			String spotJson = jsonObject.get("spot").getAsString();
			GroupInfoVO spot = gson.fromJson(spotJson, GroupInfoVO.class);
			int count = groupinfoDao.delete(spot.getGroup_no());
			writeText(response, String.valueOf(count));
		} else if (action.equals("findByForeignKey")) {
			int memno = jsonObject.get("memno").getAsInt();
			List<GroupInfoVO> groupInfoVO = groupinfoDao.findByForeignKey(memno);
			writeText(response, gson.toJson(groupInfoVO));
		} else {
			writeText(response, "");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GroupInfoDAO_interface groupinfoDao = new GroupInfoDAO();
		List<GroupInfoVO> spots = groupinfoDao.getAll();
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
		

//	}

		
//}
