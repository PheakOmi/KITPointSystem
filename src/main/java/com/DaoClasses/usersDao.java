package com.DaoClasses;

import java.util.List;

//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;

import com.EntityClasses.User_Info;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;


public interface usersDao {
	public submit addUser1(submit users);
	public boolean addUser2(User_Info users);
	public List<User_Info> getAllUsers();
	public retrieve getUserById(retrieve userid);
	public boolean deleteUser(User_Info users);
}
