package service;

import Dao.AdminDao;
import Dao.AdminDaoImpl;
import entity.Admin;

public class AdminServiceImpl implements AdminService
{
	AdminDao dao;
	public AdminServiceImpl()
	{
		dao = new AdminDaoImpl();
	}
	@Override
	public boolean Login(String username, String password) {
		Admin admin = dao.queryOne(username);
		if(null == admin)
		{
			System.out.println("该用户输入的账户不存在");
			return false;
		}
		else
		{
			 if(admin.getPassword().equals(password))
			 {
				 System.out.println("登陆成功");
				 return true;
			 }
			 else{
				 System.out.println("该用户输入的密码有误");
				 return false;
			 }
		}
	}
	public void insertOne(Admin admin)
	{
		dao.insertOne(admin);
	}
	
}
