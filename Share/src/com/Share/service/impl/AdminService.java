package com.Share.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Share.dao.IUserDao;
import com.Share.entity.Admin;
import com.Share.entity.User;
import com.Share.exception.LoginErrorException;
import com.Share.service.IAdminService;

@Transactional
@Service
public class AdminService extends BaseService<Admin> implements IAdminService<Admin> {
	
	@Override
	public void hideUser(int id) {
		User user = userDao.get(id);
		user.setStatus(User.STATUS_PRIVATE);
		userDao.update(user);
		
	}

	@Override
	public void enableUser(int id) {
		User user = userDao.get(id);
		user.setStatus(User.STATUS_PUBLIC);
		userDao.update(user);
		
	}
	
	
	public Admin login(Admin admin) throws LoginErrorException{
		Admin adminReturn = adminDao.findByUsername(admin.getUsername());
		if (adminReturn==null) {
			throw new LoginErrorException("用户不存在！");
		}
		
		if (adminReturn.getPassword().equals(admin.getPassword())) {
			throw new LoginErrorException("密码不正确！");
		}
		
		return adminReturn;
	}
	
}
