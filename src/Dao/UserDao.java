package Dao;

import java.util.List;

import entity.User;

public interface UserDao {
	//用来向数据库内插入一条信息
	public List<User> searchAllUser();
	public User queryOne(String username);
	public void insertOne(User user);
	public void deleteOne(User user);
	public void updateTime(User user);
	public void updateInfo(User user);
}
