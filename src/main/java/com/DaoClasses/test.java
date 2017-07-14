package com.DaoClasses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Project_Stage_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;

class A{
	public static List<Project_Stage_Master> getAllStage() {
        List<Project_Stage_Master> stages= new ArrayList<Project_Stage_Master>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            stages = session.createQuery("from Project_Stage_Master").list();
            System.out.println("Size is "+stages.size());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return stages;
        } finally {
            session.flush();
            session.close();
        }
        return stages;
    }  }
public class test { 
	public static void main(String args[])
	{
		
	}

}
