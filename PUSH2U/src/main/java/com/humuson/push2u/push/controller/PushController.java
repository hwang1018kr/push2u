package com.humuson.push2u.push.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.humuson.push2u.push.service.PushService;
import com.humuson.push2u.util.PagingHelper;
import com.humuson.push2u.util.TargetPagingHelper;

/**
 * push 관련 처리 컨트롤러
 * @author 황집중
 *
 */

@Controller
@RequestMapping("/push")
public class PushController {

	private static final Logger logger = LoggerFactory.getLogger(PushController.class);
	
	@Autowired
	private PushService pushService;
	
	public void setPushService(PushService pushService) {
		this.pushService = pushService;
	}
	
	/*
	 * push 발송화면 요청
	 */
	@RequestMapping(value="/sendView")
	public String sendView() {
		return "send/sendView";
	}
	
	/*
	 * push 발송
	 */
	@RequestMapping(value="/sendPush", method=RequestMethod.POST)
	public String sendPush(HttpServletRequest request, HttpSession session) throws Exception {
		
		String userId        = String.valueOf(session.getAttribute("userId"));
		String pushTitle     = request.getParameter("push_title");
		String popupContents = request.getParameter("popup_contents");
		String inAppContents = request.getParameter("inapp_contents");
		String smsYN		 = request.getParameter("smsYN"); 
		
		List<Map<String, String>> appUserList = pushService.getAppUserList();
		
		pushService.insertCampaign(userId, "T", pushTitle, popupContents, popupContents, inAppContents, smsYN, appUserList.size());
		
		String camId = String.valueOf(pushService.getMaxCamId());
		
		JsonObject jsonObj  = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		
		List<Map<String, Object>> detailList  = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> detailMap = null;
			
		int i = 1;
		
		for (Map<String, String> map : appUserList) {
			JsonObject listObj = new JsonObject();
			
			listObj.addProperty("reqUid", camId + "_" + i);
			listObj.addProperty("custId", map.get("CUST_ID"));
			
			jsonArray.add(listObj);
			
			detailMap = new HashMap<String, Object>();
			
			detailMap.put("camId", Integer.parseInt(camId));
			detailMap.put("reqUid", camId + "_" + i);
			detailMap.put("custId", map.get("CUST_ID"));
			
			detailList.add(detailMap);
			
			i++;
		}
		
		pushService.insertPushDetail(detailList);
		
		jsonObj.addProperty("bizId", "2fa2cd24481642f190919a4edf64f653");
		jsonObj.addProperty("msgType", "T");
		jsonObj.addProperty("pushTime", 1800);
		jsonObj.addProperty("pushTitle", pushTitle);
		jsonObj.addProperty("pushMsg", popupContents);
		jsonObj.addProperty("inappContent", inAppContents);
		jsonObj.addProperty("pushKey", "1");
		jsonObj.addProperty("pushValue", "http://www.pushpia.com");
		jsonObj.addProperty("reserveTime", "20160211121212");
		jsonObj.add("list", jsonArray);

		String param = "d=" + URLEncoder.encode(jsonObj.toString(), "UTF-8");
		
		logger.debug(jsonObj.toString());
		
		logger.debug(param);
		
		URL url = new URL("http://dev-api.pushpia.com/msg/send/realtime");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());
        dos.writeBytes(param);
        dos.flush();
        dos.close(); 
        
        con.disconnect();
        
        printByInputStream(con.getInputStream());
		 
		return "redirect:/push/reportView";
	}
	

	
	/*
	 * Rich push 발송화면 요청
	 */
	@RequestMapping(value="/sendRich")
	public String sendRich() {
		return "send/sendRich";
	}
	
	/*
	 * Rich push 발송
	 */
	@RequestMapping(value="/sendPushRich", method=RequestMethod.POST)
	public String sendPushRich(HttpServletRequest request, HttpSession session) throws Exception {
		
		String userId        = String.valueOf(session.getAttribute("userId"));
		String pushTitle     = request.getParameter("push_title");
		String popupContents = request.getParameter("popup_contents");
		String inAppContents = request.getParameter("smarteditor");
		String smsYN		 = request.getParameter("smsYN");
		
		List<Map<String, String>> appUserList = pushService.getAppUserList();
		
		pushService.insertCampaign(userId, "H", pushTitle, popupContents, popupContents, inAppContents, smsYN, appUserList.size());
		
		String camId = String.valueOf(pushService.getMaxCamId());
		
		logger.debug(inAppContents);
		
		JsonObject jsonObj  = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		
		List<Map<String, Object>> detailList  = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> detailMap = null;
			
		int i = 1;
		
		for (Map<String, String> map : appUserList) {
			JsonObject listObj = new JsonObject();
			
			listObj.addProperty("reqUid", camId + "_" + i);
			listObj.addProperty("custId", map.get("CUST_ID"));
			
			jsonArray.add(listObj);
			
			detailMap = new HashMap<String, Object>();
			
			detailMap.put("camId", Integer.parseInt(camId));
			detailMap.put("reqUid", camId + "_" + i);
			detailMap.put("custId", map.get("CUST_ID"));
			
			detailList.add(detailMap);
			
			i++;
		}
		
		pushService.insertPushDetail(detailList);
		
		jsonObj.addProperty("bizId", "2fa2cd24481642f190919a4edf64f653");
		jsonObj.addProperty("msgType", "H");
		jsonObj.addProperty("pushTime", 1800);
		jsonObj.addProperty("pushTitle", pushTitle);
		jsonObj.addProperty("popupContent", inAppContents);
		jsonObj.addProperty("pushMsg", popupContents);
		jsonObj.addProperty("inappContent", inAppContents);
		jsonObj.addProperty("pushKey", "1");
		jsonObj.addProperty("pushValue", "http://www.pushpia.com");
		jsonObj.addProperty("reserveTime", "20160212121212");
		jsonObj.add("list", jsonArray);
		

		String param = "d=" + URLEncoder.encode(jsonObj.toString(), "UTF-8");
	
		
		logger.debug(jsonObj.toString());
		
		logger.debug(param);
		
		URL url = new URL("http://dev-api.pushpia.com/msg/send/realtime");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());
        dos.writeBytes(param);
        dos.flush();
        dos.close(); 
        
        con.disconnect();
        
        printByInputStream(con.getInputStream());
        
        
        
		return "redirect:/push/reportView";
	}
	
	
	// 웹 서버로 부터 받은 웹 페이지 결과를 콘솔에 출력하는 메소드
	public void printByInputStream(InputStream is) {
		byte[] buf = new byte[1024];
		int len = -1;
		StringBuffer result = new StringBuffer();
		
		try {
			while ((len = is.read(buf, 0, buf.length)) != -1) {
				
				result.append(new String(buf, 0, len));
				
				logger.debug("PUSH Return Value : " + result.toString());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * report 화면 요청
	 */
	@RequestMapping(value="/reportView")
	public String reportView(Model model, HttpSession session, HttpServletRequest request) {
		
		//String userId    = String.valueOf(session.getAttribute("userId"));
		String userId     	= "qqq";
		String pagerHtml  	= null;
		int reportSize    	= pushService.allReportSize(userId);
		int limit  	      	= 0;
		String pageString 	= request.getParameter("pageNum");
		int 				pageNum = 1;
		
		if (pageString != null){
			pageNum =  Integer.parseInt(pageString);
		}

		PagingHelper pagingHelper = new PagingHelper(5, 5, reportSize, pageNum);
		pagingHelper.calculate();
		
		pagerHtml = pagingHelper.toHtml("", "");
		
		if(pageNum == 1) {
			limit = 0;
		} else {
			limit = limit + 5 * (pageNum - 1);
		}
		
		List<Map<String, Object>> reportList = pushService.getReportList(userId, limit);
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("pagerHtml", pagerHtml);
		
		return "report/reportView";
	}
	
	/*
	 * report 상세 화면 요청
	 */
	@RequestMapping(value="/detailReport")
	public String detailReportView(Model model, HttpSession session, HttpServletRequest request) {
		//String userId    = String.valueOf(session.getAttribute("userId"));
		String userId = "qqq";
		String camId 	 = request.getParameter("camId");

		Map<String, Object> detailReport = pushService.getDetailReport(userId, Integer.parseInt(camId));
		
		model.addAttribute("detailReport", detailReport);
		return "report/detailReport";
	}

	
	/*
	 * report push target 화면 요청
	 */
	@RequestMapping(value = "/pushTarget")
	public String pushTargetView(Model model, HttpSession session, HttpServletRequest request) {

		String camId        = request.getParameter("camId");
		String pagerHtml 	= null;
		int reportSize   	= pushService.allTargetSize(Integer.parseInt(camId));
		int limit  	     	= 0;
		String pageString 	= request.getParameter("pageNum");
		int pageNum 		= 1;
		
		if (pageString != null){
			pageNum =  Integer.parseInt(pageString);
		}
		
		logger.debug("***************" + camId);
		TargetPagingHelper pagingHelper = new TargetPagingHelper(5, 5, reportSize, pageNum);
		pagingHelper.calculate();
		pagerHtml = pagingHelper.toHtml("", "");
		
		if(pageNum == 1) {
			limit = 0;
		} else {
			limit = limit + 5 * (pageNum - 1);
		}
		
		List<Map<String, Object>> reportList = pushService.getTargetList(Integer.parseInt(camId), limit);
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("pagerHtml", pagerHtml);
		
		return "report/pushTarget";
	}

	/*
	 * report push 성공 화면 요청
	 */
	@RequestMapping(value = "/pushSuccess")
	public String pushSuccessView() {
		return "report/pushSuccess";
	}

	/*
	 * report push 실패 화면 요청
	 */
	@RequestMapping(value = "/pushFail")
	public String pushFailView() {
		return "report/pushFail";
	}

	/*
	 * report push 오픈 화면 요청
	 */
	@RequestMapping(value = "/pushOpen")
	public String pushOpenView() {
		return "report/pushOpen";
	}

	/*
	 * report push 미오픈 화면 요청
	 */
	@RequestMapping(value = "/pushNoOpen")
	public String pushNoOpenView() {
		return "report/pushNoOpen";
	}

	/*
	 * report sms 성공 화면 요청
	 */
	@RequestMapping(value = "/smsSuccess")
	public String smsSuccessView() {
		return "report/smsSuccess";
	}

	/*
	 * report sms 실패 화면 요청
	 */
	@RequestMapping(value = "/smsFail")
	public String smsFailView() {
		return "report/smsFail";
	}

}
