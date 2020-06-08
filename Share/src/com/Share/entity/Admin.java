package com.Share.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Admin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String imgPath;
	private Set<Badword> badwords = new HashSet(0);

	// Constructors


	public Admin() {
	}


	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}


	public Admin(String username, String password, String imgPath, Set badwords) {
		this.username = username;
		this.password = password;
		this.imgPath = imgPath;
		this.badwords = badwords;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Set<Badword> getBadwords() {
		return this.badwords;
	}

	public void setBadwords(Set<Badword> badwords) {
		this.badwords = badwords;
	}

}