package service;

import java.util.List;

import Dao.UserDao;
import Dao.UserDaoImpl;
import entity.User;

public class UserServiceiImpl implements UserService{
	UserDao dao;
	public UserServiceiImpl() {
		// TODO Auto-generated constructor stub
		dao = new UserDaoImpl();
	}
	@Override
	public boolean Login(String username,String password) {
		
		User user = dao.queryOne(username);
		//进行相关的数据信息判断
		if(null==user)
		{
			System.out.println("该用户输入的账户不存在");
			return false;
		}
		else
		{
			 if(user.getPassword().equals(password))
			 {
				 System.out.println("登陆成功");
				 dao.updateTime(user);
				 return true;
			 }
			 else{
				 System.out.println("该用户输入的密码有误");
				 return false;
			 }
		}
	}
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users =  dao.searchAllUser();
		return users;
	}
	@Override
	public void insertOne(User u) {
		// TODO Auto-generated method stub
		dao.insertOne(u);
	}
	@Override
	public void deleteOne(User u) {
		// TODO Auto-generated method stub
		dao.deleteOne(u);
	}
	@Override
	public User queryOne(String username) {
		// TODO Auto-generated method stub
		User user = dao.queryOne(username);
		return user;
	}
	@Override
	public void updateOne(User u) {
		// TODO Auto-generated method stub
		dao.updateInfo(u);
	}
}
