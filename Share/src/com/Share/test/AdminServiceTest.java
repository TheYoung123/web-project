package com.Share.test;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.Share.entity.Admin;
import com.Share.entity.User;
import com.Share.exception.LoginErrorException;
import com.Share.service.IAdminService;
import com.Share.service.IUserService;
import com.Share.service.impl.AdminService;
import com.Share.service.impl.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class AdminServiceTest {
	      
	
			
	@Autowired
	IAdminService<Admin> adminService;
	@Autowired
	IUserService<User> userService;
	@Test
	public void testHideUser() {

		System.out.println(1111111111);
		adminService.hideUser(1);
		User user = userService.get(1);
		int status=user.getStatus();
		System.out.println(status);
		assertEquals(1,status);
	}

	@Test
	public void testEnableUser() {
    	adminService.enableUser(1);
		User user = userService.get(1);
		int status=user.getStatus();
		System.out.println(status);
		assertEquals(0,status);
	}

	@Test
	public void testLogin() throws LoginErrorException {
		Admin admin=new Admin();
		admin.setUsername("gjy");
		admin.setPassword("123456");
		System.out.println(admin.getUsername());
		
		adminService.login(admin);
	}

}
