package com.EntityClasses;

import java.sql.Timestamp;
import java.util.Date;

public class Task_Master {
		private int id;
		private int project_id;
		private String name;
		private int assigned_to;
		private String description;
		private String status;
		private int time_spend;
		private Date deadline;
		private Date start_date;
		private Date end_date;
		private Timestamp created_at;
		private Timestamp updated_at;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getProject_id() {
			return project_id;
		}
		public void setProject_id(int project_id) {
			this.project_id = project_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAssigned_to() {
			return assigned_to;
		}
		public void setAssigned_to(int assigned_to) {
			this.assigned_to = assigned_to;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getTime_spend() {
			return time_spend;
		}
		public void setTime_spend(int time_spend) {
			this.time_spend = time_spend;
		}
		public Date getDeadline() {
			return deadline;
		}
		public void setDeadline(Date deadline) {
			this.deadline = deadline;
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
