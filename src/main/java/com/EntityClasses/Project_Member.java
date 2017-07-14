package com.EntityClasses;

import java.security.Timestamp;

public class Project_Member {
		private int id;
		private int project_id;
		private int user_id;
		private Timestamp created_at;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		private Timestamp updated_at;
		public int getProject_id() {
			return project_id;
		}
		public void setProject_id(int project_id) {
			this.project_id = project_id;
		}
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
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
		
		
}
