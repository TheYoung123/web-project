package com.Share.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Share.dao.impl.UserDao;
import com.Share.entity.Admin;
import com.Share.entity.User;
import com.Share.util.LoginCookieUtil;
import com.Share.util.ServiceUtil;

public class LoginFilter implements Filter {


	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hRequest=(HttpServletRequest)request;
		HttpServletResponse hResponse=(HttpServletResponse)response;
		
		Admin admin=(Admin)hRequest.getSession().getAttribute("adminInfo");
		if(hRequest.getRequestURI().contains("visitor/")){
			//访客相关的uri,直接放行
			chain.doFilter(request, response);
		}else if (hRequest.getRequestURI().contains("admin/")|| (hRequest.getRequestURI().contains("badword/")&&!hRequest.getRequestURI().contains("/badword/isBadwordExist"))) {
				//判断用户是否登陆前，拦截admin相关的uri,判断admin是否登陆
				if(isPublicForAdmin(hRequest)){
					chain.doFilter(request, response);
				}else if (admin==null) {
					hResponse.sendRedirect(hRequest.getContextPath()+"/admin/loginUI.do");
				}else {
					chain.doFilter(request, response);
				}
		}else {
			//1.执行自动登陆的操作
			boolean autoLogin=LoginCookieUtil.readCookieAndLogin(hRequest, hResponse);
			
			//2.判断用户是否登陆
			User user = (User)hRequest.getSession().getAttribute("loginInfo");
			if(autoLogin){
				if (isPublicForUser(hRequest)) {
					//自动登陆的情况下，这些uri不可访问，直接跳转到首页
					hResponse.sendRedirect(hRequest.getContextPath()+"/index/index.do");
				}else {
					//其他情况下，已经登陆，放行
					chain.doFilter(request, response);
				}
			}else if(isPublicForUser(hRequest)){//注册登陆uri
				chain.doFilter(request, response);
			}else if (user==null) {//用户未登录，跳转登陆页面
				hResponse.sendRedirect(hRequest.getContextPath()+"/user/loginUI.do");
			}else {//用户已登陆，放行
				chain.doFilter(request, response);
			}
		}
		
	}
	
	/**
	 * 判断uri是否对admin公开(admin登陆页面)
	 * @param request
	 * @return
	 */
	private boolean isPublicForAdmin(HttpServletRequest request){
		if (request.getRequestURI().contains("admin/loginUI")
				||request.getRequestURI().contains("admin/login")
				||request.getRequestURI().contains("admin/getCode")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断uri是否是用户登陆注册时用到的(自动登陆时这些uri不可通过)
	 * @param request
	 * @return
	 */
	private boolean isPublicForUser(HttpServletRequest request){
		if (request.getRequestURI().contains("user/loginUI")
				||request.getRequestURI().contains("user/registerUI")
				||request.getRequestURI().contains("user/login")
				||request.getRequestURI().contains("user/register")
				||request.getRequestURI().contains("user/getCode")) {
			return true;
		}
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
