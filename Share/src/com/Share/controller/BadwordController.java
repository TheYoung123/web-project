package com.Share.controller;


import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Share.entity.Admin;
import com.Share.entity.Badword;
import com.Share.exception.BadwordException;
import com.Share.service.IBadwordService;
import com.Share.util.Json;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("/badword")
public class BadwordController {

	@Autowired
	IBadwordService<Badword> badwordService;
	
	/**
	 * 添加关键字
	 * @param session
	 * @return
	 */
	@RequestMapping("/addBadword")
	@ResponseBody
	public Json saveBadword(Badword badword,HttpSession session){
		Json result = new Json();
		try {
			if(badword.getContent()==null||badword.getContent().equals("")){
				throw new BadwordException("关键字不能为空");
			}
			//获取当前登录的管理员
			Admin admin = (Admin) session.getAttribute("adminInfo");

			//设置管理员
			badword.setAdmin(admin);
			//设置添加时间
			badword.setCreateTime(new Timestamp(System.currentTimeMillis()));
			
			JSONObject jBadword = badwordService.addBadword(badword);
			result.setData(jBadword);
			result.setStatus(true);
			result.setMsg("添加关键字成功");
		} catch (BadwordException e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("添加关键字失败"+e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/deleteBadword")
	@ResponseBody
	public Json deleteBadword(Integer id){
		Json result = new Json();
		try {
			badwordService.deleteBadword(id);
			result.setStatus(true);
			result.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("删除失败");
		}
		return result;
	}
	
	
	/**
	 * 查找全部关键字
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public Json findAll(){
		Json result = new Json();
		try {
			JSONArray jbwList = badwordService.findAllBadwordList();
			result.setData(jbwList);
			result.setStatus(true);
			result.setMsg("获取关键字列表成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("获取关键字列表失败");
		}
		return result;
		
	}
	
	
	/**
	 * 根据admin_id查找到该管理员添加的所有屏蔽关键字
	 * @param adminId
	 * 测试获取管理员及管理员创建的关键字
	 * @return
	 */
	@RequestMapping("/getBadwordListByAdminId")
	@ResponseBody
	public Json findBwByAdminId(Integer adminId){
		Json result = new Json();
		try {
			JSONArray Bd = badwordService.findByAdminId(adminId);
			result.setData(Bd);
			result.setStatus(true);
			result.setMsg("获取屏蔽关键字成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("获取屏蔽关键字失败");
		}
		return result;
		
	}
	
	@RequestMapping("/getBadwordListWithoutMe")
	@ResponseBody
	public Json getBadwordListWithoutMe(HttpSession session){
		Json result=new Json();
		
		try {
			Admin admin = (Admin)session.getAttribute("adminInfo");
			JSONArray badwordList = badwordService.getBadwordList(admin.getId());
			result.setData(badwordList);
			result.setStatus(true);
			result.setMsg("获取关键字列表成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("获取关键字列表失败！");
		}
		return result;
	}
	
	@RequestMapping("/badwordUI")
	public ModelAndView badwordUI(){
		return new ModelAndView("admin/badword");
	}
	
	@RequestMapping("/findBadwordByContent")
	@ResponseBody
	public Json findBadwordByContent(String content){
		Json result=new Json();
		try {
			result.setData(badwordService.findByContent(content));
			result.setStatus(true);
			result.setMsg("根据内容查找关键字成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("根据内容查找关键字成功!"+e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/isBadwordExist")
	@ResponseBody
	public Json isBadwordExist(String text){
		Json result=new Json();
		try {
			result.setData(badwordService.isBadwordExist(text));
			result.setStatus(true);
			result.setMsg("查询关键字是否存在成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMsg("查询关键字是否存在失败!");
		}
		return result;
	}


}
