package com.Share.dao;

import com.Share.entity.User;
import com.Share.util.Pagination;

public interface IUserDao<T> extends IBaseDao<T> {
	
	/**
	 * 根据用户名查找用户，找到返回User,找不到返回null
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
}
