package com.Share.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Share.dao.IBadwordDao;
import com.Share.entity.Badword;

@Repository
public class BadwordDao extends BaseDao<Badword> implements IBadwordDao<Badword> {

	@Override
	public List<Badword> findByAdminId(Integer adminId) {
		
		String hql = "from Badword bw where bw.admin.id=?";
		
		return this.find(hql, new Object[]{adminId});
	}

	@Override
	public List<Badword> findAll() {
		String hql = "from Badword bw where 1=1";
		
		return this.find(hql);
	}

}
