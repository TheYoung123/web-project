package com.Share.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Share.dao.IAdminDao;
import com.Share.entity.Admin;
import com.Share.entity.Badword;

@Repository
public class AdminDao extends BaseDao<Admin> implements IAdminDao<Admin> {

	@Override
	public Admin findByUsername(String username) {
		String hql="from Admin a where a.username=?";
		List<Admin> result = this.find(hql, new Object[]{username});
		if (result!=null&&result.size()>0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<Admin> findListWithout(int id) {
		
		String hql="from Admin a where a.id!=?";
		
		return this.find(hql, new Object[]{id});
	}
	
	

}
