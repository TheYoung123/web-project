package com.Share.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.Share.entity.User;
import com.Share.exception.LoginErrorException;
import com.Share.exception.UserRepeatException;
import com.Share.service.IUserService;
import com.Share.util.Pagination;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService extends BaseService<User> implements IUserService<User> {

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void saveUser(User user) throws UserRepeatException{
		//1.判断用户名是否重复
		if (userDao.findByUsername(user.getUsername())!=null) {
			throw new UserRepeatException("用户名重复！");
		}
		//2.保存用户
		userDao.save(user);
	}

	@Override
	public User login(User user) throws LoginErrorException{
		//1.判读用户名是否存在
		User userReturn = userDao.findByUsername(user.getUsername());
		if (userReturn==null) {
			throw new LoginErrorException("用户不存在!");
		}
		//2.判读密码是否正确
		if (!userReturn.getPassword().equals(user.getPassword())) {
			throw new LoginErrorException("密码不正确!");
		}
		return userReturn;
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public JSONObject getUserList(int page, int rows, String username) {
		String hql="from User u where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(username!=null&&!username.equals("")){
			hql+=" and u.username like ? ";
			params.add("%"+username+"%");
		}
		Pagination<User> pagination = userDao.findPagination(hql, params.toArray(), page, rows);
		JSONArray jUserList=new JSONArray();
		for (User user : pagination.getData()) {
			JSONObject jUser=new JSONObject();
			jUser.put("id", user.getId());
			jUser.put("username", user.getUsername());
			jUser.put("imgPath", user.getImgPath());
			jUser.put("gender", user.getGender());
			jUser.put("status", user.getStatus());
			jUserList.add(jUser);
		}
		JSONObject data=new JSONObject();
		data.put("data", jUserList);
		data.put("page", page);
		data.put("rows", rows);
		data.put("count", pagination.getCount());
		return data;
	}

	@Override
	public JSONObject getProfile(int id) {
		JSONObject profile=new JSONObject();
		User user = userDao.get(id);
		profile.put("id", user.getId());
		profile.put("username", user.getUsername());
		profile.put("imgPath", user.getImgPath());
		profile.put("status", user.getStatus());
		profile.put("shareCount", user.getShares().size());
		profile.put("fansCount", user.getFanses().size());
		profile.put("followCount", user.getUsers().size());
		return profile;
	}

	@Override
	public JSONArray getSearchUsers(String condition) {
		JSONArray userList=new JSONArray();
		if(condition==null||condition.length()==0){
			return userList;
		}
		//根据用户名搜索用户
		String hql="from User u where u.username like ?";
		List<User> users = userDao.find(hql, new Object[]{"%"+condition+"%"});
		
		for (User user : users) {
			JSONObject jUser=new JSONObject();
			jUser.put("id", user.getId());
			jUser.put("username", user.getUsername());
			jUser.put("imgPath", user.getImgPath());
			jUser.put("gender", user.getGender());
			userList.add(jUser);
		}
		return userList;
	}

}
