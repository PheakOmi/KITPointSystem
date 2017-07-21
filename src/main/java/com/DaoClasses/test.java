package com.DaoClasses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Project_Master;
import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;

class A{
	public static List < Project_Master > getAllProjectData() {
		  List < Project_Master > project = new ArrayList < Project_Master > ();
		  Transaction trns = null;
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  try {
		   trns = session.beginTransaction();
		   project = session.createQuery("from Project_Master").list();
		   //for ()
		   
		  } catch (RuntimeException e) {
		   e.printStackTrace();
		   return project;
		  } finally {
		   session.flush();
		   session.close();
		  }
		  return project;
		 }
	public void main(String args[])
	{
		List < Project_Master > project = A.getAllProjectData();
	}

}
