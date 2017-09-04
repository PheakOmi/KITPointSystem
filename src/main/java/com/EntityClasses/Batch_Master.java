package com.EntityClasses;

import java.sql.Timestamp;
import java.util.Date;
public class Batch_Master {
	private int id;
	private String semester;
	private int odoo_id;
	private Date start_date;
	private Date end_date;
	public int getOdoo_id() {
		return odoo_id;
	}
	public void setOdoo_id(int odoo_id) {
		this.odoo_id = odoo_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
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
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
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
