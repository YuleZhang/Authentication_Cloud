package Dao;

import entity.Admin;
import entity.User;

public interface AdminDao {
	public Admin queryOne(String username);
}
