package com.Share.service;


import com.Share.entity.Badword;
import com.Share.exception.BadwordException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IBadwordService<T> extends IBaseService<T> {

	/**
	 * 分页获取关键字列表
	 * @return
	 */
	public JSONArray getBadwordList(int id);
	
	/**
	 * 根据管理员id获取该管理员添加的屏蔽关键字
	 * @param adminId
	 * @return
	 */
	public JSONArray findByAdminId(Integer adminId);
	
	
	/**
	 * 查找全部屏蔽关键字列表
	 * @return
	 */
	public JSONArray findAllBadwordList();
	
	/**
	 * 根据id删除关键字
	 * @param id
	 */
	public void deleteBadword(Integer id);
	
	/**
	 * 添加关键字
	 * @param badword
	 */
	public JSONObject addBadword(Badword badword) throws BadwordException;
	
	/**
	 * @param content 根据内容查找badword
	 * @return
	 */
	public boolean findByContent(String content);
	
	/**
	 * 判断文章中是否存在关键字
	 * @param text
	 * @return
	 */
	public boolean isBadwordExist(String text);
	
}
