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
			System.out.println("���û�������˻�������");
			return false;
		}
		else
		{
			 if(admin.getPassword().equals(password))
			 {
				 System.out.println("��½�ɹ�");
				 return true;
			 }
			 else{
				 System.out.println("���û��������������");
				 return false;
			 }
		}
	}
	public void insertOne(Admin admin)
	{
		dao.insertOne(admin);
	}
	
}
