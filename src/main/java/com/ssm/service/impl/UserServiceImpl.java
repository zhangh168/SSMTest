package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.bean.UserBean;
import com.ssm.dao.IUserDao;
import com.ssm.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	public List<UserBean> getUserList(Map<String, Object> map) {
		return userDao.getUserList(map);
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,readOnly=false)
	public void addUser(UserBean user) throws Exception{
		userDao.addUser(user);
		//throw new Exception("测试事物");
	}
}
