package com.Share.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Share.dao.IUserDao;
import com.Share.entity.User;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao<User> {

	@Override
	public User findByUsername(String username) {
		String hql="from User u where u.username=?";
		List<User> result = this.find(hql, new Object[]{username});
		if (result!=null&&result.size()>0) {
			return result.get(0);
		}
		return null;
	}

}
