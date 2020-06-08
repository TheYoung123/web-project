package com.Share.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Share.entity.Admin;
import com.Share.entity.User;
import com.Share.exception.LoginErrorException;
import com.Share.service.IAdminService;
import com.Share.service.IUserService;
import com.Share.util.AuthImageUtil;
import com.Share.util.Json;
import com.Share.util.MD5Util;
import com.alibaba.fastjson.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IAdminService<Admin> adminService;
	
	@Autowired
	IUserService<User> userService;
	
	
	@RequestMapping("/loginUI")
	public ModelAndView loginUI(){
		return new ModelAndView("admin/login");
	}
	
	@RequestMapping("/indexUI")
	public ModelAndView index(){
		return new ModelAndView("admin/index");
	}
	
	/**
	 * 屏蔽用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/hideUser")
	@ResponseBody
	public Json hideUser(Integer id){
		Json result=new Json();
		try {
			adminService.hideUser(id);
			result.setStatus(true);
			result.setMsg("屏蔽用户成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("屏蔽用户失败!");
		}
		return result;
	}
	
	/**
	 * 重新允许用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/enableUser")
	@ResponseBody
	public Json enableUser(Integer id){
		Json result = new Json();
		try {
			adminService.enableUser(id);
			result.setStatus(true);
			result.setMsg("重新允许用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("重新允许用户失败");
		}
		return result;
	}
	
	
	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		AuthImageUtil.getCode(response, request,AuthImageUtil.ADMIN_CODE);
	}
	
	@RequestMapping("/login")
	public ModelAndView login(Admin admin,HttpServletRequest request,HttpServletResponse response,String code) throws IOException{
		try {
			
			//判断验证码是否正确
			if (code==null||!code.toLowerCase().equals(request.getSession().getAttribute(AuthImageUtil.ADMIN_CODE))) {
				request.setAttribute("msg", "验证码不正确");
				return new ModelAndView("admin/login");
			}
			
			//验证之前md5加密
			admin.setPassword(MD5Util.getMD5(admin.getPassword()));
			
			//1.调用service
			Admin adminInfo = adminService.login(admin);
			
			//2.保存用户信息到session
			request.getSession().setAttribute("adminInfo", adminInfo);
			//3.跳转到首页
			return new ModelAndView("redirect:/admin/indexUI.do");
		} catch (LoginErrorException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			return new ModelAndView("admin/login");
		} 
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session){
		session.setAttribute("adminInfo", null);
		return new ModelAndView("redirect:/admin/indexUI.do");
	}
	
	@RequestMapping("/getUserList")
	@ResponseBody
	public Json getUserList(Integer page,Integer rows,String username){
		Json result=new Json();
		
		try {
			if(page==null){
				page=1;
			}
			if(rows==null){
				rows=5;
			}
			JSONObject data = userService.getUserList(page, rows, username);
			data.put("username", username==null?"":username);
			result.setData(data);
			result.setStatus(true);
			result.setMsg("获取用户列表成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("获取用户列表失败！"+e.getMessage());
		}
		return result;
	}
	
	
	
	
}
