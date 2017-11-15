package com.EntityClasses;

import java.sql.Timestamp;

public class KIT_Point_Student_Wise {
	private int id;
	private int user_id;
	private int project_id;
	private int batch_id;
	private String kit_point;
	private String name;
	private String tpoint;
	private Timestamp created_at;
	private Timestamp updated_at;
	public int getId() {
		return id;
	}
	public String getTpoint() {
		return tpoint;
	}
	public void setTpoint(String tpoint) {
		this.tpoint = tpoint;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getKit_point() {
		return kit_point;
	}
	public void setKit_point(String kit_point) {
		this.kit_point = kit_point;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
