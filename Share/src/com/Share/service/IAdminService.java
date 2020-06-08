package com.Share.service;

import com.Share.entity.Admin;
import com.Share.exception.LoginErrorException;

public interface IAdminService<T> extends IBaseService<T> {
	/**
	 * 屏蔽用户
	 * @param id
	 */
	public void hideUser(int id);
	
	/**
	 * 重新允许用户
	 * @param id
	 */
	public void enableUser(int id);
	
	/**
	 * 管理员登陆
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin) throws LoginErrorException;
	
	
}
