package com.Share.controller;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Share.Constant;
import com.Share.entity.Follow;
import com.Share.entity.User;
import com.Share.exception.FileUploadException;
import com.Share.exception.LoginErrorException;
import com.Share.exception.UserRepeatException;
import com.Share.service.IFollowService;
import com.Share.service.IUserService;
import com.Share.util.AuthImageUtil;
import com.Share.util.FileUtil;
import com.Share.util.Json;
import com.Share.util.LoginCookieUtil;
import com.Share.util.MD5Util;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService<User> userService;
	
	@Autowired
	private IFollowService<Follow> followService;
	
	/**
	 * 注册界面
	 * @return
	 */
	@RequestMapping("/registerUI")
	public ModelAndView registerUI(){
		return new ModelAndView("user/register");
	}
	
	/**
	 * 登陆界面
	 * @return
	 */
	@RequestMapping("/loginUI")
	public ModelAndView loginUI(){
		return new ModelAndView("user/login");
	}
	
	/**
	 * 注册
	 * @param user
	 * @param request
	 * @param password2
	 * @param imgStr
	 * @param code
	 * @return
	 */
	@RequestMapping("/register")
	public ModelAndView register(User user,HttpServletRequest request,String password2,String imgStr,String code){
		
		try {
			
			if (user.getUsername()==null
					||user.getUsername().equals("")
					||user.getUsername().length()<3||user.getUsername().length()>16) {
				throw new IllegalArgumentException("用户名长度在3到16位之间!");
			}
			
			if(user.getPassword()==null||user.getPassword().equals("")
					||!user.getPassword().equals(password2)){
				throw new IllegalArgumentException("密码必填，两次密码输入要一致！");
			}
			
			//判断验证码是否正确
			if (code==null||!code.equals(request.getSession().getAttribute(AuthImageUtil.CODE))) {
				request.setAttribute("msg", "验证码不正确");
				return new ModelAndView("user/register");
			}
			
			
			
			//1.封装参数到user
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			user.setStatus(0);
			//2.上传图片，封装图片路径到user
			String imgPath = FileUtil.base64ToImg(imgStr, request);
			if (imgPath!=null) {
				user.setImgPath(imgPath);
			}else {
				user.setImgPath(Constant.DEFAULT_USER_IMG);
			}
			
			//注册之前md5加密
			user.setPassword(MD5Util.getMD5(user.getPassword()));
			
			//3.调用service保存用户	
			userService.saveUser(user);	
				
			return new ModelAndView("redirect:/user/loginUI.do");
		} catch (FileUploadException e) {
			e.printStackTrace();
			request.setAttribute("msg", "上传图片出错！");
			return new ModelAndView("user/register");
		} catch (UserRepeatException e) {
			e.printStackTrace();
			request.setAttribute("msg", "用户名重复!");
			return new ModelAndView("user/register");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			return new ModelAndView("user/register");
		}
	}
	
	/**
	 * 登陆
	 * @param user
	 * @param request
	 * @param response
	 * @param remember
	 * @param code
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/login")
	public ModelAndView login(User user,HttpServletRequest request,HttpServletResponse response,String remember,String code) throws IOException{
		try {
			
			//判断验证码是否正确
			if (code==null||!code.toLowerCase().equals(request.getSession().getAttribute(AuthImageUtil.CODE))) {
				request.setAttribute("msg", "验证码不正确");
				return new ModelAndView("user/login");
			}
			
			//验证之前md5加密
			user.setPassword(MD5Util.getMD5(user.getPassword()));
			
			//1.调用service
			User loginInfo = userService.login(user);
			
			//判断是否需要保存登陆信息
			if (remember!=null&&remember.equals("1")) {
				LoginCookieUtil.saveCookie(loginInfo, response);
			}
			
			//2.保存用户信息到session
			request.getSession().setAttribute("loginInfo", loginInfo);
			//3.跳转到首页
			return new ModelAndView("redirect:/index/index.do");
		} catch (LoginErrorException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			return new ModelAndView("user/login");
		} 
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletResponse response,HttpSession session){
		session.setAttribute("loginInfo", null);
		//清除自动登陆cookie
		LoginCookieUtil.clearCookie(response);
		return new ModelAndView("redirect:/user/loginUI.do");
	}
	
	@RequestMapping("/getCode")
	public void getImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		AuthImageUtil.getCode(response, request,AuthImageUtil.CODE);
	}
	
	/**
	 * 获取用户简介，包括用户名，头像，用户粉丝数，关注数，分享数
	 * @param id 用户id
	 * @return
	 */
	@RequestMapping("/getProfile")
	@ResponseBody
	public Json getProfile(Integer id,HttpSession session){
		Json result=new Json();
		try {
			JSONObject profile = userService.getProfile(id);
			User user=(User)session.getAttribute("loginInfo");
			
			//添加下面的信息，用于生成关注/取消关注按钮
			if(!id.equals(user.getId())){
				profile.put("isMe", false);
				boolean isFollow = followService.isFollow(user.getId(), id);
				profile.put("isFollow", isFollow);
			}else {
				profile.put("isMe", true);
			}
			result.setData(profile);
			result.setStatus(true);
			result.setMsg("获取个人简介成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("获取个人简介失败！"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据用户名和内容搜索相关用户
	 * @param condition
	 * @return
	 */
	@RequestMapping("/getSearchUsers")
	@ResponseBody
	public Json getSearchUsers(String condition){
		Json result=new Json();
		try {
			JSONArray searchUsers = userService.getSearchUsers(condition);
			result.setData(searchUsers);
			result.setStatus(true);
			result.setMsg("搜素用户成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("搜索用户失败！"+e.getMessage());
		}
		
		return result;
	}
	

}
