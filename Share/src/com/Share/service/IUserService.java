package com.Share.service;

import com.Share.entity.User;
import com.Share.exception.LoginErrorException;
import com.Share.exception.UserRepeatException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IUserService<T> extends IBaseService<T> {
	/**
	 * 保存用户（注册）
	 * @param user
	 */
	public void saveUser(User user) throws UserRepeatException;
	
	/**
	 * 登陆
	 * @param user
	 * @return 登录用户的信息
	 */
	public User login(User user) throws LoginErrorException;
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	
	/**
	 * 获取用户列表
	 * @param page
	 * @param rows
	 * @param username
	 * @return
	 */
	public JSONObject getUserList(int page,int rows,String username);
	
	/**
	 * 获取个人简介
	 * @param id
	 * @return
	 */
	public JSONObject getProfile(int id);
	
	public JSONArray getSearchUsers(String condition);
}
