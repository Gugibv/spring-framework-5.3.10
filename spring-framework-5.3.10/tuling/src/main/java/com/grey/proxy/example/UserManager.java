package com.grey.proxy.example;

/**
 * @author: Grey
 * @description: TODO
 * @date: 2022/7/6 17:04
 */
public interface UserManager {
	//新增用户抽象方法
	void addUser(String userName,String password);
	//删除用户抽象方法
	void delUser(String userName);
}
