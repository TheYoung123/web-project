package com.Share.dao;

import java.util.List;

import com.Share.entity.Admin;

public interface IAdminDao<T> extends IBaseDao<T> {
	/**
	 * 根据用户名查找admin
	 * @param username
	 * @return
	 */
	public Admin findByUsername(String username);
	
	/**
	 * 查出所有用户除了传入的用户
	 * @param id
	 * @return
	 */
	public List<Admin> findListWithout(int id);
}
