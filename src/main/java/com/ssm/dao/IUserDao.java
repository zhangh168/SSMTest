package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.bean.UserBean;

public interface IUserDao {
	
	/**
	 * 查询用户列表
	 * @param map
	 * @return
	 */
	public List<UserBean> getUserList(Map<String,Object> map);
	
	/**
	 * 新增用户
	 * @param user
	 */
	public void addUser(UserBean user) throws Exception;
	
}
