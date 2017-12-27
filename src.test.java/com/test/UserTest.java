package com.test;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.bean.UserBean;
import com.ssm.service.IUserService;

/**
 * 测试类
 * @author user
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class UserTest {
	private static final Logger logger = Logger.getLogger(UserTest.class); 
	
	@Resource
	private IUserService userService;
	
	@Test
	public void test_userList(){
		try {
			List<UserBean> userList = userService.getUserList(null);
			System.out.println(userList.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_addUser(){
		try {
			UserBean user = new UserBean();
			user.setUsername("mazi");
			user.setPassword("123456");
			userService.addUser(user);
			System.out.println(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
