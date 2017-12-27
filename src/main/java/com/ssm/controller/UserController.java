package com.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ssm.bean.UserBean;
import com.ssm.service.IUserService;
import com.ssm.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final  Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired 
	private IUserService userService;
	
	/**
	 * 查询用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getUserList")
	public String getUserLst(HttpServletRequest request,
			HttpServletResponse response){
		try {
			String username = request.getParameter("username");//获取前端传入的参数
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("username", username);
			
			List<UserBean> userList = userService.getUserList(map);
			
			request.setAttribute("userList", userList);//保存结果到requst中，jsp页面可以直接获取request中的数据
			
		} catch (Exception e) {
			logger.error("程序错误"+e.getMessage());
			e.printStackTrace();
		}
		
		return "userList";//返回那个页面，配置会自动加上后缀.jsp
	}
	
	/**
	 * 新增用户(异步)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(HttpServletRequest request,
			HttpServletResponse response){
		JSONObject result = new JSONObject();
		int stateCode = 1;
		String stateMessage = "新增成功！";
		try {
			String username = request.getParameter("username");//用户名
			String password = request.getParameter("password");//密码
			if(!StringUtil.isNullOrBlank(username)&&!StringUtil.isNullOrBlank(password)){
				UserBean user = new UserBean();
				user.setUsername(username);
				user.setPassword(password);
				userService.addUser(user);
			}else{
				stateCode = 2;
				stateMessage = "参数错误";
			}
		} catch (Exception e) {
			logger.error("程序错误"+e.getMessage());
			e.printStackTrace();
			stateCode = -999;
			stateMessage = "程序错误";
		}
		result.put("stateCode", stateCode);
		result.put("stateMessage", stateMessage);
		return result.toString();
	}
	
	/**
	 * 新增用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addFormUser")
	public String addFormUser(HttpServletRequest request,
			HttpServletResponse response){
		try {
			String username = request.getParameter("username");//用户名
			String password = request.getParameter("password");//密码
			if(!StringUtil.isNullOrBlank(username)&&!StringUtil.isNullOrBlank(password)){
				UserBean user = new UserBean();
				user.setUsername(username);
				user.setPassword(password);
				userService.addUser(user);
			}
		} catch (Exception e) {
			logger.error("程序错误"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/user/getUserList.do";
	}
}
