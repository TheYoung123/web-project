package com.Share.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.Share.dao.IBadwordDao;
import com.Share.entity.Admin;
import com.Share.entity.Badword;
import com.Share.exception.BadwordException;
import com.Share.service.IBadwordService;
import com.Share.util.Pagination;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BadwordService extends BaseService<Badword> implements
		IBadwordService<Badword> {	
	
	@Override
	public JSONArray findByAdminId(Integer adminId) {
		List<Badword> BwLists = badwordDao.findByAdminId(adminId);
		//获取屏蔽关键字的id，admin_id,content,createTime
		JSONArray jBwLists = new JSONArray();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(Badword bad:BwLists){
			JSONObject jBw = new JSONObject();
			jBw.put("id", bad.getId());
			jBw.put("admin_id", bad.getAdmin().getId());
			jBw.put("content", bad.getContent());
			jBw.put("creatTime", sdf.format(new Date(bad.getCreateTime().getTime())));
			jBwLists.add(jBw);
		}
		return jBwLists;
	}


	@Override
	public JSONArray findAllBadwordList() {
		List<Badword> BwLists = badwordDao.findAll();
		
		JSONArray jBwLists = new JSONArray();
		for(Badword bad:BwLists){
			JSONObject jBw = new JSONObject();
			jBw.put("id", bad.getId());
			jBw.put("admin_id", bad.getAdmin().getId());
			jBw.put("content", bad.getContent());
			jBw.put("creatTime", bad.getCreateTime());
			jBwLists.add(jBw);
		}
		return jBwLists;
	}


	@Override
	public void deleteBadword(Integer id) {
		Badword bw = badwordDao.get(id);
		badwordDao.delete(bw);
	}

	//先查出所以admin,再根据每个admin的id,查询出他们的所有badword
	@Override
	public JSONArray getBadwordList(int id) {
		 List<Admin> admins = adminDao.findListWithout(id);
		 JSONArray jAdmins=new JSONArray();
		 for (Admin admin : admins) {
			JSONObject jAdmin=new JSONObject();
			jAdmin.put("id", admin.getId());
			jAdmin.put("username", admin.getUsername());
			jAdmin.put("img", admin.getImgPath());
			JSONArray jBadwords=new JSONArray();
			
			List<Badword> badwords = badwordDao.findByAdminId(admin.getId());
			
			for (Badword badword : badwords) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				JSONObject jBadword=new JSONObject();
				jBadword.put("id", badword.getId());
				jBadword.put("content", badword.getContent());
				jBadword.put("createTime", sdf.format(new Date(badword.getCreateTime().getTime())));
				jBadwords.add(jBadword);
			}
			jAdmin.put("badwords", jBadwords);
			

			jAdmins.add(jAdmin);
		}
		 return jAdmins;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject addBadword(Badword badword) throws BadwordException {
		List<Badword> BwLists = badwordDao.findAll();
		//判断关键字是否重复
		for(Badword bw : BwLists){
			if(bw.getContent().equals(badword.getContent())){
				throw new BadwordException("关键字重复");
			}
		}
		badwordDao.saveOrUpdate(badword);
		JSONObject jBadword=new JSONObject();
		jBadword.put("id", badword.getId());
		jBadword.put("content", badword.getContent());
		return jBadword;
	}


	@Override
	public boolean findByContent(String content) {
		if (content==null||"".equals(content)) {
			return false;
		}
		String hql="from Badword b where b.content=?";
		List<Badword> badwords = badwordDao.find(hql, new Object[]{content});
		return badwords.size()>0;
	}


	@Override
	public boolean isBadwordExist(String text) {
		if (text==null||text.length()==0) {
			return false;
		}
		List<Badword> badwords = badwordDao.find("from Badword");
		if (badwords!=null) {
			for (Badword badword : badwords) {
				if (text.contains(badword.getContent())) {
					return true;
				}
			}	
		}
		return false;
	}
}
