package com.EntityClasses;

public class User {

	private int user_id;
	private String user_name;
	private String user_salary;
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_salary() {
		return user_salary;
	}
	public void setUser_salary(String user_salary) {
		this.user_salary = user_salary;
	}
	
	/*
	@Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_salary=" + user_salary + "]";
    }    
	*/
}
