package com.ServiceClasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.DaoClasses.userDaoImpl;
import com.DaoClasses.usersDao;
import com.EntityClasses.User;
import com.EntityClasses.User_Info;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;


@Service
public class usersServiceImpl implements usersService{

	@Autowired
	usersDao usersDao1;
	
	public submit addUser1(submit users){
		return usersDao1.addUser1(users);	
	}
	
	public boolean addUser2(User_Info users) {
		return usersDao1.addUser2(users);
	}

	public List<User_Info> getAllUsers() {
		return usersDao1.getAllUsers();
	}
	
	public retrieve getUserById(retrieve userid){
		return usersDao1.getUserById(userid);
	}

	public boolean deleteUser(User_Info users){
		return usersDao1.deleteUser(users);
	}
	
	
	
}
