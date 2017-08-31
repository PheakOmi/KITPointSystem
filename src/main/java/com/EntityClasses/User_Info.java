package com.EntityClasses;

import java.sql.Timestamp;
import java.util.Set;



public class User_Info {

		private int id;
		private String name;
		private String email;
		private String password;
		private String user_type;
		private Timestamp created_at;
		private Timestamp updated_at;
		private boolean enabled;
		
		private Set<UserRole> userRole; 
		
		
		
		/*public User_Info(String username, String email2, String hashedPassword,
				boolean b, Timestamp created_at2) {
			this.name=username;
			this.email=email2;
			this.password=hashedPassword;
			this.enabled=b;
			this.created_at=created_at2;
			
		}*/
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUser_type() {
			return user_type;
		}
		public void setUser_type(String user_type) {
			this.user_type = user_type;
		}
		public Timestamp getCreated_at() {
			return created_at;
		}
		public void setCreated_at(Timestamp created_at) {
			this.created_at = created_at;
		}
		public Timestamp getUpdated_at() {
			return updated_at;
		}
		public void setUpdated_at(Timestamp updated_at) {
			this.updated_at = updated_at;
		}
		
		
		
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		
		public Set<UserRole> getUserRole() {
			return userRole;
		}
		public void setUserRole(Set<UserRole> userRole) {
			this.userRole = userRole;
		}
		
		
}
