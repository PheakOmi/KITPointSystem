package com.EntityClasses;



import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole{

	
	private Integer userRoleId;
				
	private String role;
		
	private User_Info user_info;

	
/*	
	public UserRole(String role, User user) {
		this.role=role;
		this.user=user;
	}
	
*/	

	

	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	
	public User_Info getUser_info() {
		return user_info;
	}

	public void setUser_info(User_Info user_info) {
		this.user_info = user_info;
	}

	
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}