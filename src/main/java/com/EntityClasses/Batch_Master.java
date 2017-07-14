package com.EntityClasses;

import java.sql.Timestamp;
public class Batch_Master {
	private int id;
	private int semester_id;
	private String name;
	private Timestamp created_at;
	private Timestamp updated_at;
	//private Set Children ;
	public Batch_Master(){
		
	}
	public Batch_Master(int id_data)
	{
		this.id=id_data;
	}
	
	/*public Set getChildren() {
		return Children;
	}
	public void setChildren(Set children) {
		this.Children = children;
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSemester_id() {
		return semester_id;
	}
	public void setSemester_id(int semester_id) {
		this.semester_id = semester_id;
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
