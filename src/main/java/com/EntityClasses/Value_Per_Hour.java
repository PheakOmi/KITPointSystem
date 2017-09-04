package com.EntityClasses;

import java.sql.Timestamp;

public class Value_Per_Hour {
		private int id;
		private int semester_id;
		private String semester_name;
		
		public String getSemester_name() {
			return semester_name;
		}

		public void setSemester_name(String semester_name) {
			this.semester_name = semester_name;
		}
		private int batch_id;
		private String value;
		private Timestamp created_at;
		private Timestamp updated_at;
		



		public Value_Per_Hour() {
			
		}

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
	
		public int getBatch_id() {
			return batch_id;
		}

		public void setBatch_id(int batch_id) {
			this.batch_id = batch_id;
		}

		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
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
		public Value_Per_Hour( int v3 , String valuePerHour,Timestamp created_at_date,int b ) {
			
			this.semester_id = v3;
			this.value= valuePerHour;
			this.created_at=created_at_date;
			this.batch_id=b;
			
		}
	
		/*@Override
		public String toString()
		{
		 return semester_id+" "+ value	;
		}*/
		

}
