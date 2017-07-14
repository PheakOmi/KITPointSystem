package com.EntityClasses;

import java.sql.Timestamp;

public class Project_Stage {
	private int id;
	private int project_id;
	private int stage_id;
	private Timestamp created_at;
	private Timestamp updated_at;
	public int getProject_id() {
		return project_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getStage_id() {
		return stage_id;
	}
	public void setStage_id(int stage_id) {
		this.stage_id = stage_id;
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
