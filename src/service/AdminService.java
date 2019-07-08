package service;

import entity.Admin;

public interface AdminService {
	public boolean Login(String username,String password);
	public void insertOne(Admin admin);
}
