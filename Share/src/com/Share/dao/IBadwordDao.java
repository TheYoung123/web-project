package com.Share.dao;

import java.util.List;

import com.Share.entity.Badword;

public interface IBadwordDao<T> extends IBaseDao<T> {
	/**
	 * 根据管理员id获取该管理员添加的屏蔽关键字
	 * @param adminId
	 * @return
	 */
	public List<Badword> findByAdminId(Integer adminId);
	
	/**
	 * 查询全部关键字
	 * @return
	 */
	public List<Badword> findAll();

}
