package com.EntityClasses;

import java.sql.Timestamp;

public class Skillset_Project_Wise {
	private int id;
	private int project_id;
	private int skillset_id;
	private String skillset_name;
	private Timestamp created_at;
	private Timestamp updated_at;
	private String text;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getSkillset_id() {
		return skillset_id;
	}
	public void setSkillset_id(int skillset_id) {
		this.skillset_id = skillset_id;
	}
	public String getSkillset_name() {
		return skillset_name;
	}
	public void setSkillset_name(String skillset_name) {
		this.skillset_name = skillset_name;
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
