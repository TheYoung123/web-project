package com.Share.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.Share.entity.Admin;
import com.Share.entity.Badword;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminTest {

	@Autowired
	private Admin admin=new Admin();
	

	@Test
	public void testId() {
	   admin.setId(1);
	   assertEquals(1,(int)admin.getId());
	}



	@Test
	public void testUsername() {
		admin.setUsername("123");
		assertEquals("123",admin.getUsername());
	}



	@Test
	public void testPassword() {
		admin.setPassword("123");
		assertEquals("123",admin.getPassword());
	}



	@Test
	public void testImgPath() {
		admin.setImgPath("c:/123.jpg");
		assertEquals("c:/123.jpg",admin.getImgPath());
	}



	@Test
	public void testBadwords() {
		Admin admin=new Admin("1","gjy");
		Badword bad=new Badword(admin,"hh");
		Set<Badword> bads=new HashSet<>();
		bads.add(bad);
		admin.setBadwords(bads);
		assertEquals(bads,admin.getBadwords());
	}



}
