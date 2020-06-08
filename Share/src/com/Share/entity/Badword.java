package com.Share.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;



public class Badword implements java.io.Serializable {

	// Fields

	private Integer id;
	private Admin admin;
	private String content;
	private Timestamp createTime;

	// Constructors


	public Badword() {
	}


	public Badword(Admin admin, String content) {
		this.admin = admin;
		this.content = content;
	}


	public Badword(Admin admin, String content, Timestamp createTime) {
		this.admin = admin;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}