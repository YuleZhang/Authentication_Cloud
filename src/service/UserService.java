package service;

import java.util.List;

import entity.User;

public interface UserService {
	public boolean Login(String username,String password);
	public List<User> getAllUser();
	public User queryOne(String username);
	public void insertOne(User u);
	public void deleteOne(User u);
	public void updateOne(User u);
}
